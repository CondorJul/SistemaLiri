package login;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import cargando.Cargando;
import controller.alquiler.CAlquiler;
import controller.principal.PrincipalController;
import dal.Usuario;
import dataBase.ServerController;
import funciones.Conexion;
import funciones.Funciones;
import funciones.Servidor;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import model.usuario.MUsuario;
import sesion.Permisos;
import sesion.Sesion;

public class LoginControler extends Funciones implements Initializable {
	
	MUsuario mUsuario=new MUsuario();

	@FXML private JFXButton jFXButtonClose;
	@FXML private Button buttonIngresar;
	//@FXML private Button restaurar;
    @FXML private Label labelAdvertencia;
    @FXML private JFXTextField jFXTextFieldUsuario;
    @FXML private JFXPasswordField jFXPasswordFieldContrasena;
    @FXML private JFXCheckBox jFXCheckBoxRecordarContrasena;

    @FXML
    private Hyperlink hiperlinkConfigurarServidor;
    @FXML
    private Hyperlink restaurar;
    
    private String stringFicheroUser="login/user";
	private String stringFicheroPass="login/pass";
    private String stringFicheroIP="servidor/ip";
    private ServerController sv = new ServerController();
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
				
		//cargamos los campos con los datos guardados
		jFXTextFieldUsuario.setText(leerFichero(stringFicheroUser));
		jFXPasswordFieldContrasena.setText(leerFichero(stringFicheroPass));
		
