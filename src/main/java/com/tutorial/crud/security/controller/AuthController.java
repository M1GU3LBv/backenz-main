package com.tutorial.crud.security.controller;

import com.tutorial.crud.dto.Mensaje;
import com.tutorial.crud.entity.Persona;
import com.tutorial.crud.security.dto.JwtDto;
import com.tutorial.crud.security.dto.LoginUsuario;
import com.tutorial.crud.security.dto.NuevoUsuario;
import com.tutorial.crud.security.entity.Rol;
import com.tutorial.crud.security.entity.Usuario;
import com.tutorial.crud.security.enums.RolNombre;
import com.tutorial.crud.security.jwt.JwtProvider;
import com.tutorial.crud.security.service.RolService;
import com.tutorial.crud.security.service.UsuarioService;
import com.tutorial.crud.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioService usuarioService;

    @Autowired
    RolService rolService;

    @Autowired
    PersonaService personaService;
    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/nuevoP")
    public ResponseEntity<?> nuevo1(@RequestBody Persona persona){

        if(personaService.existsByDni(persona.getDni()))
            return new ResponseEntity(new Mensaje("ese Dni ya existe"), HttpStatus.BAD_REQUEST);
//        Usuario usuario =
//                new Usuario(nuevoUsuario.getNombreUsuario(),nuevoUsuario.getIdpersona(),
//                        passwordEncoder.encode(nuevoUsuario.getPassword()));
//        Set<Rol> roles = new HashSet<>();
//        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
//        if(nuevoUsuario.getRoles().contains("admin"))
//            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
//        usuario.setRoles(roles);
         int id = personaService.save(persona);

        return new ResponseEntity(new Mensaje(String.valueOf(id)), HttpStatus.CREATED);

    }









    @PostMapping("/nuevo")
    public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult){



        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("correo mal escrito"), HttpStatus.BAD_REQUEST);
        if(usuarioService.existsByNombreUsuario(nuevoUsuario.getNombreUsuario()))
            return new ResponseEntity(new Mensaje("ese nombre ya existe"), HttpStatus.BAD_REQUEST);
//        if(usuarioService.existsByEmail(nuevoUsuario.getEmail()))
//            return new ResponseEntity(new Mensaje("ese email ya existe"), HttpStatus.BAD_REQUEST);
        Usuario usuario =
                new Usuario(nuevoUsuario.getNombreUsuario(),nuevoUsuario.getIdpersona(),
                        passwordEncoder.encode(nuevoUsuario.getPassword()));
        Set<Rol> roles = new HashSet<>();
        roles.add(rolService.getByRolNombre(RolNombre.ROLE_USER).get());
        if(nuevoUsuario.getRoles().contains("admin"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_ADMIN).get());
        usuario.setRoles(roles);
        if(nuevoUsuario.getRoles().contains("gerencia"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_GERENCIA).get());
        usuario.setRoles(roles);
        if(nuevoUsuario.getRoles().contains("mesadepartes"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_MESAPARTES).get());
        if(nuevoUsuario.getRoles().contains("subgerencia"))
            roles.add(rolService.getByRolNombre(RolNombre.ROLE_SUBGERENCIA).get());
        usuario.setRoles(roles);
        usuarioService.save(usuario);

        return new ResponseEntity(new Mensaje("Registrado correctamente"), HttpStatus.CREATED);

    }

    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Mensaje("campos mal puestos"), HttpStatus.BAD_REQUEST);
        Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUsuario.getNombreUsuario(), loginUsuario.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails)authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity(jwtDto, HttpStatus.OK);
    }
}
