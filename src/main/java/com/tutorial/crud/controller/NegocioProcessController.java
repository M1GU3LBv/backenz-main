package com.tutorial.crud.controller;
import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.entity.NuevaSolicitud;
import com.tutorial.crud.entity.Organizaciones;
import com.tutorial.crud.entity.Solicitud;
import com.tutorial.crud.service.NuevSoliService;
import com.tutorial.crud.service.SolicitudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/process")
public class NegocioProcessController {
    @Autowired
    SolicitudService solicitudService;
@Autowired
NuevSoliService nuevSoliService;




    @GetMapping(value="/listar")
    public List<Solicitud> getAllSolicitud (){


        return solicitudService.getAllSolicitudes();
    }

    @GetMapping("/detailestado/estado")
    public ResponseEntity<List<Solicitud>> getLaptopsByName(@RequestParam String estado) {
        return new ResponseEntity<List<Solicitud>>(solicitudService.getByEstado(estado), HttpStatus.OK);
    }

    @PostMapping(value="/nueva")
    public ResponseEntity<?> create(@RequestBody NuevaSolicitud nuevaSolicitud){
        nuevSoliService.save(nuevaSolicitud);
        return new ResponseEntity(new Mensaje("Solicitud Enviada con exito"), HttpStatus.OK);
    }







}
