import { Component, OnInit, ViewChild } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { UtilService } from '@shared/config/util-service';
import { SimpleTableComponent } from '@delon/abc';
import { NzCascaderComponent } from 'ng-zorro-antd/src/cascader/nz-cascader.component';

@Component({
  selector: 'system-class',
  templateUrl: './class.component.html'
})

export class ClassComponent implements OnInit {

  editStatus = -1; // -1 是未添加，0 新添加未保存过
  status: number;
  clazz = [];
  vo = this.newVO();
  nzOptions = [];
  values: any;
  majorGrade;

  constructor(
    public _msg: NzMessageService,
    private _http: _HttpClient,
    private _util: UtilService
  ) {}

  ngOnInit(): void {
    this._http.post('sysclass/findAll').subscribe((response: any) => {
      this.clazz = response.data;
    });
    this._http.post('major/classRef').subscribe((response: any) => {
      this.nzOptions = response;
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
    this.vo.classGradeId = this.vo.classGradeId[this.vo.classGradeId.length - 1];
    this._http.post('sysclass/save', { ...this.vo }).subscribe((response: any) => {
      this._msg.success('保存成功');
      this.editStatus = -1;
      this.refreshData();
    });
  }

  refreshData(){
    this._http.post('sysclass/findAll').subscribe((response: any) => {
      this.clazz = response.data;
    });
  }

  del(data){
    this._http.post('sysclass/delete', { ...data }).subscribe((response: any) => {
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
    this.vo.classGradeId = data.classGradeId;
  }

  changeName(){
    this.vo.allName = (this.majorGrade ? this.majorGrade + '-': '') + (this.vo.name ? this.vo.name : '');
  }

  majorGradeChange(values: any){
    this.vo.allName = this.vo.classGradeId + this.vo.name;
    let id = values[values.length - 1];
    this._http.post('grade/findOne', { id: id }).subscribe((response: any) => {
      this.majorGrade = response.majorName + '-' + response.name;
      this.vo.allName = this.majorGrade + '-' + (this.vo.name ? this.vo.name : '');
    });
  }

}
