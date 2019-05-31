package firstpage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class Controller implements Initializable {

	@FXML
	private Label lblText;

	@FXML
	private Label lblBaglandi;
	private StringProperty labelText;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblText.setText("recai");
		labelText = new SimpleStringProperty();
		lblText.textProperty().bind(labelText);
		Server server = new Server();
		server.start();
		try {
			server.bind(54555, 54777);
			Platform.runLater(() -> {
				lblBaglandi.setText("Server A��k");
			});

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Platform.runLater(() -> {
				lblBaglandi.setText("Server Kapal�");
			});
		}

		server.addListener(new Listener() {
			public void connected(Connection connection) {

			}

			public void received(Connection connection, Object object) {
				if (object instanceof String) {
					Platform.runLater(() -> {
						labelText.set(object.toString() + "\n");

					});

				}

				// System.out.println(object);

			}
		});

	}
}
