import org.junit.jupiter.api.Test;

public class 진법변환 {

    @Test
    public void sol(){
        solution(2,4,2,1);
    }

    public String solution(int n, int t, int m, int p) {

        String answer = "";
        String longward = "0";
        int number = 0;

        while( (longward.length()/p) < t) {

            longward += toDeposition(number, n);
            number++;
        }

        for(int i = 0; i < t; i ++) {
         answer += longward.charAt( (p-1) * (i+1) );
        }

        return answer;
    }



    public static String toDeposition(int value, int n){
        String returnString = "";

        while( value != 0 ){

            if( (value % n) < 10 ){

                returnString = (value % n) + returnString;
                value = value / n;

            } else {

                int t = (char)((value % n) + 55);
                returnString = t + returnString;

            }
        }

        return returnString;

    }
}
