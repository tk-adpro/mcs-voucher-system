package id.ac.ui.cs.advprog.eshop.voucher.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
class VoucherTest {
    Voucher voucher;
    @BeforeEach
    void setUp(){
        this.voucher = new Voucher();

        this.voucher.setVoucherId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        this.voucher.setVoucherName("Gratis Ongkir");
        this.voucher.setVoucherDescription("Syarat dan Ketentuan Berlaku");
        this.voucher.setVoucherDiscount(50);
        this.voucher.setVoucherUsageLimit(50);

        LocalDate startDate = LocalDate.of(2024, 4, 24);
        this.voucher.setVoucherStartDate(startDate);

        LocalDate endDate = LocalDate.of(2024, 5, 24);
        this.voucher.setVoucherEndDate(endDate);
    }
    @Test
    void testGetVoucherId() {
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", this.voucher.getVoucherId());
    }

    @Test
    void testGetVoucherName() {
        assertEquals("Gratis Ongkir", this.voucher.getVoucherName());
    }

    @Test
    void testGetVoucherDescription() {
        assertEquals("Syarat dan Ketentuan Berlaku", this.voucher.getVoucherDescription());
    }

    @Test
    void testGetVoucherDiscount() {
        assertEquals(50, this.voucher.getVoucherDiscount());
    }

    @Test
    void testGetVoucherUsageLimit() {
        assertEquals(50, this.voucher.getVoucherUsageLimit());
    }

    @Test
    void testGetVoucherStartDate() {
        LocalDate startDate = LocalDate.of(2024, 4, 24);
        assertEquals(startDate, this.voucher.getVoucherStartDate());
    }

    @Test
    void testGetVoucherEndDate() {
        LocalDate endDate = LocalDate.of(2024, 5, 24);
        assertEquals(endDate, this.voucher.getVoucherEndDate());
    }
}
