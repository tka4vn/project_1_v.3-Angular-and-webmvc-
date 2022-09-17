import {NgModule} from '@angular/core'
import {RouterModule, Routes} from '@angular/router'
import {DepartmentComponent} from "./department/department.component";
import {DepartmentEditComponent} from "./department/department-edit/department-edit.component";
import {AppointmentComponent} from "./appointment/appointment.component";
import {AppointmentEditComponent} from "./appointment/appointment-edit/appointment-edit.component";
import {LoginComponent} from "./login/login.component";
import {IndexComponent} from "./index/index.component";
import {AboutComponent} from "./about/about.component";
import {RegistrationComponent} from "./login/registration.component";
import {RolesComponent} from "./roles/roles.component";
import {ServicesComponent} from "./services/services.component";
import {RolesEditComponent} from "./roles/roles-edit/roles-edit.component";
import {ServicesEditComponent} from "./services/service-edit/services-edit.component";
import {EmployeeComponent} from "./employee/employee.component";
import {PositionComponent} from "./position/position.component";
import {EmployeeEditComponent} from "./employee/employee-edit/employee-edit.component";
import {PositionEditComponent} from "./position/position-edit/position-edit.component";
import {IngressComponent} from "./ingress/ingress.component";
import {UsersComponent} from "./users/users.component";
import {UsersEditComponent} from "./users/users-edit/users-edit.component";
import {RequestComponent} from "./request/request.component";

const routes: Routes = [
  {path: 'login', component: LoginComponent},
  {path: 'registration', component: RegistrationComponent},
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: ':id', component: IndexComponent,  children: [
      {path: 'about', component: AboutComponent},
      {path: 'department', component: DepartmentComponent},
      {path: 'department/:id', component: DepartmentEditComponent},
      {path: 'employee', component: EmployeeComponent},
      {path: 'employee/:id', component: EmployeeEditComponent},
      {path: 'position', component: PositionComponent},
      {path: 'position/:id', component: PositionEditComponent},
      {path: 'appointment', component: AppointmentComponent},
      {path: 'appointment/:id', component: AppointmentEditComponent},
      {path: 'ingress', component: IngressComponent},
      {path: 'roles', component: RolesComponent},
      {path: 'roles/:id', component: RolesEditComponent},
      {path: 'services', component: ServicesComponent},
      {path: 'services/:id', component: ServicesEditComponent},
      {path: 'users', component: UsersComponent},
      {path: 'users/:id', component: UsersEditComponent},
      {path: 'request', component: RequestComponent},
      {path: '**', redirectTo: '/login', pathMatch: 'full'},
    ]},
  {path: '**', redirectTo: '/login', pathMatch: 'full'},
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {


}
