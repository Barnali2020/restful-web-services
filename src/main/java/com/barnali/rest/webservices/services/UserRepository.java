package com.barnali.rest.webservices.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barnali.rest.webservices.beans.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
