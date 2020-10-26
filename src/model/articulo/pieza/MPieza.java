package model.articulo.pieza;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dal.Articulo;
import dal.Pieza;

import model.MPadre;
import sesion.Sesion;

public class MPieza extends MPadre{
	/*
	private Pieza pieza=null;
	public int insertarPieza(Pieza piezas){
		  int idUltimo=-1;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement("INSERT INTO PIEZAS( Descripcion,TMant, PrecioCompra, PrecioAlquiler, PrecioVenta, "
			  		+ "ReqPlanchar, Comentarios, Estado, Stock, ArticuloId, CreadoPor, "
			  		+ "ModificadoPor ) values ()" ,PreparedStatement.RETURN_GENERATED_KEYS);
			  
				pst.setString(1,(piezas.getDescripcion()!=null && !piezas.getDescripcion().trim().isEmpty())?piezas.getDescripcion().trim():(String)null);
				if(piezas.getTipoMantenimiento()!=-1){
					pst.setInt(2, piezas.getTipoMantenimiento());
				}else{
					pst.setNull(2, java.sql.Types.INTEGER);
				}
				
				if(piezas.getPrecioCompra()!=-1.0){
					pst.setDouble(3, piezas.getPrecioCompra());
				}else{
					pst.setNull(3, java.sql.Types.DOUBLE);
				}
				
				if(piezas.getPrecioAlquiler()!=-1.0){
					pst.setDouble(4, piezas.getPrecioAlquiler());
				}else{
					pst.setNull(4, java.sql.Types.DOUBLE);
				}
				
				
				if(piezas.getPrecioVenta()!=-1.0){
					pst.setDouble(5, piezas.getPrecioVenta());
				}else{
					pst.setNull(5, java.sql.Types.DOUBLE);
				}
				
				
				if(piezas.getRequierePlanchar()!=-1){
					pst.setInt(6, piezas.getRequierePlanchar());
				}else{
					pst.setNull(6, java.sql.Types.INTEGER);
				}
				
				
				pst.setString(7,(piezas.getComentarios()!=null && !piezas.getComentarios().trim().isEmpty())?piezas.getComentarios().trim():(String)null);
				
				if(piezas.getEstado()!=-1){
					pst.setInt(8, piezas.getEstado());
				}else{
					pst.setNull(8, java.sql.Types.INTEGER);
				}
				
				
				if(piezas.getStock()!=-1){
					pst.setInt(9, piezas.getStock());
				}else{
					pst.setNull(9, java.sql.Types.INTEGER);
				}
				
				if(piezas.getIdArticulo()!=-1){
					pst.setInt(10, piezas.getIdArticulo());
				}else{
					pst.setNull(10, java.sql.Types.INTEGER);
				}
				
			
				
			  pst.setString(11, Sesion.DNI);
     	   	  pst.setString(12, Sesion.DNI);
			  
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
	
	public Pieza seleccionarArticulo(int artId){
		Pieza pieza=null;		
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement("SELECT  Id, Codigo, Descripcion,TMant, PrecioCompra, PrecioAlquiler, PrecioVenta, "
			  		+ "ReqPlanchar, Comentarios, Estado, Stock, ArticuloId, CreadoPor, "
			  		+ "ModificadoPor, FCreacion, FModificacion FROM ARTICULO where id=?");
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
	
	*/

}
