import {Pipe, PipeTransform} from '@angular/core';

import {EnumService} from '@shared/config/enmu-service';
import {HttpClient} from '@angular/common/http';
import {zip} from 'rxjs/observable/zip';
import {catchError} from 'rxjs/operators';


@Pipe({name: 'enumLabel'})
export class EnumLabelPipe implements PipeTransform {
    enum_map_cache = {};
    constructor(
        private httpClient: HttpClient) {
        this.viaHttp(()=>{},()=>{});
    }
    transform(value: any, topic: string): string {
        return this.enum_map_cache[topic][value];
    }

    private viaHttp(resolve: any, reject: any) {
        zip(
            this.httpClient.get('assets/enum-data.json')
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
                for(let i in res){
                    let items = {};
                    for(let j of res[i]){
                        items[j["id"]] = j["name"];
                    }
                    this.enum_map_cache[i] = items;
                }
            },
            () => { },
            () => {
                resolve(null);
            });
    }



}
