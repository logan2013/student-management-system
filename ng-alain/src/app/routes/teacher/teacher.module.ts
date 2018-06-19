import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { TeacherRoutingModule } from './teacher-routing.module';
import { TeacherInfoComponent } from './teacher-info/teacher-info.component';
import { TeacherFormComponent } from './teacher-info/teacher-form.component';
import { TeacherSettingComponent } from './teacher-setting/teacher-setting.component';

@NgModule({
  imports: [SharedModule, TeacherRoutingModule],
  declarations: [
    TeacherInfoComponent,
    TeacherFormComponent,
    TeacherSettingComponent
  ],
})
export class TeacherModule {}
