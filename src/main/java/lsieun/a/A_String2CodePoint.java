package lsieun.a;

import lsieun.utils.radix.HexUtils;

public class A_String2CodePoint {
    public static void main(String[] args) {
        String[] strArray = new String[] {"A", "Ä", "Ⓐ", "宋", "\uD83C\uDD30"};
        for(String ch : strArray) {
            print(ch);
        }
    }

    public static void print(String ch) {
        //转换之前的字符
        System.out.println("字符（前）: " + ch);

        int codePoint = string2CodePoint(ch);
        System.out.println("codePoint: " + codePoint);
        String hexCode = HexUtils.fromInt(codePoint).toUpperCase();
        System.out.println("Hex Code:" + hexCode);

        //转换之后的字符
        String value = codePoint2String(codePoint);
        System.out.println("字符（后）: " + value);
        System.out.println("====================");
    }

    public static int string2CodePoint(String ch) {
        int codePoint = ch.codePointAt(0);
        return codePoint;
    }

    public static String codePoint2String(int codePoint) {
        char[] chars = Character.toChars(codePoint);
        //return new String(chars);
        String value = String.valueOf(chars);
        return value;
    }
}
