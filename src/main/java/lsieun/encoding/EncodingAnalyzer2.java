package lsieun.encoding;

import java.io.UnsupportedEncodingException;

public class EncodingAnalyzer2 {
    static char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static void main(String[] args) {
        String charset = null;
        if (args.length > 0) charset = args[0];
        if (charset == null) System.out.println("Default encoding:");
        else System.out.println(charset + " encoding:");

        int lastByte = 0;
        int lastLength = 0;
        byte[] startSequence = null;
        int startChar = 0;
        byte[] endSequence = null;
        int endChar = 0;
        boolean isFirstChar = true;
        for (int i = 0; i <= 0x10FFFF; i++) {
            int c = i;
            String s = new String(Character.toChars(c));
            byte[] b = null;
            if (charset == null) {
                b = s.getBytes();
            } else {
                try {
                    b = s.getBytes(charset);
                } catch (UnsupportedEncodingException e) {
                    System.out.println(e.toString());
                    break;
                }
            }
            int l = b.length;
            int lb = ((int) b[l - 1]) & 0x00FF;
            if (isFirstChar == true) {
                isFirstChar = false;
                startSequence = b;
                startChar = c;
                lastByte = lb - 1;
                lastLength = l;
            }
            if (!(l == lastLength && (lb == lastByte + 1 || lb == lastByte))) {
                System.out.print(intToHex(startChar) + " >");
                printBytes(startSequence);
                System.out.print(" - " + intToHex(endChar) + " >");
                printBytes(endSequence);
                if (lastByte == 0x3F) System.out.print(": Invalid range");
                System.out.println("");
                startSequence = b;
                startChar = c;
            }
            endSequence = b;
            endChar = c;
            lastLength = l;
            lastByte = lb;
        }
        System.out.print(intToHex(startChar) + " >");
        printBytes(startSequence);
        System.out.print(" - " + intToHex(endChar) + " >");
        printBytes(endSequence);
        if (lastByte == 0x3F) System.out.print(": Invalid range");
        System.out.println("");
    }

    public static void printBytes(byte[] b) {
        for (int j = 0; j < b.length; j++)
            System.out.print(" " + byteToHex(b[j]));
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
