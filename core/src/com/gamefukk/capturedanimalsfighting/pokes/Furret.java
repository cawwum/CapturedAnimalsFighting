package com.gamefukk.capturedanimalsfighting.pokes;

import com.gamefukk.capturedanimalsfighting.Poke;
import com.gamefukk.capturedanimalsfighting.Type;
import com.gamefukk.capturedanimalsfighting.moves.Slam;

public class Furret extends Poke
{
    public Furret(int level)
    {
        super("Furret", Type.NORMAL,Type.NONE,level,85,76,64,90);
        move = new Slam();
    }
}
