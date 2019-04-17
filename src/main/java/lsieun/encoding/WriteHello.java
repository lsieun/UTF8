package lsieun.encoding;

import lsieun.utils.io.FileUtils;

public class WriteHello {
    public static void main(String[] args) {
        String dir = WriteHello.class.getResource("/").getPath();
        String filepath = dir + "hello.txt";
        System.out.println("file://" + filepath);

        byte[] bytes = new byte[]{72, 101, 108, 108, 111};
        FileUtils.writeBytes(filepath, bytes);
    }
}
