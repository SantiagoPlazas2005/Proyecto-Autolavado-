import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  user = {
    email: '',
    password: ''
  };

  constructor(private router: Router) {}

  login() {
    if (!this.user.email || !this.user.password) {
      alert('Por favor, ingresa tu correo y contraseña.');
      return;
    }

    // Simulación de autenticación
    if (this.user.email === 'admin@test.com' && this.user.password === '1234') {
      alert('Inicio de sesión exitoso');
      this.router.navigate(['/dashboard']); // Redirigir al dashboard
    } else {
      alert('Correo o contraseña incorrectos');
    }
  }
}

