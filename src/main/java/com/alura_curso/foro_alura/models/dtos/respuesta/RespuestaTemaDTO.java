package com.alura_curso.foro_alura.models.dtos.respuesta;
import lombok.Data;
@Data
public class RespuestaTemaDTO {
    private Integer id;
    private String mensajeRespuesta;
    private Integer usuarioId;
    private String usuarioNombre;
    private String filePerfilRespuesta;
}
