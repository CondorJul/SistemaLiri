package dal;

import java.util.Date;



public class Boleta extends DALPadre{
	
	public static final int EST_PENDIENTE=1;
	public static final int EST_RESERVADO=2;
	public static final int EST_FACTURADO=3;
	
	public static final int TIPO_VENTA_ALQUILER=1;
	public static final int TIPO_VENTA_VENTA=2;
	public static final int TIPO_VENTA_DUPLICADO_BOLEETA=3;
	
	//estado eliminados 
	public static final int ESTADO_ELIMINADO_TRUE=1;
	public static final int ESTADO_ELIMINADO_FALSE=0;
	
	
	private int tipo=-1;
	
	private int id=-1;
	private String serie=null;
	private int numero=-1;
	private Date fecha;
	
	
	
	private int idCliente=-1;
	
	private boolean cupon1=false;
	private boolean cupon2=false;
	private boolean cupon3=false;
	private double valorCuponPor=-1.0;
	
	private int desAdicionalAc=0;
	private double desAdiSoles=-1.0;
	private double desAdiPor=-1.0;
	
	/*Precios*/
	private String subTotal =null;
	private String desCupones=null;
	private String desAdicional=null;
	private String totalPagar=null;
	
	
	/*Garantia*/
	private boolean garDni=false;
	private String garNroDni=null;
	private boolean garDniMenor=false;
	private String garNroDniMenor=null;
	private boolean garLicencia=false;
	private String garNroLicencia=null;
	private boolean garDinero=false;
	private double garMonto=-1.0;
	private boolean garOtro=false;
	private String garOtroEspecifique=null;
	private boolean garEnlazarBoleta=false;
	private String garSerieBoleta=null;
	private int garNumeroBoleta=-1;
	private int garIdBoleta=-1;
	
	/*Recomendacion*/
	private int recIdCliente=-1;
	
	
	/*Separacion*/
	private boolean sepSeparar=false;
	public boolean isSepSeparar() {
		return sepSeparar;
	}
	public void setSepSeparar(boolean sepSeparar) {
		this.sepSeparar = sepSeparar;
	}
	private Date sepFechaRecojo=null;
	private double sepACuenta=-1.0;
	private double sepSaldo=-1.0;
	
	/*Fecha Engrega*/
	private Date devFechaEntrega=null;
	private Date devFechaDevolucion=null;
	
	
	/*1=pendiente , 2 = reservado, 3=factuado*/
	private int estadoAccion=1;
	
