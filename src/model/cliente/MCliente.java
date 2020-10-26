package model.cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dal.ArticuloAlquilado;
import dal.Cliente;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MPadre;
import sesion.Sesion;

public class MCliente extends MPadre {
	
	private Cliente cliente=null;
	private ArticuloAlquilado aa = null;
	
	public int agregarCliente(Cliente cliente){
		
		int idUltimo = -1;
		this.cliente=cliente;
		String url = "INSERT INTO CLIENTE(TipoCliente, DNI, NombresApellidos, FechaNaci, NroCelular, Direccion, Referencia, EMail, Reputacion, Comentarios, CreadoPor) "
				+ "VALUES(?,?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url,PreparedStatement.RETURN_GENERATED_KEYS);
			
			pst.setString(1, (cliente.getCli_tipoCliente()!=null && !cliente.getCli_tipoCliente().isEmpty())?cliente.getCli_tipoCliente().trim():(String)null);
			pst.setString(2,(cliente.getCli_dni()!=null && !cliente.getCli_dni().trim().isEmpty())?cliente.getCli_dni().trim():(String)null);
			pst.setString(3,(cliente.getCli_apellNom()!=null && !cliente.getCli_apellNom().trim().isEmpty())?cliente.getCli_apellNom().trim():(String)null);
			pst.setDate(4,(cliente.getCli_fechNac()!=null)?new java.sql.Date(cliente.getCli_fechNac().getTime()):(java.sql.Date)null);
			pst.setString(5,(cliente.getCli_telefono()!=null && !cliente.getCli_telefono().trim().isEmpty())?cliente.getCli_telefono().trim():(String)null);
			pst.setString(6,(cliente.getCli_direccion()!=null && !cliente.getCli_direccion().trim().isEmpty())?cliente.getCli_direccion().trim():(String)null);
			pst.setString(7,(cliente.getCli_referencia()!=null && !cliente.getCli_referencia().trim().isEmpty())?cliente.getCli_referencia().trim():(String)null);
			pst.setString(8,(cliente.getCli_email()!=null && !cliente.getCli_email().trim().isEmpty())?cliente.getCli_email().trim():(String)null);
			pst.setInt(9, (cliente.getCli_reputacion()!=-1)?cliente.getCli_reputacion():null);
			pst.setString(10,(cliente.getCli_comentarios()!=null && !cliente.getCli_comentarios().trim().isEmpty())?cliente.getCli_comentarios().trim():(String)null);
			pst.setString(11, Sesion.DNI);
			pst.executeUpdate();
			rs=pst.getGeneratedKeys();
			while (rs.next()){
				this.cliente.setCli_id(rs.getInt(1));
				idUltimo=rs.getInt(1);
			}
			this.setNoError(CORRECTO);
			this.setMensaje(MEN_INSERT_CORRECTO);
		}catch (Exception e) {
			this.setNoError(INCORRECTO);
     	    this.setMensaje(MEN_INSERT_INCORRECTO+e.getMessage());
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
		return idUltimo;
	}
	
	public void actualizarCliente(Cliente cliente){
		
		this.cliente=cliente;
		String url = "UPDATE  CLIENTE SET TipoCliente=?, DNI=?, NombresApellidos=?, FechaNaci=?, NroCelular=?, Direccion=?, Referencia=?, EMail=?, Comentarios=?, ModificadoPor=?, FModificacion=CURDATE() WHERE id=?;";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url);
			
