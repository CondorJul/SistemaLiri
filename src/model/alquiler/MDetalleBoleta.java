package model.alquiler;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dal.DetalleBoleta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.stage.StageStyle;
import model.MPadre;
import sesion.Sesion;

public class MDetalleBoleta extends MPadre{
private DetalleBoleta detalleBoleta=null;
	
	public int agregarDetalleBoleta(DetalleBoleta detalleBoleta){
		
		int idUltimo = -1;
		this.detalleBoleta=detalleBoleta;
		String url = "INSERT INTO detalleboleta( IdBoleta, TipoVenta, TipoPro, idPieza, idArticulo, Cant, CreadoPor) "
				+ " VALUES(?,?,?,?,?,?,?);";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			pst=conexionServidor.prepareStatement(url,PreparedStatement.RETURN_GENERATED_KEYS);

			
			pst.setInt(1, detalleBoleta.getIdBoleta());
			if(detalleBoleta.getTipoVenta()!=-1){
				pst.setInt(2, detalleBoleta.getTipoVenta());
			}else{
				pst.setNull(2, java.sql.Types.INTEGER);
			}
			if(detalleBoleta.getTipoPro()!=-1){
				pst.setInt(3,detalleBoleta.getTipoPro());
			}else{
				pst.setNull(3, java.sql.Types.INTEGER);
			}
			
			if(detalleBoleta.getIdPieza()!=-1){
				pst.setInt(4, detalleBoleta.getIdPieza());
			}else{
				pst.setNull(4, java.sql.Types.INTEGER);
			}
			
			if(detalleBoleta.getIdArticulo()!=-1){
				pst.setInt(5, detalleBoleta.getIdArticulo());
			}else{
				pst.setNull(5, java.sql.Types.INTEGER);
			}
			
			pst.setInt(6, Integer.parseInt(detalleBoleta.getCant().getText()));
			pst.setString(7, Sesion.DNI);

			
			pst.executeUpdate();
			rs=pst.getGeneratedKeys();
			while (rs.next()){
				this.detalleBoleta.setId(rs.getInt(1));
				idUltimo=rs.getInt(1);
			}
			this.setNoError(CORRECTO);
			this.setMensaje(MEN_INSERT_CORRECTO);
		}catch (Exception e) {
			this.setNoError(INCORRECTO);
     	    this.setMensaje(MEN_INSERT_INCORRECTO+e.getMessage());
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
		return idUltimo;
	}
	
	

	public DetalleBoleta seleccionarDetalleBoleta(int idDetalleBoleta){
		DetalleBoleta detalleBoleta = null;
		String url = "SELECT Id, IdBoleta, TipoVenta, TipoPro, idPieza, idArticulo, Cant, Codigo, "
				+ " Descripcion, PrecioUnit, Importe from detalleboleta where Id=?;";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url);
			pst.setInt(1,idDetalleBoleta);
			rs=pst.executeQuery();
			while (rs.next()){
				detalleBoleta=new DetalleBoleta();
				detalleBoleta.setId(rs.getInt("Id"));
				detalleBoleta.setIdBoleta(rs.getInt("IdBoleta"));
				detalleBoleta.setTipoVenta(rs.getInt("TipoVenta"));
				detalleBoleta.setTipoPro(rs.getInt("TipoPro"));
				detalleBoleta.setIdPieza((rs.getBoolean("idPieza"))?rs.getInt("idPieza"):-1);
				detalleBoleta.setIdArticulo((rs.getBoolean("idArticulo"))?rs.getInt("idArticulo"):-1);
				
				detalleBoleta.getCant().setText(rs.getString("Cant"));
				detalleBoleta.setCodigo(rs.getString("Codigo"));
				detalleBoleta.setDescripcion(rs.getString("Descripcion"));
				detalleBoleta.setPrecioUnit(rs.getString("PrecioUnit"));
				detalleBoleta.setImporte(rs.getString("Importe"));

				
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
		
		return detalleBoleta;
	}
	
	

	public ObservableList<DetalleBoleta> seleccionarDetalleBoletaPorBoleta(int idBoleta){
		ObservableList<DetalleBoleta> arrayDetalleBoleta=FXCollections.observableArrayList();
		DetalleBoleta detalleBoleta = null;
		String url = "SELECT Id, IdBoleta, TipoVenta, TipoPro, idPieza, idArticulo, Cant, Codigo, "
				+ " Descripcion, PrecioUnit, Importe from detalleboleta where IdBoleta=?;";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url);
			pst.setInt(1,idBoleta);
			rs=pst.executeQuery();
			while (rs.next()){
				detalleBoleta=new DetalleBoleta();
				detalleBoleta.setId(rs.getInt("Id"));
				detalleBoleta.setIdBoleta(rs.getInt("IdBoleta"));
				detalleBoleta.setTipoVenta(rs.getInt("TipoVenta"));
				detalleBoleta.setTipoPro(rs.getInt("TipoPro"));
				detalleBoleta.setIdPieza((rs.getBoolean("idPieza"))?rs.getInt("idPieza"):-1);
				detalleBoleta.setIdArticulo((rs.getBoolean("idArticulo"))?rs.getInt("idArticulo"):-1);
				
				detalleBoleta.getCant().setText(rs.getString("Cant"));
				detalleBoleta.setCodigo(rs.getString("Codigo"));
				detalleBoleta.setDescripcion(rs.getString("Descripcion"));
				detalleBoleta.setPrecioUnit(rs.getString("PrecioUnit"));
				detalleBoleta.setImporte(rs.getString("Importe"));
				detalleBoleta.setCantEntero(rs.getInt("Cant"));

				arrayDetalleBoleta.add(detalleBoleta);	
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
		
		return arrayDetalleBoleta;
	}
	

	public DetalleBoleta seleccionarDetalleBoletaCantImporte(int idDetalleBoleta){
		DetalleBoleta detalleBoleta = null;
		String url = "SELECT Id, Cant,  "
				+ "  Importe from detalleboleta where Id=?;";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url);
			pst.setInt(1,idDetalleBoleta);
			rs=pst.executeQuery();
			while (rs.next()){
				detalleBoleta=new DetalleBoleta();
				detalleBoleta.setId(rs.getInt("Id"));
				detalleBoleta.getCant().setText(rs.getString("Cant"));
				detalleBoleta.setImporte(rs.getString("Importe"));
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
		
		return detalleBoleta;
	}
	
	
	

	public void eliminarDetalleBoleta(int idDetalleBoleta){
		String url = "delete from detalleboleta where Id=?;";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url);
			pst.setInt(1,idDetalleBoleta);
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
	
public boolean actualizarCantDetalleBoleta(int idDetalleBoleta, int cant){
		boolean devolver=false;

		String url = "UPDATE detalleboleta set Cant=?,  ModificadoPor=? "
				+ " where Id=?;";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			pst=conexionServidor.prepareStatement(url,PreparedStatement.RETURN_GENERATED_KEYS);
			pst.setInt(1,cant);
			pst.setString(2, Sesion.DNI);
			pst.setInt(3, idDetalleBoleta);
			pst.executeUpdate();
			pst.executeUpdate();
			this.setNoError(CORRECTO);
			this.setMensaje(MEN_INSERT_CORRECTO);
			devolver=true;
		}catch (Exception e) {
			devolver=false;
			this.setNoError(INCORRECTO);
     	    this.setMensaje(MEN_INSERT_INCORRECTO+e.getMessage());
     	    
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
		return devolver;
	}
	
	public void eliminarDetalleBoletaPorBoleta(int idBoleta){
		String url = "delete from detalleboleta where IdBoleta=?;";
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
}
