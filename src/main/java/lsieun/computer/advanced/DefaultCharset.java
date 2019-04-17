package lsieun.computer.advanced;

import java.nio.charset.Charset;

public class DefaultCharset {
    public static void main(String[] args) {
        //csn = char set name
        String csn = Charset.defaultCharset().name();
        System.out.println(csn);

        //
        String encoding = System.getProperty("file.encoding");
        System.out.println(encoding);
    }
}
