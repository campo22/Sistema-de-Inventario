package com.inventario.sistemainventario.producto;

import jakarta.persistence.*;

@Entity
@Table(name = "producto_detalles")
public class producto_detalles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 45, nullable = false)
    private String name;

    @Column(length = 45, nullable = false)
    private String valor;

    @ManyToOne
    @JoinColumn(name = "producto_id")
    private Producto producto;

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

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public producto_detalles(Integer id, String name, String valor, Producto producto) {
        this.id = id;
        this.name = name;
        this.valor = valor;
        this.producto = producto;
    }

    public producto_detalles(String name, String valor, Producto producto) {
        this.name = name;
        this.valor = valor;
        this.producto = producto;
    }

    public producto_detalles() {
    }


    @Override
    public String toString() {
        return "producto_detalles{" +

                ", name='" + name + '\'' +
                ", valor='" + valor + '\'' +
                '}';
    }
}
