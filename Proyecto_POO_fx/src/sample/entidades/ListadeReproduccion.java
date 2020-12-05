package sample.entidades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

/**
 * @auhtor Andrea Jimenez
 * @version 1.1
 */
public class ListadeReproduccion {

    private int id;
    private int idusuario;
    private String nombre;
    private LocalDate fecha_de_creacion;
    private int calificacion;

    /**
     * get del id
     * @return
     */

    public int getId() {
        return id;
    }

    /**
     * set del id
     * @param id
     */

    public void setId(int id) {
        this.id = id;
    }

    /**
     * get del nombre
     * @return
     */

    public String getNombre() {
        return nombre;
    }

    /**
     * set del nombre
     * @param nombre
     */

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * get de la fecha de creacion
     * @return
     */

    public LocalDate getFecha_de_creacion() {
        return fecha_de_creacion;
    }

    /**
     * set de la fecha de creación
     * @param fecha_de_creacion
     */

    public void setFecha_de_creacion(LocalDate fecha_de_creacion) {
        this.fecha_de_creacion = fecha_de_creacion;
    }

    /**
     * get de la calificacion
     * @return
     */

    public int getCalificacion() {
        return calificacion;
    }

    /**
     * set de la calificacion
     * @param calificacion
     */

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * get del id del usuario creador
     * @return
     */

    public int getIdusuario() {
        return idusuario;
    }

    /**
     * set del id del creador
     * @param idusuario
     */

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    /**
     * constructor vacío
     */

    public ListadeReproduccion() { }

    /**
     * constructor con parámetros de lista
     * @param idusuario
     * @param nombre
     * @param fecha_de_creacion
     * @param calificacion
     */

    public ListadeReproduccion(int idusuario, String nombre, LocalDate fecha_de_creacion, int calificacion) {
        this.idusuario = idusuario;
        this.nombre = nombre;
        this.fecha_de_creacion = fecha_de_creacion;
        this.calificacion = calificacion;
    }

    /**
     * método toString de lista
     * @return
     */

    @Override
    public String toString() {
        return "ListadeReproduccion{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fecha_de_creacion='" + fecha_de_creacion + '\'' +
                ", calificacion=" + calificacion +
                ", usuario=" + idusuario +
                '}';
    }

    /**
     * método equals
     * @param o
     * @return
     */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListadeReproduccion that = (ListadeReproduccion) o;
        return id == that.id &&
                idusuario == that.idusuario &&
                calificacion == that.calificacion &&
                Objects.equals(nombre, that.nombre) &&
                Objects.equals(fecha_de_creacion, that.fecha_de_creacion);
    }

    /**
     * método hashcode
     * @return
     */

    @Override
    public int hashCode() {
        return Objects.hash(id, idusuario, nombre, fecha_de_creacion, calificacion);
    }
}
