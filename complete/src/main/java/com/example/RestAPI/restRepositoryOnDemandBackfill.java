package com.example.RestAPI;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface restRepositoryOnDemandBackfill extends JpaRepository<OnDemandBackfill, Integer> {
	List<OnDemandBackfill> findAllById(int id);
}