
/********************************************************************************
 ** Form generated from reading ui file 'untitled.jui'
 **
 ** Created by: Qt User Interface Compiler version 4.8.6
 **
 ** WARNING! All changes made in this file will be lost when recompiling ui file!
 ********************************************************************************/
package pages;
import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;

public class BasitGuiQt implements com.trolltech.qt.QUiForm<QMainWindow> {
    public QWidget centralwidget;
    public QWidget widget;
    public QHBoxLayout horizontalLayout;
    public QTextEdit textEdit;
    public QDateEdit dateEdit;
    public QComboBox comboBox;
    public QDoubleSpinBox doubleSpinBox;
    public QMenuBar menubar;
    public QStatusBar statusbar;

    public BasitGuiQt() {
        super();
    }

    public void setupUi(QMainWindow MainWindow) {
        MainWindow.setObjectName("MainWindow");
        MainWindow.resize(new QSize(800, 600).expandedTo(MainWindow.minimumSizeHint()));
        centralwidget = new QWidget(MainWindow);
        centralwidget.setObjectName("centralwidget");
        widget = new QWidget(centralwidget);
        widget.setObjectName("widget");
        widget.setGeometry(new QRect(90, 120, 501, 194));
        horizontalLayout = new QHBoxLayout(widget);
        horizontalLayout.setObjectName("horizontalLayout");
        textEdit = new QTextEdit(widget);
        textEdit.setObjectName("textEdit");

        horizontalLayout.addWidget(textEdit);

        dateEdit = new QDateEdit(widget);
        dateEdit.setObjectName("dateEdit");

        horizontalLayout.addWidget(dateEdit);

        comboBox = new QComboBox(widget);
        comboBox.setObjectName("comboBox");

        horizontalLayout.addWidget(comboBox);

        doubleSpinBox = new QDoubleSpinBox(widget);
        doubleSpinBox.setObjectName("doubleSpinBox");

        horizontalLayout.addWidget(doubleSpinBox);

        MainWindow.setCentralWidget(centralwidget);
        menubar = new QMenuBar(MainWindow);
        menubar.setObjectName("menubar");
        menubar.setGeometry(new QRect(0, 0, 800, 26));
        MainWindow.setMenuBar(menubar);
        statusbar = new QStatusBar(MainWindow);
        statusbar.setObjectName("statusbar");
        MainWindow.setStatusBar(statusbar);
        retranslateUi(MainWindow);
        MainWindow.connectSlotsByName();
    } // setupUi

    void retranslateUi(QMainWindow MainWindow) {
        MainWindow.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "MainWindow", null));
    } // retranslateUi

   

}
