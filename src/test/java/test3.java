import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class test3 {
    @Test
    void test01(){
        assertEquals(0, StringCalculator.add(""));
        assertEquals(2, StringCalculator.add("2"));
        assertEquals(4, StringCalculator.add("2,2"));
    }

    @Test
    void test02(){
        assertEquals(9, StringCalculator.add("2,2,5"));
    }

    @Test
    void test03(){
        assertEquals(9, StringCalculator.add("2,2\n5"));
    }

    @Test
    void test04(){
        assertEquals(9, StringCalculator.add("//;\n2;2;5"));
    }

    @Test
    void test05(){
        Exception e = assertThrows(RuntimeException.class, ()->StringCalculator.add("2,5,-4,5,-9"));
        assertEquals("Negatives not allowed: -4,-9", e.getMessage());
    }

    @Test
    void test06(){
        assertEquals(1009, StringCalculator.add("//;\n2;2;5;1000"));
        assertEquals(9, StringCalculator.add("//;\n2;2;5;1001"));
    }

    @Test
    void test07(){
        assertEquals(1009, StringCalculator.add("//[;;;;]\n2;;;;2;;;;5;;;;1000"));
        assertEquals(9, StringCalculator.add("//[||]\n2||2||5||1001"));
    }

    @Test
    void test08(){
        assertEquals(1009, StringCalculator.add("//[%][|]\n2%2|5|1000"));
    }

    @Test
    void test09(){
        assertEquals(1009, StringCalculator.add("//[%%][||]\n2%%2||5||1000"));
        assertEquals(1009, StringCalculator.add("//[[[[][||]\n2[[[2||5||1000"));
        assertEquals(1009, StringCalculator.add("//[]]]][||]\n2]]]2||5||1000"));
    }
}
