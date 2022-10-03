/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanvalero.APIAdProyecto.controller;

import com.sanvalero.APIAdProyecto.domain.Equipo;
import com.sanvalero.APIAdProyecto.domain.Jugador;
import com.sanvalero.APIAdProyecto.domain.Partido;
import com.sanvalero.APIAdProyecto.domain.equipoDTO.EquipoResumen;
import com.sanvalero.APIAdProyecto.domain.equipoDTO.EquipoResumenNoId;
import com.sanvalero.APIAdProyecto.domain.jugadorDTO.JugadorResumenNoId;
import com.sanvalero.APIAdProyecto.domain.partidoDTO.PartidoEquipoDto;
import com.sanvalero.APIAdProyecto.domain.partidoDTO.PartidoEquipoId;
import com.sanvalero.APIAdProyecto.domain.partidoDTO.PartidoResumen;
import com.sanvalero.APIAdProyecto.exception.ProductNotFoundException;
import com.sanvalero.APIAdProyecto.service.EquipoServiceImpl;
import com.sanvalero.APIAdProyecto.service.JugadorServiceImpl;
import com.sanvalero.APIAdProyecto.service.PartidoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author usuario
 */
@RestController
@Tag(name = "partidos", description = "Lista de partidos")
public class PartidoController {

    private final Logger logger = LoggerFactory.getLogger(JugadorController.class);

    @Autowired
    private PartidoServiceImpl partidoService = new PartidoServiceImpl();
    @Autowired
    private EquipoServiceImpl equipoService = new EquipoServiceImpl();

    @Operation(summary = "Obtiene un listado de todos los partidos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado de partidos", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PartidoResumen.class)))),
    })
    @GetMapping("partido/")
    public ResponseEntity<Set<PartidoResumen>> getPartidos() {
        logger.info("start getPartidos");
        Set<Partido> partidos = new HashSet();
        Set<PartidoResumen> partidoResumen = new HashSet();
        partidos = partidoService.findAll();
        for (Partido p : partidos) {
            //int id_equipo, String nombre, Date fecha_fundacion, int presupuesto, String estadio, String entrenador
            partidoResumen.add(PartidoResumen.partidoToPartidoResumen(p));
        }

        logger.info("fin getPartidos");
        return new ResponseEntity<>(partidoResumen, HttpStatus.OK);
    }

    
    
    
    
    @Operation(summary = "Obtiene un listado de todos los partidos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Obtiene un partido en especifico", content = @Content(array = @ArraySchema(schema = @Schema(implementation = PartidoEquipoDto.class)))),
    })
    @GetMapping("/partido/{id}")
    public ResponseEntity<PartidoEquipoDto> getPartido(@PathVariable long id) {
       Partido partido = partidoService.findById(id)
             .orElseThrow(() -> new ProductNotFoundException(id));
       
            
       PartidoEquipoDto dto;
         dto = PartidoEquipoDto.equipoToEquipoDto(partido);

        logger.info("fin getPartidos");
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
    
        @Operation(summary = "Borra un jugador de la lista de jugadores")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Elimina un partido", content = @Content(array = @ArraySchema(schema = @Schema(implementation = JugadorResumenNoId.class)))),})
    @DeleteMapping("/partido/{id}")
    public ResponseEntity<Response> deleteJugador(@PathVariable long id) {
        partidoService.deletePartido(id);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.ACCEPTED);

    }
    
    
        @Operation(summary = "Añadir un partido nuevo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Añadir un partido", content = @Content(array = @ArraySchema(schema = @Schema(implementation = EquipoResumenNoId.class)))),})
    @PostMapping("/partido")
    public ResponseEntity<PartidoEquipoId> addPartido(@RequestBody PartidoEquipoId er) {
        
        Equipo local;
        
        local = equipoService.findById(er.getIdLocal()).orElseThrow(() ->
        new ProductNotFoundException("Equipo no encontrado"));
        
        Equipo visitante;
        
        visitante = equipoService.findById(er.getIdVisitante()).orElseThrow(() ->
        new ProductNotFoundException("Equipo no encontrado"));
        
        
        Partido addedPartido = partidoService.addPartido(PartidoEquipoId.partidoEquipoToPartido(er));
        addedPartido.setLocal(local);
        addedPartido.setVisitante(visitante);
        
        partidoService.addPartido(addedPartido);
        
        return new ResponseEntity<>(er, HttpStatus.CREATED);

    }
    
    
        @PutMapping("/partido/{id}")
    public ResponseEntity<PartidoResumen> modifyPartido(@PathVariable long id, @RequestBody PartidoResumen pr) {
        Partido e = partidoService.findById(id).orElseThrow(() -> new ProductNotFoundException(id));

        e = PartidoResumen.partidoFromPartidoResumen(pr, e);
        partidoService.modifyPartido(id,e);
        return new ResponseEntity<>(pr, HttpStatus.OK);
    }
    
    
    
    
}
