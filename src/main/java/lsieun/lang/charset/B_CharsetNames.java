package lsieun.lang.charset;

import java.nio.charset.Charset;
import java.util.Set;

public class B_CharsetNames {
    public static void main(String[] args) {
        String[] names = new String[] { "Big5", "GB2312", "GBK", "GB18030", "UTF-8", "UTF-16"};
        for(int i=0; i<names.length; i++) {
            String csn = names[i];
            Charset cs = Charset.forName(csn);
            String name = cs.name();
            Set<String> aliases = cs.aliases();
            System.out.println(name + ": " + aliases);
        }
    }
}
