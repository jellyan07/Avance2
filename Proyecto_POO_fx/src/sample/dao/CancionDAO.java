package sample.dao;

import sample.entidades.Album;
import sample.entidades.Artista;
import sample.entidades.Cancion;
import sample.entidades.Compositor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CancionDAO {

    Connection cnx;
    ArtistaDAO artistaDAO = new ArtistaDAO(cnx);
    AlbumDAO albumDAO = new AlbumDAO(cnx);
    GeneroDAO generoDAO = new GeneroDAO(cnx);
    CompositorDAO compositorDAO = new CompositorDAO(cnx);

    public CancionDAO(Connection cnx){
        this.cnx = cnx;
    }

    public void save(Cancion material) throws SQLException {
        Statement stmt = cnx.createStatement();
        StringBuilder buildSentence = new StringBuilder("insert into cancion (nombre, artista, genero, compositor, fecha_lanzamiento, album, precio)");
        buildSentence.append(" values ('");
        buildSentence.append(material.getNombre());
        buildSentence.append("',");
        buildSentence.append(material.getArtista().getID());
        buildSentence.append(",");
        buildSentence.append(material.getGenero().getID());
        buildSentence.append(",");
        buildSentence.append(material.getCompositor().getID());
        buildSentence.append(",'");
        buildSentence.append(material.getFecha_de_lanzamiento());
        buildSentence.append("',");
        buildSentence.append(material.getAlbum().getID());
        buildSentence.append(",");
        buildSentence.append(material.getPrecio());
        buildSentence.append(")");
        System.out.println(buildSentence.toString());
        stmt.execute(buildSentence.toString());
    }

    public List<Cancion> findAll() throws SQLException {
        ArrayList<Cancion> listOfResults = new ArrayList<>();
        Statement stmt = cnx.createStatement();
        ResultSet result = stmt.executeQuery("select * from cancion");
        while(result.next()){
            Cancion uno = new Cancion();
            uno.setID(result.getInt("idcancion"));
            uno.setNombre(result.getString("nombre"));
            uno.setArtista(artistaDAO.findArtistaByID(result.getInt("idartista")));
            uno.setGenero(generoDAO.findGeneroByID(result.getInt("idgenero")));
            uno.setCompositor(compositorDAO.findCompositorByID(result.getInt("idcompositor")));
            uno.setFecha_de_lanzamiento(result.getDate("fecha_lanzamiento").toLocalDate());
            uno.setAlbum(albumDAO.findAlbumByID(result.getInt("idalbum")));
            uno.setPrecio(result.getDouble("precio"));

            listOfResults.add(uno);
        }
        return listOfResults;
    }

    public Cancion findCancionByID(int id) throws SQLException {
        List<Cancion> canciones = findAll();
        for (Cancion cancion: canciones) {
            if(cancion.getID() == id) {
                return cancion;
            }
        }
        return null;
    }
}
