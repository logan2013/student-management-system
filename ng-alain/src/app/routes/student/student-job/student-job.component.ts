import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { NzMessageService, NzModalService, NzTabChangeEvent } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { SimpleTableColumn, SimpleTableComponent, SimpleTableData, XlsxService } from '@delon/abc';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UtilService } from '@shared/config/util-service';
import { deepCopy } from '@delon/util';
import { CustomModalWidget } from '@shared/json-schema/widgets/custom-modal/custom-modal.widget';

@Component({
  selector: 'student-job',
  templateUrl: './student-job.component.html',
})

export class StudentJobComponent implements OnInit {
  @ViewChild('stc') stc: SimpleTableComponent;

  url = `job/findAll`;
  form: FormGroup;
  vo = this.newVO();
  checkboxChangeList = [];
  isDisable = true;
  data = [];
  name = '学生就业信息';
  isImport = false;

  constructor(
    private fb: FormBuilder,
    public _msg: NzMessageService,
    private modalSrv: NzModalService,
    private _http: _HttpClient,
    public _util: UtilService,
    private xlsx: XlsxService,
  ) {
  }

  jobColumns: SimpleTableColumn[] = [
    { title: '编号', index: 'jid', type: 'checkbox', fixed: 'left', width: '40px' },
    { title: '姓名', index: 'sname', fixed: 'left', width: '100px' },
    { title: '学号', index: 'sno', fixed: 'left', width: '120px' },
    { title: '专业', index: 'majorName' },
    { title: '年级', index: 'gradeName' },
    { title: '班级', index: 'sysClassName' },
    { title: '方式', index: 'mode' },
    { title: '就业单位', index: 'jobunit' },
    { title: '就业时间', type: 'date', index: 'jtime' },
    { title: '档案转递地址', index: 'fileAddress' },
    { title: '就业去向', index: 'jobwhere', fixed: 'right', width: '100px' },
    {
      title: '删除', exported: false, buttons: [{
        text: '删除', type: 'del', click: (job: any) => {
          this.delete(job);
        },
      }],
    },
  ];

  ngOnInit() {
    this.refreshData();
    this.form = this.fb.group({
      jobsno: [null, [Validators.required]],
      jtime: [null, [Validators.required]],
      jobunit: [null, [Validators.required]],
      fileAddress: [null, [Validators.required]],
      jobwhere: [null, [Validators.required]],
      mode: [null, [Validators.required]],
    });
  }

  refreshData() {
    this._http.post('job/findAll').subscribe((response: any) => {
      this.data = response.data;
    });
  }

  dataChange(data: SimpleTableData[]) {
    return data.map((i: SimpleTableData, index: number) => {
      // i.disabled = index === 0;
      return i;
    });
  }

  checkboxChange(list: any[]) {
    this.checkboxChangeList = list;
  }

  newVO() {
    const vo: any = {};
    return vo;
  }

  invalid(): boolean {
    for (const i in this.form.controls) {
      if (this.form.controls[i].invalid) {
        this.modalSrv.error({
          nzTitle: '表单验证未通过',
          nzContent: '请填写必选项信息！',
        });
        return false;
      }
    }
    return true;
  }

  add(tpl: TemplateRef<{}>) {
    this.vo = this.newVO();
    this.isDisable = true;
    this.modalSrv.create({
      nzTitle: '新建',
      nzContent: tpl,
      nzOkText: '保存',
      nzCancelText: '取消',
      nzBodyStyle: { height: 200 },
      nzOnOk: () => {
        if (this.invalid()) {
          this.save();
        } else {
          return false;
        }
      },
    });
  }

  save() {
    this._http.post('job/save', { ...this.vo }).subscribe((response: any) => {
      this._msg.success('保存成功');
      // this.stc.load();
      this.refreshData();
    });
  }

  delete(job: any) {
    this._http.post('job/delete', { ...job }).subscribe((response: any) => {
      this._msg.success('删除成功');
      // this.stc.load();
      this.refreshData();
    });
  }

  editPre(tpl: TemplateRef<{}>) {
    if (this.checkboxChangeList.length === 0 || this.checkboxChangeList.length > 1) {
      this._msg.warning('请选择一条数据！');
      return;
    }
    this.editSrv(tpl, this.checkboxChangeList[0]);
  }

  editSrv(tpl: TemplateRef<{}>, entity: any) {
    this.vo = this.newVO();
    entity.jobsno = entity.sno;
    this.vo = deepCopy(entity);
    this.isDisable = false;
    this.modalSrv.create({
      nzTitle: '修改',
      nzContent: tpl,
      nzOkText: '保存',
      nzCancelText: '取消',
      nzBodyStyle: { height: 200 },
      nzOnOk: () => {
        // this.save();
        if (this.invalid()) {
          this.edit();
        } else {
          return false;
        }
      },
    });
  }

  edit() {
    this._http.post('job/update', { ...this.vo }).subscribe((response: any) => {
      this._msg.success('修改成功');
      this.stc.load();
    });
  }

  exportAll() {
    const data = [this.jobColumns.map(i => i.exported !== false ? i.title : '')];
    this.data.forEach(i =>
      data.push(this.jobColumns.map(c => i[c.index as string])),
    );
    this.xlsx.export({
      sheets: [
        {
          data: data,
          name: '学生就业信息',
        },
      ],
      filename: '学生就业信息.xlsx',
    });
  }

  export() {
    if (this.checkboxChangeList.length === 0 || this.checkboxChangeList.length > 1) {
      this._msg.warning('请选择数据！');
      return;
    }
    const data = [this.jobColumns.map(i => i.exported !== false ? i.title : '')];
    this.checkboxChangeList.forEach(i =>
      data.push(this.jobColumns.map(c => i[c.index as string])),
    );
    this.xlsx.export({
      sheets: [
        {
          data: data,
          name: '学生就业信息',
        },
      ],
      filename: '学生就业信息.xlsx',
    });
  }

  download() {
    this._http.post('job/template', { ...this.vo }).subscribe((response: any) => {
      const data = [response.data.map(i => i.title)];
      this.xlsx.export({
        sheets: [
          {
            data: data,
            name: 'Sheet1',
          },
        ],
        filename: this.name + '导入模板.xlsx',
      });
    });
  }

  import() {
    this.isImport = !this.isImport;
  }

  change(e: Event) {
    const node = e.target as HTMLInputElement;
    this.xlsx.import(node.files[0]).then(res => {
      // this.importData = res;
      this._http.post('job/import', { ...res.Sheet1 }).subscribe((response: any) => {
        this.modalSrv.create({
          nzTitle: '预览',
          nzContent: CustomModalWidget,
          nzComponentParams: {
            dataSet: response.data.listData,
            columns: response.data.columns,
          },
          nzOkText: '确认导入',
          nzWidth: 1200,
          nzCancelText: '取消',
          nzOnOk: () => {
            this._http.get('job/saveImport', { ...res.Sheet1 }).subscribe((response: any) => {
              this.refreshData();
              this.isImport = false;
            });
          },
          nzOnCancel: () => {
            this._http.get('job/cancelImport', { ...res.Sheet1 }).subscribe((response: any) => {

            });
          }
        });
      });
    });
    node.value = '';
  }

}
