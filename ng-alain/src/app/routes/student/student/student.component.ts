import { Component, OnInit } from '@angular/core';
import { NzMessageService, NzTabChangeEvent } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { SimpleTableColumn } from '@delon/abc';

@Component({
  selector: 'student-info',
  templateUrl: './student.component.html',
  styleUrls: ['./advanced.component.less'],
})

export class StudentComponent implements OnInit {

  constructor(
    public msg: NzMessageService,
    private http: _HttpClient
  ) {}

  ngOnInit() {
  }

}
