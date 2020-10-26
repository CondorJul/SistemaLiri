package model.estadistica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dal.Estadistica;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.XYChart;
import model.MPadre;

public class MEstadisticaMensual extends MPadre {
	
	private Estadistica estaditica=null;

	public ObservableList<Estadistica> seleccionarColeccionIM(){
		ObservableList<Estadistica> arrayListIM = FXCollections.observableArrayList();
		this.estaditica=null;
		String url = "call ingresoMensual();";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst=conexionServidor.prepareStatement(url);
			rs = pst.executeQuery();
			while(rs.next()){
				estaditica = new Estadistica();
				estaditica.setEstad_conceptoMes(rs.getString("iMensual"));
				estaditica.setEstad_totalConceptoMes(rs.getDouble("iMTotal"));
				arrayListIM.add(estaditica);
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
		return arrayListIM;
	}
	
	public ObservableList<Estadistica> seleccionarColeccionEM(){
		ObservableList<Estadistica> arrayListEM = FXCollections.observableArrayList();
		this.estaditica=null;
		String url = "call egresoMensual();";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst=conexionServidor.prepareStatement(url);
			rs = pst.executeQuery();
			while(rs.next()){
				estaditica = new Estadistica();
				estaditica.setEstad_conceptoMes(rs.getString("eMensual"));
				estaditica.setEstad_totalConceptoMes(rs.getDouble("eMTotal"));
				arrayListEM.add(estaditica);
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
		return arrayListEM;
	}
	
	public Estadistica balanceMensual(){
		Estadistica estadistica = null;
		String url = "call balanceMensual();";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			pst=conexionServidor.prepareStatement(url);
			rs=pst.executeQuery();
			while(rs.next()){
				estadistica = new Estadistica();
				estadistica.setEstad_ingreso(rs.getDouble("iMensual"));
				estadistica.setEstad_egreso(rs.getDouble("eMensual"));
				estadistica.setEstad_balance(rs.getDouble("bMensual"));				
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
		
	public ObservableList<XYChart.Series<String, Number>> estadisticaIEMensual(){
		ObservableList<XYChart.Series<String, Number>> array=FXCollections.observableArrayList();
		
		try { 
			
			ObservableList<Estadistica> arrayIngreso =  seleccionarColeccionIM();
			ObservableList<Estadistica> arrayEgreso =  seleccionarColeccionEM();
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
