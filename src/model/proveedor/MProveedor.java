package model.proveedor;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dal.Proveedor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MPadre;
import sesion.Sesion;

public class MProveedor extends MPadre{
	
	private Proveedor proveedor=null;
	
	public int insertarProveedor(Proveedor proveedor){
		int idUltimo = -1;
		this.proveedor=proveedor;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement("INSERT INTO proveedor(PrvTelefono, PrvArticulo, PrvDireccion,"
			  		+ " PrvNombre, PrvEMail, PrvComentarios, CreadoPor, ModificadoPor) VALUES (?,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			  
			  if(proveedor.getTelefono()!=null && !proveedor.getTelefono().trim().isEmpty()){
           	   	  pst.setString(1, proveedor.getTelefono());
              }else{
            	  pst.setNull(1,java.sql.Types.VARCHAR);
              }
			  
			  if(proveedor.getArticulo()!=null && !proveedor.getArticulo().trim().isEmpty()){
           	   	  pst.setString(2, proveedor.getArticulo());
              }else{
            	  pst.setNull(2,java.sql.Types.VARCHAR);
              }
			  
			  if(proveedor.getDireccion()!=null && !proveedor.getDireccion().trim().isEmpty()){
           	   	  pst.setString(3, proveedor.getDireccion());
              }else{
            	  pst.setNull(3,java.sql.Types.VARCHAR);
              }
			  
			  if(proveedor.getNombre()!=null && !proveedor.getNombre().trim().isEmpty()){
           	   	  pst.setString(4, proveedor.getNombre());
              }else{
            	  pst.setNull(4,java.sql.Types.VARCHAR);
              }
			  
			  if(proveedor.getEmail()!=null && !proveedor.getEmail().trim().isEmpty()){
           	   	  pst.setString(5, proveedor.getEmail());
              }else{
            	  pst.setNull(5,java.sql.Types.VARCHAR);
              }
		
			  if(proveedor.getComentario()!=null && !proveedor.getComentario().trim().isEmpty()){
           	   	  pst.setString(6, proveedor.getComentario());
              }else{
            	  pst.setNull(6,java.sql.Types.VARCHAR);
              }
			  
			  pst.setString(7, Sesion.DNI);
       	   	  pst.setString(8, Sesion.DNI);
			  
			  pst.executeUpdate();
			  
			  rs=pst.getGeneratedKeys();
			  while(rs.next()){
				  this.proveedor.setId(rs.getInt(1));
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
	
	public void actualizarProveedor(Proveedor proveedor){
		this.proveedor=proveedor;  
		String url = "UPDATE PROVEEDOR SET PrvTelefono=?, PrvArticulo=?, PrvDireccion=?, PrvNombre=?, PrvEMail=?, PrvComentarios=?, ModificadoPor=? WHERE PrvId=?";
		PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement(url);
			  
			  if(proveedor.getTelefono()!=null && !proveedor.getTelefono().trim().isEmpty()){
         	   	  pst.setString(1, proveedor.getTelefono());
			  }else{
          	  pst.setNull(1,java.sql.Types.VARCHAR);
			  }
			  
			  if(proveedor.getArticulo()!=null && !proveedor.getArticulo().trim().isEmpty()){
         	   	  pst.setString(2, proveedor.getArticulo());
			  }else{
          	  pst.setNull(2,java.sql.Types.VARCHAR);
			  }
			  
			  if(proveedor.getDireccion()!=null && !proveedor.getDireccion().trim().isEmpty()){
         	   	  pst.setString(3, proveedor.getDireccion());
			  }else{
				  pst.setNull(3,java.sql.Types.VARCHAR);
			  }
			  
			  if(proveedor.getNombre()!=null && !proveedor.getNombre().trim().isEmpty()){
         	   	  pst.setString(4, proveedor.getNombre());
			  }else{
          	  pst.setNull(4,java.sql.Types.VARCHAR);
			  }
			  
			  if(proveedor.getEmail()!=null && !proveedor.getEmail().trim().isEmpty()){
         	   	  pst.setString(5, proveedor.getEmail());
			  }else{
          	  pst.setNull(5,java.sql.Types.VARCHAR);
			  }
		
			  if(proveedor.getComentario()!=null && !proveedor.getComentario().trim().isEmpty()){
         	   	  pst.setString(6, proveedor.getComentario());
			  }else{
				  pst.setNull(6,java.sql.Types.VARCHAR);
			  }
			 
			  pst.setString(7, Sesion.DNI);
			  pst.setInt(8, proveedor.getId());
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
	
	public void eliminarProveedor(int id) {
		String url = "DELETE FROM proveedor WHERE PrvId=?;";
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
		
	public ObservableList<Proveedor> seleccionarColeccionProveedor(){
		ObservableList<Proveedor> arrayListProveedor=FXCollections.observableArrayList();
		Proveedor proveedor = null;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			 pst=conexionServidor.prepareStatement("SELECT PrvId, PrvCod, PrvNombre, PrvArticulo, PrvDireccion, PrvTelefono, PrvEmail, PrvComentarios, "
			 		+ "CreadoPor, ModificadoPor, FCreacion, FModificacion FROM proveedor;"); 
			rs=pst.executeQuery();

			while(rs.next()){

				proveedor =new Proveedor();
				proveedor.setId(rs.getInt("PrvId"));
				proveedor.setCodigo(rs.getString("PrvCod"));
				proveedor.setNombre(rs.getString("PrvNombre"));
				proveedor.setArticulo(rs.getString("PrvArticulo"));
				proveedor.setDireccion(rs.getString("PrvDireccion"));
				proveedor.setTelefono(rs.getString("PrvTelefono"));
				proveedor.setEmail(rs.getString("PrvEmail"));
				proveedor.setComentario(rs.getString("PrvComentarios"));
				proveedor.setCreadoPor(rs.getString("CreadoPor"));
				proveedor.setModificadoPor(rs.getString("ModificadoPor"));
				proveedor.setfCreacion(rs.getDate("FCreacion"));
				proveedor.setfModificacion(rs.getDate("FModificacion"));
				arrayListProveedor.add(proveedor);
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
		  return arrayListProveedor;		
	}
	
	public ObservableList<Proveedor> BuscarBDProveedor(String buscar){
		ObservableList<Proveedor> arrayListProveedor=FXCollections.observableArrayList();
		Proveedor proveedor = null;	
		String url = "SELECT PrvId, PrvCod, PrvNombre, PrvArticulo, PrvDireccion, PrvTelefono, PrvEmail, PrvComentarios, CreadoPor, ModificadoPor, FCreacion, FModificacion FROM PROVEEDOR "
				+ "WHERE PrvCod LIKE ? OR PrvNombre LIKE ? OR PrvArticulo LIKE ?;";  
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
	        pst = conexionServidor.prepareStatement(url);
	        pst.setString(1, "%" + buscar + "%");
	        pst.setString(2, "%" + buscar + "%");
	        pst.setString(3, "%" + buscar + "%");
	        rs = pst.executeQuery();
	        
	        while(rs.next()){
	        	proveedor =new Proveedor();
	        	proveedor.setId(rs.getInt("PrvId"));
				proveedor.setCodigo(rs.getString("PrvCod"));
				proveedor.setNombre(rs.getString("PrvNombre"));
				proveedor.setArticulo(rs.getString("PrvArticulo"));
				proveedor.setDireccion(rs.getString("PrvDireccion"));
				proveedor.setTelefono(rs.getString("PrvTelefono"));
				proveedor.setEmail(rs.getString("PrvEmail"));
				proveedor.setComentario(rs.getString("PrvComentarios"));
				proveedor.setCreadoPor(rs.getString("CreadoPor"));
				proveedor.setModificadoPor(rs.getString("ModificadoPor"));
				proveedor.setfCreacion(rs.getDate("FCreacion"));
				proveedor.setfModificacion(rs.getDate("FModificacion"));
				arrayListProveedor.add(proveedor);   
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
		return arrayListProveedor;
	}
		
	public Proveedor seleccionarProveedor(int prvId){
		Proveedor proveedor=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			pst=conexionServidor.prepareStatement("SELECT PrvId, PrvCod, PrvTelefono, PrvArticulo, PrvDireccion,"
		  		+ " PrvNombre, PrvEMail, PrvComentarios, "
		  		+ " CreadoPor, ModificadoPor, FCreacion, FModificacion FROM PROVEEDOR where PrvId=?");
	 	   	pst.setInt(1, prvId);
	 	   	rs=pst.executeQuery();
		   
		   while(rs.next()){
			   proveedor=new Proveedor();
			   proveedor.setId(rs.getInt("PrvId"));
			   proveedor.setCodigo(rs.getString("PrvCod"));
			   proveedor.setTelefono(rs.getString("PrvTelefono"));
			   proveedor.setArticulo(rs.getString("PrvArticulo"));
			   proveedor.setDireccion(rs.getString("PrvDireccion"));
			   proveedor.setNombre(rs.getString("PrvNombre"));
			   proveedor.setEmail(rs.getString("PrvEMail"));
			   proveedor.setComentario(rs.getString("PrvComentarios"));
			   proveedor.setCreadoPor(rs.getString("CreadoPor"));
			   proveedor.setModificadoPor(rs.getString("ModificadoPor"));
			   proveedor.setfCreacion(rs.getDate("FCreacion"));
			   proveedor.setfModificacion(rs.getDate("FModificacion"));
			   
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
		
		return proveedor;
	}
	
	public String traerCodigoProveedor(){
		String pro=null;
		String url = "SELECT IFNULL(CONCAT(LEFT(MAX(PrvCod), 1), LPAD(RIGHT(MAX(PrvCod), 3)+1, 4, '0')), 'P0001') AS COD from PROVEEDOR;";  
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			pst = conexionServidor.prepareStatement(url);
	        rs=pst.executeQuery();
	        while(rs.next()){
	        	pro = rs.getString("COD");
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
	  	   }catch (SQLException e) {
	  		   e.printStackTrace();
		   }
	  	   
	     }
		return pro;
	}
	
}
