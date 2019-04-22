package lsieun.a;

import lsieun.encoding.UTF16;
import lsieun.utils.radix.HexUtils;

public class B_CodePoint2Char {
    public static void main(String[] args) {
        int[] codePointArray = new int[] {65, 196, 9398, 23435, 127280};
        for(int codePoint : codePointArray) {
            print(codePoint);
        }
    }

    public static void print(int codePoint) {
        String ch = String.valueOf(Character.toChars(codePoint));
        System.out.println("字符: " + ch);
        System.out.println("codePoint(前): " + codePoint);

        char[] chars1 = UTF16.getChars(codePoint);
        char[] chars2 = Character.toChars(codePoint);
        System.out.println("chars(实验): " + HexUtils.fromChars(chars1));
        System.out.println("chars(参照): " + HexUtils.fromChars(chars2));

        int newCodePoint = UTF16.toCodePoint(chars2);
        System.out.println("codePoint(后): " + newCodePoint);
        System.out.println("===========================");
    }

}
