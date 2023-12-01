package com.nebrija.mvc.interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nebrija.mvc.modelo.Usuario;

@Repository
public interface IUsuario extends CrudRepository<Usuario, Integer>{

}
