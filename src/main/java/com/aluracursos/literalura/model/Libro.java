package com.aluracursos.literalura.model;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")

public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String titulo;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private Set<Autor> autores;
    @Enumerated(EnumType.STRING)
    private Idioma idioma;
    private Double numeroDeDescargas;

    public Libro(){}

    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();
        this.idioma = Idioma.fromString(datosLibro.idioma().get(0));
        this.autores = datosLibro.autor().stream()
                .map(a -> new Autor(a))
                .collect(Collectors.toSet());
    }

    @Override
    public String toString() {
        String listaAutores = autores.stream()
                .map(Autor::getNombre)
                .collect(Collectors.joining(", "));

        return
                "----------- Libro -------------" +
                "\n Titulo:" + titulo +
                        "\n Autor:" + listaAutores +
                        "\n Idioma:" + idioma +
                        "\n numeroDeDescargas:" + numeroDeDescargas +
                "\n ------------------------------- \n \n";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Set<Autor> getAutor() {
        return autores;
    }

    public void setAutor(Set<Autor> autor) {
        this.autores = autor;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }
}
