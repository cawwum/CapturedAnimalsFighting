package com.gamefukk.capturedanimalsfighting;

public class Move
{
    public String name;
    public Type type;
    public float power;
    public float accuracy;

    public Move(String name,Type type,float power,float accuracy)
    {
        this.name = name;
        this.type = type;
        this.power = power;
        this.accuracy = accuracy;
    }
}
