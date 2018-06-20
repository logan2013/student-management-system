import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UtilService } from '@shared/config/util-service';
import { NzMessageService } from 'ng-zorro-antd';
import { _HttpClient, SettingsService } from '@delon/theme';

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
    private _http: _HttpClient,
    public settings: SettingsService,
    public _msg: NzMessageService
  ){}

  ngOnInit(): void {
    this._http.get('user/getUser').subscribe((response: any) => {
      this.vo = response.data;
    });
    this.profileForm = this.fb.group({
      tid: [ null,[Validators.required]],
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
    this._http.post('teacher/update',{ ...this.vo }).subscribe((response: any) => {
      this._msg.success('修改成功');
      this.settings.user.name = this.vo.tname;
    });
  }

  pwdSave() {
    if (!this.pwd.old_password || this.pwd.old_password !== this.vo.password) {
      return this._msg.error('无效的旧密码 ... ');
    }
    if (!this.pwd.new_password || this.pwd.new_password.length < 5 || this.pwd.new_password === this.vo.password) {
      return this._msg.error('无效的新密码');
    }
    if (!this.pwd.confirm_new_password || this.pwd.new_password !== this.pwd.confirm_new_password) {
      return this._msg.error('无效的重复输入');
    }
    this.vo.password = this.pwd.new_password;
    this._http.post('teacher/updatePassword',{ ...this.vo }).subscribe((response: any) => {
      this._msg.success('密码修改成功');
      this.pwd.old_password = '';
      this.pwd.new_password = '';
      this.pwd.confirm_new_password = '';
    });
  }

}
