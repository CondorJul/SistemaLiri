/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataBase;


import java.io.BufferedReader;
//import StoreKeeper.StoreKeeper;
//import controller.RegistrationController;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;

import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;

import controller.CPadre;
import funciones.Servidor;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author rifat
 */
public class RestaurarBackupController extends CPadre implements Initializable  {

	@FXML
    private Label titleBackup;

    @FXML
    private TextField textFieldUsesrMySQL;

    @FXML
    private PasswordField textFieldContreseniaMySQL;

    @FXML
    private Button btnConnect;

    @FXML
    private TextField textFieldCompiladorMySQL;

    @FXML
    private Button buttonBuscarCompiladoMySQL;

    @FXML
    private Label lablServerStatus;

    @FXML
    private TextArea textAreaMensaje;

    @FXML
    private TextField textFieldUbicacionBackup;

    @FXML
    private Button buttonBuscarBuscarubicacionBackup;

    @FXML
    private TextField textFieldUbicacionBackupImagen;

    @FXML
    private Button buttonBuscarUbicacionBackupImagen;


  
    Properties properties = new Properties();
    InputStream inputStream;
    OutputStream output = null;
    
    Connection con;
    
    String url;
    String user;
    String pass;
    String unicode= "?useUnicode=yes&characterEncoding=UTF-8";

  
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        checkSQLStatus();
      //  getDataFromFile();
        
        buttonBuscarCompiladoMySQL.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				DirectoryChooser seleccionar = new DirectoryChooser();
				
