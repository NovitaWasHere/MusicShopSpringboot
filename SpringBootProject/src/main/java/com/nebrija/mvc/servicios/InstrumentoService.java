package com.nebrija.mvc.servicios;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nebrija.mvc.interfaceService.IInstrumentoService;
import com.nebrija.mvc.interfaces.IInstrumento;
import com.nebrija.mvc.modelo.Instrumento;

@Service
public class InstrumentoService implements IInstrumentoService{

	@Autowired
	private IInstrumento data;
	
	@Override
	public List<Instrumento> listarInstrumento() {
		// TODO Auto-generated method stub
		return (List<Instrumento>) data.findAll();
	}

	@Override
	public Optional<Instrumento> listarUnico(int id) {
		
		return data.findById(id);
	}

	@Override
	public int guardar(Instrumento u) {
		
		int numero = 0;
		
		Instrumento up = data.save(u);
		
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
