/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyectogestortareas;

import java.time.LocalDate;

/**
 *
 * @author david
 */
public class ProyectoGestorTareas {
//ESTE COSO SE USO DE PRUEBA PARA QUE FUNKARA MI PARTE, BORREN ESTO PQ AQUI DEBE DE IR LO DE ARBIZU PQ ES EL MAIN
    //OJITO
    public static void main(String[] args) {
        try {
            Categoria raiz = new Categoria("Raiz");
            Categoria cursos = new Categoria("Cursos");
            raiz.agregarSubcategoria(cursos);

            Tareas t = new Tareas("Entregar proyecto", LocalDate.now().plusDays(5), 1);
            cursos.agregarTarea(t);

            System.out.println(t);
            System.out.println("Total de tareas: " + raiz.contarTareas());
            raiz.mostrarSubarbol();

            new Tareas("Mala", LocalDate.now().plusDays(1), 7); // debe fallar
        } catch (FechaInvalidaException | PrioridadFueraDeRangoException e) {
            System.out.println("Error esperado: " + e.getMessage());
        }
    }
}