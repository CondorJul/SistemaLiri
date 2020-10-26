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
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.articulo.MArticulo;

public class CSeeImgArticulo extends CPadre implements Initializable{
	MArticulo mArticulo=new MArticulo();
	Articulo articulo=new Articulo();
	
    @FXML
    private Button buttonCerrar;

    @FXML
    private JFXButton jfxButtonCerrar;

    @FXML
    private ImageView imageViewFoto;

    @FXML
    private Slider sliderDimension;
    private double altImgReal=0;
	private double ancImgReal=0;
	
	
	private double altImgIV=0;
	private double achImageIV=0;

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
		
		sliderDimension.setOnMouseDragged(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				renderizarImagen();

			}
		});
		imageViewFoto.setOnMouseDragged(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println("Dragged"+event.getScreenY()+" - "+event.getScreenX());
			}


		});
		
		imageViewFoto.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println("Clicked"+event.getScreenY()+" - "+event.getScreenX());
			}
		});
		
		imageViewFoto.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				System.out.println("Exited"+event.getScreenY()+" - "+event.getScreenX());
			}
		});
		
		
		
		
		
		
	}
	
	private void renderizarImagen(){
	
		if(altImgReal<=altImgIV || ancImgReal<=achImageIV){
			sliderDimension.setDisable(true);
			return;
		}
		
		double nuevoDimAlto=(((altImgReal-altImgIV)*sliderDimension.getValue())/100)+altImgIV;
		double nuevoDimAncho=(((ancImgReal-achImageIV)*sliderDimension.getValue())/100)+achImageIV;
		imageViewFoto.setFitHeight(nuevoDimAlto);
		imageViewFoto.setFitWidth(nuevoDimAncho);
	}

	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		if(CPadre.NULO==tipoModal){
			Image img=mArticulo.seleccioanrImagen((int)objeto);
			if(img!=null){
				altImgReal=img.getHeight();
				ancImgReal=img.getWidth();
				
				imageViewFoto.setImage(img);
				
				altImgIV=imageViewFoto.getFitHeight();
				achImageIV=imageViewFoto.getFitWidth();
			}
			
			
			
			
		}
		
	}

	@Override
	public Object getObjeto() {
		return null;
	}

	
	
}
