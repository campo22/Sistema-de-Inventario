package com.inventario.sistemainventario;

import com.inventario.sistemainventario.carrito.compras.ArticuloCarrito;
import com.inventario.sistemainventario.carrito.compras.ArticuloCarritoRepository;
import com.inventario.sistemainventario.producto.Producto;
import com.inventario.sistemainventario.usuario.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class ArticuloCarritoTests {

    @Autowired
    private ArticuloCarritoRepository articuloCarritoRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void tesAñadirArticuloCarrito() {
        Producto producto = entityManager.find(Producto.class, 4);
        Usuario usuario = entityManager.find(Usuario.class, 2);

        ArticuloCarrito articuloCarrito = new ArticuloCarrito(1, 2, producto, usuario);
        articuloCarritoRepository.save(articuloCarrito);
    }

    @Test
    public void tesadirMuchosArticulosCarrito() {
        // Asume que el Usuario y los Productos ya existen en la base de datos,
        // y se obtienen utilizando entityManager.find

        Usuario usuario = entityManager.find(Usuario.class, 14);
        Producto producto = entityManager.find(Producto.class, 11);
        Producto producto2 = entityManager.find(Producto.class, 12);

        // Si el Usuario y los Productos no existen en la base de datos, deben ser creados y guardados antes:
        if (usuario == null) {
            usuario = new Usuario(14);
            entityManager.persist(usuario);
        }

        if (producto == null) {
            producto = new Producto(11);
            entityManager.persist(producto);
        }

        if (producto2 == null) {
            producto2 = new Producto(12);
            entityManager.persist(producto2);
        }

        ArticuloCarrito articuloCarrito = new ArticuloCarrito(2, producto, usuario);
        ArticuloCarrito articuloCarrito2 = new ArticuloCarrito(3, producto2, usuario);

        articuloCarritoRepository.saveAll(List.of(articuloCarrito, articuloCarrito2));
    }
    @Test
    public void testListarArticulosCarrito() {
        List<ArticuloCarrito> articulosCarrito = articuloCarritoRepository.findAll();
        articulosCarrito.forEach(System.out::println);
    }

    @Test
    public void testActualizarArticulosCarrito() {
        ArticuloCarrito articuloCarrito = articuloCarritoRepository.findById(1).get();
        articuloCarrito.setCantidad(77);
        articuloCarrito.setProducto(new Producto(12));
        articuloCarritoRepository.save(articuloCarrito);
    }

}

