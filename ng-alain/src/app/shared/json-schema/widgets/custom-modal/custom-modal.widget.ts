import { Component, Input } from '@angular/core';

@Component({
  selector: 'custom-modal',
  template: './custom-modal.component.html'
})

export class CustomModalWidget{

  static readonly KEY = 'custom-modal';
  @Input() dataSet: any;
  @Input() columns: any;

}
