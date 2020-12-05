package sample.gestor;

import sample.dao.*;
import sample.entidades.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class Gestor {

    private Connection conection;

    public Gestor(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            this.conection = DriverManager.getConnection( "jdbc:mysql://localhost:3306/bdproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC"
                    , "root", "root");
            this.artistas = new ArtistaDAO(this.conection);
            this.usuarios = new UsuarioDAO(this.conection);
            this.compositores = new CompositorDAO(this.conection);
            this.generos = new GeneroDAO(this.conection);
            this.albumes = new AlbumDAO(this.conection);
            this.listas = new ListaReproduccionDAO(this.conection);
            this.canciones = new CancionDAO(this.conection);
        } catch (Exception e) {
            System.out.println("Cant connect to db");
            e.printStackTrace();
        }
    }

    private ArtistaDAO artistas;
    private UsuarioDAO usuarios;
    private CompositorDAO compositores;
    private GeneroDAO generos;
    private AlbumDAO albumes;
    private ListaReproduccionDAO listas;
    private CancionDAO canciones;

    // USUARIOS

    public void crearUsuario(String nombre, String apellido1, String apellido2, String correo, String password, String nombre_usuario,int edad, String pais, String img) throws SQLException {
        Usuario usuario = new Usuario(nombre, apellido1, apellido2, correo, password, nombre_usuario, edad, pais, img);
        usuarios.save(usuario);
    }

    public List<Usuario> listUsuarios() throws SQLException {
        return usuarios.findAll();
    }

    public Usuario encontrarUsuarioPorID (int id) throws SQLException {
        List<Usuario> usuarios = listUsuarios();
        for (Usuario usuario: usuarios) {
            if(usuario.getId() == id) {
                return usuario;
            }
        }
        return null;
    }

    //ARTISTAS

    public void crearArtista(String nombre, String apellido, String nombre_artístico, LocalDate fecha_de_nacimiento, LocalDate fecha_de_defuncion, String pais_de_nacimiento, String genero, int edad) throws SQLException {
        Artista artista = new Artista(nombre, apellido, nombre_artístico, fecha_de_nacimiento, fecha_de_defuncion, pais_de_nacimiento, genero, edad);
        artistas.save(artista);
    }

    public List<Artista> listArtistas() throws SQLException {
        return artistas.findAll();
    }

    public Artista encontrarArtistaPorID (int id) throws SQLException {
        List<Artista> artistas = listArtistas();
        for (Artista artista: artistas) {
            if(artista.getID() == id) {
                return artista;
            }
        }
        return null;
    }

    //COMPOSITORES

    public void crearCompositor(String nombre, String apellido, String pais_de_nacimiento, Genero genero, int edad) throws SQLException {
        Compositor compositor = new Compositor(nombre, apellido, pais_de_nacimiento, genero, edad);
        compositores.save(compositor);
    }

    public List<Compositor> listCompositores() throws SQLException {
        return compositores.findAll();
    }

    public Compositor encontrarCompositorPorID (int id) throws SQLException {
        List<Compositor> compositores = listCompositores();
        for (Compositor compositor: compositores) {
            if(compositor.getID() == id) {
                return compositor;
            }
        }
        return null;
    }

    //GENEROS

    public void crearGenero(String nombre, String descripcion) throws SQLException {
        Genero genero = new Genero( nombre, descripcion);
        generos.save(genero);
    }

    public List<Genero> listGeneros() throws SQLException {
        return generos.findAll();
    }

    public Genero encontrarGeneroPorID (int id) throws SQLException {
        List<Genero> generos = listGeneros();
        for (Genero genero: generos) {
            if(genero.getID() == id) {
                return genero;
            }
        }
        return null;
    }

    //CANCIONES

    public void crearCancion(String nombre, Artista artista, Genero genero, Compositor compositor, LocalDate fecha_de_lanzamiento, Album album, double precio, Usuario creador) throws SQLException {
        Cancion cancion = new Cancion(nombre, artista, genero, compositor, fecha_de_lanzamiento, album, precio, creador);
        canciones.save(cancion);
    }

    public List<Cancion> listCanciones() throws SQLException {
        return canciones.findAll();
    }

    public Cancion encontrarCancionPorID (int id) throws SQLException {
        List<Cancion> canciones = listCanciones();
        for (Cancion cancion: canciones) {
            if(cancion.getID() == id) {
                return cancion;
            }
        }
        return null;
    }

    //ALBUMES

    public void crearAlbum(String nombre, LocalDate fecha_lanzamiento, String imagen) throws SQLException {
        Album album = new Album( nombre, fecha_lanzamiento, imagen);
        albumes.save(album);
    }

    public List<Album> listAlbumes() throws SQLException {
        return albumes.findAll();
    }

    public Album encontrarAlbumPorID (int id) throws SQLException {
        List<Album> albumes = listAlbumes();
        for (Album album: albumes) {
            if(album.getID() == id) {
                return album;
            }
        }
        return null;
    }

    //LISTAS

    public void crearLista(int idusuario, String nombre, LocalDate fecha_de_creacion, int calificacion) throws SQLException {
        ListadeReproduccion lista = new ListadeReproduccion(idusuario, nombre, fecha_de_creacion, calificacion);
        listas.save(lista);
    }

    public List<ListadeReproduccion> listListas() throws SQLException {
        return listas.findAll();
    }

    public ListadeReproduccion encontrarListaPorID (int id) throws SQLException {
        List<ListadeReproduccion> albumes = listListas();
        for (ListadeReproduccion lista: albumes) {
            if(lista.getId() == id) {
                return lista;
            }
        }
        return null;
    }

}
