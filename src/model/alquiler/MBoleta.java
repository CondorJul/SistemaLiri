package model.alquiler;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dal.Boleta;
import dal.Cliente;
import javafx.scene.control.Alert;
import model.MPadre;
import model.MPadre.ExcepcionMPadre;
import sesion.Sesion;

public class MBoleta extends MPadre{
private Boleta boleta=null;
	
	public int agregarBoleta(Boleta boleta){
		
		int idUltimo = -1;
		this.boleta=boleta;
		String url = "INSERT INTO boleta (IdCliente, Tipo, CreadoPor) VALUES (?,?,?);";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url,PreparedStatement.RETURN_GENERATED_KEYS);
			
			if(boleta.getIdCliente()!=-1){
				pst.setInt(1, boleta.getIdCliente());
			}else {
				pst.setNull(1, java.sql.Types.NULL);
			}
			if(boleta.getTipo()!=-1){
				pst.setInt(2, boleta.getTipo());
			}else {
				pst.setNull(2, java.sql.Types.NULL);
			}
			pst.setString(3, Sesion.DNI);
			pst.executeUpdate();
			rs=pst.getGeneratedKeys();
			while (rs.next()){
				this.boleta.setId(rs.getInt(1));
				idUltimo=rs.getInt(1);
			}
			this.setNoError(CORRECTO);
			this.setMensaje(MEN_INSERT_CORRECTO);
		}catch (Exception e) {
			this.setNoError(INCORRECTO);
     	    this.setMensaje(MEN_INSERT_INCORRECTO+e.getMessage());
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
		return idUltimo;
	}
	
	
	public int actualizarBoleta(Boleta boleta){
		
		int idUltimo = -1;
		this.boleta=boleta;
		String url = "INSERT INTO boleta (IdCliente, CreadoPor) VALUES (?,?);";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url,PreparedStatement.RETURN_GENERATED_KEYS);
			
			pst.setInt(1, boleta.getIdCliente());
			pst.setString(9, Sesion.DNI);
			pst.executeUpdate();
			rs=pst.getGeneratedKeys();
			while (rs.next()){
				this.boleta.setId(rs.getInt(1));
				idUltimo=rs.getInt(1);
			}
			this.setNoError(CORRECTO);
			this.setMensaje(MEN_INSERT_CORRECTO);
		}catch (Exception e) {
			this.setNoError(INCORRECTO);
     	    this.setMensaje(MEN_INSERT_INCORRECTO+e.getMessage());
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
		return idUltimo;
	}
	
	