			pst.setString(1,(cliente.getCli_tipoCliente()!=null && !cliente.getCli_tipoCliente().trim().isEmpty())?cliente.getCli_tipoCliente().trim():(String)null);
			pst.setString(2,(cliente.getCli_dni()!=null && !cliente.getCli_dni().trim().isEmpty())?cliente.getCli_dni().trim():(String)null);
			pst.setString(3,(cliente.getCli_apellNom()!=null && !cliente.getCli_apellNom().trim().isEmpty())?cliente.getCli_apellNom().trim():(String)null);
			pst.setDate(4,(cliente.getCli_fechNac()!=null)?new java.sql.Date(cliente.getCli_fechNac().getTime()):(java.sql.Date)null);
			pst.setString(5,(cliente.getCli_telefono()!=null && !cliente.getCli_telefono().trim().isEmpty())?cliente.getCli_telefono().trim():(String)null);
			pst.setString(6,(cliente.getCli_direccion()!=null && !cliente.getCli_direccion().trim().isEmpty())?cliente.getCli_direccion().trim():(String)null);
			pst.setString(7,(cliente.getCli_referencia()!=null && !cliente.getCli_referencia().trim().isEmpty())?cliente.getCli_referencia().trim():(String)null);
			pst.setString(8,(cliente.getCli_email()!=null && !cliente.getCli_email().trim().isEmpty())?cliente.getCli_email().trim():(String)null);
			pst.setString(9,(cliente.getCli_comentarios()!=null && !cliente.getCli_comentarios().trim().isEmpty())?cliente.getCli_comentarios().trim():(String)null);
			pst.setString(10, Sesion.DNI);
			pst.setInt(11, cliente.getCli_id());
			pst.executeUpdate();
			
