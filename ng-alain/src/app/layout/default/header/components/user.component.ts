import { Component, Inject, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { _HttpClient, SettingsService } from '@delon/theme';
import { DA_SERVICE_TOKEN, ITokenService } from '@delon/auth';

@Component({
  selector: 'header-user',
  template: `
  <nz-dropdown nzPlacement="bottomRight">
    <div class="item d-flex align-items-center px-sm" nz-dropdown>
      <nz-avatar [nzSrc]="settings.user.avatar" nzSize="small" class="mr-sm"></nz-avatar>
      {{settings.user.name}}
    </div>
    <div nz-menu class="width-sm">
      <div nz-menu-item (click)="account()"><i class="anticon anticon-user mr-sm"></i>个人中心</div>
      <li nz-menu-divider></li>
      <div nz-menu-item (click)="logout()"><i class="anticon anticon-setting mr-sm"></i>退出登录</div>
    </div>
  </nz-dropdown>
  `,
})
export class HeaderUserComponent implements OnInit {
  constructor(
    public settings: SettingsService,
    private router: Router,
    private _http: _HttpClient,
    @Inject(DA_SERVICE_TOKEN) private tokenService: ITokenService,
  ) {}

  ngOnInit(): void {
    this.tokenService.change().subscribe((res: any) => {
      if(res && res.user) {
        this.settings.setUser(res);
        this.settings.user.name = res.user.tname;
        this.settings.user.avatar = './assets/tmp/img/avatar.jpg';
        this.settings.user.email = res.user.tno;
        this.settings.user.tno = res.user.tno;
      }
    });
    // mock
    const token = this.tokenService.get() || {
      token: 'nothing',
      name: 'YDeity',
      avatar: './assets/logo-color.svg',
      email: '1334112864@qq.com',
    };
    this.tokenService.set(token);
  }

  account(){
    this.router.navigate(['/teacher/teacher-setting']);
  }

  logout() {
    this._http.get('user/logout');
    this.tokenService.clear();
    this.router.navigateByUrl(this.tokenService.login_url);
  }
}
