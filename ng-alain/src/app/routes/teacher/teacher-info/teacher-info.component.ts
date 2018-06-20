import { Component, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { Router } from '@angular/router';
import { _HttpClient } from '@delon/theme';
import { deepCopy } from '@delon/util';

@Component({
  selector: 'teacher-info',
  templateUrl: './teacher-info.component.html'
})

export class TeacherInfoComponent implements OnInit {

  view = 'list';
  dataSet = [];
  teacher = {};

  constructor(
    private router: Router,
    public _msg: NzMessageService,
    private _http: _HttpClient
  ) {}

  ngOnInit(): void {
    this._http.post('teacher/findAll').subscribe((response: any) => {
      this.dataSet = response;
    });
  }

  detail(teacher: any){
    console.log(teacher);
    this.teacher = deepCopy(teacher);
    this.view = 'teacherDetail';
  }

  back(){
    this.view = 'list';
  }

  add(){
    this.router.navigate(['/teacher/teacher-form']);
  }

}
