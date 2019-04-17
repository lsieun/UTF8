package lsieun.lang;

/**
 * The primitive type "char" is no longer capable to support Unicode characters
 * in the full range of U+0000 to U+10FFFF.
 */
public class UnicodeFriendly {
    public static void main(String[] args) {
        int letterC = 0x43; // ASCII character
        int degreeCelsius = 0x2103; // BMP character
        int squaredC = 0x1F132; // Supplementary character

        StringBuilder buffer = new StringBuilder();
        buffer.appendCodePoint(letterC);
        buffer.appendCodePoint(degreeCelsius);
        buffer.appendCodePoint(squaredC);
        String unicodeString = new String(buffer);
        //Output: C℃🄲
        System.out.println(unicodeString);
    }
}
