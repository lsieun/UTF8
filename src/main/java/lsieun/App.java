package lsieun;

public class App {
    public static void main(String[] args) {
        char ch = '宋';
        int i = ch;
        System.out.println(i);
        String name = Character.getName(i);
        System.out.println(name);
        int value = 0x5B8B;
        System.out.println(value);
        int type = Character.getType(i);
        System.out.println(type);


    }
}
