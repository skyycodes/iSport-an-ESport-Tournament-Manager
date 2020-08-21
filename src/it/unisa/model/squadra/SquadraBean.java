package it.unisa.model.squadra;

import java.io.Serializable;

public class SquadraBean implements Serializable {

	private static final long serialVersionUID = -991459462790678646L;
	private String nome,nazionalita,teamImage;
	
	public SquadraBean() {
		nome="";
		nazionalita="";
		teamImage="";
	}
	
	public String getTeamImage() {
		return teamImage;
	}
	
	public String setTeamImage(String img) {
		return teamImage=img;
	}

	public String getNazionalita() {
		return nazionalita;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNazionalita(String nazionalita) {
		this.nazionalita = nazionalita;
	}
	
	

}
