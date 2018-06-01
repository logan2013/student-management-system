import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';

import { StudentRoutingModule } from './student-routing.module';
import { StudentComponent } from './student/student.component';
import { StudentHomeDetailComponent } from './student-home/student-home-detail.component';
import { StudentHomeListComponent } from './student-home/student-home-list.component';

@NgModule({
  imports: [SharedModule, StudentRoutingModule],
  declarations: [
    StudentComponent,
    StudentHomeDetailComponent,
    StudentHomeListComponent
  ],
})
export class StudentModule {}
