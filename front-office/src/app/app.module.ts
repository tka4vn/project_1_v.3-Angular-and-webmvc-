import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {AppComponent} from './app.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {StatusComponent} from './status/status.component';
import {DepartmentComponent} from './department/department.component';
import {AppRoutingModule} from "./app-routing.modul";
import {DepartmentEditComponent} from './department/department-edit/department-edit.component';
import {AppointmentComponent} from './appointment/appointment.component';
import {AppointmentEditComponent} from "./appointment/appointment-edit/appointment-edit.component";
import {MatSliderModule} from '@angular/material/slider';
import {MatFormFieldModule} from "@angular/material/form-field";
import {MatSelectModule} from "@angular/material/select";
import {LoginComponent} from './login/login.component';
import {IndexComponent} from './index/index.component';
import {AboutComponent} from './about/about.component';
import {RegistrationComponent} from "./login/registration.component";
import { ServicesComponent } from './services/services.component';
import { RolesComponent } from './roles/roles.component';
import {RolesEditComponent} from "./roles/roles-edit/roles-edit.component";
import {ServicesEditComponent} from "./services/service-edit/services-edit.component";
import { EmployeeComponent } from './employee/employee.component';
import { PositionComponent } from './position/position.component';
import {EmployeeEditComponent} from "./employee/employee-edit/employee-edit.component";
import {PositionEditComponent} from "./position/position-edit/position-edit.component";
import { IngressComponent } from './ingress/ingress.component';
import { UsersComponent } from './users/users.component';
import {UsersEditComponent} from "./users/users-edit/users-edit.component";
import { RequestComponent } from './request/request.component';
import {MatDatepickerModule} from "@angular/material/datepicker";
import {MatTableModule} from "@angular/material/table";
import {MatIconModule} from "@angular/material/icon";
import {MatDividerModule} from "@angular/material/divider";


@NgModule({
  declarations: [
    AppComponent,
    StatusComponent,
    DepartmentComponent,
    DepartmentEditComponent,
    AppointmentComponent,
    AppointmentEditComponent,
    LoginComponent,
    RegistrationComponent,
    IndexComponent,
    AboutComponent,
    ServicesComponent,
    ServicesEditComponent,
    RolesComponent,
    RolesEditComponent,
    EmployeeComponent,
    EmployeeEditComponent,
    PositionComponent,
    PositionEditComponent,
    IngressComponent,
    UsersComponent,
    UsersEditComponent,
    RequestComponent,
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    MatSliderModule,
    MatFormFieldModule,
    MatSelectModule,
    MatDatepickerModule,
    MatTableModule,
    MatIconModule,
    MatDividerModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
