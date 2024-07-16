package com.alura_curso.foro_alura.services;

import com.alura_curso.foro_alura.models.dtos.tema.Genero;
import com.alura_curso.foro_alura.models.dtos.tema.TemaDto;
import com.alura_curso.foro_alura.services.iServices.iHomeService;

import java.time.LocalDate;
import java.util.List;

public class HomeService implements iHomeService {

    @Override
    public List<TemaDto> findByGenero(Genero genero) {
        return null;
    }

    @Override
    public List<TemaDto> getTemasByDate(LocalDate localDate) {
        return null;
    }
}
