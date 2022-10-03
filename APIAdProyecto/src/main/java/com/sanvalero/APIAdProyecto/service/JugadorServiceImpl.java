/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sanvalero.APIAdProyecto.service;

import com.sanvalero.APIAdProyecto.domain.Equipo;
import com.sanvalero.APIAdProyecto.domain.Jugador;
import com.sanvalero.APIAdProyecto.exception.ProductNotFoundException;
import com.sanvalero.APIAdProyecto.repository.JugadorRepository;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author usuario
 */
@Service
public class JugadorServiceImpl implements JugadorService {
    
     @Autowired
    private JugadorRepository jugadorRepository ;

    @Override
    public Jugador addJugador(Jugador jugador) {
        return jugadorRepository.save(jugador);

    }

    @Override
    public Jugador modifyJugador(long id, Jugador newProduct) {
        Jugador jugador = jugadorRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        newProduct.setId_jugador(jugador.getId_jugador());
        return jugadorRepository.save(newProduct);    }

    @Override
    public void deleteJugador(long id) {
        jugadorRepository.findById(id)
                 .orElseThrow(() -> new ProductNotFoundException(id));
        jugadorRepository.deleteById(id);
    }    

    @Override
    public Set<Jugador> findAll() {
    return jugadorRepository.findAll();
    }

    @Override
    public Optional<Jugador> findById(long id) {
        return jugadorRepository.findById(id); 

    }

  
    
}
