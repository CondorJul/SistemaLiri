package controller.articulo;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import controller.CPadre;
import dal.Articulo;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.articulo.MArticulo;

public class CSeeRentabilidadArticulo extends CPadre implements Initializable{

    @FXML
    private Button buttonCerrar;

    @FXML
    private JFXButton jfxButtonCerrar;
    
    @FXML 
    private Label labelRentSinDesc;

    @FXML 
    private Label labelRentConDesc;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		
		jfxButtonCerrar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				((Stage)((Node)event.getSource()).getScene().getWindow()).close();
			}
		});
		
		buttonCerrar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				((Stage)((Node)event.getSource()).getScene().getWindow()).close();
			}
		});
		
		
	}
	


	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		int idArticulo=(int)objeto;

		labelRentSinDesc.setText(String.valueOf(getRentabilidadadAproximadaSinDRP(idArticulo))+" %");
		labelRentConDesc.setText(String.valueOf(getRentabilidadadConDescuentoSinP(idArticulo))+" %");
		
	}

	
	
	@Override
	public Object getObjeto() {
		return null;
	}

	public Double getRentabilidadadAproximadaSinDRP( int idArticulo){
		Double doubleRentabilidad=0.0;
		MArticulo mArticulo=new MArticulo();
		mArticulo.iniciarConexionServidor();
		doubleRentabilidad=mArticulo.getRentabilidadadAproximadaSinDRP(idArticulo);
		mArticulo.cerrarConexionServidor();
		
		
		return doubleRentabilidad;
	}
	
	public Double getRentabilidadadConDescuentoSinP( int idArticulo){
		Double doubleRentabilidad=0.0;
		MArticulo mArticulo=new MArticulo();
		mArticulo.iniciarConexionServidor();
		doubleRentabilidad=mArticulo.getRentabilidadadConDescuentoSinP(idArticulo);
		mArticulo.cerrarConexionServidor();
		
		
		return doubleRentabilidad;
	}
	
	
	
}
