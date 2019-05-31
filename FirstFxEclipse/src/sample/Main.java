package sample;

import java.io.File;
import java.util.Enumeration;

import com.sun.xml.internal.fastinfoset.sax.Properties;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				Platform.exit();
				System.exit(0);
			}
		});
		Parent root = FXMLLoader.load(getClass().getResource("/firstpage/sample.fxml"));

		primaryStage.setTitle("Hello World");

		File file = new File("assets/index.jpg");
		System.out.println(file.exists());

		// primaryStage.getIcons().add(new Image( file.getAbsolutePath()));

		primaryStage.getIcons().add(new Image("file:assets/warning.png"));

		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

	public static void main(String[] args) {
		jvmInfo();
		launch(args);
	}

	static void jvmInfo() {
		java.util.Properties p = System.getProperties();
		Enumeration keys = p.keys();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			String value = (String) p.get(key);
			System.out.println(key + ": " + value);
		}
	}
}
