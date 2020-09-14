import org.junit.Test;
import org.testng.Assert;

public class CoupangTest2 {

    @Test
    public void test(){

        int[][] p = {{2,2}};
        int result =  solution(p);
        Assert.assertEquals(result, 1);

    }

    public int solution(int[][] balloons){

        boolean[] check = new boolean[balloons.length];
        int count = 0;

        for(int i=0; i<balloons.length; i++){

            if(!check[i]) {
                for (int j = i + 1; j < balloons.length; j++) {
                    if (!check[j]) {

                        if(balloons[j][0] / balloons[i][0] > 0) {
                            if(balloons[j][1] / balloons[i][1] > 0 ) {
                                if ((balloons[j][0] % balloons[i][0]) == 0) {
                                    if ((balloons[j][1] % balloons[i][1]) == 0) {
                                        check[i] = true;
                                        check[j] = true;
                                    }
                                }
                            }
                        }


                    }
                }

                count++;
            }

        }

        return count;
    }
}
