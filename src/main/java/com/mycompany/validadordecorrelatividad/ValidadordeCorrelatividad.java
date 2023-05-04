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
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ValidadordeCorrelatividad {

    private static Conexion conexion = new Conexion();

    public static void main(String[] args) throws SQLException, JsonProcessingException, IOException {

        Scanner sc = new Scanner(System.in);

        conexion.establecerConexion();
        Statement st = conexion.conectar.createStatement();
        int opcion;
        String lm;
        do {
            System.out.println("-------------------------------------------");
            System.out.println("-¡Bienvenido al Programa de Inscripciones!-");
            System.out.println("-------------------------------------------");
            System.out.println("1: Para Nuevo Alumno");
            System.out.println("2: Para Crear Materia");
            System.out.println("3: Para realizar una Inscripción");
            System.out.println("4: Para Obtener Datos de Alumnos");
            System.out.println("5: Para Buscar una Materia");
            //System.out.println("6: para nuevo Alumno");
            //System.out.println("7: para nuevo Alumno");
            System.out.println("10: Para Salir");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    Alumno crearAlumno = new Alumno();
                    crearAlumno.CrearAlumno();
                    break;
                case 2:
                    Materia crearMateria = new Materia();
                    crearMateria.CrearMateria();
                    break;
                case 3: //metodo de Inscripción
                    try {
                    int nb;
                    System.out.println("----------------------------------------------------");
                    System.out.println("|Ingresa el legajo del alumno que se va a Inscribir|");
                    System.out.println("----------------------------------------------------");
                    nb = sc.nextInt();
                    sc.nextLine();
                    Alumno datosAlumnos = new Alumno();
                    datosAlumnos.traerDatosAlumno(nb);
                    System.out.println("---------------------------");
                    System.out.println("¿A qué materia se inscribe?");
                    System.out.println("---------------------------");
                    String mt = sc.nextLine();
                    Materia tDMateria = new Materia();
                    tDMateria.traerDatosMateria(mt);
                    
                    if (tDMateria.getCorrelativa() == null){
                    System.out.println("--------------------");
                    System.out.println("Inscripcion Aprobada");
                    System.out.println("--------------------");
                    }else if(tDMateria.getCorrelativa().equals(datosAlumnos.getMateriasAprobadas())){
                    System.out.println("--------------------");
                    System.out.println("Inscripcion Aprobada");
                    System.out.println("--------------------");
                    } else {
                    System.out.println("-----------------------");
                    System.out.println("Inscripcion Desaprobada");
                    System.out.println("-----------------------");
                    }
                } catch (Exception e) {
                    System.err.println(e);
                }
                    break;
                case 4:
                    int nb;
                    System.out.println("---------------------------------");
                    System.out.println("|Insertar N° de legajo de alumno|");
                    System.out.println("---------------------------------");
                    nb = sc.nextInt();
                    Alumno datosAlumnos = new Alumno();
                    datosAlumnos.traerDatosAlumno(nb);
                    System.out.println(datosAlumnos);
                    
                    break;

                case 5:
                    try {
                    System.out.println("-----------------------------");
                    System.out.println("Insertar Nombre de la Materia");
                    System.out.println("-----------------------------");
                    String mt = sc.nextLine();
                    Materia tDMateria = new Materia();
                    tDMateria.traerDatosMateria(mt);
                    System.out.println(tDMateria);
                } catch (Exception e) {
                    System.err.println(e);
                }

                break;
                
                default:
                    System.out.println("---------------------");
                    System.out.println("la opción es invalida");
                    System.out.println("---------------------");
            }

        } while (opcion != 10);
        {
            System.out.println("--------------------------------");
            System.out.println("Gracias por utilizar el programa");
            System.out.println("--------------------------------");
        }

        conexion.cerrarConnection();

    }

}
