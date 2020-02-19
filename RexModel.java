package com.example.saadqamer.rex2;

import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created by Saad Qamer on 2017-03-28.
 */

public class RexModel
{
    public static final int SET_SIZE = 3;
    private String rex;
    private boolean digit,letter,anchor;
    private Random rng;

    public RexModel()
    {
        this.rex = "";
        this.digit=true;
        this.letter=true;
        this.anchor=true;
        this.rng = new Random();
    }

    public void setDigit(boolean digit)
    {
        this.digit = digit;
    }
    public void setLetter(boolean letter)
    {
        this.letter = letter;
    }
    public void setAnchor(boolean anchor)
    {
        this.anchor = anchor;
    }
    public String getRex()
    {
        return this.rex;
    }

    public boolean doesMatch(String s)
    {
        return Pattern.matches(rex,s);
    }

    public void generate(int repeat)
    {
        this.rex = "";
        for(int i = 0; i < repeat; i++)
        {
            genDigit();
            genLetter();
        }
        if(this.anchor)
        {
            this.rex="^"+this.getRex()+"$";
        }

    }

    private void genDigit()
    {
        if (rng.nextDouble() < 0.5)
        {
            this.rex+="[";
            for(int k = 0; k < SET_SIZE; k++)
            {
                int x = rng.nextInt(10);
                this.rex+=x;
            }
            this.rex+="]";
        }
        else
        {
            this.rex+="[0-9]";
        }
        getQuantifier();

    }

    private void genLetter()
    {
        if (rng.nextDouble() < 0.5)
        {

            this.rex+="[";
            if(rng.nextInt(4)==2){rex +="^";}
            for(int l = 0; l < SET_SIZE; l++)
            {

                this.rex+=(char)(97+rng.nextInt(26));
            }
            this.rex+="]";
        }
        else
        {
            this.rex+="[a-z]";
        }
        getQuantifier();
    }

    private void getQuantifier()
    {
        if (rng.nextDouble() < 0.5)
        {
            this.rex+="{"+1+rng.nextInt(SET_SIZE)+"}";
        }
        else
        {
            if(rng.nextInt(SET_SIZE) == 0)
            {
                this.rex+="*";
            }
            else if (rng.nextInt(SET_SIZE) == 1)
            {
                this.rex+="+";
            }
            else
            {
                this.rex+="?";
            }

        }
    }

    public static void main(java.lang.String[] args)
    {
        RexModel model = new RexModel();
        model.setDigit(true);
        model.setLetter(true);
        model.setAnchor(true);
        model.generate(1);
        System.out.println(model.getRex());
        System.out.println(model.doesMatch("098abc"));
    }


}
