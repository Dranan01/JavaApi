/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanvalero.APIAdProyecto.domain.jugadorDTO;

import com.sanvalero.APIAdProyecto.domain.Jugador;
import io.swagger.v3.oas.annotations.media.Schema;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
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
public class JugadorResumen {
    
    
    private long id_jugador;

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
   
    
    
    public static JugadorResumen jugadorToJugadorResumen(Jugador j){
        JugadorResumen jr;
        
        jr = new JugadorResumen(j.getId_jugador(),
                j.getNombre(),
                j.getDorsal(),
                j.getPosicion(),
                j.getFecha_nacimiento(),
                j.getNacionalidad());
        
        return jr;
    }
    

    
    
    
}
