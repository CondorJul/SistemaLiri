package model.recomendacion;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dal.Egreso;
import dal.Recomendacion;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MPadre;
import sesion.Sesion;

public class MRecomendacion extends MPadre {
	
	private Recomendacion recomendacion = null;
	private Egreso egreso = null;
	
	public ObservableList<Recomendacion> seleccionarColeccionRecomendacion(int idCliente, int estadoPago){
		ObservableList<Recomendacion> arrayListCliente=FXCollections.observableArrayList();
		
		this.recomendacion = null;
		String url = null;
		if (estadoPago==0) {
			url = "select cliente.Id as idCli, DNI, Codigo, NombresApellidos, boleta.Id as idBol, concat(serie, ' - ',lpad(numero, 8, '0')) as boleta, Fecha, TotalPagar, PorcentajePago as pago "
					+ "from cliente inner join boleta ON cliente.Id=boleta.IdCliente where boleta.IdClienteRec=? and boleta.EstadoAccion=3 and boleta.pagoRecom=0 and EstadoEliminado=0;";
		}else if (estadoPago==1) {
			url = "select cliente.Id as idCli, DNI, Codigo, NombresApellidos, boleta.Id as idBol, concat(serie, ' - ',lpad(numero, 8, '0')) as boleta, Fecha, TotalPagar, PorcentajePago as pago "
					+ "from cliente inner join boleta ON cliente.Id=boleta.IdCliente where boleta.IdClienteRec=? and boleta.EstadoAccion=3 and boleta.pagoRecom=1 and EstadoEliminado=0;";
		}else if (estadoPago==2) {
			url = "select cliente.Id as idCli, DNI, Codigo, NombresApellidos, boleta.Id as idBol, concat(serie, ' - ',lpad(numero, 8, '0')) as boleta, Fecha, TotalPagar, PorcentajePago as pago "
					+ "from cliente inner join boleta ON cliente.Id=boleta.IdCliente where boleta.EstadoAccion=3 and EstadoEliminado=0;";
		}
		
		PreparedStatement pst=null;
		ResultSet rs=null;
		  try{
			pst=conexionServidor.prepareStatement(url);
			if (estadoPago!=2) {
				pst.setInt(1, idCliente);
			}
			rs=pst.executeQuery();

			while(rs.next()){

				recomendacion =new Recomendacion();
				recomendacion.setRecom_idCli(rs.getInt("idCli"));
				recomendacion.setRecom_codCli(rs.getString("Codigo"));
				recomendacion.setRecom_dniCli(rs.getString("DNI"));
				recomendacion.setRecom_apellNomCli(rs.getString("NombresApellidos"));
				recomendacion.setRecom_bolId(rs.getInt("idBol"));
				recomendacion.setRecom_bolSerNro(rs.getString("boleta"));
				recomendacion.setRecom_bolFech(rs.getDate("Fecha"));
				recomendacion.setRecom_bolImporte(rs.getString("TotalPagar"));
				recomendacion.setRecom_bolPago(rs.getString("pago"));
				arrayListCliente.add(recomendacion);
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
	
	public ObservableList<Recomendacion> BuscarBDProximosPagos(String buscar, int idCliente, int estadoPago){
		ObservableList<Recomendacion> arrayListRecomPagos=FXCollections.observableArrayList();
		this.recomendacion = null;
		String url = null;
		if (estadoPago==0) {
			url = "select cliente.Id as idCli, DNI, Codigo, NombresApellidos, boleta.Id as idBol, concat(serie, ' - ',lpad(numero, 8, '0')) as boleta, Fecha, TotalPagar, PorcentajePago as pago "
					+ "from cliente inner join boleta ON cliente.Id=boleta.IdCliente where boleta.IdClienteRec=? and boleta.EstadoAccion=3 and boleta.pagoRecom=0 and EstadoEliminado=0 "
					+ "and (Codigo LIKE ? OR NombresApellidos LIKE ? OR concat(serie, ' - ',lpad(numero, 8, '0')) LIKE ?);";
		}else if(estadoPago==1){
			url = "select cliente.Id as idCli, DNI, Codigo, NombresApellidos, boleta.Id as idBol, concat(serie, ' - ',lpad(numero, 8, '0')) as boleta, Fecha, TotalPagar, PorcentajePago as pago "
					+ "from cliente inner join boleta ON cliente.Id=boleta.IdCliente where boleta.IdClienteRec=? and boleta.EstadoAccion=3 and boleta.pagoRecom=1 and EstadoEliminado=0 "
					+ "and (Codigo LIKE ? OR NombresApellidos LIKE ? OR concat(serie, ' - ',lpad(numero, 8, '0')) LIKE ?);";
		}else if (estadoPago==2) {
			url = "select cliente.Id as idCli, DNI, Codigo, NombresApellidos, boleta.Id as idBol, concat(serie, ' - ',lpad(numero, 8, '0')) as boleta, Fecha, TotalPagar, PorcentajePago as pago "
					+ "from cliente inner join boleta ON cliente.Id=boleta.IdCliente where boleta.EstadoAccion=3 and EstadoEliminado=0 "
					+ "and (Codigo LIKE ? OR NombresApellidos LIKE ? OR concat(serie, ' - ',lpad(numero, 8, '0')) LIKE ?);";
		}
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
	        pst = conexionServidor.prepareStatement(url);
	        if (estadoPago!=2) {
	        	pst.setInt(1, idCliente);
	        	pst.setString(2, "%" + buscar + "%");
		        pst.setString(3, "%" + buscar + "%");
		        pst.setString(4, "%" + buscar + "%");
			}else{
				pst.setString(1, "%" + buscar + "%");
	        	pst.setString(2, "%" + buscar + "%");
	        	pst.setString(3, "%" + buscar + "%");
			}
	        rs = pst.executeQuery();
	        
	        while(rs.next()){
	        	recomendacion =new Recomendacion();
				recomendacion.setRecom_idCli(rs.getInt("idCli"));
				recomendacion.setRecom_codCli(rs.getString("Codigo"));
				recomendacion.setRecom_dniCli(rs.getString("DNI"));
				recomendacion.setRecom_apellNomCli(rs.getString("NombresApellidos"));
				recomendacion.setRecom_bolId(rs.getInt("idBol"));
				recomendacion.setRecom_bolSerNro(rs.getString("boleta"));
				recomendacion.setRecom_bolFech(rs.getDate("Fecha"));
				recomendacion.setRecom_bolImporte(rs.getString("TotalPagar"));
				recomendacion.setRecom_bolPago(rs.getString("pago"));
				arrayListRecomPagos.add(recomendacion);  
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
		return arrayListRecomPagos;
	}
	
	public String montoPagarRecomendado(int idCliente, int estadoPago){
		String recom=null;
		String url = null;
		if (estadoPago==0) {
			url = "select  pagoRecomendacion(IFNULL(SUM(PorcentajePago),0.00)) as totalRecom "
					+ "from cliente inner join boleta ON cliente.Id=boleta.IdCliente "
					+ "where boleta.IdClienteRec=? and boleta.EstadoAccion=3 and boleta.pagoRecom=0 and EstadoEliminado=0;";
		}else if (estadoPago==1) {
			url = "select  pagoRecomendacion(IFNULL(SUM(PorcentajePago),0.00)) as totalRecom "
					+ "from cliente inner join boleta ON cliente.Id=boleta.IdCliente "
					+ "where boleta.IdClienteRec=? and boleta.EstadoAccion=3 and boleta.pagoRecom=1 and EstadoEliminado=0;";
		}
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			pst = conexionServidor.prepareStatement(url);
			pst.setInt(1, idCliente);
	        rs=pst.executeQuery();
	        while(rs.next()){
	        	recom = rs.getString("totalRecom");
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
		return recom;
	}
	
	public void actualizarRecomendacion(Recomendacion recomendacion){
		String url = "UPDATE recomendacion SET PorcentajePago=?, ModificadoPor=? WHERE Id=?";
		PreparedStatement pst = null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement(url);
			 			  
			  if(recomendacion.getRecom_porcentajePago()!=-1){
           	   	  pst.setInt(1, recomendacion.getRecom_porcentajePago());
              }else{
            	  pst.setNull(1,java.sql.Types.INTEGER);
              }			  
			  pst.setString(2, Sesion.DNI);
			  pst.setInt(3, recomendacion.getRecom_id());
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
	
	public Recomendacion seleccionarRecomendacion(int Id){
		Recomendacion recomendacion = null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			pst=conexionServidor.prepareStatement("SELECT Id, PorcentajePago, "
			 		+ "CreadoPor, ModificadoPor, FCreacion, FModificacion FROM recomendacion WHERE id=?;");
	 	   	pst.setInt(1, Id);
	 	   	rs=pst.executeQuery();
		   
		   while(rs.next()){
			   
			   recomendacion =new Recomendacion();
			   recomendacion.setRecom_id(rs.getInt("Id"));
			   recomendacion.setRecom_porcentajePago(rs.getInt("PorcentajePago"));
			   recomendacion.setCreadoPor(rs.getString("CreadoPor"));
			   recomendacion.setModificadoPor(rs.getString("ModificadoPor"));
			   recomendacion.setfCreacion(rs.getDate("FCreacion"));
			   recomendacion.setfModificacion(rs.getDate("FModificacion"));
			   
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
		
		return recomendacion;
	}
	
	public int insertarEgresoRecomendacion(Egreso egreso, int idCliente){
		int idUltimo = -1;
		this.egreso = egreso;
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement("INSERT INTO egreso(TipoDocumento, NombreRazon, SerieDoc, NroDoc, Ruc, FechaEmision, Monto, Comentarios, IdConcepto, CreadoPor, "
			  		+ "ModificadoPor) SELECT ?,(select NombresApellidos from cliente where Id=?),?,(select ifnull(concat(left(max(NroDoc),1), lpad(right(max(NroDoc),5)+1, 5, '0')) ,'P00001') from egreso where IdConcepto=1),?,date(now()),?,?,?,?,?;",PreparedStatement.RETURN_GENERATED_KEYS);
			  
			  if(egreso.getEgreTipoDoc()!=-1){
           	   	  pst.setInt(1, egreso.getEgreTipoDoc());
              }else{
            	  pst.setNull(1,java.sql.Types.INTEGER);
              }
			  pst.setInt(2, idCliente);
			  if (egreso.getEgreSerieDoc()!=null && !egreso.getEgreSerieDoc().trim().isEmpty()) {
				  pst.setString(3, egreso.getEgreSerieDoc());
			  } else {
				  pst.setNull(3, (java.sql.Types.VARCHAR));
			  }
			  if (egreso.getEgreRuc()!=null && !egreso.getEgreRuc().trim().isEmpty()) {
				  pst.setString(4, egreso.getEgreRuc());
			  } else {
				  pst.setNull(4, (java.sql.Types.VARCHAR));
			  }			  
			  if(egreso.getEgreMonto()!=null && !egreso.getEgreMonto().trim().isEmpty()){
           	   	  pst.setString(5, egreso.getEgreMonto());
              }else{
            	  pst.setNull(5, (java.sql.Types.DOUBLE));
              }
			  if (egreso.getEgreComentarios()!=null && !egreso.getEgreComentarios().trim().isEmpty()) {
				pst.setString(6, egreso.getEgreComentarios());
			  }else {
				 pst.setNull(6, (java.sql.Types.VARCHAR));
			  }
			  if(egreso.getEgreIdConcepto()!=-1){
           	   	  pst.setInt(7, egreso.getEgreIdConcepto());
              }else{
            	  pst.setNull(7,java.sql.Types.INTEGER);
              }
			  
			  pst.setString(8, Sesion.DNI);
       	   	  pst.setString(9, Sesion.DNI);
			  
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
        	   e.printStackTrace();
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
	
	public void actualizarBoletaRecomendacion(int IdCliente, int idEgreso){ 
		String url = "update boleta set PagoRecom=1, idEgreso=?, ModificadoPor=? where idClienteRec=? and EstadoAccion=3 and pagoRecom=0;";
		PreparedStatement pst = null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement(url);
			  pst.setInt(1, idEgreso);
			  pst.setString(2, Sesion.DNI);
			  pst.setInt(3, IdCliente);
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
	
	public String seleccionarNumeroFechaPagoRecomendacion(int idEgreso){		
		String url = "select concat(NroDoc, ';', FechaEmision) as numeroFecha FROM egreso where Id=?;";
		String numeroFecha= null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			pst=conexionServidor.prepareStatement(url);
			pst.setInt(1, idEgreso);
			rs=pst.executeQuery();
		
			while(rs.next()){
				
				numeroFecha=rs.getString("numeroFecha");
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
		
		return numeroFecha;
	}
	
	public String seleccionarDiaPago(){		
		String url = "select dayname(now()) as dia";
		String numeroFecha= null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		
		try {
			pst=conexionServidor.prepareStatement(url);
			rs=pst.executeQuery();
		
			while(rs.next()){
				
				numeroFecha=rs.getString("dia");
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
		
		return numeroFecha;
	}
	
	//Seleccionar filtros de pagos por recomendacion
	public ObservableList<Recomendacion> seleccionarFiltroPagos(int estadoPago){
		ObservableList<Recomendacion> arrayListCliente=FXCollections.observableArrayList();
		
		this.recomendacion = null;
		String url = null;
		if (estadoPago==0) {
			url = "select cliente.Id as idCli, DNI, Codigo, NombresApellidos, boleta.Id as idBol, concat(serie, ' - ',lpad(numero, 8, '0')) as boleta, Fecha, TotalPagar, PorcentajePago as pago "
					+ "from cliente inner join boleta ON cliente.Id=boleta.IdCliente where boleta.EstadoAccion=3 and boleta.pagoRecom=0 and EstadoEliminado=0;";
		}else if (estadoPago==1) {
			url = "select cliente.Id as idCli, DNI, Codigo, NombresApellidos, boleta.Id as idBol, concat(serie, ' - ',lpad(numero, 8, '0')) as boleta, Fecha, TotalPagar, PorcentajePago as pago "
					+ "from cliente inner join boleta ON cliente.Id=boleta.IdCliente where boleta.EstadoAccion=3 and boleta.pagoRecom=1 and EstadoEliminado=0;";
		}
		
		PreparedStatement pst=null;
		ResultSet rs=null;
		  try{
			pst=conexionServidor.prepareStatement(url);
			rs=pst.executeQuery();

			while(rs.next()){

				recomendacion =new Recomendacion();
				recomendacion.setRecom_idCli(rs.getInt("idCli"));
				recomendacion.setRecom_codCli(rs.getString("Codigo"));
				recomendacion.setRecom_dniCli(rs.getString("DNI"));
				recomendacion.setRecom_apellNomCli(rs.getString("NombresApellidos"));
				recomendacion.setRecom_bolId(rs.getInt("idBol"));
				recomendacion.setRecom_bolSerNro(rs.getString("boleta"));
				recomendacion.setRecom_bolFech(rs.getDate("Fecha"));
				recomendacion.setRecom_bolImporte(rs.getString("TotalPagar"));
				recomendacion.setRecom_bolPago(rs.getString("pago"));
				arrayListCliente.add(recomendacion);
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
	
	public String montoTotalPagarPagado(int estadoPago){
		String recom=null;
		String url = null;
		if (estadoPago==0) {
			url = "select  pagoRecomendacion(IFNULL(SUM(PorcentajePago),0.00)) as totalRecom "
					+ "from boleta where boleta.EstadoAccion=3 and boleta.pagoRecom=0 and EstadoEliminado=0;";
		}else if (estadoPago==1) {
			url = "select  pagoRecomendacion(IFNULL(SUM(PorcentajePago),0.00)) as totalRecom "
					+ "from boleta where boleta.EstadoAccion=3 and boleta.pagoRecom=1 and EstadoEliminado=0;";
		}
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			pst = conexionServidor.prepareStatement(url);
	        rs=pst.executeQuery();
	        while(rs.next()){
	        	recom = rs.getString("totalRecom");
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
		return recom;
	}
	
	public ObservableList<Egreso> seleccionarColeccionPagosFacturados(int idCliente){
		ObservableList<Egreso> arrayListEgreso=FXCollections.observableArrayList();
		Egreso egreso = null;
		String url = "select distinct(egreso.Id), case when TipoDocumento=3 then 'Ticket' end as tipDoc, NombreRazon, NroDoc, FechaEmision, (select Concepto from concepto where Id=1) as Concepto, Monto "
			 		+ "from egreso inner join boleta on egreso.Id=boleta.IdEgreso where IdClienteRec=? and IdConcepto=1 order by egreso.FCreacion desc;";
		  PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			 pst=conexionServidor.prepareStatement(url); 
			 pst.setInt(1, idCliente);
			 rs=pst.executeQuery();

			while(rs.next()){

				egreso =new Egreso();
				egreso.setEgreId(rs.getInt("Id"));
				egreso.setEgreDocumento(rs.getString("tipDoc"));
				egreso.setEgreNombreRazon(rs.getString("NombreRazon"));
				egreso.setEgreNroDoc(rs.getString("NroDoc"));
				egreso.setEgreDescripcion(rs.getString("Concepto"));
				egreso.setEgreFechaEmision(rs.getDate("FechaEmision"));
				egreso.setEgreMonto(rs.getString("Monto"));
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
	
	public String montoPagarPagosFacturados(int idCliente){
		String pagoFac=null;
		String url = null;
		url = "select pagoTotalFacturado(?) as totalPagoFac;";
		PreparedStatement pst=null;
		ResultSet rs=null;
		try{
			pst = conexionServidor.prepareStatement(url);
			pst.setInt(1, idCliente);
	        rs=pst.executeQuery();
	        while(rs.next()){
	        	pagoFac = rs.getString("totalPagoFac");
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
		return pagoFac;
	}
}
