import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-reservation',
  templateUrl: './reservation.component.html',
  styleUrls: ['./reservation.component.scss']
})
export class ReservationComponent {
  services: string[] = ['Lavado Básico', 'Lavado Completo', 'Lavado Premium'];
  selectedService: string = '';
  date: string = '';

  constructor(private router: Router) {}

  makeReservation() {
    console.log('Reservando:', this.selectedService, this.date);
    alert(`Reservación realizada para ${this.selectedService} el ${this.date}`);
    this.router.navigate(['dashboard']);
  }

  navigateTo(route: string) {
    this.router.navigate([route]);
  }
}
