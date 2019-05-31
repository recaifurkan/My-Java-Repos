package com.byrfb.main;


import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Main extends Application {
	/*
	 * 
	 * Main gerek yok zaten Testlerde crud yap�ld�
	 * 
	 */
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			@Override
			public void handle(WindowEvent t) {
				Platform.exit();
				System.exit(0);
			}
		});

		// e�er �st klas�rdekine gideceksen maalesef roota girmen gerekiyo
		Parent root = FXMLLoader.load(this.getClass().getResource("/com/byrfb/gui/Page.fxml"));

		primaryStage.setTitle("Hello World");

		primaryStage.setScene(new Scene(root));
		primaryStage.show();

	}

}
