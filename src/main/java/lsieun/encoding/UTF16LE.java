package lsieun.encoding;

@SuppressWarnings("Duplicates")
public class UTF16LE {
    public static byte[] getBytes(int codePoint) {
        char[] chars = UTF16.getChars(codePoint);
        int len = chars.length;

        byte[] bytes = new byte[len * 2];
        for(int i=0; i<len; i++) {
            char ch = chars[i];

            byte hi = (byte) (ch >>> 8 & 0xFF);
            byte lo = (byte) (ch & 0xFF);

            bytes[2 * i] = lo;
            bytes[2 * i + 1] = hi;
        }

        return bytes;
    }
}
