/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanvalero.APIAdProyecto.domain.equipoDTO;

import com.sanvalero.APIAdProyecto.domain.*;
import io.swagger.v3.oas.annotations.media.Schema;
import java.sql.Date;
import java.util.ArrayList;
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
public class EquipoResumen
{
    
    long id_equipo;
    
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

        
    static public EquipoResumen EquipoToEquipoResumen( Equipo e){
        EquipoResumen res;
        
        res = new EquipoResumen(e.getId_equipo(),
                e.getNombre(),
                e.getFecha_fundacion(),
                e.getPresupuesto(),
                e.getEstadio(),
                e.getEntrenador());
        
        
        return res;
    }    
    
    static public Equipo EquipoFromEquipoResumen(EquipoResumen er){
        Equipo e;
        List<Partido> local = new ArrayList();
        List<Partido> visitante = new ArrayList();
        List<Jugador> jugadores = new ArrayList();
        
        
        //int,string,string
        e = new Equipo(er.getId_equipo(),
                er.getNombre(),
                er.getFecha_fundacion(),
                er.getPresupuesto(),
                er.getEntrenador(),
                er.getEstadio(), 
                local,
                visitante,
                jugadores);
        
        
        
        return e;
    }
    
    static public Equipo ModifyEquipo(Equipo e,EquipoResumen er){
        e.setEntrenador(er.getEntrenador());
        e.setEstadio(er.getEstadio());
        e.setFecha_fundacion(er.getFecha_fundacion());
        e.setNombre(er.getNombre());
        e.setPresupuesto(er.getPresupuesto());
        
        //int,string,string        
        
        
        return e;
    }
    
    
    
    
  /*  public EquipoResumen(int id_equipo, String nombre, Date fecha_fundacion, int presupuesto, String estadio, String entrenador) {
        this.id_equipo = id_equipo;
        this.nombre = nombre;
        this.fecha_fundacion = fecha_fundacion;
        this.presupuesto = presupuesto;
        this.estadio = estadio;
        this.entrenador = entrenador;
    } */
    
    
    
    
    
    
}
