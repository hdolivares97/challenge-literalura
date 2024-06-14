package com.aluracursos.literalura.repository;

import com.aluracursos.literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE fechaNacimiento <= :buscarAutor AND fechaFallecimiento > :buscarAutor")
    List<Autor> mostrarAutoresVivos(int buscarAutor);
}
