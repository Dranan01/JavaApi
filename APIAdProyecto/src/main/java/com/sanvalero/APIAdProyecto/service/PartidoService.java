/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanvalero.APIAdProyecto.service;

import com.sanvalero.APIAdProyecto.domain.Partido;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author franm
 */
public interface PartidoService {
    Set<Partido> findAll();
    Optional<Partido> findById(long id);
    Partido addPartido(Partido equipo);
    Partido modifyPartido(long id, Partido newProduct);
    void deletePartido(long id);
}
