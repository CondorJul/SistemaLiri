package model.alquiler;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dal.TAlquilerHoy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MPadre;

public class MTAlquilerHoy  extends MPadre{
public ObservableList<TAlquilerHoy> seleccionarTAlquilerHoy(String buscar){
	buscar=(buscar!=null && !buscar.trim().isEmpty())?"%"+buscar+"%":"%";

		
		ObservableList<TAlquilerHoy> arrayTAlquilerHoy=FXCollections.observableArrayList();;
		
		TAlquilerHoy tAlquilerHoy=null;
		
		String url = "select boleta.Id as Id, IdCliente, Fecha, Serie, lpad(Numero, 8, '0') as Numero, case Tipo when 1 then 'Alquiler' when 2 then 'Venta' when 3 then 'Duplicado' end as Tipo ,  concat(Codigo, '-', Dni) as codDni, "
				+ "    NombresApellidos, DNI, Subtotal , DesCupones, DesAdicional, TotalPagar, DevFechaEntrega, DevFechaDevolucion, "
				+ "    cliente.Codigo as codigo "
				+ " from cliente inner join boleta on cliente.Id=boleta.IdCliente "
				+ " where (concat(Serie,'-',lpad(Numero, 8, '0')) like ? or NombresApellidos like ? or DNI like ?  or Codigo like ?) and EstadoAccion=3 and date(Fecha)=curdate();";
		
		PreparedStatement pst=null;
		ResultSet rs=null;
		int contador=1;
		
		try {
			pst=conexionServidor.prepareStatement(url);
			pst.setString(1, buscar);
			pst.setString(2, buscar);
			pst.setString(3, buscar);
			pst.setString(4, buscar);
					
			rs=pst.executeQuery();
		
			while(rs.next()){
				
				tAlquilerHoy=new TAlquilerHoy();
				
				tAlquilerHoy.setId(rs.getInt("Id"));
				tAlquilerHoy.setIdCliente(rs.getInt("IdCliente"));
				tAlquilerHoy.setNro(String.valueOf(contador++));
				tAlquilerHoy.setSerie(rs.getString("Serie"));
				tAlquilerHoy.setNumero(rs.getString("Numero"));
				tAlquilerHoy.setTipo(rs.getString("Tipo"));
				tAlquilerHoy.setCodDni(rs.getString("codDni"));
				tAlquilerHoy.setApellNom(rs.getString("NombresApellidos"));
				tAlquilerHoy.setSubTotal(rs.getString("Subtotal"));
				tAlquilerHoy.setDesCupones(rs.getString("DesCupones"));
				tAlquilerHoy.setDesAdic(rs.getString("DesAdicional"));
				tAlquilerHoy.setTotal(rs.getString("TotalPagar"));
				tAlquilerHoy.setfEntrega(rs.getString("DevFechaEntrega"));
				tAlquilerHoy.setfDevolucion(rs.getString("DevFechaDevolucion"));
				
				arrayTAlquilerHoy.add(tAlquilerHoy);
				
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
		
		return arrayTAlquilerHoy;
	}
}
