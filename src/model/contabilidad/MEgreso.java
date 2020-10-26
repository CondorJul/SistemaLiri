 package model.contabilidad;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import dal.Egreso;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MPadre;
import sesion.Sesion;

public class MEgreso extends MPadre{
	
	private Egreso egreso=null;
	
	public int insertarEgreso(Egreso egreso){
		int idUltimo = -1;
		this.egreso = egreso;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement("INSERT INTO egreso(TipoDocumento, NombreRazon, SerieDoc, NroDoc, Ruc, FechaEmision, Monto, Comentarios, IdConcepto, CreadoPor, "
			  		+ "ModificadoPor) VALUES (?,?,?,?,?,?,?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
			  
			  if(egreso.getEgreTipoDoc()!=-1){
           	   	  pst.setInt(1, egreso.getEgreTipoDoc());
              }else{
            	  pst.setNull(1,java.sql.Types.INTEGER);
              }
			  
			  if(egreso.getEgreNombreRazon()!=null && !egreso.getEgreNombreRazon().trim().isEmpty()){
           	   	  pst.setString(2, egreso.getEgreNombreRazon());;
              }else{
            	  pst.setNull(2, (java.sql.Types.VARCHAR));
              }
			  if (egreso.getEgreSerieDoc()!=null && !egreso.getEgreSerieDoc().trim().isEmpty()) {
				  pst.setString(3, egreso.getEgreSerieDoc());
			  } else {
				  pst.setNull(3, (java.sql.Types.VARCHAR));
			  }
			  if (egreso.getEgreNroDoc()!=null && !egreso.getEgreNroDoc().trim().isEmpty()) {
				  pst.setString(4, egreso.getEgreNroDoc());
			  } else {
				  pst.setNull(4, (java.sql.Types.VARCHAR));
			  }
			  if (egreso.getEgreRuc()!=null && !egreso.getEgreRuc().trim().isEmpty()) {
				  pst.setString(5, egreso.getEgreRuc());
			  } else {
				  pst.setNull(5, (java.sql.Types.VARCHAR));
			  }
			  if(egreso.getEgreFechaEmision()!=null){
           	   	  pst.setDate(6, new java.sql.Date(egreso.getEgreFechaEmision().getTime()));
              }else{
            	  pst.setDate(6, (java.sql.Date)null);
              }
			  if(egreso.getEgreMonto()!=null && !egreso.getEgreMonto().trim().isEmpty()){
           	   	  pst.setString(7, egreso.getEgreMonto());
              }else{
            	  pst.setNull(7, (java.sql.Types.DOUBLE));
              }
			  if (egreso.getEgreComentarios()!=null && !egreso.getEgreComentarios().trim().isEmpty()) {
				pst.setString(8, egreso.getEgreComentarios());
			  }else {
				 pst.setNull(8, (java.sql.Types.VARCHAR));
			  }
			  if(egreso.getEgreIdConcepto()!=-1){
           	   	  pst.setInt(9, egreso.getEgreIdConcepto());
              }else{
            	  pst.setNull(9,java.sql.Types.INTEGER);
              }
			  
			  pst.setString(10, Sesion.DNI);
       	   	  pst.setString(11, Sesion.DNI);
			  
			  pst.executeUpdate();
			  
			  rs=pst.getGeneratedKeys();
			  while(rs.next()){
				  this.egreso.setEgreId(rs.getInt(1));;
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
	
	public void actualizarEgreso(Egreso egreso){
		this.egreso = egreso;  
		String url = "UPDATE egreso SET TipoDocumento=?, NombreRazon=?, SerieDoc=?, NroDoc=?, Ruc=?, FechaEmision=?, Monto=?, Comentarios=?, IdConcepto=?, ModificadoPor=? WHERE Id=?";
		PreparedStatement pst = null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement(url);
			  
			  if(egreso.getEgreTipoDoc()!=-1){
           	   	  pst.setInt(1, egreso.getEgreTipoDoc());
              }else{
            	  pst.setNull(1,java.sql.Types.INTEGER);
              }
			  
			  if(egreso.getEgreNombreRazon()!=null && !egreso.getEgreNombreRazon().trim().isEmpty()){
           	   	  pst.setString(2, egreso.getEgreNombreRazon());;
              }else{
            	  pst.setNull(2, (java.sql.Types.VARCHAR));
              }
			  if (egreso.getEgreSerieDoc()!=null && !egreso.getEgreSerieDoc().trim().isEmpty()) {
				  pst.setString(3, egreso.getEgreSerieDoc());
			  } else {
				  pst.setNull(3, (java.sql.Types.VARCHAR));
			  }
			  if (egreso.getEgreNroDoc()!=null && !egreso.getEgreNroDoc().trim().isEmpty()) {
				  pst.setString(4, egreso.getEgreNroDoc());
			  } else {
				  pst.setNull(4, (java.sql.Types.VARCHAR));
			  }
			  if (egreso.getEgreRuc()!=null && !egreso.getEgreRuc().trim().isEmpty()) {
				  pst.setString(5, egreso.getEgreRuc());
			  } else {
				  pst.setNull(5, (java.sql.Types.VARCHAR));
			  }
			  if(egreso.getEgreFechaEmision()!=null){
           	   	  pst.setDate(6, new java.sql.Date(egreso.getEgreFechaEmision().getTime()));
              }else{
            	  pst.setDate(6, (java.sql.Date)null);
              }
			  if(egreso.getEgreMonto()!=null && !egreso.getEgreMonto().trim().isEmpty()){
           	   	  pst.setString(7, egreso.getEgreMonto());
              }else{
            	  pst.setNull(7, (java.sql.Types.DOUBLE));
              }
			  if (egreso.getEgreComentarios()!=null && !egreso.getEgreComentarios().trim().isEmpty()) {
				pst.setString(8, egreso.getEgreComentarios());
			  }else {
				 pst.setNull(8, (java.sql.Types.VARCHAR));
			  }
			  if(egreso.getEgreIdConcepto()!=-1){
           	   	  pst.setInt(9, egreso.getEgreIdConcepto());
              }else{
            	  pst.setNull(9,java.sql.Types.INTEGER);
              }
			  
			  pst.setString(10, Sesion.DNI);
			  pst.setInt(11, egreso.getEgreId());
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
	
	public void eliminarEgreso(int id) {
		String url = "DELETE FROM egreso WHERE Id=?;";
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
		
	public ObservableList<Egreso> seleccionarColeccionEgreso(){
		ObservableList<Egreso> arrayListEgreso=FXCollections.observableArrayList();
		Egreso egreso = null;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			 pst=conexionServidor.prepareStatement("SELECT Id, CASE TipoDocumento WHEN 1 THEN 'Boleta'"
			 		+ "WHEN 2 THEN 'Factura'"
			 				+ "WHEN 3 THEN 'Ticket' END AS Documento, NombreRazon, SerieDoc, NroDoc, Ruc, (SELECT Concepto FROM concepto WHERE Id=egreso.IdConcepto) AS descripcion, FechaEmision, Monto, Comentarios, CreadoPor, ModificadoPor, FCreacion, FModificacion FROM egreso;"); 
			 rs=pst.executeQuery();

			while(rs.next()){

				egreso =new Egreso();
				egreso.setEgreId(rs.getInt("Id"));
				egreso.setEgreDocumento(rs.getString("Documento"));
				egreso.setEgreNombreRazon(rs.getString("NombreRazon"));
				egreso.setEgreSerieDoc(rs.getString("SerieDoc"));
				egreso.setEgreNroDoc(rs.getString("NroDoc"));
				egreso.setEgreRuc(rs.getString("Ruc"));
				egreso.setEgreDescripcion(rs.getString("descripcion"));
				egreso.setEgreFechaEmision(rs.getDate("FechaEmision"));
				egreso.setEgreMonto(rs.getString("Monto"));
				egreso.setEgreComentarios(rs.getString("Comentarios"));
				egreso.setCreadoPor(rs.getString("CreadoPor"));
				egreso.setModificadoPor(rs.getString("ModificadoPor"));
				egreso.setfCreacion(rs.getDate("FCreacion"));
				egreso.setfModificacion(rs.getDate("FModificacion"));
				arrayListEgreso.add(egreso);
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
		  return arrayListEgreso;		
	}
	
	public ObservableList<Egreso> BuscarBDEgreso(String buscar, LocalDate fechaInicio, LocalDate fechaFin){
		ObservableList<Egreso> arrayListEgreso=FXCollections.observableArrayList();
		Egreso egreso = null;	
		String url = "SELECT Id, CASE TipoDocumento WHEN 1 THEN 'Boleta'"
			 				+ "WHEN 2 THEN 'Factura'"
			 				+ "WHEN 3 THEN 'Ticket' END AS Documento, NombreRazon, SerieDoc, NroDoc, Ruc, "
			 				+ "(SELECT Concepto FROM concepto WHERE Id=egreso.IdConcepto) AS descripcion, FechaEmision, Monto, Comentarios, "
			 				+ "CreadoPor, ModificadoPor, FCreacion, FModificacion FROM egreso "
				+ "WHERE ((SELECT Concepto FROM concepto WHERE Id=egreso.IdConcepto) LIKE ? OR NombreRazon LIKE ? OR SerieDoc LIKE ? OR NroDoc LIKE ?) AND  FechaEmision BETWEEN ? AND ?;";  
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
	        pst = conexionServidor.prepareStatement(url);
	        pst.setString(1, "%" + buscar + "%");
	        pst.setString(2, "%" + buscar + "%");
	        pst.setString(3, "%" + buscar + "%");
	        pst.setString(4, "%" + buscar + "%");
	        pst.setDate(5, java.sql.Date.valueOf(fechaInicio));
			 pst.setDate(6, java.sql.Date.valueOf(fechaFin));
	        rs = pst.executeQuery();
	        
	        while(rs.next()){
	        	egreso =new Egreso();
				egreso.setEgreId(rs.getInt("Id"));
				egreso.setEgreDocumento(rs.getString("Documento"));
				egreso.setEgreNombreRazon(rs.getString("NombreRazon"));
				egreso.setEgreSerieDoc(rs.getString("SerieDoc"));
				egreso.setEgreNroDoc(rs.getString("NroDoc"));
				egreso.setEgreRuc(rs.getString("Ruc"));
				egreso.setEgreDescripcion(rs.getString("descripcion"));
				egreso.setEgreFechaEmision(rs.getDate("FechaEmision"));
				egreso.setEgreMonto(rs.getString("Monto"));
				egreso.setEgreComentarios(rs.getString("Comentarios"));
				egreso.setCreadoPor(rs.getString("CreadoPor"));
				egreso.setModificadoPor(rs.getString("ModificadoPor"));
				egreso.setfCreacion(rs.getDate("FCreacion"));
				egreso.setfModificacion(rs.getDate("FModificacion"));
				arrayListEgreso.add(egreso); 
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
		return arrayListEgreso;
	}
		
	public Egreso seleccionarEgreso(int Id){
		Egreso egreso = null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			pst=conexionServidor.prepareStatement("SELECT Id, TipoDocumento, NombreRazon, SerieDoc, NroDoc, Ruc, FechaEmision, Monto, Comentarios, (SELECT Concepto FROM concepto WHERE Id=egreso.IdConcepto) AS descripcion, IdConcepto, "
			 		+ "CreadoPor, ModificadoPor, FCreacion, FModificacion FROM egreso WHERE Id=?;");
	 	   	pst.setInt(1, Id);
	 	   	rs=pst.executeQuery();
		   
		   while(rs.next()){
			   
			   	egreso =new Egreso();
				egreso.setEgreId(rs.getInt("Id"));
				egreso.setEgreTipoDoc(rs.getInt("TipoDocumento"));
				egreso.setEgreNombreRazon(rs.getString("NombreRazon"));
				egreso.setEgreSerieDoc(rs.getString("SerieDoc"));
				egreso.setEgreNroDoc(rs.getString("NroDoc"));
				egreso.setEgreRuc(rs.getString("Ruc"));
				egreso.setEgreDescripcion(rs.getString("Descripcion"));
				egreso.setEgreFechaEmision(rs.getDate("FechaEmision"));
				egreso.setEgreMonto(rs.getString("Monto"));
				egreso.setEgreComentarios(rs.getString("Comentarios"));
				egreso.setEgreIdConcepto(rs.getInt("IdConcepto"));
				egreso.setCreadoPor(rs.getString("CreadoPor"));
				egreso.setModificadoPor(rs.getString("ModificadoPor"));
				egreso.setfCreacion(rs.getDate("FCreacion"));
				egreso.setfModificacion(rs.getDate("FModificacion"));
			   
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
		
		return egreso;
	}
}
