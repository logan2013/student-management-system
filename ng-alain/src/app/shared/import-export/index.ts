import { Component, Input } from '@angular/core';
import { NzMessageService, NzModalService, NzNotificationService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { XlsxService } from '@delon/abc';
import { CustomModalWidget } from '@shared/json-schema/widgets/custom-modal/custom-modal.widget';

@Component({
  selector: 'import-export',
  templateUrl: './import-export.component.html'
})

export class ImportAndExportComponent {
  @Input() name: string;
  @Input() moduleName: string;
  @Input() parentComponent: any;
  isImport = false;

  constructor(
    private msg: NzMessageService,
    private _http: _HttpClient,
    private xlsx: XlsxService,
    private notification: NzNotificationService,
    private modalSrv: NzModalService,
    private _msg: NzMessageService,
  ) {}

  download() {
    this._http.get(this.moduleName + '/template').subscribe((response: any) => {
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
      this._http.post(this.moduleName + '/import', { ...res.Sheet1 }).subscribe((response: any) => {
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
            this._http.get(this.moduleName + '/saveImport', { ...res.Sheet1 }).subscribe((response: any) => {
              if(response.code === 555){
                this.notification.create('error', '错误信息', response.msg,{ nzDuration: 0 });
              }
              this.parentComponent.refreshData();
              this.isImport = false;
            });
          },
          nzOnCancel: () => {
            this._http.get(this.moduleName + '/cancelImport', { ...res.Sheet1 }).subscribe((response: any) => {
            });
          }
        });
      });
    });
    node.value = '';
  }

  exportAll() {
    const data = [this.parentComponent.columns.map(i => i.exported !== false ? i.title : '')];
    this.parentComponent.data.forEach(i =>
      data.push(this.parentComponent.columns.map(c => i[c.index as string])),
    );
    this.xlsx.export({
      sheets: [
        {
          data: data,
          name: this.name,
        },
      ],
      filename: this.name + '.xlsx',
    });
  }

  export() {
    if (this.parentComponent.checkboxChangeList.length === 0) {
      this._msg.warning('请选择数据！');
      return;
    }
    const data = [this.parentComponent.columns.map(i => i.exported !== false ? i.title : '')];
    this.parentComponent.checkboxChangeList.forEach(i =>
      data.push(this.parentComponent.columns.map(c => i[c.index as string])),
    );
    this.xlsx.export({
      sheets: [
        {
          data: data,
          name: this.name,
        },
      ],
      filename: this.name + '.xlsx',
    });
  }

}
