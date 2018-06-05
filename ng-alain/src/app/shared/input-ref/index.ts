import { NzMessageService } from 'ng-zorro-antd';
import { ChangeDetectionStrategy, Component, OnInit, ViewEncapsulation } from '@angular/core';
import { _HttpClient } from '@delon/theme';

@Component({
  selector: 'input-ref',
  encapsulation: ViewEncapsulation.None,
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './input-ref.component.html',
  styleUrls: ['./input.ref.less']
})

export class InputRefComponent implements OnInit {

  inputValue: string;
  optionGroups: any[];
  dataSet = [];

  constructor(
    public _msg: NzMessageService,
    private _http: _HttpClient
  ) {
  }

  ngOnInit(): void {
    setTimeout(() => {
      this.optionGroups = [{
        title: '话题',
        children: [{
          title: 'AntDesign',
          count: 10000
        },         {
          title: 'AntDesign UI',
          count: 10600
        }]
      }];
    }, 1000);
    this._http.post('student/findAll').subscribe((response: any) => {
      this.dataSet = response.data;
    });
  }

  onChange(value: any): void {
    console.log(value);
  }
}
