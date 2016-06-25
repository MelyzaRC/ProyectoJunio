package practice1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;

public class Metodos {
    
    public void mandarahistorial(String url, String usuario, String contrasena, Connection conexion,int code, int prop,int inicial, int ultimo){
     try {
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(url, usuario, contrasena);
            java.sql.Statement st = conexion.createStatement();
            String cadena = "INSERT INTO public.hollis( codigo, propuesta, inicial, final) VALUES (" + code + "," + prop + ", " + inicial +  ", " + ultimo + ")";
            ResultSet result = st.executeQuery(cadena);
            conexion.close();
     }catch(Exception es){
            }
    }
    
    
    
 
    public void busqueda(String url, String usuario, String contrasena, Connection conexion, int state, JList lista, int contador, DefaultListModel modelo, String cadena, Registro primero) {
        String visual = "";
        try {
            Class.forName("org.postgresql.Driver");
            Connection conex = DriverManager.getConnection(url, usuario, contrasena);
            java.sql.Statement st = conex.createStatement();
            ResultSet result = st.executeQuery(cadena);
            //Cadena: es la concatenación que use para hacer el select que muestra los resultados abajo
            while (result.next()) {
                String cod = result.getString("cod_historial");
                String transaccion = result.getString("cod_transaccion");
                String retribucion = result.getString("cod_pago_retribucion");
                Calendar c = Calendar.getInstance();
                String fecha = Integer.toString(c.get(Calendar.DATE)) + "/" + Integer.toString(c.get(Calendar.MONTH)) + "/" + Integer.toString(c.get(Calendar.YEAR));
                Registro nuevo = new Registro();
                nuevo.codigo = Integer.parseInt(cod);
                nuevo.transaccion = Integer.parseInt(transaccion);
                nuevo.fecha = fecha;
                nuevo.retribucion = Integer.parseInt(retribucion);
                nuevo.estado = state;

                if (primero == null) {
                    primero = nuevo;
                } else {
                    nuevo.siguiente=primero;
                    primero=nuevo;
                }
            }
            conex.close();    
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
        
        Registro t1 = new Registro();
            t1=primero;
            while(t1.siguiente != null){
                visual = contador + "-  Propuesta: " + t1.codigo + "        Estado: " + t1.estado + "      Día: " + t1.fecha;
                modelo.addElement(visual);
                lista.setModel(modelo);
                contador++;
                t1=t1.siguiente;
            }
            contador = 1;
    }

    public void cambiarestado(String url, String usuario, String contrasena, Connection conex, int next, int stat) {
        int resp = JOptionPane.showConfirmDialog(null, "¿Desea cambiar de estado el paquete?");
        if (resp == 0) {
            try {
                url = "jdbc:postgresql://localhost:5432/postgres";
                usuario = "postgres";
                contrasena = "Admin123";
                Class.forName("org.postgresql.Driver");
                conex = DriverManager.getConnection(url, usuario, contrasena);
                java.sql.Statement st = conex.createStatement();
                String sql = "UPDATE historial SET estado =" + next + "  WHERE estado =" + stat;
                ResultSet result = st.executeQuery(sql);
                conex.close();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

}
