import { NzMessageService } from 'ng-zorro-antd';
import { Inject,Component, OnInit } from '@angular/core';
import { _HttpClient } from '@delon/theme';
import { Router } from '@angular/router';
import { DA_SERVICE_TOKEN, ITokenService } from '@delon/auth';

@Component({
  selector: 'app-dashboard-v1',
  templateUrl: './v1.component.html',
})
export class DashboardV1Component implements OnInit {
  constructor(
    private http: _HttpClient,
    public msg: NzMessageService,
    @Inject(DA_SERVICE_TOKEN) private tokenService: ITokenService,
    private router: Router
  ) {}

  todoData: any[] = [
    {
      completed: true,
      avatar: '1',
      name: '苏先生',
      content: `请告诉我，我应该说点什么好？`,
    },
    {
      completed: false,
      avatar: '2',
      name: 'はなさき',
      content: `ハルカソラトキヘダツヒカリ`,
    },
    {
      completed: false,
      avatar: '3',
      name: 'cipchk',
      content: `this world was never meant for one as beautiful as you.`,
    }
  ];

  webSite: any[] = [];
  salesData: any[] = [];
  offlineChartData: any[] = [];

  ngOnInit() {
    const token = this.tokenService.get() || {
      token: 'nothing',
      name: 'YDeity',
      avatar: './assets/img/zorro.svg',
      email: 'cipchk@qq.com',
    };
    if (JSON.stringify(token) === '{}') {
      this.router.navigateByUrl(this.tokenService.login_url);
    }
    this.http.get('/chart').subscribe((res: any) => {
      this.webSite = res.visitData.slice(0, 10);
      this.salesData = res.salesData;
      this.offlineChartData = res.offlineChartData;
    });
  }
}
