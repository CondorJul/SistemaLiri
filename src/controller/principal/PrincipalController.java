package controller.principal;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Calendar;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import controller.CPadre;
import controller.alquiler.CAlquiler;
import controller.cliente.CCliente;
import funciones.Funciones;
import funciones.Servidor;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import login.LoginControler;
import sesion.Permisos;

public class PrincipalController extends Funciones implements Initializable{
	
	
	@FXML private VBox vBoxMenu;
	@FXML private HBox hBoxMenu;
	
	@FXML private ToggleButton toggleButtonInicio;
    @FXML private ToggleGroup toggleGroupOpciones;
    @FXML private ToggleButton toggleButtonArticulo;
    @FXML private ToggleButton toggleButtonAlquiler;
    @FXML private ToggleButton toggleButtonDevolucion;
    @FXML private ToggleButton toggleButtonContabilidad;
    @FXML private ToggleButton toggleButtonProveedor;
    @FXML private ToggleButton toggleButtonImportante;
    @FXML private ToggleButton toggleButtonEstadistica;
    @FXML private ToggleButton toggleButtonCliente;
    @FXML private ToggleButton toggleButtonUsuario;
    @FXML private ToggleButton toggleButtonRecomendacion;
    @FXML private ToggleButton toggleButtonReputacion;
    @FXML private ToggleButton toggleButtonPerfil;
    @FXML private BorderPane borderPaneContainer;
    @FXML private StackPane stackPaneTransparencia;
        
    private BorderPane borderPaneArticulo;
    private BorderPane borderPaneCliente;
    private BorderPane borderPaneAlquiler;
    private BorderPane borderPaneDevolucion;
    private BorderPane borderPaneContabilidad;
    private BorderPane borderPaneImportante;
    private BorderPane borderPaneReputacion;
    private BorderPane borderPaneProveedor;
    private BorderPane borderPaneUsuario;
    private BorderPane borderPanePerfil;
    private BorderPane borderPaneRecomendacion;
    private AnchorPane anchorPaneInicio;
    private BorderPane borderPaneEstadistica;
    
    @FXML private Menu menuTranExt;
    @FXML private Menu menuConfiguracion;
    @FXML private Menu menuHerramientas;
     
    @FXML private MenuItem menuItemSalir;
    @FXML private MenuItem menuItemRefrescar;
    @FXML private MenuItem menuItemInicio;
    @FXML private MenuItem menuItemArticulo;
    @FXML private MenuItem menuItemCliente;
    @FXML private MenuItem menuItemUsuario;
    @FXML private MenuItem menuItemAlquiler;
    @FXML private MenuItem menuItemDevolucion;
    @FXML private MenuItem menuItemContabilidad;
    @FXML private MenuItem menuItemProveedor;
    @FXML private MenuItem menuItemRecomendacion;
    @FXML private MenuItem menuItemReputacion;
    @FXML private MenuItem menuItemImportante;
    @FXML private MenuItem menuItemEstadistica;
    @FXML private MenuItem menuItemPorDesc;
    @FXML private MenuItem menuItemPorPRecom;
    @FXML private MenuItem menuItemInterReput;
    @FXML private MenuItem menuItemCopiaSeguridad;
    @FXML private MenuItem menuItemRestaurarCopiaSeguridad;
    @FXML private MenuItem menuItemGuiaUsuario;
    @FXML private MenuItem menuItemPiezasEspera;
    @FXML private MenuItem menuItemPiezasLavanderia;
    @FXML private MenuItem menuItemPiezasReparacion;
    @FXML private MenuItem menuItemPiezasPlanchado;
    
    @FXML private Menu menuAyuda;

    @FXML private MenuItem menuItemAcercaDe;

        
	/* (non-Javadoc)
	 * @see javafx.fxml.Initializable#initialize(java.net.URL, java.util.ResourceBundle)
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		desactivarModulos();
		desactivarTransaccionesExternas();
		desactivarHerramientas();
		desactivarConfiguracion();
		cargarInterfazInterna();
		setInterfazInterna1(borderPaneContainer, anchorPaneInicio);
		/*
		toggleButtonInicio.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				ToggleButton toggleButton = (ToggleButton)arg0.getSource();
				ocultarMenu();
				if(toggleButton.isSelected()){
					setInterfazInterna(borderPaneContainer, borderPaneInicio);
				}else{
					toggleButton.setSelected(true);
				}
				
				
			}
		});*/
		
