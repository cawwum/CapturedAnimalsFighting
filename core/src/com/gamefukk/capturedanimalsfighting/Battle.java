package com.gamefukk.capturedanimalsfighting;

import com.badlogic.gdx.Gdx;
import com.gamefukk.capturedanimalsfighting.pokes.Furret;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Battle
{
    public Poke playerPoke;
    public Poke enemyPoke;
    public ArrayList<Poke> moveStack = new ArrayList<Poke>();
    public TypeUtil types = new TypeUtil();
    public Random random = new Random();

    public Battle()
    {
        playerPoke = new Furret(20);
        playerPoke.setNickname("Hero Furret");
        enemyPoke = new Furret(20);
        enemyPoke.setNickname("Evil Furret");
        stackMoves();
    }

    public void stackMoves()
    {
        playerPoke.target = enemyPoke;
        enemyPoke.target = playerPoke;
        moveStack.add(playerPoke);
        moveStack.add(enemyPoke);

        //slowest to fastest.... ?

        //during bubblesort do a check for same speed and if they are same do a swap 50% of the time

        if(enemyPoke.spd > playerPoke.spd)
        {
            nextMove();
        }
        else if(enemyPoke.spd == playerPoke.spd)
        {
            System.out.println("SPEED TIE!");
            if(random.nextInt(2) == 1)Collections.swap(moveStack,0,1);
            nextMove();
        }
        else
        {
            Collections.swap(moveStack,0,1);
            nextMove();
        }
    }

    public void nextMove()
    {
        if(moveStack.size() == 0)
        {
            endTurn();
        }
        else
        {
            Poke poke = moveStack.get(moveStack.size() - 1);

            if (!poke.fainted)
            {
                System.out.println(poke.usedName + " used " + poke.move.name + "!");

                if (attackMissed(poke))
                {
                    System.out.println(poke.usedName + "'s attack missed!");
                }
                else
                {

                    int damage = calculateDamage(poke, poke.target);
                    int hpBefore = poke.target.hp;
                    poke.target.hp -= damage;
                    System.out.println(poke.target.usedName + "'s HP: " + hpBefore + " -> " + poke.target.hp);

                    if (poke.target.hp <= 0)
                    {
                        System.out.println(poke.target.usedName + " fainted!");
                        poke.target.fainted = true;
                    }
                }
            }

            moveStack.remove(moveStack.size() - 1);
            nextMove();
        }
    }

    public void endTurn()
    {
        if(!playerPoke.fainted && !enemyPoke.fainted)
        {
            stackMoves();
        }
        else
        {
            endBattle();
        }
    }

    public void endBattle()
    {
        Gdx.app.exit();
    }


    public int calculateDamage(Poke user,Poke target)
    {
        return (int)((((2f * user.level / 5f) * user.move.power * user.atk / target.def)/50f+2f) * calculateModifier(user,target));
    }

    public float calculateModifier(Poke user,Poke target)
    {
        float typeModifier = types.effectiveness(user.move.type,target);
        float randomModifier = (1f - random.nextFloat() * 0.15f);
        float stab = (user.move.type == user.type1) || (user.move.type == user.type2) ? 1.5f : 1f;
        return typeModifier * randomModifier * stab;
    }

    //factor in accuracy too!
    public boolean attackMissed(Poke user)
    {
        return user.move.accuracy/100f * user.acc/100f < random.nextFloat();
    }
}
