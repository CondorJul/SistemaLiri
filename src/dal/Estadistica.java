package dal;

public class Estadistica extends DALPadre {
	
	private String estad_conceptoMes = null;
	private Double estad_totalConceptoMes = null;
	
	private Double estad_ingreso = -1.0;
	private Double estad_egreso = -1.0;
	private Double estad_balance = -1.0;
	
	public String getEstad_conceptoMes() {
		return estad_conceptoMes;
	}
	public void setEstad_conceptoMes(String estad_conceptoMes) {
		this.estad_conceptoMes = estad_conceptoMes;
	}
	public Double getEstad_totalConceptoMes() {
		return estad_totalConceptoMes;
	}
	public void setEstad_totalConceptoMes(Double estad_totalConceptoMes) {
		this.estad_totalConceptoMes = estad_totalConceptoMes;
	}
	
	public Double getEstad_ingreso() {
		return estad_ingreso;
	}
	public void setEstad_ingreso(Double estad_ingreso) {
		this.estad_ingreso = estad_ingreso;
	}
	public Double getEstad_egreso() {
		return estad_egreso;
	}
	public void setEstad_egreso(Double estad_egreso) {
		this.estad_egreso = estad_egreso;
	}
	public Double getEstad_balance() {
		return estad_balance;
	}
	public void setEstad_balance(Double estad_balance) {
		this.estad_balance = estad_balance;
	}
	public Estadistica(){
		
	}
	
}
