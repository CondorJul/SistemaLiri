package model.transaccionesExternas;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dal.Articulo;
import dal.Pieza;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import model.MPadre;
import sesion.Sesion;

public class MTransaccionesExternas extends MPadre {
	
	public static final String TIPO_ARTICULO ="pieza.ArticuloId";
	public static final String TIPO_PIEZA="Id"; 
	
	public static final String A_UBICACION_ALMACEN_STOCK="Stock";
	public static final String A_UBICACION_LAVANDERIA="LavanderiaCant";
	public static final String A_UBICACION_PLANCHADO="PlanchadoCant";
	public static final String A_UBICACION_REPARACION="ReparacionCant";
	public static final String A_UBICACION_ESPERA="EsperaCant";
	
	public static final String DE_UBICACION_ALMACEN_STOCK="Stock";
	public static final String DE_UBICACION_LAVANDERIA="LavanderiaCant";
	public static final String DE_UBICACION_PLANCHADO="PlanchadoCant";
	public static final String DE_UBICACION_REPARACION="ReparacionCant";
	public static final String DE_UBICACION_ESPERA="EsperaCant";
	
	
	
	public static final int LAVANDERIA=1;
	public static final int REPARACION=2;
	public static final int PLANCHADO=3;
	
	public static final int TIPO_UNO=1;
	public static final int TIPO_TODO=2;
	public static final int TIPO_OTRA_CANT=3;
	
	private static final String U_LAVANDERIA=" LavanderiaCant ";
	private static final String U_REPARACION =" ReparacionCant ";
	private static final String U_PLANCHADO=" PlanchadoCant ";
	
	
	
	
	
	
	
	
	private Pieza pieza = null;
	private Articulo articulo=null;
	
