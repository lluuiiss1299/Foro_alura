package com.alura_curso.foro_alura.controllers;

import com.alura_curso.foro_alura.models.dtos.respuesta.RespuestaDTO;
import com.alura_curso.foro_alura.services.RespuestaService;
import com.alura_curso.foro_alura.services.iServices.iRespuestaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/respuesta")
public class RespuestaController {

    @Autowired
    private iRespuestaService respuestaService;

    @Autowired
    public RespuestaController(RespuestaService respuestaService) {
        this.respuestaService = respuestaService;
    }

    @GetMapping(value = "/list")
    List<RespuestaDTO> findAll(){
        return respuestaService.findAll();
    }

    @GetMapping("/{id}")
    public RespuestaDTO findById(@PathVariable(value = "id") Integer id){
        return respuestaService.findById(id);
    }

    @GetMapping
    Page<RespuestaDTO> paginate(@PageableDefault(sort ="createdAt",direction = Sort.Direction.DESC, size = 10) Pageable pageable){
        return respuestaService.paginate(pageable);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public RespuestaDTO save(@RequestBody @Valid RespuestaDTO respuestaDTO){
        return respuestaService.save(respuestaDTO);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PutMapping(value = "/{id}")
    RespuestaDTO update(@PathVariable(value = "id") Integer id, @RequestBody @Valid RespuestaDTO respuestaDTO){
        return respuestaService.update(id,respuestaDTO);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    Boolean delete(@PathVariable(value = "id") Integer id){
        return respuestaService.delete(id);
    }


}
