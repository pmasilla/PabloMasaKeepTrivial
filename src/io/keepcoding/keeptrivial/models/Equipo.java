package io.keepcoding.keeptrivial.models;

import java.util.HashSet;
import java.util.Set;

public class Equipo {
	private String nombre;
	private Set<String> quesitos;
	
	public Equipo(String nombre) {
		this.nombre = nombre;
		this.quesitos = new HashSet<>();
	}
	public void agregarQuesitos(String quesito) {
		quesitos.add(quesito);
	}
	public boolean haGanado() {
		return quesitos.size() == 5;
	}
	public String getNombre() {
		return nombre;
	}
	public Set<String> getQuesitos() {
		return quesitos;
	}
	public void mostrarEstado() {
		System.out.println("Equipo: " + nombre);
		System.out.println("Quesitos conseguidos: " + quesitos);
	}
}
