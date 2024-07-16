package com.alura_curso.foro_alura.models.dtos.autenticacion;
import com.alura_curso.foro_alura.models.dtos.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PerfilUsuarioDTO {

    private Integer id;
    private String nombre;
    private String email;
    private String password;
    private Role role;

}
