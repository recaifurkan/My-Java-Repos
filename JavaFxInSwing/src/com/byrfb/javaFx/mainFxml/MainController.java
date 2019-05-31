/**
 * bu sýnýf controller olarak kullanýlýyor
 * fxml de bulunan componentler buradan olaya kontrol edilebiliypr çok gzel bir iekilde çalýþýyor
 * 
 */

package com.byrfb.javaFx.mainFxml;

import com.byrfb.lwjgl.Game;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {
	@FXML
	private Button btnClickable;

	@FXML
	void onClick(ActionEvent event) {
//    	System.out.println("click");
		Game.x = Game.x + 5;
		System.out.println("Clicked");

	}

}
