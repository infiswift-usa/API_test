package com.example.RestAPI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface restRepositoryLoginMode extends JpaRepository<LoginMode, Integer> {

}

