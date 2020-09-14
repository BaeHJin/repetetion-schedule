import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class 완전탐색레벨2_소수찾기_모든경우의수만들기 {
    @Test
    public void test(){

        String num = "11";
        solution(num);

    }

    Set<Integer> result = new HashSet<>();

    public int solution(String numbers) {

        //모든 숫자 조함 구하기
        for(int i=0; i<numbers.length(); i++){
            if(numbers.charAt(i) != '0')
                getNumbers(String.valueOf(numbers.charAt(i)), numbers.substring(0,i)+numbers.substring(i+1, numbers.length()));
        }

        return result.size();
    }

    private void getNumbers(String prefix, String excludedNumber){

        int num = Integer.valueOf(prefix);

        //소수인지 확인
        if(isPrime(num))
            result.add(num);

        for(int i=0; i < excludedNumber.length(); i++)
        getNumbers(prefix + excludedNumber.charAt(i),
                excludedNumber.substring(0,i)+excludedNumber.substring(i+1, excludedNumber.length()));




    }


    private Boolean isPrime(int number) {

        if(number == 1 ) return false;

        for(int i = 2; i < number; i++){
            if(number % i == 0) return false;
        }

        return true;

    }

}
