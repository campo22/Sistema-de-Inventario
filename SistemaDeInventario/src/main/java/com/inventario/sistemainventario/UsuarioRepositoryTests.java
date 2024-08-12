package com.inventario.sistemainventario;

import com.inventario.sistemainventario.usuario.Rol;
import com.inventario.sistemainventario.usuario.Usuario;
import com.inventario.sistemainventario.usuario.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UsuarioRepositoryTests {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void tesCrearRoles() {
        Rol roAdmin = new Rol("ADMIN");
        Rol rolUser = new Rol("USER");
        Rol rolEditor = new Rol("Editor");

        entityManager.persist(roAdmin);
        entityManager.persist(rolUser);
        entityManager.persist(rolEditor);
    }

    @Test
    public void testCrearUsuario() {
        Rol rolAdmin = entityManager.find(Rol.class, 1);
        Usuario user = new Usuario("Diver", "diver@gmail.com", "admin");
        user.addRol(rolAdmin);
        usuarioRepository.save(user);
    }

    @Test
    public void testCrearUsuarioConDosroles() {
        Rol rolUser = entityManager.find(Rol.class, 2);
        Rol rolEditor = entityManager.find(Rol.class, 3);
        Usuario usuario = new Usuario("Giselle", "giselle@gmail", "12345");
        usuario.addRol(rolUser);
        usuario.addRol(rolEditor);
        usuarioRepository.save(usuario);
    }

    @Test
    public void tesAsignarRolAusuarioExistente() {
        Usuario user = usuarioRepository.findById(1).get();
        Rol rolEditor = entityManager.find(Rol.class, 3);
        user.addRol(rolEditor);

    }

    @Test
    public void tesEliminarRolAusuario() {
        Usuario user = usuarioRepository.findById(1).get();
        Rol rol = new Rol(1);
        user.eliminarRol(rol);
    }

    @Test
    public void testCrearNuevoUsuarioConNuevoRol() {
        Rol rolJefe = new Rol("jefes");
        Usuario user1 = new Usuario("Campo", "cam@gmail", "12345");
        user1.addRol(rolJefe);
        usuarioRepository.save(user1);
    }
    @Test
    public void testObtenerUsuario(){
        Usuario user = usuarioRepository.findById(1).get();
        System.out.println(user.getName());
        System.out.println(user.getEmail());
        System.out.println(user.getRol());
    }
    @Test
    void testEliminarUsuario(){
        usuarioRepository.deleteById(1);
    }

}
