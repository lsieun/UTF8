package lsieun.lang;

public class UnicodeStringLiterals {
    public static void main(String[] arg) {
        try {
            String str = "Delicious food \uD83D\uDE0B takes time \u23F3";
            System.out.print("\n          String: " + str);
            System.out.print("\ncodePointCount(): " + str.codePointCount(0, str.length()));
            System.out.print("\n        length(): " + str.length());
            System.out.print("\n     String dump: ");
            printString(str);
        } catch (Exception e) {
            System.out.print("\n" + e.toString());
        }
    }

    public static void printString(String s) {
        char[] chars = s.toCharArray();
        for (char c : chars) {
            byte hi = (byte) (c >>> 8);
            byte lo = (byte) (c & 0xff);
            System.out.print(String.format("%02X%02X ", hi, lo));
        }
    }
}
