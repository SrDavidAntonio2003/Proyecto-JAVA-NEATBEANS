/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

import java.sql.SQLException;
import java.util.ArrayList;
import negocio.Custodio;
import servicios.MS_SQLServer;

/**
 *
 * @author David
 */
public class BD_Custodio {

    private Custodio cu;
    private MS_SQLServer cnx = null; //Manejador de conexión

    public BD_Custodio() {
        cu = null;
    }

    public BD_Custodio(Custodio cu) {
        this.cu = cu;
    }//Fin constructor

    public int guardar() throws ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            SQLException {
        String procedureCall = "{call [Inventory].[Ingresar custodio](?, ?, ?, ?, ?, ?)}";

        cnx = new MS_SQLServer();
        cnx.callState = cnx.conexion.prepareCall(procedureCall);
        cnx.callState.setInt(1, cu.getInss());
        cnx.callState.setString(2, cu.getNombres());
        cnx.callState.setString(3, cu.getApellidos());
        cnx.callState.setInt(4, cu.getTelefono());
        cnx.callState.setString(5, cu.getCedula());
        cnx.callState.setInt(6, 1);

        return cnx.callState.executeUpdate();
    }//Fin método

    public Custodio leer(int inss) throws ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            SQLException {

        Custodio p = null;
        String sql = "select * from  [Inventory].[CustodioBuscarView]\n"
                + "		 where inss = ?";// + inss;
        //Preparar la conexión hacia el SGBD para obtener registros
        //cnx = ServiceFactory.getInstancia().getConexion();
        cnx = new MS_SQLServer();
        //Procesar la ejecución de la consulta en la base de datos 
        cnx.pst = cnx.conexion.prepareStatement(sql);
        cnx.pst.setInt(1, inss);
        cnx.resultado = cnx.pst.executeQuery();
        //Recorrer los resultados obtenidos en la consulta si los hay 
        if (cnx.resultado.next()) {
            //Recuperar los valores del registro y asignar al objeto p
            p = new Custodio(
                    cnx.resultado.getInt("inss"),
                    cnx.resultado.getString("nombres"),
                    cnx.resultado.getString("apellidos"),
                    cnx.resultado.getInt("telefono"),
                    cnx.resultado.getString("cedula"),
                    cnx.resultado.getInt("activo")
            );
        }//Fin de la instrucción if
        return p;//Retornar el objeto con los valores encontrados
    }

    public int actualizar(int inss) throws ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            SQLException {
        String procedureCall = "{call [Inventory].[Actualizar custodio](?, ?, ?, ?, ?, ?)}";

        cnx = new MS_SQLServer();
        cnx.callState = cnx.conexion.prepareCall(procedureCall);
        cnx.callState.setInt(1, inss);
        cnx.callState.setString(2, cu.getNombres());
        cnx.callState.setString(3, cu.getApellidos());
        cnx.callState.setInt(4, cu.getTelefono());
        cnx.callState.setString(5, cu.getCedula());
        cnx.callState.setInt(6, cu.getActivo());

        return cnx.callState.executeUpdate();
    }

    public boolean verificarCarnetRegistrado(int inss) throws ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            SQLException {
        String functionCall = "{? = call [Inventory].[VerificarInssRegistrado](?)}";

        cnx = new MS_SQLServer();
        cnx.callState = cnx.conexion.prepareCall(functionCall);
        cnx.callState.registerOutParameter(1, java.sql.Types.INTEGER);
        cnx.callState.setInt(2, inss);
        cnx.callState.execute();

        int count = cnx.callState.getInt(1);
        return count > 0;
    }

    public int borrar(int inss) throws ClassNotFoundException,
            InstantiationException,
            IllegalAccessException,
            SQLException {
        cnx = new MS_SQLServer();
        String procedureCall = "{call  [Inventory].[BorradoLogico](?, ?)}";
        cnx.callState = cnx.conexion.prepareCall(procedureCall);
        cnx.callState.setInt(1, cu.getActivo());
        cnx.callState.setInt(2, inss);
        return cnx.callState.executeUpdate();
    }

    // List para llenar la tabla
    public Object[] Listado() throws ClassNotFoundException, InstantiationException, IllegalAccessException, SQLException {
        String procedureCall = "{call [Inventory].[ObtenerCustodios]}";
        cnx = new MS_SQLServer();
        cnx.callState = cnx.conexion.prepareCall(procedureCall);
        cnx.resultado = cnx.callState.executeQuery();
        cnx.rstm = cnx.resultado.getMetaData();
        int cantidadColumnas = cnx.rstm.getColumnCount(); //se obtiene el numero de columnas de los resultados
        ArrayList<Object[]> filas = new ArrayList<>(); //arrailist para almacenar las filas de resultados

        while (cnx.resultado.next()) {
            Object[] fila = new Object[cantidadColumnas];//Arreglo de objetos para almacenar los valores de cada fila
            for (int i = 0; i < cantidadColumnas; i++) {
              
                fila[i] = cnx.resultado.getObject(i + 1);
            }
            filas.add(fila);
        }

        Object[] filasArray = filas.toArray(new Object[0]);// convierte el arraylist de filas a un arreglo de objetos

        return filasArray; // Retorna el arreglo de filas
    }

}
