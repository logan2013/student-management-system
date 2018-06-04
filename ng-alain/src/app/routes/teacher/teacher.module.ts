import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { TeacherRoutingModule } from './teacher-routing.module';
import { TeacherInfoComponent } from './teacher-info/teacher-info.component';
import { TeacherFormComponent } from './teacher-info/teacher-form.component';

@NgModule({
  imports: [SharedModule, TeacherRoutingModule],
  declarations: [
    TeacherInfoComponent,
    TeacherFormComponent
  ],
})
export class TeacherModule {}
