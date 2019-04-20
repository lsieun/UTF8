package lsieun.a;

import lsieun.utils.radix.HexUtils;

public class A_Char2CodePoint {
    public static void main(String[] args) {
        String str = "宋";
        int codePoint = str.codePointAt(0);

        String hexCode = HexUtils.intToHex(codePoint).toUpperCase();
        System.out.println("Hex: " + hexCode);
    }
}
