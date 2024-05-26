package id.ac.ui.cs.advprog.eshop.voucher.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class VoucherTest {

    @Test
    void testVoucherBuilderWithRequiredParameters() {
        Voucher voucher = new Voucher.VoucherBuilder("1", "Test Voucher", "Description", 10.0)
                .build();

        assertNotNull(voucher);
        assertEquals("1", voucher.getVoucherId());
        assertEquals("Test Voucher", voucher.getVoucherName());
        assertEquals("Description", voucher.getVoucherDescription());
        assertEquals(10.0, voucher.getVoucherDiscount());
        assertNull(voucher.getVoucherType());
        assertNull(voucher.getVoucherStartDate());
        assertNull(voucher.getVoucherEndDate());
        assertEquals(0, voucher.getVoucherUsageLimit());
    }

    @Test
    void testVoucherBuilderWithAllParameters() {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = LocalDate.now().plusDays(10);

        Voucher voucher = new Voucher.VoucherBuilder("1", "Test Voucher", "Description", 10.0)
                .setType("Expired Date and Usage Limit")
                .setVoucherDate(startDate, endDate)
                .setUsageLimit(100)
                .build();

        assertNotNull(voucher);
        assertEquals("1", voucher.getVoucherId());
        assertEquals("Test Voucher", voucher.getVoucherName());
        assertEquals("Description", voucher.getVoucherDescription());
        assertEquals(10.0, voucher.getVoucherDiscount());
        assertEquals("Expired Date and Usage Limit", voucher.getVoucherType());
        assertEquals(startDate, voucher.getVoucherStartDate());
        assertEquals(endDate, voucher.getVoucherEndDate());
        assertEquals(100, voucher.getVoucherUsageLimit());
    }

    @Test
    void testVoucherBuilderWithNullValues() {
        Voucher voucher = new Voucher.VoucherBuilder("1", "Test Voucher", "Description", 10.0)
                .setType(null)
                .setVoucherDate(null, null)
                .setUsageLimit(0)
                .build();

        assertNotNull(voucher);
        assertEquals("1", voucher.getVoucherId());
        assertEquals("Test Voucher", voucher.getVoucherName());
        assertEquals("Description", voucher.getVoucherDescription());
        assertEquals(10.0, voucher.getVoucherDiscount());
        assertNull(voucher.getVoucherType());
        assertNull(voucher.getVoucherStartDate());
        assertNull(voucher.getVoucherEndDate());
        assertEquals(0, voucher.getVoucherUsageLimit());
    }

    @Test
    void testVoucherBuilderMissingRequiredParameters() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            new Voucher.VoucherBuilder("1", null, "Description", 10.0).build();
        });

        String expectedMessage = "voucherName";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testVoucherBuilderLessThanZeroDiscount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Voucher.VoucherBuilder("1", "Test Voucher", "Description", -10.0).build();
        });

        String expectedMessage = "voucherDiscount";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testVoucherBuilderMoreThanHundredDiscount() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Voucher.VoucherBuilder("1", "Test Voucher", "Description", 110.0).build();
        });

        String expectedMessage = "voucherDiscount";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testVoucherBuilderWithEmptyName() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            new Voucher.VoucherBuilder("1", "", "Description", 10.0).build();
        });

        String expectedMessage = "voucherName";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void testVoucherBuilderWithEmptyDescription() {
        Exception exception = assertThrows(NullPointerException.class, () -> {
            new Voucher.VoucherBuilder("1", "Test Voucher", "", 10.0).build();
        });

        String expectedMessage = "voucherDescription";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

}
