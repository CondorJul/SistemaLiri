package cargando;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import funciones.Funciones;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;







 
public class CargandoControlerAnchorPane extends AnchorPane {

   public CargandoControlerAnchorPane (){
	 	FXMLLoader loader=new FXMLLoader(getClass().getResource("CargandoInterfaz.fxml"));
			//getStylesheets().add(getClass().getResource("togleButtonFoto.css").toExternalForm());
			loader.setRoot(this);
			loader.setController(this);
			try{
				loader.load();
			}catch(IOException e){
				e.printStackTrace();
				System.out.println(e);
				
			}
   }
	

	

	


  

}