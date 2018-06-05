import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';

import { StudentRoutingModule } from './student-routing.module';
import { StudentComponent } from './student/student.component';
import { StudentHomeDetailComponent } from './student-home/student-home-detail.component';
import { StudentHomeListComponent } from './student-home/student-home-list.component';
import { StudentFormComponent } from './student/student-form.component';
import { StudentJobComponent } from './student-job/student-job.component';
import { StudentAwardComponent } from './student-award/student-award.component';
import { StudentPunishmentComponent } from './student-punishment/student-punishment.component';
import { StudentSubsidizeComponent } from './student-subsidize/student-subsidize.component';
import { StudentListComponent } from './student/student-list.component';

@NgModule({
  imports: [SharedModule, StudentRoutingModule],
  declarations: [
    StudentComponent,
    StudentHomeDetailComponent,
    StudentHomeListComponent,
    StudentFormComponent,
    StudentJobComponent,
    StudentAwardComponent,
    StudentPunishmentComponent,
    StudentSubsidizeComponent,
    StudentListComponent
  ],
})
export class StudentModule {}
