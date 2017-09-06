package com.gamefukk.capturedanimalsfighting;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class GameInput implements InputProcessor
{
    public Battle battle;
    public GameInput(Battle battle)
    {
        this.battle = battle;
    }
    @Override
    public boolean keyDown(int keycode)
    {
        return false;
    }

    @Override
    public boolean keyUp(int keycode)
    {
        switch(keycode)
        {
            case Input.Keys.LEFT:
                battle.leftReleased();
                break;

            case Input.Keys.RIGHT:
                battle.rightReleased();
                break;
        }

        return true;
    }

    @Override
    public boolean keyTyped(char character)
    {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY)
    {
        return false;
    }

    @Override
    public boolean scrolled(int amount)
    {
        return false;
    }
}
