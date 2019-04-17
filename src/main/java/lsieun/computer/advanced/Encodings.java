package lsieun.computer.advanced;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;
import java.util.SortedMap;

public class Encodings {
    public static void main(String[] arg) {
        SortedMap m = Charset.availableCharsets();
        Set k = m.keySet();
        System.out.println("Canonical name, Display name,"
                +" Can encode, Aliases");
        Iterator i = k.iterator();
        while (i.hasNext()) {
            String n = (String) i.next();
            Charset e = (Charset) m.get(n);
            String d = e.displayName();
            boolean c = e.canEncode();
            System.out.print(n+", "+d+", "+c);
            Set s = e.aliases();
            Iterator j = s.iterator();
            while (j.hasNext()) {
                String a = (String) j.next();
                System.out.print(", "+a);
            }
            System.out.println("");
        }
    }
}
