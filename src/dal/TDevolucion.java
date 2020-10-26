package dal;

public class TDevolucion  extends DALPadre{
	private int id=-1;
	private int idCliente=-1;
	private String nroEnum=null;
	
	private String fecha=null;
	private String serieBoleta=null;
	private String numeroBoleta=null;

	private String codigoCliente=null;
	private String dniCliente=null;

	private String apellNom=null;
	private String subTotal=null;
	private String desCupones=null;
	private String desAdic=null;
	private String total=null;
	private String fEntrega=null;
	private String fDevolucion=null;
	
	private String tiempoRetante=null;
	
	private String fechaDevuelto=null;
    private int devCompletada=-1; 
    private int devGarantiaCompletada=-1;
    
    
    private String primerArticuloPieza;
    
    
    
	public String getPrimerArticuloPieza() {
		return primerArticuloPieza;
	}

	public void setPrimerArticuloPieza(String primerArticuloPieza) {
		this.primerArticuloPieza = primerArticuloPieza;
	}

	public int getDevGarantiaCompletada() {
		return devGarantiaCompletada;
	}

	public void setDevGarantiaCompletada(int devGarantiaCompletada) {
		this.devGarantiaCompletada = devGarantiaCompletada;
	}

	public int getDevCompletada() {
		return devCompletada;
	}

	public void setDevCompletada(int devCompletada) {
		this.devCompletada = devCompletada;
	}

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

	public String getNroEnum() {
		return nroEnum;
	}

	public void setNroEnum(String nroEnum) {
		this.nroEnum = nroEnum;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getSerieBoleta() {
		return serieBoleta;
	}

	public void setSerieBoleta(String serieBoleta) {
		this.serieBoleta = serieBoleta;
	}

	public String getNumeroBoleta() {
		return numeroBoleta;
	}

	public void setNumeroBoleta(String numeroBoleta) {
		this.numeroBoleta = numeroBoleta;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getDniCliente() {
		return dniCliente;
	}

	public void setDniCliente(String dniCliente) {
		this.dniCliente = dniCliente;
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

	public void setfEntrega(String fEntrega) {
		this.fEntrega = fEntrega;
	}

	public String getFDevolucion() {
		return fDevolucion;
	}

	public void setfDevolucion(String fDevolucion) {
		this.fDevolucion = fDevolucion;
	}

	public String getTiempoRetante() {
		return tiempoRetante;
	}

	public void setTiempoRetante(String tiempoRetante) {
		this.tiempoRetante = tiempoRetante;
	}

	public String getFechaDevuelto() {
		return fechaDevuelto;
	}

	public void setFechaDevuelto(String fechaDevuelto) {
		this.fechaDevuelto = fechaDevuelto;
	}
	

	
	//
	
	
	
	
}
