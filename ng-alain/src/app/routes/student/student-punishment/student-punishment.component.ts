import { Component, OnInit, TemplateRef, ViewChild } from '@angular/core';
import { NzMessageService, NzModalService, NzTabChangeEvent } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { SimpleTableColumn, SimpleTableComponent, SimpleTableData } from '@delon/abc';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { UtilService } from '@shared/config/util-service';
import { deepCopy } from '@delon/util';

@Component({
  selector: 'student-punishment',
  templateUrl: './student-punishment.component.html'
})

export class StudentPunishmentComponent implements OnInit {
  @ViewChild('stc') stc: SimpleTableComponent;

  url = `punishment/findAll`;
  form: FormGroup;
  vo = this.newVO();
  checkboxChangeList = [];
  isDisable = true;
  data = [];
  name = '学生违纪信息';
  moduleName = 'punishment';

  constructor(
    private fb: FormBuilder,
    public _msg: NzMessageService,
    private modalSrv: NzModalService,
    private _http: _HttpClient,
    public _util: UtilService
  ) {}

  columns: SimpleTableColumn[] = [
    { title: '编号', index: 'aid', type: 'checkbox', fixed: 'left', width: '40px'},
    { title: '姓名', index: 'sname' ,fixed: 'left', width: '100px'},
    { title: '学号', index: 'sno' ,fixed: 'left', width: '120px'},
    { title: '专业', index: 'majorName' },
    { title: '年级', index: 'gradeName' },
    { title: '班级', index: 'sysClassName' },
    { title: '处分时间', type: 'date', index: 'ptime' },
    { title: '处分种类', index: 'ptype' },
    { title: '发文号', index: 'pno' },
    { title: '处分原因', index: 'info' },
    { title: '解除处分时间', type: 'date', index: 'removeTime' },
    { title: '删除', buttons: [ { text: '删除', type: 'del' , click:(job: any)=> { this.delete(job); }}]}
  ];

  ngOnInit() {
    this.refreshData();
    this.form = this.fb.group({
      studentNum: [null, [Validators.required]],
      ptime: [null, [Validators.required]],
      ptype: [null, [Validators.required]],
      pno: [null, [Validators.required]],
      info: [null, [Validators.required]],
      removeTime: [null]
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

  invalid():boolean {
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
        if(this.invalid()){
          this.save();
        }else{
          return false;
        }
      }
    });
  }

  save() {
    this._http.post('punishment/save', { ...this.vo }).subscribe((response: any) => {
      this._msg.success('保存成功');
      // this.stc.load();
      this.refreshData();
    });
  }

  delete(job: any) {
    this._http.post('punishment/delete', { ... job }).subscribe((response: any) => {
      this._msg.success('删除成功');
      // this.stc.load();
      this.refreshData();
    });
  }

  editPre(tpl: TemplateRef<{}>){
    if(this.checkboxChangeList.length === 0 || this.checkboxChangeList.length > 1){
      this._msg.warning("请选择一条数据！")
      return ;
    }
    this.editSrv(tpl,this.checkboxChangeList[0]);
  }

  editSrv(tpl: TemplateRef<{}>,entity: any) {
    this.vo = this.newVO();
    entity.studentNum = entity.sno;
    this.vo =  deepCopy(entity);
    this.isDisable = false;
    this.modalSrv.create({
      nzTitle: '修改',
      nzContent: tpl,
      nzOkText: '保存',
      nzCancelText: '取消',
      nzBodyStyle: { height: 200 },
      nzOnOk: () => {
        if(this.invalid()){
          this.edit();
        }else{
          return false;
        }
      }
    });
  }

  edit(){
    this._http.post('punishment/update', { ...this.vo }).subscribe((response: any) => {
      this._msg.success('修改成功');
      // this.stc.load();
      this.refreshData();
    });
  }

  refreshData(){
    this._http.post('punishment/findAll').subscribe((response: any) => {
      this.data = response.data;
    });
  }

}
