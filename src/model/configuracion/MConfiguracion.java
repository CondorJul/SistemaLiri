package model.configuracion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dal.Cupon;
import model.MPadre;

public class MConfiguracion extends MPadre {
	
	private Cupon cupon = null;
		
	public Cupon seleccionarCupon(int Id){
		this.cupon = null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			pst=conexionServidor.prepareStatement("SELECT Id, valorActualPor FROM cupon WHERE id=?;");
	 	   	pst.setInt(1, Id);
	 	   	rs=pst.executeQuery();
		   
		   while(rs.next()){
			   
			   cupon =new Cupon();
			   cupon.setConfig_id(rs.getInt("Id"));
			   cupon.setConfig_cupon(rs.getDouble("valorActualPor"));
		   }
			  
         }   
         catch(SQLException e){
      	   e.printStackTrace();
		 }finally{
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
		
		return cupon;
	}
	
	public void actualizarCupon(Cupon cupon){
		String url = "UPDATE cupon SET valorActualPor=? WHERE Id=?";
		PreparedStatement pst = null;
		ResultSet rs=null;
		try{
			pst=conexionServidor.prepareStatement(url);
			 			  
			if(cupon.getConfig_cupon()!=-1){
           	   	  pst.setDouble(1, cupon.getConfig_cupon());
            }else{
            	  pst.setNull(1,java.sql.Types.DOUBLE);
            }
			pst.setInt(2, cupon.getConfig_id());
			pst.executeUpdate();
			
			this.setNoError(CORRECTO);
			this.setMensaje(MEN_UPDATE_CORRECTO);
			  
         }   
         catch(SQLException e){      	
        	 this.setNoError(INCORRECTO);
        	 this.setMensaje(MEN_UPDATE_INCORRECTO+e.getMessage());
        	 e.printStackTrace();
		  }finally{
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
	}
		
}
