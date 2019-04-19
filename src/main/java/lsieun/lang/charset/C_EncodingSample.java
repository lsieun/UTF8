package lsieun.lang.charset;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.IllegalCharsetNameException;

public class C_EncodingSample {
    static String defaultCharset = null;
    static int[] codePointArray = {
            0x0000, 0x003F, 0x0040, 0x007F, 0x0080, 0x00BF,
            0x00C0, 0x00FF, 0x0100, 0x3FFF, 0x4000, 0x7FFF,
            0x8000, 0xBFFF, 0xC000, 0xEFFF, 0xF000, 0xFFFF,
            0x1F108, 0x1F132, 0x1F1A0};
    static char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7',
            '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static void main(String[] args) {
        String charset = null;
        if (args.length > 0) charset = args[0];
        //charset = "US-ASCII";
        //charset = "ISO-8859-1";
        //charset = "GBK";
        charset = "UTF-16";
        //charset = "UTF-16BE";
        //charset = "UTF-16LE";
        //charset = "UTF-8";
        OutputStreamWriter writer = new OutputStreamWriter(new ByteArrayOutputStream());
        defaultCharset = writer.getEncoding();

        if (charset == null) System.out.println("Default (" + defaultCharset + ") encoding:");
        else System.out.println(charset + " encoding:");

        System.out.println("Char,    String, Writer, Charset, Encoder");
        for (int i = 0; i < codePointArray.length; i++) {
            int codePoint = codePointArray[i];
            byte[] b1 = encodeByString(codePoint, charset);
            byte[] b2 = encodeByWriter(codePoint, charset);
            byte[] b3 = encodeByCharset(codePoint, charset);
            byte[] b4 = encodeByEncoder(codePoint, charset);
            System.out.print(intToHex(codePoint) + ",");
            printBytes(b1);
            System.out.print(",");
            printBytes(b2);
            System.out.print(",");
            printBytes(b3);
            System.out.print(",");
            printBytes(b4);
            System.out.println("");
        }
    }

    public static String codePoint2String(int codePoint) {
        char[] chars = Character.toChars(codePoint);
        String str = String.valueOf(chars);
        return str;
    }

    public static byte[] encodeByCharset(int codePoint, String csn) {
        Charset cs = null;
        byte[] bytes = null;
        try {
            //(1) Charset
            if (csn == null) cs = Charset.forName(defaultCharset);
            else cs = Charset.forName(csn);

            //(2) String
            String str = codePoint2String(codePoint);

            //(3) Do it!
            ByteBuffer bb = cs.encode(str);
            bytes = copyBytes(bb.array(), bb.limit());
        } catch (IllegalCharsetNameException e) {
            System.out.println(e.toString());
        }
        return bytes;
    }

    public static byte[] encodeByEncoder(int codePoint, String csn) {
        Charset cs = null;
        byte[] bytes = null;
        try {
            if (csn == null) cs = Charset.forName(defaultCharset);
            else cs = Charset.forName(csn);

            CharsetEncoder ce = cs.newEncoder();
            ce.reset();
            ByteBuffer bb = ce.encode(CharBuffer.wrap(Character.toChars(codePoint)));
            bytes = copyBytes(bb.array(), bb.limit());
        } catch (IllegalCharsetNameException e) {
            System.out.println(e.toString());
        } catch (CharacterCodingException e) {
            //System.out.println(e.toString());
            bytes = new byte[]{(byte) 0x00};
        }
        return bytes;
    }

    public static byte[] encodeByString(int codePoint, String csn) {
        String s = new String(Character.toChars(codePoint));
        byte[] bytes = null;
        if (csn == null) {
            bytes = s.getBytes();
        } else {
            try {
                bytes = s.getBytes(csn);
            } catch (UnsupportedEncodingException e) {
                System.out.println(e.toString());
            }
        }
        return bytes;
    }

    public static byte[] encodeByWriter(int codePoint, String csn) {

        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        OutputStreamWriter out = null;
        if (csn == null) {
            out = new OutputStreamWriter(bs);
        } else {
            try {
                out = new OutputStreamWriter(bs, csn);
            } catch (UnsupportedEncodingException e) {
                System.out.println(e.toString());
            }
        }

        byte[] bytes = null;
        String str = new String(Character.toChars(codePoint));
        try {
            out.write(str);
            out.flush();
            bytes = bs.toByteArray();
            out.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
        return bytes;
    }

    public static byte[] copyBytes(byte[] source, int length) {
        byte[] target = new byte[length];
        for (int i = 0; i < Math.min(length, source.length); i++) target[i] = source[i];
        return target;
    }

    public static void printBytes(byte[] bytes) {
        for (int i = 0; i < bytes.length; i++)
            System.out.print(" " + byteToHex(bytes[i]));
    }

    public static String byteToHex(byte b) {
        char[] a = {hexDigit[(b >>> 4) & 0x0f], hexDigit[b & 0x0f]};
        return new String(a);
    }

    public static String charToHex(char ch) {
        byte hi = (byte) (ch >>> 8);
        byte lo = (byte) (ch & 0xff);
        return byteToHex(hi) + byteToHex(lo);
    }

    public static String intToHex(int i) {
        char hi = (char) (i >>> 16);
        char lo = (char) (i & 0xffff);
        return charToHex(hi) + charToHex(lo);
    }
}
