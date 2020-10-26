package controller.recomendacion;

import java.awt.Dimension;
import java.awt.print.PageFormat;
import java.awt.print.Paper;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import com.jfoenix.controls.JFXButton;

import controller.CPadre;
import dal.Cliente;
import dal.Egreso;
import dal.Recomendacion;
import funciones.Funciones;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.cliente.MCliente;
import model.recomendacion.MRecomendacion;

public class CSeeBoletaPago  extends CPadre implements Initializable{
	
	@FXML private Button buttonCerrar;
    @FXML private JFXButton jfxButtonCancelar;
    @FXML private JFXButton jfxButtonValidar;
    @FXML private Label labelVerificacion;
    @FXML private AnchorPane anchorPane;
    
    MRecomendacion mRecomendacion = new MRecomendacion();
    Recomendacion recomendacion = new Recomendacion();
    MCliente mCliente = new MCliente();
    Cliente cliente = new Cliente();
    Egreso egreso = new Egreso();
    public int idCliente=-1;
    
    public String sumaTotal = null;
    public int idEgresoUltimo = -1;
    
    private JEditorPane jEditorPaneBoleta = new JEditorPane("text/html", "");
    private final SwingNode swingNode = new SwingNode();
    private JScrollPane editorScrollPane = new JScrollPane(jEditorPaneBoleta);
    private  String editorboleta="";
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		editorboleta=Funciones.leerEstructuraBoleta(new File("boleta/boletaPago.html"));
			
		jfxButtonCancelar.setOnAction(new EventHandler<ActionEvent>() {

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
			public void handle(ActionEvent arg0) {
				validar();
				insertarPagoRecomendacion();
				actualizarBoletaRecomendacion();
				String[] numeroFecha=seleccionarFechaNumero().split(";");
				editorboleta=editorboleta.replace(VariablesPago.NUM, numeroFecha[0]);
				editorboleta=editorboleta.replace(VariablesPago.F_EMISION, numeroFecha[1]);
				jEditorPaneBoleta.setText((editorboleta));
				imprimir();
			}
		});
		cargarEditorPane();
	}
	
	private void createSwingContent(final SwingNode swingNode) {
	      SwingUtilities.invokeLater(new Runnable() {
	          @Override
	          public void run() {
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
	
	public boolean insertarPagoRecomendacion(){
		mRecomendacion.iniciarConexionServidor();
		idEgresoUltimo = mRecomendacion.insertarEgresoRecomendacion(this.egreso, idCliente);
		mRecomendacion.cerrarConexionServidor();
		return true;
	}
	
	public boolean validar(){
		this.egreso.setEgreTipoDoc(3);
		this.egreso.setEgreIdConcepto(1);
		return false;
	}
	
	public void SeleccionarCliente(int IdCliente){
		mCliente.iniciarConexionServidor();
		cliente = mCliente.seleccionarCliente(IdCliente);
		mCliente.cerrarConexionServidor();
		
		editorboleta=editorboleta.replace(VariablesPago.CLI_COD_RECOM, cliente.getCli_codigo());
		editorboleta=editorboleta.replace(VariablesPago.CLI_SENIOR, cliente.getCli_apellNom());
		editorboleta=editorboleta.replace(VariablesPago.CLI_DNI, cliente.getCli_dni());
		editorboleta=editorboleta.replace(VariablesPago.CLI_DIR, cliente.getCli_direccion());
		editorboleta=editorboleta.replace(VariablesPago.CLI_CEL, cliente.getCli_telefono());
	}
	
	public void traerSumaTotalRecomendado(int IdCliente){
		mRecomendacion.iniciarConexionServidor();
		this.egreso.setEgreMonto(mRecomendacion.montoPagarRecomendado(IdCliente, 0));
		sumaTotal = mRecomendacion.montoPagarRecomendado(cliente.getCli_id(), 0);
		mRecomendacion.cerrarConexionServidor();
		
		editorboleta=editorboleta.replace(VariablesPago.TOTAL, sumaTotal);
	}
	
	public void actualizarBoletaRecomendacion(){
		mRecomendacion.iniciarConexionServidor();
		mRecomendacion.actualizarBoletaRecomendacion(cliente.getCli_id(), idEgresoUltimo);
		mRecomendacion.cerrarConexionServidor();
	}
	
	public String seleccionarFechaNumero(){
   	 	mRecomendacion.iniciarConexionServidor();
   	 	String numFech=mRecomendacion.seleccionarNumeroFechaPagoRecomendacion(idEgresoUltimo);
   	 	mRecomendacion.cerrarConexionServidor();
   	 	return numFech;
    }
	
	public void imprimir(){
 		Task<Void> task=new Task<Void>(){

 			@Override
 			protected Void call() throws Exception {
 				
 				try {
 					Paper paper = new Paper();
					paper.setSize(fromCMToPPI(7.6), fromCMToPPI(29.7)); // A4
					paper.setImageableArea(fromCMToPPI(1.0), fromCMToPPI(1.0), 
					                fromCMToPPI(7.6) - fromCMToPPI(1.0), fromCMToPPI(29.7) - fromCMToPPI(1.0));

					PageFormat pageFormat = new PageFormat();
					pageFormat.setPaper(paper);

					PrinterJob pj = PrinterJob.getPrinterJob();
					pj.setPrintable(jEditorPaneBoleta.getPrintable(null, null), pageFormat);
					PageFormat pf = pj.pageDialog(pageFormat);
					if (pj.printDialog()) {
					    pj.print();
					}
						
						
				} catch (PrinterException e) {
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

	@Override
	public void ejecutarAcciones(Object objeto, int tipoModal) {
		SeleccionarCliente((int)objeto);
		traerSumaTotalRecomendado((int)objeto);
		jEditorPaneBoleta.setText(editorboleta);
		idCliente = (int)objeto;
	}

	@Override
	public Object getObjeto() {
		// TODO Auto-generated method stub
		return null;
	}
}