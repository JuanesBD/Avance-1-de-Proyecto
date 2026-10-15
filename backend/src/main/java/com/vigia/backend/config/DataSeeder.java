package com.vigia.backend.config;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.vigia.backend.usuario.Rol;
import com.vigia.backend.usuario.Usuario;
import com.vigia.backend.usuario.UsuarioRepository;

/**
 * Se ejecuta una vez cada vez que arranca la aplicacion.
 * Si la coleccion "usuarios" esta vacia, crea 4 usuarios de prueba (uno por rol).
 */
@Component
public class DataSeeder implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);
    private static final String EMPRESA_DEMO = "EMP-DEMO";

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final String claveDePrueba;

    public DataSeeder(UsuarioRepository usuarioRepository,
                      PasswordEncoder passwordEncoder,
                      @Value("${SEED_PASSWORD:}") String claveDePrueba) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.claveDePrueba = claveDePrueba;
    }

    @Override
    public void run(String... args) {
        if (usuarioRepository.count() > 0) {
            log.info("Ya existen usuarios en la base; no se crean usuarios de prueba.");
            return;
        }
        if (claveDePrueba.isBlank()) {
            log.warn("SEED_PASSWORD no esta definido en .env; no se crean usuarios de prueba.");
            return;
        }

        usuarioRepository.saveAll(List.of(
            crear("Admin Prueba", "admin@sst.test", Rol.ADMIN),
            crear("Trabajador Prueba", "trabajador@sst.test", Rol.TRABAJADOR),
            crear("Profesional SST Prueba", "sst@sst.test", Rol.PROFESIONAL_SST),
            crear("Coordinador Prueba", "coordinador@sst.test", Rol.COORDINADOR)
        ));
        log.info("Se crearon 4 usuarios de prueba.");
    }

    private Usuario crear(String nombre, String correo, Rol rol) {
        // Cada contrasena se cifra por separado (BCrypt le pone un "salt" distinto a cada una)
        return new Usuario(nombre, correo, passwordEncoder.encode(claveDePrueba), rol, EMPRESA_DEMO, null);
    }
}
