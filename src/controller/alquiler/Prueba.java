package controller.alquiler;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.layout.BorderPane;

public class Prueba {
	@FXML
    private TextField textFieldCodigoCliente;

    @FXML
    private Button buttonBuscarCliente;

    @FXML
    private Button buttonEliminarCliente;

    @FXML
    private JFXTextField jFXTextFieldCodigo;

    @FXML
    private JFXTextField jFXTextFieldApellidosNombres;

    @FXML
    private JFXTextField jFXTextFieldDireccion;

    @FXML
    private JFXTextField jFXTextFieldReferencia;

    @FXML
    private JFXTextField jFXTextFieldDNI;

    @FXML
    private JFXTextField jFXTextFieldCorreo;

    @FXML
    private JFXTextField jFXTextFieldTelefono;

    @FXML
    private TableView<?> tableViewArticulos;

    @FXML
    private TableColumn<?, ?> tableColumnCantArticulos;

    @FXML
    private TableColumn<?, ?> tableColumnCodigoArticulos;

    @FXML
    private TableColumn<?, ?> tableColumnDescripcionArticulos;

    @FXML
    private TableColumn<?, ?> tableColumnPrecioArticulos;

    @FXML
    private TableColumn<?, ?> tableColumnImporteArticulos;

    @FXML
    private TableColumn<?, ?> tableColumnEliminarArticulos;

    @FXML
    private TextField textFieldSubTotal;

    @FXML
    private TextField textFieldDesCupones;

    @FXML
    private TextField textFieldDesAdicional;

    @FXML
    private TextField textFieldTotalPagar;

    @FXML
    private JFXCheckBox jFXCheckBoxCupon1;

    @FXML
    private JFXCheckBox jFXCheckBoxCupon2;

    @FXML
    private JFXCheckBox jFXCheckBoxCupon3;

    @FXML
    private JFXRadioButton jFXRadioButtonDesNinguno;

    @FXML
    private ToggleGroup groupDesAdic;

    @FXML
    private JFXRadioButton jFXRadioButtonDesSoles;

    @FXML
    private JFXRadioButton jFXRadioButtonDesPor;

    @FXML
    private Label labelDescuentoEn;

    @FXML
    private Spinner<?> spinnerDesMonPor;

    @FXML
    private TableView<?> tableViewPiezas;

    @FXML
    private TableColumn<?, ?> tableColumnSeleccionPiezas;

    @FXML
    private TableColumn<?, ?> tableColumnNroPiezas;

    @FXML
    private TableColumn<?, ?> tableColumnCantPiezas;

    @FXML
    private TableColumn<?, ?> tableColumnDescripcionPiezas;

    @FXML
    private TableColumn<?, ?> tableColumnPrecioPiezas;

    @FXML
    private TableColumn<?, ?> tableColumnImportePiezas;

    @FXML
    private BorderPane boderPaneBodyGarantia;

    @FXML
    private JFXCheckBox jFXCheckBoxDniGarantia;

    @FXML
    private JFXCheckBox jFXCheckBoxLicenciaGarantia;

    @FXML
    private JFXCheckBox jFXCheckBoxDineroGarantia;

    @FXML
    private JFXCheckBox jFXCheckBoxDniMenorGarantia;

    @FXML
    private TextField textFieldNroDniGarantia;

    @FXML
    private TextField textFieldNroMenorGarantia;

    @FXML
    private TextField textFieldNroLicenciaGarantia;

    @FXML
    private TextField textFieldDineroGarantia;

    @FXML
    private JFXCheckBox jFXTextFieldOtroGarantia;

    @FXML
    private TextArea textAreaOtroGarantia;

    @FXML
    private Button buttonEnlazarBoleta;

    @FXML
    private Button buttonEliminarEnlazarBoleta;

    @FXML
    private TextField textFieldNroBoletaGarantia;

    @FXML
    private JFXCheckBox jFXTextFieldBoletaAlqGarantia;

    @FXML
    private TextField textFieldSerieBoletaGarantia;

    @FXML
    private BorderPane boderPaneBodyRecomendacion;

    @FXML
    private TextField textFieldCodigoClienteRecome;

    @FXML
    private Button buttonBuscarClienteRecome;

    @FXML
    private Button buttonEliminarClienteRecomen;

    @FXML
    private BorderPane boderPaneBodyDevolucion;

    @FXML
    private DatePicker datePickerFechaEntrega;

    @FXML
    private DatePicker datePickerFechaDevolucion;

    @FXML
    private JFXButton jFXButtonReservar;

    @FXML
    private JFXButton jFXButtonEfectuarOperacion;

    @FXML
    private MenuItem menuItemNuevoAlquiler;

    @FXML
    private MenuItem menuItemNuevoVenta;

    @FXML
    private BorderPane boderPaneBodyDevolucion1;

    @FXML
    private JFXCheckBox jFXCheckBoxDniGarantia1;

    @FXML
    private TextField textFieldDineroGarantia1;

    @FXML
    private TextField textFieldDineroGarantia11;

    @FXML
    private TableView<?> tablaprueba;

    @FXML
    private TableColumn<?, ?> column1;

    @FXML
    private TableColumn<?, ?> column2;

    @FXML
    private TableColumn<?, ?> tableColumnNro;

    @FXML
    private TableColumn<?, ?> tableColumnDescripcionArticulo;

    @FXML
    private TableColumn<?, ?> tableColumnTallla;

    @FXML
    private TableColumn<?, ?> tableColumnGenero;

    @FXML
    private TableColumn<?, ?> tableColumnPrecioCompra;

    @FXML
    private TableColumn<?, ?> tableColumnPrecioAlquiler;

    @FXML
    private TableColumn<?, ?> tableColumnPrecioVenta;

    @FXML
    private TreeTableColumn<?, ?> tableColumnStock;

    @FXML
    private TableColumn<?, ?> tableColumnAgregar;

    @FXML
    private TextField textFieldBuscarProductos;

    @FXML
    private TreeTableView<?> treeTableViewArticulos;

    @FXML
    private TreeTableColumn<?, ?> treeTableColumnCodigo;

    @FXML
    private TreeTableColumn<?, ?> treeTableColumnDescripcion;

    @FXML
    private TreeTableColumn<?, ?> treeTableColumnTalla;

    @FXML
    private TreeTableColumn<?, ?> treeTableColumnGenero;

    @FXML
    private TreeTableColumn<?, ?> treeTableColumnPCompra;

    @FXML
    private TreeTableColumn<?, ?> treeTableColumnPAlquiler;

    @FXML
    private TreeTableColumn<?, ?> treeTableColumnPVenta;

    @FXML
    private TreeTableColumn<?, ?> treeTableColumnStock;

    @FXML
    private TreeTableColumn<?, ?> treeTableColumnAgregar;

    @FXML
    private TextField textFieldBuscarAlquiler;

    @FXML
    private TreeTableView<?> treeTableViewAlquiler;

    @FXML
    private TreeTableColumn<?, ?> tableColumnCodigo;

    @FXML
    private TreeTableColumn<?, ?> tableColumnNombre;

    @FXML
    private TreeTableColumn<?, ?> tableColumnFamilia;


}
