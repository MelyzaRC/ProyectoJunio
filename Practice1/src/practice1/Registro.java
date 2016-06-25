/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practice1;

import java.sql.Date;

/**
 *
 * @author MelyzaR
 */
public class Registro {
    public int codigo;
    public int transaccion;
    public String fecha;
    public int retribucion;
    public int estado;
    Registro siguiente;

    Registro(){
    codigo=0;
    transaccion = 0;
    fecha = null;
    retribucion = 0;
    estado = 0;
    siguiente=null;
    }
    
    Registro(int code, int tran, String fe, int retrib, int state){
    codigo=code;
    transaccion = tran;
    fecha = fe;
    retribucion = retrib;
    estado = state;
    siguiente=null;
    }
    
    

   
}
