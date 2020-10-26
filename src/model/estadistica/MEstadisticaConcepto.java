package model.estadistica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import dal.Estadistica;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import model.MPadre;

public class MEstadisticaConcepto extends MPadre{
	
	private Estadistica estadistica=null;
	
	public ObservableList<Estadistica> seleccionarColeccionIC(){
		ObservableList<Estadistica> arrayListIC = FXCollections.observableArrayList();
		this.estadistica=null;
		String url = "SELECT CASE Tipo WHEN 1 THEN 'Alquiler' WHEN 2 THEN 'Venta' WHEN 3 THEN 'Duplicado de Boleta' END AS iConcepto, ifnull(sum(TotalPagar), 00.00) as iCTotal "
				+ "FROM boleta WHERE EstadoAccion=3 AND EstadoEliminado=0 GROUP BY Tipo;";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst=conexionServidor.prepareStatement(url);
			rs = pst.executeQuery();
			while(rs.next()){
				estadistica = new Estadistica();
				estadistica.setEstad_conceptoMes(rs.getString("iConcepto"));
				estadistica.setEstad_totalConceptoMes(rs.getDouble("iCTotal"));
				arrayListIC.add(estadistica);
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
		return arrayListIC;
	}
	
	public ObservableList<Estadistica> seleccionarColeccionEC(){
		ObservableList<Estadistica> arrayListEC = FXCollections.observableArrayList();
		this.estadistica=null;
		String url = "SELECT (SELECT Concepto FROM concepto WHERE Id=egreso.IdConcepto) as eConcepto, ifnull(sum(monto), 00.00) as eCTotal "
				+ "FROM egreso GROUP BY IdConcepto;";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst=conexionServidor.prepareStatement(url);
			rs = pst.executeQuery();
			while(rs.next()){
				estadistica = new Estadistica();
				estadistica.setEstad_conceptoMes(rs.getString("eConcepto"));
				estadistica.setEstad_totalConceptoMes(rs.getDouble("eCTotal"));
				arrayListEC.add(estadistica);
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
		return arrayListEC;
	}
	
	public Estadistica balanceConcepto(){
		Estadistica estadistica = null;
		String url = "call balanceConcepto();";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			pst=conexionServidor.prepareStatement(url);
			rs=pst.executeQuery();
			while(rs.next()){
				estadistica = new Estadistica();
				estadistica.setEstad_ingreso(rs.getDouble("iConcepto"));
				estadistica.setEstad_egreso(rs.getDouble("eConcepto"));
				estadistica.setEstad_balance(rs.getDouble("bConcepto"));				
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
		
		return estadistica;	
	}
		
	public ObservableList<XYChart.Series<String, Number>> estadisticaIngresoConcepto(){
		ObservableList<XYChart.Series<String, Number>> array=FXCollections.observableArrayList();
		
		String url = "call balanceConcepto();";
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst=conexionServidor.prepareStatement(url); 
			rs=pst.executeQuery();
			if(rs.next()){
				XYChart.Series<String, Number> ingreso=new XYChart.Series<String, Number>();
				ingreso.setName ("Ingreso");
				ingreso.getData().add(new XYChart.Data("", rs.getDouble("iConcepto")));
				XYChart.Series<String, Number> egreso=new XYChart.Series<String, Number>();
				egreso.setName ("Egreso");
				egreso.getData().add(new XYChart.Data("", rs.getDouble("eConcepto")));
				XYChart.Series<String, Number> balance=new XYChart.Series<String, Number>();
				balance.setName ("Balance");
				balance.getData().add(new XYChart.Data("", rs.getDouble("bConcepto")));      	
			
	        	array.addAll(ingreso, egreso, balance);
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
		return array;
	}
}
