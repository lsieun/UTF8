# StandardCharsets

## Standard charsets

Every implementation of the Java platform is required to support the following **standard charsets**.

| Charset        | Description                                                  |
| -------------- | ------------------------------------------------------------ |
| `US-ASCII`     | Seven-bit ASCII, a.k.a. `ISO646-US`, a.k.a. the Basic Latin block of the Unicode character set |
| `ISO-8859-1  ` | ISO Latin Alphabet No. 1, a.k.a. `ISO-LATIN-1`               |
| `UTF-8`        | Eight-bit UCS Transformation Format                          |
| `UTF-16BE`     | Sixteen-bit UCS Transformation Format, big-endian byte order |
| `UTF-16LE`     | Sixteen-bit UCS Transformation Format, little-endian byte order |
| `UTF-16`       | Sixteen-bit UCS Transformation Format, byte order identified by an optional byte-order mark |

The `StandardCharsets` class defines constants for each of the standard charsets.

Every instance of the **Java virtual machine** has **a default charset**, which may or may not be one of the standard charsets. **The default charset** is determined during virtual-machine startup and typically depends upon the **locale** and **charset** being used by the underlying operating system.
