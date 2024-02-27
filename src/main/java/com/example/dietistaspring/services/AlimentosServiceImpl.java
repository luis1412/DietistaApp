package com.example.dietistaspring.services;


import com.example.dietistaspring.entities.Alimentos;
import com.example.dietistaspring.repositories.AlimentosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AlimentosServiceImpl implements AlimentosService{

    @Autowired
    private AlimentosRepository alimentosRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Alimentos> findAll() {
        return alimentosRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Alimentos> findById(Long id) {
        return alimentosRepository.findById(id);
    }

    @Override
    @Transactional
    public Alimentos save(Alimentos alimentos) {
        return alimentosRepository.save(alimentos);
    }

    @Override
    @Transactional
    public Optional<Alimentos> update(Long id, Alimentos alimentos) {
        Optional <Alimentos> optionalAlimentos = alimentosRepository.findById(id);
        if(optionalAlimentos.isPresent()){
            Alimentos alimentos1 = optionalAlimentos.orElseThrow();
            alimentos1.setNombre(alimentos.getNombre());
            alimentos1.setCalorias(alimentos.getCalorias());
            alimentos1.setGrasas(alimentos.getGrasas());
            alimentos1.setSal(alimentos.getSal());
            alimentos1.setHidratos(alimentos1.getHidratos());
            alimentos1.setProteinas(alimentos.getProteinas());
            return Optional.of(alimentosRepository.save(alimentos1));
        }
        return optionalAlimentos;
    }

    @Override
    @Transactional
    public Optional<Alimentos> delete(Long id) {
        Optional <Alimentos> productOptional = alimentosRepository.findById(id);
        productOptional.ifPresent( alimentos -> alimentosRepository.delete(alimentos));
        return productOptional;
    }

    @Override
    public List<Alimentos> getAlimentosByNombreContains(String name) {
        return alimentosRepository.getAlimentosByNombreContains(name);
    }

    @Override
    public List<Alimentos> getAlimentosByCaloriasBetween(Long numeroCaloriasMin, Long numeroCaloriasMax) {
        return alimentosRepository.getAlimentosByCaloriasBetween(numeroCaloriasMin, numeroCaloriasMax);
    }

    @Override
    public List<Alimentos> getAlimentosByGrasasBetween(Double numeroCaloriasMin, Double numeroCaloriasMax) {
        return alimentosRepository.getAlimentosByGrasasBetween(numeroCaloriasMin,numeroCaloriasMax);
    }

    @Override
    public List<Alimentos> getAlimentosBySalBetween(Double salMin, Double salMax) {
        return alimentosRepository.getAlimentosBySalBetween(salMin,salMax);
    }

    @Override
    public List<Alimentos> getAlimentosByHidratosBetween(Double hidratosMin, Double hidratosMax) {
        return alimentosRepository.getAlimentosByHidratosBetween(hidratosMin,hidratosMax);
    }


}
