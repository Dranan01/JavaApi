/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanvalero.APIAdProyecto.domain.partidoDTO;

import com.sanvalero.APIAdProyecto.domain.Partido;
import io.swagger.v3.oas.annotations.media.Schema;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author franm
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PartidoResumen {

    @Schema(description = "fecha y hora del partido", example = "10/01/20 10:00:00", required = true)

    Timestamp fecha_hora;

    @Schema(description = "arbitro del partido", example = "Miguelito", required = true)

    private String arbitro;


    private int num_asistentes;

    @Schema(description = "estadio del partido", example = "Los Cármenes", required = true)


    private String estadio;

    @Schema(description = "goles del equipo visitante del partido", example = "1", required = true)
   
    private int goles_visitante;
    
    
    private String nombreEquipoVisitante;

    @Schema(description = "goles del equipo local del partido", example = "1", required = true)

    private int goles_local;

    private String nombreEquipoLocal;
     
 
    
    
    
    public static PartidoResumen partidoToPartidoResumen(Partido p){
        PartidoResumen pr;
        
        pr = new PartidoResumen(
        p.getFecha_hora(),
        p.getArbitro(),
        p.getNum_asistentes(),
        p.getEstadio(),
        p.getGoles_visitante(),
        p.getVisitante().getNombre(),
        p.getGoles_local(),
        p.getLocal().getNombre());
        

        return pr;
    }
    
     //Timestamp fecha_hora, String arbitro, int num_asistentes, String estadio, int goles_visitante, int goles_local
        public static Partido partidoFromPartidoResumen(PartidoResumen pr, Partido p){
        
            p.setArbitro(pr.getArbitro());
            p.setEstadio(pr.getArbitro());
            p.setFecha_hora(pr.getFecha_hora());
            p.setGoles_local(pr.getGoles_local());
            p.setGoles_visitante(pr.getGoles_visitante());
            p.setNum_asistentes(p.getNum_asistentes());
            
            

        return p;
    }
    
    
    
    
}
