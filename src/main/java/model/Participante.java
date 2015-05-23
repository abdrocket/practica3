package model;

public class Participante {
	private String datos;
	private String descripcion;
	private String imgpath;
	private String votantes;
	
	public Participante(String datos, String descripcion, String imgpath, String votantes){
		this.datos = datos;
		this.descripcion = descripcion;
		this.imgpath = imgpath;
		this.votantes = votantes;
	}

	public String toString(){
		return this.datos + System.lineSeparator() +
				this.descripcion;
	}
	
	public String getVotos(){
		return this.votantes;
	}
	
}
