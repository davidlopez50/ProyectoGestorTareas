package com.mycompany.proyectogestortareas;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MainGestorTareas {

    // Punto de entrada principal: Controla el flujo del menu interactivo y la interaccion con el usuario a traves de consola
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        GestorTareas gestor = new GestorTareas();
        int opcion = -1;

        System.out.println("Bienvenido al gestor de tareas.");

        while (opcion != 0) {
            System.out.println("\n=== Menu ===");
            System.out.println("1. Agregar tarea nueva");
            System.out.println("2. Procesar siguiente tarea");
            System.out.println("3. Ver tareas pendientes");
            System.out.println("4. Ver tareas completadas");
            System.out.println("5. Completar una tarea");
            System.out.println("6. Deshacer ultima accion");
            System.out.println("7. Buscar tarea por nombre");
            System.out.println("8. Ordenar pendientes");
            System.out.println("9. Ver arbol de categorias");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");

            // Si escriben letras, se atrapa el error y se vuelve a mostrar el menu
            try {
                opcion = Integer.parseInt(entrada.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: debe escribir un numero.");
                opcion = -1;
                continue;
            }

            switch (opcion) {
                // Opcion 1: Lectura de datos, instanciacion de la tarea y envio al buzon
                case 1: {
                    System.out.print("Nombre de la tarea: ");
                    String nombre = entrada.nextLine();
                    System.out.print("Descripcion: ");
                    String descripcion = entrada.nextLine();
                    System.out.print("Fecha limite (aaaa-mm-dd): ");
                    String textoFecha = entrada.nextLine();
                    System.out.print("Prioridad (1, 2 o 3): ");
                    String textoPrioridad = entrada.nextLine();

                    try {
                        LocalDate fecha = LocalDate.parse(textoFecha);
                        int prioridad = Integer.parseInt(textoPrioridad);
                        Tareas t = new Tareas(nombre, descripcion, fecha, prioridad);
                        gestor.recibirTarea(t);
                        System.out.println("Tarea recibida: " + t);
                    } catch (DateTimeParseException e) {
                        System.out.println("Error: la fecha debe tener el formato aaaa-mm-dd.");
                    } catch (NumberFormatException e) {
                        System.out.println("Error: la prioridad debe ser un numero.");
                    } catch (FechaInvalidaException e) {
                        System.out.println("Error: " + e.getMessage());
                    } catch (PrioridadFueraDeRangoException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                }

                // Opcion 2: Procesamiento de la tarea encolada y asignacion a una categoria del arbol
                case 2: {
                    // Saca la primera tarea de la cola y la pasa a pendientes
                    Tareas t = gestor.procesarSiguiente();
                    if (t == null) {
                        System.out.println("El buzon esta vacio.");
                        break;
                    }
                    System.out.println("Tarea procesada: " + t);

                    // Se elige la categoria de la tarea
                    List<Categoria> categorias = gestor.getRaiz().getSubcategorias();
                    System.out.println("Categorias:");
                    for (int i = 0; i < categorias.size(); i++) {
                        System.out.println((i + 1) + ". " + categorias.get(i).getNombre());
                    }
                    System.out.print("Numero de la categoria: ");
                    try {
                        int num = Integer.parseInt(entrada.nextLine());
                        if (num >= 1 && num <= categorias.size()) {
                            categorias.get(num - 1).agregarTarea(t);
                            System.out.println("Tarea asignada a la categoria.");
                        } else {
                            System.out.println("Numero fuera de rango, queda sin categoria.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: queda sin categoria.");
                    }
                    break;
                }

                // Opcion 3: Recorrido y despliegue en pantalla de la lista de tareas pendientes
                case 3: {
                    System.out.println("Tareas pendientes:");
                    if (gestor.getPendientes().isEmpty()) {
                        System.out.println("No hay tareas pendientes.");
                    }
                    for (int i = 0; i < gestor.getPendientes().size(); i++) {
                        System.out.println((i + 1) + ". " + gestor.getPendientes().get(i));
                    }
                    break;
                }

                // Opcion 4: Recorrido y desplieggue en pantalla de la lista de tareas completadas
                case 4: {
                    System.out.println("Tareas completadas:");
                    if (gestor.getCompletadas().isEmpty()) {
                        System.out.println("No hay tareas completadas.");
                    }
                    for (int i = 0; i < gestor.getCompletadas().size(); i++) {
                        System.out.println((i + 1) + ". " + gestor.getCompletadas().get(i));
                    }
                    break;
                }

                // Opcion 5: Marcar una tarea pendiente como completada
                case 5: {
                    if (gestor.getPendientes().isEmpty()) {
                        System.out.println("No hay tareas pendientes.");
                        break;
                    }
                    for (int i = 0; i < gestor.getPendientes().size(); i++) {
                        System.out.println((i + 1) + ". " + gestor.getPendientes().get(i));
                    }
                    System.out.print("Numero de la tarea a completar: ");
                    try {
                        int num = Integer.parseInt(entrada.nextLine());
                        if (num >= 1 && num <= gestor.getPendientes().size()) {
                            Tareas t = gestor.getPendientes().get(num - 1);
                            gestor.marcarComoCompletada(t);
                            System.out.println("Tarea completada: " + t.getNombre());
                        } else {
                            System.out.println("Numero fuera de rango.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: debe escribir un numero.");
                    }
                    break;
                }

                // Opcion 6: Ejecucion de la funcion deshacer (Undo) apoyada en la pila del historial
                case 6: {
                    if (gestor.deshacerAccion()) {
                        System.out.println("Se deshizo la ultima accion.");
                    } else {
                        System.out.println("No hay acciones para deshacer.");
                    }
                    break;
                }

                // Opcion 7: Busqueda binaria de una tarea por su nombre
                case 7: {
                    System.out.print("Nombre de la tarea a buscar: ");
                    String nombre = entrada.nextLine();
                    Tareas t = gestor.buscarTareaPorNombre(nombre);
                    if (t != null) {
                        System.out.println("Tarea encontrada: " + t);
                    } else {
                        System.out.println("No se encontro la tarea.");
                    }
                    break;
                }

                // Opcion 8: Ordenamiento en burbuja de tareas pendientes por fecha limite o prioridad
                case 8: {
                    System.out.println("1. Ordenar por fecha limite");
                    System.out.println("2. Ordenar por prioridad");
                    System.out.print("Seleccione una opcion: ");
                    String criterio = entrada.nextLine();

                    if (!criterio.equals("1") && !criterio.equals("2")) {
                        System.out.println("Opcion no valida.");
                        break;
                    }

                    // Se ordena una copia con burbuja para no alterar la lista original
                    ArrayList<Tareas> copia = new ArrayList<>(gestor.getPendientes());
                    for (int i = 0; i < copia.size() - 1; i++) {
                        for (int j = 0; j < copia.size() - i - 1; j++) {
                            boolean cambiar;
                            if (criterio.equals("1")) {
                                cambiar = copia.get(j).getFechaLimite().isAfter(copia.get(j + 1).getFechaLimite());
                            } else {
                                cambiar = copia.get(j).getPrioridad() > copia.get(j + 1).getPrioridad();
                            }
                            if (cambiar) {
                                Tareas temp = copia.get(j);
                                copia.set(j, copia.get(j + 1));
                                copia.set(j + 1, temp);
                            }
                        }
                    }

                    System.out.println("Tareas ordenadas:");
                    for (int i = 0; i < copia.size(); i++) {
                        System.out.println((i + 1) + ". " + copia.get(i));
                    }
                    break;
                }

                // Opcion 9: Impresion recursiva de la estructura completa del arbol de categorias
                case 9: {
                    gestor.getRaiz().mostrarSubarbol();
                    break;
                }

                // Opcion 0: Salir de la aplicacion
                case 0: {
                    System.out.println("Hasta luego.");
                    break;
                }

                default: {
                    System.out.println("Opcion no valida.");
                }
            }
        }

        entrada.close();
    }
}