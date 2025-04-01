import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register-vehicle',
  templateUrl: './register-vehicle.component.html',
  styleUrls: ['./register-vehicle.component.scss']
})
export class RegisterVehicleComponent {
  vehicle = {
    type: '',
    plate: ''
  };
  
  basePrice = 50000; // Precio base para un carro
  estimatedPrice = this.basePrice;

  constructor(private router: Router) {}

  calculatePrice() {
    switch (this.vehicle.type) {
      case 'camioneta':
        this.estimatedPrice = this.basePrice + 20000;
        break;
      case 'autobus':
      case 'camion':
        this.estimatedPrice = this.basePrice + 50000; // Puedes cambiar esto según el tamaño
        break;
      default:
        this.estimatedPrice = this.basePrice;
    }
  }

  registerVehicle() {
    if (!this.vehicle.type || !this.vehicle.plate) {
      alert('Por favor, completa todos los campos.');
      return;
    }

    alert(`Vehículo registrado: ${this.vehicle.plate}, Tipo: ${this.vehicle.type}, Precio: $${this.estimatedPrice}`);
    this.router.navigate(['/reservation']); // Redirigir a la página de reservas
  }
}
