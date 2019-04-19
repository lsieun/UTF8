# Charset

<!-- TOC -->

- [1. What](#1-what)
- [2. Charset List](#2-charset-list)
- [3. Charset names](#3-charset-names)
- [4. Terminology](#4-terminology)
- [5. Method](#5-method)
- [6. Concurrency](#6-concurrency)
- [7. Extension](#7-extension)

<!-- /TOC -->

## 1. What

- `java.nio.charset.Charset`

**A named mapping** between **sequences of sixteen-bit Unicode code units** and **sequences of bytes**.

**The native character encoding** of the **Java programming language** is `UTF-16`. A **charset** in the **Java platform** therefore defines **a mapping** between **sequences of sixteen-bit UTF-16 code units** (that is, sequences of chars) and **sequences of bytes**.

When **decoding**, the `UTF-16` charset interprets the byte-order mark at the beginning of the input stream to indicate the byte-order of the stream but defaults to **big-endian** if there is no byte-order mark; when **encoding**, it uses big-endian byte order and writes a **big-endian byte-order mark**.

## 2. Charset List

Before looking at **how to perform an character encoding**, let's see **how many encodings are supported in Java** using the `availableCharsets()` static method in the `java.nio.charset.Charset` class.

```java
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;

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
```

## 3. Charset names

Every charset has a **canonical name** and may also have one or more **aliases**. The **canonical name** is returned by the `name` method of this class. **Canonical names** are, by convention, usually in upper case. The **aliases** of a charset are returned by the `aliases` method.

```java
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
```

Output:

```txt
Big5: [csBig5]
GB2312: [gb2312, euc-cn, x-EUC-CN, euccn, EUC_CN, gb2312-80, gb2312-1980]
GBK: [CP936, windows-936]
GB18030: [gb18030-2000]
UTF-8: [unicode-1-1-utf-8, UTF8]
UTF-16: [UTF_16, unicode, utf16, UnicodeBig]
```

Some charsets have an **historical name** that is defined for compatibility with previous versions of the Java platform. A charset's **historical name** is either its **canonical name** or one of its **aliases**. The **historical name** is returned by the `getEncoding()` methods of the `InputStreamReader` and `OutputStreamWriter` classes.



## 4. Terminology

A **coded character set** is a mapping between **a set of abstract characters** and **a set of integers**. `US-ASCII`, `ISO 8859-1`, `JIS X 0201`, and `Unicode` are examples of **coded character sets**.

Some standards have defined **a character set** to be simply **a set of abstract characters** without an associated assigned numbering. An alphabet(字母表) is an example of such a character set. However, the subtle distinction between **character set** and **coded character set** is rarely used in practice; the former has become a short form for the latter, including in the Java API specification.

A **character-encoding scheme** is a mapping between **one or more coded character sets** and **a set of octet (eight-bit byte) sequences**. `UTF-8`, `UTF-16`, `ISO 2022`, and `EUC` are examples of character-encoding schemes. **Encoding schemes** are often associated with a particular **coded character set**; `UTF-8`, for example, is used only to encode `Unicode`. Some schemes, however, are associated with multiple coded character sets; EUC, for example, can be used to encode characters in a variety of Asian coded character sets.

## 5. Method

This class defines methods

- for creating decoders and encoders and
- for retrieving the various names associated with a charset.

This class also defines `static` methods

- for testing whether a particular charset is supported,
- for locating charset instances by name, and
- for constructing a map that contains every charset for which support is available in the current Java virtual machine.

## 6. Concurrency

Instances of this class are immutable.

> immutable意味着什么呢？意味着不可变，不可变又意味着多个线程运行时，不会因为更改其状态而造成某种不一致。

All of the methods defined in this class are safe for use by multiple concurrent threads.

## 7. Extension

Support for **new charsets** can be added via the **service-provider interface** defined in the `CharsetProvider` class.


