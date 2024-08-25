package com.inventario.sistemainventario.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class usuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RolRepository rolRepository;


    @GetMapping("/usuarios")
    public String listarUsuarios(Model model){
        List<Usuario> listarUsuarios = usuarioRepository.findAll();
        model.addAttribute("listarUsuarios", listarUsuarios);

        return "usuarios";

    }
    @GetMapping("/usuario/nuevo")
    public String mostrarFormularioDeNuevoUsuario(Model model) {
        List<Rol> listaRoles = rolRepository.findAll();
        model.addAttribute("listaRoles",listaRoles);
        model.addAttribute("usuario", new Usuario());
        return "usuario_formulario";
    }
    @PostMapping("/usuarios/guardar")
    public String guardarUsuario(Usuario usuario) {
        usuarioRepository.save(usuario);
        return "redirect:/usuarios";
    }

    @GetMapping("/usuario/editar/{id}")
    public String mostrarFormularioDeEditarUsuario(Model model, @PathVariable("id") Integer id) {

        List<Rol> listaRoles = rolRepository.findAll();
        model.addAttribute("listaRoles",listaRoles);

        model.addAttribute("usuario", usuarioRepository.findById(id).get());
        return "usuario_formulario";
    }
    @GetMapping("/usuario/eliminar/{id}")
    public String eliminarUsuario(@PathVariable("id") Integer id) {
        usuarioRepository.deleteById(id);
        return "redirect:/usuarios";
    }

}
