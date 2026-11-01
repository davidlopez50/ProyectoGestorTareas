/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectogestortareas;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author david
 */
public class Categoria {

    private String nombre;
    private Categoria padre;
    private List<Categoria> subcategorias;
    private List<Tareas> tareasAsignadas;

    // Constructor que inicializa el nombre de la categoria y sus listas de subcategorias y tareas asignadas
    public Categoria(String nombre) {
        this.nombre = nombre;
        this.padre = null;
        this.subcategorias = new ArrayList<>();
        this.tareasAsignadas = new ArrayList<>();
    }

    // Metodos de acceso para obtener los atributos principales de la categoria
    public String getNombre() {
        return nombre;
    }

    public Categoria getPadre() {
        return padre;
    }

    public List<Categoria> getSubcategorias() {
        return subcategorias;
    }

    public List<Tareas> getTareasAsignadas() {
        return tareasAsignadas;
    }

    // Metodo que vincula una subcategoria como hija de la categoria actual y establece a esta ultima como su padre
    public void agregarSubcategoria(Categoria sub) {
        sub.padre = this;
        subcategorias.add(sub);
    }

    // Metodo para asignar una tarea a esta categoria y vincular la categoria en la tarea
    public void agregarTarea(Tareas t) {
        t.setCategoriaAsignada(this);
        tareasAsignadas.add(t);
    }

    // Metodo para remover una tarea asignada y desvincular la referencia de la categoria
    public void quitarTarea(Tareas t) {
        tareasAsignadas.remove(t);
        t.setCategoriaAsignada(null);
    }

    // Suma las tareas de esta categoría y las de cada subcategoría
    public int contarTareas() {
        int total = tareasAsignadas.size();
        for (Categoria sub : subcategorias) {
            total += sub.contarTareas();
        }
        return total;
    }

    // Imprime el árbol con sangría según el nivel
    public void mostrarSubarbol(int nivel) {
        String sangria = "";
        for (int i = 0; i < nivel; i++) {
            sangria += "\t";
        }
        System.out.println(sangria + nombre + " [" + contarTareas() + "]");
        for (Categoria sub : subcategorias) {
            sub.mostrarSubarbol(nivel + 1);
        }
    }

    public void mostrarSubarbol() {
        mostrarSubarbol(0);
    }

    // Sobrescribe el metodo toString para retornar unicamente el nombre de la categoria en las impresiones
    @Override
    public String toString() {
        return nombre;
    }
}