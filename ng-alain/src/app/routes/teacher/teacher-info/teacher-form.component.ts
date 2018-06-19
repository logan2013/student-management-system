import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NzMessageService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { Router } from '@angular/router';
import { UtilService } from '@shared/config/util-service';
import { isMobile } from '@delon/util';

@Component({
  selector: 'teacher-form',
  templateUrl: './teacher-form.component.html'
})

export class TeacherFormComponent implements OnInit {
  form: FormGroup;
  vo = this.newVO();

  constructor(
    private fb: FormBuilder,
    public _msg: NzMessageService,
    private _http: _HttpClient,
    private router: Router,
    public _util: UtilService
  ) {}

  ngOnInit(): void {
    this.form = this.fb.group({
      tno: [null,[Validators.required]],
      tname: [null,[Validators.required]],
      gender: [null,[Validators.required]],
      nation: [null,[Validators.required]],
      birthday: [null,[Validators.required]],
      politicalStatus: [null,[Validators.required]],
      schooling: [null,[Validators.required]],
      title: [null,[Validators.required]],
      phoneNum: [null,[Validators.required]],
      dept: [null],
      inTime: [null,[Validators.required]]
    });
  }

  newVO() {
    const vo: any = {};
    return vo;
  }

  _submitForm() {
    for (const i in this.form.controls) {
      this.form.controls[i].markAsDirty();
      this.form.controls[i].updateValueAndValidity();
    }
    if (this.form.invalid) return;
    this._http.post('teacher/save', { ...this.vo }).subscribe((response: any) => {
      this._msg.success('保存成功');
      this.router.navigate(['/teacher/teacher-info']);
    });
  }

}
