package model.inicio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.MPadre;

public class MInicio extends MPadre {
	
	public static String NUM_ALQUILER_HOY="select count(*) as num from boleta where day(Fecha)=day(now()) and month(Fecha)=month(now()) and Tipo=1 and EstadoAccion=3;";
	public static String NUM_ALQUILER_MES="select count(*) as num from boleta where month(FCreacion)=month(now()) and Tipo=1 and EstadoAccion=3;";
	public static String NUM_ALQUILER_TOTAL="select count(*) as num from boleta where Tipo=1 and EstadoAccion=3;";
	
	public static String NUM_VENTA_HOY="select count(*) as num from boleta where day(Fecha)=day(now()) and month(Fecha)=month(now()) and Tipo=2 and EstadoAccion=3;";
	public static String NUM_VENTA_MES="select count(*) as num from boleta where month(FCreacion)=month(now()) and Tipo=2 and EstadoAccion=3;";
	public static String NUM_VENTA_TOTAL="select count(*) as num from boleta where Tipo=2 and EstadoAccion=3;";
	
	public static String NUM_ARTICULO_HOY="select count(*) as num from articulo where day(FCreacion)=day(now()) and month(FCreacion)=month(now());";
	public static String NUM_ARTICULO_MES="select count(*) as num from articulo where month(FCreacion)=month(now());";
	public static String NUM_ARTICULO_TOTAL="select count(*) as num from articulo;";
	
	public static String NUM_PIEZA_HOY="select count(*) as num from pieza where day(FCreacion)=day(now()) and month(FCreacion)=month(now());";
	public static String NUM_PIEZA_MES="select count(*) as num from pieza where month(FCreacion)=month(now());";
	public static String NUM_PIEZA_TOTAL="select count(*) as num from pieza;";
	
	public static String NUM_CLIENTE_HOY="select count(*) as num from cliente where day(FCreacion)=day(now()) and month(FCreacion)=month(now());";
	public static String NUM_CLIENTE_MES="select count(*) as num from cliente where month(FCreacion)=month(now());";
	public static String NUM_CLIENTE_TOTAL="select count(*) as num from cliente;";
	
	public static String NUM_USUARIO_HOY="select count(*) as num from usuario where day(FCreacion)=day(now()) and month(FCreacion)=month(now());";
	public static String NUM_USUARIO_MES="select count(*) as num from usuario where month(FCreacion)=month(now());";
	public static String NUM_USUARIO_TOTAL="select count(*) as num from usuario;";
		
	public static String NUM_EGRESO_HOY="select count(*) as num from egreso where day(FCreacion)=day(now()) and month(FCreacion)=month(now());";
	public static String NUM_EGRESO_MES="select count(*) as num from egreso where month(FCreacion)=month(now());";
	public static String NUM_EGRESO_TOTAL="select count(*) as num from egreso;";
	
	public static String NUM_PROVEEDOR_HOY="select count(*) as num from proveedor where day(FCreacion)=day(now()) and month(FCreacion)=month(now());";
	public static String NUM_PROVEEDOR_MES="select count(*) as num from proveedor where month(FCreacion)=month(now());";
	public static String NUM_PROVEEDOR_TOTAL="select count(*) as num from proveedor;";
	
	public static String NUM_IMPORTANTE_HOY="select count(*) as num from importante where day(FCreacion)=day(now()) and month(FCreacion)=month(now());";
	public static String NUM_IMPORTANTE_MES="select count(*) as num from importante where month(FCreacion)=month(now());";
	public static String NUM_IMPORTANTE_TOTAL="select count(*) as num from importante;";
			
	public int numeroDB(String query){
		int num = -1;
		String url = query;  
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			pst = conexionServidor.prepareStatement(url);
	        rs=pst.executeQuery();
	        if (rs.next()) {
				num=rs.getInt("num");
			}
	        noError=1;
	     }catch(SQLException e){
	    	 e.printStackTrace();
	         noError=0;
	     }finally{
	  	 try{
	  		 if(pst!=null){
	  			 pst.close();
			 }
	  		if(rs!=null){
	  			 pst.close();
			 }
	  	   }catch (SQLException e) {
	  		   e.printStackTrace();
		   }
	     }
		return num;
	}
}
