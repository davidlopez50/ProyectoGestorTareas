
package com.mycompany.proyectogestortareas;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

public class GestorTareas {
    
  //Datos
    
    // Atributos
    private Queue<Tareas> buzon;  //Cola para las tareas que van ingresando
    private ArrayList<Tareas> pendientes; //Lista de tareas que estan pendientes
    private ArrayList<Tareas> completadas; //Lista de tareas completadas
    private ArrayDeque<Object> historial; //Pila para el boton de deshacer
    private Categoria raiz; // Raiz apra el arbol de categorias

    // Constructor que inicializa las colecciones principales y construye la estructura inicial del arbol de categorias
    public GestorTareas() {
        this.buzon = new LinkedList<>();
        this.pendientes = new ArrayList<>();
        this.completadas = new ArrayList<>();
        this.historial = new ArrayDeque<>();
        
        //Inicializamos el Arbol de categorias Automaticamente
        inicializarArbolCategorias();
    }
    
    // Metodo privado: Construye la taxonomia completa del arbol de categorias y subcategorias por defecto
    private void inicializarArbolCategorias() {
        this.raiz = new Categoria("Raiz (Sistema Gestor de Tareas)");
        
        // 1. ACADEMICO Y FORMACION
        Categoria cat1 = new Categoria ("1. Academico y formacion");
            Categoria cat1_sub1 = new Categoria("Asignaturas / cursos");
            
                cat1_sub1.agregarSubcategoria(new Categoria("Clases y clases Magistrales"));
                
                cat1_sub1.agregarSubcategoria(new Categoria("Tareas, guias y talleres"));
                
                cat1_sub1.agregarSubcategoria(new Categoria("Examenes y evaluaciones"));
                
       
        cat1.agregarSubcategoria(cat1_sub1);
        cat1.agregarSubcategoria(new Categoria("Proyectos academicos e investigaciones"));
        cat1.agregarSubcategoria(new Categoria ("Pasantias, practicas y servicio social"));
        cat1.agregarSubcategoria(new Categoria ("Formacion Continua (Cursos online, Certificaciones, Idiomas)"));
        
        //2. PROFESIONAL Y LABORAL
        
        Categoria cat2 = new Categoria ("2. Profesional y Laboral");
            Categoria cat2_sub1 = new Categoria("Empleo / Trabajo Principal");
            cat2_sub1.agregarSubcategoria(new Categoria("Operaciones Diarias"));
            cat2_sub1.agregarSubcategoria(new Categoria("Entregables"));
            cat2_sub1.agregarSubcategoria(new Categoria("Reuniones"));
        cat2.agregarSubcategoria(cat2_sub1);
        cat2.agregarSubcategoria(new Categoria ("Emprendimiento y negocios"));
        cat2.agregarSubcategoria(new Categoria ("Proyectos independientes (Freelance / Side projects)"));
        cat2.agregarSubcategoria(new Categoria ("Red de contactos y Busqueda de  empleo"));
        
        //3. FINANZAS Y ECONOMIA
        
        Categoria cat3 = new Categoria ("3. Finanzas y economia");
            cat3.agregarSubcategoria(new Categoria ("Pagos recurrentes y facturas (Luz, agua, internet, alquiler)"));
            cat3.agregarSubcategoria(new Categoria ("Gestion de Deudas, creditos y prestamos"));
            cat3.agregarSubcategoria(new Categoria ("Compras y presupuesto mensual"));
            cat3.agregarSubcategoria(new Categoria ("Ahorro, inversiones y patrimonio"));
            cat3.agregarSubcategoria(new Categoria ("Tramites fiscales e impuestos"));
            
            //4. Salud, bienestar y cuidado personal
            
            Categoria cat4= new Categoria ("4. Salud, bienestar y cuidado personal");
                cat4.agregarSubcategoria(new Categoria ("Salud medica (Citas, analisis, tratamiento medico)"));
                cat4.agregarSubcategoria(new Categoria ("Ejercicio, deporte y acondicionamiento  fisico"));
                cat4.agregarSubcategoria(new Categoria ("Nutricion, dietas y planificacion de comida"));
                cat4.agregarSubcategoria(new Categoria ("Salud mental y descanso"));
                
       // 5. HOGAR, LOGISTICA Y CULTIVO / MASCOTAS
       
       Categoria cat5 = new Categoria ("5. Hogar, logistica y cultivo / mascotas");
        cat5.agregarSubcategoria(new Categoria ("Limpieza, mantenimiento y organizacion del hogar"));
        cat5.agregarSubcategoria(new Categoria ("Reparaciones, obras y renovaciones"));
        cat5.agregarSubcategoria(new Categoria ("Vehiculo y transporte (mantenimiento, combustible, trafico)"));
        cat5.agregarSubcategoria(new Categoria ("Cuidado de mascotas (Vacunas, veterinaria, paseos)"));
        cat5.agregarSubcategoria(new Categoria ("Cultivo, jardineria y huerto (riego, poda, propagacion)"));
        
    //6. TRAMITES, LEGAL Y DOCUMENTACION
    
    Categoria cat6 = new Categoria ("6. Tramites, legal y documentacion");
        cat6.agregarSubcategoria(new Categoria ("Documentacion personal (DUI, pasaporte, licencia de conducir )"));
        cat6.agregarSubcategoria(new Categoria ("Contratos, seguros y garantias"));
        cat6.agregarSubcategoria(new Categoria ("Gestiones gubernamentales y tramites legales"));
    
    //7. PERSONAL, SOCIAL Y RELACIONES
    
    Categoria cat7 = new Categoria ("7. Personal, social y relaciones");
        cat7.agregarSubcategoria(new Categoria("Familia y pareja"));
        cat7.agregarSubcategoria(new Categoria("Amigos y red social"));
        cat7.agregarSubcategoria(new Categoria ("Compromisos e invitaciones"));
        cat7.agregarSubcategoria(new Categoria ("Voluntariado, comunidad y causas sociales"));
    
    // 8. OCIO, EVENTOS Y ESTILO DE VIDA
    
    Categoria cat8 = new Categoria ("8. Ocio, eventos y estilo de vida");
        cat8.agregarSubcategoria(new Categoria ("Psatiempos hobbies y proyectos creativos"));
        cat8.agregarSubcategoria(new Categoria("Eventos, celebraciones y fechas festivas"));
        cat8.agregarSubcategoria(new Categoria ("Viajes, excursiones y salidas"));
        cat8.agregarSubcategoria(new Categoria ("Entretenimiento (Libros por leer, series / peliculas)"));
   
   // 9. VIDA DIGITAL Y TECNOLOGIA      
   
   Categoria cat9 = new Categoria ("9. Vida digital y tecnologia");
    cat9.agregarSubcategoria(new Categoria ("Mantenimiento de equipos (respaldos, pc, celular)"));
    cat9.agregarSubcategoria(new Categoria ("Desarrollo / programacion personal (repositorios, scripts)"));
    cat9.agregarSubcategoria(new Categoria ("Organizacion digital (Limpieza de correos, nube, archivos)"));
    
    
  // 10. Metas, Planificacion e imprevistos ---
  
  Categoria cat10 = new Categoria ("10. Metas, planificacion e imprevistos");
    cat10.agregarSubcategoria(new Categoria ("Objetivos a corto plazo (1 a 6 meses)"));
    cat10.agregarSubcategoria(new Categoria ("Objetivos a largo plazo (Vision de vida / proyectos anuales)"));
    cat10.agregarSubcategoria(new Categoria ("Imprevistos  y urgencias (Contingencias de alta prioridad)"));
  
 
   raiz.agregarSubcategoria(cat1);
   raiz.agregarSubcategoria(cat2);
   raiz.agregarSubcategoria(cat3);
   raiz.agregarSubcategoria(cat4);
   raiz.agregarSubcategoria(cat5);
   raiz.agregarSubcategoria(cat6);
   raiz.agregarSubcategoria(cat7);
   raiz.agregarSubcategoria(cat8);
   raiz.agregarSubcategoria(cat9);
   raiz.agregarSubcategoria(cat10);
        
    }
    
