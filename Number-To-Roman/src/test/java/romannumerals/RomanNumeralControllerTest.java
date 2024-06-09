package romannumerals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
/**
 * Integration tests for the RomanNumeralController class.
 */
@SpringBootTest
@AutoConfigureMockMvc
public class RomanNumeralControllerTest {

    @Autowired
    private MockMvc mockMvc;
    /**
     * Tests the conversion of a valid integer to its Roman numeral representation.
     * @throws Exception if an error occurs during the test.
     */

    @Test
    public void testValidInput() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/romannumeral")
                        .param("query", "42")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.input").value("42"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.output").value("XLII"));
    }
    /**
     * Tests handling of invalid input values.
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void testInvalidInput() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/romannumeral")
                        .param("query", "invalid")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Invalid input. Please provide an integer between 1 and 3999."));
    }
    /**
     * Tests handling of out-of-range input values.
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void testOutOfRangeInput() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/romannumeral")
                        .param("query", "4000")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Invalid input. Please provide an integer between 1 and 3999."));
    }
    /**
     * Tests the conversion of a range of integers to their Roman numeral equivalents.
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void testValidRange() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/romannumeral")
                        .param("min", "1")
                        .param("max", "3")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.conversions[0].input").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.conversions[0].output").value("I"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.conversions[1].input").value("2"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.conversions[1].output").value("II"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.conversions[2].input").value("3"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.conversions[2].output").value("III"));
    }
    /**
     * Tests handling of invalid range parameters.
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void testInvalidRange() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/romannumeral")
                        .param("min", "10")
                        .param("max", "5")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Invalid range. Please provide integers between 1 and 3999 with min < max."));
    }
    /**
     * Tests handling of missing parameters.
     * @throws Exception if an error occurs during the test.
     */
    @Test
    public void testMissingParameters() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/romannumeral")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("Invalid input. Please provide either a single query or both min and max parameters."));
    }
}
