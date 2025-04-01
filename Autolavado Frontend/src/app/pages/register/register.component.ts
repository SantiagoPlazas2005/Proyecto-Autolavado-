import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  user = {
    name: '',
    email: '',
    password: ''
  };

  constructor(private router: Router) {}

  register() {
    if (!this.user.name || !this.user.email || !this.user.password) {
      alert('Por favor, completa todos los campos.');
      return;
    }

    alert(`Usuario registrado: ${this.user.name}`);
    this.router.navigate(['/login']); // Redirigir al login
  }
}

