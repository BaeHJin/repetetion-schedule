import org.junit.Test;
import org.testng.Assert;

public class 완전탐색레벨2_넓이_높이_구하기 {

    @Test
    public void test(){

        int brown = 24;
        int yellow = 24;
        int[] expected = {8,6};

       int[] result =  solution(brown, yellow);
        Assert.assertEquals(result, expected);

    }

    int x = 0;
    int y = 0;

    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        // yellow가 제곱근인지 확인
        double doubleSqrt = Math.sqrt(yellow);
        int sqrt = (int) doubleSqrt;

        if(doubleSqrt == sqrt){
            x = sqrt;
            y = sqrt;

            // 갯수비교
            if (getXY(brown, answer)) return answer;
        }

        for(int i = 1; i <= yellow/2 ; i++){

            if( yellow%i != 0) continue;
            x = yellow / i;
            y = i;
            if (getXY(brown, answer)) return answer;

        }

        return answer;
    }

    private boolean getXY(final int brown, final int[] answer) {
        if ((2 * (x + 1) + 2 * (y + 1)) == brown) {
            answer[0] = x + 2;
            answer[1] = y + 2;
            return true;
        }
        return false;
    }


}
