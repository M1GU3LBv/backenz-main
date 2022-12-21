package com.tutorial.crud.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.persistence.*;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "solicitud")
public class NuevaSolicitud {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idsolicitud;

    private int idos;

    private String estado;
    private String f_ingreso;

    private String n_expediente;
    private String tipo;

    private String i_gob;
    private String f_gob;

}
