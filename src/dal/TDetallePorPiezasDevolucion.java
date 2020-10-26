package dal;

import com.jfoenix.controls.JFXButton;

import controller.devolucion.CAddDevolucion;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import model.devolucion.MTDetallePorPiezasDevolucion;

public class TDetallePorPiezasDevolucion extends DALPadre{
	
	private int id=-1;
	private int idPieza=-1;
	private int idDetalleBoleta=-1;
	private int nro=-1;
	private String codigo=null;
	private String pieza=null;

	private int pendiente=-1;
	private int devuelto=-1;
	
	
	private Spinner<Integer> almacen=new Spinner<Integer>(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99,0));
	private Spinner<Integer> espera=new Spinner<Integer>(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99,0));
	private Spinner<Integer> lavanderia=new Spinner<Integer>(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99,0));
	private Spinner<Integer> reparacion=new Spinner<Integer>(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99,0));
	private Spinner<Integer> planchado=new Spinner<Integer>(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99,0));
	private Spinner<Integer> vendido=new Spinner<Integer>(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 99,0));
	private JFXButton guardar=new JFXButton("Guardar");
	
	
	
	private ComboBox<String> otrasOpciones=null;

	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdDetalleBoleta() {
		return idDetalleBoleta;
	}

	public void setIdDetalleBoleta(int idDetalleBoleta) {
		this.idDetalleBoleta = idDetalleBoleta;
	}

	public int getIdPieza() {
		return idPieza;
	}

	public void setIdPieza(int idPieza) {
		this.idPieza = idPieza;
	}

	public int getNro() {
		return nro;
	}

	public void setNro(int nro) {
		this.nro = nro;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getPieza() {
		return pieza;
	}

	public void setPieza(String pieza) {
		this.pieza = pieza;
	}

	
	public int getPendiente() {
		return pendiente;
	}

	public void setPendiente(int pendiente) {
		this.pendiente = pendiente;
	}

	public int getDevuelto() {
		return devuelto;
	}

	public void setDevuelto(int devuelto) {
		this.devuelto = devuelto;
	}

	public Spinner<Integer> getAlmacen() {
		return almacen;
	}

	public void setAlmacen(Spinner<Integer> almacen) {
		this.almacen = almacen;
	}

	public Spinner<Integer> getLavanderia() {
		return lavanderia;
	}

	public void setLavanderia(Spinner<Integer> lavanderia) {
		this.lavanderia = lavanderia;
	}

	public Spinner<Integer> getReparacion() {
		return reparacion;
	}

	public void setReparacion(Spinner<Integer> reparacion) {
		this.reparacion = reparacion;
	}

	public Spinner<Integer> getPlanchado() {
		return planchado;
	}

	public void setPlanchado(Spinner<Integer> planchado) {
		this.planchado = planchado;
	}
	
	

	public Spinner<Integer> getVendido() {
		return vendido;
	}

	public void setVendido(Spinner<Integer> vendido) {
		this.vendido = vendido;
	}



	public JFXButton getGuardar() {
		return guardar;
	}

	public void setGuardar(JFXButton guardar) {
		this.guardar = guardar;
	}

	public ComboBox<String> getOtrasOpciones() {
		return otrasOpciones;
	}

	public void setOtrasOpciones(ComboBox<String> otrasOpciones) {
		this.otrasOpciones = otrasOpciones;
	}
	
	
	
	public Spinner<Integer> getEspera() {
		return espera;
	}

	public void setEspera(Spinner<Integer> espera) {
		this.espera = espera;
	}

	public TDetallePorPiezasDevolucion(){
		almacen.setDisable(false);
		espera.setDisable(false);
		lavanderia.setDisable(false);
		planchado.setDisable(false);
		reparacion.setDisable(false);
		vendido.setDisable(false);
		
		
		
		guardar.setStyle("-fx-background-color: GREEN;"
				+ " -fx-text-fill: WHITE;"
				+ "-fx-font-size: 14px;");
		guardar.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				addEventoGuardar();
			}
		});
		
		almacen.valueProperty().addListener((obs, oldValue, newValue)->{
			
			calcuarPenDev();
		});
		
		espera.valueProperty().addListener((obs, oldValue, newValue)->{
			
			calcuarPenDev();
		});
		
		
		lavanderia.valueProperty().addListener((obs, oldValue, newValue)->{
			
			calcuarPenDev();
		});
		reparacion.valueProperty().addListener((obs, oldValue, newValue)->{
			
			calcuarPenDev();
		});
		planchado.valueProperty().addListener((obs, oldValue, newValue)->{
			
			calcuarPenDev();
		});
		
		vendido.valueProperty().addListener((obs, oldValue, newValue)->{
			
			calcuarPenDev();
		});
		
		
	}
	
	private void addEventoGuardar(){
		TDetallePorPiezasDevolucion tDetallePorPiezasDevolucion=this;
		MTDetallePorPiezasDevolucion mtDetallePorPiezasDevolucion=new MTDetallePorPiezasDevolucion();
		mtDetallePorPiezasDevolucion.iniciarConexionServidor();
		if(mtDetallePorPiezasDevolucion.actualizarTDetallePorPiezasDevolucion(tDetallePorPiezasDevolucion)){
			
		}
		
		mtDetallePorPiezasDevolucion.cerrarConexionServidor();
		
		
	}
	
	private void calcuarPenDev(){
		int total=pendiente+devuelto;
		
		int cAlm=almacen.getValue();
		int cEsp=espera.getValue();
		int cLav=lavanderia.getValue();
		int cRep=reparacion.getValue();
		int cPla=planchado.getValue();
		int cVen=vendido.getValue();
		int cDev=cAlm+cEsp+cLav+cRep+cPla+cVen;

		this.devuelto=cDev;
		this.pendiente=total-cDev;
		CAddDevolucion.refrescarTableDevolucion();
	}
	
	
	
	//get y set
	
	
	
	
	
	
}
