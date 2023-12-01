package com.nebrija.mvc.interfaceService;

import java.util.List;
import java.util.Optional;

import com.nebrija.mvc.modelo.Usuario;

public interface IUsuarioService {
	
	public List<Usuario>listarUsuario();
	public Optional<Usuario>listarUnico(int id);
	public int guardar(Usuario u);
	public void eliminar(int id);

}
