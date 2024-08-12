package com.inventario.sistemainventario.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/categoria")
    public String listarCategoria(Model modelo) {
        List<Categoria> listaCategoria = categoriaRepository.findAll();
        modelo.addAttribute("listaCategoria",listaCategoria);
        return "categorias";

    }
    @GetMapping("/categorias/nuevo")
    public  String mostrarFormularioDeNuevaCategoria(Model modelo){
        modelo.addAttribute("categoria",new Categoria());
        return "categorias_formulario";
    }
    @PostMapping("/categorias/guardar")
    public String guardarCategoria(Categoria categoria){
        categoriaRepository.save(categoria);
        return "redirect:/categoria";
    }
}
