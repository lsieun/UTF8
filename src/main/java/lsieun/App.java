package lsieun;

public class App {
    public static void main(String[] args) {
//        char ch = '宋';
//        int i = ch;
//        System.out.println(i);
//        String name = Character.getName(i);
//        System.out.println(name);
//        int value = 0x5B8B;
//        System.out.println(value);
//        int type = Character.getType(i);
//        System.out.println(type);

        int intValue = 0x1F132;
        System.out.println(String.valueOf(Character.toChars(intValue)));
        System.out.println(intValue);
        System.out.println(Integer.toBinaryString(intValue));
        String str = new String(Character.toChars(intValue)) + "宋";
        System.out.println(str.charAt(2));
        System.out.println(str);
        int value = str.codePointAt(0);
        System.out.println(value);
        System.out.println(str.length());
        System.out.println(str.substring(1,3));
    }
}
