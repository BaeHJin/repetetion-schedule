import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.*;


public class 완전탐색레벨1 {

    @Test
    public void test2() {
        int k=3;
        String s = "welcometojava";
        String smallest = s.substring(0, k);
        String largest = s.substring(0, k);
        String temp = "";

        for(int i=1; i <= s.length()-k; i++){
            temp = s.substring(i,i+k);

            if(temp.compareTo(largest) > 0)
                largest = temp;

            if(temp.compareTo(smallest) < 0)
                smallest = temp;

        }

        String test =  smallest + "\n" + largest;

    }

    @Test
    public void test(){

        int[] a = {1,2,3,4,5};
        solution(a);
    }

    public int[] solution(int[] answers) {

        int[] a = {1,2,3,4,5};
        int[] b = {2,1,2,3,2,4,2,5};
        int[] c = {3,3,1,1,2,2,4,4,5,5};
        int[] score = new int[3];

        score[0] = bp(answers, a);
        score[1] = bp(answers, b);
        score[2] = bp(answers, c);

        int maxScore = Math.max(score[0], Math.max(score[1], score[2]));
        ArrayList<Integer> list = new ArrayList<>();
        if(maxScore == score[0]) {list.add(1);}
        if(maxScore == score[1]) {list.add(2);}
        if(maxScore == score[2]) {list.add(3);}

        return list.stream().mapToInt(i->i.intValue()).toArray();
    }

    private int bp(final int[] answers, final int[] user) {
        int count = 0;
        for( int i = 0; i < answers.length; i ++){

            int a = user[i%user.length];

            if(answers[i] == a)
                count++;

        }

        return count;


    }


}

