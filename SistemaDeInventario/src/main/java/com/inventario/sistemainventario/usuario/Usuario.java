package com.inventario.sistemainventario.usuario;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false, unique = true)
    private String name;

    @Column(length = 45, nullable = false, unique = true)
    private String email;

    @Column(length = 10, nullable = false)
    private String password;

    @ManyToMany //(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_rol", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> rol = new HashSet<>();

    public Usuario( Integer id) {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Rol> getRol() {
        return rol;
    }

    public void setRol(Set<Rol> rol) {
        this.rol = rol;
    }

    public void addRol(Rol rol) {
        this.rol.add(rol);
    }
    public void eliminarRol(Rol rol) {
        this.rol.remove(rol);
    }
    public Usuario(Integer id, String name, String email, String password, Set<Rol> rol) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    public Usuario(String name, String email, String password, Set<Rol> rol) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.rol = rol;
    }

    public Usuario(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public Usuario() {
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "name='" + name + '\'' +
                '}';
    }
}
