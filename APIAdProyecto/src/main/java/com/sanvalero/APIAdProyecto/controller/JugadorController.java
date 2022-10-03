/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanvalero.APIAdProyecto.controller;

import com.sanvalero.APIAdProyecto.domain.Equipo;
import com.sanvalero.APIAdProyecto.domain.Jugador;
import com.sanvalero.APIAdProyecto.domain.jugadorDTO.JugadorEquipoDto;
import com.sanvalero.APIAdProyecto.domain.jugadorDTO.JugadorResumen;
import com.sanvalero.APIAdProyecto.domain.jugadorDTO.JugadorResumenNoId;
import com.sanvalero.APIAdProyecto.exception.ProductNotFoundException;
import com.sanvalero.APIAdProyecto.service.EquipoService;
import com.sanvalero.APIAdProyecto.service.EquipoServiceImpl;
import com.sanvalero.APIAdProyecto.service.JugadorService;
import com.sanvalero.APIAdProyecto.service.JugadorServiceImpl;
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
@Tag(name = "jugadores", description = "Lista de jugadores")
public class JugadorController {

    private final Logger logger = LoggerFactory.getLogger(JugadorController.class);

    @Autowired
    private JugadorService jugadorService;
    
    @Autowired
    private EquipoService equipoService;

    @Operation(summary = "Obtiene un listado de todos los jugadores")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado de jugadores", content = @Content(array = @ArraySchema(schema = @Schema(implementation = JugadorResumen.class)))),})
    @GetMapping("jugador/")
    public ResponseEntity<Set<JugadorResumen>> getJugadores() {
        logger.info("start getJugadores");
        Set<Jugador> jugadores = null;
        Set<JugadorResumen> jugadorRes = new HashSet();
        jugadores = jugadorService.findAll();
        for (Jugador j : jugadores) {
            jugadorRes.add(JugadorResumen.jugadorToJugadorResumen(j));
        }
        logger.info("fin getJugadores");
        return new ResponseEntity<>(jugadorRes, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un jugador por su identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Jugador por identificador", content = @Content(array = @ArraySchema(schema = @Schema(implementation = JugadorEquipoDto.class)))),})
    @GetMapping("/jugador/{id}") //TODO: AQUI HAY QUE MOSTRAR EL ID DEL EQUIPO Y EL NOMBRE DEL EQUIPO
    public ResponseEntity<JugadorEquipoDto> getJugador(@PathVariable long id) {
        Jugador jugador = jugadorService.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));

        JugadorEquipoDto dto = JugadorEquipoDto.jugadorToJugadorResuemn(jugador);
        
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Añade un jugador a la lista de jugadores")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Inserta un jugador", content = @Content(array = @ArraySchema(schema = @Schema(implementation = JugadorResumenNoId.class)))),})
    @PostMapping("/jugador")
    
    public ResponseEntity<JugadorResumenNoId> addJugador(@RequestBody JugadorResumenNoId er) {
        Equipo equipo = equipoService.findById(er.getId_equipo())
                .orElseThrow(() -> new ProductNotFoundException(er.getId_equipo()));
        
        Jugador j = new Jugador(er);
        
        j.setEquipo(equipo);
        
        j = jugadorService.addJugador(j);
      //  added Jugador addedJugador = jugadorService.addJugador(JugadorResumenNoId.JugadorFromJugadorResumen(er));
        return new ResponseEntity<>(er, HttpStatus.CREATED);

    }

    @Operation(summary = "Borra un jugador de la lista de jugadores")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Elimina un jugador", content = @Content(array = @ArraySchema(schema = @Schema(implementation = JugadorResumenNoId.class)))),})
    @DeleteMapping("/jugador/{id}")
    public ResponseEntity<Response> deleteJugador(@PathVariable long id) {
        jugadorService.deleteJugador(id);
        return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);

    }

    @Operation(summary = "Modificar un jugador a la lista de jugadores")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Modifica un jugador", content = @Content(array = @ArraySchema(schema = @Schema(implementation = JugadorResumenNoId.class)))),})
    @PutMapping("/jugador/{id}")
    public ResponseEntity<Jugador> modifyEquipo(@PathVariable long id, @RequestBody JugadorResumenNoId er) {
        Optional<Jugador> e = jugadorService.findById(id);
        Jugador x = e.get();
        System.out.println(x.toString());
        x = JugadorResumenNoId.ModifyEquipo(x, er);
        jugadorService.modifyJugador(id, x);
        return new ResponseEntity<>(x, HttpStatus.OK);
    }

}
