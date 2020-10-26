package model.devolucion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dal.TBoletaAvalada;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MPadre;

public class MTBoletaAvalada extends MPadre {
	
	
	
	public ObservableList<TBoletaAvalada> seleccionarBoletaAvalada( int garIdBoleta){
			ObservableList<TBoletaAvalada> array=FXCollections.observableArrayList();;
			
			TBoletaAvalada dal=null;
			
			String url = "select Id, Fecha, Serie, LPAD(Numero, 8,'0') as Numero, DevEnlazarBoleta, DevCompletada, DevGarantiaCompletada "
					+ " from boleta  "
					+ " where GarIdBoleta=? ";
			
			PreparedStatement pst=null;
			ResultSet rs=null;
			int contador=1;
			
			try {
				pst=conexionServidor.prepareStatement(url);
				pst.setInt(1, garIdBoleta);
			
						
				rs=pst.executeQuery();
			
				while(rs.next()){
					
					dal=new TBoletaAvalada();
					
					dal.setId(rs.getInt("Id"));

					dal.setNroEnum(String.valueOf(contador++));
					dal.setFecha(rs.getString("Fecha"));
					dal.setSerieBoleta(rs.getString("Serie"));
					dal.setNumeroBoleta(rs.getString("Numero"));
					dal.setDevEnlazarBoleta(rs.getInt("DevEnlazarBoleta"));
					dal.setDevCompletada(rs.getInt("DevCompletada"));
					dal.setDevGarantiaCompletada(rs.getInt("DevGarantiaCompletada"));
					//dal.setTipo(rs.getString("Tipo"));
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

}
