package com.nebrija.mvc.interfaceService;

import java.util.List;
import java.util.Optional;

import com.nebrija.mvc.modelo.Instrumento;

public interface IInstrumentoService {
	
	public List<Instrumento>listarInstrumento();
	public Optional<Instrumento>listarUnico(int id);
	public int guardar(Instrumento u);
	public void eliminar(int id);


}
