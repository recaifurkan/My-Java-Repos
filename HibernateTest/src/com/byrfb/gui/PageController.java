package com.byrfb.gui;

import java.net.URL;

import java.util.ResourceBundle;

import com.byrfb.dao.TelefonDao;
import com.byrfb.entities.Telefon;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;

import javafx.scene.control.TableView;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class PageController implements Initializable {

	@FXML
	private TextField txtId;

	@FXML
	private TextField txtMarka;

	@FXML
	private TextField txtModel;

	@FXML
	private Button btnEkle;

	@FXML
	private Button btnDuzenle;

	@FXML
	private Button btnSil;

	@FXML
	private TableView<Telefon> tableTelefons;

	@FXML
	private TableColumn<?, ?> clmSira;

	@FXML
	private TableColumn<?, ?> clmId;

	@FXML
	private TableColumn<?, ?> clmMarka;

	@FXML
	private TableColumn<?, ?> clmModel;

	@FXML
	void telefonDuzenle(ActionEvent event) {
		Telefon telefon = dao.getTelefon(Integer.parseInt(txtId.getText()));

		telefon.setMarka(txtMarka.getText());
		telefon.setModel(txtModel.getText());
		dao.updateTelefon(telefon);
		getList();

	}

	@FXML
	void telefonEkle(ActionEvent event) {
		Telefon telefon = new Telefon();
		telefon.setMarka(txtMarka.getText());
		telefon.setModel(txtModel.getText());
		dao.addTelefon(telefon);
		getList();

	}

	@FXML
	void telefonSil(ActionEvent event) {
		Telefon telefon = new Telefon();
		telefon.setId(Integer.parseInt(txtId.getText()));
		dao.deleteTelefon(telefon);
		getList();

	}

	TelefonDao dao;

	private ObservableList<Telefon> telefonData;

	void getList() {
		telefonData = FXCollections.observableArrayList();
		dao = new TelefonDao();
		for (Telefon telefon : dao.getTelefons()) {
			telefonData.add(telefon);

		}

		tableTelefons.setItems(telefonData);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		getList();
		clmId.setCellValueFactory(new PropertyValueFactory<>("id"));
		clmMarka.setCellValueFactory(new PropertyValueFactory<>("marka"));
		clmModel.setCellValueFactory(new PropertyValueFactory<>("model"));

		tableTelefons.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
			@Override
			public void changed(ObservableValue observableValue, Object oldValue, Object newValue) {
				// Check whether item is selected and set value of selected item to Label
				if (tableTelefons.getSelectionModel().getSelectedItem() != null) {
					Telefon telefon = tableTelefons.getSelectionModel().getSelectedItem();
					System.out.println(telefon);
					txtId.setText(String.valueOf(telefon.getId()));
					txtMarka.setText(telefon.getMarka());
					txtModel.setText(telefon.getModel());
				}
			}
		});

	}

}
