package model.articulo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dal.Articulo;
import dal.Pieza;
import model.MPadre;
import model.articulo.MArticulo.ImagenArticulo;
import sesion.Sesion;

public class MPieza extends MPadre {

	private Pieza pieza=null;
	public int insertarPieza(Pieza pieza){
		this.pieza=pieza;
		  int idUltimo=-1;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement("INSERT INTO Pieza(Descripcion,TMant,PrecioCompra,"
			  		+ "PrecioAlquiler,PrecioVenta, "+
				"ReqPlanchar,Estado,Stock,ArticuloId,"
				+ " CreadoPor,ModificadoPor) "+
				"VALUES(?,?,?,?,?,?,?,?,?,?,?);",PreparedStatement.RETURN_GENERATED_KEYS);
			  
			  	pst.setString(1, (pieza.getDescripcion()!=null && !pieza.getDescripcion().trim().isEmpty())?pieza.getDescripcion().trim():(String)null);
			  	
			  	if(pieza.getTipoMantenimiento()!=-1){
					pst.setInt(2, pieza.getTipoMantenimiento());
				}else{
					pst.setNull(2, java.sql.Types.INTEGER);
				}
			  	
			  	if(pieza.getPrecioCompra()!=-1.0){
					pst.setDouble(3, pieza.getPrecioCompra());
				}else{
					pst.setNull(3, java.sql.Types.DOUBLE);
				}
			  	
			  	if(pieza.getPrecioAlquiler()!=-1.0){
					pst.setDouble(4, pieza.getPrecioAlquiler());
				}else{
					pst.setNull(4, java.sql.Types.DOUBLE);
				}
			  	
			  	if(pieza.getPrecioVenta()!=-1.0){
					pst.setDouble(5, pieza.getPrecioVenta());
				}else{
					pst.setNull(5, java.sql.Types.DOUBLE);
				}
			  	
			  	if(pieza.getRequierePlanchar()!=-1){
					pst.setInt(6, pieza.getRequierePlanchar());
				}else{
					pst.setNull(6, java.sql.Types.INTEGER);
				}
			  	
			  	if(pieza.getEstado()!=-1){
					pst.setInt(7, pieza.getEstado());
				}else{
					pst.setNull(7, java.sql.Types.INTEGER);
				}
			  	
			  	if(pieza.getStock()!=-1){
					pst.setInt(8, pieza.getStock());
				}else{
					pst.setNull(8, java.sql.Types.INTEGER);
				}
			  	
			  	if(pieza.getIdArticulo()!=-1){
			  		pst.setInt(9, pieza.getIdArticulo());
			  	}else{
			  		pst.setNull(9, java.sql.Types.INTEGER);
			  	}
			  	
			  	
			  	
			  pst.setString(10, Sesion.DNI);
     	   	  pst.setString(11, Sesion.DNI);
			  
			  pst.executeUpdate();
			  
			  rs=pst.getGeneratedKeys();
			  while(rs.next()){
				  this.pieza.setId(rs.getInt(1));
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
	
	public boolean actualizarPieza(Pieza pieza){
		this.pieza=pieza;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement("UPDATE  Pieza set Descripcion=?,TMant=?,PrecioCompra=?, "
			  		+ "PrecioAlquiler=?, PrecioVenta=?, "+
				"ReqPlanchar=?,Estado=?,Stock=?,ArticuloId=?,"
				+ " ModificadoPor=?, fModificacion=now()  "+
				" where id=?;",PreparedStatement.RETURN_GENERATED_KEYS);
			  
			  	pst.setString(1, (pieza.getDescripcion()!=null && !pieza.getDescripcion().trim().isEmpty())?pieza.getDescripcion().trim():(String)null);
			  	
			  	if(pieza.getTipoMantenimiento()!=-1){
					pst.setInt(2, pieza.getTipoMantenimiento());
				}else{
					pst.setNull(2, java.sql.Types.INTEGER);
				}
			  	
			  	if(pieza.getPrecioCompra()!=-1.0){
					pst.setDouble(3, pieza.getPrecioCompra());
				}else{
					pst.setNull(3, java.sql.Types.DOUBLE);
				}
			  	
			  	if(pieza.getPrecioAlquiler()!=-1.0){
					pst.setDouble(4, pieza.getPrecioAlquiler());
				}else{
					pst.setNull(4, java.sql.Types.DOUBLE);
				}
			  	
			  	if(pieza.getPrecioVenta()!=-1.0){
					pst.setDouble(5, pieza.getPrecioVenta());
				}else{
					pst.setNull(5, java.sql.Types.DOUBLE);
				}
			  	
			  	if(pieza.getRequierePlanchar()!=-1){
					pst.setInt(6, pieza.getRequierePlanchar());
				}else{
					pst.setNull(6, java.sql.Types.INTEGER);
				}
			  	
			  	if(pieza.getEstado()!=-1){
					pst.setInt(7, pieza.getEstado());
				}else{
					pst.setNull(7, java.sql.Types.INTEGER);
				}
			  	
			  	if(pieza.getStock()!=-1){
					pst.setInt(8, pieza.getStock());
				}else{
					pst.setNull(8, java.sql.Types.INTEGER);
				}
			  	
			  	if(pieza.getIdArticulo()!=-1){
			  		pst.setInt(9, pieza.getIdArticulo());
			  	}else{
			  		pst.setNull(9, java.sql.Types.INTEGER);
			  	}
			  	
			  	
			  	
			  pst.setString(10, Sesion.DNI);
			  
			  pst.setInt(11, pieza.getId());
			  
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
		 
		return false;
	}
	
	public Pieza seleccionarPieza(int artId){
		Pieza pieza=null;		
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement("SELECT  Id, Codigo, Descripcion,TMant, PrecioCompra, PrecioAlquiler, PrecioVenta, "
			  		+ "ReqPlanchar, Comentarios, Estado, Stock, ArticuloId, CreadoPor, "
			  		+ "ModificadoPor, FCreacion, FModificacion from pieza where id=?");
		 	   pst.setInt(1, artId);
			   rs=pst.executeQuery();
			   
			   while(rs.next()){
				   pieza=new Pieza();
				   pieza.setId(rs.getInt("Id"));
				   pieza.setCodigo(rs.getString("Codigo"));
				   pieza.setDescripcion(rs.getString("Descripcion"));
				   pieza.setTipoMantenimiento(rs.getInt("TMant"));
				   pieza.setPrecioCompra(rs.getDouble("PrecioCompra"));
				   pieza.setPrecioAlquiler(rs.getDouble("PrecioAlquiler"));
				   pieza.setPrecioVenta(rs.getDouble("PrecioVenta"));
				   pieza.setRequierePlanchar(rs.getInt("ReqPlanchar"));
				   pieza.setComentarios(rs.getString("Comentarios"));
				   pieza.setEstado(rs.getInt("Estado"));
				   pieza.setStock(rs.getInt("Stock"));
				   pieza.setIdArticulo((rs.getBoolean("ArticuloId"))?rs.getInt("ArticuloId"):-1);
				   pieza.setCreadoPor(rs.getString("CreadoPor"));
				   pieza.setModificadoPor(rs.getString("ModificadoPor"));
				   pieza.setfCreacion(rs.getDate("FCreacion"));
				   pieza.setfModificacion(rs.getDate("FModificacion"));
				   
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
		
		return pieza;
	}
	
	public int seleccionarNroPiezaArticulo(int artId){
		  int nro_piezas=-1;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			  System.out.println(artId);
			  pst=conexionServidor.prepareStatement("SELECT  ifnull(count(*),0) as nro_piezas from pieza where ArticuloId=?");
		 	   pst.setInt(1, artId);
			   rs=pst.executeQuery();
			   
			   while(rs.next()){
				   nro_piezas=rs.getInt("nro_piezas"); 
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
		
		return nro_piezas;
	}
	

	public boolean actualizarStockEnPieza(int idPieza, int stockEn){
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement("UPDATE  Pieza set Stock=Stock+? where Id=?;");
			  
			  pst.setInt(1, stockEn);
			  pst.setInt(2, idPieza);
			  pst.executeUpdate();		 		  
			  this.setNoError(CORRECTO);
			  this.setMensaje(MEN_UPDATE_CORRECTO);
         }catch(SQLException e){

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
		 
		return false;
	}
	
	


	
	public boolean eliminarPieza(int idPieza){
		
		  PreparedStatement pst=null;
		  boolean estado=false;
		  try{
			  pst=conexionServidor.prepareStatement("DELETE FROM articulo where Id=?");
			  
			  pst.setInt(2, idPieza);
			  pst.executeUpdate();		 		  
			  this.setNoError(CORRECTO);
			  this.setMensaje(MEN_DELETE_CORRECTO);
			  estado=true;
       }catch(SQLException e){

    	   this.setNoError(INCORRECTO);
    	   this.setMensaje(MEN_DELETE_INCORRECTO+e.getMessage());
    	  estado=false;
    	   e.printStackTrace();
		  }finally{
    	   try {
    		   if(pst!=null){
    			   pst.close();
				
    	   		}   		
    	   
    	   } catch (SQLException e) {
					
					e.printStackTrace();
				}
    	   
       }
		 
		return false;
	}
	
	
	
	
}
