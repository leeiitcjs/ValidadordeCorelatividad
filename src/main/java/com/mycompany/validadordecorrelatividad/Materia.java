package com.mycompany.validadordecorrelatividad;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.Array;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Materia {

    private static Conexion conexion = new Conexion();
    String nombre = "";
    ArrayList<String> correlativa = new ArrayList<>();

    public Materia(String nombre) {
        this.nombre = nombre;
    }

    public Materia() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<String> getCorrelativa() {
        return correlativa;
    }

    public void setCorrelativa(ArrayList<String> correlativa) {
        this.correlativa = correlativa;
    }

    @Override
    public String toString() {
        return "Materia{" + "nombre=" + nombre + ", correlativas=" + correlativa + '}';
    }

    public void CrearMateria() throws SQLException {

        Scanner sc = new Scanner(System.in);

        Materia materia = new Materia();
        System.out.println("-------------------------------");
        System.out.println("Ingresa el nombre de la materia");
        System.out.println("-------------------------------");
        String nombre = sc.nextLine();
        materia.setNombre(nombre);
        System.out.println("-------------------------------");
        System.out.println("¿La materia tiene correlativas?");
        System.out.println("-------------------------------");

        int cantidad = sc.nextInt();
        sc.nextLine();

        if (cantidad > 0) {
            System.out.println("---------------------------------------------");
            System.out.println("¿Cuáles son las correlativas de esta materia?");
            System.out.println("---------------------------------------------");
            ArrayList<String> correlativa = new ArrayList<>();
            String input;
            for (int i = 0; i <= cantidad; i++) {
                input = sc.nextLine();
                correlativa.add(input);
            }
            
            String correlativaJson = new Gson().toJson(correlativa);

            conexion.establecerConexion();
            Statement st = conexion.conectar.createStatement();
            st.executeUpdate("INSERT INTO materia VALUES(\"" + nombre + "\"'" + correlativaJson + "');");
            conexion.cerrarConnection();
            System.out.println("--------------------------------");
            System.out.println("La materia se creo correctamente");
            System.out.println("--------------------------------");
        } else {
            conexion.establecerConexion();
            Statement st = conexion.conectar.createStatement();
            st.executeUpdate("INSERT INTO materia VALUES(\"" + nombre + "\",'" + "sin correlativas" + "');");
            conexion.cerrarConnection();
            System.out.println("--------------------------------");
            System.out.println("La materia se creo correctamente");
            System.out.println("--------------------------------");
        }

    }

    public Materia traerDatosMateria(String nombre) throws SQLException, JsonProcessingException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

        conexion.establecerConexion();
        PreparedStatement st = conexion.conectar.prepareStatement("SELECT * FROM materia WHERE nombre = ?");
        st.setString(1, nombre);
        ResultSet rs = st.executeQuery();
        if (rs.next()) {
            setNombre(rs.getString("nombre"));
            String CorrelativaJson = rs.getString("correlativa");
            ArrayList<String> correlativa = null;
            if (CorrelativaJson != null && !CorrelativaJson.isEmpty() && !CorrelativaJson.equals("sin correlativas")) {
                correlativa = mapper.readValue(CorrelativaJson, ArrayList.class);
            }
            setCorrelativa(correlativa);
            toString();
        } else {
            System.out.println("-----------------");
            System.out.println("no existe materia");
            System.out.println("-----------------");
        }

        conexion.cerrarConnection();
        return null;
    }
}
