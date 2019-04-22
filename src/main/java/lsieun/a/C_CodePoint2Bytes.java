package lsieun.a;

import java.nio.charset.StandardCharsets;

import lsieun.encoding.UTF16BE;
import lsieun.encoding.UTF16LE;
import lsieun.encoding.UTF8;
import lsieun.utils.radix.HexUtils;

public class C_CodePoint2Bytes {
    public static void main(String[] args) {
        int[] codePointArray = new int[] {65, 196, 9398, 23435, 127280};
        for(int codePoint : codePointArray) {
            print(codePoint);
        }
    }

    private static void print(int codePoint) {
        String str = String.valueOf(Character.toChars(codePoint));
        System.out.println(str);
        System.out.println("codePoint: " + codePoint);
        System.out.println("HexCode: " + HexUtils.fromInt(codePoint).toUpperCase());

        byte[] bytes1 = UTF8.getBytes(codePoint);
        byte[] bytes2 = str.getBytes(StandardCharsets.UTF_8);
        System.out.println("UTF8 bytes(实验): " + HexUtils.fromBytes(bytes1));
        System.out.println("UTF8 bytes(参照): " + HexUtils.fromBytes(bytes2));

        byte[] bytes3 = UTF16BE.getBytes(codePoint);
        byte[] bytes4 = str.getBytes(StandardCharsets.UTF_16BE);
        System.out.println("UTF16BE bytes(实验): " + HexUtils.fromBytes(bytes3));
        System.out.println("UTF16BE bytes(参照): " + HexUtils.fromBytes(bytes4));

        byte[] bytes5 = UTF16LE.getBytes(codePoint);
        byte[] bytes6 = str.getBytes(StandardCharsets.UTF_16LE);
        System.out.println("UTF16LE bytes(实验): " + HexUtils.fromBytes(bytes5));
        System.out.println("UTF16LE bytes(参照): " + HexUtils.fromBytes(bytes6));

        byte[] bytes7 = str.getBytes(StandardCharsets.UTF_16);
        System.out.println("UTF16 bytes with BOM: " + HexUtils.fromBytes(bytes7).toUpperCase());
        System.out.println("=====================================");
    }
}
