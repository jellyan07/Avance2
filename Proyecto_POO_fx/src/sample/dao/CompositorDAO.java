package sample.dao;

import sample.entidades.Artista;
import sample.entidades.Compositor;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompositorDAO {


    Connection cnx;

    public CompositorDAO(Connection cnx){
        this.cnx = cnx;
    }
    private GeneroDAO generoDAO = new GeneroDAO(cnx);

    public void save(Compositor material) throws SQLException {
        Statement stmt = cnx.createStatement();
        StringBuilder buildSentence = new StringBuilder("insert into compositor (nombre,apellido,pais_nacimiento, id_genero, edad)");
        buildSentence.append(" values ('");
        buildSentence.append(material.getNombre());
        buildSentence.append("','");
        buildSentence.append(material.getApellido());
        buildSentence.append("','");
        buildSentence.append(material.getPais_de_nacimiento());
        buildSentence.append("',");
        buildSentence.append(material.getGenero().getID());
        buildSentence.append(",");
        buildSentence.append(material.getEdad());
        buildSentence.append(")");
        System.out.println(buildSentence.toString());
        stmt.execute(buildSentence.toString());
    }

    public List<Compositor> findAll() throws SQLException {
        ArrayList<Compositor> listOfResults = new ArrayList<>();
        Statement stmt = cnx.createStatement();
        ResultSet result = stmt.executeQuery("select * from compositor");
        while(result.next()){
            Compositor uno = new Compositor();
            uno.setID(result.getInt("idcompositor"));
            uno.setNombre(result.getString("nombre"));
            uno.setApellido(result.getString("apellido"));
            uno.setGenero(generoDAO.findGeneroByID(result.getInt("genero")));
            uno.setPais_de_nacimiento(result.getString("pais_nacimiento"));
            uno.setEdad(result.getInt("edad"));
            listOfResults.add(uno);
        }
        return listOfResults;
    }

    public Compositor findCompositorByID(int id) throws SQLException {
        List<Compositor> compositores = findAll();
        for (Compositor compositor: compositores) {
            if(compositor.getID() == id) {
                return compositor;
            }
        }
        return null;
    }
}
