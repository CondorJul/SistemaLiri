package model.alquiler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dal.Reservacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MPadre;

public class MReservacion extends MPadre {

	public ObservableList<Reservacion> seleccionarReservacion(String buscar){
		buscar=(buscar!=null && !buscar.trim().isEmpty())?"%"+buscar+"%":"%";
		
		ObservableList<Reservacion> arrayReservacion=FXCollections.observableArrayList();;
		
		Reservacion reservacion=null;
		
		String url = "select boleta.Id as Id,"
				+ " (SELECT Descripcion FROM detalleboleta WHERE IdBoleta=boleta.Id LIMIT 1) as articuloPieza ,  "
				+ " IdCliente, Fecha, if(Tipo=1,'Alquiler',if(Tipo=2,'Venta','Duplicado de Boleta')) as Tipo, NombresApellidos, DNI, cliente.Codigo as codigo, TotalPagar, "
				+ "SepAcuenta, SepSaldo, SepFechaRecojo from cliente inner join boleta on cliente.Id=boleta.IdCliente"
				+ " where (NombresApellidos like ? or DNI like ? or cliente.Codigo like ? or (SELECT Descripcion FROM detalleboleta WHERE IdBoleta=boleta.Id LIMIT 1) like ?) and EstadoAccion=2  "
				+ " ;";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			pst=conexionServidor.prepareStatement(url);
			pst.setString(1, buscar);
			pst.setString(2, buscar);
			pst.setString(3, buscar);
			pst.setString(4, buscar);
			rs=pst.executeQuery();
			while(rs.next()){
				
				reservacion=new Reservacion();
				reservacion.setId(rs.getInt("Id"));
				reservacion.setArticuloPieza(rs.getString("articuloPieza"));
				reservacion.setIdCliente(rs.getInt("IdCliente"));
				reservacion.setFecha(rs.getString("Fecha"));
				reservacion.setTipoFact(rs.getString("Tipo"));
				reservacion.setApellNom(rs.getString("NombresApellidos"));
				reservacion.setDni(rs.getString("DNI"));
				reservacion.setCodCliente(rs.getString("codigo"));
				reservacion.setTotalAPagar(rs.getString("TotalPagar"));
				reservacion.setACuenta(rs.getString("SepAcuenta"));
				reservacion.setSaldo(rs.getString("SepSaldo"));
				reservacion.setFRecojo(rs.getString("SepFechaRecojo"));
				arrayReservacion.add(reservacion);
				
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
		
		return arrayReservacion;
	}
}
