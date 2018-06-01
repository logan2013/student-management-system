import { Component, OnInit, TemplateRef } from '@angular/core';
import { NzMessageService, NzTabChangeEvent, NzModalService } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { SimpleTableColumn } from '@delon/abc';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UtilService } from '@shared/config/util-service';

export interface TreeNodeInterface {
  id: number;
  name: string;
  permissionCode: string;
  ability: number;
  generatemenu: number;
  level: number;
  expand: boolean;
  children?: TreeNodeInterface[];
}

@Component({
  selector: 'permission',
  templateUrl: './permission.component.html'
})

export class PermissionComponent implements OnInit {

  form: FormGroup;
  vo = this.newVO();
  expandDataCache = {};
  data = [];

  constructor(
    private fb: FormBuilder,
    public _msg: NzMessageService,
    private modalSrv: NzModalService,
    private _http: _HttpClient,
    public _util: UtilService
  ) {}

  collapse(array: TreeNodeInterface[], data: TreeNodeInterface, $event: boolean): void {
    if ($event === false) {
      if (data.children) {
        data.children.forEach(d => {
          const target = array.find(a => a.id === d.id);
          target.expand = false;
          this.collapse(array, target, false);
        });
      } else {
        return;
      }
    }
  }

  convertTreeToList(root: object): TreeNodeInterface[] {
    const stack = [];
    const array = [];
    const hashMap = {};
    stack.push({ ...root, level: 0, expand: false });

    while (stack.length !== 0) {
      const node = stack.pop();
      this.visitNode(node, hashMap, array);
      if (node.children) {
        for (let i = node.children.length - 1; i >= 0; i--) {
          stack.push({ ...node.children[ i ], level: node.level + 1, expand: false, parent: node });
        }
      }
    }

    return array;
  }

  visitNode(node: TreeNodeInterface, hashMap: object, array: TreeNodeInterface[]): void {
    if (!hashMap[ node.id ]) {
      hashMap[ node.id ] = true;
      array.push(node);
    }
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      text: [null],
      i18n: [null],
      icon: [null],
      generatemenu: [null],
      shortcut_root: [null],
      group: [null],
      hideInBreadcrumb: [null],
      link: [null],
      acl: [null],
      pid: [null]
    });
    this._http.get('permission/findAll').subscribe((res: any) => {
      this.data = res.data;
      if( this.data ){
        this.data.forEach( (item) => {
          this.expandDataCache[ item.id ] = this.convertTreeToList(item);
        });
      }
    });
  }

  newVO() {
    const vo: any = {};
    return vo;
  }

  add(tpl: TemplateRef<{}>) {
    this.vo = this.newVO();
    this.modalSrv.create({
      nzTitle: '新建',
      nzContent: tpl,
      nzOkText: '保存',
      nzCancelText: '取消',
      nzBodyStyle: { height: 200 },
      nzOnOk: () => {
        this.save();
/*        if(this.invalid()){
        }else {
          return false;
        }*/
      }
    });
  }

  invalid() {
    for (const i in this.form.controls) {
      if (this.form.controls[i].invalid) {
        this.modalSrv.error({
          nzTitle: '表单验证未通过',
          nzContent: '请填写必选项信息！',
        });
        return false;
      }
    }
  }

  save() {
    this._http.post('permission/save', { ...this.vo }).subscribe((response: any) => {
      this._msg.success('保存成功');
    });
    this.refreshData();
  }

  refreshData() {
    this._http.get('permission/findAll').subscribe((res: any) => {
      this.data = res.data;
      if( this.data ){
        this.data.forEach( (item) => {
          this.expandDataCache[ item.id ] = this.convertTreeToList(item);
        });
      }
    });
  }


}
