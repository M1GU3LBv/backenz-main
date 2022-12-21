package com.tutorial.crud.repository;

import com.tutorial.crud.entity.NuevaSolicitud;
import com.tutorial.crud.entity.OrganizacionesNueva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NuevSoliRepo extends JpaRepository<NuevaSolicitud, Integer> {
}
