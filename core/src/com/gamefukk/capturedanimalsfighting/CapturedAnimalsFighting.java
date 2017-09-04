package com.gamefukk.capturedanimalsfighting;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class CapturedAnimalsFighting extends ApplicationAdapter
{
    @Override
    public void create()
    {
        new Battle();
    }

    @Override
    public void render()
    {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void dispose()
    {

    }
}
