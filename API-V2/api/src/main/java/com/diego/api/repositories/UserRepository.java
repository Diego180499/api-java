package com.diego.api.repositories;

import com.diego.api.models.UsuarioModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UsuarioModel, Integer>{
   
    
}
