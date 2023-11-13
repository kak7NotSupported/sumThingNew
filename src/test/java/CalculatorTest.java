import me.kak7.Main;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @Test
    void testSimpleAddition() {
        assertEquals("3", Main.calc("1 + 2"));
    }

    @Test
    void testRomanDivision() {
        assertEquals("II", Main.calc("VI / III"));
    }

    @Test
    void testExceptionForNegativeRoman() {
        assertThrows(Exception.class, () -> Main.calc("I - II"));
    }

    @Test
    void testExceptionForMixedNumbers() {
        assertThrows(Exception.class, () -> Main.calc("I + 1"));
    }

    @Test
    void testExceptionForInvalidInput() {
        assertThrows(Exception.class, () -> Main.calc("1"));
    }

    @Test
    void testExceptionForTooManyOperands() {
        assertThrows(Exception.class, () -> Main.calc("1 + 2 + 3"));
    }

    // Тесты для арабских чисел
    @Test
    void testArabicAddition() {
        assertEquals("5", Main.calc("2 + 3"));
    }

    @Test
    void testArabicSubtraction() {
        assertEquals("1", Main.calc("3 - 2"));
    }

    @Test
    void testArabicMultiplication() {
        assertEquals("6", Main.calc("2 * 3"));
    }

    @Test
    void testArabicDivision() {
        assertEquals("2", Main.calc("4 / 2"));
    }

    // Тесты для римских чисел
    @Test
    void testRomanAddition() {
        assertEquals("V", Main.calc("II + III"));
    }

    @Test
    void testRomanSubtraction() {
        assertEquals("I", Main.calc("III - II"));
    }

    // Тесты на граничные условия
    @Test
    void testOutOfBoundsArabic() {
        assertThrows(Exception.class, () -> Main.calc("11 + 2"));
    }

    @Test
    void testOutOfBoundsRoman() {
        assertThrows(Exception.class, () -> Main.calc("XI + II"));
    }

    // Тесты на невалидные данные
    @Test
    void testInvalidInput() {
        assertThrows(Exception.class, () -> Main.calc("abc"));
    }
}