	//lavanderia	
	public ObservableList<Pieza> seleccionarColeccionPiezasLavanderia(){
		ObservableList<Pieza> arrayListLavanderia=FXCollections.observableArrayList();
		this.pieza = null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			pst=conexionServidor.prepareStatement("select Id, Codigo, Descripcion, LavanderiaCant as cantidad from pieza where LavanderiaCant<>0;"); 
			rs=pst.executeQuery();
	
			while(rs.next()){
	
				pieza =new Pieza();
				pieza.setId(rs.getInt("Id"));
				pieza.setCodigo(rs.getString("Codigo"));
				pieza.setDescripcion(rs.getString("Descripcion"));
				pieza.setCantTransacExt(rs.getInt("cantidad"));
				arrayListLavanderia.add(pieza);
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
		return arrayListLavanderia;		
	}
	
	public ObservableList<Pieza> BuscarBDPiezaLavanderia(String buscar){
		ObservableList<Pieza> arrayListLavanderia=FXCollections.observableArrayList();
		this.pieza = null;	
		String url = "select Id, Codigo, Descripcion, LavanderiaCant as cantidad from pieza "
				+ "WHERE LavanderiaCant<>0 and (Codigo LIKE ? OR Descripcion LIKE ?);";  
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
	        pst = conexionServidor.prepareStatement(url);
	        pst.setString(1, "%" + buscar + "%");
	        pst.setString(2, "%" + buscar + "%");
	        rs = pst.executeQuery();
	        
	        while(rs.next()){
	        	pieza =new Pieza();
				pieza.setId(rs.getInt("Id"));
				pieza.setCodigo(rs.getString("Codigo"));
				pieza.setDescripcion(rs.getString("Descripcion"));
				pieza.setCantTransacExt(rs.getInt("cantidad"));
				arrayListLavanderia.add(pieza);  
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
		return arrayListLavanderia;
	}
	
	//lavanderia	 articulo
		public ObservableList<Articulo> seleccionarColeccionArticuloLavanderia(){
			ObservableList<Articulo> arrayListLavanderia=FXCollections.observableArrayList();
			this.articulo = null;
			PreparedStatement pst=null;
			ResultSet rs=null;
			try{
				pst=conexionServidor.prepareStatement("select Id, Codigo, Descripcion, Ubicacion, LavanderiaCant as cantidad from articulo where LavanderiaCant<>0;"); 
				rs=pst.executeQuery();
		
				while(rs.next()){
		
					articulo =new Articulo();
					articulo.setId(rs.getInt("Id"));
					articulo.setCodigo(rs.getString("Codigo"));
					articulo.setDescripcion(rs.getString("Descripcion"));
					articulo.setUbicacion(rs.getString("Ubicacion"));
					articulo.setLavanderiaCant(rs.getInt("cantidad"));
					arrayListLavanderia.add(articulo);
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
			return arrayListLavanderia;		
		}
		
		public ObservableList<Articulo> BuscarBDArticuloLavanderia(String buscar){
			ObservableList<Articulo> arrayListLavanderia=FXCollections.observableArrayList();
			this.articulo = null;	
			String url = "select Id, Codigo, Descripcion, Ubicacion, LavanderiaCant as cantidad from articulo "
					+ "WHERE LavanderiaCant<>0 and (Codigo LIKE ? OR Descripcion LIKE ?);";  
			PreparedStatement pst=null;
			ResultSet rs=null;
			try{
		        pst = conexionServidor.prepareStatement(url);
		        pst.setString(1, "%" + buscar + "%");
		        pst.setString(2, "%" + buscar + "%");
		        rs = pst.executeQuery();
		        
		        while(rs.next()){
		        	articulo =new Articulo();
		        	articulo.setId(rs.getInt("Id"));
					articulo.setCodigo(rs.getString("Codigo"));
					articulo.setDescripcion(rs.getString("Descripcion"));
					articulo.setUbicacion(rs.getString("Ubicacion"));
					articulo.setLavanderiaCant(rs.getInt("cantidad"));
					arrayListLavanderia.add(articulo);  
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
			return arrayListLavanderia;
		}
	
	//Reparacion	
	public ObservableList<Pieza> seleccionarColeccionPiezasReparacion(){
		ObservableList<Pieza> arrayListReparacion=FXCollections.observableArrayList();
		this.pieza = null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			pst=conexionServidor.prepareStatement("select Id, Codigo, Descripcion, ReparacionCant as cantidad from pieza where ReparacionCant<>0;"); 
			rs=pst.executeQuery();
	
			while(rs.next()){
	
				pieza =new Pieza();
				pieza.setId(rs.getInt("Id"));
				pieza.setCodigo(rs.getString("Codigo"));
				pieza.setDescripcion(rs.getString("Descripcion"));
				pieza.setCantTransacExt(rs.getInt("cantidad"));
				arrayListReparacion.add(pieza);
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
		return arrayListReparacion;		
	}
	
	public ObservableList<Pieza> BuscarBDPiezaReparacion(String buscar){
		ObservableList<Pieza> arrayListReparacion=FXCollections.observableArrayList();
		this.pieza = null;	
		String url = "select Id, Codigo, Descripcion, ReparacionCant  as cantidad from pieza "
				+ "WHERE ReparacionCant<>0 and (Codigo LIKE ? OR Descripcion LIKE ?);";  
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
	        pst = conexionServidor.prepareStatement(url);
	        pst.setString(1, "%" + buscar + "%");
	        pst.setString(2, "%" + buscar + "%");
	        rs = pst.executeQuery();
	        
	        while(rs.next()){
	        	pieza =new Pieza();
				pieza.setId(rs.getInt("Id"));
				pieza.setCodigo(rs.getString("Codigo"));
				pieza.setDescripcion(rs.getString("Descripcion"));
				pieza.setCantTransacExt(rs.getInt("cantidad"));
				arrayListReparacion.add(pieza);  
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
		return arrayListReparacion;
	}	
	
	/*reparacion articulo*/
	
	public ObservableList<Articulo> seleccionarColeccionArticuloReparacion(){
		ObservableList<Articulo> arrayListReparacion=FXCollections.observableArrayList();
		this.articulo = null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			pst=conexionServidor.prepareStatement("select Id, Codigo, Descripcion, Ubicacion,ReparacionCant as cantidad from articulo where ReparacionCant<>0;"); 
			rs=pst.executeQuery();
	
			while(rs.next()){
	
				articulo =new Articulo();
				articulo.setId(rs.getInt("Id"));
				articulo.setCodigo(rs.getString("Codigo"));
				articulo.setDescripcion(rs.getString("Descripcion"));
				articulo.setUbicacion(rs.getString("Ubicacion"));
				articulo.setReparacionCant(rs.getInt("cantidad"));
				arrayListReparacion.add(articulo);
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
		return arrayListReparacion;		
	}
	
	public ObservableList<Articulo> BuscarBDArticuloReparacion(String buscar){
		ObservableList<Articulo> arrayListReparacion=FXCollections.observableArrayList();
		this.pieza = null;	
		String url = "select Id, Codigo, Descripcion, Ubicacion, ReparacionCant  as cantidad from articulo "
				+ "WHERE ReparacionCant<>0 and (Codigo LIKE ? OR Descripcion LIKE ?);";  
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
	        pst = conexionServidor.prepareStatement(url);
	        pst.setString(1, "%" + buscar + "%");
	        pst.setString(2, "%" + buscar + "%");
	        rs = pst.executeQuery();
	        
	        while(rs.next()){
	        	articulo =new Articulo();
				articulo.setId(rs.getInt("Id"));
				articulo.setCodigo(rs.getString("Codigo"));
				articulo.setUbicacion(rs.getString("Ubicacion"));
				articulo.setDescripcion(rs.getString("Descripcion"));
				articulo.setReparacionCant(rs.getInt("cantidad"));
				arrayListReparacion.add(articulo);  
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
		return arrayListReparacion;
	}	
	
	
	//Planchado	
	public ObservableList<Pieza> seleccionarColeccionPiezasPlanchado(){
		ObservableList<Pieza> arrayListPlanchado=FXCollections.observableArrayList();
		this.pieza = null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			pst=conexionServidor.prepareStatement("select Id, Codigo, Descripcion, PlanchadoCant as cantidad from pieza where PlanchadoCant<>0;"); 
			rs=pst.executeQuery();
	
			while(rs.next()){
	
				pieza =new Pieza();
				pieza.setId(rs.getInt("Id"));
				pieza.setCodigo(rs.getString("Codigo"));
				pieza.setDescripcion(rs.getString("Descripcion"));
				pieza.setCantTransacExt(rs.getInt("cantidad"));
				arrayListPlanchado.add(pieza);
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
		return arrayListPlanchado;		
	}
	
	
	
	
	public ObservableList<Pieza> BuscarBDPiezaPlanchado(String buscar){
		ObservableList<Pieza> arrayListPlanchado=FXCollections.observableArrayList();
		this.pieza = null;	
		String url = "select Id, Codigo, Descripcion, PlanchadoCant as cantidad from pieza "
				+ "WHERE PlanchadoCant<>0 and (Codigo LIKE ? OR Descripcion LIKE ?);";  
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
	        pst = conexionServidor.prepareStatement(url);
	        pst.setString(1, "%" + buscar + "%");
	        pst.setString(2, "%" + buscar + "%");
	        rs = pst.executeQuery();
	        
	        while(rs.next()){
	        	pieza =new Pieza();
				pieza.setId(rs.getInt("Id"));
				pieza.setCodigo(rs.getString("Codigo"));
				pieza.setDescripcion(rs.getString("Descripcion"));
				pieza.setCantTransacExt(rs.getInt("cantidad"));
				arrayListPlanchado.add(pieza);  
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
		return arrayListPlanchado;
	}	
	/*PLANCHADO */
	public ObservableList<Articulo> seleccionarColeccionArticuloPlanchado(){
		ObservableList<Articulo> arrayListPlanchado=FXCollections.observableArrayList();
		this.articulo = null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			pst=conexionServidor.prepareStatement("select Id, Codigo, Descripcion, Ubicacion, PlanchadoCant as cantidad from articulo where PlanchadoCant<>0;"); 
			rs=pst.executeQuery();
	
			while(rs.next()){
	
				articulo =new Articulo();
				articulo.setId(rs.getInt("Id"));
				articulo.setCodigo(rs.getString("Codigo"));
				articulo.setDescripcion(rs.getString("Descripcion"));
				articulo.setUbicacion(rs.getString("Ubicacion"));
				articulo.setPlanchadoCant(rs.getInt("cantidad"));
				arrayListPlanchado.add(articulo);
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
		return arrayListPlanchado;		
	}
	public ObservableList<Articulo> BuscarBDArticuloPlanchado(String buscar){
		ObservableList<Articulo> arrayListPlanchado=FXCollections.observableArrayList();
		this.articulo = null;	
		String url = "select Id, Codigo, Descripcion, Ubicacion,PlanchadoCant as cantidad from articulo "
				+ "WHERE EsperaCant<>0 and (Codigo LIKE ? OR Descripcion LIKE ?);";  
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
	        pst = conexionServidor.prepareStatement(url);
	        pst.setString(1, "%" + buscar + "%");
	        pst.setString(2, "%" + buscar + "%");
	        rs = pst.executeQuery();
	        
	        while(rs.next()){
	        	articulo =new Articulo();
				articulo.setId(rs.getInt("Id"));
				articulo.setCodigo(rs.getString("Codigo"));
				articulo.setDescripcion(rs.getString("Descripcion"));
				articulo.setUbicacion(rs.getString("Ubicacion"));
				articulo.setPlanchadoCant(rs.getInt("cantidad"));
				arrayListPlanchado.add(articulo);  
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
		return arrayListPlanchado;
	}	
	
	//Espera	
	public ObservableList<Pieza> seleccionarColeccionPiezasEspera(){
		ObservableList<Pieza> arrayListPlanchado=FXCollections.observableArrayList();
		this.pieza = null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			pst=conexionServidor.prepareStatement("select Id, Codigo, Descripcion, EsperaCant as cantidad from pieza where EsperaCant<>0;"); 
			rs=pst.executeQuery();
	
			while(rs.next()){
	
				pieza =new Pieza();
				pieza.setId(rs.getInt("Id"));
				pieza.setCodigo(rs.getString("Codigo"));
				pieza.setDescripcion(rs.getString("Descripcion"));
				pieza.setCantTransacExt(rs.getInt("cantidad"));
				arrayListPlanchado.add(pieza);
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
		return arrayListPlanchado;		
	}
	
	public ObservableList<Pieza> BuscarBDPiezaEspera(String buscar){
		ObservableList<Pieza> arrayListPlanchado=FXCollections.observableArrayList();
		this.pieza = null;	
		String url = "select Id, Codigo, Descripcion, EsperaCant as cantidad from pieza "
				+ "WHERE EsperaCant<>0 and (Codigo LIKE ? OR Descripcion LIKE ?);";  
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
	        pst = conexionServidor.prepareStatement(url);
	        pst.setString(1, "%" + buscar + "%");
	        pst.setString(2, "%" + buscar + "%");
	        rs = pst.executeQuery();
	        
	        while(rs.next()){
	        	pieza =new Pieza();
				pieza.setId(rs.getInt("Id"));
				pieza.setCodigo(rs.getString("Codigo"));
				pieza.setDescripcion(rs.getString("Descripcion"));
				pieza.setCantTransacExt(rs.getInt("cantidad"));
				arrayListPlanchado.add(pieza);  
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
		return arrayListPlanchado;
	}	
	
	/*espera articulo*/
	public ObservableList<Articulo> seleccionarColeccionArticuloEspera(){
		ObservableList<Articulo> arrayListPlanchado=FXCollections.observableArrayList();
		this.articulo = null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			pst=conexionServidor.prepareStatement("select Id, Codigo, Descripcion,Ubicacion, EsperaCant as cantidad from articulo where EsperaCant<>0;"); 
			rs=pst.executeQuery();
	
			while(rs.next()){
	
				articulo =new Articulo();
				articulo.setId(rs.getInt("Id"));
				articulo.setCodigo(rs.getString("Codigo"));
				articulo.setDescripcion(rs.getString("Descripcion"));
				articulo.setUbicacion(rs.getString("Ubicacion"));

				articulo.setEsperaCant(rs.getInt("cantidad"));
				arrayListPlanchado.add(articulo);
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
		return arrayListPlanchado;		
	}
	
	public ObservableList<Articulo> BuscarBDArticuloEspera(String buscar){
		ObservableList<Articulo> arrayListPlanchado=FXCollections.observableArrayList();
		this.articulo = null;	
		String url = "select Id, Codigo, Descripcion,Ubicacion, EsperaCant as cantidad from articulo "
				+ "WHERE EsperaCant<>0 and (Codigo LIKE ? OR Descripcion LIKE ?);";  
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
	        pst = conexionServidor.prepareStatement(url);
	        pst.setString(1, "%" + buscar + "%");
	        pst.setString(2, "%" + buscar + "%");
	        rs = pst.executeQuery();
	        
	        while(rs.next()){
	        	articulo =new Articulo();
				articulo.setId(rs.getInt("Id"));
				articulo.setCodigo(rs.getString("Codigo"));
				articulo.setDescripcion(rs.getString("Descripcion"));
				articulo.setUbicacion(rs.getString("Ubicacion"));
				articulo.setEsperaCant(rs.getInt("cantidad"));
				arrayListPlanchado.add(articulo);  
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
		return arrayListPlanchado;
	}	
	
	
	//private
	/*
	 
	public boolean devolverPieza(int tipo, int IdPieza, int cant, int ubicacion){
		
		boolean devolver=false;
		

		String url = "CALL devolver_pieza(?,?,?,?);";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			pst=conexionServidor.prepareStatement(url);
			pst.setInt(1,tipo);
			pst.setInt(2,IdPieza);

			pst.setInt(3, cant);
			
			pst.setInt(4, ubicacion);
			
			pst.executeUpdate();
			this.setNoError(CORRECTO);
			this.setMensaje(MEN_UPDATE_CORRECTO);
			devolver=true;
		}catch (Exception e) {
			devolver=false;
			this.setNoError(INCORRECTO);
     	    this.setMensaje(MEN_UPDATE_INCORRECTO+e.getMessage());
     	    
     	   Alert error_alert = new Alert(Alert.AlertType.ERROR);
           error_alert.setTitle("Error.");
           error_alert.setHeaderText("");
           error_alert.setContentText(e.getMessage());
           error_alert.show();
           
			e.printStackTrace();
			
			
		}finally{
			try{
				if(pst!=null){
					pst.close();
				}
				if (rs!=null) {
					rs.close();
				}
       			
   			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return devolver;

	}

	  */
	
	public boolean moverPiezaArticulo(String tipo/*1=articulo y 2=pieza*/, int id/*id de pieza o articulos*/, int cant, String deUbicacion, String aUbicacion){
		
		boolean devolver=false;
		
		String url = "UPDATE pieza SET "+deUbicacion+"="+deUbicacion+"-?, "+aUbicacion+"="+aUbicacion+"+? WHERE "+tipo+"=?";;
		
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			pst=conexionServidor.prepareStatement(url);
			pst.setInt(1,cant);
			pst.setInt(2,cant);

			pst.setInt(3, id);
			
			pst.executeUpdate();
			this.setNoError(CORRECTO);
			this.setMensaje(MEN_UPDATE_CORRECTO);
			devolver=true;
		}catch (Exception e) {
			devolver=false;
			this.setNoError(INCORRECTO);
     	    this.setMensaje(MEN_UPDATE_INCORRECTO+e.getMessage());
     	    
     	   Alert error_alert = new Alert(Alert.AlertType.ERROR);
           error_alert.setTitle("Error.");
           error_alert.setHeaderText("");
           error_alert.setContentText(e.getMessage());
           error_alert.show();
           
			e.printStackTrace();
			
			
		}finally{
			try{
				if(pst!=null){
					pst.close();
				}
				if (rs!=null) {
					rs.close();
				}
       			
   			}catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return devolver;

	}
}