				File file = seleccionar.showDialog(null);
				textFieldCompiladorMySQL.setText(file.getAbsolutePath());
				
			}
		});
        
        buttonBuscarBuscarubicacionBackup.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				FileChooser seleccionar = new FileChooser();
				//seleccionar.setInitialDirectory(new File(textFieldUbicacionBackup.getText()));

				File file = seleccionar.showOpenDialog(null);
				textFieldUbicacionBackup.setText(file.getAbsolutePath());
				
			}
		});
        
        
        buttonBuscarUbicacionBackupImagen.setOnAction(new EventHandler<ActionEvent>() {

     			@Override
     			public void handle(ActionEvent event) {
     				FileChooser seleccionar = new FileChooser();
    				//seleccionar.setInitialDirectory(new File(textFieldUbicacionBackup.getText()));

     				File file = seleccionar.showOpenDialog(null);
     				textFieldUbicacionBackupImagen.setText(file.getAbsolutePath());
     				
     			}
     		});
    }

    @FXML
    private void btnConnectOnAction(ActionEvent event) {
        //mkDbProperties();
    	if(validar()){
    		RestoreDB();
    	}

    }

  
    
    
    public  void RestoreDB(){
		
    	String user= textFieldUsesrMySQL.getText();
		String pass=textFieldContreseniaMySQL.getText();
		String ubicacionComMySQL=textFieldCompiladorMySQL.getText();
		String archivoBackup=textFieldUbicacionBackup.getText();
		String archivoBackupImagen=textFieldUbicacionBackupImagen.getText();
		
		
		
		
		
			try {
				
				//String ubicacion= String.valueOf(file.getAbsolutePath());
				//String[] letterKey = String.valueOf(file.getAbsolutePath()).split("_");
				
				
				
				if(!archivoBackup.trim().isEmpty()){
					String union="cmd /c "+ubicacionComMySQL+"\\mysql -u "+user+" -p"+pass+" sistemaliri < \""+archivoBackup+"\"";
					
					
					
					 Runtime run = Runtime.getRuntime();
			            Process pr = run.exec(union);
			            pr.waitFor();
			            BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			            String line = "";
			            while ((line=buf.readLine())!=null) {
			                System.out.println(line);
				        	 textAreaMensaje.appendText("\n"+line);

			                //Error handling and force push prompts has to be handled
			            }
					


			            
			            
					/*
					System.out.println(union);
					Process child = Runtime.getRuntime().exec(union);
					 InputStream in = child.getInputStream();
			         for (int i = 0; i < in.available(); i++) {
			        	 textAreaMensaje.appendText("\n"+in.read());
			            System.out.println("" + in.read());
			         } 
			            Thread.sleep(5000);
			        
			            child.destroy();	*/		         
				}
				
				textAreaMensaje.appendText("\n");
				if(!archivoBackupImagen.trim().isEmpty()){
					String union="cmd /c "+ubicacionComMySQL+"\\mysql -u "+user+" -p"+pass+" sistemaliriimagenes < \""+archivoBackupImagen+"\"";
					
					 Runtime run = Runtime.getRuntime();
			            Process pr = run.exec(union);
			            pr.waitFor();
			            BufferedReader buf = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			            String line = "";
			            while ((line=buf.readLine())!=null) {
			                System.out.println(line);
				        	 textAreaMensaje.appendText("\n"+line);

			                //Error handling and force push prompts has to be handled
			            }
					
					/*
					String union="cmd /c "+ubicacionComMySQL+"\\mysql -u "+user+" -p"+pass+" sistemaliriimagenes < \""+archivoBackupImagen+"\"";
					Process child = Runtime.getRuntime().exec(union);
					InputStream in = child.getInputStream();
			         for (int i = 0; i < in.available(); i++) {
			        	 textAreaMensaje.appendText("\n"+in.read());
			            System.out.println("" + in.read());
			         }
			         Thread.sleep(5000);
			         child.destroy();*/
				}
				
				
					
		        JOptionPane.showMessageDialog(null,"Backup restaurado exitosamente!");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error no se pudo restaurar"+e.getMessage());
			}
		}
		
	
    
    private boolean validar(){
    	
    	if(textFieldCompiladorMySQL.getText().trim().isEmpty()){
			mostrarAlerta("Compilador MySQL", "", "Por favor es requerido el compilador MySQL.", AlertType.WARNING);
			textFieldCompiladorMySQL.requestFocus();
			return false;
		}
    	
    	
    	//asdfasdf
    	
    	
    	String[] ubicacionArchivo= String.valueOf( new File(textFieldUbicacionBackup.getText()).getAbsolutePath()).split("_");
    	String[] ubicacionImg= String.valueOf( new File(textFieldUbicacionBackupImagen.getText()).getAbsolutePath()).split("_");
    	
    	if(!textFieldUbicacionBackup.getText().trim().isEmpty()  && !ubicacionArchivo[1].equals("dbliri")){
    		mostrarAlerta("Archivo Backup ", "", "Debe ser una backup de base de datos.\nVerifque que el  nombre del archivo contenga la cadena \"dbliri\".", AlertType.WARNING);
			textFieldUbicacionBackup.requestFocus();
			return false;
    	}
    	
    	if(!textFieldUbicacionBackupImagen.getText().trim().isEmpty()  && !ubicacionImg[1].equals("dbimg")){
    		mostrarAlerta("Archivo Backup ", "", "Debe ser una backup de Imagen.\nVerifque que el  nombre del archivo contenga la cadena \"dbimg\".", AlertType.WARNING);

    		textFieldUbicacionBackupImagen.requestFocus();
			return false;
    	}
    	

    	
    	if(textFieldUbicacionBackup.getText().trim().isEmpty() && textFieldUbicacionBackupImagen.getText().trim().isEmpty()){
			mostrarAlerta("Archivo Backup ", "", "Minimo debe ingresar Archivo Backup y/o Archivo Backup Imagen", AlertType.WARNING);
			textFieldUbicacionBackup.requestFocus();
			return false;
		}
    	
    	if(textFieldUsesrMySQL.getText().trim().isEmpty()){
			mostrarAlerta("Usuario MySQL", "", "Ingrese Usuario MySQL.", AlertType.WARNING);
			textFieldUsesrMySQL.requestFocus();
			return false;
		}
    	if(textFieldContreseniaMySQL.getText().trim().isEmpty()){
			mostrarAlerta("Compilador MySQL", "", "Ingrese contraseña MySQL.", AlertType.WARNING);
			textFieldContreseniaMySQL.requestFocus();
			return false;
		}
    	
    	return true;
    }
    /*
    public void getDataFromFile(){
        try {
            inputStream = new FileInputStream("database.properties");
            
            properties.load(inputStream);
            System.err.println("Host : "+ properties.getProperty("host"));
            tfHost.setText(properties.getProperty("host"));
            tfDBName.setText(properties.getProperty("db"));
            tfUserName.setText(properties.getProperty("user"));
            pfPassword.setText(properties.getProperty("password"));
            thPort.setText(properties.getProperty("port"));
            
            tfDBNameImage.setText(properties.getProperty("dbimg"));
            inputStream.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RestaurarBackupController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RestaurarBackupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
/*
    public void mkDbProperties() {
        try {
            output = new FileOutputStream("database.properties");
            
            properties.setProperty("host", tfHost.getText().trim());
            properties.setProperty("port", thPort.getText().trim());
            properties.setProperty("db", tfDBName.getText().trim());
            properties.setProperty("user", tfUserName.getText().trim());
            properties.setProperty("password", pfPassword.getText().trim());
            
            properties.setProperty("dbimg", tfDBNameImage.getText().trim());
            
            properties.store(output, null);
            output.close();
            if (dbConnect()) {
                con.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Conexión exitosa");
                alert.setHeaderText("Inicie sesón ahora");
                alert.setContentText("El servidor ha sido conectado satisfactoriamente \n to Click en OK para iniciar sesión.");
                alert.initStyle(StageStyle.UNDECORATED);
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    Stage stage = (Stage) thPort.getScene().getWindow();
                    stage.close();
                }
            }else{
                Alert error_alert = new Alert(Alert.AlertType.ERROR);
                error_alert.setTitle("No se puede conectar");
                error_alert.setHeaderText("No se puede conectar con el servidor de base de datos MySql");
                error_alert.setContentText("Intentar otra vez");
                error_alert.initStyle(StageStyle.UNDECORATED);
                error_alert.show();
            }
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(StoreKeeper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //Logger.getLogger(StoreKeeper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RestaurarBackupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
*/
    public void checkSQLStatus() {
        try {
            inputStream = new FileInputStream("database.properties");
            String host = properties.getProperty("host");
            int port = 3306;
            Socket socket = new Socket(host, port);
            lablServerStatus.setText("Servidor en ejecución");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RestaurarBackupController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RestaurarBackupController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /*
    public void loadPropertiesFile(){
        try {
            inputStream = new FileInputStream("database.properties");
            properties.load(inputStream);
            url = "jdbc:mysql://"+properties.getProperty("host")+":"+properties.getProperty("port")+"/";
            user = properties.getProperty("user");
            pass = properties.getProperty("password");
            
            Servidor.HOST_NAME=properties.getProperty("host")+":"+properties.getProperty("port");
            Servidor.BASE_DATOS=properties.getProperty("db");
            Servidor.USER=properties.getProperty("user");
            Servidor.PASS=properties.getProperty("password");
            
            Servidor.BASE_DATOS_IMAGEN=properties.getProperty("dbimg");
            
            
            
        } catch (IOException e) {
            System.out.println("DDDD");
        }
    }
*/

	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object getObjeto() {
		// TODO Auto-generated method stub
		return null;
	}
    
    /*
    private boolean dbConnect() {
        loadPropertiesFile();
        
        loadPropertiesFile();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url + unicode  , user, pass);
            return true;
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("Too Many Connection");
        }
        return false;
    }*/

}
