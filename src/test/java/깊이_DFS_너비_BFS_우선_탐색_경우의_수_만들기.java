import org.junit.Test;
import org.testng.Assert;


public class 깊이_DFS_너비_BFS_우선_탐색_경우의_수_만들기 {

    @Test
    public void test(){

        int[] numbers = {1,1,1,1,1};
        int target = 3;

        int result =  solution(numbers, target);
        Assert.assertEquals(result, 5);

    }

    public int solution(int[] numbers, int target) {

        return cul(0, 0, numbers, target);

    }

    public int cul(int sum, int index, int[] numbers, int target){

        if(index == numbers.length) {
            if(sum == target) return 1;
            return 0;
        }

        return cul(sum+numbers[index], index+1, numbers, target) +
        cul(sum-numbers[index], index+1, numbers, target);

    }

}
