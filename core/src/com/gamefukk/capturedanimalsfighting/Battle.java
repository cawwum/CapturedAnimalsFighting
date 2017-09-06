package com.gamefukk.capturedanimalsfighting;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.gamefukk.capturedanimalsfighting.pokes.Furret;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Battle extends ApplicationAdapter
{
    public Poke playerPokeSecond;
    public Poke playerPokeFirst;
    public Poke enemyPokeSecond;
    public Poke enemyPokeFirst;
    public ArrayList<Poke> moveStack = new ArrayList<Poke>();
    public TypeUtil types = new TypeUtil();
    public Random random = new Random();
    public State state = State.NO_INPUT;

    public Battle()
    {
        playerPokeSecond = new Furret(20);
        playerPokeFirst = new Furret(15);
        playerPokeSecond.setNickname("Hero Furret");
        playerPokeFirst.setNickname("Hero's Sidekick");

        enemyPokeSecond = new Furret(20);
        enemyPokeFirst = new Furret(18);
        enemyPokeSecond.setNickname("Evil Furret");
        enemyPokeFirst.setNickname("Minion Furret");

        promptFirstMove();
    }


    public void promptFirstMove()
    {
        if(!playerPokeFirst.fainted)
        {
            System.out.println("What will " + playerPokeFirst.usedName + " do?");
            System.out.println("< for "+playerPokeFirst.moves.get(0).name);
            System.out.println("> for "+playerPokeFirst.moves.get(1).name+"\n");
            state = State.FIRST_SELECTING_MOVE;
        }
        else
        {
            promptSecondMove();
        }
    }

    public void promptFirstTarget()
    {
        System.out.println("Which target?");
        System.out.println("< for "+enemyPokeFirst.usedName);
        System.out.println("> for "+enemyPokeSecond.usedName+"\n");
        state = State.FIRST_SELECTING_TARGET;
    }

    public void promptSecondMove()
    {
        if(!playerPokeSecond.fainted)
        {
            System.out.println("What will " + playerPokeSecond.usedName + " do?");
            System.out.println("< for "+playerPokeSecond.moves.get(0).name);
            System.out.println("> for "+playerPokeSecond.moves.get(1).name+"\n");
            state = State.SECOND_SELECTING_MOVE;
        }
        else
        {
            orderMoves();
        }
    }

    public void promptSecondTarget()
    {
        System.out.println("Which target?");
        System.out.println("< for "+enemyPokeFirst.usedName);
        System.out.println("> for "+enemyPokeSecond.usedName+"\n");
        state = State.SECOND_SELECTING_TARGET;
    }

    public void selectMove(Poke poke,int moveIndex)
    {
        poke.usedMove = poke.moves.get(moveIndex);
        moveStack.add(poke);
    }

    public void selectTarget(Poke poke,Poke target,Poke backupTarget)
    {
        poke.target = target;
        poke.backupTarget = backupTarget;
    }

    public void leftReleased()
    {
        switch(state)
        {
            case FIRST_SELECTING_MOVE:
                selectMove(playerPokeFirst,0);
                promptFirstTarget();
                break;

            case FIRST_SELECTING_TARGET:
                selectTarget(playerPokeFirst,enemyPokeFirst,enemyPokeSecond);
                promptSecondMove();
                break;

            case SECOND_SELECTING_MOVE:
                selectMove(playerPokeSecond,0);
                promptSecondTarget();
                break;

            case SECOND_SELECTING_TARGET:
                selectTarget(playerPokeSecond,enemyPokeFirst,enemyPokeSecond);
                orderMoves();
                break;

            default:
                break;
        }
    }


    public void rightReleased()
    {
        switch(state)
        {
            case FIRST_SELECTING_MOVE:
                selectMove(playerPokeFirst,1);
                promptFirstTarget();
                break;

            case FIRST_SELECTING_TARGET:
                selectTarget(playerPokeFirst,enemyPokeSecond,enemyPokeFirst);
                promptSecondMove();
                break;

            case SECOND_SELECTING_MOVE:
                selectMove(playerPokeSecond,1);
                promptSecondTarget();
                break;

            case SECOND_SELECTING_TARGET:
                selectTarget(playerPokeSecond,enemyPokeSecond,enemyPokeFirst);
                orderMoves();
                break;

            default:
                break;
        }
    }


    public void orderMoves()
    {
        state = State.NO_INPUT;

        if(!enemyPokeFirst.fainted)
        {
            if(random.nextInt(2) == 1)selectMove(enemyPokeFirst,0);
            else selectMove(enemyPokeFirst,1);

            if(random.nextInt(2) == 1)selectTarget(enemyPokeFirst,playerPokeFirst,playerPokeSecond);
            else selectTarget(enemyPokeFirst,playerPokeSecond,playerPokeFirst);
        }

        if(!enemyPokeSecond.fainted)
        {
            if(random.nextInt(2) == 1)selectMove(enemyPokeSecond,0);
            else selectMove(enemyPokeSecond,1);

            if(random.nextInt(2) == 1)selectTarget(enemyPokeSecond,playerPokeFirst,playerPokeSecond);
            else selectTarget(enemyPokeSecond,playerPokeSecond,playerPokeFirst);
        }

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
    }

    public void executeTurn()
    {
        System.out.println(" - - - - - - - - - - - - - - - - - - - \n");

        for(int i = moveStack.size();i>0;i--)
        {
            Poke poke = moveStack.get(i - 1);

            if (!poke.fainted)
            {
                System.out.println(poke.usedName + " used " + poke.usedMove.name + "!");

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
        if((!playerPokeFirst.fainted || !playerPokeSecond.fainted) && (!enemyPokeFirst.fainted || !enemyPokeSecond.fainted))
        {
            System.out.println("________________________________\n");
            promptFirstMove();
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
        return (int)((((2f * user.level / 5f) * user.usedMove.power * user.atk / target.def)/50f+2f) * calculateModifier(user,target));
    }

    public float calculateModifier(Poke user,Poke target)
    {
        float typeModifier = types.effectiveness(user.usedMove.type,target);
        float randomModifier = (1f - random.nextFloat() * 0.15f);
        float stab = (user.usedMove.type == user.type1) || (user.usedMove.type == user.type2) ? 1.5f : 1f;
        return typeModifier * randomModifier * stab;
    }

    //factor in accuracy too!
    public boolean attackMissed(Poke user)
    {
        return user.usedMove.accuracy/100f * user.acc/100f < random.nextFloat();
    }
}
