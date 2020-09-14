import org.junit.Test;
import org.testng.Assert;

import java.util.PriorityQueue;

public class 힙_우선순위_큐 {

    @Test
    public void test(){

        int k = 7;
        int[] words = {1, 2, 3, 9, 10, 12};

        Assert.assertEquals(solution(words, k), 2);

    }

    public int solution(int[] scoville, int K) {
        //기본으로 최소값 기준으로 먼저 나옴
        PriorityQueue<Integer> pqScov = new PriorityQueue<>();
        for (int s: scoville) {
            pqScov.add(s);
        }

        int cnt = 0;
        while (cnt< 10/*pqScov.size() > 1 && pqScov.peek() < K*/) {
            pqScov.add(pqScov.remove() + pqScov.remove() * 2);
            int min = pqScov.peek();

            //최대값으로 하려면 정렬 해야햄햄
       /*     PriorityQueue<Integer> reversedPriorityQueue =
                    new PriorityQueue<Integer>(pqScov.size(), Collections.reverseOrder());
            reversedPriorityQueue.addAll(pqScov);

            int max = reversedPriorityQueue.peek();*/


            cnt++;
        }

        return pqScov.peek() >= K ? cnt : -1;
    }

}
