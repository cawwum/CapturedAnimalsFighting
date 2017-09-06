package com.gamefukk.capturedanimalsfighting;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;

public class CapturedAnimalsFighting extends ApplicationAdapter
{
    public Battle battle;

    @Override
    public void create()
    {
        battle = new Battle();
        GameInput gameInput = new GameInput(battle);
        Gdx.input.setInputProcessor(gameInput);
    }

    @Override
    public void render()
    {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void dispose()
    {

    }
}
