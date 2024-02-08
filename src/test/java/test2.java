import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class test2 {
    @Test
    void test01(){
        assertEquals(0, StringCalculator.add_second_attempt(""));
        assertEquals(5, StringCalculator.add_second_attempt("5"));
        assertEquals(11, StringCalculator.add_second_attempt("5,6"));
    }

    @Test
    void test02(){
        assertEquals(21, StringCalculator.add_second_attempt("5,6,10"));
    }

    @Test
    void test03(){
        assertEquals(21, StringCalculator.add_second_attempt("5\n6,10"));
    }

    @Test
    void test04(){
        assertEquals(21, StringCalculator.add_second_attempt("//;\n5;6;10"));
    }
    @Test
    void test05(){
        Exception e = assertThrows(RuntimeException.class, ()->StringCalculator.add_second_attempt("//;\n5;6;-10"));
        assertEquals("Negatives not allowed: -10", e.getMessage());
    }

    @Test
    void test06() {
        assertEquals(63, StringCalculator.add_second_attempt("//;\n9;4;50;1001"));
        assertEquals(1063, StringCalculator.add_second_attempt("//;\n9;4;50;1000"));
    }


    @Test
    void test07(){
        assertEquals(21, StringCalculator.add_second_attempt("//;;;;\n5;;;;6;;;;10"));
    }
}
