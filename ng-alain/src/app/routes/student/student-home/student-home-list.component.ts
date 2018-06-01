import { Component, OnInit } from '@angular/core';
import { NzMessageService, NzTabChangeEvent } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { SimpleTableColumn } from '@delon/abc';

@Component({
  selector: 'student-home-list',
  templateUrl: './student-home-list.component.html'
})

export class StudentHomeListComponent implements OnInit {

  constructor(
    public msg: NzMessageService,
    private http: _HttpClient
  ) {}

  ngOnInit() {
  }

}
