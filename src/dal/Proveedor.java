package dal;

import java.util.Date;

public class Proveedor  extends DALPadre{
	
	private int id=-1;
	private String codigo=null;
	private String nombre=null;
	private String articulo=null;
	private String direccion=null;
	private String telefono=null;
	private String email=null;
	private String comentario=null;
	private String datoAdic1=null;
	private String datoAdic2=null;
	private String datoAdic3=null;
	
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getArticulo() {
		return articulo;
	}
	public void setArticulo(String articulo) {
		this.articulo = articulo;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
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
	public Proveedor(int id, String codigo, String nombre, String articulo, String direccion, String telefono, String email, String comentario, String datoAdic1, String datoAdic2, String datoAdic3, String creadoPor, String modificadoPor, Date fCreacion, Date fModificacion) {
		super(creadoPor, modificadoPor, fCreacion, fModificacion);
		this.id = id;
		this.codigo = codigo;
		this.nombre = nombre;
		this.articulo = articulo;
		this.direccion = direccion;
		this.telefono = telefono;
		this.email = email;
		this.comentario = comentario;
		this.datoAdic1 = datoAdic1;
		this.datoAdic2 = datoAdic2;
		this.datoAdic3 = datoAdic3;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Proveedor other = (Proveedor) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		if (id != other.id)
			return false;
		return true;
	}
	
	public Proveedor() {
		super();
	}
	
}
