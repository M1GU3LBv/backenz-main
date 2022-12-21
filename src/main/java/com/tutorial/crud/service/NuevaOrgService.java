package com.tutorial.crud.service;
import com.tutorial.crud.entity.OrganizacionesNueva;
import com.tutorial.crud.entity.Persona;
import com.tutorial.crud.repository.NuevoOrgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NuevaOrgService implements INuevaOrg {
    NuevoOrgRepository nuevoOrgRepository;
    @Autowired
    public NuevaOrgService(NuevoOrgRepository nuevoOrgRepository) {
        this.nuevoOrgRepository = nuevoOrgRepository;
    }

    @Override
    public OrganizacionesNueva save(OrganizacionesNueva organizacionesNueva) {
        return nuevoOrgRepository.save(organizacionesNueva);
    }
    public int add(OrganizacionesNueva organizacionesNueva){

        nuevoOrgRepository.save(organizacionesNueva);
        return organizacionesNueva.getIdos();
    }

    @Override
    public List<OrganizacionesNueva> getAllOrganizaciones() {
        return null;
    }


//    public boolean existsBynombre(String NombreOs){
//        return nuevoOrgRepository.existsByNombreOs(Nombre_os);
//    }


}
