package model;

public class Clasificacion {

	private int puesto;
	private String pais;
	private String cancion;
	private String artista;
	private int puntos;

	public Clasificacion(int puesto, String pais, String cancion,
			String artista, int puntos) {
		this.puesto = puesto;
		this.pais = pais;
		this.cancion = cancion;
		this.artista = artista;
		this.puntos = puntos;
	}

	public int getPuesto() {
		return this.puesto;
	}

	public String getPais() {
		return this.pais;
	}

	public String getCancion() {
		return this.cancion;
	}

	public String getArtista() {
		return this.artista;
	}

	public int getPuntos() {
		return this.puntos;
	}

	public String toString () {
		return String.valueOf(this.puesto) + " "
				+ this.pais + " "
				+ this.cancion + " "
				+ this.artista + " "
				+ String.valueOf(this.puntos);
	}

}
