package dal;

import java.util.Date;

public class Reputacion extends DALPadre{
	private int repId=-1;
	private int repVMRojo=-1;
	private int repVMAmmbar=-1;
	private int repVMVerde=-1;
	
	public int getRepId() {
		return repId;
	}
	public void setRepId(int repId) {
		this.repId = repId;
	}
	public int getRepVMRojo() {
		return repVMRojo;
	}
	public void setRepVMRojo(int repVMRojo) {
		this.repVMRojo = repVMRojo;
	}
	public int getRepVMAmmbar() {
		return repVMAmmbar;
	}
	public void setRepVMAmmbar(int repVMAmmbar) {
		this.repVMAmmbar = repVMAmmbar;
	}
	public int getRepVMVerde() {
		return repVMVerde;
	}
	public void setRepVMVerde(int repVMVerde) {
		this.repVMVerde = repVMVerde;
	}
	
	
	public Reputacion(int repId,int repVMRojo, int repVMAmmbar, int repVMVerde, 
			String creadoPor, String modificadoPor, Date fCreacion, Date fModificacion) {
		super(creadoPor, modificadoPor, fCreacion, fModificacion);
		this.repId = repId;
		this.repVMRojo = repVMRojo;
		this.repVMAmmbar = repVMAmmbar;
		this.repVMVerde = repVMVerde;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + repId;
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
		Reputacion other = (Reputacion) obj;
		if (repId != other.repId)
			return false;
		return true;
	}
	
	public Reputacion(){
		
	}
	
}
