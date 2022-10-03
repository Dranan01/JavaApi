/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanvalero.APIAdProyecto.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Entity(name = "partidos")
public class Partido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_partido;

    @Schema(description = "fecha y hora del partido", example = "10/01/20 10:00:00", required = true)
    @Column
    Timestamp fecha_hora;

    @Schema(description = "arbitro del partido", example = "Miguelito", required = true)
    @NotBlank
    @Column
    private String arbitro;

    @Schema(description = "numero de asistentes del partido", example = "100", required = true)
    @Column
    private int num_asistentes;

    @Schema(description = "estadio del partido", example = "Los Cármenes", required = true)
    @NotBlank
    @Column
    private String estadio;

    @Schema(description = "goles del equipo visitante del partido", example = "1", required = true)
    @Column
    private int goles_visitante;

    @Schema(description = "goles del equipo local del partido", example = "1", required = true)
    @Column
    private int goles_local;

    // equipo local
    @Schema(description = "equipo local del partido", example = "Granada", required = true)
    @ManyToOne
    @JoinColumn(name = "equipo_local")
    private Equipo local;

    // equipo visitantes
    @Schema(description = "equipo visitante del partido", example = "Malaga", required = true)
    @ManyToOne
    @JoinColumn(name = "equipo_visitante")
    private Equipo visitante;

    
    
    
    public Partido( Timestamp fecha_hora, String arbitro, int num_asistentes, String estadio, int goles_visitante, int goles_local) {
        this.fecha_hora = fecha_hora;
        this.arbitro = arbitro;
        this.num_asistentes = num_asistentes;
        this.estadio = estadio;
        this.goles_visitante = goles_visitante;
        this.goles_local = goles_local;
    }

    
    
    
    
    
    
    
    
}
