package lsieun.lang.charset;

import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

/**
 * <p>观千剑而后识器，操千曲而后晓声</p>
 *
 * 看一看一些常见的Charset： Big5、GB2312、GBK、GB18030、UTF-8和UTF-16
 */
public class A_AvailableCharsets {
    public static void main(String[] args) {
        SortedMap<String, Charset> sortedMap = Charset.availableCharsets();
        System.out.println("Size = " + sortedMap.size());
        System.out.println("=========================================");
        Set<Map.Entry<String, Charset>> entrySet = sortedMap.entrySet();
        Iterator<Map.Entry<String, Charset>> it = entrySet.iterator();
        while(it.hasNext()) {
            Map.Entry<String, Charset> entry = it.next();
            String name = entry.getKey();
            System.out.println(name);
        }
    }
}
