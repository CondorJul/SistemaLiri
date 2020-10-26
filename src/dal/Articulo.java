package dal;

import javafx.scene.image.Image;

public class Articulo extends DALPadre{
	private int id=-1;
	private String codigo=null;
	private String descripcion=null;
	private String talla=null;
	private int stock=-1;
	private int idFamilia=-1;
	private double rentabilidad=-1.0;
	private String resena=null;
	private double precioCompra=-1.0;
	private double precioAlquiler=-1.0;
	private double precioVenta=-1.0;
	private String genero=null;
	private String ubicacion=null;
	private int nroPiezas=-1;
	private int idProveedor=-1;
	
	private String datoAdic1=null;
	private String datoAdic2=null;
	private String datoAdic3=null;
	
	private String comentarios=null; 


	//campo de estadistica
	private int cantAlquilado = 1;

	private int lavanderiaCant; 
	private int reparacionCant;
	private int planchadoCant;
	private int esperaCant;
	
	public int getCantAlquilado() {
		return cantAlquilado;
	}
	public void setCantAlquilado(int cantAlquilado) {
		this.cantAlquilado = cantAlquilado;
	}
	
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	private Image imagen=null;
	
	
	public String getDatoAdic1() {
		return datoAdic1;
	}
	public void setDatoAdic1(String datoAdic1) {
		this.datoAdic1 = datoAdic1;
	}
	public String getDatoAdic2() {
		return datoAdic2;
	}
	public void setDatoAdic2(String datoAdic2) {
		this.datoAdic2 = datoAdic2;
	}
	public String getDatoAdic3() {
		return datoAdic3;
	}
	public void setDatoAdic3(String datoAdic3) {
		this.datoAdic3 = datoAdic3;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTalla() {
		return talla;
	}
	public void setTalla(String talla) {
		this.talla = talla;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getIdFamilia() {
		return idFamilia;
	}
	public void setIdFamilia(int idFamilia) {
		this.idFamilia = idFamilia;
	}
	public double getRentabilidad() {
		return rentabilidad;
	}
	public void setRentabilidad(double rentabilidad) {
		this.rentabilidad = rentabilidad;
	}
	
	public String getResena() {
		return resena;
	}
	public void setResena(String resena) {
		this.resena = resena;
	}
	public double getPrecioCompra() {
		return precioCompra;
	}
	public void setPrecioCompra(double precioCompra) {
		this.precioCompra = precioCompra;
	}
	public double getPrecioAlquiler() {
		return precioAlquiler;
	}
	public void setPrecioAlquiler(double precioAlquiler) {
		this.precioAlquiler = precioAlquiler;
	}
	public double getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(double precioVenta) {
		this.precioVenta = precioVenta;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public int getNroPiezas() {
		return nroPiezas;
	}
	public void setNroPiezas(int nroPiezas) {
		this.nroPiezas = nroPiezas;
	}
	public Image getImagen() {
		return imagen;
	}
	public void setImagen(Image imagen) {
		this.imagen = imagen;
	}
	
	
	public int getIdProveedor() {
		return idProveedor;
	}
	public void setIdProveedor(int idProveedor) {
		this.idProveedor = idProveedor;
	}
	
	
	
	public int getLavanderiaCant() {
		return lavanderiaCant;
	}
	public void setLavanderiaCant(int lavanderiaCant) {
		this.lavanderiaCant = lavanderiaCant;
	}
	public int getReparacionCant() {
		return reparacionCant;
	}
	public void setReparacionCant(int reparacionCant) {
		this.reparacionCant = reparacionCant;
	}
	public int getPlanchadoCant() {
		return planchadoCant;
	}
	public void setPlanchadoCant(int planchadoCant) {
		this.planchadoCant = planchadoCant;
	}
	public int getEsperaCant() {
		return esperaCant;
	}
	public void setEsperaCant(int esperaCant) {
		this.esperaCant = esperaCant;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Articulo other = (Articulo) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
