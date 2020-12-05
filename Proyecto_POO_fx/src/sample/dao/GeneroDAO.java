package sample.dao;



import sample.entidades.Album;
import sample.entidades.Genero;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GeneroDAO {

    Connection cnx;

    public GeneroDAO(Connection cnx){
        this.cnx = cnx;
    }

    public void save(Genero material) throws SQLException {
        Statement stmt = cnx.createStatement();
        StringBuilder buildSentence = new StringBuilder("insert into genero (nombre, descripcion)");
        buildSentence.append(" values ('");
        buildSentence.append(material.getNombre());
        buildSentence.append("','");
        buildSentence.append(material.getDescripcion());
        buildSentence.append("')");
        System.out.println(buildSentence.toString());
        stmt.execute(buildSentence.toString());
    }

    public List<Genero> findAll() throws SQLException {
        ArrayList<Genero> listOfResults = new ArrayList<>();
        Statement stmt = cnx.createStatement();
        ResultSet result = stmt.executeQuery("select * from genero");
        while(result.next()){
            Genero uno = new Genero();
            uno.setID(result.getInt("idgenero"));
            uno.setNombre(result.getString("nombre"));
            uno.setNombre(result.getString("descripcion"));
            listOfResults.add(uno);
        }
        return listOfResults;
    }

    public Genero findGeneroByID(int id) throws SQLException {
        List<Genero> generos = findAll();
        for (Genero genero: generos) {
            if(genero.getID() == id) {
                return genero;
            }
        }
        return null;
    }
}
