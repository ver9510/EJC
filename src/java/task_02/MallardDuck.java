package task_02;

import java.util.Random;

public class MallardDuck extends Duck {

    public MallardDuck(){
        quackBehavior=new Quack();
        flyBehavior=new FlyWithWIngs();
    }

    public void display(){
        System.out.println("I'm a real Mallard Duck");
    }
}
