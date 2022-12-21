package com.tutorial.crud.service;
import com.tutorial.crud.entity.Organizaciones;
import com.tutorial.crud.entity.Persona;
import com.tutorial.crud.entity.Solicitud;
import com.tutorial.crud.repository.SolicitudRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolicitudService {
    @Autowired
    SolicitudRepository solicitudRepository;

    public int save(Solicitud solicitud){

        solicitudRepository.save(solicitud);
        return solicitud.getIdsolicitud();
    }


    public List<Solicitud> getByEstado(String estado){
        return solicitudRepository.findByEstado(estado);
    }

    public List<Solicitud> getAllSolicitudes() {
        return solicitudRepository.findAll();
    }





}
