/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanvalero.APIAdProyecto.domain.jugadorDTO;

import com.sanvalero.APIAdProyecto.domain.Equipo;
import com.sanvalero.APIAdProyecto.domain.Jugador;
import com.sanvalero.APIAdProyecto.domain.equipoDTO.EquipoIdNombre;
import com.sanvalero.APIAdProyecto.domain.equipoDTO.EquipoJugadoresDto;
import io.swagger.v3.oas.annotations.media.Schema;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author franm
 */
@Data
@AllArgsConstructor
public class JugadorEquipoDto {
 
    
    
    @Schema(description = "nombre del jugador", example = "EL Bichoooooo", required = true)
    private String nombre;

    @Schema(description = "dorsal del jugador", example = "1", required = true)
    private int dorsal;

    @Schema(description = "posicion del jugador", example = "delantero centro", required = true)
    private String posicion;

    @Schema(description = "fecha de nacimiento del jugador", example = "11/05/2001", required = true)
    private Date fecha_nacimiento;

    @Schema(description = "nacionalidad del jugador", example = "español", required = true)
   private String nacionalidad;

   @Schema(description = "equipo del jugador", example = "español", required = true)
    private EquipoIdNombre equipo;
   

   
   
   
   static public JugadorEquipoDto jugadorToJugadorResuemn(Jugador j){
       JugadorEquipoDto jed;
       EquipoIdNombre dto;
       String nombre;
       long id;
       
      Equipo equipoNormal = j.getEquipo();
      nombre = equipoNormal.getNombre();
      id = equipoNormal.getId_equipo();
       dto = new EquipoIdNombre(id,nombre);
       
       jed = new JugadorEquipoDto(j.getNombre(),
       j.getDorsal(),
       j.getPosicion(),
       j.getFecha_nacimiento(),
       j.getNacionalidad(),
       dto);
       
       return jed;
       
   }
   
  
    
}
