package model.recibo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import dal.TAlquilerHoy;
import dal.TRecibo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MPadre;

public class MTRecibo  extends MPadre{
public ObservableList<TRecibo> seleccionarTRecibo(String buscar, LocalDate fechaInicio, LocalDate fechaFin){
	buscar=(buscar!=null && !buscar.trim().isEmpty())?"%"+buscar+"%":"%";

		
		ObservableList<TRecibo> arrayTRecibo=FXCollections.observableArrayList();;
		
		TRecibo tRecibo=null;
		
		String url = "select boleta.Id as Id, IdCliente, Fecha, Serie, lpad(Numero, 8, '0') as Numero, case Tipo when 1 then 'Alquiler' when 2 then 'Venta' when 3 then 'Duplicado' end as TipoString ,  Tipo, concat(Codigo, '-', Dni) as codDni, "
				+ "    NombresApellidos, DNI, Subtotal , DesCupones, DesAdicional, TotalPagar, DevFechaEntrega, DevFechaDevolucion, "
				+ "    cliente.Codigo as codigo , EstadoEliminado "
				+ " from cliente inner join boleta on cliente.Id=boleta.IdCliente "
				+ " where (concat(Serie,'-',lpad(Numero, 8, '0')) like ? or NombresApellidos like ? or DNI like ?  or Codigo like ?) and EstadoAccion=3 and date(Fecha) BETWEEN ? AND ? ; ";
		
		PreparedStatement pst=null;
		ResultSet rs=null;
		int contador=1;
		
		try {
			pst=conexionServidor.prepareStatement(url);
			pst.setString(1, buscar);
			pst.setString(2, buscar);
			pst.setString(3, buscar);
			pst.setString(4, buscar);
			pst.setDate(5, java.sql.Date.valueOf(fechaInicio));
			pst.setDate(6, java.sql.Date.valueOf(fechaFin));
					
			rs=pst.executeQuery();
		
			while(rs.next()){
				
				tRecibo=new TRecibo();
				
				tRecibo.setId(rs.getInt("Id"));
				tRecibo.setIdCliente(rs.getInt("IdCliente"));
				tRecibo.setNro(String.valueOf(contador++));
				tRecibo.setFEntrega(rs.getString("Fecha"));
				tRecibo.setSerie(rs.getString("Serie"));
				tRecibo.setNumero(rs.getString("Numero"));
				tRecibo.setTipoInt(rs.getInt("Tipo"));
				tRecibo.setTipo(rs.getString("TipoString"));
				tRecibo.setCodDni(rs.getString("codDni"));
				tRecibo.setApellNom(rs.getString("NombresApellidos"));
				tRecibo.setSubTotal(rs.getString("Subtotal"));
				tRecibo.setDesCupones(rs.getString("DesCupones"));
				tRecibo.setDesAdic(rs.getString("DesAdicional"));
				tRecibo.setTotal(rs.getString("TotalPagar"));
				tRecibo.setEstadoAccion(rs.getInt("EstadoEliminado"));
				tRecibo.setEstado((rs.getInt("EstadoEliminado")==1)?"Anulado":"Facturado");
			
				
				arrayTRecibo.add(tRecibo);
				
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
		
		return arrayTRecibo;
	}


}
