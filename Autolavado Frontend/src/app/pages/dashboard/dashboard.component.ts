import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../../ApiService/service';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  reservas: any[] = []; 

  constructor(private router: Router, private apiService: ApiService) {}

  ngOnInit(): void {
    this.cargarReservas();
  }

  navigateTo(page: string): void {
    this.router.navigate([page]);
  }

  cargarReservas(): void {
    this.apiService.getReservas().subscribe({
      next: (data: any[]) => {
        this.reservas = data;
      },
      error: (error) => {
        console.error('Error al obtener reservas:', error);
      }
    });
  }
}
