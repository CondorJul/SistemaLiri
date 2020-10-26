package model.articulo;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import dal.Articulo;
import dal.Busqueda;
import dal.CFamilia;
import dal.Cliente;
import dal.DetalleArticulo;
import dal.Pieza;
import funciones.Servidor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.StageStyle;
import model.MPadre;
import sesion.Sesion;

public class MArticulo extends MPadre{
	private Articulo articulo=null;
	public int insertarArticulo(Articulo articulo){
		this.articulo=articulo;
		  int idUltimo=-1;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement("INSERT INTO articulo(Descripcion,Talla,Stock,IdFamilia,Rentabilidad, "+
				"Resena,PrecioCompra,PrecioAlquiler,PrecioVenta,Genero,Ubicacion,NroPiezas, ProveedorId,"
				+ " DatoAdic1 , DatoAdic2, DatoAdic3, Comentarios,  "
				+ " CreadoPor,ModificadoPor) "+
				" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);",PreparedStatement.RETURN_GENERATED_KEYS);
			  
				pst.setString(1,(articulo.getDescripcion()!=null && !articulo.getDescripcion().trim().isEmpty())?articulo.getDescripcion().trim():(String)null);
				pst.setString(2,(articulo.getTalla()!=null && !articulo.getTalla().trim().isEmpty())?articulo.getTalla().trim():(String)null);
				if(articulo.getStock()!=-1){
					pst.setInt(3, articulo.getStock());
				}else{
					pst.setNull(3, java.sql.Types.INTEGER);
				}
				
				if(articulo.getIdFamilia()!=-1){
					pst.setInt(4, articulo.getIdFamilia());
				}else{
					pst.setNull(4, java.sql.Types.INTEGER);
				}
				
				if(articulo.getRentabilidad()!=-1.0){
					pst.setDouble(5, articulo.getRentabilidad());
				}else{
					pst.setNull(5, java.sql.Types.DOUBLE);
				}
				pst.setString(6,(articulo.getResena()!=null && !articulo.getResena().trim().isEmpty())?articulo.getResena().trim():(String)null);
				
				if(articulo.getPrecioCompra()!=-1.0){
					pst.setDouble(7, articulo.getPrecioCompra());
				}else{
					pst.setNull(7, java.sql.Types.DOUBLE);
				}
				
				if(articulo.getPrecioAlquiler()!=-1.0){
					pst.setDouble(8, articulo.getPrecioAlquiler());
				}else{
					pst.setNull(8, java.sql.Types.DOUBLE);
				}
				
				
				if(articulo.getPrecioVenta()!=-1.0){
					pst.setDouble(9, articulo.getPrecioVenta());
				}else{
					pst.setNull(9, java.sql.Types.DOUBLE);
				}
				pst.setString(10,(articulo.getGenero()!=null && !articulo.getGenero().trim().isEmpty())?articulo.getGenero().trim():(String)null);

				pst.setString(11,(articulo.getUbicacion()!=null && !articulo.getUbicacion().trim().isEmpty())?articulo.getUbicacion().trim():(String)null);
				
				if(articulo.getNroPiezas()!=-1.0){
					pst.setDouble(12, articulo.getNroPiezas());
				}else{
					pst.setNull(12, java.sql.Types.DOUBLE);
				}
				
				if(articulo.getIdProveedor()!=-1.0){
					pst.setDouble(13, articulo.getIdProveedor());
				}else{
					pst.setNull(13, java.sql.Types.INTEGER);
				}
				
				pst.setString(14,(articulo.getDatoAdic1()!=null && !articulo.getDatoAdic1().trim().isEmpty())?articulo.getDatoAdic1().trim():(String)null);
				pst.setString(15,(articulo.getDatoAdic2()!=null && !articulo.getDatoAdic2().trim().isEmpty())?articulo.getDatoAdic2().trim():(String)null);
				pst.setString(16,(articulo.getDatoAdic3()!=null && !articulo.getDatoAdic3().trim().isEmpty())?articulo.getDatoAdic3().trim():(String)null);
				pst.setString(17,(articulo.getComentarios()!=null && !articulo.getComentarios().trim().isEmpty())?articulo.getComentarios().trim():(String)null);

			  pst.setString(18, Sesion.DNI);
     	   	  pst.setString(19, Sesion.DNI);
			  
			  pst.executeUpdate();
			  
			  rs=pst.getGeneratedKeys();
			  while(rs.next()){
				  this.articulo.setId(rs.getInt(1));
				  idUltimo=rs.getInt(1);
			  }
			  ImagenArticulo ia=new ImagenArticulo();
			  ia.insertarImagen(idUltimo, articulo.getImagen());
			  
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
	
	
	
	
	


		public int actualizarArticulo(Articulo articulo){
			this.articulo=articulo;
			  int idUltimo=-1;
			  PreparedStatement pst=null;
			  ResultSet rs=null;
			  try{
				  pst=conexionServidor.prepareStatement("UPDATE articulo set Descripcion=?, Talla=?, Stock=?, IdFamilia=?, Rentabilidad=?, "+
					"Resena=?, PrecioCompra=?, PrecioAlquiler=?, PrecioVenta=?,Genero=?,Ubicacion=?,NroPiezas=?, ProveedorId=?,"
					+ " DatoAdic1=? , DatoAdic2=?, DatoAdic3=?, Comentarios=?,  "
					+ "  ModificadoPor=?, fModificacion=now()  where id=?"+
					";");
				  
					pst.setString(1,(articulo.getDescripcion()!=null && !articulo.getDescripcion().trim().isEmpty())?articulo.getDescripcion().trim():(String)null);
					pst.setString(2,(articulo.getTalla()!=null && !articulo.getTalla().trim().isEmpty())?articulo.getTalla().trim():(String)null);
					if(articulo.getStock()!=-1){
						pst.setInt(3, articulo.getStock());
					}else{
						pst.setNull(3, java.sql.Types.INTEGER);
					}
					
					if(articulo.getIdFamilia()!=-1){
						pst.setInt(4, articulo.getIdFamilia());
					}else{
						pst.setNull(4, java.sql.Types.INTEGER);
					}
					
					if(articulo.getRentabilidad()!=-1.0){
						pst.setDouble(5, articulo.getRentabilidad());
					}else{
						pst.setNull(5, java.sql.Types.DOUBLE);
					}
					pst.setString(6,(articulo.getResena()!=null && !articulo.getResena().trim().isEmpty())?articulo.getResena().trim():(String)null);
					
					if(articulo.getPrecioCompra()!=-1.0){
						pst.setDouble(7, articulo.getPrecioCompra());
					}else{
						pst.setNull(7, java.sql.Types.DOUBLE);
					}
					
					if(articulo.getPrecioAlquiler()!=-1.0){
						pst.setDouble(8, articulo.getPrecioAlquiler());
					}else{
						pst.setNull(8, java.sql.Types.DOUBLE);
					}
					
					
					if(articulo.getPrecioVenta()!=-1.0){
						pst.setDouble(9, articulo.getPrecioVenta());
					}else{
						pst.setNull(9, java.sql.Types.DOUBLE);
					}
					pst.setString(10,(articulo.getGenero()!=null && !articulo.getGenero().trim().isEmpty())?articulo.getGenero().trim():(String)null);

					pst.setString(11,(articulo.getUbicacion()!=null && !articulo.getUbicacion().trim().isEmpty())?articulo.getUbicacion().trim():(String)null);
					
					if(articulo.getNroPiezas()!=-1){
						pst.setDouble(12, articulo.getNroPiezas());
					}else{
						pst.setNull(12, java.sql.Types.DOUBLE);
					}

					
					if(articulo.getIdProveedor()!=-1){
						pst.setDouble(13, articulo.getIdProveedor());
					}else{
						pst.setNull(13, java.sql.Types.INTEGER);
					}
					
					pst.setString(14,(articulo.getDatoAdic1()!=null && !articulo.getDatoAdic1().trim().isEmpty())?articulo.getDatoAdic1().trim():(String)null);
					pst.setString(15,(articulo.getDatoAdic2()!=null && !articulo.getDatoAdic2().trim().isEmpty())?articulo.getDatoAdic2().trim():(String)null);
					pst.setString(16,(articulo.getDatoAdic3()!=null && !articulo.getDatoAdic3().trim().isEmpty())?articulo.getDatoAdic3().trim():(String)null);
					pst.setString(17,(articulo.getComentarios()!=null && !articulo.getComentarios().trim().isEmpty())?articulo.getComentarios().trim():(String)null);

	     	   	  pst.setString(18, Sesion.DNI);
	     	   	  pst.setInt(19, articulo.getId());
				  
				  pst.executeUpdate();
				  
				  ImagenArticulo ia=new ImagenArticulo();
				  ia.actualizarImagen(articulo.getId(), articulo.getImagen());
				  
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
			 
			return idUltimo;
		}
		
	
	public Articulo seleccionarArticulo(int artId){
		  Articulo articulo=null;		
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement("SELECT id, Codigo,Descripcion,Talla,Stock,IdFamilia,Rentabilidad, "+
				"Resena,PrecioCompra,PrecioAlquiler,PrecioVenta,Genero,Ubicacion,NroPiezas, ProveedorId, "
				+ " DatoAdic1 , DatoAdic2, DatoAdic3,  "
				+ " CreadoPor, Comentarios, "+
			     "ModificadoPor, FCreacion, FModificacion FROM articulo where id=?");
		 	   pst.setInt(1, artId);
			   rs=pst.executeQuery();
			   
			   while(rs.next()){
				   articulo=new Articulo();
				   articulo.setId(rs.getInt("Id"));
				   articulo.setCodigo(rs.getString("Codigo"));
				   articulo.setDescripcion(rs.getString("Descripcion"));
				   articulo.setTalla(rs.getString("Talla"));
				   articulo.setStock((rs.getBoolean("Stock"))?rs.getInt("Stock"):-1);
				   articulo.setIdFamilia((rs.getBoolean("IdFamilia"))?rs.getInt("IdFamilia"):-1);
				   articulo.setRentabilidad(rs.getDouble("Rentabilidad"));
				   articulo.setResena(rs.getString("Resena"));
				   articulo.setPrecioCompra(rs.getDouble("PrecioCompra"));
				   articulo.setPrecioAlquiler(rs.getDouble("PrecioAlquiler"));
				   articulo.setPrecioVenta(rs.getDouble("PrecioVenta"));
				   articulo.setGenero(rs.getString("Genero"));
				   articulo.setUbicacion(rs.getString("Ubicacion"));
				   articulo.setNroPiezas(rs.getInt("NroPiezas"));
				   articulo.setIdProveedor((rs.getBoolean("ProveedorId")?rs.getInt("ProveedorId"):-1));
				   articulo.setDatoAdic1(rs.getString("DatoAdic1"));
				   articulo.setDatoAdic2(rs.getString("DatoAdic2"));
				   articulo.setDatoAdic3(rs.getString("DatoAdic3"));
				   articulo.setCreadoPor(rs.getString("CreadoPor"));
				   articulo.setModificadoPor(rs.getString("ModificadoPor"));
				   articulo.setfCreacion(rs.getDate("FCreacion"));
				   articulo.setfModificacion(rs.getDate("FModificacion"));
				   articulo.setComentarios(rs.getString("Comentarios"));
				   ImagenArticulo ia=new ImagenArticulo();
				   articulo.setImagen(ia.seleccioanrImagen(artId));
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
		
		return articulo;
	}
	

	public ObservableList<DetalleArticulo> seleccionarArticuloDetalleVenta(int artId, String buscar){
		buscar=(buscar!=null && !buscar.isEmpty())?"%"+buscar+"%":"%";
		ObservableList<DetalleArticulo> array=FXCollections.observableArrayList();
		  DetalleArticulo detalleArticulo=null;		
		  PreparedStatement pst=null;
		  PreparedStatement pst1=null;
		  ResultSet rs=null;
		  ResultSet rs1=null;
		  int contador=0;
		  try{
			  pst=conexionServidor.prepareStatement(" select "
			  		+ " boleta.Id, pieza.Codigo, Cliente.Codigo as CodCliente, cliente.NombresApellidos, CONCAT(boleta.Serie, '-', LPAD(boleta.Numero,8,0)) as Serie_Boleta, Fecha, Cant, detalleboleta.Descripcion, PrecioUnit, Importe , FORMAT(((TotalPagar*Importe)/Subtotal),2) as ImporteConDescuento"
			  		+ " from  pieza "
			  		+ " INNER JOIN "
			  		+ " detalleboleta "
			  		+ " ON 	pieza.Id=detalleboleta.idPieza "
			  		+ " INNER JOIN boleta on detalleboleta.IdBoleta=boleta.Id "
			  		+ " INNER JOIN cliente on boleta.IdCliente=cliente.Id  where pieza.ArticuloId=? "
			  		+ " and EstadoAccion='3' and ( Cliente.Codigo like ? or  detalleboleta.Descripcion like ? or "
			  		+ " CONCAT(boleta.Serie,'-',LPAD(boleta.Numero,8,0)) like ?)");
		 	   pst.setInt(1, artId);
		 	   pst.setString(2, buscar);
		 	   pst.setString(3, buscar);
		 	   pst.setString(4, buscar);
		 	  
		 	   rs=pst.executeQuery();
			   
			   while(rs.next()){
				   detalleArticulo=new DetalleArticulo();
				   detalleArticulo.setNro(contador++);
				   detalleArticulo.setIdBoleta(rs.getInt("Id"));
				   detalleArticulo.setCodArtiPieza(rs.getString("Codigo"));
				   detalleArticulo.setCant(rs.getInt("Cant"));
				   detalleArticulo.setDescripcion(rs.getString("Descripcion"));
				   detalleArticulo.setPrecioUnit(rs.getString("PrecioUnit"));
				   detalleArticulo.setImporte(rs.getString("Importe"));
				   detalleArticulo.setImporteDesc(rs.getString("ImporteConDescuento"));
				   detalleArticulo.setCodCliente(rs.getString("CodCliente"));
				   detalleArticulo.setCliente(rs.getString("NombresApellidos"));
				   detalleArticulo.setBoleta(rs.getString("Serie_Boleta"));
				   detalleArticulo.setFecha(rs.getString("Fecha"));
				   
				   array.add(detalleArticulo);
				   
			   }
			  
			   
			   //obtener piezas
			   
			   pst1=conexionServidor.prepareStatement(" select"
			   		+ "	boleta.Id, articulo.Codigo,  Cliente.Codigo as CodCliente, cliente.NombresApellidos, "
			   		+ " CONCAT(boleta.Serie, '-', LPAD(boleta.Numero,8,0)) as Serie_Boleta, Cant, detalleboleta.Descripcion, "
			   		+ " PrecioUnit, Importe, FORMAT(((TotalPagar*Importe)/Subtotal),2) as ImporteConDescuento, Fecha "
			   		+ " from articulo "
			   		+ " inner JOIN detalleboleta on articulo.Id=detalleboleta.idArticulo "
			   		+ " inner join boleta on detalleboleta.IdBoleta=boleta.Id "
			   		+ " inner join cliente on boleta.IdCliente=cliente.Id where detalleboleta.idArticulo=? "
			   		+ " and EstadoAccion='3' and (Cliente.Codigo like ? or  detalleboleta.Descripcion like ? or "
			  		+ " CONCAT(boleta.Serie, '-', LPAD(boleta.Numero,8,0)) like ?)");
				 	   pst1.setInt(1, artId);
				 	   pst1.setString(2, buscar);
				 	   pst1.setString(3, buscar);
				 	   pst1.setString(4, buscar);
					   rs1=pst1.executeQuery();
					   
					   while(rs1.next()){
						   detalleArticulo=new DetalleArticulo();
						   detalleArticulo.setNro(contador++);
						   detalleArticulo.setIdBoleta(rs1.getInt("Id"));
						   detalleArticulo.setCodArtiPieza(rs1.getString("Codigo"));
						   detalleArticulo.setCant(rs1.getInt("Cant"));
						   detalleArticulo.setDescripcion(rs1.getString("Descripcion"));
						   detalleArticulo.setPrecioUnit(rs1.getString("PrecioUnit"));
						   detalleArticulo.setImporte(rs1.getString("Importe"));
						   detalleArticulo.setImporteDesc(rs1.getString("ImporteConDescuento"));
						   detalleArticulo.setCodCliente(rs1.getString("CodCliente"));
						   detalleArticulo.setCliente(rs1.getString("NombresApellidos"));
						   detalleArticulo.setBoleta(rs1.getString("Serie_Boleta"));
						   detalleArticulo.setFecha(rs1.getString("Fecha"));
						   array.add(detalleArticulo);
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
	      	   	if(pst1!=null){
	   			   pst1.close();
					
	   	   		}   		
	   	   		if(rs1!=null){
	   	   			rs1.close();
	   	   		}
      	   
      	   } catch (SQLException e) {
					
					e.printStackTrace();
				}
      	   
         }
		
		return array;
	}	
	
	
	public int seleccionarStockAlmacenArticulos(int artId){
		int stockArticulo=-1;
		PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement("SELECT Stock FROM articulo where id=?");
		 	   pst.setInt(1, artId);
			   rs=pst.executeQuery();
			   
			   while(rs.next()){
				   stockArticulo=rs.getInt("Stock");
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
		
		return stockArticulo;
	}
	
	public Image seleccioanrImagen(int idArt){
	 ImagenArticulo imgArticulo=new ImagenArticulo();
	 return imgArticulo.seleccioanrImagen(idArt);
		
	}
	
	

	public int seleccionarStockMinArticulosCompletos(int artId){
		int minquitcom=-1;
		PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement("select MIN(IFNULL(Stock,0)) as minquitcom from pieza where ArticuloId=?");
		 	   pst.setInt(1, artId);
			   rs=pst.executeQuery();
			   
			   while(rs.next()){
				   minquitcom=rs.getInt("minquitcom");
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
		
		return minquitcom;
	}
	
	

	public Double getRentabilidadadAproximadaSinDRP( int idArticulo){
		Double stockArticulo=-1.0;
		PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement("select rentabilidadadAproximadaSinDRP(?) as rentabilidad");
		 	   pst.setInt(1, idArticulo);
			   rs=pst.executeQuery();
			   
			   while(rs.next()){
				   stockArticulo=rs.getDouble("rentabilidad");
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
		
		return stockArticulo;
	}
	
	public Double getRentabilidadadConDescuentoSinP( int idArticulo){
		Double stockArticulo=-1.0;
		PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement("select rentabilidadadConDescuentoSinP(?) as rentabilidad");
		 	   pst.setInt(1, idArticulo);
			   rs=pst.executeQuery();
			   
			   while(rs.next()){
				   stockArticulo=rs.getDouble("rentabilidad");
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
		
		return stockArticulo;
	}

	

	public boolean actualizarStockEn(int idArticulo, int stockEn){
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement("UPDATE  Pieza set Stock=Stock+? where ArticuloId=?;");
			  
			  pst.setInt(1, stockEn);
			  pst.setInt(2, idArticulo);
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

	
	 class ImagenArticulo{

		public boolean insertarImagen(int idArti, Image artImage){
			boolean bool=false;
			Connection conexionServidor=null;
			PreparedStatement pst=null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conexionServidor = DriverManager.getConnection(Servidor.getServidorFotoArticulo(), Servidor.USER, Servidor.PASS);
				pst=conexionServidor.prepareStatement("INSERT INTO imgArticulos(ArtId,ArtImg) values (?,?);");
				pst.setInt(1,idArti );
				if (artImage != null) {
		           	   BufferedImage image = SwingFXUtils.fromFXImage(artImage, null);
		           	   
		           	   ByteArrayOutputStream baos = null;
		           	   try {
		           	       baos = new ByteArrayOutputStream();
		           	       ImageIO.write(image, "jpg", baos);
		           	   } finally {
		           	       try {
		           	           baos.close();
		           	       } catch (Exception e) {
		           	       }
		           	   }
		           	   
		           	   ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

		             pst.setBlob(2, bais);
	              } else {
	                 pst.setBlob(2, (Blob) null);
	              }
					  
					pst.executeUpdate();
					  
					bool=true;
			} catch (Exception e) {
				Alert error_alert = new Alert(Alert.AlertType.ERROR);
	            error_alert.setTitle("Error al conectar!!!");
	            error_alert.setHeaderText("No se pudo conectar con el servidor.");
	            error_alert.setContentText("Intente otra vez.");
	            error_alert.initStyle(StageStyle.UNDECORATED);
	            error_alert.show();
				cerrarConexionServidor();
			}finally{
				try {
					if (conexionServidor != null) {
						conexionServidor.close();
					}
					if(pst!=null){
						pst.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			return bool;
			
			
			
		}
		
		public boolean actualizarImagen(int idArti, Image artImage){
			
			boolean bool=false;
			Connection conexionServidor=null;
			PreparedStatement pst=null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conexionServidor = DriverManager.getConnection(Servidor.getServidorFotoArticulo(), Servidor.USER, Servidor.PASS);
				pst=conexionServidor.prepareStatement("UPDATE  imgArticulos set ArtImg=? where ArtId=?;");
				
				if (artImage != null) {
		           	   BufferedImage image = SwingFXUtils.fromFXImage(artImage, null);
		           	   
		           	   ByteArrayOutputStream baos = null;
		           	   try {
		           	       baos = new ByteArrayOutputStream();
		           	       ImageIO.write(image, "jpg", baos);
		           	   } catch(Exception e){
		           		   e.printStackTrace();
		           	   }finally {
		           	       try {
		           	           baos.close();
		           	       } catch (Exception e) {
		           	       }
		           	   }
		           	   
		           	   ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());

		             pst.setBlob(1, bais);
	              } else {
	                 pst.setBlob(1, (Blob) null);
	              }
				pst.setInt(2,idArti );

					  
					pst.executeUpdate();
					bool=true;
			} catch (Exception e) {
				e.printStackTrace();
				Alert error_alert = new Alert(Alert.AlertType.ERROR);
	            error_alert.setTitle("Error al conectar!!!");
	            error_alert.setHeaderText("No se pudo conectar con el servidor.");
	            error_alert.setContentText("Intente otra vez.");
	            error_alert.initStyle(StageStyle.UNDECORATED);
	            error_alert.show();
				cerrarConexionServidor();
			}finally{
				try {
					if (conexionServidor != null) {
						conexionServidor.close();
					}
					if(pst!=null){
						pst.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} 
			return bool;
		}
		
		public Image seleccioanrImagen(int idArt){
			Image imageArticulo=null;
			Connection conexionServidor=null;
			PreparedStatement pst=null;
			ResultSet rs=null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conexionServidor = DriverManager.getConnection(Servidor.getServidorFotoArticulo(), Servidor.USER, Servidor.PASS);
				pst=conexionServidor.prepareStatement("SELECT  ArtImg FROM imgArticulos where ArtId=?;");
				pst.setInt(1,idArt ); 
				rs=pst.executeQuery();

				while(rs.next()){
					Blob imageBlob=rs.getBlob("ArtImg");
	            	if(imageBlob!=null){
	            		ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(imageBlob.getBytes(1, (int)imageBlob.length()));
	            		imageArticulo=new Image(byteArrayInputStream);
	            		
	               	}
						
				}
			} catch (Exception e) {
				e.printStackTrace();
				Alert error_alert = new Alert(Alert.AlertType.ERROR);
	            error_alert.setTitle("Error al conectar!!!");
	            error_alert.setHeaderText("No se pudo conectar con el servidor.");
	            error_alert.setContentText("Intente otra vez.");
	            error_alert.initStyle(StageStyle.UNDECORATED);
	            error_alert.show();
				cerrarConexionServidor();
			} finally{
				try {
					if (conexionServidor != null) {
						conexionServidor.close();
					}
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
			return imageArticulo;
		}
		
		
		public boolean eliminarImagen(int idArt){
			boolean bool=false;
			Connection conexionServidor=null;
			PreparedStatement pst=null;
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conexionServidor = DriverManager.getConnection(Servidor.getServidorFotoArticulo(), Servidor.USER, Servidor.PASS);
				pst=conexionServidor.prepareStatement("DELETE FROM imgArticulos where ArtId=?;");
				pst.setInt(1,idArt ); 
				pst.executeUpdate();
				bool=true;
			} catch (Exception e) {
				Alert error_alert = new Alert(Alert.AlertType.ERROR);
	            error_alert.setTitle("Error al conectar!!!");
	            error_alert.setHeaderText("No se pudo conectar con el servidor.");
	            error_alert.setContentText("Intente otra vez.");
	            error_alert.initStyle(StageStyle.UNDECORATED);
	            error_alert.show();
				cerrarConexionServidor();
			} finally{
				try {
					if (conexionServidor != null) {
						conexionServidor.close();
					}
					if(pst!=null){
						pst.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return bool;
		}
		

	}
	 
	 
	 
	 
	 

}
