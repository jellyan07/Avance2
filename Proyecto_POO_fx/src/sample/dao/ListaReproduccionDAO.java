package sample.dao;

import sample.entidades.Artista;
import sample.entidades.Cancion;
import sample.entidades.ListadeReproduccion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ListaReproduccionDAO {

    Connection cnx;

    public ListaReproduccionDAO(Connection cnx){
        this.cnx = cnx;
    }

    public void save(ListadeReproduccion material) throws SQLException {
        Statement stmt = cnx.createStatement();
        StringBuilder buildSentence = new StringBuilder("insert into lista (nombre,fecha_creacion,calificacion)");
        buildSentence.append(" values ('");
        buildSentence.append(material.getNombre());
        buildSentence.append("','");
        buildSentence.append(material.getFecha_de_creacion());
        buildSentence.append("',");
        buildSentence.append(material.getCalificacion());
        buildSentence.append(")");
        System.out.println(buildSentence.toString());
        stmt.execute(buildSentence.toString());
    }

    public List<ListadeReproduccion> findAll() throws SQLException {
        ArrayList<ListadeReproduccion> listOfResults = new ArrayList<>();
        Statement stmt = cnx.createStatement();
        ResultSet result = stmt.executeQuery("select * from lista");
        while(result.next()){
            ListadeReproduccion uno = new ListadeReproduccion();
            uno.setId(result.getInt("idlista"));
            uno.setNombre(result.getString("nombre"));
            uno.setFecha_de_creacion(result.getDate("fecha_creacion").toLocalDate());
            uno.setCalificacion(result.getInt("calificacion"));
            listOfResults.add(uno);
        }
        return listOfResults;
    }

    public void saveCancionToLista (Cancion cancion, ListadeReproduccion lista) throws SQLException {
        Statement stmt = cnx.createStatement();
        StringBuilder buildSentence = new StringBuilder("insert into tlista-cancion (idlista,nombre,fecha_creacion,calificacion)");
        buildSentence.append(" values (");
        buildSentence.append(cancion.getID());
        buildSentence.append(",'");
        buildSentence.append(lista.getId());
        buildSentence.append(")");
        System.out.println(buildSentence.toString());
        stmt.execute(buildSentence.toString());
    }

    public ListadeReproduccion findListaByID(int id) throws SQLException {
        List<ListadeReproduccion> listas = findAll();
        for (ListadeReproduccion lista: listas) {
            if(lista.getId() == id) {
                return lista;
            }
        }
        return null;
    }
}
