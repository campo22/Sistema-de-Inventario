package com.inventario.sistemainventario.producto;

import com.inventario.sistemainventario.categoria.Categoria;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 128, nullable = false, unique = true)
    private String name;

    private float pricio;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<producto_detalles> detalles = new ArrayList<>();


    public Producto() {
    }

    public Producto(Integer id, String name, float pricio, Categoria categoria) {
        this.id = id;
        this.name = name;
        this.pricio = pricio;
        this.categoria = categoria;
    }

    public Producto(String name, float pricio, Categoria categoria) {
        this.name = name;
        this.pricio = pricio;
        this.categoria = categoria;
    }

    public Producto(String name) {
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

    public float getPricio() {
        return pricio;
    }

    public void setPricio(float pricio) {
        this.pricio = pricio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public void anadirDetalle(String name, String valor) {
        this.detalles.add(new producto_detalles(name, valor, this));
    }

    public List<producto_detalles> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<producto_detalles> detalles) {
        this.detalles = detalles;
    }
    public void setDetalle(Integer id,String name,String valor) {
        this.detalles.add(new producto_detalles(id,name,valor,this));
    }


}
