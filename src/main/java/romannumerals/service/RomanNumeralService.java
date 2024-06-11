package romannumerals.service;

import org.springframework.stereotype.Service;
import romannumerals.util.RomanNumeralConverterUtil;
/**
 * Service class for converting integers to Roman numerals.
 */

@Service
public class RomanNumeralService {
    /**
     * Converts an integer value to its Roman numeral representation.
     * @param num The integer value to convert.
     * @return The Roman numeral representation of the input value.
     * @throws IllegalArgumentException if the input value is not within the range [1, 3999].
     */
    public String convertToRoman(int num) {
        if (num < 1 || num > 3999) {
            throw new IllegalArgumentException("Input value must be between 1 and 3999.");
        }
        return RomanNumeralConverterUtil.intToRoman(num);
    }
}
