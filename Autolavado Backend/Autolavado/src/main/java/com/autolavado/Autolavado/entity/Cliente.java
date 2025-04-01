package com.autolavado.Autolavado.entity;

import jakarta.persistence.*;
import lombok.*;
@Entity
@Table(name = "clientes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 50)
  private String nombre;

  @Column(nullable = false, unique = true, length = 100)
  private String email;

  @Column(nullable = false, length = 15)
  private String telefono;

  @Column(nullable = false)
  private String contraseña;
}
