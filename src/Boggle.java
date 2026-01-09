import java.util.ArrayList;
import java.util.Arrays;

public class Boggle {

    public static String[] findWords(char[][] board, String[] dictionary) {

        TST dictList = new TST();
        for (String s : dictionary) {
            dictList.insert(s, 1);
        }

        ArrayList<String> goodWords = new ArrayList<>();
        boolean[][] visited = new boolean[board.length][board[0].length];

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                dfs(board, i, j, "", visited, dictList, goodWords);
            }
        }

        // Convert the list into a sorted array of strings, then return the array.
        String[] sol = new String[goodWords.size()];
        goodWords.toArray(sol);
        Arrays.sort(sol);
        return sol;
    }

    public static void dfs(char[][] board, int i, int j, String prefix, boolean[][] visited, TST dictList, ArrayList<String> goodWords) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length)  return;
        if (visited[i][j]) return;

        visited[i][j] = true;

        prefix = prefix + board[i][j];

        if (dictList.lookup(prefix) == 1 && !goodWords.contains(prefix)) {
            goodWords.add(prefix);
        }

        dfs(board, i - 1, j, prefix, visited, dictList, goodWords);
        dfs(board, i + 1, j, prefix, visited, dictList, goodWords);
        dfs(board, i, j - 1, prefix, visited, dictList, goodWords);
        dfs(board, i, j + 1, prefix, visited, dictList, goodWords);

        visited[i][j] = false;
    }


}
