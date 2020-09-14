import org.junit.Test;
import org.testng.Assert;

import java.util.Arrays;

public class 깊이_DFS_너비_BFS_우선_탐색_단어변환 {


    @Test
    public void test(){

        String begin = "hit";
        String target = "cog";
        String[] words = {"hot", "dot", "dog", "lot", "log"};

        solution(begin, target, words);
        Assert.assertEquals(result, 4);

    }

    int result = 0;
    public void solution(String begin, String target, String[] words) {

        if(!Arrays.stream(words).anyMatch(target::equals))
            return;

        dfs(begin, target, words, 0);
    }

    private void dfs(String begin, final String target, final String[] words, int index) {

        if(begin.equals(target)) return;
        if(index == words.length) return;

        for(int i=index; i< words.length; i++){
            if(compare(begin, words[i])) {
                index = Math.max(index, i);
            }
        }

        result++;
        dfs(words[index], target, words, index);



    }

    private boolean compare(String word, String otherWord){

        int compareCount = 0;
        for(int i=0; i<word.length(); i++){
            if(word.charAt(i) != otherWord.charAt(i))
                compareCount++;
        }

        return compareCount == 1;
    }

}