		toggleButtonInicio.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {				
				setInterfazInterna1(borderPaneContainer, anchorPaneInicio);
			}
		});
		
		toggleButtonArticulo.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {				
				setInterfazInterna(borderPaneContainer, borderPaneArticulo);
			}
		});
		
		toggleButtonCliente.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				setInterfazInterna(borderPaneContainer, borderPaneCliente);
			}
		});
		
		toggleButtonAlquiler.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				setInterfazInterna(borderPaneContainer, borderPaneAlquiler);
				
			}
		});
		

		toggleButtonDevolucion.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				setInterfazInterna(borderPaneContainer, borderPaneDevolucion);
				
			}
		});
		
		toggleButtonContabilidad.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				setInterfazInterna(borderPaneContainer, borderPaneContabilidad);
			}
		});
		
		toggleButtonImportante.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				setInterfazInterna(borderPaneContainer, borderPaneImportante);
			}
			
		});
		
		toggleButtonReputacion.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				setInterfazInterna(borderPaneContainer, borderPaneReputacion);
			}
		});
		
		toggleButtonProveedor.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				setInterfazInterna(borderPaneContainer, borderPaneProveedor);
			}
			
		});
		
		toggleButtonUsuario.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				setInterfazInterna(borderPaneContainer, borderPaneUsuario);
			}
			
		});
		
		toggleButtonPerfil.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				setInterfazInterna(borderPaneContainer, borderPanePerfil);
			}
			
		});
		
		toggleButtonRecomendacion.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				setInterfazInterna(borderPaneContainer, borderPaneRecomendacion);
			}
			
		});
		
		toggleButtonEstadistica.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				setInterfazInterna(borderPaneContainer, borderPaneEstadistica);	
			}
		});
		
		menuItemSalir.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(LoginControler.cerrarVentana()){
					CAlquiler.eliminarBoletAYDetalle();
					System.exit(0);
				}else{
					event.consume();
				}
			}
		});
		
		menuItemAlquiler.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				setInterfazInterna(borderPaneContainer, borderPaneAlquiler);
				toggleButtonAlquiler.setSelected(true);
			}
		});
		
		menuItemArticulo.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				setInterfazInterna(borderPaneContainer, borderPaneArticulo);
				toggleButtonArticulo.setSelected(true);
			}
		});
		
		menuItemCliente.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				setInterfazInterna(borderPaneContainer, borderPaneCliente);
				toggleButtonCliente.setSelected(true);
			}
		});
		
		menuItemContabilidad.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				setInterfazInterna(borderPaneContainer, borderPaneContabilidad);
				toggleButtonContabilidad.setSelected(true);
			}
		});
		
		menuItemDevolucion.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				setInterfazInterna(borderPaneContainer, borderPaneDevolucion);
				toggleButtonDevolucion.setSelected(true);
			}
		});
		
		menuItemEstadistica.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				setInterfazInterna(borderPaneContainer, borderPaneEstadistica);
				toggleButtonEstadistica.setSelected(true);
			}
		});
		
		menuItemImportante.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				setInterfazInterna(borderPaneContainer, borderPaneImportante);
				toggleButtonImportante.setSelected(true);
			}
		});
		
		menuItemInicio.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				setInterfazInterna1(borderPaneContainer, anchorPaneInicio);
				toggleButtonInicio.setSelected(true);
			}
		});
		
		menuItemProveedor.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				setInterfazInterna(borderPaneContainer, borderPaneProveedor);
				toggleButtonProveedor.setSelected(true);
			}
		});
		
		menuItemRecomendacion.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				setInterfazInterna(borderPaneContainer, borderPaneRecomendacion);
				toggleButtonRecomendacion.setSelected(true);
			}
		});
		
		menuItemReputacion.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				setInterfazInterna(borderPaneContainer, borderPaneReputacion);
				toggleButtonReputacion.setSelected(true);
			}
		});
		
		menuItemUsuario.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				setInterfazInterna(borderPaneContainer, borderPaneUsuario);
				toggleButtonUsuario.setSelected(true);
			}
		});
		
		if(Permisos.CONFIG_POR_RECOMENDACION){
			menuItemPorPRecom.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent event) {
					StackPane sp=((StackPane)((Stage)toggleButtonAlquiler.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
					sp.setVisible(true);
					String url = "/view/recomendacion/ConfiguracionRecomendacion.fxml";
					String css = "/estilocss/EstiloModal.css";
								
					CPadre cpadre=getControlerMostrarInterfazModalShowAndWait(url, css, 1, CPadre.UPDATE);
					sp.setVisible(false);
				}
			});
		}
		
		if(Permisos.CONFIG_INTER_REPUTACION){
			menuItemInterReput.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					StackPane sp=((StackPane)((Stage)toggleButtonAlquiler.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
					sp.setVisible(true);
					String url = "/view/reputacion/ConfiguracionReputacion.fxml";
					String css = "/estilocss/EstiloModal.css";
								
					CPadre cpadre=getControlerMostrarInterfazModalShowAndWait(url, css, 1, CPadre.UPDATE);
					sp.setVisible(false);
				}
			});
		}
		
		if(Permisos.CONFIG_DES_CUP_ALQUILER){
			menuItemPorDesc.setOnAction(new EventHandler<ActionEvent>() {

				@Override
				public void handle(ActionEvent arg0) {
					StackPane sp=((StackPane)((Stage)toggleButtonAlquiler.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
					sp.setVisible(true);
					String url = "/view/configuracion/ConfiguracionCupon.fxml";
					String css = "/estilocss/EstiloModal.css";
								
					CPadre cpadre=getControlerMostrarInterfazModalShowAndWait(url, css, 1, CPadre.UPDATE);
					sp.setVisible(false);
				}
			});
		}
		
		
		menuItemCopiaSeguridad.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				BackupDB();
			}
		});
		
		menuItemRestaurarCopiaSeguridad.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				RestoreDB();
			}
		});
		
		menuItemRefrescar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				CCliente cliente = new CCliente();
				cliente.recargarTabla();
			}
		});
		
		
		
		
		
		menuItemPiezasEspera.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				StackPane sp=((StackPane)((Stage)toggleButtonAlquiler.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
				sp.setVisible(true);

				String url = "/view/transaccionesExternas/PiezaEspera.fxml";
				String css = "/estilocss/EstiloModal.css";
							
				CPadre cpadre=getControlerMostrarInterfazModalShowAndWait(url, css, null, CPadre.INSERT);		
				sp.setVisible(false);

			}
		});
		
		
		
		menuItemPiezasLavanderia.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				StackPane sp=((StackPane)((Stage)toggleButtonAlquiler.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
				sp.setVisible(true);
				String url = "/view/transaccionesExternas/PiezaLavanderia.fxml";
				String css = "/estilocss/EstiloModal.css";
							
				CPadre cpadre=getControlerMostrarInterfazModalShowAndWait(url, css, null, CPadre.INSERT);
				sp.setVisible(false);
			}
		});
		
		menuItemPiezasReparacion.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				StackPane sp=((StackPane)((Stage)toggleButtonAlquiler.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
				sp.setVisible(true);
				String url = "/view/transaccionesExternas/PiezaReparacion.fxml";
				String css = "/estilocss/EstiloModal.css";
							
				CPadre cpadre=getControlerMostrarInterfazModalShowAndWait(url, css, null, CPadre.INSERT);
				
				sp.setVisible(false);
			}
		});
		
		menuItemPiezasPlanchado.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				StackPane sp=((StackPane)((Stage)toggleButtonAlquiler.getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2));
				sp.setVisible(true);
				String url = "/view/transaccionesExternas/PiezaPlanchado.fxml";
				String css = "/estilocss/EstiloModal.css";
							
				CPadre cpadre=getControlerMostrarInterfazModalShowAndWait(url, css, null, CPadre.INSERT);
				sp.setVisible(false);
			}
		});
		
		
		
		
		menuItemCopiaSeguridad.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				//BackupDB();
				 try {
		             Parent root = FXMLLoader.load(getClass().getResource("/view/server/RealizarBackup.fxml"));
		             Scene scene = new Scene(root);
		             Stage stage = new Stage();
		             stage.setScene(scene);
		             stage.setTitle("Realizar Backup");
		             stage.showAndWait();
		         } catch (IOException ex1) {
		             //Logger.getLogger(DBModel.class.getName()).log(Level.SEVERE, null, ex1);
		         }
			}
		});
		
		menuItemRestaurarCopiaSeguridad.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				//RestoreDB();
				
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
			}
		});
		
		menuItemRefrescar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				//CCliente cliente = new CCliente();
				//cliente.recargarTabla();
			}
		});
				
		stackPaneTransparencia.setOnMouseClicked(new EventHandler<Event>() {

			@Override
			public void handle(Event arg0) {
				((StackPane)((Stage)((Node)arg0.getSource()).getScene().getWindow()).getScene().getRoot().getChildrenUnmodifiable().get(2)).setVisible(false);
			}
		});
		
				
		menuItemGuiaUsuario.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				
				try {
					File path = new File ("");
				    Desktop.getDesktop().open(path);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		
		
		/*
		buttonCambiarContrasena.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent cambiar) {
				
				stackPaneTransparencia.setVisible(true);
				
				String url = "/perfil/CambiarContrasena/CambiarContrasena.fxml";
				String css = "/estilocss/EstiloModal.css";
				
				try {
					mostrarInterfazModalShowAndWait(url, css);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				stackPaneTransparencia.setVisible(false);
			}
		});
		
		buttonSalir.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				System.exit(0);
			}
		});
		*/
	}
	
	public void desactivarModulos(){
	//toggleButtonInicio;
	//toggleGroupOpciones;
		
	//Desactivar toggleButtons módulos
	toggleButtonArticulo.setDisable(!Permisos.SEE_ARTICULOS);
	toggleButtonAlquiler.setDisable(!Permisos.SEE_ALQUILER);
	toggleButtonDevolucion.setDisable(!Permisos.SEE_DEVOLUCION);
	toggleButtonContabilidad.setDisable(!Permisos.SEE_CONTABILIDAD);
	toggleButtonProveedor.setDisable(!Permisos.SEE_PROVEEDOR);
	toggleButtonImportante.setDisable(!Permisos.SEE_IMPORTANTE);
	toggleButtonEstadistica.setDisable(!Permisos.SEE_ESTADISTICA);
	toggleButtonCliente.setDisable(!Permisos.SEE_CLIENTES);
	toggleButtonUsuario.setDisable(!Permisos.SEE_USUARIOS);
	toggleButtonRecomendacion.setDisable(!Permisos.SEE_RECOMENDACION);
	toggleButtonReputacion.setDisable(!Permisos.SEE_REPUTACION);
	//toggleButtonPerfil.setDisable(false);
		
	//desactivar MenuItems módulos
	menuItemArticulo.setVisible(Permisos.SEE_ARTICULOS);
    menuItemCliente.setVisible(Permisos.SEE_CLIENTES);
    menuItemUsuario.setVisible(Permisos.SEE_DEVOLUCION);
    menuItemAlquiler.setVisible(Permisos.SEE_ALQUILER);
    menuItemDevolucion.setVisible(Permisos.SEE_DEVOLUCION);
    menuItemContabilidad.setVisible(Permisos.SEE_CONTABILIDAD);
    menuItemProveedor.setVisible(Permisos.SEE_PROVEEDOR);
    menuItemRecomendacion.setVisible(Permisos.SEE_RECOMENDACION);
    menuItemReputacion.setVisible(Permisos.SEE_REPUTACION);
    menuItemImportante.setVisible(Permisos.SEE_IMPORTANTE);
    menuItemEstadistica.setVisible(Permisos.SEE_ESTADISTICA);
    	
	}
	
	public void desactivarTransaccionesExternas(){
		menuTranExt.setVisible(Permisos.TRANS_EXTERNAS_DEVOLUCION);
	}
	
	public void desactivarHerramientas(){
		menuHerramientas.setVisible(Permisos.SEE_BACKUP || Permisos.SEE_RESTORE_BACKUP);
		
		menuItemCopiaSeguridad.setVisible(Permisos.SEE_BACKUP );;
	    menuItemRestaurarCopiaSeguridad.setVisible(Permisos.SEE_RESTORE_BACKUP);
		
		
	}
	
	
	public void desactivarConfiguracion(){
		menuItemPorDesc.setVisible(Permisos.CONFIG_DES_CUP_ALQUILER);
		menuItemPorPRecom.setVisible(Permisos.CONFIG_POR_RECOMENDACION);
		menuItemInterReput.setVisible(Permisos.CONFIG_INTER_REPUTACION);
		menuConfiguracion.setVisible(Permisos.CONFIG_DES_CUP_ALQUILER || Permisos.CONFIG_POR_RECOMENDACION || Permisos.CONFIG_INTER_REPUTACION);
		
	}

	public void cargarInterfazInterna(){
	
		try {
			
			if(Permisos.SEE_ARTICULOS){
				borderPaneArticulo=obtenerBorderPaneInterna("/view/articulo/InterfazArticulo.fxml", "");
			}
			
			if(Permisos.SEE_CLIENTES){
				borderPaneCliente=obtenerBorderPaneInterna("/view/cliente/InterfazCliente.fxml", "");
			}
			if(Permisos.SEE_ALQUILER){
				borderPaneAlquiler=obtenerBorderPaneInterna("/view/alquiler/InterfazAlquiler.fxml", "");
			}
			if (Permisos.SEE_DEVOLUCION) {
				borderPaneDevolucion=obtenerBorderPaneInterna("/view/devolucion/InterfazDevolucion.fxml", "");
			}
			if (Permisos.SEE_CONTABILIDAD) {
				borderPaneContabilidad=obtenerBorderPaneInterna("/view/contabilidad/InterfazContabilidad.fxml", "");
			}
			if (Permisos.SEE_IMPORTANTE) {
				borderPaneImportante = obtenerBorderPaneInterna("/view/importante/InterfazImportante.fxml", "");
			}		
			if(Permisos.SEE_PROVEEDOR){
				borderPaneProveedor=obtenerBorderPaneInterna("/view/proveedor/InterfazProveedor.fxml", "");
			}
			
			if(Permisos.SEE_USUARIOS){
				borderPaneUsuario=obtenerBorderPaneInterna("/view/usuario/InterfazUsuario.fxml", "");	
			}
			if (Permisos.SEE_REPUTACION) {
				borderPaneReputacion=obtenerBorderPaneInterna("/view/reputacion/InterfazReputacion.fxml", "");
			}
			if (Permisos.SEE_RECOMENDACION) {
				borderPaneRecomendacion = obtenerBorderPaneInterna("/view/recomendacion/InterfazRecomendacion.fxml", "");
			}
			if (Permisos.SEE_ESTADISTICA) {
				borderPaneEstadistica=obtenerBorderPaneInterna("/view/estadistica/InterfazEstadistica.fxml", "");
			}
			borderPanePerfil=obtenerBorderPaneInterna("/view/perfil/InterfazPerfil.fxml", "");
			anchorPaneInicio=obtenerAnchorPane("/view/inicio/InterfazInicio.fxml", "");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void BackupDB(){
		FileChooser seleccionar = new FileChooser();
		File file = seleccionar.showSaveDialog(null);
		Calendar c = Calendar.getInstance();
		String fecha = String.valueOf(c.get(Calendar.DATE));
		fecha = fecha+"-"+String.valueOf(c.get(Calendar.MONTH)+1);
		fecha = fecha+"-"+String.valueOf(c.get(Calendar.YEAR));
		if (file!=null) { 
			try {
				Runtime runtime = Runtime.getRuntime();
				
				File backupFile = new File(String.valueOf(file.getAbsolutePath().toString()+"_dbliri_"+fecha+".sql"));
				FileWriter fw = new FileWriter(backupFile);
				
				Process p = runtime.exec("cmd /c mysqldump -u "+Servidor.USER+" -p"+Servidor.PASS+" --databases sistemaliri --routines=true");
				InputStreamReader isr = new InputStreamReader(p.getInputStream());
				BufferedReader br = new BufferedReader(isr);
				String line;
				while((line=br.readLine()) != null){
					fw.write(line+"\n");
				}
				fw.close();
				isr.close();
				br.close();
				
				File backupFile2 = new File(String.valueOf(file.getAbsolutePath().toString()+"_dbimg_"+fecha+".sql"));
				FileWriter fw2 = new FileWriter(backupFile2);
				
				Process p2 = runtime.exec("cmd /c mysqldump -u "+Servidor.USER+" -p"+Servidor.PASS+" --databases sistemaliriimagenes");
				InputStreamReader isr2 = new InputStreamReader(p2.getInputStream());
				BufferedReader br2 = new BufferedReader(isr2);
				String line2;
				while((line2=br2.readLine()) != null){
					fw2.write(line2+"\n");
				}
				fw2.close();
				isr2.close();
				br2.close();				
				JOptionPane.showMessageDialog(null,"Backup realizado exitosamente!");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error no se pudo generar el backup"+e.getMessage());
			}
		}
		
	}
	
	public static void RestoreDB(){
		String user=null;
		String pass=null;
		
		FileChooser seleccionar = new FileChooser();
		File file = seleccionar.showOpenDialog(null);
		
		if(file==null){
			return;
		}
		
		
		
		TextInputDialog texInputDialogUser=new TextInputDialog();
		
		texInputDialogUser.setTitle("Restaurar Backup");
		texInputDialogUser.setHeaderText("Tener en cuenta,"
				+ "\nque la restauración de la "
				+ "\nbackup es desde el mismo servidor. "
				+ "\nEl usuario de preferencia \"root\".");
		texInputDialogUser.setContentText("Ingrese Usuario MySQL:");
		
		TextField inputField = texInputDialogUser.getEditor();
		texInputDialogUser.getDialogPane().lookupButton(ButtonType.OK).setDisable(true);
		inputField.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				if(inputField.getText().isEmpty()){
					texInputDialogUser.getDialogPane().lookupButton(ButtonType.OK).setDisable(true);
				}else {
					texInputDialogUser.getDialogPane().lookupButton(ButtonType.OK).setDisable(false);
				}
					
			}
		});
		// Traditional way to get the response value.
		Optional<String> resultUser = texInputDialogUser.showAndWait();
		if (resultUser.isPresent()){
		    user=resultUser.get();
		   
		}else{
			return;
		}
		
		Dialog<String> dialog = new Dialog<>();
	    dialog.setTitle("Restaurar Backup");
	    dialog.setHeaderText("");
	    dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

	    PasswordField pwd = new PasswordField();
	    HBox content = new HBox();
	    content.setAlignment(Pos.CENTER_LEFT);
	    content.setSpacing(10);
	    content.getChildren().addAll(new Label("Ingrese Clave MySQL:"), pwd);
	    dialog.getDialogPane().setContent(content);
	    dialog.setResultConverter(dialogButton -> {
	        if (dialogButton == ButtonType.OK) {
	            return pwd.getText();
	        }
	        return null;
	    });

	    Optional<String> result = dialog.showAndWait();
	    if (result.isPresent()) {
	    	pass=result.get();
	    }else{
	    	return;
	    }
	    
	    
	
		
		if (file!=null) { 
			try {
				String ubicacion= String.valueOf(file.getAbsolutePath());
				String[] letterKey = String.valueOf(file.getAbsolutePath()).split("_");
				if (letterKey[1].equals("dbliri")) {
					Process child = Runtime.getRuntime().exec("cmd /c mysql -u "+user+" -p"+pass+" sistemaliri < \""+ubicacion+"\"");
				} 
				if(letterKey[1].equals("dbimg")) {
					Process child = Runtime.getRuntime().exec("cmd /c mysql -u "+user+" -p"+pass+" sistemaliriimagenes < \""+ubicacion+"\"");
				}
				
				
								
		        JOptionPane.showMessageDialog(null,"Backup restaurado exitosamente!");
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Error no se pudo restaurar"+e.getMessage());
			}
		}
		
	}
	
	
	
	
	
	
}
