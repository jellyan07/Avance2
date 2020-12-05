package sample.dao;

import sample.entidades.Artista;
import sample.entidades.Usuario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ArtistaDAO {

    Connection cnx;

    public ArtistaDAO(Connection cnx){
        this.cnx = cnx;
    }

    public void save(Artista material) throws SQLException {
        Statement stmt = cnx.createStatement();
        StringBuilder buildSentence = new StringBuilder("insert into artista (idartista,nombre,apellido,nombre_artistico,pais_nacimiento,fecha_nacimiento,fecha_defuncion,genero,edad)");
        buildSentence.append(" values (");
        buildSentence.append(material.getID());
        buildSentence.append(",'");
        buildSentence.append(material.getNombre());
        buildSentence.append("','");
        buildSentence.append(material.getApellido());
        buildSentence.append("','");
        buildSentence.append(material.getNombre_artístico());
        buildSentence.append("','");
        buildSentence.append(material.getPais_de_nacimiento());
        buildSentence.append("','");
        buildSentence.append(material.getFecha_de_nacimiento());
        buildSentence.append("',");
        buildSentence.append(material.getFecha_de_defuncion());
        buildSentence.append(",'");
        buildSentence.append(material.getGenero());
        buildSentence.append("',");
        buildSentence.append(material.getEdad());
        buildSentence.append(")");
        System.out.println(buildSentence.toString());
        stmt.execute(buildSentence.toString());
    }

    public List<Artista> findAll() throws SQLException {
        ArrayList<Artista> listOfResults = new ArrayList<>();
        Statement stmt = cnx.createStatement();
        ResultSet result = stmt.executeQuery("select * from artista");
        while(result.next()){
            Artista uno = new Artista();
            uno.setID(result.getInt("idartista"));
            uno.setNombre(result.getString("nombre"));
            uno.setApellido(result.getString("apellido"));
            uno.setNombre_artístico(result.getString("nombre_artistico"));
            uno.setPais_de_nacimiento(result.getString("pais_nacimiento"));
            uno.setFecha_de_nacimiento(result.getDate("fecha_nacimiento").toLocalDate());
            if(result.getDate("fecha_defuncion") != null) {
                uno.setFecha_de_defuncion(result.getDate("fecha_defuncion").toLocalDate());
            }
            uno.setGenero(result.getString("genero"));
            uno.setEdad(result.getInt("edad"));
            listOfResults.add(uno);
        }
        return listOfResults;
    }

    public Artista findArtistaByID(int id) throws SQLException {
        List<Artista> artistas = findAll();
        for (Artista artista: artistas) {
            if(artista.getID() == id) {
                return artista;
            }
        }
        return null;
    }
}
