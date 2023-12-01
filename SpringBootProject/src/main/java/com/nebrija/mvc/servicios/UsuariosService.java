package com.nebrija.mvc.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nebrija.mvc.interfaceService.IUsuarioService;
import com.nebrija.mvc.interfaces.IUsuario;
import com.nebrija.mvc.modelo.Usuario;

@Service
public class UsuariosService implements IUsuarioService{

	@Autowired
	private IUsuario data;
	
	@Override
	public List<Usuario> listarUsuario() {
		// TODO Auto-generated method stub
		return (List<Usuario>) data.findAll();
	}

	@Override
	public Optional<Usuario> listarUnico(int id) {
		
		return data.findById(id);
	}

	@Override
	public int guardar(Usuario u) {
		
		int numero = 0;
		
		Usuario up = data.save(u);
		
		if(!up.equals(null)) {
			
			numero = 1;
			
		}
		return numero;
	}

	@Override
	public void eliminar(int id) {
		
		data.deleteById(id);
		
	}


}
