package model.alquiler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dal.DetalleBoleta;
import dal.TDetalleBoleta;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MPadre;

public class MTDetalleBoleta extends MPadre{

	public ObservableList<TDetalleBoleta> seleccionarDetalleBoletaPorBoleta(int idBoleta){
		
		ObservableList<TDetalleBoleta> arrayDetalleBoleta=FXCollections.observableArrayList();
		TDetalleBoleta tDetalleBoleta = null;
		//String url = "SELECT Id, TipoVenta, TipoPro, Cant, Codigo,  if(TipoPro=1,(select substring(Resena,1,200) from articulo where Id=idArticulo),null ) as resenia, "
			//	+ " Descripcion, PrecioUnit, Importe from detalleboleta where IdBoleta=?;";
		
		String url = "SELECT Id, TipoVenta, TipoPro, Cant, Codigo,  if(TipoPro=1,(select substring(Resena,1,200) from articulo where Id=idArticulo),null ) as resenia, "
				+ "  concat(Descripcion, '(',if(TipoPro=1, (select count(*) from pieza where ArticuloId=idArticulo),1 ),')') as Descripcion , PrecioUnit, Importe from detalleboleta where IdBoleta=?;";
		
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url);
			//pst.setInt(1,idBoleta);
			pst.setInt(1,idBoleta);
			rs=pst.executeQuery();
			while (rs.next()){
				tDetalleBoleta=new TDetalleBoleta();
				tDetalleBoleta.setResenia(rs.getString("resenia"));
				tDetalleBoleta.setId(rs.getInt("Id"));
				tDetalleBoleta.setCant(rs.getInt("Cant"));
				tDetalleBoleta.setCodigo(rs.getString("Codigo"));
				tDetalleBoleta.setDescripcion(rs.getString("Descripcion"));
				tDetalleBoleta.setPrecioUnit(rs.getString("PrecioUnit"));
				tDetalleBoleta.setImporte(rs.getString("Importe"));
				
				arrayDetalleBoleta.add(tDetalleBoleta);	
				
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
}
