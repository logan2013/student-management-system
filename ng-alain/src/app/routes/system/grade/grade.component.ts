import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';

@Component({
  selector: 'system-grade',
  templateUrl: './grade.component.html'
})

export class GradeComponent implements OnInit {

  editStatus = -1; // -1 是未添加，0 新添加未保存过
  status: number;
  grades = [];
  vo = this.newVO();

  constructor(
    public _msg: NzMessageService,
    private _http: _HttpClient
  ) {}

  ngOnInit(): void {
    this._http.post('grade/findAll').subscribe((response: any) => {
      this.grades = response.data;
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
    this._http.post('grade/save', { ...this.vo }).subscribe((response: any) => {
      this._msg.success('保存成功');
      this.editStatus = -1;
      this.refreshData();
    });
  }

  refreshData(){
    this._http.post('grade/findAll').subscribe((response: any) => {
      this.grades = response.data;
    });
  }

  del(data){
    this._http.post('grade/delete', { ...data }).subscribe((response: any) => {
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
