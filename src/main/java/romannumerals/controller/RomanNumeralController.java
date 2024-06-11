package romannumerals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import romannumerals.service.RomanNumeralService;
import romannumerals.util.RomanNumeralConverterUtil;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static romannumerals.util.RomanNumeralConverterUtil.buildResponse;

/**
 * Controller class for Roman numeral conversion.
 */
@RestController
public class RomanNumeralController {

    private final RomanNumeralService romanNumeralService;

    @Autowired
    public RomanNumeralController(RomanNumeralService romanNumeralService) {
        this.romanNumeralService = romanNumeralService;
    }
    /**
     * Endpoint for converting a single integer to a Roman numeral.
     * @param query The integer value to convert.
     * @return ResponseEntity containing the input value and its Roman numeral equivalent.
     */
    @GetMapping("/romannumeral")
    public ResponseEntity<?> getRomanNumeral(@RequestParam(required = false) String query,
                                             @RequestParam(required = false) Integer min,
                                             @RequestParam(required = false) Integer max) {
        try {
            if (query != null) {
                int num = RomanNumeralConverterUtil.validateAndParseInput(query);
                String roman = romanNumeralService.convertToRoman(num);
                return ResponseEntity.ok(buildResponse(num, roman));
            } else if (min != null && max != null) {
                RomanNumeralConverterUtil.validateRange(min, max);
                List<Map<String, String>> conversions = convertRangeToRoman(min, max);
                return ResponseEntity.ok(RomanNumeralConverterUtil.buildRangeResponse(conversions));
            } else {
                throw new IllegalArgumentException("Invalid input. Please provide either a single query or both min and max parameters.");
            }
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An unexpected error occurred");
        }
    }

    private List<Map<String, String>> convertRangeToRoman(int min, int max) {
        return IntStream.rangeClosed(min, max)
                .parallel()
                .mapToObj(num -> buildResponse(num, romanNumeralService.convertToRoman(num)))
                .collect(Collectors.toList());
    }
}
