# String

<!-- TOC -->

- [1. String Class with Unicode Utility Methods](#1-string-class-with-unicode-utility-methods)
  - [1.1. CodePoint to String](#11-codepoint-to-string)
  - [1.2. chars to String](#12-chars-to-string)
  - [1.3. char info](#13-char-info)
  - [1.4. Code Point info](#14-code-point-info)
  - [1.5. Byte info](#15-byte-info)
- [2. String.length() Is Not Number of Characters](#2-stringlength-is-not-number-of-characters)
- [String.toCharArray() Returns the UTF-16BE Sequence](#stringtochararray-returns-the-utf-16be-sequence)
- [String Literals and Source Code Encoding](#string-literals-and-source-code-encoding)

<!-- /TOC -->

## 1. String Class with Unicode Utility Methods

URL: ["String" Class with Unicode Utility Methods](http://www.herongyang.com/Unicode/Java-String-Class-with-Unicode-Utility-Methods.html)

This section provides an introduction on '`java.lang.String`' class methods added and modified since `J2SE 5.0` to support Unicode character processing.

Since designers of `J2SE 5.0` did not change the internal storage mechanism for the "`String`" class, **Unicode supplementary characters** will be stored as **surrogate "char" pairs** in "String" objects. In other words, **a single supplementary character** will take **2 storage positions** in a "String" object. If all characters in a "String" object are supplementary characters, the length of the "String" object is 2 times of the number of characters.

If a "String" object contains both **BMP characters** and **supplementary characters**, there is no 1-to-1 relation between **Unicode character positions** and **"char" storage positions**. The `n-th` Unicode character may not be stored at the `n-th` or `2*n-th` "char" position in a "String" object.

To help manage this inconvenience, designers of `J2SE 5.0` enhanced some **existing methods** and added some **new methods** in the "String" class. Here are some examples:

### 1.1. CodePoint to String

- `String(int[] codePoints, int offset, int count)` constructor - Allocates a new String that contains characters from a subarray of the Unicode code point array argument. The `offset` argument is the index of the first code point of the subarray and the `count` argument specifies the length of the subarray. The contents of the subarray are converted to chars; subsequent modification of the int array does not affect the newly created string.

### 1.2. chars to String

- `String(char[] value)` constructor - Allocates a new String so that it represents the sequence of characters currently contained in the character array argument. The contents of the character array are copied; subsequent modification of the character array does not affect the newly created string.
- `static String valueOf(char[] data)` - Returns the string representation of the char array argument. The contents of the character array are copied; subsequent modification of the character array does not affect the newly created string.

### 1.3. char info

- `int length()` - Returns the length of this string. The length is equal to **the number** of **Unicode code units** in the string.
- `char charAt(int index)` - Returns the char value at the specified `index`. An `index` ranges from `0` to `length() - 1`. The first char value of the sequence is at index `0`, the next at index `1`, and so on, as for array indexing. If the char value specified by the index is a surrogate, the surrogate value is returned.
- `int indexOf(int ch)` - Returns the index within this string of the first occurrence of the specified character. If a character with value ch occurs in the character sequence represented by this String object, then the index (in Unicode code units) of the first such occurrence is returned. For values of ch in the range from 0 to 0xFFFF (inclusive), this is the smallest value k such that: this.charAt(k) == ch, is true. For other values of ch, it is the smallest value k such that: this.codePointAt(k) == ch, is true. In either case, if no such character occurs in this string, then -1 is returned.
- `char[] toCharArray()` - Converts this string to a new character array.

下面这个方法也归到了这里，是因为，我觉得，它本质上就是针对char进行操作的

- `String substring(int beginIndex, int endIndex)` - Returns a new string that is a substring of this string. The substring begins at the specified `beginIndex` and extends to the character at index `endIndex - 1`. Thus the length of the substring is `endIndex-beginIndex`.

### 1.4. Code Point info

- `int codePointAt(int index)` - Returns the character (Unicode code point) at the specified `index`. The `index` refers to char values (Unicode code units) and ranges from `0` to `length() - 1`. If the char value specified at the given `index` is in the high-surrogate range, the following index is less than the length of this String, and the char value at the following index is in the low-surrogate range, then the supplementary code point corresponding to this surrogate pair is returned. Otherwise, the char value at the given index is returned.
- `int codePointCount(int beginIndex, int endIndex)` - Returns the number of Unicode code points in the specified text range of this String. The text range begins at the specified beginIndex and extends to the char at index endIndex - 1. Thus the length (in chars) of the text range is endIndex-beginIndex. Unpaired surrogates within the text range count as one code point each.

### 1.5. Byte info

- `byte[] getBytes(Charset charset)` - Encodes this String into a sequence of bytes using the given `charset`, storing the result into **a new byte array**. This method always replaces malformed-input and unmappable-character sequences with this charset's default replacement byte array. The `CharsetEncoder` class should be used when more control over the encoding process is required.

## 2. String.length() Is Not Number of Characters

URL: [String.length() Is Not Number of Characters](http://www.herongyang.com/Unicode/Java-String-length-Is-Not-Number-of-Characters.html)

This section provides tutorial example on showing the difference between `length()` and `codePointCount()` methods. The difference between `charAt(int index)` and `codePointAt(int index)` is also demonstrated.

Because Unicode characters are stored in "String" objects as a mixed of single "char" elements and surrogate "char" element pairs, the "char" element index and Unicode character location are difficult to calculate.

Here is a tutorial example to show you this problem:

```java
public class UnicodeStringIndex {
    static int[] unicodeList = {0x43, 0x2103, 0x1F132, 0x1F1A0, 0x37, 0x0667, 0x2166, 0x3286, 0x4E03, 0x1F108};

    public static void main(String[] arg) {
        try {

            // Constructing a String from a list of code points
            int num = unicodeList.length;
            String str = new String(unicodeList, 0, num);
            System.out.print("\n                  String: " + str);

            // String length and code point count
            System.out.print("\n # of Unicode characters: " + num);
            System.out.print("\n        codePointCount(): " + str.codePointCount(0, str.length()));
            System.out.print("\n                length(): " + str.length());

            // String element at a BMP position
            System.out.print("\n               charAt(1): " + Integer.toHexString(str.charAt(1)));
            System.out.print("\n          codePointAt(1): " + Integer.toHexString(str.codePointAt(1)));

            // String element at a high surrogate position
            char high = str.charAt(2);
            System.out.print("\n               charAt(2): " + Integer.toHexString(high));
            System.out.print("\n          codePointAt(2): " + Integer.toHexString(str.codePointAt(2)));

            // String element at a low surrogate position
            char low = str.charAt(3);
            System.out.print("\n               charAt(3): " + Integer.toHexString(low));
            System.out.print("\n          codePointAt(3): " + Integer.toHexString(str.codePointAt(3)));

            // validating the surrogate char pair
            int code = Character.toCodePoint(high, low);
            System.out.print("\n Character.toCodePoint(): " + Integer.toHexString(Character.toCodePoint(high, low)));
        } catch (Exception e) {
            System.out.print("\n" + e.toString());
        }
    }
}
```

Compile and run it

```txt

                  String: C℃🄲🆠7٧Ⅶ㊆七🄈
 # of Unicode characters: 10
        codePointCount(): 10
                length(): 13
               charAt(1): 2103
          codePointAt(1): 2103
               charAt(2): d83c
          codePointAt(2): 1f132
               charAt(3): dd32
          codePointAt(3): dd32
 Character.toCodePoint(): 1f132
```

The output confirms that:

- `codePointCount()` returns the number Unicode characters in the "String" object.
- `length()` returns the number of "char" elements in the "String" object. `length()` is always greater than or equal to `codePointCount()`.
- `charAt()` always return the "char" value at the given "char" index. It returns the high surrogate "char", if the given index points to the first "char" of a supplementary character - see charAt(2) in the output. It returns the low surrogate "char", if the given index points to the second "char" of a supplementary character - see charAt(3) in the output.
- `codePointAt()` returns the correct code point value, if the given index points to a **BMP character** - see codePointAt(1). It returns the correct code point value, if the given index points to the first "char" of a supplementary character. - see codePointAt(2). It returns the low surrogate "char", if the given index points to the second "char" of a supplementary character. - see codePointAt(3).

## String.toCharArray() Returns the UTF-16BE Sequence

This section provides tutorial example on showing that the output of `toCharArray()` is the same as `getBytes('UTF-16BE')` at the bit level.

Another way to look at a "String" object is to dump it into **a "char" sequence** or **a "byte" sequence** with different encoding algorithms:

```java
public class UnicodeStringEncoding {
    static int[] unicodeList = {0x43, 0x2103, 0x1F132, 0x1F1A0};
    static char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static void main(String[] arg) {
        try {

            // Constructing a String from a list of code points
            int num = unicodeList.length;
            String str = new String(unicodeList, 0, num);
            System.out.print("\n                  String: " + str);

            // String length and code point count
            System.out.print("\n # of Unicode characters: " + num);
            System.out.print("\n        codePointCount(): " + str.codePointCount(0, str.length()));
            System.out.print("\n                length(): " + str.length());

            // Getting the char sequence
            char[] charSeq = str.toCharArray();
            System.out.print("\n           toCharArray():");
            printChars(charSeq);

            // Getting Unicode encoding sequences
            byte[] byteSeq8 = str.getBytes("UTF-8");
            System.out.print("\n         getBytes(UTF-8):");
            printBytes(byteSeq8);
            byte[] byteSeq16 = str.getBytes("UTF-16BE");
            System.out.print("\n      getBytes(UTF-16BE):");
            printBytes(byteSeq16);
            byte[] byteSeq32 = str.getBytes("UTF-32BE");
            System.out.print("\n      getBytes(UTF-32BE):");
            printBytes(byteSeq32);
        } catch (Exception e) {
            System.out.print("\n" + e.toString());
        }
    }

    public static void printBytes(byte[] b) {
        for (int j = 0; j < b.length; j++)
            System.out.print(" " + byteToHex(b[j]));
    }

    public static String byteToHex(byte b) {
        char[] a = {hexDigit[(b >> 4) & 0x0f], hexDigit[b & 0x0f]};
        return new String(a);
    }

    public static void printChars(char[] c) {
        for (int j = 0; j < c.length; j++)
            System.out.print(" " + charToHex(c[j]));
    }

    public static String charToHex(char c) {
        byte hi = (byte) (c >>> 8);
        byte lo = (byte) (c & 0xff);
        return byteToHex(hi) + byteToHex(lo);
    }
}
```

Compile and run it:

```txt

                  String: C℃🄲🆠
 # of Unicode characters: 4
        codePointCount(): 4
                length(): 6
           toCharArray(): 0043 2103 D83C DD32 D83C DDA0
         getBytes(UTF-8): 43 E2 84 83 F0 9F 84 B2 F0 9F 86 A0
      getBytes(UTF-16BE): 00 43 21 03 D8 3C DD 32 D8 3C DD A0
      getBytes(UTF-32BE): 00 00 00 43 00 00 21 03 00 01 F1 32 00 01 F1 A0
```

The output confirms that:

- `toCharArray()` returns the same output as the `getByte("UTF-16BE")` at the bit level. In other words, Unicode characters are stored in a "String" object as a UTF-16BE encoded "char" sequence.
- `getByte("UTF-16BE")` returns the same output as the original code point value list at the bit level.

## String Literals and Source Code Encoding

URL: [String Literals and Source Code Encoding](http://www.herongyang.com/Unicode/Java-String-Literals-and-Source-Code-Encoding.html)

This section provides tutorial example on how to represent **non-ASCII characters** in **UTF-8 encoding byte sequences** as part of String literals in the **Java source code**.

In previous tutorials, we have learned how to represent **non-ASCII characters** in `\uXXXX` escape sequences as part of **String literals** in **Java source code**.

In this tutorial, we will learn how to represent **non-ASCII characters** in **UTF-8 encoding byte sequences** as part of **String literals** in Java source code.

Here is our test string that contains 2 Non-ASCII characters:

```txt
Delicious food U+1F60B takes time U+23F3

Where: 
   U+1F60B: FACE SAVOURING DELICIOUS FOOD
   U+23F3: HOURGLASS WITH FLOWING SAND
```

Our test string should be displayed like this, if you have the correct Unicode font installed on your computer.

```txt
Delicious food 😋 takes time ⏳
```

In our first test program, we will continue to use `\uXXXX` sequences in our source code. Note that `U+1F60B` character needs to be encoded as a surrogate pair of `\uD83D\uDE0B` based on the UTF-16 encoding rule.

```java
public class UnicodeStringLiterals {
    public static void main(String[] arg) {
        try {
            String str = "Delicious food \uD83D\uDE0B takes time \u23F3";
            System.out.print("\n          String: " + str);
            System.out.print("\ncodePointCount(): " + str.codePointCount(0, str.length()));
            System.out.print("\n        length(): " + str.length());
            System.out.print("\n     String dump: ");
            printString(str);
        } catch (Exception e) {
            System.out.print("\n" + e.toString());
        }
    }

    public static void printString(String s) {
        char[] chars = s.toCharArray();
        for (char c : chars) {
            byte hi = (byte) (c >>> 8);
            byte lo = (byte) (c & 0xff);
            System.out.print(String.format("%02X%02X ", hi, lo));
        }
    }
}
```

Compile and run it:

```txt

          String: Delicious food 😋 takes time ⏳
codePointCount(): 29
        length(): 30
     String dump: 0044 0065 006C 0069 0063 0069 006F 0075 0073 0020 0066 006F 006F 0064 0020 D83D DE0B 0020 0074 0061 006B 0065 0073 0020 0074 0069 006D 0065 0020 23F3
```

In our second test program, we will continue to use UTF-8 encoding byte sequences in our source code. This program is definitely better than the first program, because you can actually see non-ASCII characters displayed in the source code.

```java
public class UnicodeStringLiteralsUTF8 {
    public static void main(String[] arg) {
        try {
            String str = "Delicious food 😋 takes time ⏳";
            System.out.print("\n          String: " + str);
            System.out.print("\ncodePointCount(): " + str.codePointCount(0, str.length()));
            System.out.print("\n        length(): " + str.length());
            System.out.print("\n     String dump: ");
            printString(str);
        } catch (Exception e) {
            System.out.print("\n" + e.toString());
        }
    }

    public static void printString(String s) {
        char[] chars = s.toCharArray();
        for (char c : chars) {
            byte hi = (byte) (c >>> 8);
            byte lo = (byte) (c & 0xff);
            System.out.print(String.format("%02X%02X ", hi, lo));
        }
    }
}
```

This time, we need to make sure that `UnicodeStringLiteralsUTF8.java` is saved as a `UTF-8` encoding file and compile with the "`-encoding UTF8`" option:

```bash
javac -encoding UTF8 UnicodeStringLiteralsUTF8.java
java UnicodeStringLiteralsUTF8
```

Output:

```txt

          String: Delicious food 😋 takes time ⏳
codePointCount(): 29
        length(): 30
     String dump: 0044 0065 006C 0069 0063 0069 006F 0075 0073 0020 0066 006F 006F 0064 0020 D83D DE0B 0020 0074 0061 006B 0065 0073 0020 0074 0069 006D 0065 0020 23F3
```

The output is identical to the first program. This proves that we have properly represented non-ASCII characters in UTF-8 encoding byte sequences as part of String literals in the Java source code.
