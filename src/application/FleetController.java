package application;


import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
//import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.*;
import javafx.scene.paint.Color;
import javafx.util.Callback;
//import javafx.util.Callback;
import javafx.util.converter.BooleanStringConverter;
import javafx.util.converter.DefaultStringConverter;

import java.util.Arrays;

public class FleetController {
	FleetModule fleetModule;
	
	FleetController(FleetModule fleetModule){
		this.fleetModule = fleetModule;
	}
	@FXML 
	TextField field1;
	
	@FXML
	protected void startClientCommunication(ActionEvent event) {
		fleetModule.tcpCommunication.startClientCommunication();
	}
	
	@FXML
	protected void sendMessage(ActionEvent event) {
		fleetModule.tcpCommunication.sendMessage();
	}
	
	@FXML
	protected void startServerCommunication(ActionEvent event) {
		fleetModule.tcpCommunication.startServerCommunication();
	}
	@FXML
	protected void receiveMessage(ActionEvent event) {
		fleetModule.tcpCommunication.receiveMessage();
	}
	
	@FXML
	private TableView<Sample> tableView;
	@FXML
	private TableColumn<Sample, String> c1;
	@FXML
	private TableColumn<Sample, String> c2;
//	@FXML
//	private TableColumn<Sample, String> c3;
//	@FXML
//	private TableColumn<Sample, String> c4;
//	@FXML
//	private TableColumn<Sample, String> c5;

	@FXML
	private void initialize(){
		fleetModule.init();
//		c1.setCellValueFactory(new PropertyValueFactory<>("value1"));
//		c2.setCellValueFactory(new PropertyValueFactory<>("value2"));
//		c3.setCellValueFactory(new PropertyValueFactory<>("value3"));
//		c4.setCellValueFactory(new PropertyValueFactory<>("value4"));
//		c5.setCellValueFactory(new PropertyValueFactory<>("value5"));
//
//		tableView.getItems().addAll(
//				new Sample("Name1", "Item1"),
//				new Sample("Name2", "Item2"),
//				new Sample("Name3", "Item3"),
//				new Sample("Name4", "FreeText"));
//	
//        tableView.setEditable(true);
//        c1.setCellFactory(TextFieldTableCell.forTableColumn());
//
//        c2.setCellFactory(ChoiceBoxTableCell.forTableColumn("Item1", "Item2", "Item3"));
//        c2.setCellFactory(ComboBoxTableCell.forTableColumn("Item1", "Item2"));
//        c3.setCellFactory(CheckBoxTableCell.forTableColumn((Callback<Integer, ObservableValue<Boolean>>) c3));
//        c3.setCellFactory(TextFieldTableCell.forTableColumn(new BooleanStringConverter()));
//        c4.setCellFactory(ColorPickerTableCell.forTableColumn());
//        c4.setCellFactory(DirectColorPickerTableCell.forTableColumn());
	}
	

}
