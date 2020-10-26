package dal;

import java.util.Date;

import controller.reputacion.CProgress;

public class Cliente extends DALPadre{
	private int cli_id;
	private String cli_codigo;
	private String cli_tipoCliente;
	private String cli_dni;
	private String cli_ruc;
	private String cli_apellNom;
	private Date cli_fechNac;
	private String cli_telefono;
	private String cli_direccion;
	private String cli_referencia;
	private String cli_email;
	private String cli_comentarios;
	private int cli_reputacion;
	
	//campos calculados
	private int cli_cantRecurrencia;
	
	public int getCli_cantRecurrencia() {
		return cli_cantRecurrencia;
	}
	public void setCli_cantRecurrencia(int cli_cantRecurrencia) {
		this.cli_cantRecurrencia = cli_cantRecurrencia;
	}
	private CProgress nivel = new CProgress();
	
	public CProgress getNivel() {
		return nivel;
	}
	public void setNivel(CProgress nivel) {
		this.nivel = nivel;
	}
	public int getCli_id() {
		return cli_id;
	}
	public void setCli_id(int cli_id) {
		this.cli_id = cli_id;
	}
	public String getCli_codigo() {
		return cli_codigo;
	}
	public void setCli_codigo(String cli_codigo) {
		this.cli_codigo = cli_codigo;
	}	
	public String getCli_tipoCliente() {
		return cli_tipoCliente;
	}
	public void setCli_tipoCliente(String cli_tipoCliente) {
		this.cli_tipoCliente = cli_tipoCliente;
	}
	public String getCli_ruc() {
		return cli_ruc;
	}
	public void setCli_ruc(String cli_ruc) {
		this.cli_ruc = cli_ruc;
	}
	public String getCli_dni() {
		return cli_dni;
	}
	public void setCli_dni(String cli_dni) {
		this.cli_dni = cli_dni;
	}
	public String getCli_apellNom() {
		return cli_apellNom;
	}
	public void setCli_apellNom(String cli_apellNom) {
		this.cli_apellNom = cli_apellNom;
	}
	public Date getCli_fechNac() {
		return cli_fechNac;
	}
	public void setCli_fechNac(Date cli_fechNac) {
		this.cli_fechNac = cli_fechNac;
	}
	public String getCli_telefono() {
		return cli_telefono;
	}
	public void setCli_telefono(String cli_telefono) {
		this.cli_telefono = cli_telefono;
	}
	public String getCli_direccion() {
		return cli_direccion;
	}
	public void setCli_direccion(String cli_direccion) {
		this.cli_direccion = cli_direccion;
	}
	public String getCli_referencia() {
		return cli_referencia;
	}
	public void setCli_referencia(String cli_referencia) {
		this.cli_referencia = cli_referencia;
	}
	public String getCli_email() {
		return cli_email;
	}
	public void setCli_email(String cli_email) {
		this.cli_email = cli_email;
	}
	public String getCli_comentarios() {
		return cli_comentarios;
	}
	public void setCli_comentarios(String cli_comentarios) {
		this.cli_comentarios = cli_comentarios;
	}
	public int getCli_reputacion() {
		return cli_reputacion;
	}
	public void setCli_reputacion(int cli_reputacion) {
		nivel.modelarProgress(cli_reputacion);
		this.cli_reputacion = cli_reputacion;	
	}
		
	public Cliente(int cli_id, String cli_codigo, String cli_tipoCliente, String cli_ruc, String cli_dni, String cli_apellNom, Date cli_fechNac,
			String cli_telefono, String cli_direccion, String cli_referencia, String cli_email, String cli_comentarios,
			int cli_reputacion, String cli_creadoPor, String cli_modificadoPor,
			Date cli_fCreacion, Date cli_fModificacion) {
		super(cli_creadoPor, cli_modificadoPor, cli_fCreacion, cli_fModificacion);
		this.cli_id = cli_id;
		this.cli_codigo = cli_codigo;
		this.cli_tipoCliente = cli_tipoCliente;
		this.cli_ruc = cli_ruc;
		this.cli_dni = cli_dni;
		this.cli_apellNom = cli_apellNom;
		this.cli_fechNac = cli_fechNac;
		this.cli_telefono = cli_telefono;
		this.cli_direccion = cli_direccion;
		this.cli_referencia = cli_referencia;
		this.cli_email = cli_email;
		this.cli_comentarios = cli_comentarios;
		this.cli_reputacion = cli_reputacion;

	}
	
	public Cliente(){
		
	}
	 
	public Cliente(int cli_id, String cli_codigo){
		this.cli_id = cli_id;
		this.cli_codigo = cli_codigo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cli_codigo == null) ? 0 : cli_codigo.hashCode());
		result = prime * result + ((cli_dni == null) ? 0 : cli_dni.hashCode());
		result = prime * result + cli_id;
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
		Cliente other = (Cliente) obj;
		if (cli_codigo == null) {
			if (other.cli_codigo != null)
				return false;
		} else if (!cli_codigo.equals(other.cli_codigo))
			return false;
		if (cli_dni == null) {
			if (other.cli_dni != null)
				return false;
		} else if (!cli_dni.equals(other.cli_dni))
			return false;
		if (cli_id != other.cli_id)
			return false;
		return true;
	}
	
	
	
}
