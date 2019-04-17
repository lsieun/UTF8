package lsieun.lang;

public class UnicodeCharacterNumeric {
    static int[] unicodeList = {0x37, 0x0667, 0x2166, 0x3286, 0x4E03, 0x1F108};

    public static void main(String[] arg) {
        try {
            for (int i = 0; i < unicodeList.length; i++) {

                // Starting with the code point value
                int codePoint = unicodeList[i];

                // Dumping data in HEX numbers
                System.out.print("\n");
                System.out.print("\n                 Code point: " + Integer.toHexString(codePoint).toUpperCase());

                // Getting Unicode character numeric values
                System.out.print("\n                isDefined(): " + Character.isDefined(codePoint));
                System.out.print("\n                  getName(): " + Character.getName(codePoint));
                System.out.print("\n                  Character: " + new String(Character.toChars(codePoint)));
                System.out.print("\n          getNumericValue(): " + Character.getNumericValue(codePoint));

                // Getting Unicode character type
                int intType = Character.getType(codePoint);
                System.out.print("\n                  getType(): " + intType);
                System.out.print("\n    is DECIMAL_DIGIT_NUMBER: " + (intType == Character.DECIMAL_DIGIT_NUMBER));
                System.out.print("\n                  isDigit(): " + Character.isDigit(codePoint));
            }
        } catch (Exception e) {
            System.out.print("\n" + e.toString());
        }
    }
}
