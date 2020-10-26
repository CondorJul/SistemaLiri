package dal;

public class TBoletaSee extends DALPadre{

	private int id=-1;
	private int tipo=-1;
	
	private String fecha=null;

	private String fEntrega=null;
	private String fDevolucion=null;
	
	private String serieBoleta=null;
	private String numeroBoleta=null;


	private String subTotal=null;
	private String desCupones=null;
	private String desAdic=null;
	private String total=null;
	

	
	private String sepFRecojo=null;
	
	private String sepFACuenta=null;
	private String sepSaldo=null;
	
	private int diasRecojo=0;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	
	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getfEntrega() {
		return fEntrega;
	}
	public void setfEntrega(String fEntrega) {
		this.fEntrega = fEntrega;
	}
	public String getfDevolucion() {
		return fDevolucion;
	}
	public void setfDevolucion(String fDevolucion) {
		this.fDevolucion = fDevolucion;
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
	public String getSepFRecojo() {
		return sepFRecojo;
	}
	public void setSepFRecojo(String sepFRecojo) {
		this.sepFRecojo = sepFRecojo;
	}
	public String getSepFACuenta() {
		return sepFACuenta;
	}
	public void setSepFACuenta(String sepFACuenta) {
		this.sepFACuenta = sepFACuenta;
	}
	public String getSepSaldo() {
		return sepSaldo;
	}
	public void setSepSaldo(String sepSaldo) {
		this.sepSaldo = sepSaldo;
	}
	public int getDiasRecojo() {
		return diasRecojo;
	}
	public void setDiasRecojo(int diasRecojo) {
		this.diasRecojo = diasRecojo;
	}
	
	
	//modificacioes en la boleta 
	
	//GarNroDni, GarNroDniMenor, GarNroLicencia, GarMonto, GarOtroEspecifique, GarSerieBoleta, GarNumeroBoleta
	private String garNroDni=null;
	private String garNroDniMenor=null;
	private String garNroLicencia=null;
	private String garMonto=null;
	private String garOtroEspecifique=null;
	private String garSerieBoleta=null;
	private String garNumeroBoleta=null;

	public String getGarNroDni() {
		return garNroDni;
	}

	public void setGarNroDni(String garNroDni) {
		this.garNroDni = garNroDni;
	}

	public String getGarNroDniMenor() {
		return garNroDniMenor;
	}

	public void setGarNroDniMenor(String garNroDniMenor) {
		this.garNroDniMenor = garNroDniMenor;
	}

	public String getGarNroLicencia() {
		return garNroLicencia;
	}

	public void setGarNroLicencia(String garNroLicencia) {
		this.garNroLicencia = garNroLicencia;
	}

	public String getGarMonto() {
		return garMonto;
	}

	public void setGarMonto(String garMonto) {
		this.garMonto = garMonto;
	}

	public String getGarOtroEspecifique() {
		return garOtroEspecifique;
	}

	public void setGarOtroEspecifique(String garOtroEspecifique) {
		this.garOtroEspecifique = garOtroEspecifique;
	}

	public String getGarSerieBoleta() {
		return garSerieBoleta;
	}

	public void setGarSerieBoleta(String garSerieBoleta) {
		this.garSerieBoleta = garSerieBoleta;
	}

	public String getGarNumeroBoleta() {
		return garNumeroBoleta;
	}

	public void setGarNumeroBoleta(String garNumeroBoleta) {
		this.garNumeroBoleta = garNumeroBoleta;
	}
	
	
	
	
}
