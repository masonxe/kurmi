package org.kurmi.w.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Libro{

	private int idLibro;
	private String titulo;
	private String autor;
	private int paginas;
	private int calificacion;
	private String dniParticipante;
	private int version;
	private String idDevice;
	private String idSQLite;
	
	public Libro() {
		super();
	}

	public Libro(int idLibro, String titulo, String autor, int paginas,
			int calificacion) {
		super();
		this.idLibro = idLibro;
		this.titulo = titulo;
		this.autor = autor;
		this.paginas = paginas;
		this.calificacion = calificacion;
	}

	public Libro(String titulo, String autor, int paginas, int calificacion) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.paginas = paginas;
		this.calificacion = calificacion;
	}

	public int getIdLibro() {
		return idLibro;
	}

	public void setIdLibro(int idLibro) {
		this.idLibro = idLibro;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public int getPaginas() {
		return paginas;
	}

	public void setPaginas(int paginas) {
		this.paginas = paginas;
	}

	public int getCalificacion() {
		return calificacion;
	}

	public void setCalificacion(int calificacion) {
		this.calificacion = calificacion;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getIdDevice() {
		return idDevice;
	}

	public void setIdDevice(String idDevice) {
		this.idDevice = idDevice;
	}

	public String getIdSQLite() {
		return idSQLite;
	}

	public void setIdSQLite(String idSQLite) {
		this.idSQLite = idSQLite;
	}

	public String getDniParticipante() {
		return dniParticipante;
	}

	public void setDniParticipante(String dniParticipante) {
		this.dniParticipante = dniParticipante;
	}


}
