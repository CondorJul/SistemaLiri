package model.estadistica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dal.Estadistica;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import model.MPadre;

public class MEstadisticaAnual extends MPadre {
	
	private Estadistica estaditica=null;

	public ObservableList<Estadistica> seleccionarColeccionIA(){
		ObservableList<Estadistica> arrayListIA = FXCollections.observableArrayList();
		this.estaditica=null;
		String url = "call ingresoAnual();";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst=conexionServidor.prepareStatement(url);
			rs = pst.executeQuery();
			while(rs.next()){
				estaditica = new Estadistica();
				estaditica.setEstad_conceptoMes(rs.getString("iAnio"));
				estaditica.setEstad_totalConceptoMes(rs.getDouble("iATotal"));
				arrayListIA.add(estaditica);
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
		return arrayListIA;
	}
	
	public ObservableList<Estadistica> seleccionarColeccionEA(){
		ObservableList<Estadistica> arrayListEA = FXCollections.observableArrayList();
		this.estaditica=null;
		String url = "call egresoAnual();";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst=conexionServidor.prepareStatement(url);
			rs = pst.executeQuery();
			while(rs.next()){
				estaditica = new Estadistica();
				estaditica.setEstad_conceptoMes(rs.getString("eAnio"));
				estaditica.setEstad_totalConceptoMes(rs.getDouble("eATotal"));
				arrayListEA.add(estaditica);
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
		return arrayListEA;
	}
	
	public Estadistica balanceAnual(){
		Estadistica estadistica = null;
		String url = "call balanceAnual();";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			pst=conexionServidor.prepareStatement(url);
			rs=pst.executeQuery();
			while(rs.next()){
				estadistica = new Estadistica();
				estadistica.setEstad_ingreso(rs.getDouble("iAnual"));
				estadistica.setEstad_egreso(rs.getDouble("eAnual"));
				estadistica.setEstad_balance(rs.getDouble("bAnual"));				
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
		
	public ObservableList<XYChart.Series<String, Number>> estadisticaIEAnual(){
		ObservableList<XYChart.Series<String, Number>> array=FXCollections.observableArrayList();
		
		try { 
			
			ObservableList<Estadistica> arrayIngreso =  seleccionarColeccionIA();
			ObservableList<Estadistica> arrayEgreso =  seleccionarColeccionEA();
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
