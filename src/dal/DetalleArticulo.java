package dal;

import java.util.Date;

public class DetalleArticulo extends DALPadre {
	
	private int nro=-1;
	private int idBoleta=-1;
	private String codArtiPieza=null;
	private int cant=-1;
	private String descripcion=null;
	private String precioUnit=null;
	private String importe=null;
	private String importeDesc=null;
	private String codCliente=null;
	private String cliente=null;
	private String boleta=null;
	private String fecha=null;
	
	
	public DetalleArticulo(){
		
	}
	
	
	public int getNro() {
		return nro;
	}
	public void setNro(int nro) {
		this.nro = nro;
	}
	public int getIdBoleta() {
		return idBoleta;
	}
	public void setIdBoleta(int idBoleta) {
		this.idBoleta = idBoleta;
	}
	public String getCodArtiPieza() {
		return codArtiPieza;
	}
	public void setCodArtiPieza(String codArtiPieza) {
		this.codArtiPieza = codArtiPieza;
	}
	public int getCant() {
		return cant;
	}
	public void setCant(int cant) {
		this.cant = cant;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getPrecioUnit() {
		return precioUnit;
	}
	public void setPrecioUnit(String precioUnit) {
		this.precioUnit = precioUnit;
	}
	public String getImporte() {
		return importe;
	}
	public void setImporte(String importe) {
		this.importe = importe;
	}
	public String getImporteDesc() {
		return importeDesc;
	}
	public void setImporteDesc(String importeDesc) {
		this.importeDesc = importeDesc;
	}
	public String getCodCliente() {
		return codCliente;
	}
	public void setCodCliente(String codCliente) {
		this.codCliente = codCliente;
	}
	public String getCliente() {
		return cliente;
	}
	public void setCliente(String cliente) {
		this.cliente = cliente;
	}
	public String getBoleta() {
		return boleta;
	}
	public void setBoleta(String boleta) {
		this.boleta = boleta;
	}
	

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	
}
