package gui.mainviewcomponent;

import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;

import java.util.ResourceBundle;


import console.MusicPlayer;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import javazoom.jl.decoder.JavaLayerException;
import selenium.BrowserThread;

public class MainPageController implements Initializable {

	@FXML
	private TextField txtKalkis;

	@FXML
	private TextField txtKisi;

	@FXML
	private TextField txtVaris;

	@FXML
	private DatePicker dateTarih;

	@FXML
	private Button btnCalistir;

	@FXML
	private Button btnPlay;

	BooleanProperty isPlayProperty = new SimpleBooleanProperty(false);

	public BooleanProperty getIsPlayProperty() {
		return isPlayProperty;
	}

	MusicPlayer music;

	@FXML
	void sustur(ActionEvent event) throws JavaLayerException, FileNotFoundException, InterruptedException {
		if (!isPlayProperty.getValue()) {

			isPlayProperty.set(true);

		} else {

			isPlayProperty.set(false);
		}

	}

	@FXML
	void calistir(ActionEvent event) throws InterruptedException, FileNotFoundException, JavaLayerException {

		String kalkisYeri = txtKalkis.getText();
		String varisYeri = txtVaris.getText();
		int kisiSayisi = Integer.parseInt(txtKisi.getText());
		String yolculukTarihi = getDate(dateTarih).toString();

		Thread th = new Thread(new BrowserThread(kalkisYeri, varisYeri, kisiSayisi, yolculukTarihi, this));

		th.start();

	}

	private StringBuilder getDate(DatePicker picker) {
		LocalDate tarih = picker.getValue();
		StringBuilder tarihString = new StringBuilder();
		if (tarih.getDayOfMonth() < 10) {
			tarihString.append("0" + tarih.getDayOfMonth() + ".");
		} else {
			tarihString.append(tarih.getDayOfMonth() + ".");

		}

		if (tarih.getMonthValue() < 10) {
			tarihString.append("0" + tarih.getMonthValue() + ".");
		} else {
			tarihString.append(tarih.getMonthValue() + ".");

		}
		tarihString.append(tarih.getYear());
		return tarihString;

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		txtKalkis.setText("Ankara Gar");
		txtVaris.setText("Kars");
		txtKisi.setText("4");
		music = new MusicPlayer();
		dateTarih.setValue(LocalDate.now().plusDays(1));

		isPlayProperty.addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// Only if completed
				if (newValue) {
					try {
						music.play();
					} catch (FileNotFoundException | JavaLayerException | InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					btnPlay.setText("Sustur");

				} else {
					music.getPlayMP3().close();
					btnPlay.setText("Çaldýr");

				}
			}

		});

	}

}
