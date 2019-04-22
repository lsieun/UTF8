package lsieun.encoding;

import java.nio.charset.StandardCharsets;

import lsieun.utils.radix.HexUtils;

public class UTF8 {


    public static byte[] getBytes(int codePoint) {
        if(codePoint < 0 || codePoint > 0x10FFFF) {
            throw new IllegalArgumentException("codePoint should be in range 0~" + 0x10FFFF + ": " + codePoint);
        }

        byte[] bytes = null;
        if(codePoint < 0x0080) {
            byte b = (byte) codePoint;

            bytes = new byte[1];
            bytes[0] = b;
        }
        else if(codePoint < 0x0800) {
            byte b1 = (byte)(0xC0 | (codePoint >>> 6 & 0x1F));
            byte b2 = (byte)(0x80 | (codePoint & 0x3F));

            bytes = new byte[2];
            bytes[0] = b1;
            bytes[1] = b2;
        }
        else if(codePoint < 0x10000) {
            byte b1 = (byte)(0xE0 | codePoint >>> 12 & 0x0F);
            byte b2 = (byte)(0x80 | codePoint >>> 6 & 0x3F);
            byte b3 = (byte)(0x80 | codePoint & 0x3F);

            bytes = new byte[3];
            bytes[0] = b1;
            bytes[1] = b2;
            bytes[2] = b3;
        }
        else if(codePoint < 0x11FFFF) {
            byte b1 = (byte)(0xF0 | codePoint >>> 18 & 0x07);
            byte b2 = (byte)(0x80 | codePoint >>> 12 & 0x3F);
            byte b3 = (byte)(0x80 | codePoint >>> 6 & 0x3F);
            byte b4 = (byte)(0x80 | codePoint >>> 0 & 0x3F);

            bytes = new byte[4];
            bytes[0] = b1;
            bytes[1] = b2;
            bytes[2] = b3;
            bytes[3] = b4;
        }

        return bytes;
    }
}
