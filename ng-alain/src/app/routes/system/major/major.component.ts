import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';

@Component({
  selector: 'system-major',
  templateUrl: './major.component.html'
})

export class MajorComponent implements OnInit {

  editStatus = -1; // -1 是未添加，0 新添加未保存过
  status: number;
  majors = [];
  vo = this.newVO();

  constructor(
    public _msg: NzMessageService,
    private _http: _HttpClient
  ) {}

  ngOnInit(): void {
    this._http.post('major/findAll').subscribe((response: any) => {
      this.majors = response.data;
    });
  }

  newVO() {
    const vo: any = {};
    return vo;
  }

  add(){
    this.vo = this.newVO();
    this.editStatus = 0;
  }

  save(){
    this._http.post('major/save', { ...this.vo }).subscribe((response: any) => {
      this._msg.success('保存成功');
      this.editStatus = -1;
      this.refreshData();
    });
  }

  refreshData(){
    this._http.post('major/findAll').subscribe((response: any) => {
      this.majors = response.data;
    });
  }

  del(data){
    this._http.post('major/delete', { ...data }).subscribe((response: any) => {
      this._msg.success('删除成功');
      this.refreshData();
    });
  }

  cancel(){
    this.editStatus = -1;
    this.refreshData();
  }

  edit(data: any){
    this.editStatus = 0;
    this.vo = data;
  }

}
