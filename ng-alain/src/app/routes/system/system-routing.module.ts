import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { GradeComponent } from './grade/grade.component';
import { MajorComponent } from './major/major.component';
import { ClassComponent } from './class/class.component';

const routes: Routes = [
  { path: 'grade', component: GradeComponent },
  { path: 'major', component: MajorComponent },
  { path: 'class', component: ClassComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class SystemRoutingModule {}
