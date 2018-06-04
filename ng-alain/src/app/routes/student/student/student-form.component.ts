import { Component, OnInit } from '@angular/core';
import { NzMessageService, NzTabChangeEvent } from 'ng-zorro-antd';
import { _HttpClient } from '@delon/theme';
import { SimpleTableColumn } from '@delon/abc';
import { FormGroup, FormBuilder, Validators, FormArray } from '@angular/forms';

@Component({
  selector: 'student-form',
  templateUrl: './student-form.component.html'
})

export class StudentFormComponent implements OnInit {
  form: FormGroup;
  editIndex = -1;
  editObj = {};
  vo = this.newVO();

  constructor(
    private fb: FormBuilder,
    public _msg: NzMessageService,
    private _http: _HttpClient
  ) {}

  ngOnInit() {
    this.form = this.fb.group({
      sname: [null,[Validators.required]],
      sno: [null,[Validators.required]],
      className: [null,[Validators.required]],
      classAllName: [null,[Validators.required]],
      gender: [null,[Validators.required]],
      dorm: [null],
      phoneNum: [null,[Validators.required]],
      qqNum: [null],
      remark: [null],
      gradeName: [null],
      majorName: [null,[Validators.required]],
      politicalStatus: [null],
      status: [null],
      nation: [null],
      birthday: [null],
      idcard: [null,[Validators.required]],
      homes: this.fb.array([]),
    });
  }

  get homes() {
    return this.form.controls.homes as FormArray;
  }

  add() {
    this.homes.push(this.createHome());
    this.edit(this.homes.length - 1);
  }

  createHome(): FormGroup {
    return this.fb.group({
      name: [null, [Validators.required]],
      relation: [null, [Validators.required]],
      workplace: [null],
      telNum: [null, [Validators.required]],
      address: [null, [Validators.required]],
      wechat: [null],
    });
  }

  del(index: number) {
    this.homes.removeAt(index);
  }

  edit(index: number) {
    if (this.editIndex !== -1 && this.editObj) {
      this.homes.at(this.editIndex).patchValue(this.editObj);
    }
    this.editObj = { ...this.homes.at(index).value };
    this.editIndex = index;
  }

  save(index: number) {
    this.homes.at(index).markAsDirty();
    if (this.homes.at(index).invalid) return;
    this.editIndex = -1;
  }

  cancel(index: number) {
    if (!this.homes.at(index).value.key) {
      this.del(index);
    } else {
      this.homes.at(index).patchValue(this.editObj);
    }
    this.editIndex = -1;
  }

  newVO() {
    const vo: any = {};
    return vo;
  }

  _submitForm() {
    for (const i in this.form.controls) {
      this.form.controls[i].markAsDirty();
      this.form.controls[i].updateValueAndValidity();
    }
    if (this.form.invalid) return;
    this.vo.homes = this.form.controls.homes.value;
    this._http.post('student/save', { ...this.vo }).subscribe((response: any) => {
      this._msg.success('保存成功');
    });
  }


}
