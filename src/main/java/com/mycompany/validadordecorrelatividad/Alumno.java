package com.mycompany.validadordecorrelatividad;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class Alumno {

    private Conexion conexion = new Conexion();
    String nombre = "";
    int legajo;
    ArrayList<String> materiasAprobadas;

    public Alumno(String nombre, int legajo) {
        this.legajo = legajo;
        this.nombre = nombre;
    }

    public Alumno() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
    }

    public ArrayList<String> getMateriasAprobadas() {
        return materiasAprobadas;
    }

    public void setMateriasAprobadas(ArrayList<String> materiasAprobadas) {
        this.materiasAprobadas = materiasAprobadas;
    }

    @Override
    public String toString() {
        return "Alumno{" + "nombre=" + nombre + ", legajo=" + legajo + ", materiasAprobadas=" + materiasAprobadas + '}';
    }

    public void CrearAlumno() throws SQLException {

        Scanner sc = new Scanner(System.in);

        Alumno alumno = new Alumno();
        System.out.println("------------------------------");
        System.out.println("¿Cual es el nombre del Alumno?");
        System.out.println("------------------------------");
        String nombre = sc.nextLine();
        alumno.setNombre(nombre);
        String materias_aprobadasJson = "";

        
        Integer legajo;
        Integer cantidad;
        System.out.println("------------------------------------");
        System.out.println("¿El alumno tiene Materias Aprobadas?");
        System.out.println("------------------------------------");
        cantidad = sc.nextInt();
        System.out.println("--------------------------");
        System.out.println("¿Cuáles son esas materias?");
        System.out.println("--------------------------");
        if (cantidad > 0) {
                for (int i = 0; i <= cantidad; i++) {
                String input;
                input = sc.nextLine();
                ArrayList<String> materias_aprobadas = new ArrayList<>();
                materias_aprobadas.add(input);
                materias_aprobadasJson = new Gson().toJson(materias_aprobadas);
                
                }
                System.out.println(materias_aprobadasJson);
        }
        System.out.println("------------------------------------");
        System.out.println("Ingresa el N° de Legajo del alumno, ");
        System.out.println("------------------------------------");
        System.out.println("----------------------------------------------------------------------------------");
        System.out.println("para crear un legajo, Introduce dia del cumpleaños y los ultimos 3 digitos del DNI");
        System.out.println("----------------------------------------------------------------------------------");

        legajo = sc.nextInt();

        do {

            alumno.setLegajo(legajo);

            if (String.valueOf(legajo).length() != 5) {
                System.out.println("-----------------------------------------------------");
                System.out.println("no se puede crear alumno, el n° de legajo es invalido");
                System.out.println("Introduce un n° de legajo valido");
                System.out.println("-----------------------------------------------------");
            }
        } while (legajo.toString().length() != 5);

        conexion.establecerConexion();
        Statement st = conexion.conectar.createStatement();
        String query = "INSERT INTO alumnos VALUES(\"" + nombre + "\", " + legajo + ", '" + materias_aprobadasJson + "')";
        st.executeUpdate(query);
        conexion.cerrarConnection();
        System.out.println("----------------------------------------------");
        System.out.println("el alumno/a " + nombre + " se creo correctamente");
        System.out.println("----------------------------------------------");

    }

    public Alumno traerDatosAlumno(int legajo) throws SQLException, JsonProcessingException, IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

        conexion.establecerConexion();
        Statement st = conexion.conectar.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM alumnos WHERE legajo=" + legajo + ";");
do {
        if (rs.next()) {
            setNombre(rs.getString("nombre"));
            setLegajo(rs.getInt("legajo"));
            String materiasAprobadasJson = rs.getString("materias_aprobadas");
            ArrayList<String> materiasAprobadas = mapper.readValue(materiasAprobadasJson, ArrayList.class);
            setMateriasAprobadas(materiasAprobadas);
            toString();
        } else {
            System.out.println("----------------");
            System.out.println("no existe alumno");
            System.out.println("----------------");
        }
    } while(rs.next());

        conexion.cerrarConnection();
        return null;

    }
}
