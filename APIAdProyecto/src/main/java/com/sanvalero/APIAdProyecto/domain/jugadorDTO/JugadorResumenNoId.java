/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanvalero.APIAdProyecto.domain.jugadorDTO;

import com.sanvalero.APIAdProyecto.controller.EquipoController;
import com.sanvalero.APIAdProyecto.domain.Jugador;
import com.sanvalero.APIAdProyecto.exception.ProductNotFoundException;
import com.sanvalero.APIAdProyecto.repository.EquipoRepository;
import com.sanvalero.APIAdProyecto.service.EquipoService;
import com.sanvalero.APIAdProyecto.service.EquipoServiceImpl;
import io.swagger.v3.oas.annotations.media.Schema;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Franmi
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JugadorResumenNoId {


   
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
    private long id_equipo;

    
   
   //static public Jugador JugadorFromJugadorResumen(JugadorResumenNoId er /* Equipo*/ ) { //id,nombre,dorsal,fecha,nacionalidad,equipo
/*
              
        Jugador j;

        int,string,string
        j = new Jugador(
                er.getNombre(),
                er.getDorsal(),
                er.getPosicion(),
                er.getFecha_nacimiento(),
                er.getNacionalidad(),
                equipo);

        return j;
     
        
        ^*/
        
  //  }
    
    
    
        static public Jugador ModifyEquipo(Jugador e,JugadorResumenNoId er){
        e.setDorsal(er.getDorsal());
        e.setFecha_nacimiento(er.getFecha_nacimiento());
        e.setNacionalidad(er.getNacionalidad());
        e.setNombre(er.getNombre());
        e.setPosicion(er.getPosicion());
        
        //int,string,string        
        
        
        return e;
    }
}
