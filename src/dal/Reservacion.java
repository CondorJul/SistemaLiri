package dal;

public class Reservacion extends DALPadre{
	
	private int id=-1;
	private int idCliente=-1;
	private String fecha=null;
	private String tipoFact=null;
	private String codCliente=null;
	private String dni=null;
	private String apellNom=null;
	private String totalAPagar=null;
	private String aCuenta=null;
	private String saldo=null;
	private String fRecojo=null;
	
	private String articuloPieza=null;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public String getTipoFact() {
		return tipoFact;
	}
	public void setTipoFact(String tipoFact) {
		this.tipoFact = tipoFact;
	}
	public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	public String getApellNom() {
		return apellNom;
	}
	public void setApellNom(String apellNom) {
		this.apellNom = apellNom;
	}
	public String getTotalAPagar() {
		return totalAPagar;
	}
	public void setTotalAPagar(String totalAPagar) {
		this.totalAPagar = totalAPagar;
	}
	public String getACuenta() {
		return aCuenta;
	}
	public void setACuenta(String aCuenta) {
		this.aCuenta = aCuenta;
	}
	public String getSaldo() {
		return saldo;
	}
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}
	public String getFRecojo() {
		return fRecojo;
	}
	public void setFRecojo(String fRecojo) {
		this.fRecojo = fRecojo;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getArticuloPieza() {
		return articuloPieza;
	}
	public void setArticuloPieza(String articuloPieza) {
		this.articuloPieza = articuloPieza;
	}
	
	
	
	
}
