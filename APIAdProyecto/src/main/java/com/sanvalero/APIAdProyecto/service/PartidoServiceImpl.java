/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanvalero.APIAdProyecto.service;

import com.sanvalero.APIAdProyecto.domain.Partido;
import com.sanvalero.APIAdProyecto.exception.ProductNotFoundException;
import com.sanvalero.APIAdProyecto.repository.PartidoRepository;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author usuario
 */
@Service
public class PartidoServiceImpl implements PartidoService {

    @Autowired
    private PartidoRepository partidoRepository;

    @Override
    public void deletePartido(long id) {
        partidoRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        partidoRepository.deleteById(id);

    }

    @Override
    public Set<Partido> findAll() {
        return partidoRepository.findAll();
    }

    @Override
    public Partido addPartido(Partido partido) {
        return partidoRepository.save(partido);

    }

    @Override
    public Partido modifyPartido(long id, Partido newProduct) {
        Partido partido = partidoRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        newProduct.setId_partido(partido.getId_partido());
        return partidoRepository.save(newProduct);

    }

    @Override
    public Optional<Partido> findById(long id) {
        return partidoRepository.findById(id);
    }

}
