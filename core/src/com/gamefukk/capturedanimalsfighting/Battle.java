package com.gamefukk.capturedanimalsfighting;

import com.badlogic.gdx.Gdx;
import com.gamefukk.capturedanimalsfighting.pokes.Furret;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Battle
{
    public Poke playerPokeRight;
    public Poke playerPokeLeft;
    public Poke enemyPokeRight;
    public Poke enemyPokeLeft;
    public ArrayList<Poke> moveStack = new ArrayList<Poke>();
    public TypeUtil types = new TypeUtil();
    public Random random = new Random();

    public Battle()
    {
        playerPokeRight = new Furret(20);
        playerPokeLeft = new Furret(15);
        playerPokeRight.setNickname("Hero Furret");
        playerPokeLeft.setNickname("Hero's Sidekick");

        enemyPokeRight = new Furret(20);
        enemyPokeLeft = new Furret(18);
        enemyPokeRight.setNickname("Evil Furret");
        enemyPokeLeft.setNickname("Minion Furret");

        stackMoves();

    }

    public void stackMoves()
    {
        moveStack.clear();

        if(random.nextInt(2) == 1)
        {
            playerPokeLeft.target = enemyPokeLeft;
            playerPokeLeft.backupTarget = enemyPokeRight;
        }
        else
        {
            playerPokeLeft.target = enemyPokeRight;
            playerPokeLeft.backupTarget = enemyPokeLeft;
        }





        if(random.nextInt(2) == 1)
        {
            playerPokeRight.target = enemyPokeLeft;
            playerPokeRight.backupTarget = enemyPokeRight;
        }
        else
        {
            playerPokeRight.target = enemyPokeRight;
            playerPokeRight.backupTarget = enemyPokeLeft;
        }





        if(random.nextInt(2) == 1)
        {
            enemyPokeLeft.target = playerPokeLeft;
            enemyPokeLeft.backupTarget = playerPokeRight;
        }
        else
        {
            enemyPokeLeft.target = playerPokeRight;
            enemyPokeLeft.backupTarget = playerPokeLeft;
        }





        if(random.nextInt(2) == 1)
        {
            enemyPokeRight.target = playerPokeRight;
            enemyPokeRight.backupTarget = playerPokeLeft;
        }
        else
        {
            enemyPokeRight.target = playerPokeLeft;
            enemyPokeRight.backupTarget = playerPokeRight;
        }



        moveStack.add(playerPokeLeft);
        moveStack.add(playerPokeRight);
        moveStack.add(enemyPokeLeft);
        moveStack.add(enemyPokeRight);

        for(int i=0;i<moveStack.size();i++)
        {
            for(int j=1;j<moveStack.size()-i;j++)
            {
                if(moveStack.get(j-1).spd > moveStack.get(j).spd)
                {
                    Collections.swap(moveStack,j,j-1);
                }
                else if(moveStack.get(j-1).spd == moveStack.get(j).spd)
                {
                    if(random.nextInt(2)==1)Collections.swap(moveStack,j,j-1);
                }
            }
        }

        executeTurn();


        /*
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
        }*/
    }

    public void executeTurn()
    {
        for(int i = moveStack.size();i>0;i--)
        {
            Poke poke = moveStack.get(i - 1);

            if (!poke.fainted)
            {
                System.out.println(poke.usedName + " used " + poke.move.name + "!");

                if (poke.target.fainted)
                {
                    if (poke.backupTarget.fainted)
                    {
                        System.out.println("But there was no target....\n");
                        moveStack.remove(i - 1);
                        break;
                    }
                    else
                    {
                        poke.target = poke.backupTarget;
                    }
                }

                if (attackMissed(poke))
                {
                    System.out.println(poke.usedName + "'s attack missed!\n");
                }
                else
                {

                    int damage = calculateDamage(poke, poke.target);
                    int hpBefore = poke.target.hp;
                    poke.target.hp -= damage;
                    System.out.println(poke.target.usedName + "'s HP: " + hpBefore + " -> " + poke.target.hp+"\n");

                    if (poke.target.hp <= 0)
                    {
                        System.out.println(poke.target.usedName + " fainted!\n");
                        poke.target.fainted = true;
                    }
                }
            }

            moveStack.remove(i - 1);
        }

        endTurn();
    }



    public void endTurn()
    {
        if((!playerPokeLeft.fainted || !playerPokeRight.fainted) && (!enemyPokeLeft.fainted || !enemyPokeRight.fainted))
        {
            System.out.println("________________________________\n");
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
