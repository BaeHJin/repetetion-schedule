public class 배열2차 {

    public boolean solution(int[][] key, int[][] lock) {

        int[][] board = copyBoard(lock);

        dfs(key, board, 0);

        boolean answer = true;
        return answer;
    }

    public void dfs(int[][] key, int[][] board, int count) {
//        check()
    }


    public int[][] copyBoard(int[][] lock){
        int len = lock.length;
        int[][] board =  new int[len*3][len*3];
        for(int i=0; i<len; i++) {
            for(int j=0; j<len; j++) {
                board[i+len][j+len] = lock[i][j];
            }
        }

        return board;
    }

}
