package com.barnali.rest.webservices.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barnali.rest.webservices.beans.Post;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer>{

}
