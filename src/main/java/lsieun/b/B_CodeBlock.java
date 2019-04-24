package lsieun.b;

public class B_CodeBlock {
    public static void main(String[] args) {
        int[] codePoints = {0x41, 0x61, 0x24B6, 0x5B8B, 0x1F130};
        for(int codePoint : codePoints) {
            Character.UnicodeBlock block = Character.UnicodeBlock.of(codePoint);
            String line = String.format("%1$6X(%1$c): %2$s", codePoint, block);
            System.out.println(line);
        }
    }
}
