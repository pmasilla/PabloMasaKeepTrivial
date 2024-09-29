package io.keepcoding.keeptrivial.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Juego {
	private List<Pregunta> preguntas;
	private List<Equipo> equipos;
	private Scanner scanner;
	
	public Juego() {
		this.preguntas = Pregunta.getQuestions();
		this.equipos = new ArrayList<>();
		this.scanner = new Scanner(System.in);
	}
	
	public void iniciarJuego() {
		title("¡Bienvenido a KeepTrivial de Pablo Masa!");
		configurarEquipos();
		turnos();
	}
	
	private void configurarEquipos() {
		title("Configuración de equipos");
		while(true) {
			System.out.println("¿Cuántos equipos jugarán esta vez?: ('q' para salir) ");
			String input = scanner.nextLine();
			
			if (input.equalsIgnoreCase("q")) {
				title("Has salido del juego.");
				System.exit(0);
			}
			try {
				int cantidadEquipos = Integer.parseInt(input);
				if (cantidadEquipos > 0) {
					for (int i = 0; i < cantidadEquipos; i++) {
						nombrarEquipo(i + 1);
					}
					break;
				} else {
					System.out.println("Por favor, ingrese un número mayor a 0, o 'q' para salir.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Entrada inválida. Por favor, ingrese un número o 'q' para salir.");
			}
			
		}
	}
	
	private void nombrarEquipo(int numero) {
		while (true) {
			System.out.println("¿Como se llama el equipo número " + numero + "?('q' para salir.)");
			String nombreEquipo = scanner.nextLine();
			
			if (nombreEquipo.equalsIgnoreCase("q")) {
				System.out.println("Has salido del juego.");
				System.exit(0);
			}
			
			if (!nombreEquipo.trim().isEmpty()) {
				equipos.add(new Equipo(nombreEquipo));
				break;
			} else {
				System.out.println("Debe introducir un nombre.");
			}
		}
	}
	
	private void turnos() {
		boolean juegoTerminado = false;
		//Random random = new Random();
		
		while(!juegoTerminado) {
			for (Equipo equipo: equipos) {
				title("Es el turno de " + equipo.getNombre());
				
				Pregunta pregunta = seleccionarPreguntaAleatoria(equipo);
				
				pregunta.mostrarPregunta();
				System.out.println("Ingrese el número de su respuesta, o 'q' para salir:");
				
				String input;
				boolean entradaValida = false;
				
				while(!entradaValida) {
					input = scanner.next();
					
					if (input.equalsIgnoreCase("q")) {
						title("Has salido del juego.");
						System.exit(0);
				}
								
				try {
                    int respuesta = Integer.parseInt(input);
                    if (respuesta >= 1 && respuesta < 5) {
                        entradaValida = true;

                      
                        if (pregunta.esCorrecta(respuesta)) {
                            title("¡Enhorabuena, has acertado!");
                            equipo.agregarQuesitos(pregunta.getTema());
                        } else {
                            title("¡Respuesta incorrecta, pasamos el turno!");
                        }
                        
                    } else {
                        System.out.println("Entrada inválida. Debe ingresar un número entre 1 y 4 o 'q' para salir.");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada inválida. Debe ingresar un número entre 1 y 4 o 'q' para salir.");
                }
            }

            equipo.mostrarEstado();

            
            if (equipo.haGanado()) {
                title("¡El equipo " + equipo.getNombre() + " ha ganado el juego, enhorabuena!");
                juegoTerminado = true;
                break;
            }
		}
    }
}
	
	private Pregunta seleccionarPreguntaAleatoria(Equipo equipo) {
		Random random = new Random();
		List<Pregunta> preguntasDisponibles = new ArrayList<>();
		for (Pregunta pregunta : preguntas) {
			if (!equipo.getQuesitos().contains(pregunta.getTema())) {
				preguntasDisponibles.add(pregunta);
			}
		}
		return preguntasDisponibles.get(random.nextInt(preguntasDisponibles.size()));
	}
	
	public static void title(String text) {
		int length = text.length();
		printHashtagLine(length + 4);

        System.out.println("# " + text + " #");

        printHashtagLine(length + 4);
	}
	
	public static void printHashtagLine(int length) {
        for (int i = 0; i < length; i++) {
            System.out.print("#");
        }
        System.out.println();
    }
	
}
