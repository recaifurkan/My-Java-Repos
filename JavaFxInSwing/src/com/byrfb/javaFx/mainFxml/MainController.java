/**
 * bu s�n�f controller olarak kullan�l�yor
 * fxml de bulunan componentler buradan olaya kontrol edilebiliypr �ok gzel bir iekilde �al���yor
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
