package controller.alquiler;

import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

import javax.print.attribute.standard.MediaSizeName;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import com.jfoenix.controls.JFXButton;

import controller.CPadre;
import dal.Boleta;
import dal.Cliente;
import dal.DetalleBoleta;
import dal.TBoletaSee;
import dal.TDetalleBoleta;
import funciones.Funciones;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.MPadre;
import model.alquiler.MBoleta;
import model.alquiler.MDetalleBoleta;
import model.alquiler.MTBoletaSee;
import model.alquiler.MTDetalleBoleta;
import model.cliente.MCliente;
import model.devolucion.MDevolucion;
import validacion.Validacion;

public class CSeeBoleta  extends CPadre implements Initializable{
	public static final int RESERVA=1;
	public static final int FACTURAR=2;
 
	
	private int tipoModal=-1;
	private boolean estado=false;
	   @FXML
	    private Button buttonCerrar;

	    @FXML
	    private Label labelVerificacion;

	    @FXML
	    private AnchorPane anchorPane;

	    @FXML
	    private BorderPane borderPaneReservacion;

	    @FXML
	    private AnchorPane anchorPane1;

	    @FXML
	    private Label labelFechaRecojo;

	    @FXML
	    private Label lableDiasAntesODespues;

	    @FXML
	    private Label labelACuenta;

	    @FXML
	    private Label labelSaldo;

	    @FXML
	    private AnchorPane anchorPane11;

	    @FXML
	    private Label labelTotalACobrar;

	    @FXML
	    private TextField textFieldEfectivoRecibido;

	    @FXML
	    private Label labelCambio;

	    @FXML
	    private JFXButton jfxButtonCerrar;

	    @FXML
	    private JFXButton jfxButtonValidar;
	
	    @FXML
	    private GridPane gridPaneReservacion;
	    
	    @FXML
	    private AnchorPane anchorPaneReservacion;
	    
	    @FXML 
	    private GridPane gridPanePago;

	    private final JEditorPane jEditorPaneBoleta = new JEditorPane("text/html", "");
	    
	    private final SwingNode swingNode = new SwingNode();
	    private JScrollPane editorScrollPane = new JScrollPane(jEditorPaneBoleta);
	    
	    
	    private Boleta boleta=null;
	    private String editorboletaActual="";

	
	    
