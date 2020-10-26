package dal;

public class Pieza extends DALPadre {
	private int id=-1;
	private String codigo=null;
	private String descripcion=null;
	private int tipoMantenimiento=-1;
	private double precioCompra=-1.0;
	private double precioAlquiler=-1.0;
	private double precioVenta=-1.0;
	
	private int requierePlanchar=-1;
	private String comentarios=null;
	private int estado=-1;
	
	
	private int stock=-1;
	private int idArticulo=-1;
	
	
	private int lavanderiaCant; 
	private int reparacionCant;
	private int planchadoCant;
	private int esperaCant;

private int cantTransacExt=-1;//variable para recuperar cantidad de transacciones externas
	
	public int getCantTransacExt() {
		return cantTransacExt;
	}
	public void setCantTransacExt(int cantTransacExt) {
		this.cantTransacExt = cantTransacExt;
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
	public int getTipoMantenimiento() {
		return tipoMantenimiento;
	}
	public void setTipoMantenimiento(int tipoMantenimiento) {
		this.tipoMantenimiento = tipoMantenimiento;
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
	public int getRequierePlanchar() {
		return requierePlanchar;
	}
	public void setRequierePlanchar(int requierePlanchar) {
		this.requierePlanchar = requierePlanchar;
	}
	public String getComentarios() {
		return comentarios;
	}
	public void setComentarios(String comentarios) {
		this.comentarios = comentarios;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getIdArticulo() {
		return idArticulo;
	}
	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
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
		Pieza other = (Pieza) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
