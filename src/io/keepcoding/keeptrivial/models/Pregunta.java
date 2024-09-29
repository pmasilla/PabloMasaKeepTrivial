package io.keepcoding.keeptrivial.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Pregunta {
	private String enunciado;
	private String[] opciones;
	private int respuestaCorrecta;
	private String tema;
	
	public Pregunta(String enunciado, String[] opciones, int respuestaCorrecta, String tema) {
		this.enunciado = enunciado;
		this.opciones = opciones;
		this.respuestaCorrecta = respuestaCorrecta;
		this.tema = tema;
	}

	public boolean esCorrecta(int respuesta) {
		return respuesta == respuestaCorrecta;
	}
	public String getEnunciado() {
		return enunciado;
	}
	public String[] getOpciones() {
		return opciones;
	}
	public String getTema() {
		return tema;
	}
	public void mostrarPregunta() {
		System.out.println(enunciado);
		for (int i = 0; i < opciones.length; i++) {
			System.out.println((i+ 1) + ". " + opciones[i]);
		}
	}
	
	public static ArrayList<Pregunta> getQuestions() {
	    ArrayList<Pregunta> list = new ArrayList<>();
	    File folder = new File("questions");

	    
	    if (!folder.exists()) {
	        Juego.title("Error al cargar el fichero");
	        return list;
	    }

	    File[] filesList = folder.listFiles();

	    for (File file : filesList) {
	        if (file.isFile() && file.getName().endsWith(".txt")) {
	            String topicName = file.getName().substring(0, file.getName().length() - 4);

	            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	                String line;
	                List<String> block = new ArrayList<>();

	                
	                while ((line = br.readLine()) != null) {
	                    block.add(line); 

	                    
	                    if (block.size() == 6) {
	                        String question = block.get(0);
	                        String answer1 = block.get(1);
	                        String answer2 = block.get(2);
	                        String answer3 = block.get(3);
	                        String answer4 = block.get(4);
	                        int rightOption = Integer.parseInt(block.get(5));

	                        
	                        Pregunta nuevaPregunta = new Pregunta(
	                            question,
	                            new String[]{answer1, answer2, answer3, answer4},
	                            rightOption,
	                            topicName
	                        );
	                        list.add(nuevaPregunta);
	                        block.clear();
	                    }
	                }
	            } catch (IOException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	    return list;
	}

}