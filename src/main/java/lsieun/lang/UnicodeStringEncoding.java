package lsieun.lang;

public class UnicodeStringEncoding {
    static int[] unicodeList = {0x43, 0x2103, 0x1F132, 0x1F1A0};
    static char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

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

            // Getting the char sequence
            char[] charSeq = str.toCharArray();
            System.out.print("\n           toCharArray():");
            printChars(charSeq);

            // Getting Unicode encoding sequences
            byte[] byteSeq8 = str.getBytes("UTF-8");
            System.out.print("\n         getBytes(UTF-8):");
            printBytes(byteSeq8);
            byte[] byteSeq16 = str.getBytes("UTF-16BE");
            System.out.print("\n      getBytes(UTF-16BE):");
            printBytes(byteSeq16);
            byte[] byteSeq32 = str.getBytes("UTF-32BE");
            System.out.print("\n      getBytes(UTF-32BE):");
            printBytes(byteSeq32);
        } catch (Exception e) {
            System.out.print("\n" + e.toString());
        }
    }

    public static void printBytes(byte[] b) {
        for (int j = 0; j < b.length; j++)
            System.out.print(" " + byteToHex(b[j]));
    }

    public static String byteToHex(byte b) {
        char[] a = {hexDigit[(b >> 4) & 0x0f], hexDigit[b & 0x0f]};
        return new String(a);
    }

    public static void printChars(char[] c) {
        for (int j = 0; j < c.length; j++)
            System.out.print(" " + charToHex(c[j]));
    }

    public static String charToHex(char c) {
        byte hi = (byte) (c >>> 8);
        byte lo = (byte) (c & 0xff);
        return byteToHex(hi) + byteToHex(lo);
    }
}
