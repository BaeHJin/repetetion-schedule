import org.junit.Test;
import org.testng.Assert;

import java.util.*;

public class CoupangTest {

    @Test
    public void test(){
        String teeat = "asdfdfdfd";
        String aa = teeat.substring(1,2);

        int[][] p = {{1,3}, {3,1},{2,4}, {3,5}, {2,5}, {5,3}};
        int result =  solution(p);
        Assert.assertEquals(result, 2);

    }


    int count = 0;
    public int solution(int [][] p){

        bfs(1,  p, p[0]);
        return count;
    }

    private void bfs(int i, int[][] p, int[] pi) {

        for( ; i<p.length; i++){

            if(pi[1] == p[i][0] && pi[0] == p[i][1]) {
                count++;
            }else{
                bfs(i + 1, p, p[i]);
            }

        }

    }



}

