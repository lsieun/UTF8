# Character

<!-- TOC -->

- [1. "Character" Class with Unicode Utility Methods](#1-%22character%22-class-with-unicode-utility-methods)
  - [1.1. isXXX](#11-isxxx)
  - [1.2. char to CodePoint](#12-char-to-codepoint)
  - [1.3. Code Point to char](#13-code-point-to-char)
  - [1.4. Trivial](#14-trivial)
- [2. Character.toChars() - char Sequence of Code Point](#2-charactertochars---char-sequence-of-code-point)
- [3. Character.getNumericValue() - Numeric Value of Code Point](#3-charactergetnumericvalue---numeric-value-of-code-point)
- [4. Character.getType()](#4-charactergettype)

<!-- /TOC -->

## 1. "Character" Class with Unicode Utility Methods

原文：["Character" Class with Unicode Utility Methods](http://www.herongyang.com/Unicode/Java-Character-Class-with-Unicode-Utility-Methods.html)

This section provides an introduction on `java.lang.Character` class `static` methods added since `J2SE 5.0` as **Unicode utility methods**.



Since designers of `J2SE 5.0` did not change the internal storage size for the `java.lang.Character` class, it can not be used to support **Unicode supplementary characters** in the range of `U+0000` to `U+10FFFF`. So you should avoid using `java.lang.Character` class to represent a single Unicode character in the future to make your application Unicode-friendly.

However designers of `J2SE 5.0` did add a number of `static` methods in the `java.lang.Character` class as utility methods to help Unicode character processing. So take a look at some of them:

### 1.1. isXXX

- `static boolean isValidCodePoint(int codePoint)` - Determines whether the specified code point is a valid Unicode code point value.
- `static boolean isBmpCodePoint(int codePoint)` - Determines whether the specified character (Unicode code point) is in the Basic Multilingual Plane (BMP). Such code points can be represented using a single char.
- `static boolean isSupplementaryCodePoint(int codePoint)` - Determines whether the specified character (Unicode code point) is in the supplementary character range.
- `static boolean isDefined(int codePoint)` - Determines if a character (Unicode code point) is defined in Unicode. A character is defined if at least one of the following is true: it has an entry in the `UnicodeData` file or it has a value in a range defined by the `UnicodeData` file.
- `static boolean isDigit(int codePoint)` - Determines if the specified character (Unicode code point) is a digit. A character is a digit if its general category type, provided by `getType(codePoint)`, is `DECIMAL_DIGIT_NUMBER`.

### 1.2. char to CodePoint

- `static int toCodePoint(char high, char low)` - Converts the specified **surrogate pair** to its **supplementary code point value**. This method does not validate the specified surrogate pair. The caller must validate it using `isSurrogatePair` if necessary.
- `static int codePointAt(char[] a, int index)` - Returns the code point at the given `index` of the char array. If the char value at the given `index` in the char array is in the high-surrogate range, the following index is less than the length of the char array, and the char value at the following index is in the low-surrogate range, then the supplementary code point corresponding to this surrogate pair is returned. Otherwise, the char value at the given index is returned.

### 1.3. Code Point to char

- `static char highSurrogate(int codePoint)` - Returns the leading surrogate (a high surrogate code unit) of the surrogate pair representing the specified supplementary character (Unicode code point) in the UTF-16 encoding. If the specified character is not a supplementary character, an unspecified char is returned.
- `static char lowSurrogate(int codePoint)` - Returns the trailing surrogate (a low surrogate code unit) of the surrogate pair representing the specified supplementary character (Unicode code point) in the UTF-16 encoding. If the specified character is not a supplementary character, an unspecified char is returned.
- `static char[] toChars(int codePoint)` - Converts the specified character (Unicode code point) to its UTF-16 representation stored in a char array. If the specified code point is a **BMP** (Basic Multilingual Plane or Plane 0) value, the resulting char array has the same value as codePoint. If the specified code point is a **supplementary code point**, the resulting char array has the corresponding surrogate pair.

### 1.4. Trivial

- `static String getName(int codePoint)` - Returns the Unicode name of the specified character codePoint, or null if the code point is unassigned.

- `static int getNumericValue(int codePoint)` - Returns the int value that the specified character (Unicode code point) represents. For example, the character '\u216C' (the Roman numeral fifty) will return an int with a value of 50.

> 上面这个方法很特别，我觉得自己用不到。

- `static int getType(int codePoint)` - Returns a value indicating a character's general category.

## 2. Character.toChars() - char Sequence of Code Point

URL: [Character.toChars() - "char" Sequence of Code Point](http://www.herongyang.com/Unicode/Java-Character-toChars-char-Sequence.html)

This section provides tutorial example on how to test '`java.lang.Character`' class `toChars()` static methods to convert **Unicode code points** to **'char' sequences**, which is really identical to the byte sequences from the UTF-16BE encoding of the code point.

One interesting `static` method offered in the "`java.lang.Character`" class is the "`toChars(int codePoint)`" method, which always returns **"char" sequence** for any given Unicode character. It returns 1 "char" if a **BMP character** is given; and 2 "char"s if a **supplementary character** is given.

Here is a tutorial example on how to use "`toChars()`" and other related methods:

```java
public class UnicodeCharacterToChars {
    static int[] unicodeList = {0x43, 0x2103, 0x1F132, 0x1F1A0, 0x20FFFF};
    static char hexDigit[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public static void main(String[] arg) {
        try {
            for (int i = 0; i < unicodeList.length; i++) {

                // Starting with the code point value
                int codePoint = unicodeList[i];

                // Dumping data in HEX numbers
                System.out.print("\n");
                System.out.print("\n                 Code point: " + intToHex(codePoint));

                // Getting Unicode character basic properties
                System.out.print("\n         isValidCodePoint(): " + Character.isValidCodePoint(codePoint));
                System.out.print("\n                isDefined(): " + Character.isDefined(codePoint));
                System.out.print("\n                  getName(): " + Character.getName(codePoint));
                System.out.print("\n                  Character: " + new String(Character.toChars(codePoint)));
                System.out.print("\n           isBmpCodePoint(): " + Character.isBmpCodePoint(codePoint));
                System.out.print("\n isSupplementaryCodePoint(): " + Character.isSupplementaryCodePoint(codePoint));
                System.out.print("\n                charCount(): " + Character.charCount(codePoint));

                // Getting surrogate char pair
                char charHigh = Character.highSurrogate(codePoint);
                char charLow = Character.lowSurrogate(codePoint);
                System.out.print("\n            highSurrogate(): " + charToHex(charHigh));
                System.out.print("\n             lowSurrogate(): " + charToHex(charLow));
                System.out.print("\n          isSurrogatePair(): " + Character.isSurrogatePair(charHigh, charLow));

                // Getting char sequence
                char[] charSeq = Character.toChars(codePoint);
                System.out.print("\n                  toChars():");
                for (int j = 0; j < charSeq.length; j++)
                    System.out.print(" " + charToHex(charSeq[j]));

                // Getting UTF-16BE byte sequence
                int[] intArray = {codePoint};
                String charString = new String(intArray, 0, 1);
                byte[] utf16Seq = charString.getBytes("UTF-16BE");
                System.out.print("\n     UTF-16BE byte sequence:");
                for (int j = 0; j < utf16Seq.length; j++)
                    System.out.print(" " + byteToHex(utf16Seq[j]));
            }
        } catch (Exception e) {
            System.out.print("\n" + e.toString());
        }
    }

    public static String byteToHex(byte b) {
        char[] a = {hexDigit[(b >> 4) & 0x0f], hexDigit[b & 0x0f]};
        return new String(a);
    }

    public static String charToHex(char c) {
        byte hi = (byte) (c >>> 8);
        byte lo = (byte) (c & 0xff);
        return byteToHex(hi) + byteToHex(lo);
    }

    public static String intToHex(int i) {
        char hi = (char) (i >>> 16);
        char lo = (char) (i & 0xffff);
        return charToHex(hi) + charToHex(lo);
    }
}
```

Compile and run it:

```txt

                 Code point: 00000043
         isValidCodePoint(): true
                isDefined(): true
                  getName(): LATIN CAPITAL LETTER C
                  Character: C
           isBmpCodePoint(): true
 isSupplementaryCodePoint(): false
                charCount(): 1
            highSurrogate(): D7C0
             lowSurrogate(): DC43
          isSurrogatePair(): false
                  toChars(): 0043
     UTF-16BE byte sequence: 00 43

                 Code point: 00002103
         isValidCodePoint(): true
                isDefined(): true
                  getName(): DEGREE CELSIUS
                  Character: ℃
           isBmpCodePoint(): true
 isSupplementaryCodePoint(): false
                charCount(): 1
            highSurrogate(): D7C8
             lowSurrogate(): DD03
          isSurrogatePair(): false
                  toChars(): 2103
     UTF-16BE byte sequence: 21 03

                 Code point: 0001F132
         isValidCodePoint(): true
                isDefined(): true
                  getName(): SQUARED LATIN CAPITAL LETTER C
                  Character: 🄲
           isBmpCodePoint(): false
 isSupplementaryCodePoint(): true
                charCount(): 2
            highSurrogate(): D83C
             lowSurrogate(): DD32
          isSurrogatePair(): true
                  toChars(): D83C DD32
     UTF-16BE byte sequence: D8 3C DD 32

                 Code point: 0001F1A0
         isValidCodePoint(): true
                isDefined(): false
                  getName(): null
                  Character: 🆠
           isBmpCodePoint(): false
 isSupplementaryCodePoint(): true
                charCount(): 2
            highSurrogate(): D83C
             lowSurrogate(): DDA0
          isSurrogatePair(): true
                  toChars(): D83C DDA0
     UTF-16BE byte sequence: D8 3C DD A0

                 Code point: 0020FFFF
         isValidCodePoint(): false
                isDefined(): false
java.lang.IllegalArgumentException
```

The output confirms that:

- The `isDefined(int codePoint)` should be the first method to call make sure that the given `int` value represents a defined Unicode code point.
- If `isDefined(int codePoint)` returns `false`, stop calling other static methods. Calling `Character` method with an undefined code point value, may result exceptions.
- Java can return **the character name** for each **defined** Unicode character.
- For **BMP characters**, `highSurrogate`(int codePoint) and `lowSurrogate`(int codePoint) return **invalid values**.
- For **supplementary characters**, `highSurrogate`(int codePoint) and `lowSurrogate`(int codePoint) return **a valid surrogate "char" pair**.
- The `toChars(int codePoint)` also returns the surrogate "char" pair with high surrogate "char" first for supplementary characters.
- **The "char" sequence** returned by `toChars(int codePoint)` is identical to **the byte sequence** returned from the **UTF-16BE encoding** for both **BMP** and **supplementary characters**.

## 3. Character.getNumericValue() - Numeric Value of Code Point

URL: [Character.getNumericValue() - Numeric Value of Code Point](http://www.herongyang.com/Unicode/Java-Character-getNumericValue-Numeric-Value.html)

This section provides tutorial example on how to test '`java.lang.Character`' class `getNumericValue()` static methods to obtain the numeric value associated with a given Unicode character.

One interecting static method offered in the "`java.lang.Character`" class is the "`getNumericValue(int codePoint)`" method, which returns a numeric value represented by the given Unicode character.

Here is a tutorial example on how to use "`getNumericValue()`" and other related methods:

```java
public class UnicodeCharacterNumeric {
    static int[] unicodeList = {0x37, 0x0667, 0x2166, 0x3286, 0x4E03, 0x1F108};

    public static void main(String[] arg) {
        try {
            for (int i = 0; i < unicodeList.length; i++) {

                // Starting with the code point value
                int codePoint = unicodeList[i];

                // Dumping data in HEX numbers
                System.out.print("\n");
                System.out.print("\n                 Code point: " + Integer.toHexString(codePoint).toUpperCase());

                // Getting Unicode character numeric values
                System.out.print("\n                isDefined(): " + Character.isDefined(codePoint));
                System.out.print("\n                  getName(): " + Character.getName(codePoint));
                System.out.print("\n                  Character: " + new String(Character.toChars(codePoint)));
                System.out.print("\n          getNumericValue(): " + Character.getNumericValue(codePoint));

                // Getting Unicode character type
                int intType = Character.getType(codePoint);
                System.out.print("\n                  getType(): " + intType);
                System.out.print("\n    is DECIMAL_DIGIT_NUMBER: " + (intType == Character.DECIMAL_DIGIT_NUMBER));
                System.out.print("\n                  isDigit(): " + Character.isDigit(codePoint));
            }
        } catch (Exception e) {
            System.out.print("\n" + e.toString());
        }
    }
}
```

Compile and run it:

```txt

                 Code point: 37
                isDefined(): true
                  getName(): DIGIT SEVEN
                  Character: 7
          getNumericValue(): 7
                  getType(): 9
    is DECIMAL_DIGIT_NUMBER: true
                  isDigit(): true

                 Code point: 667
                isDefined(): true
                  getName(): ARABIC-INDIC DIGIT SEVEN
                  Character: ٧
          getNumericValue(): 7
                  getType(): 9
    is DECIMAL_DIGIT_NUMBER: true
                  isDigit(): true

                 Code point: 2166
                isDefined(): true
                  getName(): ROMAN NUMERAL SEVEN
                  Character: Ⅶ
          getNumericValue(): 7
                  getType(): 10
    is DECIMAL_DIGIT_NUMBER: false
                  isDigit(): false

                 Code point: 3286
                isDefined(): true
                  getName(): CIRCLED IDEOGRAPH SEVEN
                  Character: ㊆
          getNumericValue(): 7
                  getType(): 11
    is DECIMAL_DIGIT_NUMBER: false
                  isDigit(): false

                 Code point: 4E03
                isDefined(): true
                  getName(): CJK UNIFIED IDEOGRAPHS 4E03
                  Character: 七
          getNumericValue(): -1
                  getType(): 5
    is DECIMAL_DIGIT_NUMBER: false
                  isDigit(): false

                 Code point: 1F108
                isDefined(): true
                  getName(): DIGIT SEVEN COMMA
                  Character: 🄈
          getNumericValue(): 7
                  getType(): 11
    is DECIMAL_DIGIT_NUMBER: false
                  isDigit(): false
```

Some interesting notes on the output:

- Many Unicode characters that are not defined as digits, but they have numeric values. This is understandable. For example, "DIGIT SEVEN COMMA"(🄈), 0x1F108, is not a digit. But it has a numeric value of 7.
- It is strange to see that "ARABIC-INDIC DIGIT SEVEN"(٧), 0x0667, has numeric value of `7` and is defined as a digit!
- For some reason, the Chinese digit seven(七), 0x4E03, has no numeric value and is not defined as a digit!


## 4. Character.getType()

`getType()` returns a constant that identifies the category of a character.

```java
Character.getType('0'); // 9 数字
Character.getType('a'); // 2 英文小写字母
Character.getType('A'); // 1 英文大写字母
Character.getType('宋'); // 5 汉字
Character.getType(' '); // 12 空格
Character.getType('\t'); // 15 Tab
```

关于上面的数字，可以参考下面的内容。

```java
public final class Character {
    /**
     * General category "Lu" in the Unicode specification.
     * Lu 应该是Letter upper的缩写
     */
    public static final byte UPPERCASE_LETTER = 1;

    /**
     * General category "Ll" in the Unicode specification.
     * Ll 应该是Letter lower的缩写
     */
    public static final byte LOWERCASE_LETTER = 2;

    /**
     * General category "Lo" in the Unicode specification.
     * Lo 应该是Letter other的缩写
     */
    public static final byte OTHER_LETTER = 5;

    /**
     * General category "Nd" in the Unicode specification.
     * Nd 应该是Number decimal的缩写
     */
    public static final byte DECIMAL_DIGIT_NUMBER        = 9;

    /**
     * General category "Zs" in the Unicode specification.
     */
    public static final byte SPACE_SEPARATOR = 12;

    /**
     * General category "Cc" in the Unicode specification.
     */
    public static final byte CONTROL = 15;
}
```
