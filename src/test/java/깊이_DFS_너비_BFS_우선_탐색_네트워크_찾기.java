import org.junit.Test;
import org.testng.Assert;

import java.util.HashSet;
import java.util.Set;

public class 깊이_DFS_너비_BFS_우선_탐색_네트워크_찾기 {

    @Test
    public void test(){

        int n = 3;
        int[][] computers = {{1,1,0}, {1,1,0}, {0,0,1}};
        int result =  solution(n, computers);
        Assert.assertEquals(result, 2);

    }

    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] chk = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(!chk[i]) {
                dfs(computers, chk, i);
                answer++;
            }
        }
        return answer;
    }
    void dfs(int[][] computers, boolean[] chk, int start) {
        chk[start] = true;
        for(int i = 0; i < computers.length; i++) {
            if(computers[start][i] == 1 && !chk[i]) {
                dfs(computers, chk, i);
            }
        }
    }


}
