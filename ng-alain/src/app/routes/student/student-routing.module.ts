import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { StudentComponent } from './student/student.component';
import { StudentHomeDetailComponent } from './student-home/student-home-detail.component';
import { StudentHomeListComponent } from './student-home/student-home-list.component';

const routes: Routes = [
  { path: 'student', component: StudentComponent },
  { path: 'student-home-detail', component: StudentHomeDetailComponent },
  { path: 'student-home-list', component: StudentHomeListComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class StudentRoutingModule {}
