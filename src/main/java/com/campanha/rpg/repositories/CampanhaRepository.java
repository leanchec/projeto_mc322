package com.campanha.rpg.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.lang.NonNull;

import com.campanha.rpg.model.Campanha;

@RepositoryRestResource(collectionResourceRel = "campanhas", path = "campanhas")
public interface CampanhaRepository extends PagingAndSortingRepository<Campanha, Long>, CrudRepository<Campanha, Long> {
  // This method already exists, but Optional is preferred
  @Override
  @NonNull
  Optional<Campanha> findById(@Param("id") Long id);
  
  // Find campaigns by name
  List<Campanha> findByNome(@Param("nome") String nome);
  
  // Find campaigns containing text in their names
  List<Campanha> findByNomeContaining(@Param("nomeFragment") String nomeFragment);

  // Count campaigns
  @Override
  long count();
}