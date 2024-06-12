package romannumerals.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import romannumerals.output.RomanNumeralResponse;
import romannumerals.service.RomanNumeralService;
import romannumerals.util.RomanNumeralConverterUtil;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
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
                List<RomanNumeralResponse> conversions = convertRangeToRoman(min, max);
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

    private List<RomanNumeralResponse> convertRangeToRoman(int min, int max) throws RuntimeException{
        //Using synchronizedList to make sure that the list is thread-safe since multiple threads will be adding elements to it concurrently
        List<RomanNumeralResponse> conversions = Collections.synchronizedList(new ArrayList<>());
        // ExecutorService to execute conversions in parallel. Using a thread pool size of 10 allows for
        // 10 concurrent tasks to be executed in parallel.
        ExecutorService executor = Executors.newFixedThreadPool(10);
        for (int i = min; i <= max; i++) {
            final int num = i;
            Runnable task = () -> {
                String roman = romanNumeralService.convertToRoman(num);
                conversions.add(new RomanNumeralResponse(String.valueOf(num), roman));
            };
            executor.execute(task);
        }
        executor.shutdown();
        try {
            // Used to wait for the executor to terminate, which means waiting for all tasks to complete.
            executor.awaitTermination(1, TimeUnit.DAYS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // restore interrupt status
            // Cleanup
            throw new RuntimeException("Interrupted while waiting for executor to terminate", e);
        }
        Collections.sort(conversions, Comparator.comparingInt(r -> Integer.parseInt(r.getInput())));
        return conversions;
    }
}
