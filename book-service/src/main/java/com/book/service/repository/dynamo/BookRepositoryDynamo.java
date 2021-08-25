package com.book.service.repository.dynamo;

import com.book.service.model.dynamo.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepositoryDynamo extends CrudRepository<Book,String> {

}
