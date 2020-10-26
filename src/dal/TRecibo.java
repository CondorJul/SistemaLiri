package dal;

public class TRecibo {
	private int id=-1;
	private int idCliente=-1;
	private String nro=null;
	private String serie=null;
	private String numero=null;
	private int tipoInt=-1;
	private String tipo=null;
	private String codDni=null;
	private String apellNom=null;
	private String subTotal=null;
	private String desCupones=null;
	private String desAdic=null;
	private String total=null;
	private String fEntrega=null;
	private String fDevolucion=null;
	
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
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCodDni() {
		return codDni;
	}
	public void setCodDni(String codDni) {
		this.codDni = codDni;
	}
	public String getApellNom() {
		return apellNom;
	}
	public void setApellNom(String apellNom) {
		this.apellNom = apellNom;
	}
	public String getSubTotal() {
		return subTotal;
	}
	public void setSubTotal(String subTotal) {
		this.subTotal = subTotal;
	}
	public String getDesCupones() {
		return desCupones;
	}
	public void setDesCupones(String desCupones) {
		this.desCupones = desCupones;
	}
	public String getDesAdic() {
		return desAdic;
	}
	public void setDesAdic(String desAdic) {
		this.desAdic = desAdic;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	public String getFEntrega() {
		return fEntrega;
	}
	public void setFEntrega(String fEntrega) {
		this.fEntrega = fEntrega;
	}
	public String getFDevolucion() {
		return fDevolucion;
	}
	public void setfDevolucion(String fDevolucion) {
		this.fDevolucion = fDevolucion;
	}
	public String getNro() {
		return nro;
	}
	public void setNro(String nro) {
		this.nro = nro;
	}
	
	
	
	//
	private int estadoAccion=-1;
	private String estado=null;

	public int getEstadoAccion() {
		return estadoAccion;
	}
	public void setEstadoAccion(int estadoAccion) {
		this.estadoAccion = estadoAccion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public int getTipoInt() {
		return tipoInt;
	}
	public void setTipoInt(int tipoInt) {
		this.tipoInt = tipoInt;
	}
	
	
	
}
