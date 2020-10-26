package controller.transaccionesExternas;
import javafx.scene.Node;
import javafx.scene.control.MenuItem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
public class MenuItemT extends MenuItem {
	
	public String aUbicacion=null; 
	
	public MenuItemT(String arg0, Node arg1, String aUbicacion, EventHandler<ActionEvent> eventHandler){
		super(arg0, arg1);
		this.aUbicacion=aUbicacion;
		this.setOnAction(eventHandler);
	}
	
	public MenuItemT(String arg0, String aUbicacion, EventHandler<ActionEvent> eventHandler){
		super(arg0);
		this.aUbicacion=aUbicacion;
		this.setOnAction(eventHandler);
		
	}
	
	public MenuItemT(String arg0, EventHandler<ActionEvent> eventHandler){
		super(arg0);
		this.setOnAction(eventHandler);
		
	}
	public String  getAUbicacion(){
		return this.aUbicacion;
	}

}
