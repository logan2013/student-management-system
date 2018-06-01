import {EnumService} from '@shared/config/enmu-service';
import {Injectable} from '@angular/core';

@Injectable()
export class UtilService {

  _enum: any = EnumService.enum_data_cache;
  _enumMap: any = EnumService.enum_map_cache;
  _enumValue: any = EnumService.enum_value_cache;

  constructor(private enumService: EnumService) {
  }

  compareFn = (o1: any, o2: any) => o1 && o2 ? o1.id === o2.id : o1 === o2;

  getUUID(): string {
    const timeMillis: number = new Date().getTime();
    const s1 = timeMillis.toString(16).substring(0, 11);
    const s2 = (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1)
      + (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
    return '0' + s1 + s2;
  }

  dateFormatter(_date): string {
    let _fmt = 'yyyy-MM-dd hh:mm:ss';
    const date = new Date(_date.value);

    const o = {
      'M+': date.getMonth() + 1, // 月份
      'd+': date.getDate(), // 日
      'h+': date.getHours(), // 小时
      'm+': date.getMinutes(), // 分
      's+': date.getSeconds(), // 秒
      'q+': Math.floor((date.getMonth() + 3) / 3), // 季度
      'S': date.getMilliseconds() // 毫秒
    };
    if (/(y+)/.test(_fmt)) _fmt = _fmt.replace(RegExp.$1, (date.getFullYear() + '').substr(4 - RegExp.$1.length));
    for (const k in o)
      if (new RegExp('(' + k + ')').test(_fmt)) _fmt = _fmt.replace(RegExp.$1, (RegExp.$1.length === 1) ? (o[k]) : (('00' + o[k]).substr(('' + o[k]).length)));
    return _fmt;
  }
}
