import java.util.Random;

public class Uranai {
    public static void main(String[] args) {
        // ランダム関数を呼び出し
        Random r = new Random();

        // くじのリスト
        String[] kuji = {"大吉","小吉","吉","凶","大凶","死"};

        // 配列の数を取得
        int kazu = kuji.length;

        // ランダムで配列の番号を取得。範囲は「配列の数内」
        int bangou = r.nextInt(kazu);

        // 結果
        System.out.println(kuji[bangou]);
    }
    
}
