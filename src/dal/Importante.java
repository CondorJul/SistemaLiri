package dal;

import java.sql.Date;

public class Importante extends DALPadre{
	
	private int impId = -1;
	private String impCodigo = null;
	private String impDescripcion = null;
	private Date impFechaInicio = null;
	private Date impFechaFin = null;
	private String impTalla = null;
	private int impCantidad = -1;
	private int impDemanda = -1;
	private String impComentario = null;
	
	public int getImpId() {
		return impId;
	}
	public void setImpId(int impId) {
		this.impId = impId;
	}
	public String getImpCodigo() {
		return impCodigo;
	}
	public void setImpCodigo(String impCodigo) {
		this.impCodigo = impCodigo;
	}
	public String getImpDescripcion() {
		return impDescripcion;
	}
	public void setImpDescripcion(String impDescripcion) {
		this.impDescripcion = impDescripcion;
	}
	public Date getImpFechaInicio() {
		return impFechaInicio;
	}
	public void setImpFechaInicio(Date impFechaInicio) {
		this.impFechaInicio = impFechaInicio;
	}
	public Date getImpFechaFin() {
		return impFechaFin;
	}
	public void setImpFechaFin(Date impFechaFin) {
		this.impFechaFin = impFechaFin;
	}
	public String getImpTalla() {
		return impTalla;
	}
	public void setImpTalla(String impTalla) {
		this.impTalla = impTalla;
	}
	public int getImpCantidad() {
		return impCantidad;
	}
	public void setImpCantidad(int impCantidad) {
		this.impCantidad = impCantidad;
	}
	public int getImpDemanda() {
		return impDemanda;
	}
	public void setImpDemanda(int impDemanda) {
		this.impDemanda = impDemanda;
	}
	public String getImpComentario() {
		return impComentario;
	}
	public void setImpComentario(String impComentario) {
		this.impComentario = impComentario;
	}
	
	public Importante(int impId, String impCodigo, String impDescripcion, Date impFechaInicio, Date impFechaFin, String impTalla,
			int impCantidad, int impDemanda, String impComentario, String creadoPor, String modificadoPor, java.util.Date fCreacion, java.util.Date fModificacion) {
		super(creadoPor, modificadoPor, fCreacion, fModificacion);
		this.impId = impId;
		this.impCodigo = impCodigo;
		this.impDescripcion = impDescripcion;
		this.impFechaInicio = impFechaInicio;
		this.impFechaFin = impFechaFin;
		this.impTalla = impTalla;
		this.impCantidad = impCantidad;
		this.impDemanda = impDemanda;
		this.impComentario = impComentario;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((impCodigo == null) ? 0 : impCodigo.hashCode());
		result = prime * result + impId;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Importante other = (Importante) obj;
		if (impCodigo == null) {
			if (other.impCodigo != null)
				return false;
		} else if (!impCodigo.equals(other.impCodigo))
			return false;
		if (impId != other.impId)
			return false;
		return true;
	}
	
	public Importante(){
		super();
	}
}
