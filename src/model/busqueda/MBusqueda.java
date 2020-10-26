package model.busqueda;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dal.Busqueda;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.MPadre;

public  class MBusqueda extends MPadre{
	
	private int entidad=-1;
	public static final int BOLETA=3;
	public static final int CLIENTE=2;
	public static final int PROVEEDOR=1;
	public void setEntidad(int entidad){
		this.entidad=entidad;
	}
	
	private ArrayList<String> nombresCabeceras=null;
	
	public ArrayList<String> getNombresCabecera() {
		nombresCabeceras=new ArrayList<String>();
		if(entidad==PROVEEDOR){
			 nombresCabeceras.add(0,"Código");
			 nombresCabeceras.add(1,"Artículo");
			 nombresCabeceras.add(2,"Nombre");
			 nombresCabeceras.add(3,"Teléfono");
			 nombresCabeceras.add(4,"Dirección");
			 nombresCabeceras.add(5,"Correo Electrónico");
		}else if(entidad==CLIENTE){
			nombresCabeceras.add(0,"Códgo");
			 nombresCabeceras.add(1,"DNI");
			 nombresCabeceras.add(2,"Apellidos y Nombres");
			 nombresCabeceras.add(3,"Reputacion");
			 nombresCabeceras.add(4,"Dirección");
			 nombresCabeceras.add(5,"Teléfono");
		}else if(entidad==BOLETA){
			nombresCabeceras.add(0,"Fecha/Hora");
			nombresCabeceras.add(1,"Serie-Numero");
			 nombresCabeceras.add(2,"DNI");
			 nombresCabeceras.add(3,"Cliente");
			 nombresCabeceras.add(4,"Garantia");
			 nombresCabeceras.add(5,"Costo (S/)");
		}
		
		return nombresCabeceras;
	}



