import { Component, OnInit, ViewChild } from '@angular/core';
import { MentionOnSearchTypes, NzMessageService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { UtilService } from '@shared/config/util-service';
import { SimpleTableComponent } from '@delon/abc';
import { NzCascaderComponent } from 'ng-zorro-antd/src/cascader/nz-cascader.component';

@Component({
  selector: 'system-class',
  templateUrl: './class.component.html',
  styles: [`
    .ant-avatar.ant-avatar-sm {
      width: 14px;
      height: 14px;
      margin-right: 8px;
      position: relative
    }
  `]
})

export class ClassComponent implements OnInit {

  editStatus = -1; // -1 是未添加，0 新添加未保存过
  status: number;
  clazz = [];
  vo = this.newVO();
  nzOptions = [];
  values: any;
  majorGrade;
  loading = false;
  teachers = [];
/*  [
    { name: 'React', type: 'JavaScript', icon: 'https://zos.alipayobjects.com/rmsportal/LFIeMPzdLcLnEUe.svg' },
    { name: 'Angular', type: 'JavaScript', icon: 'https://zos.alipayobjects.com/rmsportal/PJTbxSvzYWjDZnJ.png' },
    { name: 'Dva', type: 'Javascript', icon: 'https://zos.alipayobjects.com/rmsportal/EYPwSeEJKxDtVxI.png' },
    { name: 'Flask', type: 'Python', icon: 'https://zos.alipayobjects.com/rmsportal/xaypBUijfnpAlXE.png' },
  ];*/

  constructor(
    public _msg: NzMessageService,
    private _http: _HttpClient,
    public _util: UtilService
  ) {}

  ngOnInit(): void {
    this._http.post('sysclass/findAll').subscribe((response: any) => {
      this.clazz = response;
    });
    this._http.post('major/classRef').subscribe((response: any) => {
      this.nzOptions = response;
    });
    this._http.post('teacher/findAll').subscribe((response: any) => {
      this.teachers = response;
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
    this.vo.tid = this.vo.teacher.tid;
    this._http.post('sysclass/save', { ...this.vo }).subscribe((response: any) => {
      this._msg.success('保存成功');
      this.editStatus = -1;
      this.refreshData();
    });
  }

  refreshData(){
    this._http.post('sysclass/findAll').subscribe((response: any) => {
      this.clazz = response;
    });
  }

  del(data){
    data.classGradeId = '';
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

  valueWith = data => data.tname;

  onSelect(value: any): void {
    this.vo.teacher = value;
  }

}
