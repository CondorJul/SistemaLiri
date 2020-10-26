package model.usuario;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;

import dal.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import model.MPadre;
import sesion.Sesion;

public class MUsuario extends MPadre{
	private Usuario usuario = null;
	
	public void insertarUsuario(Usuario usuario){
		this.usuario=usuario;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement("INSERT INTO usuario(DNI, NomApell, EMail, Telefono, Salario, "
			  		+ "Direccion, Cargo, Comentarios, Estado, Clave,  BlobImg, "
			  		+ "CreadoPor, ModificadoPor) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);");
			  
			  if(usuario.getDni()!=null && !usuario.getDni().trim().isEmpty()){
           	   	  pst.setString(1, usuario.getDni().trim());
              }else{
            	  pst.setNull(1,java.sql.Types.VARCHAR);
              }
			  
			  
			  if(usuario.getNomApell()!=null && !usuario.getNomApell().trim().isEmpty()){
           	   	  pst.setString(2, usuario.getNomApell().trim());
              }else{
            	  pst.setNull(2,java.sql.Types.VARCHAR);
              }
       	   	  pst.setString(3, (usuario.getEmail()!=null && !usuario.getEmail().trim().isEmpty())?usuario.getEmail():(String)null);

			  if(usuario.getEmail()!=null && !usuario.getEmail().trim().isEmpty()){
           	   	  pst.setString(3, usuario.getEmail());
              }else{
            	  pst.setNull(3,java.sql.Types.VARCHAR);
              }
			  
			  if(usuario.getTelefono()!=null && !usuario.getTelefono().trim().isEmpty()){
           	   	  pst.setString(4, usuario.getTelefono());
              }else{
            	  pst.setNull(4,java.sql.Types.VARCHAR);
              }
			  
			  if(usuario.getSalario()!=-1.0){
           	   	  pst.setDouble(5, usuario.getSalario());
              }else{
            	  pst.setNull(5,java.sql.Types.VARCHAR);
              }
			  
			  if(usuario.getDireccion()!=null && !usuario.getDireccion().trim().isEmpty()){
           	   	  pst.setString(6, usuario.getDireccion());
              }else{
            	  pst.setNull(6,java.sql.Types.VARCHAR);
              }
			  
			  if(usuario.getCargo()!=null && !usuario.getCargo().trim().isEmpty()){
           	   	  pst.setString(7, usuario.getCargo());
              }else{
            	  pst.setNull(7,java.sql.Types.VARCHAR);
              }
			  
			  if(usuario.getComentarios()!=null && !usuario.getComentarios().trim().isEmpty()){
           	   	  pst.setString(8, usuario.getComentarios());
              }else{
            	  pst.setNull(8,java.sql.Types.VARCHAR);
              }
			  
			  if(usuario.getEstado()!=-1){
           	   	  pst.setInt(9, usuario.getEstado());
              }else{
            	  pst.setNull(9,java.sql.Types.VARCHAR);
              }
			  
			  if(usuario.getClave()!=null && !usuario.getClave().trim().isEmpty()){
           	   	  pst.setString(10, usuario.getClave());
              }else{
            	  pst.setNull(10,java.sql.Types.VARCHAR);
              }
			  
			  if (usuario.getFoto() != null) {
           	   BufferedImage image = SwingFXUtils.fromFXImage(usuario.getFoto(), null);
           	   
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

                  pst.setBlob(11, bais);
              } else {
                  pst.setBlob(11, (Blob) null);
              }
			  
			  pst.setString(12, Sesion.DNI);
			  pst.setString(13, Sesion.DNI);

			  
			  pst.executeUpdate();
			  this.setNoError(CORRECTO);
			  this.setMensaje(MEN_INSERT_CORRECTO);
           }   
           catch(SQLException e){
        	   this.setNoError(INCORRECTO);
        	   this.setMensaje(MEN_INSERT_INCORRECTO+e.getMessage());
        	   e.printStackTrace();
        	  
		  }catch(IOException e){
			  this.setNoError(INCORRECTO);
			  this.setMensaje(MEN_INSERT_INCORRECTO+e.getMessage());
			  mensaje=e.getMessage();
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
	
	
	public void actualizarUsuario(Usuario usuario){
		
		
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement("UPDATE  usuario SET  NomApell=?, EMail=?, Telefono=?, Salario =?, "
			  		+ "Direccion=?, Cargo=?, Comentarios=?, Estado=?,   BlobImg=?, "
			  		+ " ModificadoPor=?, fmodificacion = now() where DNI=?");
			  			  
			  
			  if(usuario.getNomApell()!=null && !usuario.getNomApell().trim().isEmpty()){
         	   	  pst.setString(1, usuario.getNomApell().trim());
            }else{
          	  pst.setNull(1,java.sql.Types.VARCHAR);
            }

			  if(usuario.getEmail()!=null && !usuario.getEmail().trim().isEmpty()){
         	   	  pst.setString(2, usuario.getEmail());
            }else{
          	  pst.setNull(2,java.sql.Types.VARCHAR);
            }
			  
			  if(usuario.getTelefono()!=null && !usuario.getTelefono().trim().isEmpty()){
         	   	  pst.setString(3, usuario.getTelefono());
            }else{
          	  pst.setNull(3,java.sql.Types.VARCHAR);
            }
			  
			  if(usuario.getSalario()!=-1.0){
         	   	  pst.setDouble(4, usuario.getSalario());
            }else{
          	  pst.setNull(4,java.sql.Types.VARCHAR);
            }
			  
			  if(usuario.getDireccion()!=null && !usuario.getDireccion().trim().isEmpty()){
         	   	  pst.setString(5, usuario.getDireccion());
            }else{
          	  pst.setNull(5,java.sql.Types.VARCHAR);
            }
			  
			  if(usuario.getCargo()!=null && !usuario.getCargo().trim().isEmpty()){
         	   	  pst.setString(6, usuario.getCargo());
            }else{
          	  pst.setNull(6,java.sql.Types.VARCHAR);
            }
			  
			  if(usuario.getComentarios()!=null && !usuario.getComentarios().trim().isEmpty()){
         	   	  pst.setString(7, usuario.getComentarios());
            }else{
          	  pst.setNull(7,java.sql.Types.VARCHAR);
            }
			  
			  if(usuario.getEstado()!=-1){
         	   	  pst.setInt(8, usuario.getEstado());
            }else{
          	  pst.setNull(8,java.sql.Types.VARCHAR);
            }
			if (usuario.getFoto() != null) {
         	   BufferedImage image = SwingFXUtils.fromFXImage(usuario.getFoto(), null);
         	   
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

                pst.setBlob(9, bais);
            } else {
                pst.setBlob(9, (Blob) null);
            }
			  
			pst.setString(10, Sesion.DNI); 
			pst.setString(11, usuario.getDni());
			
			pst.executeUpdate();
			this.setNoError(CORRECTO);
			this.setMensaje(MEN_UPDATE_CORRECTO);
     	   	  
         }   
         catch(SQLException e){
        	 this.setNoError(INCORRECTO);
      	   	 this.setMensaje(MEN_UPDATE_INCORRECTO+e.getMessage());
      	   	 e.printStackTrace();
		  }catch(IOException e){
			  this.setNoError(INCORRECTO);
	      	   this.setMensaje(MEN_UPDATE_INCORRECTO+e.getMessage());
			  mensaje=e.getMessage();
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
	
	public void actualizarEstadoUsuario(Usuario usuario){
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement("UPDATE  usuario SET   Estado=? , "
			  		+ " ModificadoPor=?, fmodificacion = now() where DNI=?");
			  
			  pst.setInt(1, usuario.getEstado());
			  pst.setString(2, Sesion.DNI);
			  pst.setString(3,usuario.getDni());
	  
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
	
	public void actualizarPermisos(Usuario usuario){
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement("UPDATE  usuario SET "+
			  		"SeeClientes=?, AddClientes=?,UpdClientes=?, DelClientes=?, "+
			"SeeUsuarios=?, AddUsuarios=?, UpdUsuario=?, DelUsuario=?, "+
			"SeeArticulos=?, AddArticulos=?, UpdArticulos=?, DelArticulos=?, "+
 			"SeeProveedor=?, AddProveedor=?, UpdProveedor=?, DelProveedor=?, "
 			+ "SeeImportante=?, AddImportante=?, UpdImportante=?, DelImportante=?, "
 			+ "SeeContabilidad=?, AddContabilidad=?, UpdContabilidad=?, DelContabilidad=?, "
 			+ "SeeReputacion=?, AddReputacion=?, UpdReputacion=?, DelReputacion=?, "
 			+ "SeeRecomendacion=?, AddRecomendacion=?, UpdRecomendacion=?, DelRecomendacion=?, "
 			+ "SeeEstadistica=?, AddEstadistica=?, UpdEstadistica=?, DelEstadistica=?,  "
 			+ "SeeBackup=?, SeeRestoreBackup=?, "
 			+ "ModificadoPor=?, FModificacion=now() ,"
 			+ ""
 			+ " StatePasswordUsuario=?, "
 			+ " "
 			+ "ConfigPorRecomendacion=?, DevolDevolucion=?,ConfigInterReputacion=?,"
 			+ ""
 			+ "SeeAlquiler=?, AddAlquiler=?, UpdAlquiler=?, DelAlquiler=?,"
 			+ "SeeDevolucion=?,AddDevolucion=?, UpdDevolucion=?, DelDevolucion=?,  "
 			+ ""
 			+ "TransExternasDevolucion=?, ConfigDesCupAlquiler=?   "
 			+ ""
 			+ "where DNI=?;");
			  pst.setInt(1, (usuario.getSeeClientes().isSelected())?1:0);
			  pst.setInt(2, (usuario.getAddClientes().isSelected())?1:0);
			  pst.setInt(3, (usuario.getUpdClientes().isSelected())?1:0);
			  pst.setInt(4, (usuario.getDelClientes().isSelected())?1:0);
			  
			  pst.setInt(5, (usuario.getSeeUsuarios().isSelected())?1:0);
			  pst.setInt(6, (usuario.getAddUsuarios().isSelected())?1:0);
			  pst.setInt(7, (usuario.getUpdUsuarios().isSelected())?1:0);
			  pst.setInt(8, (usuario.getDelUsuarios().isSelected())?1:0);
			  
			  pst.setInt(9, (usuario.getSeeArticulos().isSelected())?1:0);
			  pst.setInt(10, (usuario.getAddArticulos().isSelected())?1:0);
			  pst.setInt(11, (usuario.getUpdArticulos().isSelected())?1:0);
			  pst.setInt(12, (usuario.getDelArticulos().isSelected())?1:0);
			  
			  pst.setInt(13, (usuario.getSeeProveedor().isSelected())?1:0);
			  pst.setInt(14, (usuario.getAddProveedor().isSelected())?1:0);
			  pst.setInt(15, (usuario.getUpdProveedor().isSelected())?1:0);
			  pst.setInt(16, (usuario.getDelProveedor().isSelected())?1:0);

			  pst.setInt(17, (usuario.getSeeImportante().isSelected())?1:0);
			  pst.setInt(18, (usuario.getAddImportante().isSelected())?1:0);
			  pst.setInt(19, (usuario.getUpdImportante().isSelected())?1:0);
			  pst.setInt(20, (usuario.getDelImportante().isSelected())?1:0);
			  
			  pst.setInt(21, (usuario.getSeeContabilidad().isSelected())?1:0);
			  pst.setInt(22, (usuario.getAddContabilidad().isSelected())?1:0);
			  pst.setInt(23, (usuario.getUpdContabilidad().isSelected())?1:0);
			  pst.setInt(24, (usuario.getDelContabilidad().isSelected())?1:0);
			  
			  pst.setInt(25, (usuario.getSeeReputacion().isSelected())?1:0);
			  pst.setInt(26, (usuario.getAddReputacion().isSelected())?1:0);
			  pst.setInt(27, (usuario.getUpdReputacion().isSelected())?1:0);
			  pst.setInt(28, (usuario.getDelReputacion().isSelected())?1:0);
			  
			  pst.setInt(29, (usuario.getSeeRecomendacion().isSelected())?1:0);
			  pst.setInt(30, (usuario.getAddRecomendacion().isSelected())?1:0);
			  pst.setInt(31, (usuario.getUpdRecomendacion().isSelected())?1:0);
			  pst.setInt(32, (usuario.getDelRecomendacion().isSelected())?1:0);
			  
			  pst.setInt(33, (usuario.getSeeEstadistica().isSelected())?1:0);
			  pst.setInt(34, (usuario.getAddEstadistica().isSelected())?1:0);
			  pst.setInt(35, (usuario.getUpdEstadistica().isSelected())?1:0);
			  pst.setInt(36, (usuario.getDelEstadistica().isSelected())?1:0);
			  
			 pst.setInt(37, (usuario.getSeeBackup().isSelected())?1:0);
			 pst.setInt(38, (usuario.getSeeRestoreBackup().isSelected())?1:0);
			  
			  pst.setString(39, Sesion.DNI);
			  
			  pst.setInt(40, (usuario.getStatePasswordUsuarios().isSelected())?1:0);
			  
			  pst.setInt(41, (usuario.getConfigPorRecomendacion().isSelected())?1:0);
			  pst.setInt(42, (usuario.getDevolDevolucion().isSelected())?1:0);
			  pst.setInt(43, (usuario.getConfigInterReputacion().isSelected())?1:0);
			  
			  pst.setInt(44, (usuario.getSeeAlquiler().isSelected())?1:0);
			  pst.setInt(45, (usuario.getAddAlquiler().isSelected())?1:0);
			  pst.setInt(46, (usuario.getUpdAlquiler().isSelected())?1:0);
			  pst.setInt(47, (usuario.getDelAlquiler().isSelected())?1:0);
			  
			  pst.setInt(48, (usuario.getSeeDevolucion().isSelected())?1:0);
			  pst.setInt(49, (usuario.getAddDevolucion().isSelected())?1:0);
			  pst.setInt(50, (usuario.getUpdDevolucion().isSelected())?1:0);
			  pst.setInt(51, (usuario.getDelDevolucion().isSelected())?1:0);
			  
			  pst.setInt(52, (usuario.getTransEsterDevolucion().isSelected())?1:0);
			  pst.setInt(53, (usuario.getConfigDesCupAlquiler().isSelected())?1:0);
			  
			  pst.setString(54, usuario.getDni());

			  
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
	
	public ObservableList<Usuario> seleccionarColeccionUsuario(){
		ObservableList<Usuario> arrayListUsuario=FXCollections.observableArrayList();
		
		Usuario usuario=null;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			 pst=conexionServidor.prepareStatement("SELECT DNI, NomApell, EMail, Telefono, Salario, Direccion, Cargo, "+
			"Cargo, Estado, Clave,  BlobImg, CreadoPor, ModificadoPor, "+
			"FCreacion, FModificacion "+
			//","+
			//" SeeClientes,AddClientes,UpdClientes,DelClientes, "+
			//"SeeUsuarios,AddUsuarios,UpdUsuario,DelUsuario, "+
			//"SeeArticulos, AddArticulos, UpdArticulos, DelArticulos, "+
			//"SeeProveedor, AddProveedor, UpdProveedor, DelProveedor "+
			"FROM usuario WHERE DNI<>'admin'");
			  
			rs=pst.executeQuery();

			while(rs.next()){

				usuario =new Usuario();
				usuario.setDni(rs.getString("DNI"));
				usuario.setNomApell(rs.getString("NomApell"));
				usuario.setEmail(rs.getString("EMail"));
				usuario.setTelefono(rs.getString("Telefono"));
				usuario.setSalario(rs.getDouble("Salario"));
				usuario.setDireccion(rs.getString("Direccion"));
				usuario.setCargo(rs.getString("Cargo"));
				usuario.setComentarios(rs.getString("Cargo"));
				usuario.setEstado(rs.getInt("Estado"));
				usuario.setClave(rs.getString("Clave"));
				/*	Blob imageBlob=rs.getBlob("BlobImg");
          	if(imageBlob!=null){
          		ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(imageBlob.getBytes(1, (int)imageBlob.length()));
          		usuario.setFoto(new Image(byteArrayInputStream));
             	}*/
          	usuario.setCreadoPor(rs.getString("CreadoPor"));
          	usuario.setModificadoPor(rs.getString("ModificadoPor"));
          	usuario.setfCreacion(rs.getDate("FCreacion"));
          	usuario.setfModificacion(rs.getDate("FModificacion"));
          	
          	//usuario.getSeeClientes().setSelected((rs.getInt("SeeClientes")==1)?true:false);
          	//usuario.getAddClientes().setSelected((rs.getInt("AddClientes")==1)?true:false);
          	//usuario.getUpdClientes().setSelected((rs.getInt("UpdClientes")==1)?true:false);
          	//usuario.getDelClientes().setSelected((rs.getInt("DelClientes")==1)?true:false);
          	
          	//usuario.getSeeUsuarios().setSelected((rs.getInt("SeeUsuarios")==1)?true:false);
          	//usuario.getAddUsuarios().setSelected((rs.getInt("AddUsuarios")==1)?true:false);
          	//usuario.getUpdUsuarios().setSelected((rs.getInt("UpdUsuario")==1)?true:false);
          	//usuario.getDelUsuarios().setSelected((rs.getInt("DelUsuario")==1)?true:false);
          	
          	//usuario.getSeeArticulos().setSelected((rs.getInt("SeeArticulos")==1)?true:false);
          	//usuario.getAddArticulos().setSelected((rs.getInt("AddArticulos")==1)?true:false);
          	//usuario.getUpdArticulos().setSelected((rs.getInt("UpdArticulos")==1)?true:false);
          	//usuario.getDelArticulos().setSelected((rs.getInt("DelArticulos")==1)?true:false);
          	
          	//usuario.getSeeProveedor().setSelected((rs.getInt("SeeProveedor")==1)?true:false);
          	//usuario.getAddProveedor().setSelected((rs.getInt("AddProveedor")==1)?true:false);
          	//usuario.getUpdProveedor().setSelected((rs.getInt("UpdProveedor")==1)?true:false);
          	//usuario.getDelProveedor().setSelected((rs.getInt("DelProveedor")==1)?true:false);
          	

          	arrayListUsuario.add(usuario);
          	
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
   	 return arrayListUsuario;		
		
	} 
	
	public ObservableList<Usuario> BuscarBDUsuario(String buscar){
		ObservableList<Usuario> arrayListUsuario = FXCollections.observableArrayList();
		Usuario usuario = null;		
		String url = "SELECT DNI, NomApell, EMail, Telefono, Salario, Direccion, Cargo, "+
			"Cargo, Estado, Clave,  BlobImg, CreadoPor, ModificadoPor, "+
			"FCreacion, FModificacion FROM usuario WHERE (DNI LIKE ? OR NomApell LIKE ?) and DNI<>'admin'";  
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
	        pst = conexionServidor.prepareStatement(url);
	        pst.setString(1, "%" + buscar + "%");
	        pst.setString(2, "%" + buscar + "%");
	        rs = pst.executeQuery();
	        
	        while(rs.next()){

				usuario =new Usuario();
				usuario.setDni(rs.getString("DNI"));
				usuario.setNomApell(rs.getString("NomApell"));
				usuario.setEmail(rs.getString("EMail"));
				usuario.setTelefono(rs.getString("Telefono"));
				usuario.setSalario(rs.getDouble("Salario"));
				usuario.setDireccion(rs.getString("Direccion"));
				usuario.setCargo(rs.getString("Cargo"));
				usuario.setComentarios(rs.getString("Cargo"));
				usuario.setEstado(rs.getInt("Estado"));
				usuario.setClave(rs.getString("Clave"));
				Blob imageBlob=rs.getBlob("BlobImg");
	          	if(imageBlob!=null){
	          		ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(imageBlob.getBytes(1, (int)imageBlob.length()));
	          		usuario.setFoto(new Image(byteArrayInputStream));
	             	}
	          	usuario.setCreadoPor(rs.getString("CreadoPor"));
	          	usuario.setModificadoPor(rs.getString("ModificadoPor"));
	          	usuario.setfCreacion(rs.getDate("FCreacion"));
	          	usuario.setfModificacion(rs.getDate("FModificacion"));
	   
	          	arrayListUsuario.add(usuario);
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
		return arrayListUsuario;
	}
	
	public Usuario seleccionarUsuario(String dni){
		  Usuario usuario=null;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			 pst=conexionServidor.prepareStatement("SELECT DNI, NomApell, EMail, Telefono, Salario, Direccion, Cargo, "+
			"Cargo, Estado, Clave,  BlobImg, CreadoPor, ModificadoPor, "+
			"FCreacion, FModificacion, "+
			"SeeClientes,AddClientes,UpdClientes,DelClientes, "+
			"SeeUsuarios,AddUsuarios,UpdUsuario,DelUsuario, StatePasswordUsuario, "+
			"SeeArticulos, AddArticulos, UpdArticulos, DelArticulos, "+
 			"SeeProveedor, AddProveedor, UpdProveedor, DelProveedor, "+
 			"SeeImportante, AddImportante, UpdImportante, DelImportante, "+
 			"SeeAlquiler, AddAlquiler, UpdAlquiler, DelAlquiler,ConfigDesCupAlquiler, "+
 			"SeeDevolucion, AddDevolucion, UpdDevolucion, DelDevolucion, DevolDevolucion, TransExternasDevolucion,"+
 			"SeeContabilidad, AddContabilidad, UpdContabilidad, DelContabilidad, "+
 			"SeeReputacion, AddReputacion, UpdReputacion, DelReputacion, ConfigInterReputacion,"+
 			"SeeRecomendacion, AddRecomendacion, UpdRecomendacion, DelRecomendacion, ConfigPorRecomendacion, "+
 			"SeeEstadistica, AddEstadistica, UpdEstadistica, DelEstadistica, "+
 			"SeeBackup, SeeRestoreBackup "+
			"FROM usuario where DNI=?;");
			  
     	   	pst.setString(1, dni);
			rs=pst.executeQuery();
			while(rs.next()){
				usuario =new Usuario();
				usuario.setDni(rs.getString("DNI"));
				usuario.setNomApell(rs.getString("NomApell"));
				usuario.setEmail(rs.getString("EMail"));
				usuario.setTelefono(rs.getString("Telefono"));
				usuario.setSalario(rs.getDouble("Salario"));
				usuario.setDireccion(rs.getString("Direccion"));
				usuario.setCargo(rs.getString("Cargo"));
				usuario.setComentarios(rs.getString("Cargo"));
				usuario.setEstado(rs.getInt("Estado"));
				usuario.setClave(rs.getString("Clave"));
				Blob imageBlob=rs.getBlob("BlobImg");
            	if(imageBlob!=null){
            		ByteArrayInputStream byteArrayInputStream=new ByteArrayInputStream(imageBlob.getBytes(1, (int)imageBlob.length()));
            		usuario.setFoto(new Image(byteArrayInputStream));
               	}
            	usuario.setCreadoPor(rs.getString("CreadoPor"));
            	usuario.setModificadoPor(rs.getString("ModificadoPor"));
            	usuario.setfCreacion(rs.getDate("FCreacion"));
            	usuario.setfModificacion(rs.getDate("FModificacion"));
            	usuario.getSeeClientes().setSelected((rs.getInt("SeeClientes")==1)?true:false);
            	usuario.getAddClientes().setSelected((rs.getInt("AddClientes")==1)?true:false);
            	usuario.getUpdClientes().setSelected((rs.getInt("UpdClientes")==1)?true:false);
            	usuario.getDelClientes().setSelected((rs.getInt("DelClientes")==1)?true:false);
            	
            	usuario.getSeeUsuarios().setSelected((rs.getInt("SeeUsuarios")==1)?true:false);
            	usuario.getAddUsuarios().setSelected((rs.getInt("AddUsuarios")==1)?true:false);
            	usuario.getUpdUsuarios().setSelected((rs.getInt("UpdUsuario")==1)?true:false);
            	usuario.getDelUsuarios().setSelected((rs.getInt("DelUsuario")==1)?true:false);
            	usuario.getStatePasswordUsuarios().setSelected((rs.getInt("StatePasswordUsuario")==1)?true:false);
            	
            	
            	usuario.getSeeArticulos().setSelected((rs.getInt("SeeArticulos")==1)?true:false);
            	usuario.getAddArticulos().setSelected((rs.getInt("AddArticulos")==1)?true:false);
            	usuario.getUpdArticulos().setSelected((rs.getInt("UpdArticulos")==1)?true:false);
            	usuario.getDelArticulos().setSelected((rs.getInt("DelArticulos")==1)?true:false);
            	
            	usuario.getSeeProveedor().setSelected((rs.getInt("SeeProveedor")==1)?true:false);
            	usuario.getAddProveedor().setSelected((rs.getInt("AddProveedor")==1)?true:false);
            	usuario.getUpdProveedor().setSelected((rs.getInt("UpdProveedor")==1)?true:false);
            	usuario.getDelProveedor().setSelected((rs.getInt("DelProveedor")==1)?true:false);
            	
            	usuario.getSeeImportante().setSelected((rs.getInt("SeeImportante")==1)?true:false);
            	usuario.getAddImportante().setSelected((rs.getInt("AddImportante")==1)?true:false);
            	usuario.getUpdImportante().setSelected((rs.getInt("UpdImportante")==1)?true:false);
            	usuario.getDelImportante().setSelected((rs.getInt("DelImportante")==1)?true:false);

            	usuario.getSeeAlquiler().setSelected((rs.getInt("SeeAlquiler")==1)?true:false);
            	usuario.getAddAlquiler().setSelected((rs.getInt("AddAlquiler")==1)?true:false);
            	usuario.getUpdAlquiler().setSelected((rs.getInt("UpdAlquiler")==1)?true:false);
            	usuario.getDelAlquiler().setSelected((rs.getInt("DelAlquiler")==1)?true:false);
            	usuario.getConfigDesCupAlquiler().setSelected((rs.getInt("ConfigDesCupAlquiler")==1)?true:false);

            	
            	usuario.getSeeDevolucion().setSelected((rs.getInt("SeeDevolucion")==1)?true:false);
            	usuario.getAddDevolucion().setSelected((rs.getInt("AddDevolucion")==1)?true:false);
            	usuario.getUpdDevolucion().setSelected((rs.getInt("UpdDevolucion")==1)?true:false);
            	usuario.getDelDevolucion().setSelected((rs.getInt("DelDevolucion")==1)?true:false);
            	usuario.getDevolDevolucion().setSelected((rs.getInt("DevolDevolucion")==1)?true:false);
            	usuario.getTransEsterDevolucion().setSelected((rs.getInt("TransExternasDevolucion")==1)?true:false);
            	
            	
            	usuario.getSeeContabilidad().setSelected((rs.getInt("SeeContabilidad")==1)?true:false);
            	usuario.getAddContabilidad().setSelected((rs.getInt("AddContabilidad")==1)?true:false);
            	usuario.getUpdContabilidad().setSelected((rs.getInt("UpdContabilidad")==1)?true:false);
            	usuario.getDelContabilidad().setSelected((rs.getInt("DelContabilidad")==1)?true:false);
            	
            	usuario.getSeeReputacion().setSelected((rs.getInt("SeeReputacion")==1)?true:false);
            	usuario.getAddReputacion().setSelected((rs.getInt("AddReputacion")==1)?true:false);
            	usuario.getUpdReputacion().setSelected((rs.getInt("UpdReputacion")==1)?true:false);
            	usuario.getDelReputacion().setSelected((rs.getInt("DelReputacion")==1)?true:false);
            	usuario.getConfigInterReputacion().setSelected((rs.getInt("ConfigInterReputacion")==1)?true:false);


            	usuario.getSeeRecomendacion().setSelected((rs.getInt("SeeRecomendacion")==1)?true:false);
            	usuario.getAddRecomendacion().setSelected((rs.getInt("AddRecomendacion")==1)?true:false);
            	usuario.getUpdRecomendacion().setSelected((rs.getInt("UpdRecomendacion")==1)?true:false);
            	usuario.getDelRecomendacion().setSelected((rs.getInt("DelRecomendacion")==1)?true:false);
            	usuario.getConfigPorRecomendacion().setSelected((rs.getInt("ConfigPorRecomendacion")==1)?true:false);
            	
            	
            	usuario.getSeeEstadistica().setSelected((rs.getInt("SeeEstadistica")==1)?true:false);
            	usuario.getAddEstadistica().setSelected((rs.getInt("AddEstadistica")==1)?true:false);
            	usuario.getUpdEstadistica().setSelected((rs.getInt("UpdEstadistica")==1)?true:false);
            	usuario.getDelEstadistica().setSelected((rs.getInt("DelEstadistica")==1)?true:false);
            	
            	usuario.getSeeBackup().setSelected((rs.getInt("SeeBackup")==1)?true:false);
            	usuario.getSeeRestoreBackup().setSelected((rs.getInt("SeeRestoreBackup")==1)?true:false);
			}
			this.usuario=usuario;
			return usuario;
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
     	 return null;
	}
	
	
	public String seleccionarContrasena(){
		
		String cotrasena=null;
		String url = "select Clave from usuario WHERE Dni=?";
		PreparedStatement pst = null;
		ResultSet rs=null;
		try {
			pst = conexionServidor.prepareStatement(url);
	        pst.setString(1, Sesion.DNI);
	        rs=pst.executeQuery();
	        if(rs.next()){
	        	cotrasena=rs.getString("Clave");
	        }
	        this.setNoError(CORRECTO);
			this.setMensaje(MEN_UPDATE_CORRECTO);

		} catch (Exception e) {
			this.setNoError(INCORRECTO);
     	   	this.setMensaje(MEN_UPDATE_INCORRECTO+e.getMessage());
     	   	e.printStackTrace();
		} finally{
			try {
				if (pst!=null) {
					pst.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return cotrasena;
	}
	public void actualizarDatosContrasena(String nuevaContrasenaEncriptada){

		String url = "UPDATE usuario SET Clave=? WHERE Dni=?";
		PreparedStatement pst = null;
		
		try {
			pst = conexionServidor.prepareStatement(url);
	        pst.setString(1, nuevaContrasenaEncriptada);
	        pst.setString(2, Sesion.DNI);
	        pst.executeUpdate();
	        this.setNoError(CORRECTO);
			this.setMensaje(MEN_UPDATE_CORRECTO);

		} catch (Exception e) {
			this.setNoError(INCORRECTO);
     	   	this.setMensaje(MEN_UPDATE_INCORRECTO+e.getMessage());
     	   	e.printStackTrace();
		} finally{
			try {
				if (pst!=null) {
					pst.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public void actualizarDatosContrasena(String nuevaContrasenaEncriptada,String DNI){

		String url = "UPDATE usuario SET Clave=? WHERE Dni=?";
		PreparedStatement pst = null;
		
		try {
			pst = conexionServidor.prepareStatement(url);
	        pst.setString(1, nuevaContrasenaEncriptada);
	        pst.setString(2, DNI);
	        pst.executeUpdate();
	        this.setNoError(CORRECTO);
			this.setMensaje(MEN_UPDATE_CORRECTO);

		} catch (Exception e) {
			this.setNoError(INCORRECTO);
     	   	this.setMensaje(MEN_UPDATE_INCORRECTO+e.getMessage());
     	   	e.printStackTrace();
		} finally{
			try {
				if (pst!=null) {
					pst.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
}
