/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanvalero.APIAdProyecto.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import java.sql.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author usuario
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "equipos")
public class Equipo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id_equipo;

    @Schema(description = "Nombre del equipo", example = "Jorge", required = true)
    @Column
    String nombre;

    @Schema(description = "fecha de fundacion del equipo", example = "10/12/2001", required = true)
    @Column
    @NotBlank
    Date fecha_fundacion;

    @Schema(description = "presupuesto del equipo", example = "50000", required = true)
    @Column
    int presupuesto;

    @Schema(description = "estadio del equipo", example = "Los Cármenes", required = true)
    @Column
    String estadio;

    @Schema(description = "entrenador del equipo", example = "Jose Antonio", required = true)
    @Column
    String entrenador;

    //ArrayList de partidos locales
    @Column
    @OneToMany(mappedBy = "local", cascade = CascadeType.ALL)
    private List<Partido> partido_local;
    
    
    
    //ArrayList de partidos visitantes
    @Column
    @OneToMany(mappedBy = "visitante", cascade = CascadeType.ALL)
    private List<Partido> partido_visitante;
    
    
    
    @Column  
    @OneToMany(mappedBy = "equipo")
    private List<Jugador> jugadores;
    //ArrayList jugadores

    public Equipo( String nombre, Date fecha_fundacion, int presupuesto, String estadio, String entrenador, List<Partido> partido_local, List<Partido> partido_visitante, List<Jugador> jugadores) {
        
        this.nombre = nombre;
        this.fecha_fundacion = fecha_fundacion;
        this.presupuesto = presupuesto;
        this.estadio = estadio;
        this.entrenador = entrenador;
        this.partido_local = partido_local;
        this.partido_visitante = partido_visitante;
        this.jugadores = jugadores;
    } 
    
    
    
}
