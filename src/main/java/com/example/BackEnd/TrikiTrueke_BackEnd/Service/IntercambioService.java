package com.Example.BackEnd.TrikiTrueke_BackEnd.Service;

import com.Example.BackEnd.TrikiTrueke_BackEnd.Model.IntercambioDTO;
import com.Example.BackEnd.TrikiTrueke_BackEnd.Model.ArticuloDTO;
import com.Example.BackEnd.TrikiTrueke_BackEnd.Repository.ArticuloRepository;
import com.Example.BackEnd.TrikiTrueke_BackEnd.Repository.IntercambioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class IntercambioService {

    @Autowired
    private IntercambioRepository repository;
    @Autowired
    private ArticuloRepository articuloRepository;

    //Create
    public IntercambioDTO create(IntercambioDTO intercambioDTO) {
        try {
            if (intercambioDTO.getArticuloOfrecido() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "articuloOfrecido es obligatorio");
            }
            if (intercambioDTO.getArticuloRecibido() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "articuloRecibido es obligatorio");
            }
            if (intercambioDTO.getUsuarioOfrece() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuarioOfrece es obligatorio");
            }
            if (intercambioDTO.getUsuarioRecibe() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuarioRecibe es obligatorio");
            }

            ArticuloDTO articuloOfrecido = articuloRepository.findById(intercambioDTO.getArticuloOfrecido().toHexString())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "El articulo ofrecido no existe"));

            if (articuloOfrecido.getUsuarioId() == null ||
                    !articuloOfrecido.getUsuarioId().equals(intercambioDTO.getUsuarioOfrece())) {
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST,
                        "El articulo ofrecido no pertenece al usuario que ofrece el intercambio"
                );
            }

            intercambioDTO.setCreadoEn(new Date());
            intercambioDTO.setEstado("PENDIENTE");
            return repository.save(intercambioDTO);
        } catch (ResponseStatusException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Datos invalidos para crear intercambio: " + ex.getMessage()
            );
        }
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
