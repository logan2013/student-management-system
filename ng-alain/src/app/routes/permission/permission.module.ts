import { NgModule } from '@angular/core';
import { SharedModule } from '@shared/shared.module';
import { PermissionRoutingModule } from './permission-routing.module';
import { RoleComponent } from './role/role.component';
import { PermissionComponent } from './permission/permission.component';

@NgModule({
  imports: [SharedModule, PermissionRoutingModule],
  declarations: [
    RoleComponent,
    PermissionComponent
  ],
})
export class PermissionModule {}
