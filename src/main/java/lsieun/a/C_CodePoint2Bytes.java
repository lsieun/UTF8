package lsieun.a;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class C_CodePoint2Bytes {
    public static void main(String[] args) {
        Character ch = 0xFFFD;
        Charset cs = StandardCharsets.UTF_8;
        System.out.println(Character.getName(0xFFFD));
    }
}
