package controller.articulo;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import controller.CPadre;
import controller.busqueda.CBusqueda;
import dal.Articulo;
import dal.Busqueda;
import dal.DatoAtomico;
import dal.Usuario;
import funciones.Conexion;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import login.LoginControler;
import model.articulo.MArticulo;
import model.articulo.MPieza;
import model.busqueda.MBusqueda;
import model.datoAtomico.MDatoAtomico;
import validacion.Validacion;

public class CAddArticulo extends CPadre implements Initializable {
	MArticulo mArticulo=new MArticulo();
	Articulo articulo=new Articulo();
	
	MDatoAtomico dDatoAtomico=new MDatoAtomico();
	DatoAtomico datoAtomico=new DatoAtomico();
	


	@FXML
    private Button buttonCerrar;
	
	@FXML
    private JFXButton jfxButtonCerrar;

    @FXML
    private JFXButton jfxButtonValidar;

    @FXML
    private Label labelVerificacion;

    @FXML
    private TextField textFieldStockAlmacen;

    @FXML
    private TextField textFieldUbicacion;

    @FXML
    private TextArea textAreaComentario;

    @FXML
    private TextField textFiedlPrecioCompra;

    @FXML
    private TextField textFiedlPrecioAlquiler;

    @FXML
    private TextField textFiedlPrecioVenta;

    @FXML
    private ImageView imageViewFoto;

    @FXML
    private Hyperlink hyperlinkSubirFoto;

    @FXML
    private TextField textFieldCodigoProveedor;

    @FXML
    private Button buttonBuscarProveedor;
    @FXML 
    private Button buttonEliminarProveedor;

    @FXML
    private TextArea textAreaResena;

    @FXML
    private TextField textFieldCodigo;

    @FXML
    private TextArea textAreaDescripcion;

    @FXML
    private ComboBox<DatoAtomico> comboBoxFamilia;

    @FXML
    private Button buttonAnadirFamilia;

    @FXML
    private TextField textFieldNroPiezas;

    @FXML
    private ComboBox<String> comboBoxTalla;

    @FXML
    private ComboBox<String> comboBoxGenero;

    @FXML
    private TextField textFieldDatoAdic1;

    @FXML
    private TextField textFieldDatoAdic2;

    @FXML
    private TextField textFieldDatoAdic3;

    
    @FXML
    private Label labelCodigoProveedor;

    @FXML
    private Label labelNombreProveedor;

    @FXML
    private Label labelArticuloProveedor;

    @FXML
    private Label labelDireccionProveedor;

