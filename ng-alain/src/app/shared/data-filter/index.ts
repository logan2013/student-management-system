import { Component, Input, OnInit } from '@angular/core';
import { NzMessageService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { UtilService } from '@shared/config/util-service';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'data-filter',
  templateUrl: './data-filter.component.html'
})

export class DataFilterComponent implements OnInit{
  @Input() name: string; // 模块名称
  @Input() parentComponent: any;
  nzOptions = [];
  teachers = [];
  expandForm = false;
  form: FormGroup;
  vo = this.newVO();
  loading = false;
  stimes = [];
  ptimes = [];
  atimes = [];

  constructor(
    private fb: FormBuilder,
    private _http: _HttpClient,
    private _msg: NzMessageService,
    public _util: UtilService
  ) {}

  ngOnInit(): void {
    switch (this.name){
      case 'subsidize':
        this._http.get('subsidize/groupStime').subscribe((response: any) => {
          this.stimes = response.data;
          console.log(this.stimes);
        });
        break;
      case 'punishment':
        this._http.get('punishment/groupStime').subscribe((response: any) => {
          this.ptimes = response.data;
          console.log(this.ptimes);
        });
        break;
      case 'award':
        this._http.get('award/groupStime').subscribe((response: any) => {
          this.atimes = response.data;
          console.log(this.atimes);
        });
        break;
    }
    this.form = this.fb.group({
      classId: [null],
      sno: [null],
      sname: [null],
      status: [null],
      politicalStatus: [null],
      tid: [null],
      jobMode: [null],
      awardTime: [null],
      ptype: [null],
      slevel: [null],
      stype: [null],
      stime: [null],
      ptime: [null]
    });
    this._http.get('major/getClassCascader').subscribe((response: any) => {
      this.nzOptions = response;
    });
    this._http.get('teacher/findAll').subscribe((response: any) => {
      this.teachers = response.data;
    });
  }

  newVO() {
    const vo: any = {};
    return vo;
  }

  getData() {
    this.loading = true;
    this._http.post(this.name + '/filter', { ...this.vo }).subscribe((response: any) => {
      this.parentComponent.data = response.data;
      this.parentComponent.setTotal();
      this.loading = false;
    });
  }

  reset(ls: any[]) {
    for (const item of ls) item.value = false;
    this.getData();
  }
}
