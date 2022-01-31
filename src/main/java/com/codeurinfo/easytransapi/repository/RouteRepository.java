package com.codeurinfo.easytransapi.repository;

import com.codeurinfo.easytransapi.model.Route;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

@RepositoryRestResource
public interface RouteRepository extends JpaRepository<Route, Long> {
  @RestResource(path = "routesByName")
  Optional<Route> findRoutesByName(@Param("name") String name);
}
