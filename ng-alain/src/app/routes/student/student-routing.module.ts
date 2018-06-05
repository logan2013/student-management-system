import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StudentComponent } from './student/student.component';
import { StudentHomeDetailComponent } from './student-home/student-home-detail.component';
import { StudentHomeListComponent } from './student-home/student-home-list.component';
import { StudentFormComponent } from './student/student-form.component';
import { StudentJobComponent } from './student-job/student-job.component';
import { StudentAwardComponent } from './student-award/student-award.component';
import { StudentPunishmentComponent } from './student-punishment/student-punishment.component';
import { StudentSubsidizeComponent } from './student-subsidize/student-subsidize.component';
import { StudentListComponent } from './student/student-list.component';

const routes: Routes = [
  { path: 'student', component: StudentComponent },
  { path: 'student-list', component: StudentListComponent },
  { path: 'student-home-detail', component: StudentHomeDetailComponent },
  { path: 'student-home-list', component: StudentHomeListComponent },
  { path: 'student-form', component: StudentFormComponent },
  { path: 'student-job', component: StudentJobComponent },
  { path: 'student-award', component: StudentAwardComponent },
  { path: 'student-punishment', component: StudentPunishmentComponent },
  { path: 'student-subsidize', component: StudentSubsidizeComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class StudentRoutingModule {}
