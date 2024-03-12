import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


class AppTest {
    @Test
    void unitTest1() {
        assertEquals(1, 1); // True
    }

    @Test
    void unitTest2() {
        //assertEquals(1, 2); // False
    }

    @Test
    void unitTest3() {
        assertTrue(1 == 1); // True
    }

    @Test
    void unitTest4() {
        //assertTrue(1 == 2); // False
    }

    @Test
    void unitTest5() {
        assertEquals(5.0, 5.02, 0.02); // True
    }

    @Test
    void unitTest6() {
        //assertEquals(5.0, 5.02, 0.01); // False
    }
}