    //Clase para manejar el historial de la pila
    
    // Representa cada registro guardado en la pila del historial para la funcion de deshacer
    private static class AccionHistorial {
        String  tipo; // "Agregar" o "Completar"
        Tareas tarea;

        public AccionHistorial(String tipo, Tareas tarea) {
            this.tipo = tipo;
            this.tarea = tarea;
        }
    }
        
    // Metodo para agregar tareas entrantes a la cola
    public void recibirTarea(Tareas t) {
        buzon.add(t);
    }

    // Metodo para sacar la tarea de la cola y pasarla a pendientes
    public Tareas procesarSiguiente() {
        Tareas siguiente = buzon.poll();
        if (siguiente != null) {
            pendientes.add(siguiente);
            historial.push(new AccionHistorial("AGREGAR", siguiente));
        }
        return siguiente;
    }

    // Metodo para mover una tarea de pendientes a completadas
    public void marcarComoCompletada(Tareas t) {
        if (pendientes.remove(t)) {
            t.setCompletada(true);
            completadas.add(t);
            historial.push(new AccionHistorial("COMPLETAR", t));
        }
    }

    // Metodo para revertir la ultima accion usando la pila
    public boolean deshacerAccion() {
        if (historial.isEmpty()) {
            return false;
        }
        Object objetoAccion = historial.pop();
        if (objetoAccion instanceof AccionHistorial) {
            AccionHistorial accion = (AccionHistorial) objetoAccion;
            if ("AGREGAR".equals(accion.tipo)) {
                pendientes.remove(accion.tarea);
                return true;
            } else if ("COMPLETAR".equals(accion.tipo)) {
                completadas.remove(accion.tarea);
                accion.tarea.setCompletada(false);
                pendientes.add(accion.tarea);
                return true;
            }
        }
        return false;
    }

    // Metodo para ordenar y buscar una tarea por su nombre
    public Tareas buscarTareaPorNombre(String nombre) {
        ArrayList<Tareas> copiaPendientes = new ArrayList<>(pendientes);

        Collections.sort(copiaPendientes, new Comparator<Tareas>() {
            @Override
            public int compare(Tareas t1, Tareas t2) {
                return t1.getNombre().compareToIgnoreCase(t2.getNombre());
            }
        });

        Tareas claveBusqueda = null;
        try {
            claveBusqueda = new Tareas(nombre, java.time.LocalDate.now().plusDays(1), 1);
        } catch (Exception e) {
        }

        int indice = Collections.binarySearch(copiaPendientes, claveBusqueda, new Comparator<Tareas>() {
            @Override
            public int compare(Tareas t1, Tareas t2) {
                return t1.getNombre().compareToIgnoreCase(t2.getNombre());
            }
        });

        if (indice >= 0) {
            return copiaPendientes.get(indice);
        }
        return null;
    }

    // Getters para las estructuras
    public Categoria getRaiz() {
        return raiz;
    }

    public ArrayList<Tareas> getPendientes() {
        return pendientes;
    }

    public ArrayList<Tareas> getCompletadas() {
        return completadas;
    }

    public Queue<Tareas> getBuzon() {
        return buzon;
    }
}