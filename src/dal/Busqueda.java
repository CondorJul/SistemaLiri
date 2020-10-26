package dal;

import javafx.scene.control.CheckBox;

public class Busqueda {
	
   private int id=-1;
   private String codigo=null;
   private CheckBox checBox=null;
   private String campo1=null;
   private String campo2=null;
   private String campo3=null;
   private String campo4=null;
   private String campo5=null;
   private String campo6=null;
   private String campo7=null;
	   
	   
	   
	public Busqueda(CheckBox checBox, String campo1, String campo2, String campo3, String campo4, String campo5,
		String campo6, String campo7) {
	super();
	this.checBox = checBox;
	this.campo1 = campo1;
	this.campo2 = campo2;
	this.campo3 = campo3;
	this.campo4 = campo4;
	this.campo5 = campo5;
	this.campo6 = campo6;
	this.campo7 = campo7;
	}
	
	public Busqueda(){
		
	}
	public CheckBox getChecBox() {
		return checBox;
	}
	public void setChecBox(CheckBox checBox) {
		this.checBox = checBox;
	}
	public String getCampo1() {
		return campo1;
	}
	public void setCampo1(String campo1) {
		this.campo1 = campo1;
	}
	public String getCampo2() {
		return campo2;
	}
	public void setCampo2(String campo2) {
		this.campo2 = campo2;
	}
	public String getCampo3() {
		return campo3;
	}
	public void setCampo3(String campo3) {
		this.campo3 = campo3;
	}
	public String getCampo4() {
		return campo4;
	}
	public void setCampo4(String campo4) {
		this.campo4 = campo4;
	}
	public String getCampo5() {
		return campo5;
	}
	public void setCampo5(String campo5) {
		this.campo5 = campo5;
	}
	public String getCampo6() {
		return campo6;
	}
	public void setCampo6(String campo6) {
		this.campo6 = campo6;
	}
	public String getCampo7() {
		return campo7;
	}
	public void setCampo7(String campo7) {
		this.campo7 = campo7;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	
	
	   
}