	    @Override
		public void initialize(URL location, ResourceBundle resources) {
	    	//editorboletaMa=Funciones.leerEstructuraBoleta(new File("boleta/boleta.html"));
	    	

	
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
		
	
			jfxButtonValidar.setOnAction(new EventHandler<ActionEvent>() {
				
				@Override
				public void handle(ActionEvent event) {
					
					jfxButtonValidar.setText("Imprimiendo..");
					jfxButtonValidar.setDisable(true);
					
					if(tipoModal==CSeeBoleta.FACTURAR){
						
						/*Facturar*/
						estado=true;
						
						boleta.setEstadoAccion(3);
						actualizarSerieNumero(boleta);
						String[] snAndFecha=seleccionarTBoletaSeeSerieNumAndFecha(boleta.getId()).split(";");
						//jEditorPaneBoleta.getText().replace(P.BOL_SER_NUM, snAndFecha[0]);
						//jEditorPaneBoleta.getText().replace(P.F_EMISION, snAndFecha[1]);
						editorboletaActual=editorboletaActual.replace(P.BOL_SER_NUM, snAndFecha[0]);
						editorboletaActual=editorboletaActual.replace(P.F_EMISION, snAndFecha[1]);
						jEditorPaneBoleta.setText((editorboletaActual));
						imprimir();
							
					}else if(tipoModal==CSeeBoleta.RESERVA){
						estado=true;
						imprimir();
					}
					
				
					
				}
			});
			
			textFieldEfectivoRecibido.setOnKeyReleased(new EventHandler<KeyEvent>() {

				@Override
				public void handle(KeyEvent event) {
					if(event.getCode()==KeyCode.ENTER){
						calcularCobrar();   
					}
					
				}
			});
			
			textFieldEfectivoRecibido.focusedProperty().addListener(new ChangeListener<Boolean>() {

				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
					if(!newValue){
						//calcularCobrar();
					}
				}
			});
			cargarEditorPane();


	}

	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		this.tipoModal=tipoModal;
		if(objeto instanceof Boleta && tipoModal==CSeeBoleta.FACTURAR){
			this.boleta=(Boleta)objeto;
			
		

			TBoletaSee tBoletaSee=seleccionarBoleta(boleta.getId());
			Cliente clienteSelec=seleccionarCliente(boleta.getIdCliente());
			ObservableList<TDetalleBoleta> arraySelec=seleccionarDetalleBoleta(boleta.getId());
			editorboletaActual=cargarBoleta(tBoletaSee,clienteSelec,arraySelec, false);

			//mostrar reserva normal xD
			mostrarReserva(tBoletaSee);
			mostrarCobrar(tBoletaSee);
			String duplicado="";
			if(boleta.getIdBoletaDuplicado()!=-1){
				TBoletaSee tBoletaSeeDup=seleccionarBoleta(boleta.getIdBoletaDuplicado());
				Cliente clienteSelecDup=seleccionarCliente(boleta.getIdCliente());
				ObservableList<TDetalleBoleta> arraySelecDup=seleccionarDetalleBoleta(boleta.getIdBoletaDuplicado());
				duplicado=cargarBoleta(tBoletaSeeDup,clienteSelecDup,arraySelecDup,true);
				//mostrar reserva normal xD
				String[] snAndFecha=seleccionarTBoletaSeeSerieNumAndFecha(boleta.getIdBoletaDuplicado()).split(";");
				duplicado=duplicado.replace(P.BOL_SER_NUM, snAndFecha[0]);
				duplicado=duplicado.replace(P.F_EMISION, snAndFecha[1]);
			}
			
			mostrarReserva(tBoletaSee);
			mostrarCobrar(tBoletaSee);
			editorboletaActual=editorboletaActual.replace(P.DUPLICADO, duplicado);
			//String texto=(editorboletaDuplicado+separatorEditor+editorboletaActual);
			//System.out.println(texto);
			jEditorPaneBoleta.setText(editorboletaActual);
	    	textFieldEfectivoRecibido.requestFocus();	

		}else if(objeto instanceof Boleta && tipoModal==CSeeBoleta.RESERVA){
			this.boleta=(Boleta)objeto;
			TBoletaSee tBoletaSee=seleccionarBoleta(boleta.getId());
			Cliente clienteSelec=seleccionarCliente(boleta.getIdCliente());
			ObservableList<TDetalleBoleta> arraySelec=seleccionarDetalleBoleta(boleta.getId());
			editorboletaActual=cargarBoletaReserva(tBoletaSee, clienteSelec,arraySelec );
			jEditorPaneBoleta.setText(editorboletaActual);
			mostrarReserva(tBoletaSee);
			gridPanePago.setVisible(false);

			

		}
	}

	@Override
	public Object getObjeto() {

		return estado;
	}

    private void createSwingContent(final SwingNode swingNode) {
	      SwingUtilities.invokeLater(new Runnable() {
	          @Override
	          public void run() {
	              //swingNode.setContent(new JButton("Click me!"));

	        	  
	        	  jEditorPaneBoleta.setEditable(false);
	        	  swingNode.setContent(editorScrollPane);
	        	  editorScrollPane.setVerticalScrollBarPolicy(
			                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	        	  editorScrollPane.setHorizontalScrollBarPolicy(
	        			  JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	        	  editorScrollPane.setPreferredSize(new Dimension(250, 145));
	        	  editorScrollPane.setMinimumSize(new Dimension(10, 10));
	          }
	      });
	  }	
      
      
      private void cargarEditorPane(){
    	  
    		createSwingContent(swingNode);
  	      
	  	  	AnchorPane.setBottomAnchor(swingNode, 5.0);
	  	  	AnchorPane.setLeftAnchor(swingNode, 5.0);
	  	  	AnchorPane.setRightAnchor(swingNode, 5.0);
	  	  	AnchorPane.setTopAnchor(swingNode, 5.0);
	  		anchorPane.getChildren().add(swingNode);
  			
      }
      				
      private void mostrarReserva(TBoletaSee tBoletaSee){
    	  
    	  if(tBoletaSee.getSepFRecojo()==null){
    		  anchorPaneReservacion.setDisable(true);
    		  
    		  return;
    	  }
    	  
    	  labelFechaRecojo.setText(tBoletaSee.getSepFRecojo());
    	  String mensajeDiasOAntes="Recojo Puntual";
    	  if(tBoletaSee.getDiasRecojo()>0){
    		  mensajeDiasOAntes="Recojo "+tBoletaSee.getDiasRecojo()+" dias antes.";
    	  }else if(tBoletaSee.getDiasRecojo()<0){
    		  mensajeDiasOAntes="Recojo "+tBoletaSee.getDiasRecojo()*(-1)+" dias despues.";
    	  }
    	  lableDiasAntesODespues.setText(mensajeDiasOAntes);
    	  labelACuenta.setText(tBoletaSee.getSepFACuenta());
    	  labelSaldo.setText(tBoletaSee.getSepSaldo()); 
    	  
      }
      
      public boolean calcularCobrar(){
    	  
    	  
    	  double efectivo=0;
    	  double totalPagar=0;
    	  	try{
    	  		
    	  		textFieldEfectivoRecibido.setText(Validacion.doubleAStringMoneda(Double.parseDouble(textFieldEfectivoRecibido.getText().trim())));
    	  		totalPagar=Double.parseDouble(labelTotalACobrar.getText());
    	  		efectivo=Double.parseDouble(textFieldEfectivoRecibido.getText());
    	  		
    		}catch(Exception e){
    			e.printStackTrace();
    			textFieldEfectivoRecibido.setText(" ");
    			mostrarAlerta("Pago", "", "No se pudo procesar el efectivo ingresado.", AlertType.WARNING);
    			textFieldEfectivoRecibido.requestFocus();
    			labelCambio.setText(" ");
    			return false;
    		}
    		double vuelto=efectivo-totalPagar;
    		if(vuelto<0){
    			textFieldEfectivoRecibido.setText(" ");
    			
    			mostrarAlerta("Pago", "", "El efectivo ingresado es menor al monto a cobrar.", AlertType.WARNING);
    			labelCambio.setText(" " );
    			textFieldEfectivoRecibido.requestFocus();	
    		}
    		DecimalFormat dc = new DecimalFormat("#0.00");
    		labelCambio.setText(dc.format(vuelto));
    		
    		return true;
      }
      
      private void mostrarCobrar(TBoletaSee tBoletaSee){
    	  /*cobro*/
    	  
    	  
    	 String totalACobrar=tBoletaSee.getTotal();
    	  if(tBoletaSee.getSepFACuenta()!=null){
    		  totalACobrar=Validacion.doubleAStringMoneda((Double.parseDouble(tBoletaSee.getTotal())-
    				  Double.parseDouble(tBoletaSee.getSepFACuenta())));
    	  }
    	
    	  labelTotalACobrar.setText(totalACobrar);
      }
      
      private String cargarBoletaReserva(TBoletaSee tBoletaSee, Cliente cliente,  ObservableList<TDetalleBoleta> arrayDetalleBoleta){
    	  String boletaReserva="";
    	  boletaReserva=Funciones.leerEstructuraBoleta(new File("boleta/boletaReservacion.html"));//editorboletaMa; 
    	  
    	  boletaReserva=boletaReserva.replace(P.NUM_RESER, tBoletaSee.getId()+"");
    	  boletaReserva=boletaReserva.replace(P.F_EMISION, tBoletaSee.getFecha());
    	  boletaReserva=boletaReserva.replace(P.F_RECOJO, tBoletaSee.getSepFRecojo());

    	  
    	  boletaReserva=boletaReserva.replace(P.CLI_COD_RECOM, cliente.getCli_codigo());
    	  boletaReserva=boletaReserva.replace(P.CLI_SENIOR, cliente.getCli_apellNom());
    	  boletaReserva=boletaReserva.replace(P.CLI_DNI, cliente.getCli_dni());
    	  boletaReserva=boletaReserva.replace(P.CLI_DIR, (cliente.getCli_referencia()!=null)?cliente.getCli_referencia():" ------------"/*cliente.getCli_direccion()*/);
    	  //boletaReserva=boletaReserva.replace(P.CLI_CEL, cliente.getCli_telefono());
    	  
    	  /*Añadir articulos a reservacion*/
    	  //creamos la tabla detalleboleta
    	  String detalleBoleta="";
    	  
    	  for (TDetalleBoleta tDetalleBoleta : arrayDetalleBoleta) {
    		  
    		  detalleBoleta = detalleBoleta+ "<tr> "+
  					" <th style='text-align:center;'><b style='font-family: arial narrow; font-size: 5px; font-weight: normal;'> "+tDetalleBoleta.getCant()+" </b></th> "+
  					" <th style='text-align:center;'><b style='font-family: arial narrow; font-size: 5px; font-weight: normal;'> "+tDetalleBoleta.getCodigo()+" </b></th> "+
  					" <th style='text-align:left;'><b style='font-family: arial narrow; font-size: 5px; font-weight: normal;'> "+tDetalleBoleta.getDescripcion()+" </b></th> "+
  					" <th style='text-align:center;'><b style='font-family: arial narrow; font-size: 5px; font-weight: normal;'> "+tDetalleBoleta.getPrecioUnit()+" </b></th> "+
  					" <th style='text-align:right;'><b style='font-family: arial narrow; font-size: 5px; font-weight: normal;'> "+tDetalleBoleta.getImporte()+" </b></th> "+
  				" </tr> ";

		}
    	  
    	  boletaReserva=boletaReserva.replace(P.DET_BOLETA, detalleBoleta);
    	  

    	  /* fin de añadir articulos */
    	  
    	  boletaReserva=boletaReserva.replace(P.CONCEPT_RESER, (tBoletaSee.getTipo()==Boleta.TIPO_VENTA_ALQUILER)?"Reserva de Alquiler S/":(tBoletaSee.getTipo()==Boleta.TIPO_VENTA_VENTA)?"Reserva de Venta S/":"");
    	  boletaReserva=boletaReserva.replace(P.BOL_TOTAL_PAGAR, tBoletaSee.getTotal());
    	  boletaReserva=boletaReserva.replace(P.A_CUENTA, tBoletaSee.getSepFACuenta());
    	  boletaReserva=boletaReserva.replace(P.SALDO, tBoletaSee.getSepSaldo());

    	  return boletaReserva;
    	  
    	  
    	  
      }
      private String  cargarBoleta(TBoletaSee tBoletaSee, Cliente cliente,  ObservableList<TDetalleBoleta> arrayDetalleBoleta, boolean duplicado){
    	  String editorboleta="";
    	  if(duplicado){
    		  editorboleta=Funciones.leerEstructuraBoleta(new File("boleta/boletaDuplicado.html"));//editorboletaMa;  
    	  }else{
    		  editorboleta=Funciones.leerEstructuraBoleta(new File("boleta/boleta.html"));//editorboletaMa;
    	  }
    	  	
    
    	  //fecha 
    	  //editorboleta.replace(P.F_EMISION, boleta.getFecha());
    	 // editorboleta=editorboleta.replace(P.F_EMISION, tBoletaSee.getFecha());
    	  /*1=Alquiler , 2=venta , 3=duplicado de venta*/
    	  if(tBoletaSee.getTipo()==1){
    		editorboleta=editorboleta.replace(P.F_ENTREGA, tBoletaSee.getfEntrega());
      	  	editorboleta=editorboleta.replace(P.F_DEVOL, tBoletaSee.getfDevolucion());

    	  }else {
    		  editorboleta=editorboleta.replace(P.F_ENTREGA, "----------");
        	  	editorboleta=editorboleta.replace(P.F_DEVOL, "----------");
  
    	  }
    		      	  //cliente
    	  editorboleta=editorboleta.replace(P.CLI_COD_RECOM, cliente.getCli_codigo());
    	  editorboleta=editorboleta.replace(P.CLI_SENIOR, cliente.getCli_apellNom());
    	  editorboleta=editorboleta.replace(P.CLI_DNI, cliente.getCli_dni());
    	  editorboleta=editorboleta.replace(P.CLI_DIR, (cliente.getCli_referencia()!=null)?cliente.getCli_referencia():" ------------");
    	  //editorboleta=editorboleta.replace(P.CLI_CEL, cliente.getCli_telefono());
    	  String garantia="Ninguna";
    	  if(tBoletaSee.getTipo()==Boleta.TIPO_VENTA_ALQUILER){
    		  String dni=(!isNullOrEmtpy(tBoletaSee.getGarNroDni()))?"DNI: "+tBoletaSee.getGarNroDni()+" ":"";
    		  String dniMenor=(!isNullOrEmtpy(tBoletaSee.getGarNroDniMenor()))?("DNI Menor: "+tBoletaSee.getGarNroDniMenor()):" ";
    		  String licencia=(!isNullOrEmtpy(tBoletaSee.getGarNroLicencia()))?("DNI Licencia: "+tBoletaSee.getGarNroLicencia()+" "):"";
    		 String dinero=(!isNullOrEmtpy(tBoletaSee.getGarMonto()))?("Monto: "+tBoletaSee.getGarMonto()+" "):"";
    		 String enlazarBoleta=(!isNullOrEmtpy(tBoletaSee.getGarSerieBoleta()) && !isNullOrEmtpy(tBoletaSee.getNumeroBoleta()))?"Boleta :"+tBoletaSee.getSerieBoleta()+"-"+tBoletaSee.getNumeroBoleta()+" ":"";
    		  // String enlazarBoleta=(!isNullOrEmtpy(tBoleta.))?"DNI Licencia: "+tBoletaSee.getGarNroLicencia();
    		 String otro=(!isNullOrEmtpy(tBoletaSee.getGarOtroEspecifique()))?"Otro: "+tBoletaSee.getGarOtroEspecifique():" ";
    		 garantia=dni+dniMenor+licencia+dinero+enlazarBoleta+otro;
    	  }
    	  editorboleta=editorboleta.replace(P.GARANTIA, garantia);
    	  
    	  
    	  //creamos la tabla detalleboleta
    	  String detalleBoleta="";
    	  String grupoResenia="";
    	  for (TDetalleBoleta tDetalleBoleta : arrayDetalleBoleta) {
    		  
    		  detalleBoleta = detalleBoleta+ "<tr> "+
  					" <th style='text-align:center;'><b style='font-family: arial narrow; font-size: 5px; font-weight: normal;'> "+tDetalleBoleta.getCant()+" </b></th> "+
  					" <th style='text-align:center;'><b style='font-family: arial narrow; font-size: 5px; font-weight: normal;'> "+tDetalleBoleta.getCodigo()+" </b></th> "+
  					" <th style='text-align:left;'><b style='font-family: arial narrow; font-size: 5px; font-weight: normal;'> "+tDetalleBoleta.getDescripcion()+" </b></th> "+
  					" <th style='text-align:center;'><b style='font-family: arial narrow; font-size: 5px; font-weight: normal;'> "+tDetalleBoleta.getPrecioUnit()+" </b></th> "+
  					" <th style='text-align:right;'><b style='font-family: arial narrow; font-size: 5px; font-weight: normal;'> "+tDetalleBoleta.getImporte()+" </b></th> "+
  				" </tr> ";
			if(tDetalleBoleta.getResenia()!=null){
				grupoResenia=grupoResenia+"<b>"+tDetalleBoleta.getCodigo()+ ": </b>"+
						tDetalleBoleta.getResenia()+"<br>";
			}
			
			
		}
    	  
    	  editorboleta=editorboleta.replace(P.DET_BOLETA, detalleBoleta);
    	  
    	  
    	  editorboleta=editorboleta.replaceAll(P.ART_RESENIA, grupoResenia);
    	  
    	  editorboleta=editorboleta.replace(P.BOL_SUB_TOTAL,tBoletaSee.getSubTotal());
    	  String descupon="";
    	  if(Double.parseDouble(tBoletaSee.getDesCupones())!=0){
    		  descupon="<tr> "+
			" <th colspan=\"4\" style=\"text-align: right;\"><b style=\"font-family: arial narrow; font-size: 5px\">Descuento Cupon S/</b></th> "+
			" <th style=\"text-align: right;\"><b style=\"font-family: arial narrow; font-size: 5px\">"+tBoletaSee.getDesCupones()+"</b></th> "+
		" </tr>";
        	  editorboleta=editorboleta.replace(P.BOL_DES_CUPON,descupon);
    		  
    	  }
    	  String desAdicicional="";
    	  if(Double.parseDouble(tBoletaSee.getDesAdic())!=0){
    		   desAdicicional="<tr> " +
			" <th colspan=\"4\" style=\"text-align: right;\"><b style=\"font-family: arial narrow; font-size: 5px\">Descuento Adicional S/</b></th> " +
			" <th style=\"text-align: right;\"><b style=\"font-family: arial narrow; font-size: 5px\">"+tBoletaSee.getDesAdic()+"</b></th> "+
		" </tr> ";
    	  }
    	  editorboleta=editorboleta.replace(P.BOL_DES_ADIC,desAdicicional);
    	  editorboleta=editorboleta.replace(P.BOL_TOTAL_PAGAR,tBoletaSee.getTotal());
    	  
    	  

    	  return editorboleta;
      }
      
     private Cliente seleccionarCliente(int id){
  		MCliente mCliente=new MCliente();
  		mCliente.iniciarConexionServidor();
  		Cliente cliente=mCliente.seleccionarCliente(id);
  		mCliente.cerrarConexionServidor();
  		
  		return cliente;
  	}
     /*
     private Boleta seleccionarBoleta(int id){
    	MBoleta mBoleta=new MBoleta();
    	mBoleta.iniciarConexionServidor();
    	Boleta boleta=mBoleta.selecccionarBoletaGarantia(id);
    	mBoleta.cerrarConexionServidor();
    	return boleta;
    	
     }
     
     *
     *
     */
     
     private TBoletaSee seleccionarBoleta(int idBoleta){
    	 MTBoletaSee mTBoletaSee=new MTBoletaSee();
    	 mTBoletaSee.iniciarConexionServidor();
    	 TBoletaSee tBoletaSee=mTBoletaSee.seleccionarTBoletaSee(idBoleta);
    	 mTBoletaSee.cerrarConexionServidor();
    	 return tBoletaSee;
    	
     }
     
     private boolean actualizarSerieNumero(Boleta boleta){
    	 MBoleta mBoleta=new MBoleta();
    	 mBoleta.iniciarConexionServidor();
    	 mBoleta.actualizarBoletaFacturado(boleta);
    	 mBoleta.cerrarConexionServidor();
    	 if(mBoleta.getNoError()==MPadre.CORRECTO){
    		 return true;
    	 }
    	 return false;
     }
     
     
     
     private ObservableList<TDetalleBoleta> seleccionarDetalleBoleta(int idBoleta){
    	 MTDetalleBoleta mTDetalleBoleta=new MTDetalleBoleta();
    	 mTDetalleBoleta.iniciarConexionServidor();
    	 ObservableList<TDetalleBoleta> array=mTDetalleBoleta.seleccionarDetalleBoletaPorBoleta(idBoleta);
    	 mTDetalleBoleta.cerrarConexionServidor();
    	 
    	 return array;
     }
     
     
     private String seleccionarTBoletaSeeSerieNumAndFecha(int idBoleta){
    	 MTBoletaSee mTBoletaSee=new MTBoletaSee();
    	 mTBoletaSee.iniciarConexionServidor();
    	 String serienumero=mTBoletaSee.seleccionarTBoletaSeeSerieNum(idBoleta);
    	 mTBoletaSee.cerrarConexionServidor();
    	 return serienumero;
    	
     }
     
     
     public void imprimir(){
  
  		Task<Void> task=new Task<Void>(){

  			@Override
  			protected Void call() throws Exception {

  				
  					try {
  						
  						
  						Paper paper = new Paper();
  						paper.setSize(fromCMToPPI(7.6), fromCMToPPI(29.7)); // A4
  						paper.setImageableArea(fromCMToPPI(0.5), fromCMToPPI(0.0), 
  						                fromCMToPPI(7.6) - fromCMToPPI(0.5), fromCMToPPI(29.7) - fromCMToPPI(0.5));

  						PageFormat pageFormat = new PageFormat();
  						pageFormat.setPaper(paper);

  						PrinterJob pj = PrinterJob.getPrinterJob();
  						pj.setPrintable(jEditorPaneBoleta.getPrintable(null, null), pageFormat);
  						PageFormat pf = pj.pageDialog(pageFormat);
  						
  						if (pj.printDialog()) {
  						    pj.print();
  						  //if(jEditorPaneBoleta.print()){
    							estado=true;
    							Platform.runLater(new Runnable() {
									
									@Override
									public void run() {
			  						    mostrarAlerta("Imprimiendo","" , "Imprisión realizada correctamente.", AlertType.CONFIRMATION);
		    							((Stage)(jfxButtonValidar).getScene().getWindow()).close();
										
									}
								});
    						

    						//}
  						}
  						Platform.runLater(new Runnable() {
							
							@Override
							public void run() {
								jfxButtonValidar.setText("Imprimir");
								jfxButtonValidar.setDisable(false);								
							}
						});
  						
  						
 					} catch (Exception e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					}
  					return null;
  				
  			}
  			
  		};
  		Thread hilo=new Thread(task);
  		hilo.setDaemon(true);
  		hilo.start();
  	}
     
     protected static double fromCMToPPI(double cm) {
    	    return toPPI(cm * 0.393700787);
    	}

    	protected static double toPPI(double inch) {
    	    return inch * 72d;
    	}
}
