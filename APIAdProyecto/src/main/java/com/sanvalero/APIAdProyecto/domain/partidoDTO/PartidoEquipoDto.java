/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanvalero.APIAdProyecto.domain.partidoDTO;

import com.sanvalero.APIAdProyecto.domain.Equipo;
import com.sanvalero.APIAdProyecto.domain.Partido;
import com.sanvalero.APIAdProyecto.domain.equipoDTO.EquipoResumenNoId;
import io.swagger.v3.oas.annotations.media.Schema;
import java.sql.Timestamp;
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
public class PartidoEquipoDto {

    @Schema(description = "fecha y hora del partido", example = "10/01/20 10:00:00", required = true)
    Timestamp fecha_hora;

    @Schema(description = "arbitro del partido", example = "Miguelito", required = true)
    private String arbitro;

    @Schema(description = "numero de asistentes del partido", example = "100", required = true)
    private int num_asistentes;

    @Schema(description = "estadio del partido", example = "Los Cármenes", required = true)
    private String estadio;

    // equipo local
    @Schema(description = "equipo local del partido", example = "Granada", required = true)
    private EquipoResumenNoId local;

    @Schema(description = "goles del equipo local del partido", example = "1", required = true)
    private int goles_local;

    // equipo visitantes
    @Schema(description = "equipo visitante del partido", example = "Malaga", required = true)
    private EquipoResumenNoId visitante;

    @Schema(description = "goles del equipo visitante del partido", example = "1", required = true)
    private int goles_visitante;
    
    
    
    static public PartidoEquipoDto equipoToEquipoDto(Partido e){
        PartidoEquipoDto dto;
        
        dto = new PartidoEquipoDto(e.getFecha_hora(),
                e.getArbitro(),
                e.getNum_asistentes(),
                e.getEstadio(),
                EquipoResumenNoId.equipoToEquipoResumenNoId(e.getLocal()),
                e.getGoles_local(),
                EquipoResumenNoId.equipoToEquipoResumenNoId(e.getVisitante()),
                e.getGoles_visitante());
        
        return dto;
    }
    
    
    
    
    
    
    
    
    
}
