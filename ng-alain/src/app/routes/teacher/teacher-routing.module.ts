import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { TeacherInfoComponent } from './teacher-info/teacher-info.component';
import { TeacherFormComponent } from './teacher-info/teacher-form.component';

const routes: Routes = [
  { path: 'teacher-info', component: TeacherInfoComponent },
  { path: 'teacher-form', component: TeacherFormComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class TeacherRoutingModule {}
