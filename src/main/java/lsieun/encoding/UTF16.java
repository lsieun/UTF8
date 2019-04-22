package lsieun.encoding;

public class UTF16 {

    public static char[] getChars(int codePoint) {
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

    public static int toCodePoint(char[] chars) {
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
