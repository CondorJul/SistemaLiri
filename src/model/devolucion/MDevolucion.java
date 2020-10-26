package model.devolucion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import dal.TDevolucion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.MPadre;

public class MDevolucion extends MPadre{
	public static final String HOY=" and  DATE(Fecha)=curdate())";
	public static final String SEMANA=" and DATE(Fecha) between subdate(curdate(), interval 7 day) and curdate())";
	public static final String MES=" and DATE(Fecha) between subdate(curdate(), interval 31 day) and curdate())";
	public static final String INICIO_CREACION=")";
	
	public static final String HOY_D=" and  DATE(DevFechaGarantiaCompletada)=curdate())";
	public static final String SEMANA_D=" and DATE(DevFechaGarantiaCompletada) between subdate(curdate(), interval 7 day) and curdate())";
	public static final String MES_D=" and DATE(DevFechaGarantiaCompletada) between subdate(curdate(), interval 31 day) and curdate())";
	public static final String INICIO_CREACION_D=")";
	
	
	public ObservableList<TDevolucion> seleccionarTDevolucion(String buscar, String tiempo){
		tiempo=(tiempo==null)?"":tiempo;
		buscar=(buscar!=null && !buscar.trim().isEmpty())?"%"+buscar+"%":"%";

			
			ObservableList<TDevolucion> array=FXCollections.observableArrayList();;
			
			TDevolucion dal=null;
			
			String url = "select boleta.Id as Id, IdCliente, Fecha, Serie, LPAD(Numero, 8,'0') as Numero, Dni     Tipo,  concat(Codigo, '-', Dni) as codDni,"
					+ "  NombresApellidos, Dni, Subtotal , DesCupones, DesAdicional, TotalPagar, "
					+ " (SELECT Descripcion FROM detalleboleta WHERE IdBoleta=boleta.Id LIMIT 1) as primerArticuloPieza,  "
					+ " DevFechaEntrega, DevFechaDevolucion,"
					+ "    datediff(DevFechaDevolucion,CURDATE() ) as tiemporestante, "
					+ "   cliente.Codigo as codigo, DevCompletada, DevGarantiaCompletada "
					+ "	 from cliente inner join boleta on cliente.Id=boleta.IdCliente "
					+ " where Tipo=1 and EstadoAccion=3 and EstadoEliminado<>1 and  ((DevCompletada IS NULL OR DevCompletada =0) OR (DevGarantiaCompletada IS NULL OR DevGarantiaCompletada=0)) and ( (concat(Serie,'-',LPAD(Numero, 8,'0')) like ? or NombresApellidos like ? or Dni like ?  or Codigo like ? or (SELECT Descripcion FROM detalleboleta WHERE IdBoleta=boleta.Id LIMIT 1) like ?)  "+tiempo;
			
			PreparedStatement pst=null;
			ResultSet rs=null;
			int contador=1;
			
			try {
				pst=conexionServidor.prepareStatement(url);
				pst.setString(1, buscar);
				pst.setString(2, buscar);
				pst.setString(3, buscar);
				pst.setString(4, buscar);
				pst.setString(5, buscar);
						
				rs=pst.executeQuery();
			
				while(rs.next()){
					
					dal=new TDevolucion();
					
					dal.setId(rs.getInt("Id"));
					dal.setIdCliente(rs.getInt("IdCliente"));
					dal.setNroEnum(String.valueOf(contador++));
					dal.setFecha(rs.getString("Fecha"));
					dal.setSerieBoleta(rs.getString("Serie"));
					dal.setNumeroBoleta(rs.getString("Numero"));
					dal.setPrimerArticuloPieza(rs.getString("primerArticuloPieza"));
					//dal.setTipo(rs.getString("Tipo"));
					dal.setDniCliente(rs.getString("Dni"));
					dal.setCodigoCliente(rs.getString("Codigo"));
					dal.setApellNom(rs.getString("NombresApellidos"));
					dal.setSubTotal(rs.getString("Subtotal"));
					dal.setDesCupones(rs.getString("DesCupones"));
					dal.setDesAdic(rs.getString("DesAdicional"));
					dal.setTotal(rs.getString("TotalPagar"));
					dal.setfEntrega(rs.getString("DevFechaEntrega"));
					dal.setfDevolucion(rs.getString("DevFechaDevolucion"));
					dal.setTiempoRetante(rs.getString("tiemporestante"));
					
					
					//modificaciones
					dal.setDevCompletada(rs.getInt("DevCompletada"));
					dal.setDevGarantiaCompletada(rs.getInt("DevGarantiaCompletada"));
					
					array.add(dal);
					
				}
				

			}catch (Exception e) {

				e.printStackTrace();
			}finally{
				try{
					if(pst!=null){
						pst.close();
					}
					if (rs!=null) {
						rs.close();
					}
	       			
	   			}catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return array;
		}
	
public int contarPiezasPendientesPorBoleta(int idoleta){
		
		int objeto=-1;
		String url = "SELECT SUM(PendienteCant) as PendienteCant FROM boleta INNER JOIN detalleboleta ON boleta.Id=detalleboleta.IdBoleta INNER JOIN piezasdetalleboleta ON detalleboleta.Id=piezasdetalleboleta.IdDetalleboleta  where Boleta.Id=?";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			pst=conexionServidor.prepareStatement(url);
		
			pst.setInt(1,  idoleta);
			
			rs=pst.executeQuery();
			if(rs.next()){
				objeto=rs.getInt("PendienteCant");
				
			}
			
		}catch (Exception e) {

			e.printStackTrace();
		}finally{
			try{
				if(pst!=null){
					pst.close();
				}
				if (rs!=null) {
					rs.close();
				}
       			
   			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return objeto;
	}




public boolean  actualizar_cliente_reputacion(){
	boolean estado=false; 

	String url = "call actualizar_cliente_reputacion();";
	
	PreparedStatement pst=null;
	ResultSet rs=null;
	
	try {
		
		pst=conexionServidor.prepareStatement(url);
		pst.execute();
		
		this.setNoError(CORRECTO);
		this.setMensaje(MEN_UPDATE_CORRECTO);
		estado=true;
	}catch (Exception e) {
		this.setNoError(INCORRECTO);
 	    this.setMensaje(MEN_UPDATE_INCORRECTO+e.getMessage());
 	    estado=false; 
 	   Alert error_alert = new Alert(Alert.AlertType.ERROR);
       error_alert.setTitle("Error.");
       error_alert.setHeaderText("");
       error_alert.setContentText(e.getMessage());
       error_alert.show();
		e.printStackTrace();
	}finally{
		try{
			if(pst!=null){
				pst.close();
			}
			if (rs!=null) {
				rs.close();
			}
   			
			}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	return estado;
}


public ObservableList<TDevolucion> seleccionarDevueltos(String buscar, String tiempo){
	tiempo=(tiempo==null)?"":tiempo;
	buscar=(buscar!=null && !buscar.trim().isEmpty())?"%"+buscar+"%":"%";

		
		ObservableList<TDevolucion> array=FXCollections.observableArrayList();;
		
		TDevolucion dal=null;
		
		String url = "select boleta.Id as Id, IdCliente, Fecha, Serie, LPAD(Numero, 8,'0') as Numero, Dni     Tipo,  concat(Codigo, '-', Dni) as codDni,"
				+ "  NombresApellidos, Dni, Subtotal , DesCupones, DesAdicional, TotalPagar, "
				+ " (SELECT Descripcion FROM detalleboleta WHERE IdBoleta=boleta.Id LIMIT 1) as primerArticuloPieza,   "
				+ "DevFechaEntrega, DevFechaDevolucion, "
				+ "    datediff(DevFechaDevolucion,DevFechaEntrega ) as tiemporestante, "
				+ "   cliente.Codigo as codigo, DevCompletada, DevGarantiaCompletada,"
				+ " DevFechaCompletada "
				+ "	 from cliente inner join boleta on cliente.Id=boleta.IdCliente "
				+ " where Tipo=1 and EstadoAccion=3 and EstadoEliminado<>1 and  DevCompletada=1 AND DevGarantiaCompletada=1 and ( (concat(Serie,'-',LPAD(Numero, 8,'0')) like ? or NombresApellidos like ? or Dni like ?  or Codigo like ? or (SELECT Descripcion FROM detalleboleta WHERE IdBoleta=boleta.Id LIMIT 1) like ?)  "+tiempo;
		
		PreparedStatement pst=null;
		ResultSet rs=null;
		int contador=1;
		
		try {
			pst=conexionServidor.prepareStatement(url);
			pst.setString(1, buscar);
			pst.setString(2, buscar);
			pst.setString(3, buscar);
			pst.setString(4, buscar);
			pst.setString(5, buscar);
					
			rs=pst.executeQuery();
		
			while(rs.next()){
				
				dal=new TDevolucion();
				
				dal.setId(rs.getInt("Id"));
				dal.setIdCliente(rs.getInt("IdCliente"));
				dal.setNroEnum(String.valueOf(contador++));
				dal.setFecha(rs.getString("Fecha"));
				dal.setSerieBoleta(rs.getString("Serie"));
				dal.setNumeroBoleta(rs.getString("Numero"));
				//dal.setTipo(rs.getString("Tipo"));
				dal.setPrimerArticuloPieza(rs.getString("primerArticuloPieza"));

				dal.setDniCliente(rs.getString("Dni"));
				dal.setCodigoCliente(rs.getString("Codigo"));
				dal.setApellNom(rs.getString("NombresApellidos"));
				dal.setSubTotal(rs.getString("Subtotal"));
				dal.setDesCupones(rs.getString("DesCupones"));
				dal.setDesAdic(rs.getString("DesAdicional"));
				dal.setTotal(rs.getString("TotalPagar"));
				dal.setfEntrega(rs.getString("DevFechaEntrega"));
				dal.setfDevolucion(rs.getString("DevFechaDevolucion"));
				dal.setTiempoRetante(rs.getString("tiemporestante"));
				
				
				//modificaciones
				dal.setDevCompletada(rs.getInt("DevCompletada"));
				dal.setDevGarantiaCompletada(rs.getInt("DevGarantiaCompletada"));
				dal.setFechaDevuelto(rs.getString("DevFechaCompletada"));
				
				array.add(dal);
				
			}
			

		}catch (Exception e) {

			e.printStackTrace();
		}finally{
			try{
					if(pst!=null){
						pst.close();
					}
				if (rs!=null) {
					rs.close();
				}
       			
   			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return array;
	}





public int  nuevaBoletaPorDanio(int idBoleta, String dniUserActual){
	int idNuevaBoletaPorDanio=-1; 

	String url = "select nueva_boleta_por_danio(?, ?) as LastId;";
	
	PreparedStatement pst=null;
	ResultSet rs=null;
	
	try {
		
		pst=conexionServidor.prepareStatement(url);
		pst.setInt(1, idBoleta);
		pst.setString(2, dniUserActual);
		rs=pst.executeQuery();
		if(rs.next()){
			idNuevaBoletaPorDanio=rs.getInt("LastId");
		}
		
		this.setNoError(CORRECTO);
		this.setMensaje(MEN_INSERT_CORRECTO);
		
	}catch (Exception e) {
		this.setNoError(INCORRECTO);
 	    this.setMensaje(MEN_INSERT_INCORRECTO+e.getMessage());
 	    idNuevaBoletaPorDanio=-1; 
 	   Alert error_alert = new Alert(Alert.AlertType.ERROR);
       error_alert.setTitle("Error.");
       error_alert.setHeaderText("");
       error_alert.setContentText(e.getMessage());
       error_alert.show();
		e.printStackTrace();
	}finally{
		try{
			if(pst!=null){
				pst.close();
			}
			if (rs!=null) {
				rs.close();
			}
   			
			}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	return idNuevaBoletaPorDanio;
}

public int  verificarAptoCrearNuevaBoleta(int idBoleta){
	int idNuevaBoletaPorDanio=0; 

	String url = "select verificarAptoCrearNuevaBoleta(?) as estado;";
	
	PreparedStatement pst=null;
	ResultSet rs=null;
	
	try {
		
		pst=conexionServidor.prepareStatement(url);
		pst.setInt(1, idBoleta);
		rs=pst.executeQuery();
		if(rs.next()){
			idNuevaBoletaPorDanio=rs.getInt("estado");
		}
		
		this.setNoError(CORRECTO);
		this.setMensaje(MEN_INSERT_CORRECTO);
		
	}catch (Exception e) {
		this.setNoError(INCORRECTO);
 	    this.setMensaje(MEN_INSERT_INCORRECTO+e.getMessage());
 	    idNuevaBoletaPorDanio=0; 
 	   Alert error_alert = new Alert(Alert.AlertType.ERROR);
       error_alert.setTitle("Error.");
       error_alert.setHeaderText("");
       error_alert.setContentText(e.getMessage());
       error_alert.show();
		e.printStackTrace();
	}finally{
		try{
			if(pst!=null){
				pst.close();
			}
			if (rs!=null) {
				rs.close();
			}
   			
			}catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	return idNuevaBoletaPorDanio;
}
}
