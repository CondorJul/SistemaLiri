package controller.transaccionesExternas;

import javafx.scene.control.Menu;
import model.transaccionesExternas.MTransaccionesExternas;
public class MenuT extends Menu {
	private String aUbicacion=null;
	public MenuT(String arg0, String aUbicacion){
		super(arg0);
		this.aUbicacion=aUbicacion;
		
	}
	
	public String getAUbicacion(){
		return this.aUbicacion;
	}
	
	public String getNameUbicacion(){
		String name="";
		if(aUbicacion.equals(MTransaccionesExternas.A_UBICACION_ALMACEN_STOCK)){
			name="Almacen(Stock)";
		}else if(aUbicacion.equals(MTransaccionesExternas.A_UBICACION_ESPERA)){
			name="Espera";
		}else if(aUbicacion.equals(MTransaccionesExternas.A_UBICACION_LAVANDERIA)){
			name="Lavanderia";
		}else if(aUbicacion.equals(MTransaccionesExternas.A_UBICACION_PLANCHADO)){
			name="Planchado";
		}else if(aUbicacion.equals(MTransaccionesExternas.A_UBICACION_REPARACION)){
			name="Reparación";
		}
		return name;
	}
	
	
}
