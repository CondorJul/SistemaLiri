package dal;

import java.sql.Date;

public class Egreso extends DALPadre{
	private int egreId=-1;
	private int egreTipoDoc=-1;
	private String egreDocumento=null;
	private String egreNombreRazon = null;
	private String egreSerieDoc = null;
	private String egreNroDoc = null;
	private String egreRuc = null;
	private String egreDescripcion = null;
	private Date egreFechaEmision =null;
	private String egreMonto=null;
	private String egreComentarios=null;
	private int egreIdConcepto = -1;
	
	public int getEgreId() {
		return egreId;
	}
	public void setEgreId(int egreId) {
		this.egreId = egreId;
	}
	public int getEgreTipoDoc() {
		return egreTipoDoc;
	}
	public void setEgreTipoDoc(int egreTipoDoc) {
		this.egreTipoDoc = egreTipoDoc;
	}
	public String getEgreDocumento() {
		return egreDocumento;
	}
	public void setEgreDocumento(String egreDocumento) {
		this.egreDocumento = egreDocumento;
	}
	public String getEgreNombreRazon() {
		return egreNombreRazon;
	}
	public void setEgreNombreRazon(String egreNombreRazon) {
		this.egreNombreRazon = egreNombreRazon;
	}
	public String getEgreSerieDoc() {
		return egreSerieDoc;
	}
	public void setEgreSerieDoc(String egreSerieDoc) {
		this.egreSerieDoc = egreSerieDoc;
	}
	public String getEgreNroDoc() {
		return egreNroDoc;
	}
	public void setEgreNroDoc(String egreNroDoc) {
		this.egreNroDoc = egreNroDoc;
	}
	public String getEgreRuc() {
		return egreRuc;
	}
	public void setEgreRuc(String egreRuc) {
		this.egreRuc = egreRuc;
	}
	public String getEgreDescripcion() {
		return egreDescripcion;
	}
	public void setEgreDescripcion(String egreDescripcion) {
		this.egreDescripcion = egreDescripcion;
	}
	public Date getEgreFechaEmision() {
		return egreFechaEmision;
	}
	public void setEgreFechaEmision(Date egreFechaEmision) {
		this.egreFechaEmision = egreFechaEmision;
	}
	public String getEgreMonto() {
		return egreMonto;
	}
	public void setEgreMonto(String egreMonto) {
		this.egreMonto = egreMonto;
	}
	public int getEgreIdConcepto() {
		return egreIdConcepto;
	}
	public void setEgreIdConcepto(int egreIdConcepto) {
		this.egreIdConcepto = egreIdConcepto;
	}	
	public String getEgreComentarios() {
		return egreComentarios;
	}
	public void setEgreComentarios(String egreComentarios) {
		this.egreComentarios = egreComentarios;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + egreId;
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
		Egreso other = (Egreso) obj;
		if (egreId != other.egreId)
			return false;
		return true;
	}
	
	public Egreso(int egreId, int egreTipoDoc, String egreNombreRazon, String egreSerieDoc, String egreNroDoc, String egreRuc, String egreDescripcion,
			Date egreFechaEmision, String egreMonto, String egreComentarios, int egreIdConcepto, String creadoPor, String modificadoPor, java.util.Date fCreacion, java.util.Date fModificacion) {
		super(creadoPor, modificadoPor, fCreacion, fModificacion);
		this.egreId = egreId;
		this.egreTipoDoc = egreTipoDoc;
		this.egreNombreRazon = egreNombreRazon;
		this.egreSerieDoc = egreSerieDoc;
		this.egreNroDoc = egreNroDoc;
		this.egreRuc = egreRuc;
		this.egreDescripcion = egreDescripcion;
		this.egreFechaEmision = egreFechaEmision;
		this.egreMonto = egreMonto;
		this.egreComentarios= egreComentarios;
		this.egreIdConcepto = egreIdConcepto;
	}
	
	public Egreso(){
		super();
	}
	
}
