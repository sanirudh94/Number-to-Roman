import romannumerals.output.RomanNumeralResponse;

import java.util.List;

public class RomanNumeralRangeResponse {
    private String error;
    private List<RomanNumeralResponse> conversions;

    public RomanNumeralRangeResponse(String error, String message) {
        this.error = error;
    }

    public RomanNumeralRangeResponse(List<RomanNumeralResponse> conversions) {
        this.conversions = conversions;
    }

    public String getError() {
        return error;
    }

    public List<RomanNumeralResponse> getConversions() {
        return conversions;
    }
}