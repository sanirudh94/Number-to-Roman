package romannumerals.output;

/**
 * Represents a single Roman numeral conversion response.
 * It contains the input integer as a string and the corresponding Roman numeral output.
 */
public class RomanNumeralResponse {
    private final String input;
    private final String output;
    /**
     * Constructs a RomanNumeralResponse with the specified input and output.
     *
     * @param input  The input integer value as a string.
     * @param output The Roman numeral equivalent of the input value.
     */
    public RomanNumeralResponse(String input, String output) {
        this.input = input;
        this.output = output;
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }
}

