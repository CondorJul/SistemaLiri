package dal;

import java.util.Date;

public class Concepto extends DALPadre{
	private int conId = -1;
	private String conConcepto = null;
	private double conMonto = -1.00;
	public int getConId() {
		return conId;
	}
	public void setConId(int conId) {
		this.conId = conId;
	}
	public String getConConcepto() {
		return conConcepto;
	}
	public void setConConcepto(String conConcepto) {
		this.conConcepto = conConcepto;
	}
	public double getConMonto() {
		return conMonto;
	}
	public void setConMonto(double conMonto) {
		this.conMonto = conMonto;
	}
	
	
	
	public Concepto(int conId, String conConcepto, double conMonto, String creadoPor, String modificadoPor, Date fCreacion, Date fModificacion) {
		super(creadoPor, modificadoPor, fCreacion, fModificacion);
		this.conId = conId;
		this.conConcepto = conConcepto;
		this.conMonto = conMonto;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + conId;
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
		Concepto other = (Concepto) obj;
		if (conId != other.conId)
			return false;
		return true;
	}
	
	public Concepto(){
		super();
	}
	
}
