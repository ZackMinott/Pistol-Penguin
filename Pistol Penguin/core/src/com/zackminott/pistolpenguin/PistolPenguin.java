package com.zackminott.pistolpenguin;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PistolPenguin extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;

	//Runs when the app is starts
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("bg.png");
	}

	//Updates every frame
	@Override
	public void render () {
		batch.begin(); //tells render method that we can start displaing sprites now
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //Draws the background to fit the width and height of the screen

	}
	
	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
	}
}
