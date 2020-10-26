package dal;

public class TDetalleBoletaDevolucion extends DALPadre{
	private int id=-1;
	private int idBoleta=-1;
	private int tipoVenta=-1;
	private int tipoPro=-1;
	
	private int idPieza=-1;
	private int idArticulo=-1;
	
	private int cant=-1;
	private String codigo=null;
	private String  descripcion=null;
	
	private String estado=null;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdBoleta() {
		return idBoleta;
	}

	public void setIdBoleta(int idBoleta) {
		this.idBoleta = idBoleta;
	}

	public int getTipoVenta() {
		return tipoVenta;
	}

	public void setTipoVenta(int tipoVenta) {
		this.tipoVenta = tipoVenta;
	}

	public int getTipoPro() {
		return tipoPro;
	}

	public void setTipoPro(int tipoPro) {
		this.tipoPro = tipoPro;
	}

	public int getIdPieza() {
		return idPieza;
	}

	public void setIdPieza(int idPieza) {
		this.idPieza = idPieza;
	}

	public int getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
	}

	public int getCant() {
		return cant;
	}

	public void setCant(int cant) {
		this.cant = cant;
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	
	
	
	
}
