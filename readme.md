# APP LITERALURA

Esta aplicacion tiene el objetivo de buscar libros mediante la API de Gutendex.

La aplicación se compone de un menu con las siquientes opciones:

* Buscar libro por titulo
* Listar libros registrados
* Listar autores registrados
* Listar autores vivos en un determinado año
* Listar libros por idioma
* Salir

### Buscar libro por titulo
En esta opción solo hay que introducir algun titulo de interes de un libro. 
Nos devolverá un texto con la informacion del titulo, idioma, autor(es) y núnero de descargas, ademas la información se guardara en una BD. 
En caso de no encontrar el libro nos mostrar un texto "Libro no encontrado" y en el caso de que el libro ya existe en la BD al igual nos indicará "No se puede registrar el libro más de una vez".


### Listar libros registrados
Esta opción enlista todos los libros guardados en la BD con la información mencionada anteriormente.

### Listar autores registrados
Esta opción lista los autores registrados en la BD, muestra información: Nombre, año de nacimiento y año de fallecimiento.

### Listar autores vivos en un determinado año
Aquí hay que introducir el año de interes para listar los autores vivos.

### Listar libros por idioma
En esta opción nos aparece un pequeño menu para seleccionar el idioma de interes en listar libros en cuestion, solo hay que introducir la abreviatura del idioma:
* es - español
* en - inglés
* fr - francés
* pt - portugués

### Salir
Finaliza la aplicación.