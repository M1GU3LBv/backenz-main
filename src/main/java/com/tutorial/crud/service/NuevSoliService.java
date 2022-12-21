package com.tutorial.crud.service;

import com.tutorial.crud.entity.NuevaSolicitud;
import com.tutorial.crud.entity.OrganizacionesNueva;
import com.tutorial.crud.repository.NuevSoliRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NuevSoliService {
    @Autowired
    NuevSoliRepo nuevSoliRepo;



    public NuevaSolicitud save(NuevaSolicitud nuevaSolicitud) {
        return nuevSoliRepo.save(nuevaSolicitud);
    }



}
