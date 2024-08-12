package com.inventario.sistemainventario.producto;

import com.inventario.sistemainventario.categoria.Categoria;
import com.inventario.sistemainventario.categoria.CategoriaRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ProductoController {

    private static final Logger logger = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("producto/nuevo")
    public String mostrarFormularioDeNuevoProducto(Model modelo) {
        List<Categoria> listaCategoria = categoriaRepository.findAll();
        modelo.addAttribute("producto", new Producto());
        modelo.addAttribute("listaCategoria", listaCategoria);
        return "producto_formulario";
    }

    @PostMapping("producto/guardar")
    public String guardarProducto(Producto producto, HttpServletRequest request) {
        String[] detallesIDs = request.getParameterValues("detallesIDs");
        String[] detallesNombre = request.getParameterValues("detallesNombre");
        String[] detallesValor = request.getParameterValues("detallesValor");

        logger.info("Guardando producto con ID: {}", producto.getId());
        logger.info("Detalles IDs: {}", detallesIDs);
        logger.info("Detalles Nombre: {}", detallesNombre);
        logger.info("Detalles Valor: {}", detallesValor);

        for (int i = 0; i < detallesNombre.length; i++) {
            if (detallesIDs != null && detallesIDs.length > 0) {
                producto.setDetalle(Integer.valueOf(detallesIDs[i]), detallesNombre[i], detallesValor[i]);
            } else {
                producto.anadirDetalle(detallesNombre[i], detallesValor[i]);
            }
        }

        productoRepository.save(producto);
        return "redirect:/producto";
    }

    @GetMapping("/producto")
    public String listarProductos(Model modelo) {
        List<Producto> listarProductos = productoRepository.findAll();
        modelo.addAttribute("listarProductos", listarProductos);
        return "productos";
    }

    @GetMapping("/producto/editar/{id}")
    public String mostarFormularioDeModificarProducto(@PathVariable("id") Integer id, Model modelo) {
        Producto producto = productoRepository.findById(id).orElseThrow();
        modelo.addAttribute("producto", producto);
        List<Categoria> listaCategoria = categoriaRepository.findAll();
        modelo.addAttribute("listaCategoria", listaCategoria);
        return "producto_formulario";
    }

    @GetMapping("/producto/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") Integer id, Model model) {
        productoRepository.deleteById(id);
        return "redirect:/producto";
    }

    @GetMapping("/producto/detalles/{id}")
    @ResponseBody
    public Producto obtenerDetallesProducto(@PathVariable Integer id) {
        Producto producto = productoRepository.findById(id).orElseThrow();
        logger.info("Detalles del producto con ID: {}", id);
        logger.info("Detalles: {}", producto.getDetalles()); // Asegúrate de que el método getDetalles() esté disponible
        return producto;
    }
}
