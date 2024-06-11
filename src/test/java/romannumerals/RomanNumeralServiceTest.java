package romannumerals;

import org.junit.jupiter.api.Test;
import romannumerals.service.RomanNumeralService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RomanNumeralServiceTest {

    private final RomanNumeralService romanNumeralService = new RomanNumeralService();

    @Test
    public void testConvertToRoman() {
        assertEquals("I", romanNumeralService.convertToRoman(1));
        assertEquals("IV", romanNumeralService.convertToRoman(4));
        assertEquals("IX", romanNumeralService.convertToRoman(9));
        assertEquals("XLII", romanNumeralService.convertToRoman(42));
        assertEquals("XCIX", romanNumeralService.convertToRoman(99));
        assertEquals("CDXLIV", romanNumeralService.convertToRoman(444));
        assertEquals("CMXCIX", romanNumeralService.convertToRoman(999));
        assertEquals("MMMCMXCIX", romanNumeralService.convertToRoman(3999));
    }

    @Test
    public void testOutOfRangeInput() {
        // Test if the service throws IllegalArgumentException for out-of-range input
        assertThrows(IllegalArgumentException.class, () -> {
            romanNumeralService.convertToRoman(0);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            romanNumeralService.convertToRoman(4000);
        });
    }
}
