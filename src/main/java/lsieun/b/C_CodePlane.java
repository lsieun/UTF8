package lsieun.b;

public class C_CodePlane {
    public static void main(String[] args) {
        int[] codePoints = {0x41, 0x61, 0x24B6, 0x5B8B, 0x1F130};
        for(int codePoint : codePoints) {
            boolean bmpCodePoint = Character.isBmpCodePoint(codePoint);
            boolean supplementaryCodePoint = Character.isSupplementaryCodePoint(codePoint);
            String line = String.format("%1$6X(%1$c): BMP(%2$b) Supplementary(%3$b)", codePoint, bmpCodePoint, supplementaryCodePoint);
            System.out.println(line);
        }
    }
}
