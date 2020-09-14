import org.junit.jupiter.api.Test;

public class 완전검색_back_tracking {

    int N;
    int count;
    boolean[][] board;

    @Test
    public void sol(){
        solution(4);
    }

    public int solution(int n) {

        N=n;
        board = new boolean[n][n];
        count = 0;

        for(int i=0; i<n; i++){
            dfs(0,i);
        }

        return count;
    }

    public void dfs(int row, int col) {
        board[row][col] = true;

        if(row == N - 1) count++;

        for(int i = 0; i < N; i++) {
            if(check(row + 1, i)){
                dfs(row + 1, i);
            }
        }

        board[row][col] = false;
    }

    public boolean check(int row, int col) {

        for(int i = 0; i < row; i++) {
            if(board[i][col]) return false;
        }

        for(int i = 1; i < N; i++) {
            if(row - i >= 0 && col - i >= 0 && board[row - i][col - i]) return false;
            if(row - i >= 0 && col + i < N && board[row - i][col + i]) return false;
        }

        return true;
    }

}
