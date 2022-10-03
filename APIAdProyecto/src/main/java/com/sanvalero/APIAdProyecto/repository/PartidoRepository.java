/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanvalero.APIAdProyecto.repository;

import com.sanvalero.APIAdProyecto.domain.Partido;
import java.util.Set;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author usuario
 */
@Repository
public interface PartidoRepository extends CrudRepository<Partido, Long>{
    Set<Partido> findAll();
    
}