	public int getTipo() {
		return tipo;
	}
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getIdCliente() {
		return idCliente;
	}
	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}
	public boolean isCupon1() {
		return cupon1;
	}
	public void setCupon1(boolean cupon1) {
		this.cupon1 = cupon1;
	}
	public boolean isCupon2() {
		return cupon2;
	}
	public void setCupon2(boolean cupon2) {
		this.cupon2 = cupon2;
	}
	public boolean isCupon3() {
		return cupon3;
	}
	public void setCupon3(boolean cupon3) {
		this.cupon3 = cupon3;
	}
	public double getValorCuponPor() {
		return valorCuponPor;
	}
	public void setValorCuponPor(double valorCuponPor) {
		this.valorCuponPor = valorCuponPor;
	}
	public String getDesAdicional() {
		return desAdicional;
	}
	public void setDesAdicional(String desAdicional) {
		this.desAdicional = desAdicional;
	}
	public double getDesAdiSoles() {
		return desAdiSoles;
	}
	public void setDesAdiSoles(double desAdiSoles) {
		this.desAdiSoles = desAdiSoles;
	}
	public double getDesAdiPor() {
		return desAdiPor;
	}
	public void setDesAdiPor(double desAdiPor) {
		this.desAdiPor = desAdiPor;
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
	public String getTotalPagar() {
		return totalPagar;
	}
	public void setTotalPagar(String totalPagar) {
		this.totalPagar = totalPagar;
	}
	public boolean isGarDni() {
		return garDni;
	}
	public void setGarDni(boolean garDni) {
		this.garDni = garDni;
	}
	public String getGarNroDni() {
		return garNroDni;
	}
	public void setGarNroDni(String garNroDni) {
		this.garNroDni = garNroDni;
	}
	public boolean isGarDniMenor() {
		return garDniMenor;
	}
	public void setGarDniMenor(boolean garDniMenor) {
		this.garDniMenor = garDniMenor;
	}
	public String getGarNroDniMenor() {
		return garNroDniMenor;
	}
	public void setGarNroDniMenor(String garNroDniMenor) {
		this.garNroDniMenor = garNroDniMenor;
	}
	public boolean isGarLicencia() {
		return garLicencia;
	}
	public void setGarLicencia(boolean garLicencia) {
		this.garLicencia = garLicencia;
	}
	public String getGarNroLicencia() {
		return garNroLicencia;
	}
	public void setGarNroLicencia(String garNroLicencia) {
		this.garNroLicencia = garNroLicencia;
	}
	public boolean isGarDinero() {
		return garDinero;
	}
	public void setGarDinero(boolean garDinero) {
		this.garDinero = garDinero;
	}
	public double getGarMonto() {
		return garMonto;
	}
	public void setGarMonto(double garMonto) {
		this.garMonto = garMonto;
	}
	public boolean isGarOtro() {
		return garOtro;
	}
	public void setGarOtro(boolean garOtro) {
		this.garOtro = garOtro;
	}
	public String getGarOtroEspecifique() {
		return garOtroEspecifique;
	}
	public void setGarOtroEspecifique(String garOtroEspecifique) {
		this.garOtroEspecifique = garOtroEspecifique;
	}
	public boolean isGarEnlazarBoleta() {
		return garEnlazarBoleta;
	}
	public void setGarEnlazarBoleta(boolean garEnlazarBoleta) {
		this.garEnlazarBoleta = garEnlazarBoleta;
	}
	public int isGarIdBoleta() {
		return garIdBoleta;
	}
	public void setGarIdBoleta(int garIdBoleta) {
		this.garIdBoleta = garIdBoleta;
	}
	public int getRecIdCliente() {
		return recIdCliente;
	}
	public void setRecIdCliente(int recIdCliente) {
		this.recIdCliente = recIdCliente;
	}
	public Date getSepFechaRecojo() {
		return sepFechaRecojo;
	}
	public void setSepFechaRecojo(Date sepFechaRecojo) {
		this.sepFechaRecojo = sepFechaRecojo;
	}
	public double getSepACuenta() {
		return sepACuenta;
	}
	public void setSepACuenta(double sepACuenta) {
		this.sepACuenta = sepACuenta;
	}
	public double getSepSaldo() {
		return sepSaldo;
	}
	public void setSepSaldo(double sepSaldo) {
		this.sepSaldo = sepSaldo;
	}
	public Date getDevFechaEntrega() {
		return devFechaEntrega;
	}
	public void setDevFechaEntrega(Date devFechaEntrega) {
		this.devFechaEntrega = devFechaEntrega;
	}
	public Date getDevFechaDevolucion() {
		return devFechaDevolucion;
	}
	public void setDevFechaDevolucion(Date devFechaDevolucion) {
		this.devFechaDevolucion = devFechaDevolucion;
	}
	public int getDesAdicionalAc() {
		return desAdicionalAc;
	}
	public void setDesAdicionalAc(int desAdicionalAc) {
		this.desAdicionalAc = desAdicionalAc;
	}
	public int getEstadoAccion() {
		return estadoAccion;
	}
	public void setEstadoAccion(int estadoAccion) {
		this.estadoAccion = estadoAccion;
	}
	public String getGarSerieBoleta() {
		return garSerieBoleta;
	}
	public void setGarSerieBoleta(String garSerieBoleta) {
		this.garSerieBoleta = garSerieBoleta;
	}
	public int getGarNumeroBoleta() {
		return garNumeroBoleta;
	}
	public void setGarNumeroBoleta(int garNumeroBoleta) {
		this.garNumeroBoleta = garNumeroBoleta;
	}
	public int getGarIdBoleta() {
		return garIdBoleta;
	}

	
	private int estadoEliminado=-1;
	public int getEstadoEliminado() {
		return estadoEliminado;
	}
	public void setEstadoEliminado(int estadoEliminado) {
		this.estadoEliminado = estadoEliminado;
	}
	
	/*este atributo deberia ir en detalle de esta venta, pero por temas de comodidad se ha incluida en este objeto*/
	
	private int idBoletaDuplicado=-1;
	public int getIdBoletaDuplicado() {
		return idBoletaDuplicado;
	}
	public void setIdBoletaDuplicado(int idBoletaDuplicado) {
		this.idBoletaDuplicado = idBoletaDuplicado;
	}
	
	
	/*Gar Devoluciones */
	
    private String devFechaCompletada=null;
    private String devFechaCompleta =null;
    private int devCompletada=-1; 
    
    private String devFechaGarantiaCompletada=null;
    private int devGarantiaCompletada=-1;

    
    private boolean devDni=false;
    private boolean devDniMenor =false;
    private boolean devLicencia=false;
    private boolean devDinero=false; 
    private boolean devOtro=false;
    private boolean devEnlazarBoleta=false;
    
    private int pagoRecom=0;
    private double porcentajePorPagoRec =-1;;
    private double porcentajePago =-1;
    private int idEgreso =-1;
    
    
    private int devNroBoletaDannio= -1; /*Nor de boleta que ser genera por daño en en los articulos */
    private int devEstadoBoletaDanio=-1;  /*Ver si la boleta por daño se ha efectuado */
	public String getDevFechaCompletada() {
		return devFechaCompletada;
	}
	public void setDevFechaCompletada(String devFechaCompletada) {
		this.devFechaCompletada = devFechaCompletada;
	}
	public String getDevFechaCompleta() {
		return devFechaCompleta;
	}
	public void setDevFechaCompleta(String devFechaCompleta) {
		this.devFechaCompleta = devFechaCompleta;
	}
	public int getDevCompletada() {
		return devCompletada;
	}
	public void setDevCompletada(int devCompletada) {
		this.devCompletada = devCompletada;
	}
	public String getDevFechaGarantiaCompletada() {
		return devFechaGarantiaCompletada;
	}
	public void setDevFechaGarantiaCompletada(String devFechaGarantiaCompletada) {
		this.devFechaGarantiaCompletada = devFechaGarantiaCompletada;
	}
	public int getDevGarantiaCompletada() {
		return devGarantiaCompletada;
	}
	public void setDevGarantiaCompletada(int devGarantiaCompletada) {
		this.devGarantiaCompletada = devGarantiaCompletada;
	}
	public boolean isDevDni() {
		return devDni;
	}
	public void setDevDni(boolean devDni) {
		this.devDni = devDni;
	}
	public boolean isDevDniMenor() {
		return devDniMenor;
	}
	public void setDevDniMenor(boolean devDniMenor) {
		this.devDniMenor = devDniMenor;
	}
	public boolean isDevLicencia() {
		return devLicencia;
	}
	public void setDevLicencia(boolean devLicencia) {
		this.devLicencia = devLicencia;
	}
	public boolean isDevDinero() {
		return devDinero;
	}
	public void setDevDinero(boolean devDinero) {
		this.devDinero = devDinero;
	}
	public boolean isDevOtro() {
		return devOtro;
	}
	public void setDevOtro(boolean devOtro) {
		this.devOtro = devOtro;
	}
	public boolean isDevEnlazarBoleta() {
		return devEnlazarBoleta;
	}
	public void setDevEnlazarBoleta(boolean devEnlazarBoleta) {
		this.devEnlazarBoleta = devEnlazarBoleta;
	}
	public int getDevNroBoletaDannio() {
		return devNroBoletaDannio;
	}
	public void setDevNroBoletaDannio(int devNroBoletaDannio) {
		this.devNroBoletaDannio = devNroBoletaDannio;
	}
	public int getDevEstadoBoletaDanio() {
		return devEstadoBoletaDanio;
	}
	public void setDevEstadoBoletaDanio(int devEstadoBoletaDanio) {
		this.devEstadoBoletaDanio = devEstadoBoletaDanio;
	}
	public int getPagoRecom() {
		return pagoRecom;
	}
	public void setPagoRecom(int pagoRecom) {
		this.pagoRecom = pagoRecom;
	}
	public double getPorcentajePorPagoRec() {
		return porcentajePorPagoRec;
	}
	public void setPorcentajePorPagoRec(double porcentajePorPagoRec) {
		this.porcentajePorPagoRec = porcentajePorPagoRec;
	}
	public double getPorcentajePago() {
		return porcentajePago;
	}
	public void setPorcentajePago(double porcentajePago) {
		this.porcentajePago = porcentajePago;
	}
	public int getIdEgreso() {
		return idEgreso;
	}
	public void setIdEgreso(int idEgreso) {
		this.idEgreso = idEgreso;
	}
    
    
    /*Fin de Modificaciones adicionales*/
    
	
	/*inio modificacioens de hoy 03/08/2019*/
	private int idBoletaDanio=-1;
	public int getIdBoletaDanio() {
		return idBoletaDanio;
	}
	public void setIdBoletaDanio(int idBoletaDanio) {
		this.idBoletaDanio = idBoletaDanio;
	}
	
	/*fin de modificaciones de hoy 03/08/2019*/
    
    
	
	

	
	
	
	
	


}
