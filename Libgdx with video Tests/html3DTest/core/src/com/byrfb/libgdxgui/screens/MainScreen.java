package com.byrfb.libgdxgui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.byrfb.debug.DebugPanel;
import com.kotcrab.vis.ui.VisUI;
import com.kotcrab.vis.ui.layout.HorizontalFlowGroup;
import com.kotcrab.vis.ui.widget.MenuBar;

import com.kotcrab.vis.ui.widget.VisCheckBox;
import com.kotcrab.vis.ui.widget.VisDialog;
import com.kotcrab.vis.ui.widget.VisLabel;
import com.kotcrab.vis.ui.widget.VisList;
import com.kotcrab.vis.ui.widget.VisScrollPane;
import com.kotcrab.vis.ui.widget.VisSlider;
import com.kotcrab.vis.ui.widget.VisTable;
import com.kotcrab.vis.ui.widget.VisTextArea;
import com.kotcrab.vis.ui.widget.VisTextButton;
import com.kotcrab.vis.ui.widget.VisTextField;
import com.kotcrab.vis.ui.widget.VisWindow;

public class MainScreen extends ScreenAdapter {

	DebugPanel panel;
	private Stage stage;
	VisTable root;

	@Override
	public void show() {
		super.show();
		panel = new DebugPanel();
		VisUI.load();
		stage = new Stage(new ScreenViewport());
		root = new VisTable(true);
		root.setFillParent(true);
		stage.addActor(root);
		Gdx.input.setInputProcessor(stage);

		guiDesign();
		

//		stage.addActor(new TestCollapsible());
//		stage.addActor(new TestColorPicker());

	}

	int sayi = 0;

	@Override
	public void render(float delta) {

		sayi++;
		if (sayi == 300) {
			guiDesign();
//			System.out.println("sýfýrlandý");
			sayi = 0;
		}

		super.render(delta);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
		stage.draw();
		panel.render();
	}

	private void guiDesign() {
		root.clearChildren();
		root.debug();


		VisTextButton button = new VisTextButton("Button");
		root.add(button);

		VisTextField field = new VisTextField();
		root.add(field);

		VisList<String> list = new VisList<String>();

		Array<String> listStrings = new Array<String>();
		listStrings.add("recai");
		listStrings.add("furkan");
		list.setItems(listStrings);

		root.align(Align.center);

		VisScrollPane pane = new VisScrollPane(list);
		root.add(pane);
		
		VisSlider slider = new VisSlider(0, 100, 5, false);
		root.row();
		root.add();
		root.add(slider);

		VisCheckBox checkBox = new VisCheckBox("box");
		root.row();
		checkBox.align(Align.center);
		root.row();
		root.add(checkBox).center();

		VisTable table2 = new VisTable();

		VisTextButton button2 = new VisTextButton("button2");

		table2.add(button2);

		VisTextButton button3 = new VisTextButton("button2");

		table2.add(button3);

		root.add(table2);
		
		VisWindow window = new VisWindow("Title");
		window.addCloseButton();
		window.setResizable(true);
		root.add(window);
////		stage.addActor(window);
//		
		root.row();
//		
//		VisDialog dialog = new VisDialog("Dialog");
//		dialog.setResizable(true);
//		
//		root.add(dialog);
//		
		HorizontalFlowGroup hfg = new HorizontalFlowGroup(3);
		VisTextButton button4 = new VisTextButton("button2");
		VisTextButton button5 = new VisTextButton("button2");
		VisTextButton button6 = new VisTextButton("button2");
		VisTextButton button7 = new VisTextButton("button2");
//		
		hfg.addActor(button7);
		hfg.addActor(button6);
		hfg.addActor(button5);
		hfg.addActor(button4);
//		
		root.row();
//		
		root.add(hfg).align(Align.left);

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		super.hide();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
		VisUI.dispose();
	}

}
