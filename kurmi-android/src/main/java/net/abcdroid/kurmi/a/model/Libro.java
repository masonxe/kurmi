package net.abcdroid.kurmi.a.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Libro implements Parcelable {

	private String dniParticipante;
	private int idLibro;
	private String titulo;
	private String autor;
	private int paginas;
	private int calificacion;
	private int version;
	
	private String idDevice;
	private String idMySQL;
	
	@Override
	public int describeContents() {
		return 0;
	}
	
	
	@Override
	public void writeToParcel(Parcel dest, int arg1) {
		dest.writeInt(idLibro);
		dest.writeString(titulo);
		dest.writeString(autor);
		dest.writeInt(paginas);
		dest.writeInt(calificacion);
		dest.writeString(dniParticipante);
		
		dest.writeString(idMySQL);
		dest.writeInt(version);
		dest.writeString(idDevice);
	}
	
	private void readFromParcel(Parcel in) {
		idLibro = in.readInt();
		titulo = in.readString();
		autor = in.readString();
		paginas = in.readInt();
		calificacion = in.readInt();
		dniParticipante = in.readString();
		
		idMySQL = in.readString();
		version = in.readInt();
		idDevice = in.readString();
		
	}
	
	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public Libro createFromParcel(Parcel in) {
             return new Libro(in); 
        }
 
         public Libro[] newArray(int size) {
             return new Libro[size];
         }
     };
     
    public Libro(Parcel in) { readFromParcel(in); }
 	
     
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

	public Libro(String titulo, String autor, int paginas, int calificacion, String idDevice, int version) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.paginas = paginas;
		this.calificacion = calificacion;
		this.idDevice= idDevice;
		this.version= version;
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


	public String getIdMySQL() {
		return idMySQL;
	}


	public void setIdMySQL(String idMySQL) {
		this.idMySQL = idMySQL;
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


	public String getDniParticipante() {
		return dniParticipante;
	}


	public void setDniParticipante(String dniParticipante) {
		this.dniParticipante = dniParticipante;
	}



	

}
