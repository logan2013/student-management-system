import { NzMessageService } from 'ng-zorro-antd';
import { Component, Input, OnInit } from '@angular/core';
import { _HttpClient } from '@delon/theme';

@Component({
  selector: 'teacher-detail',
  templateUrl: './teacher-detail.component.html',
  styleUrls: ['./advanced.component.less'],
})

export class TeacherDetailComponent implements OnInit {

  @Input() teacher: any;
  @Input() parentComponent: any;

  constructor(
    public msg: NzMessageService,
    private http: _HttpClient
  ) {
  }

  ngOnInit(): void {
  }

  back(){
    this.parentComponent.back();
  }
}
