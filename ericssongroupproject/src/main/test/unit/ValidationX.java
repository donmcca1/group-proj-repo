package unit;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.ericsson.group.utilities.Validation;
import org.junit.Test;

public class ValidationX {
    @Test
    public void testValidateLine_valid() {
        // Given
        //ValidationOld validation = new ValidationOld(baseData, causeCodeData, mccMncData, ueFileData, failureClassData);
        Validation validation = new Validation();
        String[] line = {
                "1/11/2013 17:15", // Date / Time
                "4098",            // Event Id
                "1",               // Failure Class
                "21060800",        // UE Type
                "344",             // Market
                "930",             // Operator
                "4",               // Cell Id
                "1000",            // Duration
                "0",               // Cause Code
                "11B",             // NE Version
                "344930000000011"  // IMSI
        };

        // When
        boolean valid = validation.validateLine(line);

        // Then
        assertTrue(valid);
    }

    @Test
    public void testValidateLine_invalidCellId() {
        // Given
        //ValidationOld validation = new ValidationOld(baseData, causeCodeData, mccMncData, ueFileData, failureClassData);
        Validation validation = new Validation();
        String[] line = {
                "1/11/2013 17:15", // Date / Time
                "4098",            // Event Id
                "1",               // Failure Class
                "21060800",        // UE Type
                "344",             // Market
                "930",             // Operator
                "4A",              // Cell Id  <-- Should be an integer
                "1000",            // Duration
                "0",               // Cause Code
                "11B",             // NE Version
                "344930000000011"  // IMSI
        };

        // When
        boolean valid = validation.validateLine(line);

        // Then
        assertFalse(valid);
    }

    @Test
    public void testValidateLine_invalidDuration() {
        // Given
        //ValidationOld validation = new ValidationOld(baseData, causeCodeData, mccMncData, ueFileData, failureClassData);
        Validation validation = new Validation();
        String[] line = {
                "1/11/2013 17:15", // Date / Time
                "4098",            // Event Id
                "1",               // Failure Class
                "21060800",        // UE Type
                "344",             // Market
                "930",             // Operator
                "4",               // Cell Id
                "1000L",           // Duration  <-- Should be an integer
                "0",               // Cause Code
                "11B",             // NE Version
                "344930000000011"  // IMSI
        };

        // When
        boolean valid = validation.validateLine(line);

        // Then
        assertFalse(valid);
    }

    @Test
    public void testValidateLine_invalidImsiNotOnlyDigits() {
        // Given
        //ValidationOld validation = new ValidationOld(baseData, causeCodeData, mccMncData, ueFileData, failureClassData);
        Validation validation = new Validation();
        String[] line = {
                "1/11/2013 17:15", // Date / Time
                "4098",            // Event Id
                "1",               // Failure Class
                "21060800",        // UE Type
                "344",             // Market
                "930",             // Operator
                "4",               // Cell Id
                "1000L",           // Duration
                "0",               // Cause Code
                "11B",             // NE Version
                "344930000/00011"  // IMSI  <-- Should contain 15 digits
        };

        // When
        boolean valid = validation.validateLine(line);

        // Then
        assertFalse(valid);
    }

    @Test
    public void testValidateLine_invalidImsiTooLong() {
        // Given
        //ValidationOld validation = new ValidationOld(baseData, causeCodeData, mccMncData, ueFileData, failureClassData);
        Validation validation = new Validation();
        String[] line = {
                "1/11/2013 17:15", // Date / Time
                "4098",            // Event Id
                "1",               // Failure Class
                "21060800",        // UE Type
                "344",             // Market
                "930",             // Operator
                "4",               // Cell Id
                "1000L",           // Duration
                "0",               // Cause Code
                "11B",             // NE Version
                "3449300000000111" // IMSI  <-- Should contain 15 digits
        };

        // When
        boolean valid = validation.validateLine(line);

        // Then
        assertFalse(valid);
    }

    @Test
    public void testValidateLine_invalidImsiTooShort() {
        // Given
        //ValidationOld validation = new ValidationOld(baseData, causeCodeData, mccMncData, ueFileData, failureClassData);
        Validation validation = new Validation();
        String[] line = {
                "1/11/2013 17:15", // Date / Time
                "4098",            // Event Id
                "1",               // Failure Class
                "21060800",        // UE Type
                "344",             // Market
                "930",             // Operator
                "4",               // Cell Id
                "1000L",           // Duration
                "0",               // Cause Code
                "11B",             // NE Version
                "34493000000001"   // IMSI  <-- Should contain 15 digits
        };

        // When
        boolean valid = validation.validateLine(line);

        // Then
        assertFalse(valid);
    }

    @Test
    public void testValidateLine_invalidImsiDoesNotMatchOperatorAndMarket() {
        // Given
        //ValidationOld validation = new ValidationOld(baseData, causeCodeData, mccMncData, ueFileData, failureClassData);
        Validation validation = new Validation();
        String[] line = {
                "1/11/2013 17:15", // Date / Time
                "4098",            // Event Id
                "1",               // Failure Class
                "21060800",        // UE Type
                "310",             // Market
                "560",             // Operator
                "4",               // Cell Id
                "1000L",           // Duration
                "0",               // Cause Code
                "11B",             // NE Version
                "34493000000001"   // IMSI  <-- Should contain 15 digits
        };

        // When
        boolean valid = validation.validateLine(line);

        // Then
        assertFalse(valid);
    }

    @Test
    public void testValidateLine_invalidDate() {
        // Given
        //ValidationOld validation = new ValidationOld(baseData, causeCodeData, mccMncData, ueFileData, failureClassData);
        Validation validation = new Validation();
        String[] line = {
                "30/02/2013 17:15", // Date / Time
                "4098",             // Event Id
                "1",                // Failure Class
                "21060800",         // UE Type
                "344",              // Market
                "930",              // Operator
                "4",                // Cell Id
                "1000",             // Duration
                "0",                // Cause Code
                "11B",              // NE Version
                "344930000000011"   // IMSI
        };

        // When
        boolean valid = validation.validateLine(line);

        // Then
        assertFalse(valid);
    }

}