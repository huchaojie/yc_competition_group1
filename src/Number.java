public class Number {
    public static void main(String[] args) {
        int array[][] = new int[3][3];
        int a=2;
        int b=1;
        for (int i=1;i<=9;i++){

            array[a++][b++]=i;
            if (i%3==0){
                a=a-2;
                b=b-1;
                System.out.println(a+"+"+b+"\t");
            }else {
                a=a%3;
                b=b%3;
            }
        }
        System.out.println("输出九宫格：");
        for (int i = 0; i < 3; i++) {
            for (int j = 0;j < 3;j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }
}
