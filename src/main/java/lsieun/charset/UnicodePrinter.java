package lsieun.charset;

import lsieun.utils.radix.HexUtils;

public class UnicodePrinter {
    public static void main(String[] args) {
        //大写字母序号
        printUnicode(0x24B6, 0x24CF);
        //小写字母序号
        printUnicode(0x24D0, 0x24E9);
        // BMP
        printUnicode(0x30, 0xFFFF);
        // Plane 1
        //printUnicode(0x10000, 0x1FFFF);
    }

    public static void printUnicode(int from, int to) {
        StringBuilder sb = new StringBuilder();

        for(int i=from; i<=to; i++) {
            int remainder = i % 16;
            if(remainder == 0 || i==from) {
                char ch = (char) (i);
                sb.append(HexUtils.charToHex(ch).toUpperCase() + ": ");
            }




            if(Character.isValidCodePoint(i) && Character.isDefined(i)) {
                char[] chars = Character.toChars(i);
                String str = String.valueOf(chars);
                sb.append(" " + str + ",");
            }
            else {
                sb.append(" ,");
            }

            if(remainder == 15) {
                sb.append("|@@|\r\n");
            }
        }

        System.out.println(sb.toString());
        System.out.println("=================================");
    }
}