public  boolean actualizarBoletaDescuentos(Boleta boleta){
		
		boolean estado=false; 
		this.boleta=boleta;
		String url = "update  boleta set Cupon1=?, Cupon2=?, Cupon3=?,  "
				+ " DesAdicionalAc=?, DesAdiSoles=?, DesAdiPor=?, ModificadoPor=?  where id=?";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url);
			pst.setInt(1, (boleta.isCupon1())?1:0);
			pst.setInt(2, (boleta.isCupon2())?1:0);
			pst.setInt(3, (boleta.isCupon3()?1:0));
			pst.setInt(4, boleta.getDesAdicionalAc());
			
			/*0=ninguno, 1=soles ,2=porcentaje*/
			if(boleta.getDesAdicionalAc()==0){
				pst.setNull(5, java.sql.Types.INTEGER);
				pst.setNull(6, java.sql.Types.INTEGER);
			}else if(boleta.getDesAdicionalAc()==1){
				if(boleta.getDesAdiSoles()>=0){
					pst.setDouble(5,boleta.getDesAdiSoles());
				}else{
					pst.setNull(5, java.sql.Types.INTEGER);
				}
				pst.setNull(6, java.sql.Types.INTEGER);

			}else if(boleta.getDesAdicionalAc()==2){
				pst.setNull(5, java.sql.Types.INTEGER);
				if(boleta.getDesAdiPor()<0){
					pst.setNull(6,java.sql.Types.INTEGER);
				}else {
					pst.setDouble(6, boleta.getDesAdiPor());
				}
			}
			
			pst.setString(7, Sesion.DNI);
			pst.setInt(8, boleta.getId());
			
			pst.executeUpdate();
			
			this.setNoError(CORRECTO);
			this.setMensaje(MEN_INSERT_CORRECTO);
			estado=true;
		}catch (Exception e) {
			this.setNoError(INCORRECTO);
     	    this.setMensaje(MEN_INSERT_INCORRECTO+e.getMessage());
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



public  boolean actualizarBoletaClienteTipo(Boleta boleta){
		
		boolean estado=false; 
		this.boleta=boleta;
		String url = "update  boleta set IdCliente=?, Tipo=?,"
				+ "  ModificadoPor=?  where id=?";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url);
			
			if(boleta.getIdCliente()!=-1){
				pst.setInt(1, boleta.getIdCliente());
			}else{
				pst.setNull(1, java.sql.Types.INTEGER);
			}
			pst.setInt(2, boleta.getTipo());

			pst.setString(3, Sesion.DNI);
			pst.setInt(4, boleta.getId());
			
			pst.executeUpdate();
			
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

public  boolean actualizarBoletaReservado(Boleta boleta){
	
	boolean estado=false; 
	this.boleta=boleta;
	String url = "update  boleta set EstadoAccion=?, "
			+ "  ModificadoPor=?, Fecha=now() where id=?";
	PreparedStatement pst=null;
	ResultSet rs=null;
	
	try {
		
		pst=conexionServidor.prepareStatement(url);
		
		if(boleta.getEstadoAccion()==2){
			pst.setInt(1, boleta.getEstadoAccion());
		}else{
			pst.setNull(1, java.sql.Types.INTEGER);
		}

		pst.setString(2, Sesion.DNI);
		pst.setInt(3, boleta.getId());
		
		pst.executeUpdate();
		
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

public  boolean actualizarBoletaFacturado(Boleta boleta){
	
	boolean estado=false; 
	this.boleta=boleta;
	String url = "update  boleta set fecha=now(), EstadoAccion=?, "
			+ "  ModificadoPor=?  where id=?;";
	PreparedStatement pst=null;
	ResultSet rs=null;
	
	try {
		
		pst=conexionServidor.prepareStatement(url);
		/*Facturado=3*/
		if(boleta.getEstadoAccion()==3){
			pst.setInt(1, boleta.getEstadoAccion());
		}else{
			throw new ExcepcionMPadre("Surgio un error al facturar");
			//pst.setNull(1, java.sql.Types.INTEGER);
		}

		pst.setString(2, Sesion.DNI);
		pst.setInt(3, boleta.getId());
		
		pst.executeUpdate();
		
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


public  boolean actualizarBoletaAnular(Boleta boleta){
	
	boolean estado=false; 
	this.boleta=boleta;
	String url = "update  boleta set EstadoEliminado=?, "
			+ "  ModificadoPor=?  where id=?;";
	PreparedStatement pst=null;
	ResultSet rs=null;
	
	try {
		
		pst=conexionServidor.prepareStatement(url);
		/*Facturado=3*/
		if(boleta.getEstadoAccion()==1){
			pst.setInt(1, boleta.getEstadoEliminado());
		}else{
			throw new ExcepcionMPadre("Surgio un error al anular dicha boleta");
			//pst.setNull(1, java.sql.Types.INTEGER);
		}

		pst.setString(2, Sesion.DNI);
		pst.setInt(3, boleta.getId());
		
		pst.executeUpdate();
		
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

public  boolean actualizarBoletaGarantia(Boleta boleta){
		
		boolean estado=false; 
		this.boleta=boleta;
		String url = "update  boleta set GarDni=?, GarNroDni=?,"
				+ " GarDniMenor=?, GarNroDniMenor=?, "
				+ " GarLicencia=?, GarNroLicencia=?, "
				+ " GarDinero=?, GarMonto=?, "
				+ " GarOtro=?,  GarOtroEspecifique=?,"
				+ " GarEnlazarBoleta=?, GarSerieBoleta=?,  GarNumeroBoleta=?, GarIdBoleta=?, "
				+ "  ModificadoPor=?  where id=?";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url);
			
			if(boleta.isGarDni()){
				pst.setInt(1, 1);
				pst.setString(2, boleta.getGarNroDni());
			}else{
				pst.setInt(1, 0);
				pst.setNull(2, java.sql.Types.VARCHAR);
			}
			
			if(boleta.isGarDniMenor()){
				pst.setInt(3, 1);
				pst.setString(4, boleta.getGarNroDniMenor());
			}else{
				pst.setInt(3, 0);
				pst.setNull(4, java.sql.Types.VARCHAR);
			}		
			
			
			
			
			if(boleta.isGarLicencia()){
				pst.setInt(5, 1);
				pst.setString(6, boleta.getGarNroLicencia());
			}else{
				pst.setInt(5, 0);
				pst.setNull(6, java.sql.Types.VARCHAR);
			}
			
			
			if(boleta.isGarDinero()){
				pst.setInt(7, 1);
				if(boleta.getGarMonto()>=0){
					pst.setDouble(8, boleta.getGarMonto());
				}else{
					pst.setNull(8, java.sql.Types.DECIMAL);
				}
			}else{
				pst.setInt(7, 0);
				pst.setNull(8, java.sql.Types.VARCHAR);
			}
			
			
			if(boleta.isGarOtro()){
				pst.setInt(9, 1);
				if(boleta.getGarOtroEspecifique()!=null && !boleta.getGarOtroEspecifique().isEmpty()){
					pst.setString(10, boleta.getGarOtroEspecifique());
				}else{
					pst.setNull(10, java.sql.Types.VARCHAR);
				}
			}else{
				pst.setInt(9, 0);
				pst.setNull(10, java.sql.Types.VARCHAR);
			}
			
			if(boleta.isGarEnlazarBoleta()){
				pst.setInt(11, 1);
				
				if(boleta.getGarSerieBoleta()!=null && !boleta.getGarSerieBoleta().isEmpty()){
					pst.setString(12, boleta.getGarSerieBoleta());
				}else{
					pst.setNull(12, java.sql.Types.VARCHAR);
				}
				
				if(boleta.getGarNumeroBoleta()!=-1){
					pst.setInt(13, boleta.getGarNumeroBoleta());
				}else{
					pst.setNull(13, java.sql.Types.INTEGER);
				}
				
				if(boleta.getGarIdBoleta()!=-1){
					pst.setInt(14, boleta.getGarIdBoleta());
				}else{
					pst.setNull(14, java.sql.Types.INTEGER);
				}
				
				
			}else{
				pst.setInt(11, 0);
				pst.setNull(12, java.sql.Types.VARCHAR);
				pst.setNull(13, java.sql.Types.INTEGER);
				pst.setNull(14, java.sql.Types.INTEGER);
			}

			pst.setString(15, Sesion.DNI);
			pst.setInt(16, boleta.getId());
			
			pst.executeUpdate();
			
			this.setNoError(CORRECTO);
			this.setMensaje(MEN_INSERT_CORRECTO);
			estado=true;
		}catch (Exception e) {
			this.setNoError(INCORRECTO);
     	    this.setMensaje(MEN_INSERT_INCORRECTO+e.getMessage());
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



public  boolean actualizarBoletaRecomendacion(Boleta boleta){
		
		boolean estado=false; 
		this.boleta=boleta;
		String url = "update  boleta set IdClienteRec=?, "
				+ "  ModificadoPor=?  where id=?";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url);
			
			System.out.println(boleta.getRecIdCliente());
			if(boleta.getRecIdCliente()!=-1){
				pst.setInt(1, boleta.getRecIdCliente());
			}else{
				pst.setNull(1, java.sql.Types.INTEGER);
			}

			pst.setString(2, Sesion.DNI);
			pst.setInt(3, boleta.getId());
			
			pst.executeUpdate();
			
			this.setNoError(CORRECTO);
			this.setMensaje(MEN_INSERT_CORRECTO);
			estado=true;
		}catch (Exception e) {
			this.setNoError(INCORRECTO);
     	    this.setMensaje(MEN_INSERT_INCORRECTO+e.getMessage());
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





public  boolean actualizarBoletaSeparacion(Boleta boleta){
		
		boolean estado=false; 
		this.boleta=boleta;
		String url = "update  boleta set SepSeparar=?, SepFechaRecojo=?, SepAcuenta=?, SepSaldo=? , "
				+ "  ModificadoPor=?  where id=?";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url);
			pst.setInt(1, (boleta.isSepSeparar())?1:0);
			
			if(boleta.getSepFechaRecojo()!=null){
				pst.setDate(2, new java.sql.Date(boleta.getSepFechaRecojo().getTime()));
			}else{
				pst.setNull(2, java.sql.Types.DATE);
			}
			
			if(boleta.getSepACuenta()!=-1.0){
				pst.setDouble(3, boleta.getSepACuenta());
			}else{
				pst.setNull(3, java.sql.Types.DOUBLE);
			}
			
			if(boleta.getSepSaldo()!=-1.0){
				pst.setDouble(4, boleta.getSepSaldo());
			}else{
				pst.setNull(4, java.sql.Types.DOUBLE);
			}

			pst.setString(5, Sesion.DNI);
			pst.setInt(6, boleta.getId());
			
			pst.executeUpdate();
			
			this.setNoError(CORRECTO);
			this.setMensaje(MEN_INSERT_CORRECTO);
			estado=true;
		}catch (Exception e) {
			this.setNoError(INCORRECTO);
     	    this.setMensaje(MEN_INSERT_INCORRECTO+e.getMessage());
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




public  boolean actualizarBoletaDevolucion(Boleta boleta){
		
		boolean estado=false; 
		this.boleta=boleta;
		String url = "update  boleta set DevFechaEntrega=?,  DevFechaDevolucion=?, "
				+ "  ModificadoPor=?  where id=?";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url);
			
			if(boleta.getDevFechaEntrega()!=null){
				pst.setDate(1, new java.sql.Date(boleta.getDevFechaEntrega().getTime()));
			}else{
				pst.setNull(1,java.sql.Types.DATE);
			}
			
			if(boleta.getDevFechaDevolucion()!=null){
				pst.setDate(2, new java.sql.Date(boleta.getDevFechaDevolucion().getTime()));
			}else{
				pst.setNull(2, java.sql.Types.DATE);
			}
			
			pst.setString(3, Sesion.DNI);
			pst.setInt(4, boleta.getId());
			
			pst.executeUpdate();
			
			this.setNoError(CORRECTO);
			this.setMensaje(MEN_INSERT_CORRECTO);
			estado=true;
		}catch (Exception e) {
			this.setNoError(INCORRECTO);
     	    this.setMensaje(MEN_INSERT_INCORRECTO+e.getMessage());
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






	
	public Boleta selecccionarBoletaPendiente(){
		Boleta boleta=null;
		String url = "SELECT Serie, Numero, Id , Fecha,IdCliente ,Tipo, Cupon1, Cupon2, Cupon3, ValorCuponPor, "
				+ " DesAdicionalAc, DesAdiSoles, DesAdiPor, Subtotal, DesCupones, DesAdicional, TotalPagar, GarDni, GarNroDni, "
				+ " GarDniMenor, GarNroDniMenor, GarLicencia, GarNroLicencia, GarDinero, "
				+ " GarMonto, GarOtro, GarOtroEspecifique, GarEnlazarBoleta, GarSerieBoleta, GarNumeroBoleta, GarIdBoleta, "
				+ " IdClienteRec, SepSeparar, SepFechaRecojo, SepAcuenta, SepSaldo,"
				+ " DevFechaEntrega, DevFechaDevolucion,  EstadoAccion, CreadoPor   FROM boleta where EstadoAccion = 1 AND CreadoPor =?";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url);
			pst.setString(1,  Sesion.DNI);
			rs=pst.executeQuery();
			if(rs.next()){
				System.out.println((rs.getBoolean("Id"))?rs.getInt("Id"):-1);
				boleta=new Boleta();
				boleta.setSerie(rs.getString("Serie"));
				boleta.setNumero((rs.getBoolean("Numero"))?rs.getInt("Numero"):-1);
				boleta.setId((rs.getBoolean("Id"))?rs.getInt("Id"):-1);
				boleta.setFecha(rs.getDate("Fecha"));
				boleta.setIdCliente((rs.getBoolean("IdCliente"))?rs.getInt("IdCliente"):-1);
				boleta.setTipo((rs.getBoolean("Tipo"))?rs.getInt("Tipo"):-1);
				boleta.setCupon1((rs.getInt("Cupon1")==1)?true:false);
				boleta.setCupon2((rs.getInt("Cupon2")==1)?true:false);
				boleta.setCupon3((rs.getInt("Cupon3")==1)?true:false);
				boleta.setValorCuponPor(((Double)rs.getObject("ValorCuponPor")!=null)?rs.getDouble("ValorCuponPor"):-1.0);
				boleta.setDesAdicionalAc(rs.getInt("DesAdicionalAc"));
				boleta.setDesAdiSoles(((Double)rs.getObject("DesAdiSoles")!=null)?rs.getDouble("DesAdiSoles"):-1.0);
				boleta.setDesAdiPor(((Double)rs.getObject("DesAdiPor")!=null)?rs.getDouble("DesAdiPor"):-1.0);
				
				/*Costos*/
				boleta.setSubTotal(rs.getString("SubTotal"));
				boleta.setDesCupones(rs.getString("DesCupones"));
				boleta.setDesAdicional(rs.getString("DesAdicional"));
				boleta.setTotalPagar(rs.getString("TotalPagar"));

				/*Garantia*/
				boleta.setGarDni((rs.getInt("GarDni")==1)?true:false);
				boleta.setGarNroDni(rs.getString("GarNroDni"));
				boleta.setGarDniMenor((rs.getInt("GarDniMenor")==1)?true:false);
				boleta.setGarNroDniMenor(rs.getString("GarNroDniMenor"));
				boleta.setGarLicencia((rs.getInt("GarLicencia")==1)?true:false);
				boleta.setGarNroLicencia(rs.getString("GarNroLicencia"));
				boleta.setGarDinero((rs.getInt("GarDinero")==1)?true:false);
				boleta.setGarMonto(((Double)rs.getObject("GarMonto")!=null)?rs.getDouble("GarMonto"):-1.0);
				boleta.setGarOtro((rs.getInt("GarOtro")==1)?true:false);
				boleta.setGarOtroEspecifique(rs.getString("GarOtroEspecifique"));
				boleta.setGarEnlazarBoleta((rs.getInt("GarEnlazarBoleta")==1)?true:false);
				boleta.setGarIdBoleta((rs.getBoolean("GarIdBoleta"))?rs.getInt("GarIdBoleta"):-1);
			
				/*Recomendacion*/
				boleta.setRecIdCliente((rs.getBoolean("IdClienteRec"))?rs.getInt("GarIdBoleta"):-1);
				
				/*Separacioon*/
				boleta.setSepSeparar((rs.getInt("SepSeparar")==1)?true:false);
				boleta.setSepFechaRecojo(rs.getDate("SepFechaRecojo"));
				boleta.setSepACuenta(((Double)rs.getObject("SepAcuenta")!=null)?rs.getDouble("GarMonto"):-1.0);
				boleta.setSepSaldo(((Double)rs.getObject("SepSaldo")!=null)?rs.getDouble("GarMonto"):-1.0);	
				
				/*Devolucion*/
				boleta.setDevFechaEntrega(rs.getDate("DevFechaEntrega"));
				boleta.setDevFechaDevolucion(rs.getDate("DevFechaDevolucion"));
				boleta.setEstadoAccion(rs.getInt("EstadoAccion"));
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
		
		return boleta;
	}
	


	
	public Boleta selecccionarBoletaReserva(int idBoleta){
		Boleta boleta=null;
		String url = "SELECT Serie, Numero, Id , Fecha,IdCliente ,Tipo, Cupon1, Cupon2, Cupon3, ValorCuponPor, "
				+ " DesAdicionalAc, DesAdiSoles, DesAdiPor, Subtotal, DesCupones, DesAdicional, TotalPagar, GarDni, GarNroDni, "
				+ " GarDniMenor, GarNroDniMenor, GarLicencia, GarNroLicencia, GarDinero, "
				+ " GarMonto, GarOtro, GarOtroEspecifique, GarEnlazarBoleta, GarSerieBoleta, GarNumeroBoleta, GarIdBoleta, "
				+ " IdClienteRec, SepSeparar, SepFechaRecojo, SepAcuenta, SepSaldo,"
				+ " DevFechaEntrega, DevFechaDevolucion,  EstadoAccion, CreadoPor   FROM boleta where Id=?";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url);
			pst.setInt(1,  idBoleta);
			rs=pst.executeQuery();
			if(rs.next()){
				System.out.println((rs.getBoolean("Id"))?rs.getInt("Id"):-1);
				boleta=new Boleta();
				boleta.setSerie(rs.getString("Serie"));
				boleta.setNumero((rs.getBoolean("Numero"))?rs.getInt("Numero"):-1);
				boleta.setId((rs.getBoolean("Id"))?rs.getInt("Id"):-1);
				boleta.setFecha(rs.getDate("Fecha"));
				boleta.setIdCliente((rs.getBoolean("IdCliente"))?rs.getInt("IdCliente"):-1);
				boleta.setTipo((rs.getBoolean("Tipo"))?rs.getInt("Tipo"):-1);
				boleta.setCupon1((rs.getInt("Cupon1")==1)?true:false);
				boleta.setCupon2((rs.getInt("Cupon2")==1)?true:false);
				boleta.setCupon3((rs.getInt("Cupon3")==1)?true:false);
				boleta.setValorCuponPor(((Double)rs.getObject("ValorCuponPor")!=null)?rs.getDouble("ValorCuponPor"):-1.0);
				boleta.setDesAdicionalAc(rs.getInt("DesAdicionalAc"));
				boleta.setDesAdiSoles(((Double)rs.getObject("DesAdiSoles")!=null)?rs.getDouble("DesAdiSoles"):-1.0);
				boleta.setDesAdiPor(((Double)rs.getObject("DesAdiPor")!=null)?rs.getDouble("DesAdiPor"):-1.0);
				
				/*Costos*/
				boleta.setSubTotal(rs.getString("SubTotal"));
				boleta.setDesCupones(rs.getString("DesCupones"));
				boleta.setDesAdicional(rs.getString("DesAdicional"));
				boleta.setTotalPagar(rs.getString("TotalPagar"));

				/*Garantia*/
				boleta.setGarDni((rs.getInt("GarDni")==1)?true:false);
				boleta.setGarNroDni(rs.getString("GarNroDni"));
				boleta.setGarDniMenor((rs.getInt("GarDniMenor")==1)?true:false);
				boleta.setGarNroDniMenor(rs.getString("GarNroDniMenor"));
				boleta.setGarLicencia((rs.getInt("GarLicencia")==1)?true:false);
				boleta.setGarNroLicencia(rs.getString("GarNroLicencia"));
				boleta.setGarDinero((rs.getInt("GarDinero")==1)?true:false);
				boleta.setGarMonto(((Double)rs.getObject("GarMonto")!=null)?rs.getDouble("GarMonto"):-1.0);
				boleta.setGarOtro((rs.getInt("GarOtro")==1)?true:false);
				boleta.setGarOtroEspecifique(rs.getString("GarOtroEspecifique"));
				boleta.setGarEnlazarBoleta((rs.getInt("GarEnlazarBoleta")==1)?true:false);
				boleta.setGarIdBoleta((rs.getBoolean("GarIdBoleta"))?rs.getInt("GarIdBoleta"):-1);
				boleta.setGarSerieBoleta(rs.getString("GarSerieBoleta"));
				boleta.setGarNumeroBoleta((rs.getBoolean("GarNumeroBoleta"))?rs.getInt("GarNumeroBoleta"):-1);
				/*Recomendacion*/
				boleta.setRecIdCliente((rs.getBoolean("IdClienteRec"))?rs.getInt("GarIdBoleta"):-1);
				
				/*Separacioon*/
				boleta.setSepSeparar((rs.getInt("SepSeparar")==1)?true:false);
				boleta.setSepFechaRecojo(rs.getDate("SepFechaRecojo"));
				boleta.setSepACuenta(((Double)rs.getObject("SepAcuenta")!=null)?rs.getDouble("SepAcuenta"):-1.0);
				boleta.setSepSaldo(((Double)rs.getObject("SepSaldo")!=null)?rs.getDouble("SepSaldo"):-1.0);	
				
				/*Devolucion*/
				boleta.setDevFechaEntrega(rs.getDate("DevFechaEntrega"));
				boleta.setDevFechaDevolucion(rs.getDate("DevFechaDevolucion"));
				boleta.setEstadoAccion(rs.getInt("EstadoAccion"));
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
		
		return boleta;
	}
	

	public Boleta selecccionarBoleta(int idBoleta){
		Boleta boleta=null;
		String url = "SELECT Serie, Numero, Id , Fecha,IdCliente ,Tipo, Cupon1, Cupon2, Cupon3, ValorCuponPor, "
				+ " DesAdicionalAc, DesAdiSoles, DesAdiPor, Subtotal, DesCupones, DesAdicional, TotalPagar, GarDni, GarNroDni, "
				+ " GarDniMenor, GarNroDniMenor, GarLicencia, GarNroLicencia, GarDinero, "
				+ " GarMonto, GarOtro, GarOtroEspecifique, GarEnlazarBoleta, GarSerieBoleta, GarNumeroBoleta, GarIdBoleta, "
				+ ""
				+ "DevFechaCompletada, DevCompletada,DevFechaGarantiaCompletada, DevGarantiaCompletada, DevDni, DevDniMenor, DevLicencia,DevDinero, DevOtro, DevEnlazarBoleta, DevNroBoletaDannio, DevEstadoBoletaDanio, "
				+ "PagoRecom, PorcentajePorPagoRec, PorcentajePago, IdEgreso, "
				+ ""
				+ " IdClienteRec, SepSeparar, SepFechaRecojo, SepAcuenta, SepSaldo,"
				+ " DevFechaEntrega, DevFechaDevolucion,  EstadoAccion, CreadoPor, ModificadoPor, FCreacion ,FModificacion  FROM boleta where Id=?";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url);
			pst.setInt(1,  idBoleta);
			rs=pst.executeQuery();
			if(rs.next()){
				System.out.println((rs.getBoolean("Id"))?rs.getInt("Id"):-1);
				boleta=new Boleta();
				boleta.setSerie(rs.getString("Serie"));
				boleta.setNumero((rs.getBoolean("Numero"))?rs.getInt("Numero"):-1);
				boleta.setId((rs.getBoolean("Id"))?rs.getInt("Id"):-1);
				boleta.setFecha(rs.getDate("Fecha"));
				boleta.setIdCliente((rs.getBoolean("IdCliente"))?rs.getInt("IdCliente"):-1);
				boleta.setTipo((rs.getBoolean("Tipo"))?rs.getInt("Tipo"):-1);
				boleta.setCupon1((rs.getInt("Cupon1")==1)?true:false);
				boleta.setCupon2((rs.getInt("Cupon2")==1)?true:false);
				boleta.setCupon3((rs.getInt("Cupon3")==1)?true:false);
				boleta.setValorCuponPor(((Double)rs.getObject("ValorCuponPor")!=null)?rs.getDouble("ValorCuponPor"):-1.0);
				boleta.setDesAdicionalAc(rs.getInt("DesAdicionalAc"));
				boleta.setDesAdiSoles(((Double)rs.getObject("DesAdiSoles")!=null)?rs.getDouble("DesAdiSoles"):-1.0);
				boleta.setDesAdiPor(((Double)rs.getObject("DesAdiPor")!=null)?rs.getDouble("DesAdiPor"):-1.0);
				
				/*Costos*/
				boleta.setSubTotal(rs.getString("SubTotal"));
				boleta.setDesCupones(rs.getString("DesCupones"));
				boleta.setDesAdicional(rs.getString("DesAdicional"));
				boleta.setTotalPagar(rs.getString("TotalPagar"));

				/*Garantia*/
				boleta.setGarDni((rs.getInt("GarDni")==1)?true:false);
				boleta.setGarNroDni(rs.getString("GarNroDni"));
				boleta.setGarDniMenor((rs.getInt("GarDniMenor")==1)?true:false);
				boleta.setGarNroDniMenor(rs.getString("GarNroDniMenor"));
				boleta.setGarLicencia((rs.getInt("GarLicencia")==1)?true:false);
				boleta.setGarNroLicencia(rs.getString("GarNroLicencia"));
				boleta.setGarDinero((rs.getInt("GarDinero")==1)?true:false);
				boleta.setGarMonto(((Double)rs.getObject("GarMonto")!=null)?rs.getDouble("GarMonto"):-1.0);
				boleta.setGarOtro((rs.getInt("GarOtro")==1)?true:false);
				boleta.setGarOtroEspecifique(rs.getString("GarOtroEspecifique"));
				boleta.setGarEnlazarBoleta((rs.getInt("GarEnlazarBoleta")==1)?true:false);
				boleta.setGarIdBoleta((rs.getBoolean("GarIdBoleta"))?rs.getInt("GarIdBoleta"):-1);
				boleta.setGarSerieBoleta(rs.getString("GarSerieBoleta"));
				boleta.setGarNumeroBoleta((rs.getBoolean("GarNumeroBoleta"))?rs.getInt("GarNumeroBoleta"):-1);
				/*Recomendacion*/
				boleta.setRecIdCliente((rs.getBoolean("IdClienteRec"))?rs.getInt("GarIdBoleta"):-1);
				
				/*Separacioon*/
				boleta.setSepSeparar((rs.getInt("SepSeparar")==1)?true:false);
				boleta.setSepFechaRecojo(rs.getDate("SepFechaRecojo"));
				boleta.setSepACuenta(((Double)rs.getObject("SepAcuenta")!=null)?rs.getDouble("SepAcuenta"):-1.0);
				boleta.setSepSaldo(((Double)rs.getObject("SepSaldo")!=null)?rs.getDouble("SepSaldo"):-1.0);	
				
				/*Devolucion*/
				boleta.setDevFechaEntrega(rs.getDate("DevFechaEntrega"));
				boleta.setDevFechaDevolucion(rs.getDate("DevFechaDevolucion"));
				boleta.setEstadoAccion(rs.getInt("EstadoAccion"));
				
				boleta.setCreadoPor(rs.getString("CreadoPor"));
				boleta.setfCreacion(rs.getDate("FCreacion"));
				boleta.setModificadoPor(rs.getString("ModificadoPor"));
				boleta.setfModificacion(rs.getDate("FModificacion"));
				
				
				
				/*modficaciones ultimas */
				boleta.setDevFechaCompleta(rs.getString("DevFechaCompletada"));
				boleta.setDevCompletada(rs.getInt("DevCompletada"));
				
				boleta.setDevFechaGarantiaCompletada(rs.getString("DevFechaGarantiaCompletada"));
				boleta.setDevGarantiaCompletada(rs.getInt("DevGarantiaCompletada"));
				boleta.setDevDni(rs.getBoolean("DevDni"));
				boleta.setDevDniMenor(rs.getBoolean("DevDniMenor"));
				boleta.setDevLicencia(rs.getBoolean("DevLicencia"));
				boleta.setDevDinero(rs.getBoolean("DevDinero"));
				boleta.setDevOtro(rs.getBoolean("DevOtro"));
				boleta.setDevEnlazarBoleta(rs.getBoolean("DevEnlazarBoleta"));
				boleta.setDevNroBoletaDannio(rs.getInt("DevNroBoletaDannio"));
				boleta.setDevEstadoBoletaDanio(rs.getInt("DevEstadoBoletaDanio"));
				//boleta.setDevGarantiaCompletada(devGarantiaCompletada);
				/**/
				
				boleta.setPagoRecom(rs.getInt("PagoRecom"));
				boleta.setPorcentajePorPagoRec(rs.getDouble("PorcentajePorPagoRec"));
				boleta.setPorcentajePago(rs.getDouble("PorcentajePago"));
				boleta.setIdEgreso(rs.getInt("IdEgreso"));
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
		
		return boleta;
	}
	
	public Boleta selecccionarPreciosCalculados(int id){
		Boleta boleta=null;
		String url = "SELECT "
				+ " Subtotal, DesCupones, DesAdicional, TotalPagar, "
				+ " CreadoPor, ModificadoPor  FROM boleta where Id=?";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			pst=conexionServidor.prepareStatement(url);
			pst.setInt(1,  id);
			
			rs=pst.executeQuery();
			if(rs.next()){
				boleta=new Boleta();
				
				/*Costos*/
				boleta.setSubTotal(rs.getString("SubTotal"));
				boleta.setDesCupones(rs.getString("DesCupones"));
				boleta.setDesAdicional(rs.getString("DesAdicional"));
				boleta.setTotalPagar(rs.getString("TotalPagar"));
				
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
		
		return boleta;
	}

	public void eliminarBoleta(int idBoleta){
		String url = "delete from boleta where Id=?;";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url);
			pst.setInt(1,idBoleta);
			pst.executeUpdate();
			this.setNoError(CORRECTO);
			this.setMensaje(MEN_DELETE_CORRECTO);
		}catch (Exception e) {
			this.setNoError(INCORRECTO);
     	    this.setMensaje(MEN_DELETE_INCORRECTO+e.getMessage()); 
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
		
	}
	
	
	public Boleta selecccionarBoletaGarantia(int id){
		Boleta boleta=null;
		String url = "SELECT "
				+ " Subtotal, DesCupones, DesAdicional, TotalPagar, "
				+ " CreadoPor, ModificadoPor  FROM boleta where Id=?";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			pst=conexionServidor.prepareStatement(url);
			pst.setInt(1,  id);
			
			rs=pst.executeQuery();
			if(rs.next()){
				boleta=new Boleta();
				
				/*Costos*/
				boleta.setSubTotal(rs.getString("SubTotal"));
				boleta.setDesCupones(rs.getString("DesCupones"));
				boleta.setDesAdicional(rs.getString("DesAdicional"));
				boleta.setTotalPagar(rs.getString("TotalPagar"));
				
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
		
		return boleta;
	}
	
	public Object[] selecccionarBoletaGarantia(String serie, int numero){
		
		Object[] objetos=null;
		String url = "SELECT "
				+ " Id, (select NombresApellidos from cliente where cliente.Id= boleta.IdCliente) as apellnomb, "
				+ " CreadoPor, ModificadoPor  FROM boleta where Serie=? and Numero=? and  GarEnlazarBoleta=0 and "
				+ " (DevCompletada is null or DevCompletada<>1) and (DevGarantiaCompletada is null or DevGarantiaCompletada<>1)"
				+ " and Tipo=1 and EstadoAccion=3 and EstadoEliminado=0";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			pst=conexionServidor.prepareStatement(url);
			pst.setString(1, serie);
			pst.setInt(2,  numero);
			
			rs=pst.executeQuery();
			if(rs.next()){
				objetos=new Object[2];
				objetos[0]=rs.getInt("Id");
				objetos[1]=rs.getString("apellnomb");
				
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
		
		return objetos;
	}
	
	public void eliminarBoletaPendientea(int idBoleta){
		String url = "delete from boleta where Id=? and EstadoAccion=1 ;";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url);
			pst.setInt(1,idBoleta);
			pst.executeUpdate();
			this.setNoError(CORRECTO);
			this.setMensaje(MEN_DELETE_CORRECTO);
		}catch (Exception e) {
			this.setNoError(INCORRECTO);
	 	    this.setMensaje(MEN_DELETE_INCORRECTO+e.getMessage()); 
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
		
	}
	
	
	
	public void eliminarBoletaReserva(int idBoleta){
		String url = "delete from boleta where Id=? and EstadoAccion=2 ;";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url);
			pst.setInt(1,idBoleta);
			pst.executeUpdate();
			this.setNoError(CORRECTO);
			this.setMensaje(MEN_DELETE_CORRECTO);
		}catch (Exception e) {
			this.setNoError(INCORRECTO);
	 	    this.setMensaje(MEN_DELETE_INCORRECTO+e.getMessage()); 
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
		
	}
	
	/*Este es un metodo excluisvamente para duplicado de boleta, este metodo a diferencia de otros metodos es improvisado..*/
/*PARAMETROS: Id de boleta que se va duplicar*/
	public int agregarBoletaDuplicado(Boleta boleta){
		
		int idUltimo = -1;
		this.boleta=boleta;
		String url = "call agregar_boleta_duplicado(?,?,?)";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url);
			
			if(boleta.getIdCliente()!=-1){
				pst.setInt(1, boleta.getIdCliente());
			}else {
				pst.setNull(1, java.sql.Types.NULL);
			}
			
			pst.setString(2, Sesion.DNI);
			
			if(boleta.getIdBoletaDuplicado()!=-1){
				pst.setInt(3, boleta.getIdBoletaDuplicado());
			}else {
				pst.setNull(3, java.sql.Types.NULL);
			}
			rs=pst.executeQuery();
			//rs=pst.getResultSet();
			
			while (rs.next()){
				this.boleta.setId(rs.getInt("ultimoIdBoleta"));
				idUltimo=rs.getInt(1);
			}
			this.setNoError(CORRECTO);
			this.setMensaje(MEN_INSERT_CORRECTO);
		}catch (Exception e) {
			this.setNoError(INCORRECTO);
     	    this.setMensaje(MEN_INSERT_INCORRECTO+e.getMessage());
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
		return idUltimo;
	}
	

	
	public Boleta selecccionarBoletaGarantiaAndDevolucionGarantia(int idBoleta ){
		Boleta boleta=null;
		String url = "SELECT GarDni , GarNroDni, GarDniMenor, GarNroDniMenor, GarLicencia, GarNroLicencia, GarDinero, "+
    " GarMonto, GarOtro, GarOtroEspecifique,  GarEnlazarBoleta,  GarSerieBoleta,"+
" 	GarNumeroBoleta, GarIdBoleta, DevFechaCompletada,DevCompletada,DevDni, "+ 
    " DevDniMenor,  DevLicencia,  DevDinero, DevOtro,  DevEnlazarBoleta,   DevNroBoletaDannio, "+
    " DevEstadoBoletaDanio FROM boleta where Id=?;";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url);
			pst.setInt(1,  idBoleta);
			rs=pst.executeQuery();
			if(rs.next()){
				boleta=new Boleta();
			
			

				/*Garantia*/
				boleta.setGarDni((rs.getInt("GarDni")==1)?true:false);
				boleta.setGarNroDni(rs.getString("GarNroDni"));
				boleta.setGarDniMenor((rs.getInt("GarDniMenor")==1)?true:false);
				boleta.setGarNroDniMenor(rs.getString("GarNroDniMenor"));
				boleta.setGarLicencia((rs.getInt("GarLicencia")==1)?true:false);
				boleta.setGarNroLicencia(rs.getString("GarNroLicencia"));
				boleta.setGarDinero((rs.getInt("GarDinero")==1)?true:false);
				boleta.setGarMonto(((Double)rs.getObject("GarMonto")!=null)?rs.getDouble("GarMonto"):-1.0);
				boleta.setGarOtro((rs.getInt("GarOtro")==1)?true:false);
				boleta.setGarOtroEspecifique(rs.getString("GarOtroEspecifique"));
				boleta.setGarEnlazarBoleta((rs.getInt("GarEnlazarBoleta")==1)?true:false);
				boleta.setGarSerieBoleta(rs.getString("GarSerieBoleta"));
				boleta.setGarNumeroBoleta(rs.getInt("GarNumeroBoleta"));
				boleta.setGarIdBoleta((rs.getBoolean("GarIdBoleta"))?rs.getInt("GarIdBoleta"):-1);
				
				boleta.setDevFechaCompleta(rs.getString("DevFechaCompletada"));
				boleta.setDevCompletada(rs.getInt("DevCompletada"));
				
				boleta.setDevDni(rs.getBoolean("DevDni"));
				boleta.setDevDniMenor(rs.getBoolean("DevDniMenor"));
				boleta.setDevLicencia(rs.getBoolean("DevLicencia"));
				boleta.setDevDinero(rs.getBoolean("DevDinero"));
				boleta.setDevOtro(rs.getBoolean("DevOtro"));
				boleta.setDevEnlazarBoleta(rs.getBoolean("DevEnlazarBoleta"));
				boleta.setDevNroBoletaDannio(rs.getInt("DevNroBoletaDannio"));
				boleta.setDevEstadoBoletaDanio(rs.getInt("DevEstadoBoletaDanio"));

	
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
		
		return boleta;
	}
	
	public boolean  actualizarBoletaGarantiaDevolucion(Boleta boleta){
		boolean estado=false; 
		this.boleta=boleta;
		String url = "update boleta set "+
		" DevFechaCompletada=now(), DevDni=?, "+ 
    	" DevDniMenor=?,  DevLicencia=?,  DevDinero=?, DevOtro=?,  DevEnlazarBoleta =? "+
    	"   where Id=?;";
		
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url);
			
			pst.setInt(1,(boleta.isDevDni())?1:0);
			pst.setInt(2, (boleta.isDevDniMenor())?1:0);
			pst.setInt(3, (boleta.isDevLicencia())?1:0);
			pst.setInt(4,( boleta.isDevDinero())?1:0);
			pst.setInt(5, (boleta.isDevOtro())?1:0);
			pst.setInt(6, (boleta.isDevEnlazarBoleta())?1:0);
			
			pst.setInt(7, boleta.getId());
			
			
			pst.executeUpdate();
			
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

	public boolean  actualizarBoletaDevCompletada(int idBoleta){
		boolean estado=false; 

		String url = "update boleta set DevFechaCompletada=now(), DevCompletada=1, ModificadoPor=? where Id=?;";
		
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url);
			pst.setString(1, Sesion.DNI);
			pst.setInt(2, idBoleta);
			
			
			pst.executeUpdate();
			
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

	

	public boolean  actualizarBoletaDevGarantiaCompletada(int idBoleta){
		boolean estado=false; 

		String url = "update boleta set DevFechaGarantiaCompletada=now(), DevGarantiaCompletada=1, ModificadoPor=? where Id=?;";
		
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url);
			pst.setString(1, Sesion.DNI);
			pst.setInt(2, idBoleta);
			
			
			pst.executeUpdate();
			
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
	/*Inicio-Modificaciones 03/08/2019*/
	public Boleta seleccionarDevArticulosGarantiaVentaDanio(int idBoleta ){
		Boleta boleta=null;
		String url = "SELECT 	DevCompletada,  DevGarantiaCompletada, IdBoletaDanio FROM boleta where Id=?;";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url);
			pst.setInt(1,  idBoleta);
			rs=pst.executeQuery();
			if(rs.next()){
				boleta=new Boleta();
				boleta.setId(idBoleta);
				boleta.setDevCompletada(rs.getInt("DevCompletada"));
				boleta.setDevGarantiaCompletada(rs.getInt("DevGarantiaCompletada"));
				System.out.println("Id boleta dañado"+boleta.getIdBoletaDanio());
				boleta.setIdBoletaDanio((rs.getInt("IdBoletaDanio")==0)?-1:rs.getInt("IdBoletaDanio"));
				System.out.println("Id boleta dañado luego"+boleta.getIdBoletaDanio());
				System.out.println("Id boleta dañado luego de base"+rs.getInt("IdBoletaDanio"));


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
		
		return boleta;
	}
	
	public Boleta cargarDescuentosAdicYCupones(int idBoleta ){
		Boleta boleta=null;
		String url = "SELECT  Cupon1, Cupon2, Cupon3, ValorCuponPor, "
				+ " DesAdicionalAc, DesAdiSoles, DesAdiPor FROM boleta where Id=?;";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url);
			pst.setInt(1,  idBoleta);
			rs=pst.executeQuery();
			if(rs.next()){
				boleta=new Boleta();
				boleta.setId(idBoleta);
				
				boleta.setCupon1((rs.getInt("Cupon1")==1)?true:false);
				boleta.setCupon2((rs.getInt("Cupon2")==1)?true:false);
				boleta.setCupon3((rs.getInt("Cupon3")==1)?true:false);
				boleta.setValorCuponPor(((Double)rs.getObject("ValorCuponPor")!=null)?rs.getDouble("ValorCuponPor"):-1.0);
				boleta.setDesAdicionalAc(rs.getInt("DesAdicionalAc"));
				boleta.setDesAdiSoles(((Double)rs.getObject("DesAdiSoles")!=null)?rs.getDouble("DesAdiSoles"):-1.0);
				boleta.setDesAdiPor(((Double)rs.getObject("DesAdiPor")!=null)?rs.getDouble("DesAdiPor"):-1.0);
				
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
		
		return boleta;
	}
	
	
	public Boleta checkFacturacion(int idBoleta ){
		Boleta boleta=null;
		String url = "SELECT EstadoAccion FROM boleta where Id=?;";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url);
			pst.setInt(1,  idBoleta);
			rs=pst.executeQuery();
			if(rs.next()){
				boleta=new Boleta();
				boleta.setId(idBoleta);
				boleta.setEstadoAccion(rs.getInt("EstadoAccion"));
			
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
		
		return boleta;
	}
	
	
	
	/*fin 03/08/2019*/
	
}
