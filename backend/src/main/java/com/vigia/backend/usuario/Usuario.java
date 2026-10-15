package com.vigia.backend.usuario;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Representa un usuario guardado en la coleccion "usuarios" de MongoDB.
 */
@Document("usuarios")
public class Usuario {

    @Id
    private String id;              // Mongo lo genera solo

    private String nombre;

    @Indexed(unique = true)
    private String correo;          // no se pueden repetir correos

    private String contrasena;      // SIEMPRE cifrada con BCrypt, nunca en texto plano

    private Rol rol;

    private String empresaId;

    private String areaId;

    private boolean activo = true;

    public Usuario() {
    }

    public Usuario(String nombre, String correo, String contrasena, Rol rol, String empresaId, String areaId) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasena = contrasena;
        this.rol = rol;
        this.empresaId = empresaId;
        this.areaId = areaId;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }

    public String getEmpresaId() { return empresaId; }
    public void setEmpresaId(String empresaId) { this.empresaId = empresaId; }

    public String getAreaId() { return areaId; }
    public void setAreaId(String areaId) { this.areaId = areaId; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }
}
