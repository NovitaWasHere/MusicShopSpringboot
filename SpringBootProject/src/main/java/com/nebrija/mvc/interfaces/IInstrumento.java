package com.nebrija.mvc.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nebrija.mvc.modelo.Instrumento;

@Repository
public interface IInstrumento extends CrudRepository<Instrumento, Integer>{

	

}
