package com.gamefukk.capturedanimalsfighting;

import java.util.HashMap;

public class TypeUtil
{
    public HashMap<Type,Integer> intType = new HashMap<Type,Integer>();
    float[] normalVs = {1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,0.5f,0f,1f,1f,0.5f,1f};
    float[] fireVs = {1f,0.5f,0.5f,1f,2f,2f,1f,1f,1f,1f,1f,2f,0.5f,1f,0.5f,1f,2f,1f};
    float[] waterVs = {1f,2f,0.5f,1f,0.5f,1f,1f,1f,2f,1f,1f,1f,2f,1f,0.5f,1f,1f,1f};
    float[] electricVs = {1f,1f,2f,0.5f,0.5f,1f,1f,1f,0f,2f,1f,1f,1f,1f,0.5f,1f,1f,1f};
    float[] grassVs = {1f,0.5f,2f,1f,0.5f,1f,1f,0.5f,2f,0.5f,1f,0.5f,2f,1f,0.5f,1f,0.5f,1f};
    float[] iceVs = {1f,0.5f,2f,1f,2f,0.5f,1f,1f,2f,2f,1f,1f,1f,1f,2f,1f,0.5f,1f};
    float[] fightingVs = {2f,1f,1f,1f,1f,2f,1f,0.5f,1f,0.5f,0.5f,0.5f,2f,0f,1f,2f,2f,0.5f};
    float[] poisonVs = {1f,1f,1f,1f,2f,1f,1f,0.5f,0.5f,1f,1f,1f,0.5f,0.5f,1f,1f,0f,2f};
    float[] groundVs = {1f,2f,1f,2f,0.5f,1f,1f,2f,1f,0f,1f,0.5f,2f,1f,1f,1f,2f,1f};
    float[] flyingVs = {1f,1f,1f,0.5f,2f,1f,2f,1f,1f,1f,1f,2f,0.5f,1f,1f,1f,0.5f,1f};
    float[] psychicVs = {1f,1f,1f,1f,1f,1f,2f,2f,1f,1f,0.5f,1f,1f,1f,1f,0f,0.5f,1f};
    float[] bugVs = {1f,0.5f,1f,1f,2f,1f,0.5f,0.5f,1f,0.5f,2f,1f,1f,0.5f,1f,2f,0.5f,0.5f};
    float[] rockVs = {1f,2f,1f,1f,1f,2f,0.5f,1f,0.5f,2f,1f,2f,1f,1f,1f,1f,0.5f,1f};
    float[] ghostVs = {0f,1f,1f,1f,1f,1f,1f,1f,1f,1f,2f,1f,1f,2f,1f,0.5f,1f,1f};
    float[] dragonVs = {1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,1f,2f,1f,0.5f,0f};
    float[] darkVs = {1f,1f,1f,1f,1f,1f,0.5f,1f,1f,1f,2f,1f,1f,2f,1f,0.5f,1f,0.5f};
    float[] steelVs = {1f,0.5f,0.5f,0.5f,1f,2f,1f,1f,1f,1f,1f,1f,2f,1f,1f,1f,0.5f,2f};
    float[] fairyVs = {1f,0.5f,1f,1f,1f,1f,2f,0.5f,1f,1f,1f,1f,1f,1f,2f,2f,0.5f,1f};
    public float[][] typeChart = {normalVs,fireVs,waterVs,electricVs,grassVs,iceVs,fightingVs,poisonVs,groundVs,flyingVs,psychicVs,bugVs,rockVs,ghostVs,dragonVs,darkVs,steelVs,fairyVs};

    public TypeUtil()
    {
        intType.put(Type.NORMAL,0);
        intType.put(Type.FIRE,1);
        intType.put(Type.WATER,2);
        intType.put(Type.ELECTRIC,3);
        intType.put(Type.GRASS,4);
        intType.put(Type.ICE,5);
        intType.put(Type.FIGHTING,6);
        intType.put(Type.POISON,7);
        intType.put(Type.GROUND,8);
        intType.put(Type.FLYING,9);
        intType.put(Type.PSYCHIC,10);
        intType.put(Type.BUG,11);
        intType.put(Type.ROCK,12);
        intType.put(Type.GHOST,13);
        intType.put(Type.DRAGON,14);
        intType.put(Type.DARK,15);
        intType.put(Type.STEEL,16);
        intType.put(Type.FAIRY,17);
    }

    public float effectiveness(Type attackType,Poke defendPoke)
    {
        float multiplier;
        Type defendType1 = defendPoke.type1;
        Type defendType2 = defendPoke.type2;

        if(defendType2 == Type.NONE)
        {
            multiplier = typeChart[intType.get(attackType)][intType.get(defendType1)];
        }
        else
        {
            multiplier = typeChart[intType.get(attackType)][intType.get(defendType1)] * typeChart[intType.get(attackType)][intType.get(defendType2)];
        }

        if(multiplier == 2f)System.out.println("It's super effective!");
        else if(multiplier == 0.5f)System.out.println("It's not very effective...");
        else if(multiplier == 0f)System.out.println("It doesn't affect "+defendPoke.name+"...");
        return multiplier;
    }
}
