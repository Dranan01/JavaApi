/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanvalero.APIAdProyecto.controller;

import com.sanvalero.APIAdProyecto.domain.Equipo;
import com.sanvalero.APIAdProyecto.domain.equipoDTO.EquipoJugadoresDto;
import com.sanvalero.APIAdProyecto.domain.equipoDTO.EquipoResumen;
import com.sanvalero.APIAdProyecto.domain.equipoDTO.EquipoResumenNoId;
import com.sanvalero.APIAdProyecto.exception.ProductNotFoundException;
import com.sanvalero.APIAdProyecto.service.EquipoServiceImpl;
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
@Tag(name = "equipo", description = "Lista de equipos")
public class EquipoController {

    private final Logger logger = LoggerFactory.getLogger(EquipoController.class);

    @Autowired
    private EquipoServiceImpl equipoService = new EquipoServiceImpl();

    @Operation(summary = "Obtiene un listado de todos los equipos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado de equipos", content = @Content(array = @ArraySchema(schema = @Schema(implementation = EquipoResumen.class)))),})
    @GetMapping("equipo/")
    public ResponseEntity<Set<EquipoResumen>> getEquipos() {
        logger.info("start getEquipos");
        Set<Equipo> equipos = null;
        Set<EquipoResumen> equiposRes = new HashSet();

        equipos = equipoService.findAll();
        for (Equipo e : equipos) {
            //int id_equipo, String nombre, Date fecha_fundacion, int presupuesto, String estadio, String entrenador
            equiposRes.add(EquipoResumen.EquipoToEquipoResumen(e));
        }

        logger.info("fin getEquipos");
        return new ResponseEntity<>(equiposRes, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un listado de un equipo por su identificador")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Equipo por identificador", content = @Content(array = @ArraySchema(schema = @Schema(implementation = EquipoJugadoresDto.class)))),})
    @GetMapping("/equipo/{id}")
    public ResponseEntity<EquipoJugadoresDto> getEquipo(@PathVariable long id) {
        Equipo equipo = equipoService.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        EquipoJugadoresDto dto = EquipoJugadoresDto.EquipoToEquipoResumen(equipo);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Operation(summary = "Añadir un equipo nuevo")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Añadir un equipo", content = @Content(array = @ArraySchema(schema = @Schema(implementation = EquipoResumenNoId.class)))),})
    @PostMapping("/equipo")
    public ResponseEntity<Equipo> addEquipo(@RequestBody EquipoResumenNoId er) {
        Equipo addedEquipo = equipoService.addEquipo(EquipoResumenNoId.EquipoFromEquipoResumen(er));
        return new ResponseEntity<>(addedEquipo, HttpStatus.CREATED);

    }

    @PutMapping("/equipo/{id}")
    public ResponseEntity<Equipo> modifyEquipo(@PathVariable long id, @RequestBody EquipoResumenNoId er) {
        Optional<Equipo> e = equipoService.findById(id);
        Equipo x = e.get();
        System.out.println(x.toString());
        x = EquipoResumenNoId.ModifyEquipo(x, er);
        equipoService.modifyEquipo(id, x);
        return new ResponseEntity<>(x, HttpStatus.OK);
    }

    @DeleteMapping("/equipo/{id}")
    public ResponseEntity<Response> deleteEquipo(@PathVariable long id) {
        Equipo equipo = equipoService.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        if (equipo.getPartido_local().isEmpty() && equipo.getPartido_visitante().isEmpty() && equipo.getJugadores().isEmpty()) {
            equipoService.deleteEquipo(id);
            return new ResponseEntity<>(Response.noErrorResponse(), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(Response.errorResonse(405, "El equipo tiene datos asociados"), HttpStatus.OK);

        }

    }
}