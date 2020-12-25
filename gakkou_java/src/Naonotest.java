public class Naonotest {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        System.out.println("これはtestで動かないのでそのための一時的避難です。");

        int num = 0;
        if (num == 0) {
            System.out.println("numは0です");
        } else {
            System.out.println("numは0ではない");
        }

        int num2 = 10;
        int num3 = 30;
        if (num2 < 5 || num3 > 20 ) {
            System.out.println("真");
        } else {
            System.out.println("偽");
        }

        int year;
        // うるう年の場合
        year = 2016;
        if (year % 4 == 0) {
            System.out.println(year + "年は、うるう年です！");
        }
        else {
            System.out.println(year + "年は、うるう年ではありません！");
        }


        // うるう年でない場合
        year = 2017;
        if (year % 4 == 0) {
            System.out.println(year + "年は、うるう年です！");
        }
        else {
            System.out.println(year + "年は、うるう年ではありません！");
        }


        int age;
        // スケートの年齢制限は15歳以上
        int limitLow = 15;
        // サッカーの年齢制限は23歳以下
        int limitHigh = 23;
        // 14歳の場合
        age = 14;
        // 結果表示の処理
        if (age < limitLow) {
            System.out.println(age + "歳でスケートに出場できません");
        }
        // 15歳の場合
        age = 15;
        // 結果表示の処理
        if (age >= limitLow) {
            System.out.println(age + "歳でスケートに出場できます");
        }
        // 23歳の場合
        age = 23;
        // 結果表示の処理
        if (age <= limitHigh) {
            System.out.println(age + "歳はサッカーに出場できます");
        }
        // 24歳の場合
        age = 24;
        // 結果表示の処理
        if (age > limitHigh) {
            System.out.println(age + "歳でサッカーに出場できません");
        }
    }
}
