package com.vigia.backend.usuario;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Acceso a la coleccion "usuarios".
 * Spring crea la implementacion automaticamente: save, findAll, count, deleteById, etc.
 */
public interface UsuarioRepository extends MongoRepository<Usuario, String> {

    // Spring arma la consulta a partir del nombre del metodo: busca por el campo "correo"
    Optional<Usuario> findByCorreo(String correo);
}