			this.setNoError(CORRECTO);
			this.setMensaje(MEN_UPDATE_CORRECTO);
		
		}catch (Exception e) {
			this.setNoError(INCORRECTO);
       	 	this.setMensaje(MEN_UPDATE_INCORRECTO+e.getMessage());
			e.printStackTrace();
		}finally{
			try{
				if(pst!=null){
					pst.close();
				}
				if(rs!=null){
      	   			rs.close();
      	   		}
       			
   			}catch (SQLException e) {
				e.printStackTrace();
			}
		}			
	}
	
	public void eliminarCliente(int id) {
		String url = "DELETE FROM cliente WHERE Id=?;";
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
	
	public void actualizarReputacionCliente(Cliente cliente){
		
		this.cliente=cliente;
		String url = "UPDATE  CLIENTE SET reputacion=?, ModificadoPor=?, FModificacion=CURDATE() WHERE Id=?;";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url);
			
			pst.setInt(1,(cliente.getCli_reputacion()!=-1?cliente.getCli_reputacion():null));
			pst.setString(2, Sesion.DNI);
			pst.setInt(3, cliente.getCli_id());
			pst.executeUpdate();
			
			this.setNoError(CORRECTO);
			this.setMensaje(MEN_UPDATE_CORRECTO);
		
		}catch (Exception e) {
			this.setNoError(INCORRECTO);
       	 	this.setMensaje(MEN_UPDATE_INCORRECTO+e.getMessage());
			e.printStackTrace();
		}finally{
			try{
				if(pst!=null){
					pst.close();
				}
				if(rs!=null){
      	   			rs.close();
      	   		}
       			
   			}catch (SQLException e) {
				e.printStackTrace();
			}
		}			
	}
	
	public ObservableList<Cliente> seleccionarColeccionCliente(){
		ObservableList<Cliente> arrayListCliente=FXCollections.observableArrayList();
		
		Cliente cliente = null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		  try{
			 pst=conexionServidor.prepareStatement("SELECT Id, Codigo, TipoCliente, DNI, NombresApellidos, FechaNaci, Direccion, Referencia, "
			 		+ "NroCelular, EMail, Comentarios, Reputacion, CreadoPor, ModificadoPor, FCreacion, FModificacion FROM CLIENTE;"); 
			rs=pst.executeQuery();

			while(rs.next()){

				cliente =new Cliente();
				cliente.setCli_id(rs.getInt("Id"));
				cliente.setCli_codigo(rs.getString("Codigo"));
				cliente.setCli_tipoCliente(rs.getString("TipoCliente"));
				cliente.setCli_dni(rs.getString("DNI"));
				cliente.setCli_apellNom(rs.getString("NombresApellidos"));
				cliente.setCli_fechNac(rs.getDate("FechaNaci"));
				cliente.setCli_direccion(rs.getString("Direccion"));
				cliente.setCli_referencia(rs.getString("Referencia"));
				cliente.setCli_telefono(rs.getString("NroCelular"));
				cliente.setCli_email(rs.getString("EMail"));
				cliente.setCli_email(rs.getString("Comentarios"));
				cliente.setCli_reputacion(rs.getInt("Reputacion"));
				cliente.setCreadoPor(rs.getString("CreadoPor"));
				cliente.setModificadoPor(rs.getString("ModificadoPor"));
				cliente.setfCreacion(rs.getDate("FCreacion"));
				cliente.setfModificacion(rs.getDate("FModificacion"));
				arrayListCliente.add(cliente);
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
		  return arrayListCliente;		
	}
	
	public ObservableList<Cliente> BuscarBDCliente(String buscar){
		ObservableList<Cliente> arrayListCliente=FXCollections.observableArrayList();
		Cliente cliente=null;
		String url = "SELECT Id, Codigo, TipoCliente, DNI, NombresApellidos, FechaNaci, Direccion, Referencia, NroCelular, EMail, Comentarios, Reputacion, CreadoPor, ModificadoPor, FCreacion, FModificacion FROM CLIENTE "
						+ "WHERE Codigo LIKE ? OR DNI LIKE ? OR NombresApellidos LIKE ?;";  
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
	        pst = conexionServidor.prepareStatement(url);
	        pst.setString(1, "%" + buscar + "%");
	        pst.setString(2, "%" + buscar + "%");
	        pst.setString(3, "%" + buscar + "%");
	        rs = pst.executeQuery();
	        
	        while(rs.next()){
	        	cliente =new Cliente();
	        	cliente.setCli_id(rs.getInt("Id"));
	        	cliente.setCli_codigo(rs.getString("Codigo"));
	        	cliente.setCli_tipoCliente(rs.getString("TipoCliente"));
	        	cliente.setCli_dni(rs.getString("DNI"));
	        	cliente.setCli_apellNom(rs.getString("NombresApellidos"));
				cliente.setCli_fechNac(rs.getDate("FechaNaci"));
				cliente.setCli_direccion(rs.getString("Direccion"));
				cliente.setCli_referencia(rs.getString("Referencia"));
				cliente.setCli_telefono(rs.getString("NroCelular"));
				cliente.setCli_email(rs.getString("EMail"));
				cliente.setCli_email(rs.getString("Comentarios"));
				cliente.setCli_reputacion(rs.getInt("Reputacion"));
	        	cliente.setCreadoPor(rs.getString("CreadoPor"));
	        	cliente.setModificadoPor(rs.getString("ModificadoPor"));
	        	cliente.setfCreacion(rs.getDate("FCreacion"));
	        	cliente.setfModificacion(rs.getDate("FModificacion"));
				arrayListCliente.add(cliente);   
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
		return arrayListCliente;
	}
	
	public Cliente seleccionarCliente(int id){
		Cliente cliente = null;
		String url = "SELECT Id, Codigo, TipoCliente, DNI, NombresApellidos, FechaNaci, NroCelular, Direccion, Referencia, EMail, Comentarios, Reputacion, "
				+ "CreadoPor, ModificadoPor, FCreacion, FModificacion from CLIENTE where id=?;";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url);
			pst.setInt(1,id);
			rs=pst.executeQuery();
			while (rs.next()){
				cliente =new Cliente();
	        	cliente.setCli_id(rs.getInt("Id"));
	        	cliente.setCli_codigo(rs.getString("Codigo"));
	        	cliente.setCli_tipoCliente(rs.getString("TipoCliente"));
	        	cliente.setCli_dni(rs.getString("DNI"));
	        	cliente.setCli_apellNom(rs.getString("NombresApellidos"));
				cliente.setCli_fechNac(rs.getDate("FechaNaci"));
				cliente.setCli_telefono(rs.getString("NroCelular"));
				cliente.setCli_direccion(rs.getString("Direccion"));
				cliente.setCli_referencia(rs.getString("Referencia"));
				cliente.setCli_email(rs.getString("EMail"));
				cliente.setCli_comentarios(rs.getString("Comentarios"));
				cliente.setCli_reputacion(rs.getInt("Reputacion"));
	        	cliente.setCreadoPor(rs.getString("CreadoPor"));
	        	cliente.setModificadoPor(rs.getString("ModificadoPor"));
	        	cliente.setfCreacion(rs.getDate("FCreacion"));
	        	cliente.setfModificacion(rs.getDate("FModificacion"));
			}
						
		}catch (Exception e) {
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
		
		return cliente;
	}
	
	public ObservableList<ArticuloAlquilado> seleccionarColeccionArticulosAlquilados(int idCliente){
		ObservableList<ArticuloAlquilado> arrayListAa=FXCollections.observableArrayList();
		
		ArticuloAlquilado aa = null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		  try{
			 pst=conexionServidor.prepareStatement("SELECT boleta.id as Id, CONCAT(Serie,'-',LPAD(Numero,8,0)) as boleta, Fecha, Cant, Codigo, "
			 		+ "Descripcion, PrecioUnit, Importe, IFNULL(ROUND(((Importe*TotalPagar)/Subtotal),2),0.00) as impDesc FROM boleta "
			 		+ "INNER JOIN detalleboleta ON boleta.Id=detalleboleta.IdBoleta WHERE IdCliente=? AND EstadoAccion='3'"); 
			 pst.setInt(1, idCliente);
			 rs=pst.executeQuery();

			while(rs.next()){

				aa =new ArticuloAlquilado();
				aa.setAaIdBoleta(rs.getInt("Id"));
				aa.setAaSerNroBoleta(rs.getString("boleta"));
				aa.setAaFecha(rs.getString("fecha"));
				aa.setAaCant(rs.getInt("Cant"));
				aa.setAaCodigo(rs.getString("Codigo"));
				aa.setAaDescripcion(rs.getString("Descripcion"));
				aa.setAaPrecioUnit(rs.getString("PrecioUnit"));
				aa.setAaImporte(rs.getString("Importe"));
				aa.setAaImpDesc(rs.getString("impDesc"));
				arrayListAa.add(aa);
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
		  return arrayListAa;		
	}
	
	public ObservableList<ArticuloAlquilado> BuscarArticulosAlquilados(String buscar, int idCliente){
		ObservableList<ArticuloAlquilado> arrayListAa=FXCollections.observableArrayList();
		ArticuloAlquilado aa = null;
		String url = null;
		url = "SELECT boleta.id as Id, CONCAT(Serie,'-',LPAD(Numero,8,0)) as boleta, Cant, Codigo, "
			 + "Descripcion, PrecioUnit, Importe, IFNULL(ROUND(((Importe*TotalPagar)/Subtotal),2), 0.00) as impDesc FROM boleta "
			 + "INNER JOIN detalleboleta ON boleta.Id=detalleboleta.IdBoleta WHERE IdCliente=? AND EstadoAccion='3' AND "
			 + "(Codigo LIKE ? OR Descripcion LIKE ? OR CONCAT(Serie,'-',LPAD(Numero,8,0)) LIKE ?);";
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
	        pst = conexionServidor.prepareStatement(url);
        	pst.setInt(1, idCliente);
        	pst.setString(2, "%" + buscar + "%");
	        pst.setString(3, "%" + buscar + "%");
	        pst.setString(4, "%" + buscar + "%");
	        rs = pst.executeQuery();
	        
	        while(rs.next()){
	        	aa =new ArticuloAlquilado();
				aa.setAaIdBoleta(rs.getInt("Id"));
				aa.setAaSerNroBoleta(rs.getString("boleta"));
				aa.setAaCant(rs.getInt("Cant"));
				aa.setAaCodigo(rs.getString("Codigo"));
				aa.setAaDescripcion(rs.getString("Descripcion"));
				aa.setAaPrecioUnit(rs.getString("PrecioUnit"));
				aa.setAaImporte(rs.getString("Importe"));
				aa.setAaImpDesc(rs.getString("impDesc"));
				arrayListAa.add(aa);  
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
		return arrayListAa;
	}
	
	public ArticuloAlquilado importesFacturados(int idCliente){
		ArticuloAlquilado aa = null;
		String url = null;
		url = "SELECT IFNULL(SUM(Importe), 0.00) AS tImporte, IFNULL(ROUND(SUM((Importe*TotalPagar)/Subtotal),2), 0.00) AS tImpDesc FROM boleta INNER JOIN "
				+ "detalleboleta ON boleta.Id=detalleboleta.IdBoleta WHERE IdCliente=? AND EstadoAccion='3';";
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			pst = conexionServidor.prepareStatement(url);
			pst.setInt(1, idCliente);
	        rs=pst.executeQuery();
	        
	        while(rs.next()){
	        	aa =new ArticuloAlquilado();
				aa.setAaImpSinDesc(rs.getString("tImporte"));
				aa.setAaImpConDesc(rs.getString("tImpDesc"));
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
		  		 if (rs!=null) {
					pst.close();
				}
		  	  }catch (SQLException e) {
		  		   e.printStackTrace();
			  } 
	     }
		return aa;
	}
	
	public String traerCodigoCliente(){
		String cli=null;
		String url = "SELECT IFNULL(CONCAT(LEFT(MAX(CODIGO), 1), LPAD(RIGHT(MAX(CODIGO), 3)+2, 3, '0')),'A002') AS COD from CLIENTE;";  
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			pst = conexionServidor.prepareStatement(url);
	        rs=pst.executeQuery();
	        while(rs.next()){
	        	cli = rs.getString("COD");
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
		  		 if (rs!=null) {
					pst.close();
				}
		  	  }catch (SQLException e) {
		  		   e.printStackTrace();
			  } 
	     }
		return cli;
	}
	
	

	public Cliente seleccionarCliente(String codigoDNI){
		Cliente cliente = null;
		String url = "SELECT Id, Codigo, TipoCliente, DNI, NombresApellidos, FechaNaci, NroCelular, Direccion, Referencia, EMail, Comentarios, Reputacion, "
				+ "CreadoPor, ModificadoPor, FCreacion, FModificacion from CLIENTE where Codigo=? OR DNI=?;";
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			
			pst=conexionServidor.prepareStatement(url);
			pst.setString(1,codigoDNI);
			pst.setString(2,codigoDNI);
			rs=pst.executeQuery();
			while (rs.next()){
				cliente =new Cliente();
	        	cliente.setCli_id(rs.getInt("Id"));
	        	cliente.setCli_codigo(rs.getString("Codigo"));
	        	cliente.setCli_tipoCliente(rs.getString("TipoCliente"));
	        	cliente.setCli_dni(rs.getString("DNI"));
	        	cliente.setCli_apellNom(rs.getString("NombresApellidos"));
				cliente.setCli_fechNac(rs.getDate("FechaNaci"));
				cliente.setCli_telefono(rs.getString("NroCelular"));
				cliente.setCli_direccion(rs.getString("Direccion"));
				cliente.setCli_referencia(rs.getString("Referencia"));
				cliente.setCli_email(rs.getString("EMail"));
				cliente.setCli_comentarios(rs.getString("Comentarios"));
				cliente.setCli_reputacion(rs.getInt("Reputacion"));
	        	cliente.setCreadoPor(rs.getString("CreadoPor"));
	        	cliente.setModificadoPor(rs.getString("ModificadoPor"));
	        	cliente.setfCreacion(rs.getDate("FCreacion"));
	        	cliente.setfModificacion(rs.getDate("FModificacion"));
			}
						
		}catch (Exception e) {
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
		
		return cliente;
	}
	
	public int traerReputacion(){
		int reputacion=-1;
		String url = "SELECT VMaxVerde FROM reputacion;";
		PreparedStatement pst=null;
		ResultSet rs=null;
		try {
			pst = conexionServidor.prepareStatement(url);
			rs = pst.executeQuery();
			while(rs.next()){
				reputacion = rs.getInt("VMaxVerde");
			}
			noError=1;
		}catch (Exception e) {
			e.printStackTrace();
			noError=0;
		}finally {
			try {
				if (pst!=null) {
					pst.close();
				}
				if (rs!=null) {
					rs.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return reputacion;
	}
	
}
