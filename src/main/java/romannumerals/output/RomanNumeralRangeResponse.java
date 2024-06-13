package romannumerals.output;

import java.util.List;

/**
 * Represents the response for a range of Roman numeral conversions.
 * It contains a list of successful conversions.
 */
public class RomanNumeralRangeResponse {
    private final List<RomanNumeralResponse> conversions;
    /**
     * Constructs a response with a list of successful conversions.
     *
     * @param conversions The list of Roman numeral conversions.
     */

    public RomanNumeralRangeResponse(List<RomanNumeralResponse> conversions) {
        this.conversions = conversions;
    }

    public List<RomanNumeralResponse> getConversions() {
        return conversions;
    }
}