package com.alura_curso.foro_alura.controllers;
import com.alura_curso.foro_alura.exceptions.ResourceNotFoundException;
import com.alura_curso.foro_alura.models.Usuario;
import com.alura_curso.foro_alura.models.dtos.autenticacion.PerfilUsuarioDTO;
import com.alura_curso.foro_alura.models.dtos.autenticacion.RespuestaAutenticacion;
import com.alura_curso.foro_alura.models.dtos.autenticacion.SolicitudAutenticacion;
import com.alura_curso.foro_alura.repository.iUsuarioRepository;
import com.alura_curso.foro_alura.security.jwt.ProveedorDeToken;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api")
@AllArgsConstructor
public class JWTController {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final ProveedorDeToken proveedorDeToken;
    private final iUsuarioRepository usuarioRepository;

    @PostMapping(value = "/autenticacion")
    public ResponseEntity<?> autenticacion(@RequestBody @Valid SolicitudAutenticacion solicitudAutenticacion){
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                solicitudAutenticacion.getEmail(),
                solicitudAutenticacion.getPassword()
        );

        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = proveedorDeToken.crearToken(authentication);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer "+ token);

        Usuario usuario = usuarioRepository
                .findByEmail(solicitudAutenticacion.getEmail())
                .orElseThrow(ResourceNotFoundException::new);

        RespuestaAutenticacion respuestaAutenticacion = new RespuestaAutenticacion(token, new PerfilUsuarioDTO(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getEmail(),
                usuario.getPassword(),
                usuario.getRole()
        ));

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(respuestaAutenticacion);

    }

}
