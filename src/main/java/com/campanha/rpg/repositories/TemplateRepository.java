package com.campanha.rpg.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.lang.NonNull;

import com.campanha.rpg.model.Campanha;
import com.campanha.rpg.model.Template;

@RepositoryRestResource(collectionResourceRel = "templates", path = "templates")
public interface TemplateRepository extends PagingAndSortingRepository<Template, Long>, CrudRepository<Template, Long> {
  // This method already exists, but Optional is preferred
  @Override
  @NonNull
  Optional<Template> findById(@Param("id") Long id);

  List<Template> findByCampanha(Campanha campanha);

  // Find templates by name
  List<Template> findByNome(@Param("nome") String nome);

  // Find templates containing text in their names
  List<Template> findByNomeContaining(@Param("nomeFragment") String nomeFragment);

  // Count campaigns
  @Override
  long count();
}