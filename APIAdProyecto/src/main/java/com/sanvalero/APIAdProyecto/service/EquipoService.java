/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanvalero.APIAdProyecto.service;

import com.sanvalero.APIAdProyecto.domain.Equipo;
import java.util.Optional;
import java.util.Set;

/**
 *
 * @author Franmi
 */




public interface EquipoService {
    Set<Equipo> findAll();
    Optional<Equipo> findById(long id);
    Equipo addEquipo(Equipo equipo);
    Equipo modifyEquipo(long id, Equipo newProduct);
    void deleteEquipo(long id);
}
