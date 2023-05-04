/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.validadordecorrelatividad;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import java.util.HashMap;


/**
 *
 * @author Leonardo Lopez
 */
public class Inscripcion {
    private static Conexion conexion = new Conexion(); 
    
    Materia materia;
    Alumno alumno;
    Date fecha = new Date();
    Boolean aprobado;
  
    public Inscripcion(){
        this.alumno = alumno;
        this.materia = materia;
       
    } 

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Boolean getAprobado() {
        return aprobado;
    }

    public void setAprobado(Boolean aprobado) {
        this.aprobado = aprobado;
    }

  
        
//     public void traerDatosMateria() throws SQLException, JsonProcessingException, IOException {
//
//        Materia materia = new Materia();
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
//        
//        HashMap<String, Materia> hmMaterias = new HashMap<>();
//
//        
//        conexion.establecerConexion();
//        Statement st = conexion.conectar.createStatement();
//
//        ResultSet lt = st.executeQuery("SELECT * FROM materia");
//
//        while (lt.next()) {
//
//            materia = new Materia(lt.getString("nombre"));
//
//            String jsonText = objectMapper.writeValueAsString(lt.getString("correlativa"));
//
//            java.util.ArrayList<String> nombreCorrelativas = objectMapper.readValue(jsonText, java.util.ArrayList.class);
//
//            materia.setCorrelativa(nombreCorrelativas);
//
//            hmMaterias.put(materia.getNombre(), materia);
//
//        }
//        conexion.cerrarConnection();
//        
//        System.out.println(hmMaterias);
       
    @Override
    public String toString(){
        String estado = "aprobada";
        if(this.aprobado==false){
         estado = "desaprobada";
        }
    return "La Inscrpcion del alumno " +alumno +" a la materia " + materia + ", el día " + fecha + ", fue " + estado;   
    }
    
}