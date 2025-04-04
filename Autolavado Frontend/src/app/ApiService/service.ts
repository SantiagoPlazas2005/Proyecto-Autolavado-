import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private baseUrl = 'http://localhost:8080/api/usuarios'; // Ajusta la URL según tu backend

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient) {}

  // Obtener lista de usuarios
  getUsuarios(): Observable<any> {
    return this.http.get(`${this.baseUrl}/usuarios`).pipe(
      catchError(this.handleError)
    );
  }

  // Crear usuario
  createUsuario(usuario: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/usuarios`, usuario, this.httpOptions).pipe(
      catchError(this.handleError)
    );
  }

  // Actualizar usuario
  updateUsuario(id: number, usuario: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/usuarios/${id}`, usuario, this.httpOptions).pipe(
      catchError(this.handleError)
    );
  }

  // Eliminar usuario
  deleteUsuario(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/usuarios/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  // Obtener lista de reservas
  getReservas(): Observable<any> {
    return this.http.get(`${this.baseUrl}/reservas`).pipe(
      catchError(this.handleError)
    );
  }

  // Crear reserva
  createReserva(reserva: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/reservas`, reserva, this.httpOptions).pipe(
      catchError(this.handleError)
    );
  }

  // Actualizar reserva
  updateReserva(id: number, reserva: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/reservas/${id}`, reserva, this.httpOptions).pipe(
      catchError(this.handleError)
    );
  }

  // Eliminar reserva
  deleteReserva(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/reservas/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  // Manejo de errores
  private handleError(error: any) {
    console.error('Error en la API:', error);
    return throwError(error);
  }
}
