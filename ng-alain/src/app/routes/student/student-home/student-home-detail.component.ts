import { Component, OnInit } from '@angular/core';
import { NzMessageService, NzTabChangeEvent } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { SimpleTableColumn } from '@delon/abc';

@Component({
  selector: 'student-home-detail',
  templateUrl: './student-home-detail.component.html'
})

export class StudentHomeDetailComponent implements OnInit {
  homes: {};

  constructor(
    public msg: NzMessageService,
    private http: _HttpClient
  ) {}

  homesColumns: SimpleTableColumn[] = [
    { title: '编号', index: 'id'},
    { title: '姓名', index: 'name' },
    { title: '工作单位', index: 'workplace' },
    { title: '联系电话', index: 'telNum' },
    { title: '家庭地址', index: 'address' },
    { title: '微信号', index: 'wechat' },
  ];

  ngOnInit() {
  }

}
