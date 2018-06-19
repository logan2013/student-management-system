import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UtilService } from '@shared/config/util-service';
import { NzMessageService } from 'ng-zorro-antd';

@Component({
  selector: 'teacher-setting',
  templateUrl: './teacher-setting.component.html'
})

export class TeacherSettingComponent implements OnInit {
  vo = this.newVO();
  active = 1;
  profileForm: FormGroup;
  pwd = {
    old_password: '',
    new_password: '',
    confirm_new_password: '',
  };

  constructor(
    private fb: FormBuilder,
    public _util: UtilService,
    public _msg: NzMessageService
  ){}

  ngOnInit(): void {
    this.profileForm = this.fb.group({
      tname: [ null,[Validators.required]],
      phoneNum: [ null,],
      title: [ null,],
      schooling: [ null,],
      politicalStatus: [ null,],
      dept: [ null,]
    });
  }

  newVO() {
    const vo: any = {};
    return vo;
  }

  profileSave(event, value) {
    console.log('profile value', value);
  }

  pwdSave() {
    if (!this.pwd.old_password) {
      return this._msg.error('invalid old password');
    }
    if (!this.pwd.new_password) {
      return this._msg.error('invalid new password');
    }
    if (!this.pwd.confirm_new_password) {
      return this._msg.error('invalid confirm new password');
    }
    console.log('pwd value', this.pwd);
  }

}
