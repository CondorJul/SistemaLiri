package model.devolucion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dal.TDetalleBoletaDevolucion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MPadre;

public class MTDetalleBoletaDevolucion extends MPadre{
	public ObservableList<TDetalleBoletaDevolucion> seleccionarTDetalleBoletaDevolucioonPorBoleta(int idBoleta){
		

			
			ObservableList<TDetalleBoletaDevolucion> array=FXCollections.observableArrayList();;
			
			TDetalleBoletaDevolucion dal=null;
			
			String url = " SELECT  Id, IdBoleta, TipoVenta, TipoPro, idPieza, idArticulo, Codigo, Cant, "
			  		+ " Descripcion from detalleBoleta where IdBoleta=?;  ";
			
			PreparedStatement pst=null;
			ResultSet rs=null;
			int contador=1;
			
			try {
				pst=conexionServidor.prepareStatement(url);
				pst.setInt(1, idBoleta);
			
				rs=pst.executeQuery();
			
				while(rs.next()){
					
					dal=new TDetalleBoletaDevolucion();
					dal.setId(rs.getInt("Id"));
				   dal.setIdBoleta(rs.getInt("IdBoleta"));
				   dal.setTipoVenta(rs.getInt("TipoVenta"));
				   dal.setTipoPro(rs.getInt("TipoPro"));
				   dal.setIdPieza(rs.getInt("idPieza"));
				   dal.setIdArticulo(rs.getInt("idArticulo"));
				   dal.setCodigo(rs.getString("Codigo"));
				   dal.setCant(rs.getInt("Cant"));
				   dal.setDescripcion(rs.getString("Descripcion"));
				
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
	
	
	/*
	

	public TDetalleBoletaDevolucion seleccionarTDetalleBoletaDevolucioonPorBoleta(int boletaId){
		
		TDetalleBoletaDevolucion tDetalleBoletaDevolucion=null;		
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement("SELECT  Id, IdBoleta, TipoVenta, TipoPro, idPieza, idArticulo, Codigo, Cant, "
			  		+ " Descripcion from detalleBoleta where IdBoleta=?; ");
		 	   pst.setInt(1, boletaId);
			   rs=pst.executeQuery();
			   
			   while(rs.next()){
				   tDetalleBoletaDevolucion=new TDetalleBoletaDevolucion();
				   tDetalleBoletaDevolucion.setId(rs.getInt("Id"));
				   tDetalleBoletaDevolucion.setIdBoleta(rs.getInt("IdBoleta"));
				   tDetalleBoletaDevolucion.setTipoVenta(rs.getInt("TipoVenta"));
				   tDetalleBoletaDevolucion.setTipoPro(rs.getInt("TipoPro"));
				   tDetalleBoletaDevolucion.setIdPieza(rs.getInt("idPieza"));
				   tDetalleBoletaDevolucion.setIdArticulo(rs.getInt("idArticulo"));
				   tDetalleBoletaDevolucion.setCodigo(rs.getString("Codigo"));
				   tDetalleBoletaDevolucion.setCant(rs.getInt("Cant"));
				   
			   }
			  
         }   
         catch(SQLException e){
      	   e.printStackTrace();

		  }finally{
      	   try {
      		   if(pst!=null){
      			   pst.close();
				
      	   		}   		
      	   		if(rs!=null){
      	   			rs.close();
      	   		}
      	   
      	   } catch (SQLException e) {
					
					e.printStackTrace();
				}
      	   
         }
		
		return tDetalleBoletaDevolucion;
	}*/
}
