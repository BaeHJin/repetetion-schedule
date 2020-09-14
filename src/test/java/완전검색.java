import org.junit.jupiter.api.Test;

import static java.lang.Math.min;

public class 완전검색 {

    int result = 9;

    @Test
    public void test(){
    solution(3, 7);
    }

    public int solution(int N, int number) {
        dfs(N, 1, N, number);
        return result < 9 ? result : -1;
    }

    public void dfs(int N, int count, int num, int number) {

        if (count > 8)
            return;

        if (num == number) {
            result = min(result, count);
            return ;
        }

        int next = count+1;

        dfs(N, next, num + N,  number);
        dfs(N, next, num - N,  number);
        dfs(N, next, num * N,  number);
        dfs(N, next, num / N,  number);
        dfs(N, next, num*10+N, number);

    }


}
