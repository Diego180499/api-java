package com.diego.api.repositories;

import com.diego.api.repositories.models.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserModel, Integer>{
   
    
}
