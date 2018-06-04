import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { GradeComponent } from './grade/grade.component';
import { SystemRoutingModule } from './system-routing.module';
import { MajorComponent } from './major/major.component';
import { ClassComponent } from './class/class.component';

@NgModule({
  imports: [SharedModule, SystemRoutingModule],
  declarations: [
    GradeComponent,
    MajorComponent,
    ClassComponent
  ],
})
export class SystemModule {}
