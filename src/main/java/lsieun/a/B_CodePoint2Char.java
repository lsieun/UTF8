package lsieun.a;

import lsieun.utils.radix.HexUtils;

public class B_CodePoint2Char {
    public static void main(String[] args) {
        int[] codePointArray = new int[] {65, 196, 9398, 23435, 127280};
        for(int codePoint : codePointArray) {
            print(codePoint);
        }
    }

    public static void print(int codePoint) {
        String ch = A_String2CodePoint.codePoint2String(codePoint);
        System.out.println("字符: " + ch);
        System.out.println("codePoint(前): " + codePoint);

        char[] chars1 = codePoint2Chars(codePoint);
        char[] chars2 = Character.toChars(codePoint);
        System.out.println("chars(实验): " + toHex(chars1));
        System.out.println("chars(参照): " + toHex(chars2));

        int newCodePoint = chars2CodePoint(chars2);
        System.out.println("codePoint(后): " + codePoint);
        System.out.println("===========================");
    }

    public static char[] codePoint2Chars(int codePoint) {
        if(codePoint < 0 || codePoint > 0x10FFFF) {
            throw new IllegalArgumentException("codePoint should be in range 0~" + 0x10FFFF + ": " + codePoint);
        }

        char[] chars = null;
        if(codePoint < 0x10000) {
            char ch = (char) codePoint;

            chars = new char[1];
            chars[0] = ch;
        }
        else if(codePoint <= 0x10FFFF) {
            int diff = codePoint - 0x10000;
            char ch1 = (char) (0xD800 + (diff >>> 10 & 0x3FF));
            char ch2 = (char) (0xDC00 + (diff & 0x3FF));

            chars = new char[2];
            chars[0] = ch1;
            chars[1] = ch2;
        }
        return chars;
    }

    public static String toHex(char[] chars) {
        StringBuilder sb = new StringBuilder();
        if(chars.length == 1) {
            char ch = chars[0];
            sb.append(HexUtils.charToHex(ch));
        }
        else if(chars.length == 2) {
            char ch1 = chars[0];
            char ch2 = chars[1];
            sb.append(HexUtils.charToHex(ch1));
            sb.append(HexUtils.charToHex(ch2));
        }

        return sb.toString();
    }

    public static int chars2CodePoint(char[] chars) {
        if(chars == null || chars.length < 1) {
            throw new IllegalArgumentException("chars should not be empty!");
        }

        int codePoint = 0;
        if(chars.length == 1) {
             codePoint = chars[0];
        }
        else if(chars.length == 2) {
            int ch1 = chars[0];
            int ch2 = chars[1];
            codePoint = (ch1 & 0x3FF) << 10 | (ch2 & 0x3FF) + 0x10000;
        }

        return codePoint;
    }
}
