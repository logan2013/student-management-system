import { Component, OnInit, TemplateRef } from '@angular/core';
import { NzMessageService, NzTabChangeEvent } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { SimpleTableColumn } from '@delon/abc';
import { deepCopy } from '@delon/util';
import { Router } from '@angular/router';

@Component({
  selector: 'student-list',
  templateUrl: './student-list.component.html',
})

export class StudentListComponent implements OnInit {
  dataSet = [];
  view = 'list';
  student = {};

  constructor(
    private router: Router,
    public _msg: NzMessageService,
    private _http: _HttpClient
  ) {}

  ngOnInit(): void {
    this._http.post('student/findAll').subscribe((response: any) => {
      this.dataSet = response.data;
    });
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
}
