package com.campanha.rpg.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.lang.NonNull;

import com.campanha.rpg.model.Campanha;
import com.campanha.rpg.model.Vertice;

@RepositoryRestResource(collectionResourceRel = "vertices", path = "vertices")
public interface VerticeRepository extends PagingAndSortingRepository<Vertice, Long>, CrudRepository<Vertice, Long> {
  @Override
  @NonNull
  Optional<Vertice> findById(@Param("id") Long id);

  List<Vertice> findByCampanha(Campanha campanha);

  // Find vertices by name
  List<Vertice> findByNome(@Param("nome") String nome);

  // Find vertices containing text in their names
  List<Vertice> findByNomeContaining(@Param("nomeFragment") String nomeFragment);

  // Count campaigns
  @Override
  long count();
}