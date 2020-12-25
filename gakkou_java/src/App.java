public class App {
    public static void main(String[] args) throws Exception {
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
    }
}
