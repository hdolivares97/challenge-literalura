package com.aluracursos.literalura.principal;

import com.aluracursos.literalura.model.*;
import com.aluracursos.literalura.repository.AutorRepository;
import com.aluracursos.literalura.repository.LibroRepository;
import com.aluracursos.literalura.service.ConsumoAPI;
import com.aluracursos.literalura.service.ConvierteDatos;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConvierteDatos conversor = new ConvierteDatos();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private LibroRepository repositorio;
    private AutorRepository autorRepositorio;

    public Principal(LibroRepository repository, AutorRepository autorRepository) {
        this.repositorio = repository;
        this.autorRepositorio = autorRepository;
    }

    public void muestraElMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por titulo
                    2 - Listar libros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos en un determinado año
                    5 - Listar libros por idioma
                    0 - Salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    mostrarLibrosBuscados();
                    break;
                case 3:
                    mostrarAutores();
                    break;
                case 4:
                    mostrarAutoresVivos();
                    break;
                case 5:
                    mostrarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }

    private void buscarLibro() {
        System.out.println("Ingrese el nombre del libro que desea buscar");
        var titulo = teclado.nextLine();

        String json = consumoAPI.obtenerDatos(URL_BASE + titulo.replace(" ", "+"));
        System.out.println(json);
        if (json.equalsIgnoreCase("{\"count\":0,\"next\":null,\"previous\":null,\"results\":[]}")) {
            System.out.println("Libro no encontrado");
        } else {
            DatosLibro libroBuscado = conversor.obtenerDatos(json, Datos.class).resultados().get(0);
            //System.out.println(libroBuscado.titulo());
            var tituloABuscar = libroBuscado.titulo();
            Optional<Libro> tituloBuscado = repositorio.findByTitulo(tituloABuscar);
            if (tituloBuscado.isPresent()) {
                    System.out.println("No se puede registrar el libro más de una vez");
            } else {
                Libro libro = new Libro(libroBuscado);
                repositorio.save(libro);
                System.out.println(libro);
            }
        }
        //DatosLibro libroBuscado = conversor.obtenerDatos(json, Datos.class).resultados().get(0);
        //System.out.println(libroBuscado);
        //Libro libro = new Libro(libroBuscado);
        //repositorio.save(libro);
        //System.out.println(libro);
    }

    public void mostrarLibrosBuscados(){
        List<Libro> libros = repositorio.findAll();
        libros.stream()
                .forEach(System.out::println);
    }

    public void mostrarAutores(){
        List<Autor> autores = autorRepositorio.findAll();
        autores.stream()
                .forEach(System.out::println);
    }

    public void mostrarAutoresVivos(){
        System.out.println("Ingrese el año vivo de autor(es) que desea buscar");
        var anio = teclado.nextInt();
        List<Autor> autoresVivos = autorRepositorio.mostrarAutoresVivos(anio);
        autoresVivos.stream()
                .forEach(System.out::println);
    }

    private void mostrarLibrosPorIdioma() {
        System.out.println("""
                Ingrese el idioma para buscar los libros:
                es - español
                en - inglés
                fr - francés
                pt - portugués
                """);

        var idiomaAbreviado = teclado.nextLine();
        Idioma idioma = Idioma.fromString(idiomaAbreviado);
        List<Libro> librosPorIdioma = repositorio.buscarLibroPorIdioma(idioma);
        librosPorIdioma.stream()
                .forEach(System.out::println);
    }

}
