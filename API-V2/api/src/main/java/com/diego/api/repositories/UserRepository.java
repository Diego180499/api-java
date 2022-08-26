
package com.diego.api.repositories;

import com.diego.api.models.UsuarioModel;
import java.util.ArrayList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UsuarioModel, Integer>{
    
    //public abstract ArrayList<UsuarioModel> buscarUsuarioNombre(String name);
    
}
