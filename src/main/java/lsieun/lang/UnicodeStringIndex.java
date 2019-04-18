package lsieun.lang;

public class UnicodeStringIndex {
    static int[] unicodeList = {0x43, 0x2103, 0x1F132, 0x1F1A0, 0x37, 0x0667, 0x2166, 0x3286, 0x4E03, 0x1F108};

    public static void main(String[] arg) {
        try {

            // Constructing a String from a list of code points
            int num = unicodeList.length;
            String str = new String(unicodeList, 0, num);
            System.out.print("\n                  String: " + str);

            // String length and code point count
            System.out.print("\n # of Unicode characters: " + num);
            System.out.print("\n        codePointCount(): " + str.codePointCount(0, str.length()));
            System.out.print("\n                length(): " + str.length());

            // String element at a BMP position
            System.out.print("\n               charAt(1): " + Integer.toHexString(str.charAt(1)));
            System.out.print("\n          codePointAt(1): " + Integer.toHexString(str.codePointAt(1)));

            // String element at a high surrogate position
            char high = str.charAt(2);
            System.out.print("\n               charAt(2): " + Integer.toHexString(high));
            System.out.print("\n          codePointAt(2): " + Integer.toHexString(str.codePointAt(2)));

            // String element at a low surrogate position
            char low = str.charAt(3);
            System.out.print("\n               charAt(3): " + Integer.toHexString(low));
            System.out.print("\n          codePointAt(3): " + Integer.toHexString(str.codePointAt(3)));

            // validating the surrogate char pair
            int code = Character.toCodePoint(high, low);
            System.out.print("\n Character.toCodePoint(): " + Integer.toHexString(Character.toCodePoint(high, low)));
        } catch (Exception e) {
            System.out.print("\n" + e.toString());
        }
    }
}
