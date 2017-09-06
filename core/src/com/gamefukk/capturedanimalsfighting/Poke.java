package com.gamefukk.capturedanimalsfighting;

import java.util.ArrayList;

public class Poke
{
    public String name;
    public String nickname;
    public String usedName;

    public Type type1;
    public Type type2;
    public int level;

    public int hp;
    public int atk;
    public int def;
    public int spd;
    public int acc;

    public int basehp;
    public int baseatk;
    public int basedef;
    public int basespd;

    public Move move;
    public ArrayList<Move> moves = new ArrayList<Move>();
    public Move usedMove;
    public Poke target;
    public Poke backupTarget;

    public ArrayList<Poke> targets = new ArrayList<Poke>();
    public boolean fainted = false;

    public Poke(String name, Type type1,Type type2,int level,int basehp,int baseatk,int basedef,int basespd)
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
        acc = 100;
    }

    //simplified below until base structure of game set up
    //kept as floats.... rounding added in later??
    //ignoring nature, evs and ivs of course....

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
        usedName = nickname;
    }

    public int calculateHP(int base)
    {
        return (int)(2f * base * level / 100f)+level+10;
    }

    public int calculateStat(int base)
    {
        return (int)(2f * base * level / 100f)+5;
    }
}