    @FXML
    private Label labelTelefonoProveedor;
    
    
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comboBoxTalla.getItems().addAll("0", "2","4","6","8","10","12","14","16","S","M", "L", "XL","XXL","Pequeño","Mediano","Grande");
		comboBoxGenero.getItems().addAll("Femenino","Masculino","Femenino/Masculino");
		cargarComboBoxFamilia();
		agregarRetricciones();
		jfxButtonValidar.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				if(getAccion()==CPadre.INSERT){
					if(validar()){
						insertar(event);
					}
					
				}else if(getAccion()==CPadre.UPDATE){
					if(validar()){
						actualizar(event);
					}
					
				}
			}
		});
		
		buttonAnadirFamilia.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				agregarFamilia();				
			}
		});
		
		buttonBuscarProveedor.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				String url = "/view/busqueda/Busqueda.fxml";
				String css = "/estilocss/EstiloModal.css";
				
				CPadre cpadre = getControlerMostrarInterfazModalShowAndWait(url, css, MBusqueda.PROVEEDOR, CPadre.NULO);
				CBusqueda cArticulo = (CBusqueda)cpadre;
				extraerDatosDeBusqueda((Busqueda)cArticulo.getObjeto());	
			}
		});
		
		
		buttonEliminarProveedor.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				textFieldCodigoProveedor.setText("");
				limpiarDatosBusquedaProvedor();				
			}
		});
				
		textFieldCodigoProveedor.setOnKeyReleased(new EventHandler<Event>() {

			@Override
			public void handle(Event event) {
				 if (textFieldCodigoProveedor.getText().length()==5)
		            {
		               MBusqueda mbusqueda=new MBusqueda();
		               mbusqueda.iniciarConexionServidor();
		               Busqueda busqueda=mbusqueda.seleccionarBusquedaB(textFieldCodigoProveedor.getText());
		               mbusqueda.cerrarConexionServidor();
		               if(busqueda!=null){
		            	   extraerDatosDeBusqueda(busqueda);
		               }
		            }else{
		            	limpiarDatosBusquedaProvedor();
		            }
				 
			}
		} );
		
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
		
		hyperlinkSubirFoto.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Image image=seleccionarImage();
				if(image!=null){
					//articulo.setImagen(image);
					imageViewFoto.setImage(image);
				}
				
			}
		});
		
		
		//fousing
		textAreaDescripcion.requestFocus();
	}

	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		setAccion(tipoModal);
		if(CPadre.INSERT==tipoModal){
			
		}else if(CPadre.UPDATE==tipoModal){
			mArticulo.iniciarConexionServidor();
			articulo=mArticulo.seleccionarArticulo((int)objeto);
			mArticulo.cerrarConexionServidor();
			   
			MBusqueda mbusqueda=new MBusqueda();
            mbusqueda.iniciarConexionServidor();
            Busqueda busqueda=mbusqueda.seleccionarBusqueda(articulo.getIdProveedor());
            mbusqueda.cerrarConexionServidor();
            if(busqueda!=null){
         	   extraerDatosDeBusqueda(busqueda);
            }
            
			extraerDatos();
			
			/*mPieza.iniciarConexionServidor();
			pieza=mPieza.seleccionarArticulo((int)objeto);
			mPieza.cerrarConexionServidor();
			extraerDatos();*/
		}
		
	}

	@Override
	public Object getObjeto() {
		return null;
	}
	
	
	private boolean validar(){
		//this.articulo=new Articulo();
		this.articulo.setCodigo(textFieldCodigo.getText());
		
		if(textAreaDescripcion.getText().trim().isEmpty()){
			mostrarAlerta("Descripión de Artículo", "", "La descripción esta vacío.\nEste campo es requerido.", AlertType.WARNING);
			textAreaDescripcion.requestFocus();
			return false;
		}
		
		if(Integer.parseInt(textFieldStockAlmacen.getText())<=0){
			if(!consultarAlerta("El stock en almacen no puede ser "+textFieldStockAlmacen.getText()+".\n"
					+ "¿Esta seguro que desea continuar?")){
				textFieldStockAlmacen.requestFocus();
				return false;
			}
		}
		
		if(getAccion()==CPadre.UPDATE){
			MPieza mPieza=new MPieza();
			mPieza.iniciarConexionServidor();
			int nropiezas=mPieza.seleccionarNroPiezaArticulo(articulo.getId());
			mPieza.cerrarConexionServidor();
			if(nropiezas>Integer.parseInt(textFieldNroPiezas.getText())){
				mostrarAlerta("Número de Piezas del Artículo", "", "Este artículo tiene asociado "+nropiezas+" piezas.\n"
						+ "", AlertType.WARNING);
				textFieldNroPiezas.requestFocus();
				return false;
			}
		}
		
		if(!(Double.parseDouble(textFiedlPrecioAlquiler.getText())<Double.parseDouble(textFiedlPrecioCompra.getText()) &&Double.parseDouble(textFiedlPrecioCompra.getText())<Double.parseDouble(textFiedlPrecioVenta.getText()))){
			if(!consultarAlerta("Se ha detectado una incosistencia en los precios.\n"
					+ "¿Esta seguro que desea continuar?")){
				textFiedlPrecioCompra.requestFocus();
				return false;
			}
		}
		
		this.articulo.setDescripcion(textAreaDescripcion.getText());
		this.articulo.setTalla(comboBoxTalla.getSelectionModel().getSelectedItem());
		this.articulo.setGenero(comboBoxGenero.getSelectionModel().getSelectedItem());
		this.articulo.setIdFamilia((comboBoxFamilia.getSelectionModel().getSelectedItem()!=null)?comboBoxFamilia.getSelectionModel().getSelectedItem().getId():-1);
		this.articulo.setNroPiezas(Integer.parseInt(textFieldNroPiezas.getText()));
		this.articulo.setDatoAdic1(textFieldDatoAdic1.getText());
		this.articulo.setDatoAdic2(textFieldDatoAdic2.getText());
		this.articulo.setDatoAdic3(textFieldDatoAdic3.getText());
		this.articulo.setImagen(imageViewFoto.getImage());
		this.articulo.setResena(textAreaResena.getText());
		this.articulo.setStock(Integer.parseInt(textFieldStockAlmacen.getText()));
		this.articulo.setUbicacion(textFieldUbicacion.getText());
		
		this.articulo.setPrecioCompra(Double.parseDouble(textFiedlPrecioCompra.getText()));
		this.articulo.setPrecioAlquiler(Double.parseDouble(textFiedlPrecioAlquiler.getText()));
		this.articulo.setPrecioVenta(Double.parseDouble(textFiedlPrecioVenta.getText()));
		this.articulo.setComentarios(textAreaComentario.getText());
			
		//esto se hace cuando se extrae los datos de la busqueda xD
		//this.articulo.setIdProveedor(2);
		return true;
	}
	private boolean insertar(Event event){
		mArticulo.iniciarConexionServidor();
		mArticulo.insertarArticulo(this.articulo);
		mArticulo.cerrarConexionServidor();
		mostrarInformacion(labelVerificacion, mArticulo.getMensaje(), mArticulo.getNoError());
		if(mArticulo.getNoError()==CPadre.CORRECTO){
			((Stage)((Node)event.getSource()).getScene().getWindow()).close();
		}
		return false;
	}
	
	private boolean actualizar(Event event){
		mArticulo.iniciarConexionServidor();
		mArticulo.actualizarArticulo(this.articulo);
		mArticulo.cerrarConexionServidor();
		mostrarInformacion(labelVerificacion, mArticulo.getMensaje(), mArticulo.getNoError());
		if(mArticulo.getNoError()==CPadre.CORRECTO){
			((Stage)((Node)event.getSource()).getScene().getWindow()).close();
		}
		return false;
	}
	
	private boolean agregarFamilia(){
		TextInputDialog texInputDialogIp=new TextInputDialog("");
		texInputDialogIp.setTitle("Nueva Familia");
		texInputDialogIp.setHeaderText("");
		texInputDialogIp.setContentText("Ingrese Nueva Familia:");

		Optional<String> result = texInputDialogIp.showAndWait();
		if (result.isPresent()){
			String nuevaFamilia=result.get();
			datoAtomico=new DatoAtomico();
			datoAtomico.setNombre(nuevaFamilia);
			dDatoAtomico.iniciarConexionServidor();
			dDatoAtomico.insertarDatoAtomico(datoAtomico, MDatoAtomico.QUERY_INSERT_FAMILIA);
			dDatoAtomico.cerrarConexionServidor();
			
			datoAtomico=dDatoAtomico.getDatoAtomico();
			
			cargarComboBoxFamilia();
			comboBoxFamilia.getSelectionModel().select(datoAtomico);
			
		   
		}
		return false;
	}
	
	public void cargarComboBoxFamilia(){
		dDatoAtomico.iniciarConexionServidor();
		comboBoxFamilia.setItems(dDatoAtomico.seleccionarDatosAtomicos(MDatoAtomico.QUERY_SELECT_FAMILIAS));
		dDatoAtomico.cerrarConexionServidor();
	}
	public void extraerDatosDeBusqueda(Busqueda busqueda){
		articulo.setIdProveedor(busqueda.getId());
		textFieldCodigoProveedor.setText(busqueda.getCampo1());
		labelCodigoProveedor.setText(busqueda.getCampo2());
		labelArticuloProveedor.setText(busqueda.getCampo3());
		labelNombreProveedor.setText(busqueda.getCampo4());

		labelTelefonoProveedor.setText(busqueda.getCampo4());
		labelDireccionProveedor.setText(busqueda.getCampo5());
		
	}
	
	public void limpiarDatosBusquedaProvedor(){
		articulo.setIdProveedor(-1);
		labelCodigoProveedor.setText("");
		labelArticuloProveedor.setText("");
		labelNombreProveedor.setText("");

		labelTelefonoProveedor.setText("");
		labelDireccionProveedor.setText("");
		
	}
	
	private void agregarRetricciones(){
		textFiedlPrecioCompra.setTextFormatter(Validacion.doubleFormater());
		textFiedlPrecioAlquiler.setTextFormatter(Validacion.doubleFormater());
		textFiedlPrecioVenta.setTextFormatter(Validacion.doubleFormater());
		textFieldNroPiezas.setTextFormatter(Validacion.intFormater());
		textFieldStockAlmacen.setTextFormatter(Validacion.intFormater());
	}
	
	public void extraerDatos(){

		textFieldCodigo.setText(articulo.getCodigo());
		textAreaDescripcion.setText(articulo.getDescripcion());
		comboBoxTalla.getSelectionModel().select(articulo.getTalla());
		comboBoxGenero.getSelectionModel().select(articulo.getGenero());
		datoAtomico.setId(articulo.getIdFamilia());
		comboBoxFamilia.getSelectionModel().select(datoAtomico);
		textFieldNroPiezas.setText(String.valueOf(articulo.getNroPiezas()));
		textFieldDatoAdic1.setText(articulo.getDatoAdic1());
		textFieldDatoAdic2.setText(articulo.getDatoAdic2());
		textFieldDatoAdic3.setText(articulo.getDatoAdic3());
		imageViewFoto.setImage(articulo.getImagen());
		textAreaResena.setText(articulo.getResena());
		
		textFieldStockAlmacen.setText(String.valueOf(articulo.getStock()));
		textFieldUbicacion.setText(articulo.getUbicacion());
		textFiedlPrecioCompra.setText(String.valueOf(articulo.getPrecioCompra()));
		textFiedlPrecioAlquiler.setText(String.valueOf(articulo.getPrecioAlquiler()));
		textFiedlPrecioVenta.setText(String.valueOf(articulo.getPrecioVenta()));
		textAreaComentario.setText(String.valueOf(articulo.getComentarios()));
		
		
		//texare
		
	}

}
