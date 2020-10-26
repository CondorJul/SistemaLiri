package funciones;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;

import javax.jws.soap.SOAPBinding.Style;
import javax.swing.text.Element;
import javax.swing.text.html.ImageView;

import org.apache.commons.codec.digest.DigestUtils;
import org.controlsfx.control.Notifications;

import controller.CPadre;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class Funciones {
	
	public static final int CORRECTO=1;
	public static final int INCORRECTO=0;
	public void mostrarInformacion(Label label, String mensaje, int validacion ){
		ObservableList<String> styleClass = label.getStyleClass();
        styleClass.removeAll(Collections.singleton("labelInformacionincorrecto"));  
        styleClass.removeAll(Collections.singleton("labelInformacioncorrecto"));
        
        if(validacion==CORRECTO){
        	 if (!styleClass.contains("labelInformacioncorrecto")) {
                 styleClass.add("labelInformacioncorrecto");
             }
             
        }else{
        	 if (! styleClass.contains("labelInformacionincorrecto")) {
                 styleClass.add("labelInformacionincorrecto");  
             }
        }
        label.setText(mensaje);
	}	
	
	
	
	public void setInterfazInterna (BorderPane stPane_ventana, String url , String css) throws IOException{
		
		FXMLLoader fXMLLoader = new FXMLLoader();
		fXMLLoader.load(getClass().getResource(url).openStream());
		BorderPane borderPane =fXMLLoader.getRoot();
		borderPane.getStylesheets().add(getClass().getResource(css ).toExternalForm());
        stPane_ventana.getChildren().clear();
        borderPane.setMinSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        borderPane.setPrefSize(400, 600);
        borderPane.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        stPane_ventana.setCenter(borderPane);   
	}
	
	public void setInterfazInterna(BorderPane stPane_ventana, BorderPane stBorderPane){
		stPane_ventana.getChildren().clear();
		stBorderPane.setMinSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
		stBorderPane.setPrefSize(400, 600);
		stBorderPane.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        stPane_ventana.setCenter(stBorderPane);
	}
	
	public BorderPane obtenerBorderPaneInterna(String url , String css) throws IOException{
		FXMLLoader fXMLLoader = new FXMLLoader();
		fXMLLoader.load(getClass().getResource(url).openStream());
		BorderPane borderPane =fXMLLoader.getRoot();
		borderPane.getStylesheets().add(getClass().getResource(css ).toExternalForm());
		return borderPane;
	}
	public void setInterfazInterna1(BorderPane stPane_ventana, AnchorPane stBorderPane){
		stPane_ventana.getChildren().clear();
    	stBorderPane.setMinSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
		stBorderPane.setPrefSize(400, 600);
		stBorderPane.setMaxSize(Region.USE_COMPUTED_SIZE, Region.USE_COMPUTED_SIZE);
        stPane_ventana.setCenter(stBorderPane);
	}
	public AnchorPane obtenerAnchorPane(String url , String css) throws IOException{
		AnchorPane borderPane= FXMLLoader.load(getClass().getResource(url));
		borderPane.getStylesheets().add(getClass().getResource(css ).toExternalForm());
		return borderPane;
	}
	
	public void  mostrarInterfazModal(String urlFxml, String css) throws IOException{
		FXMLLoader fXMLLoader=new FXMLLoader();
		fXMLLoader.setLocation(getClass().getResource(urlFxml));
		fXMLLoader.load();
		Parent parent= fXMLLoader.getRoot();
		Scene scene=new Scene(parent);
		scene.setFill(new Color(0,0,0,0));
		scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
		Stage stage=new Stage();
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.show();
	}
	
	public void mostrarInterfazModalShowAndWait(String urlFxml, String css) throws IOException{
		FXMLLoader fXMLLoader=new FXMLLoader();
		fXMLLoader.setLocation(getClass().getResource(urlFxml));
		fXMLLoader.load();
		Parent parent= fXMLLoader.getRoot();
		Scene scene=new Scene(parent);
		scene.setFill(new Color(0,0,0,0));
		scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
		Stage stage=new Stage();
		stage.setScene(scene);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.showAndWait();	
	}
	
	public CPadre  getControlerMostrarInterfazModal(String urlFxml, String css, Object objeto, int tipoModal){
		FXMLLoader fXMLLoader=new FXMLLoader();
		fXMLLoader.setLocation(getClass().getResource(urlFxml));
		try {
			fXMLLoader.load();
			Parent parent= fXMLLoader.getRoot();
			CPadre controllerPadre=fXMLLoader.getController();
			controllerPadre.ejecutarAcciones(objeto,tipoModal);
			Scene scene=new Scene(parent);
			scene.setFill(new Color(0,0,0,0));
			scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initStyle(StageStyle.TRANSPARENT);
			stage.show();
			return  controllerPadre;
		} catch (IOException e) {
			Alert error_alert = new Alert(Alert.AlertType.ERROR);
            error_alert.setTitle("Error al cargar!!!");
            error_alert.setHeaderText("Surgio un error al intentar cargar el modal.");
            error_alert.setContentText("Intente otra vez.");
            error_alert.initStyle(StageStyle.UNDECORATED);
            error_alert.show();
            e.printStackTrace();
			return null;
			
		}
		
		
	}
	
	public CPadre getControlerMostrarInterfazModalShowAndWait(String urlFxml, String css, Object objeto,int tipoModal){
		FXMLLoader fXMLLoader=new FXMLLoader();
		fXMLLoader.setLocation(getClass().getResource(urlFxml));
		try {
			fXMLLoader.load();
			Parent parent= fXMLLoader.getRoot();
			CPadre controllerPadre=fXMLLoader.getController();
			controllerPadre.ejecutarAcciones(objeto,tipoModal);
			Scene scene=new Scene(parent);
			scene.setFill(new Color(0,0,0,0));
			scene.getStylesheets().add(getClass().getResource(css).toExternalForm());
			Stage stage=new Stage();
			stage.setScene(scene);
			stage.initModality(Modality.APPLICATION_MODAL);
			stage.initStyle(StageStyle.TRANSPARENT);
			stage.showAndWait();	
			return controllerPadre;
		} catch (IOException e) {
			Alert error_alert = new Alert(Alert.AlertType.ERROR);
            error_alert.setTitle("Error al cargar!!!");
            error_alert.setHeaderText("Surgio un error al intentar cargar el modal.");
            error_alert.setContentText("Intente otra vez.");
            error_alert.initStyle(StageStyle.UNDECORATED);
            error_alert.show();
            e.printStackTrace();
			return null;
		}
		
	}
	
	public Image seleccionarImage(){
		 FileChooser fileChooser = new FileChooser();
	        FileChooser.ExtensionFilter extFilterjpg = new FileChooser.ExtensionFilter("jpg files (*.jpg)", "*.jpg");
	        FileChooser.ExtensionFilter extFilterpng = new FileChooser.ExtensionFilter("png files (*.png)", "*.png");
	        Image image=null;
	        fileChooser.getExtensionFilters().addAll(extFilterjpg, extFilterpng);
	        File file;
	        file = fileChooser.showOpenDialog(null);

	        if (file != null) {
	            if (file.length() < 6000000) {
					try {
						InputStream inputStream = new FileInputStream(file.getAbsolutePath());
		                 image = new Image(inputStream);
					} catch (IOException e) {
						e.printStackTrace();
					}
	                
	         } else {

	        	    Alert alert = new Alert(Alert.AlertType.INFORMATION);
	                alert.setTitle("Alerta");
	                alert.setHeaderText("Alerta");
	                alert.setContentText("Este archivo es demasidado grande :(.. \n");
	                alert.initStyle(StageStyle.UNDECORATED);
	                return null;

	            }

	        }
	        return image;
		
	}

	public String encriptar(String texto){
		
		return DigestUtils.md5Hex(texto); 
	
	}
	
	public boolean EliminarDatos(){
		Alert alert = new Alert(AlertType.CONFIRMATION, "¿Está seguro que desea Eliminar :( ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
		    return true;
		}else {
			return false;
		}
	}
	
	public static String decimalReducido(String value)
	{
	    int integerPlaces = 0;
	    String aux;
	    if (value.contains("."))
	    {
	    	value=value+"000";
	     
	    }else{
	    	value=value+".00000";
	    }
	    integerPlaces = value.indexOf('.');
	    
	     aux=value.substring(0, integerPlaces+3);
	    return aux;
	}
	
	public static String leerEstructuraBoleta(File archivo){
		
	      FileReader fr = null;
	      BufferedReader br = null;
	      String estBoleta="";

	      try {
	         // Apertura del fichero y creacion de BufferedReader para poder
	         // hacer una lectura comoda (disponer del metodo readLine()).
	        
	    	 //archivo = new File ("C:\\archivo.txt");
	         fr = new FileReader (archivo);
	         br = new BufferedReader(fr);

	         // Lectura del fichero
	         String linea;
	         while((linea=br.readLine())!=null)
	        	 estBoleta=estBoleta+linea;
	            //System.out.println(linea);
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }finally{
	         // En el finally cerramos el fichero, para asegurarnos
	         // que se cierra tanto si todo va bien como si salta 
	         // una excepcion.
	         try{                    
	            if( null != fr ){   
	               fr.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
	      }
	      
	      return estBoleta;
	      
	      
	}
	
	public boolean isNullOrEmtpy(String text){
		if(text!=null && !text.trim().isEmpty()){
			return false;
		}
		
		return true;
	}

	public static void mostrarNotificacion(String mensaje, int estado){
		Image imgExito = new Image("/icon/exito.png");
		Image imgError = new Image("/icon/error.png");
		if (estado==1) {
			Notifications notificationsBuilder = Notifications.create()
					.title("Sistema de Alquileres Liri")
					.text(mensaje)
					.graphic(new javafx.scene.image.ImageView(imgExito))
					.darkStyle()
					.hideAfter(Duration.seconds(10))
					.position(Pos.BOTTOM_RIGHT);
			notificationsBuilder.show();
		}else{
			Notifications notificationsBuilder = Notifications.create()
					.title("Sistema de Alquileres Liri")
					.text(mensaje)
					.graphic(new javafx.scene.image.ImageView(imgError))
					.darkStyle()
					.hideAfter(Duration.seconds(10))
					.position(Pos.BOTTOM_RIGHT);
			notificationsBuilder.show();
		}
		
	}
}
