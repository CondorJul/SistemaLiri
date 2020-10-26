package dal;

import java.util.Date;

import opciones.COpcion;

public class DALPadre {
	
	private String creadoPor=null;
	private String modificadoPor=null;
	private Date fCreacion=null;
	private Date fModificacion=null;
	private  COpcion opciones=new COpcion();
	
	public COpcion getOpciones() {
		return opciones;
	}
	public String getCreadoPor() {
		return creadoPor;
	}
	public void setCreadoPor(String creadoPor) {
		this.creadoPor = creadoPor;
	}
	public String getModificadoPor() {
		return modificadoPor;
	}
	public void setModificadoPor(String modificadoPor) {
		this.modificadoPor = modificadoPor;
	}
	
	public Date getfCreacion() {
		return fCreacion;
	}
	public void setfCreacion(Date fCreacion) {
		this.fCreacion = fCreacion;
	}
	public Date getfModificacion() {
		return fModificacion;
	}
	public void setfModificacion(Date fModificacion) {
		this.fModificacion = fModificacion;
	}

	public DALPadre(String creadoPor, String modificadoPor, Date fCreacion, Date fModificacion) {
		super();
		this.creadoPor = creadoPor;
		this.modificadoPor = modificadoPor;
		this.fCreacion = fCreacion;
		this.fModificacion = fModificacion;
	}
	
	
	public DALPadre(){
		
	}
	
	
	
	

}
