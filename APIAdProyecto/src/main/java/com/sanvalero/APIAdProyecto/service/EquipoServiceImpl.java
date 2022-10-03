/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanvalero.APIAdProyecto.service;

import com.sanvalero.APIAdProyecto.domain.Equipo;
import com.sanvalero.APIAdProyecto.exception.ProductNotFoundException;
import com.sanvalero.APIAdProyecto.repository.EquipoRepository;
import java.util.Optional;
import java.util.Set;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author usuario
 */
@Service
public class EquipoServiceImpl implements EquipoService {
    
    /**
     * HACER DTO'S de los modelos para mostrar
     */

    @Autowired
    private EquipoRepository equipoRepository ;

    @Override
    public Equipo addEquipo(Equipo equipo) {
        return equipoRepository.save(equipo);
    }

    @Override
    public Equipo modifyEquipo(long id, Equipo newProduct) {

        Equipo equipo = equipoRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        newProduct.setId_equipo(equipo.getId_equipo());
        return equipoRepository.save(newProduct);
        
    }

    @Override
    public void deleteEquipo(long id) {
         equipoRepository.findById(id)
                 .orElseThrow(() -> new ProductNotFoundException(id));
        equipoRepository.deleteById(id);
    }

    @Override
    public Set<Equipo> findAll() {
        return equipoRepository.findAll();

    }

    @Override
    public Optional<Equipo> findById(long id) {
        return equipoRepository.findById(id); 

    }
    
    
    
}
