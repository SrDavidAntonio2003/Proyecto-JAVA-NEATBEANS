/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import datos.BD_Custodio;
import java.sql.SQLException;

/**
 *
 * @author David
 */
public class Custodio extends Personal {

    private int inss;

    public Custodio() {
        this.inss = 0;
        this.nombres = "";
        this.apellidos = "";
        this.telefono = 0;
        this.cedula = "";
        this.activo = 0;
        this.contrasenia = "";

    }

    public Custodio(int inss) {
        this.inss = inss;
    }

    // Guardar
    public Custodio(int inss, String nomb, String apell,
            int telefono, String cedula) {
        this.inss = inss;
        this.nombres = nomb;
        this.apellidos = apell;
        this.telefono = telefono;
        this.cedula = cedula;
    }

    public Custodio(int inss, String contrasenia) {
        this.contrasenia = contrasenia;
        this.inss = inss;
    }

    // Constructor actualizar
    public Custodio(int inss, String nombres, String apellidos,
            int telefono, String Cedula, int activo) {
        this.inss = inss;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.cedula = Cedula;
        this.activo = activo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public int getInss() {
        return inss;
    }

    public void setInss(int inss) {
        this.inss = inss;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public int getActivo() {
        return activo;
    }

    public void setActivo(int activo) {
        this.activo = activo;
    }

    /* ************************************************************************/
 /*                      Métodos de Instancia                              */
 /* ************************************************************************/
    @Override
    public int agregarRegistro() throws ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            SQLException {
        BD_Custodio pa_bd = new BD_Custodio(this);
        return pa_bd.guardar();
    }

    @Override
    public Personal LEER(int inss) throws ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            SQLException {
        BD_Custodio bdP = new BD_Custodio(this);
        return (Personal) bdP.leer(inss);
    }

    @Override
    public int actualizarRegistro(int inss) throws ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            SQLException {
        BD_Custodio bdP = new BD_Custodio(this);
        return bdP.actualizar(inss);
    }

    public boolean verificarCarnetRegistrado(int inss) throws ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            SQLException {
        BD_Custodio CU_bd = new BD_Custodio();
        return CU_bd.verificarCarnetRegistrado(inss);
    }

    @Override
    public int borrarRegistro(int inss) throws ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            SQLException {
        BD_Custodio bdP = new BD_Custodio(this);
        return bdP.borrar(inss);

    }

    public Object[] Listado() throws ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            SQLException {
        BD_Custodio bdP = new BD_Custodio(this);
        return bdP.Listado();
    }

}
