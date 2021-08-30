package com.authentication.service.repository;

import com.authentication.service.model.LoginUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginUserRepository extends CrudRepository<LoginUser,String> {
}
