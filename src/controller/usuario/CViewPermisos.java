package controller.usuario;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import controller.CPadre;
import dal.Usuario;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.usuario.MUsuario;

public class CViewPermisos extends CPadre implements Initializable {
	MUsuario mUsuario=new MUsuario();
	Usuario usuario=null;

	@FXML private JFXButton jfxButtonCerrar;
    @FXML private JFXButton jfxButtonValidar;
    @FXML private GridPane gridPanePermisos;
    @FXML private Label labelVerificacion;
    @FXML private Button buttonCerrar;
	

	@Override
	public Object getObjeto() {
		return null;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		jfxButtonValidar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent arg0) {
				mUsuario.iniciarConexionServidor();
				mUsuario.actualizarPermisos(usuario);
				mUsuario.cerrarConexionServidor();
				mostrarInformacion(labelVerificacion, mUsuario.getMensaje(), mUsuario.getNoError());
				((Stage)((Node)arg0.getSource()).getScene().getWindow()).close();

			}
		});
		
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
		if(tipoModal==CPadre.INSERT){
			usuario=(Usuario)objeto;
			 mostrarPermisos();
		}else if(tipoModal==CPadre.UPDATE){
			String dni=(String)objeto;
			mUsuario.iniciarConexionServidor();
			this.usuario=mUsuario.seleccionarUsuario(dni);
			this.mUsuario.cerrarConexionServidor();
			mostrarPermisos();

		}
	}
	
	public void mostrarPermisos(){
		String style="-fx-font-weight: bold";
		int column =0;
		int row=0;
		
		
		Label label=new Label();
		label.setText("Articulos");
		label.setStyle(style);

		gridPanePermisos.add(label, column, row++);
		gridPanePermisos.add(usuario.getSeeArticulos(), column, row++);
		gridPanePermisos.add(usuario.getAddArticulos(), column, row++);
		gridPanePermisos.add(usuario.getUpdArticulos(), column, row++);
		/*gridPanePermisos.add(usuario.getDelArticulos(), column, row++);*/row++;
		
		column++;
		row=0;
		
		
		label=new Label();
		label.setText("Clientes");
		label.setStyle(style);
		gridPanePermisos.add(label, column, row++);
		gridPanePermisos.add(usuario.getSeeClientes(), column, row++);
		gridPanePermisos.add(usuario.getAddClientes(), column, row++);
		gridPanePermisos.add(usuario.getUpdClientes(), column, row++);
		gridPanePermisos.add(usuario.getDelClientes(), column, row++);
		
		
		column++;
		row=0;;
		
		
		label=new Label();
		label.setText("Usuarios");
		label.setStyle(style);
		gridPanePermisos.add(label, column, row++);
		gridPanePermisos.add(usuario.getSeeUsuarios(), column, row++);
		gridPanePermisos.add(usuario.getAddUsuarios(), column, row++);
		gridPanePermisos.add(usuario.getUpdUsuarios(), column, row++);
		gridPanePermisos.add(usuario.getStatePasswordUsuarios(), column, row++);
		
		
		column++;
		row=0;
		
		label=new Label();
		label.setText("Proveedores");
		label.setStyle(style);
		gridPanePermisos.add(label, column, row++);
		gridPanePermisos.add(usuario.getSeeProveedor(), column, row++);
		gridPanePermisos.add(usuario.getAddProveedor(), column, row++);
		gridPanePermisos.add(usuario.getUpdProveedor(), column, row++);
		gridPanePermisos.add(usuario.getDelProveedor(), column, row++);
		
		
		
		column=0;
		row=6;
		
		label=new Label();
		label.setText("Importante");
		label.setStyle(style);
		gridPanePermisos.add(label, column, row++);
		gridPanePermisos.add(usuario.getSeeImportante(), column, row++);
		gridPanePermisos.add(usuario.getAddImportante(), column, row++);
		gridPanePermisos.add(usuario.getUpdImportante(), column, row++);
		gridPanePermisos.add(usuario.getDelImportante(), column, row++);

		
		column++;
		row=6;
		
		label=new Label();
		label.setText("Alquiler");
		label.setStyle(style);
		gridPanePermisos.add(label, column, row++);
		gridPanePermisos.add(usuario.getSeeAlquiler(), column, row++);
		gridPanePermisos.add(usuario.getConfigDesCupAlquiler(), column, row++);
		
		/*gridPanePermisos.add(usuario.getAddAlquiler(), column, row++);*/ 
		/*gridPanePermisos.add(usuario.getUpdAlquiler(), column, row++);*/row++;
		/*gridPanePermisos.add(usuario.getDelAlquiler(), column, row++);*/row++;
		
		
		column++;
		row=6;
		
		label=new Label();
		label.setText("Devolución");
		label.setStyle(style);
		gridPanePermisos.add(label, column, row++);
		gridPanePermisos.add(usuario.getSeeDevolucion(), column, row++);
		gridPanePermisos.add(usuario.getDevolDevolucion(), column, row++);
		gridPanePermisos.add(usuario.getTransEsterDevolucion(), column, row++);
		/*gridPanePermisos.add(usuario.getAddDevolucion(), column, row++);*/
		/*gridPanePermisos.add(usuario.getUpdDevolucion(), column, row++);*/
		/*gridPanePermisos.add(usuario.getDelDevolucion(), column, row++);*/row++;
		
		column++;
		row=6;
		
		label=new Label();
		label.setText("Contabilidad");
		label.setStyle(style);
		gridPanePermisos.add(label, column, row++);
		gridPanePermisos.add(usuario.getSeeContabilidad(), column, row++);
		/*gridPanePermisos.add(usuario.getAddContabilidad(), column, row++);*/row++;
		/*gridPanePermisos.add(usuario.getUpdContabilidad(), column, row++);*/row++;
		/*gridPanePermisos.add(usuario.getDelContabilidad(), column, row++);*/row++;
		
		
		column=0;
		row=12;
		
		label=new Label();
		label.setText("Reputación");
		label.setStyle(style);
		gridPanePermisos.add(label, column, row++);
		gridPanePermisos.add(usuario.getSeeReputacion(), column, row++);
		
		gridPanePermisos.add(usuario.getConfigInterReputacion(), column, row++);
		
		/*gridPanePermisos.add(usuario.getAddReputacion(), column, row++);*/
		gridPanePermisos.add(usuario.getUpdReputacion(), column, row++);
		/*gridPanePermisos.add(usuario.getDelReputacion(), column, row++);*/
		
		column++;
		row=12;
		
		label=new Label();
		label.setText("Estadística");
		label.setStyle(style);
		gridPanePermisos.add(label, column, row++);
		gridPanePermisos.add(usuario.getSeeEstadistica(), column, row++);
		/*gridPanePermisos.add(usuario.getAddEstadistica(), column, row++);*/row++;
		/*gridPanePermisos.add(usuario.getUpdEstadistica(), column, row++);*/row++;
		/*gridPanePermisos.add(usuario.getDelEstadistica(), column, row++);*/row++;
		
		column++;
		row=12;
		
		label=new Label();
		label.setText("Recomendación");
		label.setStyle(style);
		gridPanePermisos.add(label, column, row++);
		gridPanePermisos.add(usuario.getSeeRecomendacion(), column, row++);
		gridPanePermisos.add(usuario.getConfigPorRecomendacion(), column, row++);
		/*gridPanePermisos.add(usuario.getAddRecomendacion(), column, row++);*/
		/*gridPanePermisos.add(usuario.getUpdRecomendacion(), column, row++);*/row++;
		/*gridPanePermisos.add(usuario.getDelRecomendacion(), column, row++);*/row++;
		
		column++;
		row=12;
		
		label=new Label();
		label.setText("Seguridad");
		label.setStyle(style);
		gridPanePermisos.add(label, column, row++);
		gridPanePermisos.add(usuario.getSeeBackup(), column, row++);
		gridPanePermisos.add(usuario.getSeeRestoreBackup(), column, row++);
		//gridPanePermisos.add(usuario.getUpdRecomendacion(), column, row++);
		//gridPanePermisos.add(usuario.getDelRecomendacion(), column, row++);
		
		
	}
}
