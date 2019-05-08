package com.zackminott.pistolpenguin;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.Random;

public class PistolPenguin extends ApplicationAdapter {
	SpriteBatch batch;
	Texture background;

	Texture[] birds;
	int flapState = 0;
	float birdY = 0;
	float velocity = 0;

	int gameState = 0;
	float gravity = 1.5f;

	Texture topTube;
	Texture bottomTube;
	float gap = 400;
	float maxTubeOffset;
	Random randomGenerator;
	float tubeVelocity = 4; //Used for moving the tubes to the left
	int numberOfTubes = 4;
	float tubeX[] = new float[numberOfTubes]; //We need to keep track of the tubes x coordinate because the tubes position keeps moving left
	float[] tubeOffset = new float[numberOfTubes];
	float distanceBetweenTubes;

	//Runs when the app is starts
	@Override
	public void create () {
		batch = new SpriteBatch();
		background = new Texture("bg.png");

		birds = new Texture[2];
		birds[0] = new Texture("bird.png");
		birds[1] = new Texture("bird2.png");
		birdY = Gdx.graphics.getHeight()/2 - birds[0].getHeight()/2;

		topTube = new Texture("toptube.png");
		bottomTube = new Texture("bottomtube.png");
		maxTubeOffset = Gdx.graphics.getHeight()/2 - gap/2 - 100;
		randomGenerator = new Random();
		distanceBetweenTubes = Gdx.graphics.getWidth() * 3 / 4;

		for(int i = 0; i < numberOfTubes; i++){
			tubeOffset[i] = (randomGenerator.nextFloat() - 0.5f) * (Gdx.graphics.getHeight() - gap - 200); //creates float from random generator (this variable will shift the tubes up or down)
			tubeX[i] = Gdx.graphics.getWidth()/2 - topTube.getWidth()/2 + i * distanceBetweenTubes; //reset the tubes position every tap
		}
	}

	//Updates every frame
	@Override
	public void render () {
		batch.begin(); //tells render method that we can start displaing sprites now
		batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()); //Draws the background to fit the width and height of the screen

		if(gameState != 0){
			if(Gdx.input.justTouched()){
				velocity = -27;

			}
			for(int i = 0; i < numberOfTubes; i++) {
				if(tubeX[i] < - topTube.getWidth()){
					tubeX[i] += numberOfTubes * distanceBetweenTubes;
				} else {
					tubeX[i] = tubeX[i] - tubeVelocity; //Moves the tubes x position to the left every frame
				}

				batch.draw(topTube, tubeX[i], Gdx.graphics.getHeight() / 2 + gap / 2 + tubeOffset[i]);
				batch.draw(bottomTube, tubeX[i], Gdx.graphics.getHeight() / 2 - gap / 2 - bottomTube.getHeight() + tubeOffset[i]);
			}

			//stops the bird at the bottom of the screen
			if(birdY > 0 || velocity < 0){
				velocity += gravity;
				birdY -= velocity;
			}

		} else {
			if(Gdx.input.justTouched()){
				gameState = 1;
			}
		}

		if(flapState == 0)
			flapState = 1;
		else
			flapState = 0;


		batch.draw(birds[flapState], Gdx.graphics.getWidth()/2 - birds[flapState].getWidth()/2, birdY);
		batch.end();



	}
	
	@Override
	public void dispose () {
		batch.dispose();
		background.dispose();
	}
}
