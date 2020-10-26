package model.reputacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dal.Reputacion;
import model.MPadre;
import sesion.Sesion;

public class MReputacion extends MPadre{
	
	public void actualizarReputacion(Reputacion reputacion){
		String url = "UPDATE reputacion SET VMaxRojo=?, VMaxAmbar=?, VMaxVerde=?, ModificadoPor=? WHERE Id=?";
		PreparedStatement pst = null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement(url);
			 			  
			  if(reputacion.getRepVMRojo()!=-1){
           	   	  pst.setInt(1, reputacion.getRepVMRojo());
              }else{
            	  pst.setNull(1,java.sql.Types.INTEGER);
              }  
			  
			  if(reputacion.getRepVMAmmbar()!=-1){
           	   	  pst.setInt(2, reputacion.getRepVMAmmbar());
              }else{
            	  pst.setNull(2,java.sql.Types.INTEGER);
              } 
			  
			  if(reputacion.getRepVMVerde()!=-1){
           	   	  pst.setInt(3, reputacion.getRepVMVerde());
              }else{
            	  pst.setNull(3,java.sql.Types.INTEGER);
              } 
			  
			  pst.setString(4, Sesion.DNI);
			  pst.setInt(5, reputacion.getRepId());
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
		
	public Reputacion seleccionarReputacion(int Id){
		Reputacion reputacion = null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			pst=conexionServidor.prepareStatement("SELECT Id, VMaxRojo, VMaxAmbar, VMaxVerde, "
			 		+ "CreadoPor, ModificadoPor, FCreacion, FModificacion FROM reputacion WHERE id=?;");
	 	   	pst.setInt(1, Id);
	 	   	rs=pst.executeQuery();
		   
		   while(rs.next()){
			   
			   reputacion =new Reputacion();
			   reputacion.setRepId(rs.getInt("Id"));
			   reputacion.setRepVMRojo(rs.getInt("VMaxRojo"));
			   reputacion.setRepVMAmmbar(rs.getInt("VMaxAmbar"));
			   reputacion.setRepVMVerde(rs.getInt("VMaxVerde"));
			   reputacion.setCreadoPor(rs.getString("CreadoPor"));
			   reputacion.setModificadoPor(rs.getString("ModificadoPor"));
			   reputacion.setfCreacion(rs.getDate("FCreacion"));
			   reputacion.setfModificacion(rs.getDate("FModificacion"));
			   
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
		
		return reputacion;
	}
		
}
