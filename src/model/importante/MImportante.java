package model.importante;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import dal.Importante;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MPadre;
import sesion.Sesion;

public class MImportante extends MPadre{
	
	private Importante importante = null;
	
	public int insertarImportante(Importante importante){
		int idUltimo = -1;
		this.importante = importante;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement("INSERT INTO importante(Codigo, Descripcion, FechaFin, Talla, Cantidad, vecesBusqueda, Comentario, CreadoPor, "
			  		+ "ModificadoPor) VALUES (?,?,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			  
			  if (importante.getImpCodigo()!=null && !importante.getImpCodigo().trim().isEmpty()) {
				  pst.setString(1, importante.getImpCodigo());
			  } else {
				  pst.setNull(1,java.sql.Types.VARCHAR);
			  }
			  if(importante.getImpDescripcion()!=null && !importante.getImpDescripcion().trim().isEmpty()){
           	   	  pst.setString(2, importante.getImpDescripcion());
              }else{
            	  pst.setNull(2,java.sql.Types.VARCHAR);
              }
			  
			  if(importante.getImpFechaFin()!=null){
           	   	  pst.setDate(3, new java.sql.Date(importante.getImpFechaFin().getTime()));
              }else{
            	  pst.setDate(3, (java.sql.Date)null);
              }
			  
			  if(importante.getImpTalla()!=null && !importante.getImpTalla().trim().isEmpty()){
           	   	  pst.setString(4, importante.getImpTalla().trim());
              }else{
            	  pst.setNull(4, java.sql.Types.VARCHAR);
              }
			  
			  if(importante.getImpCantidad()!=-1){
           	   	  pst.setInt(5, importante.getImpCantidad());
              }else{
            	  pst.setNull(5, java.sql.Types.INTEGER);
              }
			  
			  if(importante.getImpDemanda()!=-1){
           	   	  pst.setInt(6, importante.getImpDemanda());
              }else{
            	  pst.setNull(6, java.sql.Types.INTEGER);
              }
		
			  if(importante.getImpComentario()!=null && !importante.getImpComentario().trim().isEmpty()){
           	   	  pst.setString(7, importante.getImpComentario());
              }else{
            	  pst.setNull(7 ,java.sql.Types.VARCHAR);
              }		  
			  pst.setString(8, Sesion.DNI);
       	   	  pst.setString(9, Sesion.DNI);
			  
			  pst.executeUpdate();
			  
			  rs=pst.getGeneratedKeys();
			  while(rs.next()){
				  this.importante.setImpId(rs.getInt(1));
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
	
	public void actualizarImportante(Importante importante){
		this.importante = importante;  
		String url = "UPDATE importante SET Descripcion=?, FechaFin=?, Talla=?, Cantidad=?, Comentario=?, ModificadoPor=? WHERE Id=?";
		PreparedStatement pst = null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement(url);
			  
			  if(importante.getImpDescripcion()!=null && !importante.getImpDescripcion().trim().isEmpty()){
         	   	  pst.setString(1, importante.getImpDescripcion());
			  }else{
				  pst.setNull(1,java.sql.Types.VARCHAR);
			  }
			  
			  if(importante.getImpFechaFin()!=null){
         	   	  pst.setDate(2, new java.sql.Date(importante.getImpFechaFin().getTime()));
			  }else{
          	  pst.setDate(2, (java.sql.Date)null);
			  }
			  
			  if(importante.getImpTalla()!=null && !importante.getImpTalla().trim().isEmpty()){
           	   	  pst.setString(3, importante.getImpTalla().trim());
              }else{
            	  pst.setNull(3, java.sql.Types.VARCHAR);;
              }
			  
			  if(importante.getImpCantidad()!=-1){
           	   	  pst.setInt(4, importante.getImpCantidad());
              }else{
            	  pst.setNull(4,java.sql.Types.INTEGER);
              }
		
			  if(importante.getImpComentario()!=null && !importante.getImpComentario().trim().isEmpty()){
           	   	  pst.setString(5, importante.getImpComentario());
              }else{
            	  pst.setNull(5,java.sql.Types.VARCHAR);
              }		  
			  
			  pst.setString(6, Sesion.DNI);
			  pst.setInt(7, importante.getImpId());
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
	
	public void eliminarImportante(int id) {
		String url = "DELETE FROM importante WHERE Id=?;";
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
		
	public ObservableList<Importante> seleccionarColeccionImportante(){
		ObservableList<Importante> arrayListImportante=FXCollections.observableArrayList();
		Importante importante = null;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			 pst=conexionServidor.prepareStatement("SELECT Id, Codigo, Descripcion, FechaInicio, FechaFin, Talla, Cantidad, vecesBusqueda, Comentario, "
			 		+ "CreadoPor, ModificadoPor, FCreacion, FModificacion FROM importante;"); 
			rs=pst.executeQuery();

			while(rs.next()){

				importante =new Importante();
				importante.setImpId(rs.getInt("Id"));
				importante.setImpCodigo(rs.getString("Codigo"));
				importante.setImpDescripcion(rs.getString("Descripcion"));
				importante.setImpFechaInicio(rs.getDate("FechaInicio"));
				importante.setImpFechaFin(rs.getDate("FechaFin"));
				importante.setImpTalla(rs.getString("Talla"));
				importante.setImpCantidad(rs.getInt("Cantidad"));
				importante.setImpDemanda(rs.getInt("vecesBusqueda"));
				importante.setImpComentario(rs.getString("Comentario"));
				importante.setCreadoPor(rs.getString("CreadoPor"));
				importante.setModificadoPor(rs.getString("ModificadoPor"));
				importante.setfCreacion(rs.getDate("FCreacion"));
				importante.setfModificacion(rs.getDate("FModificacion"));
				arrayListImportante.add(importante);
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
		  return arrayListImportante;		
	}
	
	public ObservableList<Importante> BuscarBDImportante(String buscar){
		ObservableList<Importante> arrayListImportante=FXCollections.observableArrayList();
		Importante importante = null;	
		String url = "SELECT Id, Codigo, Descripcion, FechaInicio, FechaFin, Talla, Cantidad, vecesBusqueda, Comentario, CreadoPor, ModificadoPor, FCreacion, FModificacion FROM importante "
				+ "WHERE Codigo LIKE ? OR Descripcion LIKE ? OR Talla LIKE ?;";  
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
	        pst = conexionServidor.prepareStatement(url);
	        pst.setString(1, "%" + buscar + "%");
	        pst.setString(2, "%" + buscar + "%");
	        pst.setString(3, "%" + buscar + "%");
	        rs = pst.executeQuery();
	        
	        while(rs.next()){
	        	importante =new Importante();
				importante.setImpId(rs.getInt("Id"));
				importante.setImpCodigo(rs.getString("Codigo"));
				importante.setImpDescripcion(rs.getString("Descripcion"));
				importante.setImpFechaInicio(rs.getDate("FechaInicio"));
				importante.setImpFechaFin(rs.getDate("FechaFin"));
				importante.setImpTalla(rs.getString("Talla"));
				importante.setImpCantidad(rs.getInt("Cantidad"));
				importante.setImpDemanda(rs.getInt("vecesBusqueda"));
				importante.setImpComentario(rs.getString("Comentario"));
				importante.setCreadoPor(rs.getString("CreadoPor"));
				importante.setModificadoPor(rs.getString("ModificadoPor"));
				importante.setfCreacion(rs.getDate("FCreacion"));
				importante.setfModificacion(rs.getDate("FModificacion"));
				arrayListImportante.add(importante);  
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
		return arrayListImportante;
	}
		
	public Importante seleccionarImportante(int Id){
		Importante importante = null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			pst=conexionServidor.prepareStatement("SELECT Id, Codigo, Descripcion, FechaInicio, FechaFin, Talla, Cantidad, vecesBusqueda, Comentario, "
			 		+ "CreadoPor, ModificadoPor, FCreacion, FModificacion FROM importante WHERE id=?;");
	 	   	pst.setInt(1, Id);
	 	   	rs=pst.executeQuery();
		   
		   while(rs.next()){
			   
			   importante =new Importante();
			   importante.setImpId(rs.getInt("Id"));
			   importante.setImpCodigo(rs.getString("Codigo"));
			   importante.setImpDescripcion(rs.getString("Descripcion"));
			   importante.setImpFechaInicio(rs.getDate("FechaInicio"));
			   importante.setImpFechaFin(rs.getDate("FechaFin"));
			   importante.setImpTalla(rs.getString("Talla"));
			   importante.setImpCantidad(rs.getInt("Cantidad"));
			   importante.setImpDemanda(rs.getInt("vecesBusqueda"));
			   importante.setImpComentario(rs.getString("Comentario"));
			   importante.setCreadoPor(rs.getString("CreadoPor"));
			   importante.setModificadoPor(rs.getString("ModificadoPor"));
			   importante.setfCreacion(rs.getDate("FCreacion"));
			   importante.setfModificacion(rs.getDate("FModificacion"));
			   
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
		
		return importante;
	}
	
	public ObservableList<Importante> seleccionarIntervaloFechasImportante(LocalDate fechaInicio, LocalDate fechaFin){
		ObservableList<Importante> arrayListIntervaloImportante=FXCollections.observableArrayList();
		Importante importante = null;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			 pst=conexionServidor.prepareStatement("SELECT * FROM importante WHERE FechaInicio BETWEEN ? AND ?"); 
			 pst.setDate(1, java.sql.Date.valueOf(fechaInicio));
			 pst.setDate(2, java.sql.Date.valueOf(fechaFin));
		 	 rs=pst.executeQuery();

			while(rs.next()){

				importante =new Importante();
				importante.setImpId(rs.getInt("Id"));
				importante.setImpCodigo(rs.getString("Codigo"));
				importante.setImpDescripcion(rs.getString("Descripcion"));
				importante.setImpFechaInicio(rs.getDate("FechaInicio"));
				importante.setImpFechaFin(rs.getDate("FechaFin"));
				importante.setImpTalla(rs.getString("Talla"));
				importante.setImpCantidad(rs.getInt("Cantidad"));
				importante.setImpDemanda(rs.getInt("vecesBusqueda"));
				importante.setImpComentario(rs.getString("Comentario"));
				importante.setCreadoPor(rs.getString("CreadoPor"));
				importante.setModificadoPor(rs.getString("ModificadoPor"));
				importante.setfCreacion(rs.getDate("FCreacion"));
				importante.setfModificacion(rs.getDate("FModificacion"));
				arrayListIntervaloImportante.add(importante);
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
		  return arrayListIntervaloImportante;		
	}
	
	public void contarVecesBusqueda(int id) {
		String url = "UPDATE importante SET vecesBusqueda=vecesBusqueda+1 WHERE Id=?;";
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
	
	public String traerCodigoImportante(){
		String imp = null;
		String url = "SELECT IFNULL(CONCAT(LEFT(MAX(Codigo), 1), LPAD(RIGHT(MAX(Codigo), 3)+1, 4, '0')), 'I0001') AS COD from importante;";  
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			pst = conexionServidor.prepareStatement(url);
	        rs=pst.executeQuery();
	        while(rs.next()){
	        	imp = rs.getString("COD");
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
		return imp;
	}
	
}
