package model.contabilidad;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dal.Concepto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MPadre;
import sesion.Sesion;

public class MConcepto extends MPadre{
	
	private Concepto concepto = null;
	
	public int insertarConcepto(Concepto concepto){
		int idUltimo = -1;
		this.concepto = concepto;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement("INSERT INTO concepto(Concepto, Monto, CreadoPor, "
			  		+ "ModificadoPor) VALUES (?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			  
			  if(concepto.getConConcepto()!=null && !concepto.getConConcepto().trim().isEmpty()){
           	   	  pst.setString(1, concepto.getConConcepto());
              }else{
            	  pst.setNull(1,java.sql.Types.VARCHAR);
              }
			  
			  if(concepto.getConMonto()!=-1.00){
           	   	  pst.setDouble(2, concepto.getConMonto());
              }else{
            	  pst.setNull(2, (java.sql.Types.DOUBLE));
              }		  
			  pst.setString(3, Sesion.DNI);
       	   	  pst.setString(4, Sesion.DNI);
			  
			  pst.executeUpdate();
			  
			  rs=pst.getGeneratedKeys();
			  while(rs.next()){
				  this.concepto.setConId(rs.getInt(1));
				  idUltimo=rs.getInt(1);
			  }
			  this.setNoError(CORRECTO);
			  this.setMensaje(MEN_INSERT_CORRECTO);
           }   
           catch(SQLException e){

        	   this.setNoError(INCORRECTO);
        	   this.setMensaje(MEN_INSERT_INCORRECTO+e.getMessage());
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
		return idUltimo;
	}
	
	public void actualizarConcepto(Concepto concepto){
		this.concepto = concepto;  
		String url = "UPDATE concepto SET Concepto=?, Monto=?, ModificadoPor=? WHERE Id=?";
		PreparedStatement pst = null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement(url);
			  
			  if(concepto.getConConcepto()!=null && !concepto.getConConcepto().trim().isEmpty()){
         	   	  pst.setString(1, concepto.getConConcepto());
			  }else{
				  pst.setNull(1,java.sql.Types.VARCHAR);
			  }
			  
			  if(concepto.getConMonto()!=-1.00){
         	   	  pst.setDouble(2, concepto.getConMonto());;
			  }else{
          	  pst.setNull(2, java.sql.Types.DOUBLE);
			  }

			  pst.setString(3, Sesion.DNI);
			  pst.setInt(4, concepto.getConId());
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
	
	public void eliminarConcepto(int id) {
		String url = "DELETE FROM concepto WHERE Id=?;";
		PreparedStatement  pst = null;
		
		try {
			pst=conexionServidor.prepareStatement(url);
			pst.setInt(1,id);
			pst.executeUpdate();
			
			this.setNoError(CORRECTO);
			this.setMensaje(MEN_DELETE_CORRECTO);
			
		} catch (Exception e) {
			this.setNoError(INCORRECTO);
       	 	this.setMensaje(MEN_DELETE_INCORRECTO+e.getMessage());
			e.printStackTrace();
		}finally{
			try{
				if(pst!=null){
					pst.close();
				}       			
   			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
		
	public ObservableList<Concepto> seleccionarColeccionConcepto(){
		ObservableList<Concepto> arrayListConcepto=FXCollections.observableArrayList();
		Concepto concepto = null;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			 pst=conexionServidor.prepareStatement("SELECT Id, Concepto, Monto, CreadoPor, ModificadoPor, FCreacion, FModificacion FROM concepto;"); 
			rs=pst.executeQuery();

			while(rs.next()){

				concepto =new Concepto();
				concepto.setConId(rs.getInt("Id"));
				concepto.setConConcepto(rs.getString("Concepto"));
				concepto.setConMonto(rs.getDouble("Monto"));
				concepto.setCreadoPor(rs.getString("CreadoPor"));
				concepto.setModificadoPor(rs.getString("ModificadoPor"));
				concepto.setfCreacion(rs.getDate("FCreacion"));
				concepto.setfModificacion(rs.getDate("FModificacion"));
				arrayListConcepto.add(concepto);
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
		  return arrayListConcepto;		
	}
	
	public ObservableList<Concepto> BuscarBDConcepto(String buscar){
		ObservableList<Concepto> arrayListConcepto=FXCollections.observableArrayList();
		Concepto concepto = null;	
		String url = "SELECT Id, Concepto, Monto, CreadoPor, ModificadoPor, FCreacion, FModificacion FROM concepto "
				+ "WHERE Concepto LIKE ?;";  
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
	        pst = conexionServidor.prepareStatement(url);
	        pst.setString(1, "%" + buscar + "%");
	        rs = pst.executeQuery();
	        
	        while(rs.next()){
	        	concepto =new Concepto();
				concepto.setConId(rs.getInt("Id"));
				concepto.setConConcepto(rs.getString("Concepto"));
				concepto.setConMonto(rs.getDouble("Monto"));
				concepto.setCreadoPor(rs.getString("CreadoPor"));
				concepto.setModificadoPor(rs.getString("ModificadoPor"));
				concepto.setfCreacion(rs.getDate("FCreacion"));
				concepto.setfModificacion(rs.getDate("FModificacion"));
				arrayListConcepto.add(concepto);  
	        }
	     }catch(SQLException e){
	         e.printStackTrace();
	     }finally{
		  	 try{
		  		 if(rs!=null){
		  			 rs.close(); 
		  		 }
		  		 if(pst!=null){
		  			 pst.close();
				 }
		  		 
		  	 }catch (SQLException e) {		
		  		 e.printStackTrace();
			 } 
	     }
		return arrayListConcepto;
	}
		
	public Concepto seleccionarConcepto(int Id){
		Concepto concepto = null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			pst=conexionServidor.prepareStatement("SELECT Id, Concepto, Monto,"
			 		+ "CreadoPor, ModificadoPor, FCreacion, FModificacion FROM concepto WHERE Id=?;");
	 	   	pst.setInt(1, Id);
	 	   	rs=pst.executeQuery();
		   
		   while(rs.next()){
			   
			   concepto =new Concepto();
			   concepto.setConId(rs.getInt("Id"));
			   concepto.setConConcepto(rs.getString("Concepto"));
			   concepto.setConMonto(rs.getDouble("Monto"));
			   concepto.setCreadoPor(rs.getString("CreadoPor"));
			   concepto.setModificadoPor(rs.getString("ModificadoPor"));
			   concepto.setfCreacion(rs.getDate("FCreacion"));
			   concepto.setfModificacion(rs.getDate("FModificacion"));
			   
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
		
		return concepto;
	}
	
}
