/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanvalero.APIAdProyecto.domain;

import com.sanvalero.APIAdProyecto.domain.jugadorDTO.JugadorResumenNoId;
import io.swagger.v3.oas.annotations.media.Schema;
import java.sql.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
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
@Entity(name = "jugadores")
public class Jugador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id_jugador;

    @Schema(description = "nombre del jugador", example = "EL Bichoooooo", required = true)
    @Column
    @NotBlank
    private String nombre;

    @Schema(description = "dorsal del jugador", example = "1", required = true)
    @Column
    private int dorsal;

    @Schema(description = "posicion del jugador", example = "delantero centro", required = true)
    @Column
    private String posicion;

    @Schema(description = "fecha de nacimiento del jugador", example = "11/05/2001", required = true)
    @Column
    @NotBlank
    private Date fecha_nacimiento;

    @Schema(description = "nacionalidad del jugador", example = "español", required = true)
    @Column
    @NotBlank
    private String nacionalidad;

    @Schema(description = "equipo del jugador", example = "Alavés", required = true)
    @ManyToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Equipo equipo;

    public Jugador(String nombre, int dorsal, String posicion, Date fecha_nacimiento, String nacionalidad, Equipo equipo) {
        this.nombre = nombre;
        this.dorsal = dorsal;
        this.posicion = posicion;
        this.fecha_nacimiento = fecha_nacimiento;
        this.nacionalidad = nacionalidad;
        this.equipo = equipo;
    }
    
    
    public Jugador(JugadorResumenNoId er){
        this.nombre = er.getNombre();
        this.dorsal = er.getDorsal();
        this.posicion = er.getPosicion();
        this.fecha_nacimiento = er.getFecha_nacimiento();
        this.nacionalidad = er.getNacionalidad();
    }

    
    
    
 

    
   
}
