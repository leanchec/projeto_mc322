package com.campanha.rpg.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.lang.NonNull;

import com.campanha.rpg.model.Campanha;
import com.campanha.rpg.model.Vertice;

import jakarta.transaction.Transactional;

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

  // Uses raw query to delete without needing to cascade or handle relationships
  @Override
  @Modifying
  @Transactional
  @Query(value = "DELETE FROM vertice_caracteristicas_integer WHERE vertice_id = :verticeId; DELETE FROM vertice_caracteristicas_string WHERE vertice_id = :verticeId; DELETE FROM vizinhos WHERE vertice_id = :verticeId OR vizinho_id = :verticeId; DELETE FROM vertices WHERE id = :verticeId", nativeQuery = true)
  void deleteById(@Param("verticeId") Long verticeId);

  @Override
  default void delete(Vertice entity) {
    deleteById(entity.getId());
  }

  // Count campaigns
  @Override
  long count();
}