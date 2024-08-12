package com.inventario.sistemainventario.marca;

import com.inventario.sistemainventario.categoria.Categoria;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Marca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false, unique = true)
    private String name;

    @OneToMany
    @JoinColumn(name = "marca_id")
    private List<Categoria> categorias = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public Marca(Integer id, String name, List<Categoria> categorias) {
        this.id = id;
        this.name = name;
        this.categorias = categorias;
    }

    public Marca(String name, List<Categoria> categorias) {
        this.name = name;
        this.categorias = categorias;
    }

    public Marca() {
    }

    @Override
    public String toString() {
        return "Marca{" +
                "name='" + name + '\'' +
                ", categorias=" + categorias +
                '}';
    }
}
