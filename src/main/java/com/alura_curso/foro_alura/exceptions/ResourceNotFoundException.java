package com.alura_curso.foro_alura.exceptions;
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException() {
        super();
    }
    public ResourceNotFoundException(String s) {
        super(s);
    }
}
