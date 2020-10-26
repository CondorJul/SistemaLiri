package model.devolucion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dal.Boleta;
import dal.TDetallePorPiezasDevolucion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.MPadre;
import model.MPadre.ExcepcionMPadre;
import sesion.Sesion;

public class MTDetallePorPiezasDevolucion  extends MPadre{

	public ObservableList<TDetallePorPiezasDevolucion> seleccionarTDetallePorPiezasDevolucion(int idDetalleBoleta){
		

		
		ObservableList<TDetallePorPiezasDevolucion> array=FXCollections.observableArrayList();;
		
		TDetallePorPiezasDevolucion dal=null;
		
		String url = " select Id, IdPieza, IdDetalleboleta, "
				+ " (select Codigo from pieza where Id=piezasdetalleboleta.IdPieza) as Codigo, "
				+ " (select Descripcion from pieza where Id=piezasdetalleboleta.IdPieza) as Descripcion, "
				+ " PendienteCant, DevueltoCant, AlmacenCant , EsperaCant, LavanderiaCant, ReparacionCant, PlanchadoCant , VentaCant "
				+ " from piezasdetalleboleta where IdDetalleboleta=?;  ";
		
		PreparedStatement pst=null;
		ResultSet rs=null;
		int contador=1;
		
		try {
			pst=conexionServidor.prepareStatement(url);
			pst.setInt(1, idDetalleBoleta);
		
			rs=pst.executeQuery();
		
			while(rs.next()){
				
				dal=new TDetallePorPiezasDevolucion();
				dal.setNro(contador++);
				dal.setId(rs.getInt("Id"));
				dal.setIdDetalleBoleta(rs.getInt("IdDetalleboleta"));
				dal.setIdPieza(rs.getInt("IdPieza"));
				dal.setCodigo(rs.getString("Codigo"));
				dal.setPieza(rs.getString("Descripcion"));
				dal.setPendiente(rs.getInt("PendienteCant"));
				dal.setDevuelto(rs.getInt("DevueltoCant"));
				dal.getAlmacen().getValueFactory().setValue(rs.getInt("AlmacenCant"));
				dal.getEspera().getValueFactory().setValue(rs.getInt("EsperaCant"));
				dal.getLavanderia().getValueFactory().setValue(rs.getInt("LavanderiaCant"));
				dal.getReparacion().getValueFactory().setValue(rs.getInt("ReparacionCant"));
				dal.getPlanchado().getValueFactory().setValue(rs.getInt("PlanchadoCant"));
				dal.getVendido().getValueFactory().setValue(rs.getInt("VentaCant"));
			
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
	

public boolean actualizarTDetallePorPiezasDevolucion(TDetallePorPiezasDevolucion tDetallePorPiezasDevolucion){
		boolean devolver=false;

		String url = "update piezasdetalleboleta set  PendienteCant=?, DevueltoCant=?, AlmacenCant=?,EsperaCant=?, "
				+ " LavanderiaCant=?, ReparacionCant=?,PlanchadoCant=?, VentaCant=?, ModificadoPor=? where Id=?;";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			pst=conexionServidor.prepareStatement(url);
			pst.setInt(1,tDetallePorPiezasDevolucion.getPendiente());
			pst.setInt(2,tDetallePorPiezasDevolucion.getDevuelto());
			pst.setInt(3,tDetallePorPiezasDevolucion.getAlmacen().getValue());
			pst.setInt(4,tDetallePorPiezasDevolucion.getEspera().getValue());
			pst.setInt(5,tDetallePorPiezasDevolucion.getLavanderia().getValue());
			pst.setInt(6,tDetallePorPiezasDevolucion.getReparacion().getValue());
			pst.setInt(7,tDetallePorPiezasDevolucion.getPlanchado().getValue());
			pst.setInt(8,tDetallePorPiezasDevolucion.getVendido().getValue());

			pst.setString(9, Sesion.DNI);
			
			pst.setInt(10, tDetallePorPiezasDevolucion.getId());
			
			
		
			pst.executeUpdate();
			pst.executeUpdate();
			this.setNoError(CORRECTO);
			this.setMensaje(MEN_UPDATE_CORRECTO);
			devolver=true;
		}catch (Exception e) {
			devolver=false;
			this.setNoError(INCORRECTO);
     	    this.setMensaje(MEN_UPDATE_INCORRECTO+e.getMessage());
     	    
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

	
}
