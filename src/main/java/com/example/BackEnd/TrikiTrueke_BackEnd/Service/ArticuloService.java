package com.example.BackEnd.TrikiTrueke_BackEnd.Service;

import com.example.BackEnd.TrikiTrueke_BackEnd.Model.ArticuloDTO;
import com.example.BackEnd.TrikiTrueke_BackEnd.Repository.ArticuloRepository;
import com.example.BackEnd.TrikiTrueke_BackEnd.Repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ArticuloService {

    @Autowired
    private ArticuloRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;


    public ArticuloDTO crear(ArticuloDTO articulo) {
        if (articulo.getUsuarioId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "usuarioId es obligatorio");
        }
        String usuarioId = articulo.getUsuarioId().toHexString();
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario asociado al articulo no existe");
        }

        articulo.setEstado("DISPONIBLE");
        articulo.setCreadoEn(LocalDateTime.now());
        return repository.save(articulo);
    }


    public List<ArticuloDTO> obtenerTodos() {
        return repository.findAll();
    }


    public ArticuloDTO obtenerPorId(String id) {
        return repository.findById(id).orElse(null);
    }


    public ArticuloDTO actualizar(String id, ArticuloDTO articulo) {
        articulo.setId(id);
        return repository.save(articulo);
    }


    public void eliminar(String id) {
        repository.deleteById(id);
    }
}
