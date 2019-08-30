package com.example.RestAPI;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface restRepositoryLoginFailure extends JpaRepository<LoginFailure, Integer> {
	//List<LoginFailure> findAllByOrderByIdAsc();
	List<LoginFailure> findAllById(int id);
}