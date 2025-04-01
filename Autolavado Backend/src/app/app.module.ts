import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { RouterModule, Routes } from '@angular/router';

import { AppComponent } from './app.component';
import { HomeComponent } from './pages/home/home.component';
import { LoginComponent } from './pages/login/login.component';
import { RegisterComponent } from './pages/register/register.component';
import { ReservationComponent } from './pages/reservation/reservation.component';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { RegisterVehicleComponent } from './pages/register-vehicle/register-vehicle.component';

const routes: Routes = [
  { path: 'dashboard', component: DashboardComponent },
  { path: 'home', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'register-vehicle', component: RegisterVehicleComponent },
  { path: 'reservation', component: ReservationComponent },
  { path: '**', redirectTo: 'home', pathMatch: 'full' } // Redirección por defecto a home
];

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    LoginComponent,
    RegisterComponent,
    ReservationComponent,
    DashboardComponent,
    RegisterVehicleComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule.forRoot(routes) // Rutas configuradas
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
