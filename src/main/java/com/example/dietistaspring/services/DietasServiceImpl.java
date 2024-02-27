package com.example.dietistaspring.services;


import com.example.dietistaspring.entities.Dietas;
import com.example.dietistaspring.repositories.DietasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DietasServiceImpl implements DietasService{

    @Autowired
    private DietasRepository dietasRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Dietas> findAll() {
        return dietasRepository.findAll();
    }

    @Override
    @Transactional
    public Dietas save(Dietas dieta) {
        return dietasRepository.save(dieta);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Dietas> findById(Long id) {
       return dietasRepository.findById(id);
    }

    @Override
    public Optional<Dietas> update(Long id, Dietas dietas) {
        Optional <Dietas> dietasOptional = dietasRepository.findById(id);
        if(dietasOptional.isPresent()){
            Dietas dietasDb = dietasOptional.orElseThrow();
            dietasDb.setAlimentos(dietas.getAlimentos());
            dietasDb.setFechaCreacion(dietas.getFechaCreacion());
            dietasDb.setUsuarios(dietas.getUsuarios());
            return Optional.of(dietasRepository.save(dietasDb));
        }
        return dietasOptional;
    }
    @Override
    public Optional<Dietas> delete(Long id) {
        Optional <Dietas> dietaOptional = dietasRepository.findById(id);
        dietaOptional.ifPresent( dietas -> dietasRepository.delete(dietas));
        return dietaOptional;
    }
    @Override
    public List<Dietas> findDietasByTotalCaloriasBetween(Long minCalorias, Long maxCalorias) {
        return dietasRepository.findDietasByTotalCaloriasBetween(minCalorias, maxCalorias);
    }
}
