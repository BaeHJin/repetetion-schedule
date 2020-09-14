import org.junit.Test;
import org.testng.Assert;

import java.util.Collections;
import java.util.PriorityQueue;

public class 힙_이중우선순위큐 {

    @Test
    public void test(){

        String[] jobs = {"I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"};
        int[] result = {0,0};

        Assert.assertEquals(solution(jobs), result);

    }

    public int[] solution(String[] operations) {

        PriorityQueue<Integer> pqAsc = new PriorityQueue<>();
        PriorityQueue<Integer> pqDesc = new PriorityQueue<>(Collections.reverseOrder());

        for(String operation : operations){
            String[] o = operation.split(" ");

            if(o[0].equals("I")) {

                pqAsc.add(Integer.valueOf(o[1]));
                pqDesc.add(Integer.valueOf(o[1]));

            } else if(o[0].equals("D")){

                if(o[1].equals("-1")) {
                    if(!pqAsc.isEmpty()) {
                        pqDesc.remove(pqAsc.remove());
                    }

                } else {
                    if(!pqDesc.isEmpty())
                        pqAsc.remove(pqDesc.remove());
                }

            }
        }
        int[] answer = new int[2];

        if( pqDesc.isEmpty() || pqAsc.isEmpty()){
            answer[0] = 0;
            answer[1] = 0;
            return answer;
        }

        if(pqDesc.peek() < pqAsc.peek()){
            answer[0] = 0;
            answer[1] = 0;
        } else {
            answer[0] = pqDesc.peek();
            answer[1] = pqAsc.peek();
        }


        return answer;
    }

}

