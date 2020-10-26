package controller.reputacion;

import javafx.geometry.Pos;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;

public class CProgress extends HBox{
	public static double VMROJO=-1;
	public static double VMAMBAR=-1;
	public static double VMVERDE=-1;
	private ProgressBar barra= new ProgressBar();
	public static final String COLOR_ROJO="#fe0000";
	public static final String COLOR_AMBAR="#ffc904";
	public static final String COLOR_VERDE="#00df02";
	
	public ProgressBar getBarra() {
		return barra;
	}

	public void setBarra(ProgressBar barra) {
		this.barra = barra;
	}
	
	public CProgress(){
		super();			
		//this.tex.setText(String.format(this.label, Math.ceil(this.workDone.get())));
		
		this.setAlignment(Pos.CENTER);
		this.setPrefHeight(25);
		this.barra.setPrefHeight(24);
		this.barra.setPrefWidth(150);
		this.getChildren().add(barra);		 
	}
	
	public void modelarProgress(int reputacion){
		
		if (reputacion<=VMROJO) {
			this.barra.setStyle("-fx-accent: "+COLOR_ROJO+";");
		} else if(reputacion<=VMAMBAR){
			this.barra.setStyle("-fx-accent: "+COLOR_AMBAR+";");
		}else if (reputacion<=VMVERDE) {
			this.barra.setStyle("-fx-accent: "+COLOR_VERDE+";");
		}
		this.barra.setProgress(reputacion/VMVERDE);
	}
		
}
