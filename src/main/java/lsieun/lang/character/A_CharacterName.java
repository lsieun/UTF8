package lsieun.lang.character;

public class A_CharacterName {
    public static void main(String[] args) {
        int[] codePointArray = new int[] {0x00,
                0x40, 0xA9, 0xAE,
                0x058D, 0x058E, // 无名字
                0x1D01, 0x1D02, // LATIN LETTER
                0x2120, 0x2121, 0x2122, // Mark or Sign
                0x2160, 0x2161, 0x2162, // ROMAN NUMERAL
                0x2460, 0x2461, 0x2462, // CIRCLED DIGIT
                0x6587, 0x6728, 0x6C34, // CJK UNIFIED IDEOGRAPHS
                0x00A0, 0xFEFF, // NO-BREAK SPACE
                0xFFFD, 0xFFFE, 0xFFFF};
        for(int codePoint : codePointArray) {
            print(codePoint);
        }
    }

    public static void print(int codePoint) {
        String hexCode = Integer.toHexString(codePoint).toUpperCase();
        String name = Character.getName(codePoint);
        int type = Character.getType(codePoint);
        String value = String.valueOf(Character.toChars(codePoint));
        System.out.println(hexCode + ": " + name + "(" + value + ")");
    }
}
