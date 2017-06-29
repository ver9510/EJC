package task_02;

import java.util.Random;

public class FlyWithWIngs implements FlyBehavior{
    int duckSpeed;
    private int flightLength;

    public int getFlightLength(){
        return flightLength;
    }
    public void setFlightLength(int flightLength){
        this.flightLength=flightLength;
    }

    @Override
    public int fly() {
        flightLength=0;
        //System.out.println("I'm flying!");
        Random rnd=new Random();
        for (int i = 0; i < 10; i++) {
            duckSpeed=10+rnd.nextInt(90);
            if(rnd.nextBoolean()==true) duckSpeed>>=1;
            else duckSpeed<<=1;
            flightLength+=duckSpeed;
        }
        //System.out.println("I flight "+flightLength+" miles!");
        return flightLength;
    }
}
