import {zip} from 'rxjs/observable/zip';
import {catchError} from 'rxjs/operators';

import {HttpClient} from '@angular/common/http';
import {Injectable} from '@angular/core';

@Injectable()
export class EnumService  {
    static enum_map_cache: any = {};
    static enum_data_cache: any = {};
    static enum_value_cache: any = {};
    constructor(
        private httpClient: HttpClient) {
    }

    load(): Promise<any> {
        // only works with promises
        // https://github.com/angular/angular/issues/15088
        return new Promise((resolve, reject) => {
            // http
            this.viaHttp(resolve, reject);
            // mock
        });
    }

    private viaHttp(resolve: any, reject: any) {
        zip(
            this.httpClient.get('assets/tmp/enum-data.json')
        ).pipe(
            // 接收其他拦截器后产生的异常消息
            catchError(([enumData]) => {
                resolve(null);
                return [enumData];
            })
        ).subscribe(([enumData]) => {

                // application data
                const res: any = enumData;
                // 循环数据的
                for (let i in res) {
                    let items = {};
                    for (let j of res[i]) {
                        items[j["id"]] = j["name"];
                    }
                    EnumService.enum_map_cache[i] = items;
                    EnumService.enum_value_cache[i] = Array.from(res[i],( item:any)=>item.id);
                }
                Object.assign(EnumService.enum_data_cache, res); // 可以直接访问
            },
            () => { },
            () => {
                resolve(null);
            });
    }

}
