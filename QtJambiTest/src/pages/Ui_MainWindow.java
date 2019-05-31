/********************************************************************************
 ** Form generated from reading ui file 'untitledfarkli.jui'
 **
 ** Created by: Qt User Interface Compiler version 4.8.6
 **
 ** WARNING! All changes made in this file will be lost when recompiling ui file!
 ********************************************************************************/
package pages;
import com.trolltech.qt.core.*;
import com.trolltech.qt.gui.*;


public class Ui_MainWindow implements com.trolltech.qt.QUiForm<QMainWindow>
{
    public QWidget centralwidget;
    public QGridLayout gridLayout;
    public QVBoxLayout verticalLayout;
    public QCommandLinkButton commandLinkButton;
    public QHBoxLayout horizontalLayout;
    public QCommandLinkButton commandLinkButton_6;
    public QTreeView treeView;
    public QCommandLinkButton commandLinkButton_5;
    public QRadioButton radioButton;
    public QToolButton toolButton_2;
    public QCommandLinkButton commandLinkButton_2;
    public QCommandLinkButton commandLinkButton_3;
    public QCommandLinkButton commandLinkButton_4;
    public QToolButton toolButton;

    public Ui_MainWindow() { super(); }

    public void setupUi(QMainWindow MainWindow)
    {
        MainWindow.setObjectName("MainWindow");
        MainWindow.resize(new QSize(800, 600).expandedTo(MainWindow.minimumSizeHint()));
        centralwidget = new QWidget(MainWindow);
        centralwidget.setObjectName("centralwidget");
        gridLayout = new QGridLayout(centralwidget);
        gridLayout.setObjectName("gridLayout");
        verticalLayout = new QVBoxLayout();
        verticalLayout.setObjectName("verticalLayout");
        commandLinkButton = new QCommandLinkButton(centralwidget);
        commandLinkButton.setObjectName("commandLinkButton");

        verticalLayout.addWidget(commandLinkButton);

        horizontalLayout = new QHBoxLayout();
        horizontalLayout.setObjectName("horizontalLayout");
        commandLinkButton_6 = new QCommandLinkButton(centralwidget);
        commandLinkButton_6.setObjectName("commandLinkButton_6");

        horizontalLayout.addWidget(commandLinkButton_6);

        treeView = new QTreeView(centralwidget);
        treeView.setObjectName("treeView");

        horizontalLayout.addWidget(treeView);

        commandLinkButton_5 = new QCommandLinkButton(centralwidget);
        commandLinkButton_5.setObjectName("commandLinkButton_5");

        horizontalLayout.addWidget(commandLinkButton_5);

        radioButton = new QRadioButton(centralwidget);
        radioButton.setObjectName("radioButton");

        horizontalLayout.addWidget(radioButton);

        toolButton_2 = new QToolButton(centralwidget);
        toolButton_2.setObjectName("toolButton_2");

        horizontalLayout.addWidget(toolButton_2);


        verticalLayout.addLayout(horizontalLayout);

        commandLinkButton_2 = new QCommandLinkButton(centralwidget);
        commandLinkButton_2.setObjectName("commandLinkButton_2");

        verticalLayout.addWidget(commandLinkButton_2);

        commandLinkButton_3 = new QCommandLinkButton(centralwidget);
        commandLinkButton_3.setObjectName("commandLinkButton_3");

        verticalLayout.addWidget(commandLinkButton_3);

        commandLinkButton_4 = new QCommandLinkButton(centralwidget);
        commandLinkButton_4.setObjectName("commandLinkButton_4");

        verticalLayout.addWidget(commandLinkButton_4);

        toolButton = new QToolButton(centralwidget);
        toolButton.setObjectName("toolButton");

        verticalLayout.addWidget(toolButton);


        gridLayout.addLayout(verticalLayout, 0, 0, 1, 1);

        MainWindow.setCentralWidget(centralwidget);
        retranslateUi(MainWindow);

        MainWindow.connectSlotsByName();
    } // setupUi

    void retranslateUi(QMainWindow MainWindow)
    {
        MainWindow.setWindowTitle(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "MainWindow", null));
        commandLinkButton.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "CommandLinkButton", null));
        commandLinkButton_6.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "CommandLinkButton", null));
        commandLinkButton_5.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "CommandLinkButton", null));
        radioButton.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "RadioButton", null));
        toolButton_2.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "...", null));
        commandLinkButton_2.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "CommandLinkButton", null));
        commandLinkButton_3.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "CommandLinkButton", null));
        commandLinkButton_4.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "CommandLinkButton", null));
        toolButton.setText(com.trolltech.qt.core.QCoreApplication.translate("MainWindow", "...", null));
    } // retranslateUi

}

