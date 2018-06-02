import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import {
  NzFormatEmitEvent,
  NzMessageService,
  NzModalService,
  NzTabChangeEvent,
  NzTreeComponent,
  NzTreeNode,
} from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { SimpleTableButton, SimpleTableColumn, SimpleTableComponent, SimpleTableData } from '@delon/abc';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UtilService } from '@shared/config/util-service';

@Component({
  selector: 'role',
  templateUrl: './role.component.html'
})

export class RoleComponent implements OnInit {
  @ViewChild('ntn') ntn: NzTreeComponent;
  @ViewChild('stc') stc: SimpleTableComponent;

  url = `role/findAll`;
  form: FormGroup;
  vo = this.newVO();
  checkboxChangeList = [];

  constructor(
    private fb: FormBuilder,
    public _msg: NzMessageService,
    private modalSrv: NzModalService,
    private _http: _HttpClient,
    public _util: UtilService
  ) {}

  roleColumns: SimpleTableColumn[] = [
    { title: '编号', index: 'rid.value', type: 'checkbox', },
    { title: '名称', index: 'rname' },
    { title: '别名', index: 'alias' },
    { title: '创建时间', type: 'date', index: 'createTime' },
    { title: '更新时间', type: 'date', index: 'updateTime' },
    { title: '删除', buttons: [ { text: '删除', type: 'del' , click:(role: any)=> { this.delete(role); }}]}
  ];


  checkboxChange(list: any[]) {
    this.checkboxChangeList = list;
  }

  dataChange(data: SimpleTableData[]) {
    return data.map((i: SimpleTableData, index: number) => {
      // i.disabled = index === 0;
      return i;
    });
  }

  ngOnInit(): void {
    this.form = this.fb.group({
      rname: [null],
      alias: [null]
    });

    this._http.post('permission/ref', { }).subscribe((response: any) => {
      // this.nodes = response.data;
      if(response.data) {
        response.data.forEach((node) => {
          let treeNode = new NzTreeNode(node);
          this.nodes.push(treeNode);
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
      }
    });
  }

  editPre(tpl: TemplateRef<{}>){
    if(this.checkboxChangeList.length === 0 || this.checkboxChangeList.length > 1){
      this._msg.warning("请选择一条数据！")
      return ;
    }
    this.editPermission(tpl,this.checkboxChangeList[0]);
  }

  editPermission(tpl: TemplateRef<{}>,role: any) {
    this.vo = this.newVO();
    this.modalSrv.create({
      nzTitle: '分配权限',
      nzContent: tpl,
      nzOkText: '保存',
      nzCancelText: '取消',
      nzBodyStyle: { height: 200 },
      nzOnOk: () => {
        // this.save();
        this.setPermission(role);
      }
    });
  }

  private setPermission(role: any) {
    let treeNodes = this.ntn.nzTreeService.getCheckedNodeList();
    this.vo = role;
    this.vo.ps = [];
    treeNodes.forEach((node) => {
      this.vo.ps.push(node.key);
    });
    this._http.post('role/setPermission', { ...this.vo }).subscribe((response: any) => {
      this._msg.success('权限分配成功');
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
    this._http.post('role/save', { ...this.vo }).subscribe((response: any) => {
      this._msg.success('保存成功');
    });
    this.stc.load();
  }

  delete(role: any) {
    this._http.post('role/delete', { ... role }).subscribe((response: any) => {
      this._msg.success('删除成功');
      this.stc.load();
    });
  }

  // 树形控件
  expandKeys = [ '1001', '10001' ];
  checkedKeys = [ '100011', '1002' ];
  selectedKeys = [ '10001', '100011' ];
  expandDefault = false;
  nodes = [];

  mouseAction(name: string, event: NzFormatEmitEvent): void {
    console.log(name, event);
    console.log('selectedNodes: %o', this.ntn.getSelectedNodeList());
  }

}
