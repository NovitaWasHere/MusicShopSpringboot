package com.nebrija.mvc.controlador;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nebrija.mvc.interfaceService.IInstrumentoService;
import com.nebrija.mvc.interfaceService.IUsuarioService;
import com.nebrija.mvc.modelo.Instrumento;
import com.nebrija.mvc.modelo.Usuario;
import com.nebrija.mvc.modelo.UsuarioIniciar;

import jakarta.validation.Valid;

@Controller
@RequestMapping
public class UsuarioController {
	
	@Autowired
	private IUsuarioService service;
	@Autowired
	private IInstrumentoService Iservice;


	@GetMapping("/crud")
	public String crud(Model modelo, HttpSession session){

		List<Usuario> usuariosAdmin = (List<Usuario>) session.getAttribute("admin");
		List<Usuario> usuarios = (List<Usuario>) session.getAttribute("usuario");

		if (usuariosAdmin != null) {

			List<Usuario> usuario =  service.listarUsuario();
			modelo.addAttribute("usuarios",usuario);
			List<Instrumento> instrumento =  Iservice.listarInstrumento();
			modelo.addAttribute("instrumentos",instrumento);

			return null;

		} else if(usuarios != null){

			return "noAdmin";
		}else {
			// El usuario no está autenticado, redirige a la página de inicio de sesión
			return "noIniciado";
		}

	}

	@GetMapping("/noAdmin")
	public String noAdmin(Model modelo, HttpSession session){

		return "index";

	}
	@GetMapping("/cerrarSesion")
	public String cerrarSesion(Model modelo, HttpSession session){

		session.invalidate();
		return "redirect:/";

	}

	@GetMapping("/noIniciado")
	public String noIniciado(Model modelo, HttpSession session){

		List<Usuario> usuarios = (List<Usuario>) session.getAttribute("usuario");

		if (usuarios != null) {

			return null;

		} else {
			// El usuario no está autenticado, redirige a la página de inicio de sesión
			return "noIniciado";
		}

	}


	@GetMapping("/inicioSesion")
	public String iniciarSesion(Model modelo, HttpSession session) {

		List<Usuario> usuarios = (List<Usuario>) session.getAttribute("usuario");

		if (usuarios != null) {

			return null;

		} else {
			// El usuario no está autenticado, redirige a la página de inicio de sesión
			modelo.addAttribute("usuario", new UsuarioIniciar());

			return "inicioSesion";
		}

	}


	@GetMapping("/recomendaciones")
	public String recomendaciones(HttpSession session) {

		List<Usuario> usuarios = (List<Usuario>) session.getAttribute("usuario");

		if (usuarios != null) {

			return null;

		} else {
			// El usuario no está autenticado, redirige a la página de inicio de sesión
			return "noIniciado";
		}

	}
	
	@PostMapping("/validar")
	public String validar(Model modelo , UsuarioIniciar p, HttpSession session) {
		
		System.out.println(p.getNombre());
		System.out.println(p.getContrasena());
		
		System.out.println("LLegamos aca");
		
		List<Usuario> usuario =  service.listarUsuario();
		
		for (Usuario user : usuario) {
			
            System.out.println(user.getNombre());
            
            if(p.getNombre().equals(user.getNombre())) {
            	
            	System.out.println(p.getNombre() + " es igual a " + user.getNombre());
            	
            	   System.out.println(user.getContrasena());
            	
            	if(p.getContrasena().equals(user.getContrasena())) {
            		
            		System.out.println(p.getContrasena() + "es igual a " + user.getContrasena());
					if(user.getRol() == 1){

						session.setAttribute("usuario", usuario);
						System.out.println("Se supone ya se crea el atributo de la sesion usuario normal");
						return "redirect:/crud";

					}else if(user.getRol() == 2){

						session.setAttribute("usuario", usuario);
						session.setAttribute("admin", usuario);
						System.out.println("Se supone ya se crea el atributo de la sesion usuario administrador");
						return "redirect:/crud";

					}
            		
            	}else {
            		
            		return "redirect:/inicioSesion";
            	}	
            }    
        }
		
		return "redirect:/inicioSesion";
	}

	@GetMapping("/registro")
	public String registro(Model modelo) {
		
		Model s = modelo.addAttribute("usuario", new Usuario());
		System.out.println("Se ha entrado a inicio de sesion" + s);
		
		return "registro";
		
	}
	
	
	@GetMapping("/error")
	public String error() {
		
		return "error";
		
	}

	@GetMapping("/agregarloUsuario")
	public String agregar(Model modelo,HttpSession session) {

		Model s = modelo.addAttribute("usuario", new Usuario());
		System.out.println("LLegue aca con " + s);

		List<Usuario> usuarios = (List<Usuario>) session.getAttribute("usuario");

		if (usuarios != null) {

			return "agregarUsuario";
		} else {
			// El usuario no está autenticado, redirige a la página de inicio de sesión
			return "noIniciado";
		}

	}
	@GetMapping("/editarloUsuario/{id}")
	public String editar(@PathVariable int id, Model modelo,HttpSession session) {

		Optional<Usuario> usuario = service.listarUnico(id);

		modelo.addAttribute("usuario",usuario);

		System.out.println("Editar esta activo");
		List<Usuario> usuarios = (List<Usuario>) session.getAttribute("usuario");

		if (usuarios != null) {

			return "editarUsuario";
		} else {
			// El usuario no está autenticado, redirige a la página de inicio de sesión
			return "noIniciado";
		}

	}

	@GetMapping("eliminarloUsuario/{id}")
	public String eliminar(@PathVariable int id,HttpSession session) {

		System.out.println("Holñadasdas");
		service.eliminar(id);
		System.out.println("Id del usuario = " + id);

		List<Usuario> usuarios = (List<Usuario>) session.getAttribute("usuario");

		if (usuarios != null) {

			return "redirect:/crud";
		} else {
			// El usuario no está autenticado, redirige a la página de inicio de sesión
			return "noIniciado";
		}

	}

	@PostMapping("/guardarUsuario")
	public String save(@Valid Usuario u,HttpSession session) {

		u.setRol(1);
		service.guardar(u);
		System.out.println("Usuario es igual a = " + u);


		List<Usuario> usuarios = (List<Usuario>) session.getAttribute("usuario");

		return "redirect:/crud";

	}

	@PostMapping("/actualizarUsuario/{id}")
	public String actualizar(@Valid Usuario u,@PathVariable int id,HttpSession session) {

		u.setId(id);
		service.guardar(u);
		System.out.println("Usuario es igual a = " + u);

		List<Usuario> usuarios = (List<Usuario>) session.getAttribute("usuario");

		if (usuarios != null) {

			return "redirect:/crud";
		} else {
			// El usuario no está autenticado, redirige a la página de inicio de sesión
			return "noIniciado";
		}

	}

}
