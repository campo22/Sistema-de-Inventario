package com.inventario.sistemainventario.categoria;

import com.inventario.sistemainventario.marca.Marca;
import jakarta.persistence.*;

@Entity
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "marca_id")
    private Marca marca;

    public Categoria(Marca marca) {
        this.marca = marca;
    }

    public Categoria(Integer id, String name, Marca marca) {
        this.id = id;
        this.name = name;
        this.marca = marca;
    }

    public Categoria(String name, Marca marca) {
        this.name = name;
        this.marca = marca;
    }

    public Categoria() {
    }

    public Categoria(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Categoria(Integer id) {
        this.id = id;
    }

    public Categoria(String name) {
        this.name = name;
    }

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

    @Override
    public String toString() {
        return name;
    }
}