		sv.loadPropertiesFile();
		//Servidor.HOST_NAME=leerFichero(stringFicheroIP);
		
		

		
		jFXButtonClose.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				JFXButton jFXButton=(JFXButton)event.getSource();
				Stage stage=(Stage)jFXButton.getScene().getWindow();
				stage.close();
				System.exit(0);
				
			}
		});
			
		buttonIngresar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				
				mUsuario.iniciarConexionServidor();
				
				Usuario usuario=mUsuario.seleccionarUsuario(jFXTextFieldUsuario.getText());
				mUsuario.cerrarConexionServidor();
				
				if(usuario!=null){
					
					if(usuario.getClave().equals(encriptar(jFXPasswordFieldContrasena.getText()))){
						
						if(usuario.getEstado()==1){
							Sesion.DNI=usuario.getDni();
							Sesion.NOMBRES_APELLIDOS=usuario.getNomApell();
							Sesion.CARGO=usuario.getCargo();
							Sesion.DIRECCION=usuario.getEmail();
							Sesion.ESTADO=usuario.getEstado();
							Sesion.SALARIO=usuario.getSalario();
							Sesion.FOTO=usuario.getFoto();
							
							Permisos.SEE_CLIENTES=usuario.getSeeClientes().isSelected();
							Permisos.ADD_CLIENTES=usuario.getAddClientes().isSelected();
							Permisos.UPD_CLIENTES=usuario.getUpdClientes().isSelected();
							Permisos.DEL_CLIENTES=usuario.getDelClientes().isSelected();
						

							Permisos.SEE_USUARIOS=usuario.getSeeUsuarios().isSelected();
							Permisos.ADD_USUARIOS=usuario.getAddUsuarios().isSelected();
							Permisos.UPD_USUARIOS=usuario.getUpdUsuarios().isSelected();
							Permisos.DEL_USUARIOS=usuario.getDelUsuarios().isSelected();
							Permisos.STATE_PASS_USUARIOS=usuario.getStatePasswordUsuarios().isSelected();
							
							Permisos.SEE_ARTICULOS=usuario.getSeeArticulos().isSelected();
							Permisos.ADD_ARTICULOS=usuario.getAddArticulos().isSelected();
							Permisos.UPD_ARTICULOS=usuario.getUpdArticulos().isSelected();
							Permisos.DEL_ARTICULOS=usuario.getDelArticulos().isSelected();
							
							Permisos.SEE_PROVEEDOR=usuario.getSeeProveedor().isSelected();
							Permisos.ADD_PROVEEDOR=usuario.getAddProveedor().isSelected();
							Permisos.UPD_PROVEEDOR=usuario.getUpdProveedor().isSelected();
							Permisos.DEL_PROVEEDOR=usuario.getDelProveedor().isSelected();
							
							Permisos.SEE_IMPORTANTE=usuario.getSeeImportante().isSelected();
							Permisos.ADD_IMPORTANTE=usuario.getAddImportante().isSelected();
							Permisos.UPD_IMPORTANTE=usuario.getUpdImportante().isSelected();
							Permisos.DEL_IMPORTANTE=usuario.getDelImportante().isSelected();
							
							Permisos.SEE_ALQUILER=usuario.getSeeAlquiler().isSelected();
							Permisos.ADD_ALQUILER=usuario.getAddAlquiler().isSelected();
							Permisos.UPD_ALQUILER=usuario.getUpdAlquiler().isSelected();
							Permisos.DEL_ALQUILER=usuario.getDelAlquiler().isSelected();
							Permisos.CONFIG_DES_CUP_ALQUILER=usuario.getConfigDesCupAlquiler().isSelected();

							Permisos.SEE_DEVOLUCION=usuario.getSeeDevolucion().isSelected();
							Permisos.ADD_DEVOLUCION=usuario.getAddDevolucion().isSelected();
							Permisos.UPD_DEVOLUCION=usuario.getUpdDevolucion().isSelected();
							Permisos.DEL_DEVOLUCION=usuario.getDelDevolucion().isSelected();
							Permisos.DEVOL_DEVOLUCION=usuario.getDevolDevolucion().isSelected();
							Permisos.TRANS_EXTERNAS_DEVOLUCION=usuario.getTransEsterDevolucion().isSelected();

							Permisos.SEE_CONTABILIDAD=usuario.getSeeContabilidad().isSelected();
							Permisos.ADD_CONTABILIDAD=usuario.getAddContabilidad().isSelected();
							Permisos.UPD_CONTABILIDAD=usuario.getUpdContabilidad().isSelected();
							Permisos.DEL_CONTABILIDAD=usuario.getDelContabilidad().isSelected();
							
							Permisos.SEE_REPUTACION=usuario.getSeeReputacion().isSelected();
							Permisos.ADD_REPUTACION=usuario.getAddReputacion().isSelected();
							Permisos.UPD_REPUTACION=usuario.getUpdReputacion().isSelected();
							Permisos.DEL_REPUTACION=usuario.getDelReputacion().isSelected();
							Permisos.CONFIG_INTER_REPUTACION=usuario.getConfigInterReputacion().isSelected();
							
							Permisos.SEE_RECOMENDACION=usuario.getSeeRecomendacion().isSelected();
							Permisos.ADD_RECOMENDACION=usuario.getAddRecomendacion().isSelected();
							Permisos.UPD_RECOMENDACION=usuario.getUpdRecomendacion().isSelected();
							Permisos.DEL_RECOMENDACION=usuario.getDelRecomendacion().isSelected();
							Permisos.CONFIG_POR_RECOMENDACION=usuario.getConfigPorRecomendacion().isSelected();
							
							Permisos.SEE_ESTADISTICA=usuario.getSeeEstadistica().isSelected();
							Permisos.ADD_ESTADISTICA=usuario.getAddEstadistica().isSelected();
							Permisos.UPD_ESTADISTICA=usuario.getUpdEstadistica().isSelected();
							Permisos.DEL_ESTADISTICA=usuario.getDelEstadistica().isSelected();
							
							Permisos.SEE_BACKUP=usuario.getSeeBackup().isSelected();
							Permisos.SEE_RESTORE_BACKUP=usuario.getSeeRestoreBackup().isSelected();
							
							
							
							
							
							Stage cargando=Cargando.cargando();
		    				cargando.show();
							//cargar el interfaz principal
		    				Task<Void> task=new Task<Void>(){

								@Override
								protected Void call() throws Exception {
									
									
								try {
									Parent parent;
									
									parent = FXMLLoader.load(getClass().getResource("/view/principal/PrincipalInterface.fxml"));
									
									Scene scene=new Scene(parent);
									
									Platform.runLater(new Runnable() {
										
										@Override
										public void run() {
											Stage stage = new Stage();
											stage.setScene(scene);
											stage.setMaximized(true);
											stage.setTitle(Sesion.NOMBRES_APELLIDOS+" - "+Sesion.CARGO);
											
											
											stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
												
												@Override
												public void handle(WindowEvent arg0) {
													if(cerrarVentana()){
														//eliminar ultimo id para poder crear una nueva boleta
														CAlquiler.eliminarBoletAYDetalle();
														
														System.exit(0);
													}else{
														arg0.consume();
													}
												}
											});
											
											
											stage.show();
						    				cargando.close();

											
											
										}
									});
								} catch (Exception e) {
								e.printStackTrace();	
								}
									

										
										
								
									
								
									return null;
								}
		    					
		    				};
		    				Thread hilo=new Thread(task);
		    				hilo.setDaemon(true);
		    				hilo.start();
		    				
		    				
		    				
							

							
								
								
								((Node)(arg0.getSource())).getScene().getWindow().hide();
								
					
						
						}else{
							mostrarInformacion(labelAdvertencia, "La cuenta esta bloqueada.", Funciones.INCORRECTO);
						}
					}else{
						mostrarInformacion(labelAdvertencia, "La constraseña es incorrecta.", Funciones.INCORRECTO);
					}
				}else{
					mostrarInformacion(labelAdvertencia, "El usuario no existe.", Funciones.INCORRECTO);

				}
				
				
				escribirFichero(stringFicheroUser, jFXTextFieldUsuario.getText());
				if(jFXCheckBoxRecordarContrasena.isSelected()){
					escribirFichero(stringFicheroPass, jFXPasswordFieldContrasena.getText());	
				}else {
					escribirFichero(stringFicheroPass, "");
				}
				
				 
			}
		});
		
		hiperlinkConfigurarServidor.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				
				 try {
		             Parent root = FXMLLoader.load(getClass().getResource("/view/server/Server.fxml"));
		             Scene scene = new Scene(root);
		             Stage stage = new Stage();
		             stage.setScene(scene);
		             stage.setTitle("Configurar Servidor");
		             stage.showAndWait();
		         } catch (IOException ex1) {
		             //Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex1);
		         }
				
				/*
				TextInputDialog texInputDialogIp=new TextInputDialog(leerFichero(stringFicheroIP));
				texInputDialogIp.setTitle("Congigurar IP Servidor");
				texInputDialogIp.setHeaderText("");
				texInputDialogIp.setContentText("Ingrese IP-SERVIDOR:");

				// Traditional way to get the response value.
				Optional<String> result = texInputDialogIp.showAndWait();
				if (result.isPresent()){
				   escribirFichero(stringFicheroIP, result.get());
				   
				   Servidor.HOST_NAME=leerFichero(stringFicheroIP);
				   
			
			}
			
			*/
				
			}
		});
		
		
	
		
		restaurar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
								
				 try {
		             Parent root = FXMLLoader.load(getClass().getResource("/view/server/RestaurarBackup.fxml"));
		             Scene scene = new Scene(root);
		             Stage stage = new Stage();
		             stage.setScene(scene);
		             stage.setTitle("Restaurar Backup");
		             stage.showAndWait();
		         } catch (IOException ex1) {
		             //Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex1);
		         }
				//PrincipalController.RestoreDB();
			}
		});
		
				
	}
	
			
	public static  boolean cerrarVentana(){
		Alert alert = new Alert(AlertType.CONFIRMATION, "¿Está seguro que desea salir :( ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
		alert.showAndWait();

		if (alert.getResult() == ButtonType.YES) {
		    return true;
		}else {
			return false;
		}
	}

	private String leerFichero(String url){
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String texto="";
		try {
			// Apertura del fichero y creacion de BufferedReader para poder
	        // hacer una lectura comoda (disponer del metodo readLine()).
	        archivo = new File (url); //"C:\\archivo.txt"
	        fr = new FileReader (archivo);
	        br = new BufferedReader(fr);

	        // Lectura del fichero
	        //texto
	        String linea;
	        while((linea=br.readLine())!=null){
	        	 texto=texto+linea;
	         }
	         
	    }catch(Exception e){
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
	      return texto;
	}
  
		
		
	private void escribirFichero(String url, String texto){

		FileWriter fichero = null;
        PrintWriter pw = null;
        try{
            fichero = new FileWriter(url);//url
            pw = new PrintWriter(fichero);

            //for (int i = 0; i < 10; i++)
                pw.println(texto);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
           try {
           // Nuevamente aprovechamos el finally para 
           // asegurarnos que se cierra el fichero.
        	   if (null != fichero){
        		   fichero.close();
        	   }	   
           }catch (Exception e2) {
        	   e2.printStackTrace();
           }
        }

	}
}