package main;

import com.trolltech.qt.gui.QApplication;
import com.trolltech.qt.gui.QMainWindow;

import pages.BasitGuiQt;
import pages.Ui_MainWindow;

public class Main {
	
	/*
	 * 
	 * �ok g�zel bir �ekilde �al��makta kendi jar� i�ine zaten g�mm��ler baba
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
