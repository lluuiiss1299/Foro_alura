package com.alura_curso.foro_alura.services.iServices;
import com.alura_curso.foro_alura.models.dtos.tema.Genero;
import com.alura_curso.foro_alura.models.dtos.tema.TemaDto;

import java.time.LocalDate;
import java.util.List;

public interface iHomeService {

    List<TemaDto> findByGenero(Genero genero);
    List<TemaDto> getTemasByDate(LocalDate localDate);


}
