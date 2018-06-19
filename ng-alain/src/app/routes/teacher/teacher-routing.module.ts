import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { TeacherInfoComponent } from './teacher-info/teacher-info.component';
import { TeacherFormComponent } from './teacher-info/teacher-form.component';
import { TeacherSettingComponent } from './teacher-setting/teacher-setting.component';

const routes: Routes = [
  { path: 'teacher-info', component: TeacherInfoComponent },
  { path: 'teacher-form', component: TeacherFormComponent },
  { path: 'teacher-setting', component: TeacherSettingComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class TeacherRoutingModule {}
