import { Component, Input, OnInit } from '@angular/core';
import { NzMessageService, NzTabChangeEvent } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { SimpleTableColumn } from '@delon/abc';

@Component({
  selector: 'student-detail',
  templateUrl: './student-detail.component.html',
  styleUrls: ['./advanced.component.less'],
})

export class StudentDetailComponent implements OnInit {

  @Input() student: any;
  @Input() parentComponent: any;

  constructor(
    public msg: NzMessageService,
    private http: _HttpClient
  ) {}

  homesColumns: SimpleTableColumn[] = [
    { title: '编号', index: 'hid'},
    { title: '姓名', index: 'name' },
    { title: '工作单位', index: 'workplace' },
    { title: '联系电话', index: 'telNum' },
    { title: '家庭地址', index: 'address' },
    { title: '微信号', index: 'wechat' },
  ];

  awardColumns: SimpleTableColumn[] = [
    { title: '编号', index: 'aid'},
    { title: '获奖时间', type: 'date', index: 'awardTime' },
    { title: '获奖详情', index: 'info' }
  ];

  jobColumns: SimpleTableColumn[] = [
    { title: '编号', index: 'jid'},
    { title: '方式', index: 'mode' },
    { title: '就业单位', index: 'jobunit' },
    { title: '就业时间', type: 'date',index: 'jtime' },
    { title: '档案转递地址', index: 'fileAddress' },
    { title: '就业去向', index: 'jobwhere' ,fixed: 'right', width: '100px'}
  ];

  punishmentColumns: SimpleTableColumn[] = [
    { title: '编号', index: 'pid'},
    { title: '处分时间', type: 'date', index: 'ptime' },
    { title: '处分种类', index: 'ptype' },
    { title: '发文号', index: 'pno' },
    { title: '处分原因', index: 'info' },
    { title: '解除处分时间', type: 'date', index: 'removeTime' }
  ];

  subsidizeColumns: SimpleTableColumn[] = [
    { title: '编号', index: 'sid'},
    { title: '资助时间', type: 'date', index: 'stime' },
    { title: '资助详情', index: 'info' },
    { title: '受助类型', index: 'type' },
    { title: '受助等级', index: 'level' }
  ];

  ngOnInit() {
  }

  back(){
    this.parentComponent.back();
  }

}
