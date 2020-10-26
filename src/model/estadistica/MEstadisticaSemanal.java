package model.estadistica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import dal.Estadistica;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import model.MPadre;

public class MEstadisticaSemanal extends MPadre{
	
	private Estadistica estadistica=null;
	
	public ObservableList<Estadistica> seleccionarColeccionIS(){
		ObservableList<Estadistica> arrayListIS = FXCollections.observableArrayList();
		this.estadistica=null;
		String url = "call ingresoSemanal();";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst=conexionServidor.prepareStatement(url);
			rs = pst.executeQuery();
			while(rs.next()){
				estadistica = new Estadistica();
				estadistica.setEstad_conceptoMes(rs.getString("iDias"));
				estadistica.setEstad_totalConceptoMes(rs.getDouble("iSTotal"));
				arrayListIS.add(estadistica);
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
		return arrayListIS;
	}
	
	public ObservableList<Estadistica> seleccionarColeccionES(){
		ObservableList<Estadistica> arrayListES = FXCollections.observableArrayList();
		this.estadistica=null;
		String url = "call egresoSemanal();";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst=conexionServidor.prepareStatement(url);
			rs = pst.executeQuery();
			while(rs.next()){
				estadistica = new Estadistica();
				estadistica.setEstad_conceptoMes(rs.getString("eDias"));
				estadistica.setEstad_totalConceptoMes(rs.getDouble("eSTotal"));
				arrayListES.add(estadistica);
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
		return arrayListES;
	}
	
	public Estadistica balanceSemanal(){
		Estadistica estadistica = null;
		String url = "call balanceSemanal();";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			pst=conexionServidor.prepareStatement(url);
			rs=pst.executeQuery();
			while(rs.next()){
				estadistica = new Estadistica();
				estadistica.setEstad_ingreso(rs.getDouble("iSemanal"));
				estadistica.setEstad_egreso(rs.getDouble("eSemanal"));
				estadistica.setEstad_balance(rs.getDouble("bSemanal"));				
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
		
	public ObservableList<XYChart.Series<String, Number>> estadisticaIESemanal(){
		ObservableList<XYChart.Series<String, Number>> array=FXCollections.observableArrayList();
		
		try { 
			
			ObservableList<Estadistica> arrayIngreso =  seleccionarColeccionIS();
			ObservableList<Estadistica> arrayEgreso =  seleccionarColeccionES();
			XYChart.Series<String, Number> ingreso=new XYChart.Series<String, Number>();
			ingreso.setName ("Ingreso");
	        XYChart.Series<String, Number> egreso=new XYChart.Series<String, Number>();
	        egreso.setName ("Egreso");
     			
			for (Estadistica estadistica : arrayIngreso) {
		        ingreso.getData().add(new XYChart.Data(estadistica.getEstad_conceptoMes(), estadistica.getEstad_totalConceptoMes()));
			}
			
			for (Estadistica estadistica : arrayEgreso) {
		        egreso.getData().add(new XYChart.Data(estadistica.getEstad_conceptoMes(), estadistica.getEstad_totalConceptoMes()));
			}	
		
	        array.addAll(ingreso, egreso);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return array;
	}
}
