package lsieun.lang;

public class UnicodeCharacterToChars {
    static int[] unicodeList = {0x43, 0x2103, 0x1F132, 0x1F1A0, 0x20FFFF};
    static char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static void main(String[] arg) {
        try {
            for (int i = 0; i < unicodeList.length; i++) {

                // Starting with the code point value
                int codePoint = unicodeList[i];

                // Dumping data in HEX numbers
                System.out.print("\n");
                System.out.print("\n                 Code point: " + intToHex(codePoint));

                // Getting Unicode character basic properties
                System.out.print("\n         isValidCodePoint(): " + Character.isValidCodePoint(codePoint));
                System.out.print("\n                isDefined(): " + Character.isDefined(codePoint));
                System.out.print("\n                  getName(): " + Character.getName(codePoint));
                System.out.print("\n                  Character: " + new String(Character.toChars(codePoint)));
                System.out.print("\n           isBmpCodePoint(): " + Character.isBmpCodePoint(codePoint));
                System.out.print("\n isSupplementaryCodePoint(): " + Character.isSupplementaryCodePoint(codePoint));
                System.out.print("\n                charCount(): " + Character.charCount(codePoint));

                // Getting surrogate char pair
                char charHigh = Character.highSurrogate(codePoint);
                char charLow = Character.lowSurrogate(codePoint);
                System.out.print("\n            highSurrogate(): " + charToHex(charHigh));
                System.out.print("\n             lowSurrogate(): " + charToHex(charLow));
                System.out.print("\n          isSurrogatePair(): " + Character.isSurrogatePair(charHigh, charLow));

                // Getting char sequence
                char[] charSeq = Character.toChars(codePoint);
                System.out.print("\n                  toChars():");
                for (int j = 0; j < charSeq.length; j++)
                    System.out.print(" " + charToHex(charSeq[j]));

                // Getting UTF-16BE byte sequence
                int[] intArray = {codePoint};
                String charString = new String(intArray, 0, 1);
                byte[] utf16Seq = charString.getBytes("UTF-16BE");
                System.out.print("\n     UTF-16BE byte sequence:");
                for (int j = 0; j < utf16Seq.length; j++)
                    System.out.print(" " + byteToHex(utf16Seq[j]));
            }
        } catch (Exception e) {
            System.out.print("\n" + e.toString());
        }
    }

    public static String byteToHex(byte b) {
        char[] a = {hexDigit[(b >> 4) & 0x0f], hexDigit[b & 0x0f]};
        return new String(a);
    }

    public static String charToHex(char c) {
        byte hi = (byte) (c >>> 8);
        byte lo = (byte) (c & 0xff);
        return byteToHex(hi) + byteToHex(lo);
    }

    public static String intToHex(int i) {
        char hi = (char) (i >>> 16);
        char lo = (char) (i & 0xffff);
        return charToHex(hi) + charToHex(lo);
    }
}
