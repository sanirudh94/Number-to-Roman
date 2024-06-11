package romannumerals.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/**
 * Utility class for performing common operations related to Roman numerals.
 */
public class RomanNumeralConverterUtil {
    private static final Map<Integer, String> romanNumeralMap = new LinkedHashMap<>();

    static {
        romanNumeralMap.put(1000, "M");
        romanNumeralMap.put(900, "CM");
        romanNumeralMap.put(500, "D");
        romanNumeralMap.put(400, "CD");
        romanNumeralMap.put(100, "C");
        romanNumeralMap.put(90, "XC");
        romanNumeralMap.put(50, "L");
        romanNumeralMap.put(40, "XL");
        romanNumeralMap.put(10, "X");
        romanNumeralMap.put(9, "IX");
        romanNumeralMap.put(5, "V");
        romanNumeralMap.put(4, "IV");
        romanNumeralMap.put(1, "I");
    }
    /**
     * Converts an integer value to its Roman numeral representation.
     * @param num The integer value to convert.
     * @return The Roman numeral representation of the input value.
     * @throws IllegalArgumentException if the input value is not within the range [1, 3999].
     */
    public static String intToRoman(int num) {
        StringBuilder roman = new StringBuilder();
        for (Map.Entry<Integer, String> entry : romanNumeralMap.entrySet()) {
            while (num >= entry.getKey()) {
                roman.append(entry.getValue());
                num -= entry.getKey();
            }
        }
        return roman.toString();
    }
    /**
     * Validates and parses the input query string.
     * @param query The query string to validate and parse.
     * @return The parsed integer value.
     * @throws IllegalArgumentException If the input is not a valid integer or is out of range.
     */
    public static int validateAndParseInput(String query) {
        if (!query.matches("\\d+")) {
            throw new IllegalArgumentException("Invalid input. Please provide an integer between 1 and 3999.");
        }
        int num = Integer.parseInt(query);
        if (num < 1 || num > 3999) {
            throw new IllegalArgumentException("Invalid input. Please provide an integer between 1 and 3999.");
        }
        return num;
    }

    public static void validateRange(int min, int max) {
        if (min < 1 || max > 3999 || min >= max) {
            throw new IllegalArgumentException("Invalid range. Please provide integers between 1 and 3999 with min < max.");
        }
    }
    /**
     * Builds a response entity containing the input value and its Roman numeral equivalent.
     * @param num The input integer value.
     * @param roman The Roman numeral equivalent.
     * @return ResponseEntity containing the input value and its Roman numeral.
     */
    public static Map<String, String> buildResponse(int num, String roman) {
        Map<String, String> response = new HashMap<>();
        response.put("input", String.valueOf(num));
        response.put("output", roman);
        return response;
    }

    /**
     * Builds a response entity containing a list of conversions from integer values to their Roman numeral equivalents.
     * @param conversions The list of conversions to be included in the response.
     * @return ResponseEntity containing the list of conversions.
     */
    public static Map<String, List<Map<String, String>>> buildRangeResponse(List<Map<String, String>> conversions) {
        // Create a map to hold the response data
        Map<String, List<Map<String, String>>> response = new HashMap<>();

        // Add the list of conversions to the response map
        response.put("conversions", conversions);

        // Return the response map
        return response;
    }

}
