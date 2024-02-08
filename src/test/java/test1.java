import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class test1 {
    @Test
    void test01() {
        assertEquals(0, StringCalculator.add_first_attempt(""));
    }

    @Test
    void test02() {
        assertEquals(9, StringCalculator.add_first_attempt("9"));
    }

    @Test
    void test03() {
        assertEquals(54, StringCalculator.add_first_attempt("9,45"));
    }

    @Test
    void test04() {
        assertEquals(63, StringCalculator.add_first_attempt("9,4,50"));
    }

    @Test
    void test05() {
        assertEquals(63, StringCalculator.add_first_attempt("9\n4,50"));
    }

    @Test
    void test06() {
        assertEquals(63, StringCalculator.add_first_attempt("//;\n9;4;50"));
    }

    @Test
    void test07() {
        Exception e = assertThrows(RuntimeException.class, ()->StringCalculator.add_first_attempt("//;\n9;-4;50;-7"));
        assertEquals("Negatives not allowed: -4,-7", e.getMessage());
    }

    @Test
    void test08() {
        assertEquals(1063, StringCalculator.add_first_attempt("//;\n9;4;50;1000"));

        assertEquals(63, StringCalculator.add_first_attempt("//;\n9;4;50;1001"));
    }

}
