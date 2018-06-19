import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { NzMessageService, NzTabChangeEvent } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { SimpleTableColumn, SimpleTableComponent, SimpleTableData } from '@delon/abc';
import { deepCopy } from '@delon/util';
import { Router } from '@angular/router';

@Component({
  selector: 'student-list',
  templateUrl: './student-list.component.html',
})

export class StudentListComponent implements OnInit {
  @ViewChild('stc') stc: SimpleTableComponent;
  data = [];
  view = 'list';
  student = {};
  checkboxChangeList = [];
  name = '学生基本信息';
  moduleName = 'student';
  total: number;

  constructor(
    private router: Router,
    public _msg: NzMessageService,
    private _http: _HttpClient
  ) {}

  columns: SimpleTableColumn[] = [
    { title: '编号', index: 'id', type: 'checkbox', fixed: 'left', width: '40px'},
    { title: '学号', index: 'sno' , type: 'link', fixed: 'left', width: '120px', click: (data: any) => { this.detail(data); }},
    { title: '姓名', index: 'sname' ,fixed: 'left', width: '100px'},
    { title: '班级全称', index: 'sysClassAllName' },
    { title: '性别', index: 'gender' },
    { title: '宿舍号', index: 'dorm' },
    { title: '联系电话', index: 'phoneNum' },
    { title: 'QQ 号', index: 'qqNum' },
    { title: '备注', index: 'remark' },
  ];

  ngOnInit(): void {
    // this.refreshData();
  }

  setTotal(){
    this.total = this.data.length;
  }

  detail(data: any){
    console.log(data);
    this.student = deepCopy(data);
    this.view = 'studentDetail';
  }

  back(){
    this.view = 'list';
  }

  add() {
    this.router.navigate(['/student/student-form']);
  }

  refreshData(){
    this._http.post('student/findAll').subscribe((response: any) => {
      this.data = response.data;
    });
  }

  dataChange(data: SimpleTableData[]) {
    return data.map((i: SimpleTableData, index: number) => {
      // i.disabled = index === 0;
      return i;
    });
  }

  checkboxChange(list: any[]) {
    this.checkboxChangeList = list;
  }

}
