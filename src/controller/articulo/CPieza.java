package controller.articulo;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;

import controller.CPadre;
import dal.DatoAtomico;
import dal.Pieza;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import model.articulo.MPieza;
import model.datoAtomico.MDatoAtomico;

public class CPieza extends CPadre implements Initializable{
		
		private MDatoAtomico dDatoAtomico=new MDatoAtomico();
		private DatoAtomico datoAtomico=new DatoAtomico();
		
		private MPieza mPieza=new MPieza();
		private Pieza pieza=new Pieza();

		@FXML
	    private Button buttonCerrar;

	    @FXML
	    private JFXButton jfxButtonCerrar;

	    @FXML
	    private JFXButton jfxButtonValidar;

	    @FXML
	    private Label labelVerificacion;

	    @FXML
	    private TextField textFieldCodigo;

	    @FXML
	    private TextArea textArea;

	    @FXML
	    private ComboBox<DatoAtomico> comboBoxTipoMantenimiento;

	    @FXML
	    private Button buttonAnadirTipoMant;

	    @FXML
	    private JFXCheckBox jFXCheckBoxReqPlancharSi;

	    @FXML
	    private JFXCheckBox jFXCheckBoxReqPlancharNo;

	    @FXML
	    private TextField textFieldStock;

	    @FXML
	    private TextField textFieldPrecioCompra;

	    @FXML
	    private TextField textFieldPrecioAlquiler;

	    @FXML
	    private TextField textFieldPrecioVenta;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		cargarComboBoxTipoMantenimiento();
		
		jfxButtonValidar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(getAccion()==CPadre.INSERT){
					validar();
					insertar();
				}else if(getAccion()==CPadre.UPDATE){
					validar();
					actualizar();
				}
				
				if(mPieza.getNoError()==CPadre.CORRECTO){
					((Stage)((Node)event.getSource()).getScene().getWindow()).close();
				}
				
				mostrarInformacion(labelVerificacion, mPieza.getMensaje(), mPieza.getNoError());
			}
		});
		
		
		
		jFXCheckBoxReqPlancharSi.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {

				jFXCheckBoxReqPlancharNo.setSelected(false);
				jFXCheckBoxReqPlancharSi.setSelected(true);
			}
		});
		jFXCheckBoxReqPlancharNo.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				jFXCheckBoxReqPlancharNo.setSelected(true);
				jFXCheckBoxReqPlancharSi.setSelected(false);
					
			}
		});
		
		buttonAnadirTipoMant.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				agregarTipoMantenimiento();
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
		setAccion(tipoModal);
		if(CPadre.INSERT==tipoModal){
			pieza.setIdArticulo((int)objeto);
		}else if(CPadre.UPDATE==tipoModal){
			mPieza.iniciarConexionServidor();
			pieza=mPieza.seleccionarArticulo((int)objeto);
			mPieza.cerrarConexionServidor();
			extraerDatos();
		}
	}

	@Override
	public Object getObjeto() {
		return null;
	}

	
	
	private boolean agregarTipoMantenimiento(){
		TextInputDialog texInputDialogIp=new TextInputDialog("");
		texInputDialogIp.setTitle("Tipo de Mantenimiento");
		texInputDialogIp.setHeaderText("");
		texInputDialogIp.setContentText("Ingrese Nueva Tipo de Mantenimiento:");

		Optional<String> result = texInputDialogIp.showAndWait();
		if (result.isPresent()){
			String nuevaFamilia=result.get();
			datoAtomico=new DatoAtomico();
			datoAtomico.setNombre(nuevaFamilia);
			dDatoAtomico.iniciarConexionServidor();
			dDatoAtomico.insertarDatoAtomico(datoAtomico, MDatoAtomico.QUERY_INSERT_TIPO_MANT);
			dDatoAtomico.cerrarConexionServidor();
			
			datoAtomico=dDatoAtomico.getDatoAtomico();
			
			cargarComboBoxTipoMantenimiento();
			comboBoxTipoMantenimiento.getSelectionModel().select(datoAtomico);
			
		   
		}
		return false;
	}
	
	
	public void cargarComboBoxTipoMantenimiento(){
		dDatoAtomico.iniciarConexionServidor();
		comboBoxTipoMantenimiento.setItems(dDatoAtomico.seleccionarDatosAtomicos(MDatoAtomico.QUERY_SELECT_TIPO_MANTS));
		dDatoAtomico.cerrarConexionServidor();
	}
	
	
	private boolean validar(){
		
		//this.articulo=new Articulo();
		this.pieza.setDescripcion(textArea.getText().trim());
		
		this.pieza.setTipoMantenimiento(comboBoxTipoMantenimiento.getSelectionModel().getSelectedItem().getId());
		this.pieza.setRequierePlanchar((jFXCheckBoxReqPlancharSi.isSelected())?1:0);
		this.pieza.setStock(Integer.parseInt(textFieldStock.getText()));
		
		this.pieza.setPrecioCompra(Double.parseDouble(textFieldPrecioCompra.getText()));
		this.pieza.setPrecioAlquiler(Double.parseDouble(textFieldPrecioAlquiler.getText()));
		this.pieza.setPrecioVenta(Double.parseDouble(textFieldPrecioVenta.getText()));
		
		
		
		return false;
	}
	
	private boolean insertar(){
		mPieza.iniciarConexionServidor();
		mPieza.insertarPieza(this.pieza);
		mPieza.cerrarConexionServidor();
		return false;
	}
	
	private boolean actualizar(){
		mPieza.iniciarConexionServidor();
		mPieza.actualizarPieza(this.pieza);
		mPieza.cerrarConexionServidor();
		return false;
	}
	
	public void extraerDatos(){
		textFieldCodigo.setText(pieza.getCodigo());
		textArea.setText(pieza.getDescripcion());
		textFieldPrecioCompra.setText(String.valueOf(pieza.getPrecioCompra()));
		textFieldPrecioAlquiler.setText(String.valueOf(pieza.getPrecioAlquiler()));
		textFieldPrecioVenta.setText(String.valueOf(pieza.getPrecioVenta()));
		datoAtomico.setId(pieza.getTipoMantenimiento());
		comboBoxTipoMantenimiento.getSelectionModel().select(datoAtomico);
		textFieldStock.setText(String.valueOf(pieza.getStock()));
		if(pieza.getRequierePlanchar()==1){
			jFXCheckBoxReqPlancharSi.setSelected(true);
			jFXCheckBoxReqPlancharNo.setSelected(false);
		}else {
			jFXCheckBoxReqPlancharSi.setSelected(false);
			jFXCheckBoxReqPlancharNo.setSelected(true);
		}
	}
	
	
	public void formater(){
		
	} 
	
}
