package com.gamefukk.capturedanimalsfighting;

public class Poke
{
    public String name;
    public String nickname;
    public String usedName;

    public Type type1;
    public Type type2;
    public float level;

    public float hp;
    public float atk;
    public float def;
    public float spd;
    public float acc;

    public float basehp;
    public float baseatk;
    public float basedef;
    public float basespd;

    public Move move;
    public Poke target;
    public boolean fainted = false;

    public Poke(String name, Type type1,Type type2,float level,float basehp,float baseatk,float basedef,float basespd)
    {
        this.name = name;
        usedName = name;
        this.type1 = type1;
        this.type2 = type2;
        this.level = level;

        this.basehp = basehp;
        this.baseatk = baseatk;
        this.basedef = basedef;
        this.basespd = basespd;

        hp = calculateHP(basehp);
        atk = calculateStat(baseatk);
        def = calculateStat(basedef);
        spd = calculateStat(basespd);
        acc = 100f;
    }

    //simplified below until base structure of game set up
    //kept as floats.... rounding added in later??
    //ignoring nature, evs and ivs of course....

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
        usedName = nickname;
    }

    public float calculateHP(float base)
    {
        return (int)(2f * base * level / 100f)+level+10f;
    }

    public float calculateStat(float base)
    {
        return (int)(2f * base * level / 100f)+5f;
    }
}
