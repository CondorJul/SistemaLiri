package model.estadistica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

import dal.Articulo;
import dal.Cliente;
import dal.Estadistica;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import model.MPadre;

public class MEstadisticaRankingAC extends MPadre {
	
	private Cliente cliente=null;
	private Articulo articulo=null;

	public ObservableList<Articulo> seleccionarColeccionRAArticulo(){
		ObservableList<Articulo> arrayListRAA = FXCollections.observableArrayList();
		this.articulo=null;
		String url = "select Codigo, Descripcion, (select sum(Cant) from detalleboleta where idArticulo=articulo.Id and TipoPro=1 and idArticulo is not null group by idArticulo) as Cant "
				+ "from articulo order by Cant desc limit 10;";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst=conexionServidor.prepareStatement(url);
			rs = pst.executeQuery();
			while(rs.next()){
				articulo = new Articulo();
				articulo.setCodigo(rs.getString("Codigo"));
				articulo.setDescripcion(rs.getString("Descripcion"));
				articulo.setCantAlquilado(rs.getInt("Cant"));
				arrayListRAA.add(articulo);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
		return arrayListRAA;
	}
	
	public ObservableList<Cliente> seleccionarColeccionRRCliente(){
		ObservableList<Cliente> arrayListRRC = FXCollections.observableArrayList();
		this.cliente=null;
		String url = "select Codigo, DNI, NombresApellidos, (select count(IdCliente) from boleta where IdCliente=cliente.Id and idCliente is not null group by IdCliente) as Cant "
				+ "from cliente order by Cant desc limit 10;";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst=conexionServidor.prepareStatement(url);
			rs = pst.executeQuery();
			while(rs.next()){
				cliente = new Cliente();
				cliente.setCli_codigo(rs.getString("Codigo"));
				cliente.setCli_dni(rs.getString("DNI"));
				cliente.setCli_apellNom(rs.getString("NombresApellidos"));
				cliente.setCli_cantRecurrencia(rs.getInt("Cant"));
				arrayListRRC.add(cliente);
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
		return arrayListRRC;
	}
			
	public ObservableList<XYChart.Series<String, Number>> estadisticaRankingArticulosAlquilados(){
		ObservableList<XYChart.Series<String, Number>> array=FXCollections.observableArrayList();
		
		try {
			ObservableList<Articulo> arrayArticulos =  seleccionarColeccionRAArticulo();
	        XYChart.Series<String, Number> articulos=new XYChart.Series<String, Number>();
	        articulos.setName ("Articulos");
     		for (Articulo articulo : arrayArticulos){
     			articulos.getData().add(new XYChart.Data<>(articulo.getCodigo(), articulo.getCantAlquilado()));
     		}	
				
	        array.add(articulos);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}
	
	public ObservableList<XYChart.Series<String, Number>> estadisticaClientesRecurrentes(){
		ObservableList<XYChart.Series<String, Number>> array=FXCollections.observableArrayList();
		
		try {
			ObservableList<Cliente> arrayClientes =  seleccionarColeccionRRCliente();
	        XYChart.Series<String, Number> clientes=new XYChart.Series<String, Number>();
	        clientes.setName ("Clientes");
     		for (Cliente cliente : arrayClientes){
     			clientes.getData().add(new XYChart.Data<>(cliente.getCli_codigo(), cliente.getCli_cantRecurrencia()));
     		}		
	        array.add(clientes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return array;
	}
		
}
