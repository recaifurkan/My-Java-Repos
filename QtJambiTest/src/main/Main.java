package main;

import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QMainWindow;

import pages.BasitGuiQt;
import pages.Ui_MainWindow;

public class Main {
	
	/*
	 * 
	 * çok güzel bir þekilde çalýþmakta kendi jarý içine zaten gömmüþler baba
	 */

	 public static void main(String[] args) {
	        

	        QApplication.initialize(args);

	        QMainWindow mainWindow = new QMainWindow();
	        Ui_MainWindow mainUIWindow = new Ui_MainWindow();
	        
//	        BasitGuiQt mainUIWindow = new BasitGuiQt();
	        mainUIWindow.setupUi(mainWindow);
	        mainWindow.show();

	        QApplication.execStatic();
	    }

}
