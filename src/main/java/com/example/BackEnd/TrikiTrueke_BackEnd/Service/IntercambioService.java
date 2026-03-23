package com.example.BackEnd.TrikiTrueke_BackEnd.Service;

import com.example.BackEnd.TrikiTrueke_BackEnd.Model.IntercambioDTO;
import com.example.BackEnd.TrikiTrueke_BackEnd.Repository.IntercambioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class IntercambioService {

    @Autowired
    private IntercambioRepository repository;

    //Create
    public IntercambioDTO create(IntercambioDTO intercambioDTO) {
        intercambioDTO.setCreadoEn(new Date());
        intercambioDTO.setEstado("pendiente");
        return repository.save(intercambioDTO);
    }
    //Get all
    public List<IntercambioDTO> getAll() {
        return repository.findAll();
    }
    //Get by Id
    public Optional<IntercambioDTO> getById(String id) {
        return repository.findById(id);
    }
    //Update
    public IntercambioDTO update(String id, IntercambioDTO intercambioDTO) {
        intercambioDTO.setId(id);
        return repository.save(intercambioDTO);
    }
    //Delete
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}