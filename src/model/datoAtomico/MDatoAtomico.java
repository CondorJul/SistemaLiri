package model.datoAtomico;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dal.DatoAtomico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MPadre;

public class MDatoAtomico extends MPadre{
	public static final String QUERY_INSERT_FAMILIA="INSERT INTO FAMILIA(nombre) values (?);";
	public static final String QUERY_SELECT_FAMILIA="SELECT Id, Nombre FROM FAMILIA where Id=?";
	public static final String QUERY_SELECT_FAMILIAS="SELECT Id, Nombre FROM FAMILIA";
	
	public static final String QUERY_INSERT_TIPO_MANT="INSERT INTO TIPO_MANTENIMIENTO(nombre) values (?);";
	public static final String QUERY_SELECT_TIPO_MANT="SELECT Id, Nombre FROM TIPO_MANTENIMIENTO where Id=?";
	public static final String QUERY_SELECT_TIPO_MANTS="SELECT Id, Nombre FROM TIPO_MANTENIMIENTO";
	
	DatoAtomico datoAtomico=new DatoAtomico();
	
	public DatoAtomico getDatoAtomico() {
		return datoAtomico;
	}


	public void setDatoAtomico(DatoAtomico datoAtomico) {
		this.datoAtomico = datoAtomico;
	}


	public int insertarDatoAtomico(DatoAtomico datoAtomico, String queryIns){
		  int idUltimo=-1;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement(queryIns,PreparedStatement.RETURN_GENERATED_KEYS);

			  pst.setString(1,(datoAtomico.getNombre()!=null && !datoAtomico.getNombre().trim().isEmpty())?datoAtomico.getNombre().trim():(String)null);

			  pst.executeUpdate();
			  
			  rs=pst.getGeneratedKeys();
			  while(rs.next()){
				  this.datoAtomico.setId(rs.getInt(1));
				  this.datoAtomico.setNombre(datoAtomico.getNombre());
				  idUltimo=rs.getInt(1);
			  }
			  this.setNoError(CORRECTO);
			  this.setMensaje(MEN_INSERT_CORRECTO);
       }   
       catch(SQLException e){

    	   this.setNoError(INCORRECTO);
    	   this.setMensaje(MEN_INSERT_INCORRECTO+e.getMessage());
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
		 
		return idUltimo;
	}
	

	public DatoAtomico seleccionarDatoAtomico(int daAId, String querySelec){
			DatoAtomico datoAtomico=null;		
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement(querySelec);
		 	   pst.setInt(1, daAId);
			   rs=pst.executeQuery();
			   
			   while(rs.next()){
				   datoAtomico=new DatoAtomico();
				   datoAtomico.setNombre(rs.getString("Nombre"));

			   }
			  
         }   
         catch(SQLException e){
      	   

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
		
		return datoAtomico;
	}
	
		public ObservableList<DatoAtomico> seleccionarDatosAtomicos(String querySelec){
			ObservableList<DatoAtomico> arrayObservableList=FXCollections.observableArrayList();
			DatoAtomico datoAtomico=null;		
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement(querySelec);
			   rs=pst.executeQuery();
			   
			   while(rs.next()){
				   datoAtomico=new DatoAtomico();
				   datoAtomico.setId(rs.getInt("Id"));
				   datoAtomico.setNombre(rs.getString("Nombre"));
				   arrayObservableList.add(datoAtomico);
	
			   }
			  
	     }   
	     catch(SQLException e){
	  	   
	
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
		
		return arrayObservableList;
	}
	
}
