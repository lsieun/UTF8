package lsieun.computer.basic;

import java.nio.ByteOrder;

public class Endian {
    public static void main(String[] args) {
        ByteOrder byteOrder = ByteOrder.nativeOrder();
        System.out.println(byteOrder);
    }
}
