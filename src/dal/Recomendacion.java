package dal;

import java.sql.Date;

public class Recomendacion extends DALPadre {
	
	private int recom_id = -1;
	private int recom_porcentajePago = -1;
	
	private int recom_idCli = -1;
	private String recom_codCli = null;
	private String recom_dniCli = null;
	private String recom_apellNomCli = null;
	private int recom_bolId = -1;
	private String recom_bolSerNro = null;
	private Date recom_bolFech = null;
	private String recom_bolImporte = null;
	private String recom_bolPago = null;
	
	private String recom_pagoTotal = null;
		
	public int getRecom_id() {
		return recom_id;
	}
	public void setRecom_id(int recom_id) {
		this.recom_id = recom_id;
	}
	public int getRecom_porcentajePago() {
		return recom_porcentajePago;
	}
	public void setRecom_porcentajePago(int recom_porcentajePago) {
		this.recom_porcentajePago = recom_porcentajePago;
	}	
	public String getRecom_pagoTotal() {
		return recom_pagoTotal;
	}
	public void setRecom_pagoTotal(String recom_pagoTotal) {
		this.recom_pagoTotal = recom_pagoTotal;
	}
	
	
	public int getRecom_idCli() {
		return recom_idCli;
	}
	public void setRecom_idCli(int recom_idCli) {
		this.recom_idCli = recom_idCli;
	}
	public String getRecom_codCli() {
		return recom_codCli;
	}
	public void setRecom_codCli(String recom_codCli) {
		this.recom_codCli = recom_codCli;
	}
	public String getRecom_dniCli() {
		return recom_dniCli;
	}
	public void setRecom_dniCli(String recom_dniCli) {
		this.recom_dniCli = recom_dniCli;
	}
	public String getRecom_apellNomCli() {
		return recom_apellNomCli;
	}
	public void setRecom_apellNomCli(String recom_apellNomCli) {
		this.recom_apellNomCli = recom_apellNomCli;
	}
	public int getRecom_bolId() {
		return recom_bolId;
	}
	public void setRecom_bolId(int recom_bolId) {
		this.recom_bolId = recom_bolId;
	}
	public String getRecom_bolSerNro() {
		return recom_bolSerNro;
	}
	public void setRecom_bolSerNro(String recom_bolSerNro) {
		this.recom_bolSerNro = recom_bolSerNro;
	}
	public Date getRecom_bolFech() {
		return recom_bolFech;
	}
	public void setRecom_bolFech(Date recom_bolFech) {
		this.recom_bolFech = recom_bolFech;
	}
	public String getRecom_bolImporte() {
		return recom_bolImporte;
	}
	public void setRecom_bolImporte(String recom_bolImporte) {
		this.recom_bolImporte = recom_bolImporte;
	}
	public String getRecom_bolPago() {
		return recom_bolPago;
	}
	public void setRecom_bolPago(String recom_bolPago) {
		this.recom_bolPago = recom_bolPago;
	}
	
	
	
	public Recomendacion(int recom_id, int recom_porcentajePago, int recom_idCli, String recom_codCli,
			String recom_dniCli, String recom_apellNomCli, int recom_bolId, String recom_bolSerNro, Date recom_bolFech,
			String recom_bolImporte, String recom_bolPago, String creadoPor, String modificadoPor, java.util.Date fCreacion,
			java.util.Date fModificacion) {
		super(creadoPor, modificadoPor, fCreacion, fModificacion);
		this.recom_id = recom_id;
		this.recom_porcentajePago = recom_porcentajePago;
		this.recom_idCli = recom_idCli;
		this.recom_codCli = recom_codCli;
		this.recom_dniCli = recom_dniCli;
		this.recom_apellNomCli = recom_apellNomCli;
		this.recom_bolId = recom_bolId;
		this.recom_bolSerNro = recom_bolSerNro;
		this.recom_bolFech = recom_bolFech;
		this.recom_bolImporte = recom_bolImporte;
		this.recom_bolPago = recom_bolPago;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + recom_bolId;
		result = prime * result + recom_idCli;
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
		Recomendacion other = (Recomendacion) obj;
		if (recom_bolId != other.recom_bolId)
			return false;
		if (recom_idCli != other.recom_idCli)
			return false;
		return true;
	}
	
	public Recomendacion(){
		
	}

}
