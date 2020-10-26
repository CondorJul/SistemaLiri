package dal;

public class TBoletaAvalada extends DALPadre{
	private int id=-1;
	private int idCliente=-1;
	private String nroEnum=null;
	
	private String fecha=null;
	private String serieBoleta=null;
	private String numeroBoleta=null;
	
	private int  devEnlazarBoleta=-1;
	
	
	private int devCompletada=-1;
	
	private int devGarantiaCompletada=-1;
	
	private String estadoDevBoleta=null;
	
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
	public int getDevEnlazarBoleta() {
		return devEnlazarBoleta;
	}
	public void setDevEnlazarBoleta(int devEnlazarBoleta) {
		this.devEnlazarBoleta = devEnlazarBoleta;
		
	}
	public String getEstadoDevBoleta() {
		return estadoDevBoleta;
	}

	
	private  void setEstadoDevBoleta(){
		this.estadoDevBoleta=(devCompletada==1 && devGarantiaCompletada==1)?"Dev. Compl.":"Pendiente";
	}
	public int getDevCompletada() {
		return devCompletada;
	}
	public void setDevCompletada(int devCompletada) {
		this.devCompletada = devCompletada;
		setEstadoDevBoleta();
	}
	public int getDevGarantiaCompletada() {
		return devGarantiaCompletada;
	}
	public void setDevGarantiaCompletada(int devGarantiaCompletada) {
		this.devGarantiaCompletada = devGarantiaCompletada;
		setEstadoDevBoleta();
	} 
	
	
}
