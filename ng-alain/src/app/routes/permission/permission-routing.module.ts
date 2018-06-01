import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { RoleComponent } from './role/role.component';
import { PermissionComponent } from './permission/permission.component';

const routes: Routes = [
  { path: 'role', component: RoleComponent },
  { path: 'permission', component: PermissionComponent },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PermissionRoutingModule {}
