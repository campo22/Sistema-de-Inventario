package com.inventario.sistemainventario.marca;

import com.inventario.sistemainventario.categoria.Categoria;
import com.inventario.sistemainventario.categoria.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MarcaControllers {

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping("/marca/nuevo")
    public String mostrarFormularioMarca(Model model) {
        List<Categoria> listaCategoria = categoriaRepository.findAll();

        model.addAttribute("listaCategoria", listaCategoria);
        model.addAttribute("marca", new Marca());

        return "marca_formulario";

    }

    @PostMapping("/marca/guardar")
    public String guardarMarca(Marca marca) {
        marcaRepository.save(marca);

        return "redirect:/";

    }

    @GetMapping("/marca")
    public String listarMarca(Model model){
        List<Marca> listarMarcas = marcaRepository.findAll();
        model.addAttribute("listarMarcas", listarMarcas);

        return "marca";
    }

    @GetMapping("/marca/eliminar/{id}")
    public String eliminarMarca(@PathVariable("id") Integer id,Model modelo) {

        marcaRepository.deleteById(id);
        return "redirect:/marca";
    }
    @GetMapping("/marca/editar/{id}")
    public String editarMarca(@PathVariable("id") Integer id, Model modelo) {
        Marca marca = marcaRepository.findById(id).get();
        modelo.addAttribute("marca", marca);

        List<Categoria>listaCategoria = categoriaRepository.findAll();
        modelo.addAttribute("listaCategoria",listaCategoria);
        return "marca_formulario";
    }

}
