package model.alquiler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dal.TBoletaSee;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MPadre;

public class MTBoletaSee extends MPadre{
	public TBoletaSee seleccionarTBoletaSee(int idBoleta){
					
			
		TBoletaSee dal=null;
			
			String url = "SELECT Serie, LPAD(Numero,8,'0') as Numero, Id , Fecha,IdCliente ,Tipo, Fecha, "
				+ " Subtotal, DesCupones, DesAdicional, TotalPagar,  DATEDIFF(SepFechaRecojo, curdate() ) as diasrecojo ,  "
				+ " IdClienteRec, SepSeparar, SepFechaRecojo, SepAcuenta, SepSaldo,"
				+ " DevFechaEntrega, DevFechaDevolucion,  EstadoAccion, CreadoPor, GarNroDni, GarNroDniMenor, GarNroLicencia, GarMonto, GarOtroEspecifique, GarSerieBoleta, GarNumeroBoleta    FROM boleta where Id=?  ";
			
			PreparedStatement pst=null;
			ResultSet rs=null;
			
			try {
				pst=conexionServidor.prepareStatement(url);
				pst.setInt(1, idBoleta);
				
						
				rs=pst.executeQuery();
			
				while(rs.next()){
					
					dal=new TBoletaSee();
					dal.setId(rs.getInt("Id"));
					dal.setTipo(rs.getInt("Tipo"));
					dal.setSerieBoleta(rs.getString("Serie"));
					dal.setNumeroBoleta(rs.getString("Numero"));
					dal.setFecha(rs.getString("Fecha"));
					dal.setfEntrega(rs.getString("DevFechaEntrega"));
					dal.setfDevolucion(rs.getString("DevFechaDevolucion"));
					
					dal.setSubTotal(rs.getString("Subtotal"));
					dal.setDesCupones(rs.getString("DesCupones"));
					dal.setDesAdic(rs.getString("DesAdicional"));
					dal.setTotal(rs.getString("TotalPagar"));
					
					dal.setSepFRecojo(rs.getString("SepFechaRecojo"));
					dal.setSepFACuenta(rs.getString("SepAcuenta"));
					dal.setSepSaldo(rs.getString("SepSaldo"));
					dal.setDiasRecojo(rs.getInt("diasrecojo"));
					
					
					dal.setGarNroDni(rs.getString("GarNroDni"));
					dal.setGarNroDniMenor(rs.getString("GarNroDniMenor"));
					dal.setGarNroLicencia(rs.getString("GarNroLicencia")); 
					dal.setGarMonto(rs.getString("GarMonto")); 
					dal.setGarOtroEspecifique(rs.getString("GarOtroEspecifique"));
					dal.setGarSerieBoleta(rs.getString("GarSerieBoleta"));
					dal.setGarNumeroBoleta(rs.getString("GarNumeroBoleta"));
					
					
					
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
			
			return dal;
		}

	public String seleccionarTBoletaSeeSerieNum(int idBoleta){
		
		
			
			String url = "select concat(Serie, '-',LPAD(Numero,8,'0'), ';',Fecha) as serienumero FROM boleta where Id=?  ";
			String serienumero="xxxx-xxxxxxxx";
			PreparedStatement pst=null;
			ResultSet rs=null;
			
			try {
				pst=conexionServidor.prepareStatement(url);
				pst.setInt(1, idBoleta);
				
						
				rs=pst.executeQuery();
			
				while(rs.next()){
					
					serienumero=rs.getString("serienumero");
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
			
			return serienumero;
		}

	
}
