package task04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        Main main=new Main();
        main.doExercise9_43();

    }

    private void doExercise9_43(){
        try(BufferedReader reader=new BufferedReader(new InputStreamReader(System.in))){
            String s1=reader.readLine();
            //Exercise 9.43
            StringBuilder builder=new StringBuilder();
            for (int i = 0; i <s1.length() ; i++) {
                if(i%2!=0){
                    builder.append(s1.charAt(i));
                }
            }
            System.out.println(builder.toString());

            /*//Exercise 9.44
            StringBuilder s2=new StringBuilder();
            for (int i = s1.length()-1; i >=0; i--) {
                s2.append(s1.charAt(i));
            }
            System.out.println(s2.toString());*/
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    //private void doExercise9_
}
