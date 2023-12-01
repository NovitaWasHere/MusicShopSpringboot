package com.nebrija.mvc.controlador;

import java.util.List;
import java.util.Optional;

import com.nebrija.mvc.modelo.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nebrija.mvc.interfaceService.IInstrumentoService;
import com.nebrija.mvc.modelo.Instrumento;

import jakarta.validation.Valid;

@Controller
@RequestMapping
public class InstrumentoController {
	
	@Autowired
	private IInstrumentoService Iservice;
	
	@GetMapping("/")
	public String listarIndex(Model modelo, HttpSession session) {

		List<Instrumento> instrumento =  Iservice.listarInstrumento();
		modelo.addAttribute("instrumentos", instrumento);
		
		return "index.html";
		
	}
	
	@GetMapping("/agregarloInstrumento")
	public String agregar(Model modelo,HttpSession session) {

		Model s = modelo.addAttribute("instrumento", new Instrumento());
		System.out.println("LLegue aca con " + s);

		List<Usuario> usuarios = (List<Usuario>) session.getAttribute("usuario");

		if (usuarios != null) {

			return "agregarInstrumento";

		} else {
			// El usuario no está autenticado, redirige a la página de inicio de sesión
			return "noIniciado";
		}

	}
	@GetMapping("/editarloInstrumento/{id}")
	public String editar(@PathVariable int id, Model modelo,HttpSession session) {

		Optional<Instrumento> instrumento = Iservice.listarUnico(id);
		
		modelo.addAttribute("instrumento",instrumento);
		
		System.out.println("Editar esta activo");

		List<Usuario> usuarios = (List<Usuario>) session.getAttribute("usuario");

		if (usuarios != null) {

			return "editarInstrumento";

		} else {
			// El usuario no está autenticado, redirige a la página de inicio de sesión
			return "noIniciado";
		}

		
	}
	@GetMapping("eliminarloInstrumento/{id}")
	public String eliminar(@PathVariable int id,HttpSession session) {
		
		System.out.println("Holñadasdas");
		Iservice.eliminar(id);
		System.out.println("Id del usuario = " + id);

		List<Usuario> usuarios = (List<Usuario>) session.getAttribute("usuario");

		if (usuarios != null) {

			return "redirect:/crud";

		} else {
			// El usuario no está autenticado, redirige a la página de inicio de sesión
			return "noIniciado";
		}
		
	}
	
	@PostMapping("/guardarInstrumento")
	public String save(@Valid Instrumento u,HttpSession session) {
		
		Iservice.guardar(u);
		System.out.println("Instrumento es igual a = " + u);

		List<Usuario> usuarios = (List<Usuario>) session.getAttribute("usuario");

		if (usuarios != null) {

			return "redirect:/crud";

		} else {
			// El usuario no está autenticado, redirige a la página de inicio de sesión
			return "noIniciado";
		}
		
	}
	
	@PostMapping("/actualizarInstrumento/{id}")
	public String actualizar(@Valid Instrumento u, @PathVariable int id, HttpSession session) {
		
		u.setId(id);
		Iservice.guardar(u);
		System.out.println("Instrumento es igual a = " + u);

		List<Usuario> usuarios = (List<Usuario>) session.getAttribute("usuario");

		if (usuarios != null) {

			return "redirect:/crud";

		} else {
			// El usuario no está autenticado, redirige a la página de inicio de sesión
			return "noIniciado";
		}
		
	}
	
	
	


}
