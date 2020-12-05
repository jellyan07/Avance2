package sample.dao;

import sample.entidades.Album;
import sample.entidades.Artista;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AlbumDAO {

    Connection cnx;

    public AlbumDAO(Connection cnx){
        this.cnx = cnx;
    }

    public void save(Album material) throws SQLException {
        Statement stmt = cnx.createStatement();
        StringBuilder buildSentence = new StringBuilder("insert into album (nombre, fecha_lanzamiento, imagen)");
        buildSentence.append(" values ('");
        buildSentence.append(material.getNombre());
        buildSentence.append("','");
        buildSentence.append(material.getFecha_lanzamiento());
        buildSentence.append("','");
        buildSentence.append(material.getImagen());
        buildSentence.append("')");
        System.out.println(buildSentence.toString());
        stmt.execute(buildSentence.toString());
    }

    public List<Album> findAll() throws SQLException {
        ArrayList<Album> listOfResults = new ArrayList<>();
        Statement stmt = cnx.createStatement();
        ResultSet result = stmt.executeQuery("select * from album");
        while(result.next()){
            Album uno = new Album();
            uno.setID(result.getInt("idalbum"));
            uno.setNombre(result.getString("nombre"));
            uno.setNombre(result.getString("fecha_lanzamiento"));
            uno.setNombre(result.getString("imagen"));
            listOfResults.add(uno);
        }
        return listOfResults;
    }

    public Album findAlbumByID(int id) throws SQLException {
        List<Album> albumes = findAll();
        for (Album album: albumes) {
            if(album.getID() == id) {
                return album;
            }
        }
        return null;
    }
}
