package com.diego.api.repositories;

import com.diego.api.repositories.models.MessageModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MessageRepository extends CrudRepository<MessageModel, Integer>{
    
}
