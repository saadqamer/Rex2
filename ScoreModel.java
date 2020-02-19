package com.example.saadqamer.rex2;

/**
 * Created by Saad Qamer on 2017-03-28.
 */

public class ScoreModel
{

    private int attempts,success;
    private long start,elapsed;

    public ScoreModel()
    {
        this.attempts = 0;
        this.success = 0;
        start=System.currentTimeMillis();
    }

    public int getAttempts()
    {
        return this.attempts;
    }

    public int getSuccess()
    {
        return this.success;
    }

    public long getStart()
    {
        return this.start;
    }

    public long getElapsedTime()
    {
        this.elapsed = (System.currentTimeMillis()-start)/1000;
        return this.elapsed;
    }

    public void record(boolean success)
    {
        if(success)
        {
            this.success++;
        }
            this.attempts++;
    }

    public double getAverageScore()
    {
        double avg;
        avg = (this.getSuccess()/this.getAttempts())*100.00;
        return avg;
    }

    public void resetTimer()
    {
        this.start = System.currentTimeMillis();
    }

    public static void main(String[] args) {
        ScoreModel model = new ScoreModel();
        model.record(true);
        System.out.println(model.getAttempts());
        System.out.println(model.getSuccess());
        System.out.println(model.getAverageScore());
    }


}
