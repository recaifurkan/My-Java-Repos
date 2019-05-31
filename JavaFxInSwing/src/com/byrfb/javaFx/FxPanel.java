/*
 * 
 * bu s�n�f swing i�inde java fx eklemlerini kullanabilmemize yar�yor
 * buradya art�k istedi�in java fx eleman�� ekleyebilirisns
 * bir nevi java fx teki start metodu gibi d���nebilrsin �ok da g�zel �al��makta babba
 */

package com.byrfb.javaFx;

import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;

public class FxPanel extends JFXPanel {

	public FxPanel() {

	}

	public void init() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("mainFxml/Main.fxml"));

		Parent parent = null;
		try {
			parent = (Parent) loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

//		Group root = new Group();
//		Rectangle rect = new Rectangle(0, 0, this.getWidth(), this.getHeight());
//		rect.setFill(Color.RED);
//		root.getChildren().add(rect);
		Scene scene = new Scene(parent,this.getWidth(),this.getHeight());
//		scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
//
//			@Override
//			public void handle(MouseEvent event) {
//				Game.x += 10;
//
//			}
//		});

//		System.out.println("Scene setting");

		this.setScene(scene);

	}

}
