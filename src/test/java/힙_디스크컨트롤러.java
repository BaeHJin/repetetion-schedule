import org.junit.Test;
import org.testng.Assert;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class 힙_디스크컨트롤러 {

    @Test
    public void test(){

        int[][] jobs = {{1,9}, {0,3}, {2,6}};

        Assert.assertEquals(solution(jobs), 2);

    }

    public int solution(int[][] jobs) {

        //요청시간순으로 정렬

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (o1, o2) -> {
                    if(o1[1] < o2[1]) return -1;
                    else if(o1[1] == o2[1]){
                        if(o1[0] < o2[0])
                            return -1;
                        else
                            return 1;
                    } else
                        return 1;
                }
        );

        for(int[] job : jobs){
            pq.add(job);
        }



        int answer = 0;
        return answer;
    }

}
