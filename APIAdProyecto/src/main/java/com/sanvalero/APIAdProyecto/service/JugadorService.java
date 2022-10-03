/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanvalero.APIAdProyecto.service;

import com.sanvalero.APIAdProyecto.domain.Equipo;
import com.sanvalero.APIAdProyecto.domain.Jugador;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author franm
 */
public interface JugadorService {

    Set<Jugador> findAll();

    Jugador addJugador(Jugador equipo);

    Optional<Jugador> findById(long id);

    Jugador modifyJugador(long id, Jugador newProduct);

    void deleteJugador(long id);
}
