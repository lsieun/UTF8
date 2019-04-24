package lsieun.b;

public class A_CodePoint {
    public static void main(String[] args) {
        int[] codePoints = {0x41, 0x61, 0x2162, 0xFEFF, 0x103C3};
        for(int codePoint : codePoints) {
            String name = Character.getName(codePoint);
            //1$或2$，表示使用第1个或第2个参数，分别对应codePoint和name变量
            //%X表示输出十六进制格式的数值，%6X表示输出的十六进制占6个位置
            //%c表示输出一个Unicode字符
            //%s表示输出一个字符串
            String line = String.format("%1$6X(%1$c): %2$s", codePoint, name);
            System.out.println(line);
        }
    }
}
