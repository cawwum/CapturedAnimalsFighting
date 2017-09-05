package com.gamefukk.capturedanimalsfighting;

public class Move
{
    public String name;
    public Type type;
    public int power;
    public int accuracy;

    public Move(String name,Type type,int power,int accuracy)
    {
        this.name = name;
        this.type = type;
        this.power = power;
        this.accuracy = accuracy;
    }
}
