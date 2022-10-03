/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanvalero.APIAdProyecto.domain.equipoDTO;

import com.sanvalero.APIAdProyecto.domain.jugadorDTO.JugadorIdNombre;
import com.sanvalero.APIAdProyecto.domain.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Franmi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipoJugadoresDto {

    @Schema(description = "Nombre del equipo", example = "Jorge", required = true)
    String nombre;

    @Schema(description = "fecha de fundacion del equipo", example = "10/12/2001", required = true)
    Date fecha_fundacion;

    @Schema(description = "presupuesto del equipo", example = "50000", required = true)
    int presupuesto;

    @Schema(description = "estadio del equipo", example = "Los Cármenes", required = true)
    String estadio;

    @Schema(description = "entrenador del equipo", example = "Jose Antonio", required = true)
    String entrenador;

    @Schema(description = "jugadores del equipo", example = "Jose, Miguel, Luis", required = true)
    List<JugadorIdNombre> jugadores;

    
    static public EquipoJugadoresDto EquipoToEquipoResumen(Equipo e) {
        EquipoJugadoresDto res;
        JugadorIdNombre dto;
        String nombre;
        long id;
        List<Jugador> jugadoresNormal = e.getJugadores();
        ArrayList<JugadorIdNombre> nombres = new ArrayList();
        Iterator<Jugador> it = jugadoresNormal.iterator();
        while (it.hasNext()) {
            Jugador j = it.next();
            nombre = j.getNombre();
            id = j.getId_jugador();
            
            dto = new JugadorIdNombre(id,nombre);
            
            nombres.add(dto);
        }

        res = new EquipoJugadoresDto(
                e.getNombre(),
                e.getFecha_fundacion(),
                e.getPresupuesto(),
                e.getEstadio(),
                e.getEntrenador(),
                nombres
        
        );

        return res;
    }

}