	public ObservableList<Busqueda> seleccionarBusqueda(String strBuscar){
		strBuscar=(strBuscar!=null && !strBuscar.trim().isEmpty()?"%"+strBuscar+"%":"%");
		
		ObservableList<Busqueda> arrayListBusqueda=FXCollections.observableArrayList();
		
		PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			  if(entidad==PROVEEDOR){
				  pst=conexionServidor.prepareStatement("SELECT PrvId,PrvCod,PrvTelefono, PrvArticulo, PrvDireccion,"
					  		+ " PrvNombre, PrvEMail, PrvComentarios, PrvDatoAdic1, PrvDatoAdic2, "
					  		+ " PrvDatoAdic3, CreadoPor, ModificadoPor "
					  		+ " FCreacion, FModificacion FROM PROVEEDOR where PrvCod like ? OR PrvArticulo like ? OR PrvNombre like ?");
				 	  
			  }else if(entidad==CLIENTE){
				  pst=conexionServidor.prepareStatement("SELECT Id, Codigo, DNI, NombresApellidos, FechaNaci, Direccion, Referencia, "
					  		+ " NroCelular, EMail, Comentarios, Reputacion, CreadoPor, ModificadoPor, FCreacion, FModificacion "
					  		+ " FROM CLIENTE where  Codigo like ? OR DNI like ? OR NombresApellidos like ?");
			  }else if(entidad==BOLETA){
				  pst=conexionServidor.prepareStatement(" select "
				  		+ "cliente.Id as Id, Fecha,  Tipo, "
				  		+ "	concat(Serie, '-', LPAD(Numero, 8,'0')) as serie_boleta, "
				  		+ "    NombresApellidos, DNI,"
				  		+ " concat("
				  		+ "		if(GarNroDni is null,'',concat('DNI: ',GarNroDni,', ')), "
				  		+ "        if(GarNroDniMenor is null,'', concat('DNI MENOR: ',GarNroDniMenor,', ')),"
				  		+ "		if(GarNroLicencia is null,'',concat('LICENCIA: ',GarNroLicencia,', ')),"
				  		+ "        if(GarMonto is null, '',concat('DINERO: ',GarMonto,', ')),"
				  		+ "        if(GarOtroEspecifique is null,'', concat('OTRO: ',GarOtroEspecifique,', ')),"
				  		+ "        if(GarEnlazarBoleta = 1, '', concat('BOLETA: ',GarSerieBoleta,'-',LPAD(GarNumeroBoleta,8,'0'),', '))"
				  		+ "    ) as garantia, TotalPagar"
				  		+ " from cliente inner join boleta on cliente.Id=boleta.IdCliente "
				  		+ " where (DNI LIKE ? or NombresApellidos like ? or concat(Serie, '-', LPAD(Numero, 8,'0')) like ?) and GarEnlazarBoleta=0 and EstadoAccion=3 "
				  		+ "and (DevCompletada IS NULL OR DevCompletada<>1) AND  (DevGarantiaCompletada IS NULL OR DevGarantiaCompletada<>1) AND  EstadoEliminado=0 AND Tipo=1");
			  }
			  pst.setString(1, strBuscar);
		 	   pst.setString(2, strBuscar);
		 	   pst.setString(3, strBuscar);
			   rs=pst.executeQuery();
			   
			   while(rs.next()){
				   if(entidad==PROVEEDOR){
					   Busqueda busqueda=new Busqueda();
					   busqueda.setId(rs.getInt("PrvId"));
					   busqueda.setCodigo(rs.getString("PrvCod"));
					   
					   busqueda.setCampo1(rs.getString("PrvCod"));
					   busqueda.setCampo2(rs.getString("PrvArticulo"));
					   busqueda.setCampo3(rs.getString("PrvNombre"));
					   busqueda.setCampo4(rs.getString("PrvTelefono"));
					   busqueda.setCampo5(rs.getString("PrvDireccion"));
					   busqueda.setCampo6(rs.getString("PrvEMail"));
					   arrayListBusqueda.add(busqueda);
					   
				   }else if(entidad==CLIENTE){
					   Busqueda busqueda=new Busqueda();
					   busqueda.setId(rs.getInt("Id"));
					   busqueda.setCodigo(rs.getString("Codigo"));
					   
					   busqueda.setCampo1(rs.getString("Codigo"));
					   busqueda.setCampo2(rs.getString("DNI"));
					   busqueda.setCampo3(rs.getString("NombresApellidos"));
					   busqueda.setCampo4(rs.getString("Reputacion"));
					   busqueda.setCampo5(rs.getString("Direccion"));
					   busqueda.setCampo6(rs.getString("NroCelular"));
					   arrayListBusqueda.add(busqueda);
				   }else if(entidad==BOLETA){
					   Busqueda busqueda=new Busqueda();
					   busqueda.setId(rs.getInt("Id"));
					   busqueda.setCodigo(rs.getString("serie_boleta"));
					   
					   busqueda.setCampo1(rs.getString("Fecha"));
					   busqueda.setCampo2(rs.getString("serie_boleta"));
					   busqueda.setCampo3(rs.getString("DNI"));
					   busqueda.setCampo4(rs.getString("NombresApellidos"));
					   busqueda.setCampo5(rs.getString("garantia"));
					   busqueda.setCampo6(rs.getString("TotalPagar"));
					   arrayListBusqueda.add(busqueda);
				   }
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
		
		return arrayListBusqueda;
	}
	
	public Busqueda seleccionarBusquedaB(String codigo){
		Busqueda busqueda=null;
		ObservableList<Busqueda> arrayListBusqueda=FXCollections.observableArrayList();
		
		PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement("SELECT PrvId,PrvCod,PrvTelefono, PrvArticulo, PrvDireccion,"
			  		+ " PrvNombre, PrvEMail, PrvComentarios, PrvDatoAdic1, PrvDatoAdic2, "
			  		+ " PrvDatoAdic3, CreadoPor, ModificadoPor "
			  		+ " FCreacion, FModificacion FROM PROVEEDOR where PrvCod = ?;");
		 	   pst.setString(1, codigo);
		 	
			   rs=pst.executeQuery();
			   
			   while(rs.next()){
				    busqueda=new Busqueda();
				   busqueda.setId(rs.getInt("PrvId"));
				   busqueda.setCodigo(rs.getString("PrvCod"));
				   busqueda.setCampo1(rs.getString("PrvCod"));
				   busqueda.setCampo2(rs.getString("PrvArticulo"));
				   busqueda.setCampo3(rs.getString("PrvNombre"));
				   busqueda.setCampo4(rs.getString("PrvTelefono"));
				   busqueda.setCampo5(rs.getString("PrvDireccion"));
				   busqueda.setCampo6(rs.getString("PrvEMail"));
				   arrayListBusqueda.add(busqueda);
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
		
		return busqueda;
	}
	
	public Busqueda seleccionarBusqueda(int id){
		Busqueda busqueda=null;				
		PreparedStatement pst=null;
		  ResultSet rs=null;
		  try{
			  pst=conexionServidor.prepareStatement("SELECT PrvId,PrvCod,PrvTelefono, PrvArticulo, PrvDireccion,"
			  		+ " PrvNombre, PrvEMail, PrvComentarios, PrvDatoAdic1, PrvDatoAdic2, "
			  		+ " PrvDatoAdic3, CreadoPor, ModificadoPor "
			  		+ " FCreacion, FModificacion FROM PROVEEDOR where PrvId = ?;");
		 	   pst.setInt(1, id);
		 	
			   rs=pst.executeQuery();
			   while(rs.next()){
				   
				    busqueda=new Busqueda();
				   busqueda.setId(rs.getInt("PrvId"));
				   busqueda.setCodigo(rs.getString("PrvCod"));
				   busqueda.setCampo1(rs.getString("PrvCod"));
				   busqueda.setCampo2(rs.getString("PrvArticulo"));
				   busqueda.setCampo3(rs.getString("PrvNombre"));
				   busqueda.setCampo4(rs.getString("PrvTelefono"));
				   busqueda.setCampo5(rs.getString("PrvDireccion"));
				   busqueda.setCampo6(rs.getString("PrvEMail"));
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
		
		return busqueda;
	}


}
