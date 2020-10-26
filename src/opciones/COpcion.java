package opciones;

import java.io.IOException;

import com.jfoenix.controls.JFXButton;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.HBox;

public class COpcion extends HBox{
	public COpcion(){
		FXMLLoader loader=new FXMLLoader(getClass().getResource("Opcion.fxml"));
		//getStylesheets().add(getClass().getResource("Opciones.css").toExternalForm());
		loader.setRoot(this);
		loader.setController(this);
		try{
			loader.load();
		}catch(IOException e){
	
			
		}
		jFXButtonVer.setVisible(false);
		
	}
		public JFXButton getjFXButtonVer() {
		return jFXButtonVer;
	}

	public JFXButton getjFXButtonEditar() {
		return jFXButtonEditar;
	}

		@FXML
	    private JFXButton jFXButtonVer;

	    @FXML
	    private JFXButton jFXButtonEditar;


	    @FXML
	    private JFXButton jFXButtonEliminar;
	    @FXML
	    private JFXButton jFXButtonAgregar;

		public JFXButton getjFXButtonAgregar() {
			return jFXButtonAgregar;
		}
		public JFXButton getjFXButtonEliminar() {
			return jFXButtonEliminar;
		}

}
