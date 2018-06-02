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

  constructor(
    private fb: FormBuilder,
    public msg: NzMessageService,
    private http: _HttpClient
  ) {}

  ngOnInit() {
    this.form = this.fb.group({
      sname: [null,[Validators.required]],
      sno: [null],
      className: [null],
      classAllName: [null],
      gender: [null],
      dorm: [null],
      phoneNum: [null],
      qqNum: [null],
      remark: [null],
      gradeName: [null],
      majorName: [null],
      politicalStatus: [null],
      status: [null],
      nation: [null],
      birthday: [null],
      idcard: [null],
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

  _submitForm() {
    for (const i in this.form.controls) {
      this.form.controls[i].markAsDirty();
      this.form.controls[i].updateValueAndValidity();
    }
    if (this.form.invalid) return;
  }

}
