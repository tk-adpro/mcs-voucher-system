package id.ac.ui.cs.advprog.eshop.voucher.repository;

import id.ac.ui.cs.advprog.eshop.voucher.model.Voucher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {
    @InjectMocks
    VoucherRepository voucherRepository;
    @BeforeEach
    void setup() {
    }
    @Test
    void testCreateVoucher() {
        // Create Voucher
        Voucher voucher = new Voucher();

        voucher.setVoucherId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        voucher.setVoucherName("Gratis Ongkir");
        voucher.setVoucherDescription("Syarat dan Ketentuan Berlaku");
        voucher.setVoucherDiscount(50);
        voucher.setVoucherUsageLimit(50);

        LocalDate startDate = LocalDate.of(2024, 4, 24);
        voucher.setVoucherStartDate(startDate);

        LocalDate endDate = LocalDate.of(2024, 5, 24);
        voucher.setVoucherEndDate(endDate);
        voucherRepository.createVoucher(voucher);

        // Find Voucher
        Iterator<Voucher> voucherIterator = voucherRepository.findAllVoucher();
        assertTrue(voucherIterator.hasNext());

        Voucher savedVoucher = voucherIterator.next();
        assertEquals(voucher.getVoucherId(), savedVoucher.getVoucherId());
        assertEquals(voucher.getVoucherName(), savedVoucher.getVoucherName());
        assertEquals(voucher.getVoucherDescription(), savedVoucher.getVoucherDescription());
        assertEquals(voucher.getVoucherUsageLimit(), savedVoucher.getVoucherUsageLimit());
        assertEquals(voucher.getVoucherStartDate(), savedVoucher.getVoucherStartDate());
        assertEquals(voucher.getVoucherEndDate(), savedVoucher.getVoucherEndDate());
    }

    @Test
    void testCreateVoucher_IfUsageLimitIsNegative(){
        // Create Invalid Voucher
        Voucher voucher = new Voucher();

        voucher.setVoucherId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        voucher.setVoucherName("Diskon Scam");
        voucher.setVoucherDescription("Syarat dan Ketentuan Berlaku");
        voucher.setVoucherDiscount(50);
        voucher.setVoucherUsageLimit(-1);

        LocalDate startDate = LocalDate.of(2024, 4, 24);
        voucher.setVoucherStartDate(startDate);

        LocalDate endDate = LocalDate.of(2024, 5, 24);
        voucher.setVoucherEndDate(endDate);

        assertThrows(IllegalArgumentException.class, () -> voucherRepository.createVoucher(voucher));
    }

    @Test
    void testCreateVoucher_IfDiscountInvalid(){
        // Create Invalid Voucher
        Voucher voucher = new Voucher();

        voucher.setVoucherId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        voucher.setVoucherName("Diskon Scam");
        voucher.setVoucherDescription("Syarat dan Ketentuan Berlaku");
        voucher.setVoucherDiscount(200);
        voucher.setVoucherUsageLimit(50);

        LocalDate startDate = LocalDate.of(2024, 4, 24);
        voucher.setVoucherStartDate(startDate);

        LocalDate endDate = LocalDate.of(2024, 5, 24);
        voucher.setVoucherEndDate(endDate);

        assertThrows(IllegalArgumentException.class, () -> voucherRepository.createVoucher(voucher));
    }

    @Test
    void testCreateProduct_IfProductIdIsNull(){
        // Create Invalid Voucher
        Voucher voucher = new Voucher();

        voucher.setVoucherId(null);
        voucher.setVoucherName("Diskon Scam");
        voucher.setVoucherDescription("Syarat dan Ketentuan Berlaku");
        voucher.setVoucherDiscount(50);
        voucher.setVoucherUsageLimit(50);

        LocalDate startDate = LocalDate.of(2024, 4, 24);
        voucher.setVoucherStartDate(startDate);

        LocalDate endDate = LocalDate.of(2024, 5, 24);
        voucher.setVoucherEndDate(endDate);

        voucherRepository.createVoucher(voucher);

        assertNotNull(voucher.getVoucherId());
    }

    @Test
    void testFindAllVoucher_IfEmpty() {
        Iterator<Voucher> productIterator = voucherRepository.findAllVoucher();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllVoucher_IfMoreThanOneVoucher() {
        // Create First Voucher
        Voucher firstVoucher = new Voucher();

        firstVoucher.setVoucherId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        firstVoucher.setVoucherName("Gratis Ongkir");
        firstVoucher.setVoucherDescription("Syarat dan Ketentuan Berlaku");
        firstVoucher.setVoucherDiscount(50);
        firstVoucher.setVoucherUsageLimit(50);

        LocalDate StartDate = LocalDate.of(2024, 4, 24);
        firstVoucher.setVoucherStartDate(StartDate);

        LocalDate EndDate = LocalDate.of(2024, 5, 24);
        firstVoucher.setVoucherEndDate(EndDate);
        voucherRepository.createVoucher(firstVoucher);

        // Create Second Voucher
        Voucher secondVoucher = new Voucher();

        secondVoucher.setVoucherId("cfc0ff53-8c04-462b-a44a-7ee4e98e5f71");
        secondVoucher.setVoucherName("Diskon 100%");
        secondVoucher.setVoucherDescription("Syarat dan Ketentuan Berlaku");
        secondVoucher.setVoucherDiscount(100);
        secondVoucher.setVoucherUsageLimit(10);

        StartDate = LocalDate.of(2024, 4, 24);
        secondVoucher.setVoucherStartDate(StartDate);

        EndDate = LocalDate.of(2024, 4, 25);
        secondVoucher.setVoucherEndDate(EndDate);

        voucherRepository.createVoucher(secondVoucher);

        // Find Vouchers
        Iterator<Voucher> voucherIterator = voucherRepository.findAllVoucher();
        assertTrue(voucherIterator.hasNext());

        Voucher savedVoucher = voucherIterator.next();
        assertEquals(firstVoucher.getVoucherId(), savedVoucher.getVoucherId());

        savedVoucher = voucherIterator.next();
        assertEquals(secondVoucher.getVoucherId(), savedVoucher.getVoucherId());

        assertFalse(voucherIterator.hasNext());
    }

    @Test
    void testGetVoucherById() {
        // Create Voucher
        Voucher voucher = new Voucher();

        voucher.setVoucherId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        voucher.setVoucherName("Gratis Ongkir");
        voucher.setVoucherDescription("Syarat dan Ketentuan Berlaku");
        voucher.setVoucherDiscount(50);
        voucher.setVoucherUsageLimit(50);

        LocalDate startDate = LocalDate.of(2024, 4, 24);
        voucher.setVoucherStartDate(startDate);

        LocalDate endDate = LocalDate.of(2024, 5, 24);
        voucher.setVoucherEndDate(endDate);
        voucherRepository.createVoucher(voucher);

        // Find By ID
        Voucher savedVoucher = voucherRepository.getVoucherById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertEquals(voucher.getVoucherId(), savedVoucher.getVoucherId());
        assertEquals(voucher.getVoucherName(), savedVoucher.getVoucherName());
        assertEquals(voucher.getVoucherDescription(), savedVoucher.getVoucherDescription());
        assertEquals(voucher.getVoucherUsageLimit(), savedVoucher.getVoucherUsageLimit());
        assertEquals(voucher.getVoucherStartDate(), savedVoucher.getVoucherStartDate());
        assertEquals(voucher.getVoucherEndDate(), savedVoucher.getVoucherEndDate());
    }

    @Test
    void testGetVoucherById_IfVoucherNotFound() {
        voucherRepository.createVoucher(new Voucher());

        Voucher savedVoucher = voucherRepository.getVoucherById("randomProductId");
        assertNull(savedVoucher);
    }

    @Test
    void testEditVoucher(){
        // Create Voucher
        Voucher voucher = new Voucher();

        voucher.setVoucherId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        voucher.setVoucherName("Gratis Ongkir");
        voucher.setVoucherDescription("Syarat dan Ketentuan Berlaku");
        voucher.setVoucherDiscount(50);
        voucher.setVoucherUsageLimit(50);

        LocalDate startDate = LocalDate.of(2024, 4, 24);
        voucher.setVoucherStartDate(startDate);

        LocalDate endDate = LocalDate.of(2024, 5, 24);
        voucher.setVoucherEndDate(endDate);
        voucherRepository.createVoucher(voucher);

        // Edit Voucher
        Voucher voucherModified = new Voucher();
        voucherModified.setVoucherId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        voucherModified.setVoucherName("Diskon 70%");
        voucherModified.setVoucherDescription("Minimal Belanja Rp30.000");
        voucherModified.setVoucherDiscount(70);
        voucherModified.setVoucherUsageLimit(50);

        startDate = LocalDate.of(2024, 5, 24);
        voucherModified.setVoucherStartDate(startDate);

        endDate = LocalDate.of(2024, 6, 24);
        voucherModified.setVoucherEndDate(endDate);

        voucherRepository.editVoucher(voucherModified);

        // Check Whether Voucher Correctly Changed
        Voucher voucherFinal = voucherRepository.getVoucherById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertEquals(voucherModified.getVoucherId(), voucherFinal.getVoucherId());
        assertEquals(voucherModified.getVoucherName(), voucherFinal.getVoucherName());
        assertEquals(voucherModified.getVoucherDescription(), voucherFinal.getVoucherDescription());
        assertEquals(voucherModified.getVoucherUsageLimit(), voucherFinal.getVoucherUsageLimit());
        assertEquals(voucherModified.getVoucherStartDate(), voucherFinal.getVoucherStartDate());
        assertEquals(voucherModified.getVoucherEndDate(), voucherFinal.getVoucherEndDate());
    }

    @Test
    void testEditVoucher_IfUsageLimitIsNegative(){
        // Create Voucher
        Voucher voucher = new Voucher();

        voucher.setVoucherId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        voucher.setVoucherName("Gratis Ongkir");
        voucher.setVoucherDescription("Syarat dan Ketentuan Berlaku");
        voucher.setVoucherDiscount(50);
        voucher.setVoucherUsageLimit(50);

        LocalDate startDate = LocalDate.of(2024, 4, 24);
        voucher.setVoucherStartDate(startDate);

        LocalDate endDate = LocalDate.of(2024, 5, 24);
        voucher.setVoucherEndDate(endDate);
        voucherRepository.createVoucher(voucher);

        // Edit With Invalid Value
        Voucher voucherModified = new Voucher();

        voucherModified.setVoucherId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        voucherModified.setVoucherName("Diskon Scam");
        voucherModified.setVoucherDescription("Syarat dan Ketentuan Berlaku");
        voucherModified.setVoucherDiscount(50);
        voucherModified.setVoucherUsageLimit(-1);

        startDate = LocalDate.of(2024, 4, 24);
        voucherModified.setVoucherStartDate(startDate);

        endDate = LocalDate.of(2024, 5, 24);
        voucherModified.setVoucherEndDate(endDate);

        assertThrows(IllegalArgumentException.class, () -> voucherRepository.editVoucher(voucherModified));

    }

    @Test
    void testEditVoucher_IfDiscountInvalid(){
        // Create Voucher
        Voucher voucher = new Voucher();

        voucher.setVoucherId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        voucher.setVoucherName("Gratis Ongkir");
        voucher.setVoucherDescription("Syarat dan Ketentuan Berlaku");
        voucher.setVoucherDiscount(50);
        voucher.setVoucherUsageLimit(50);

        LocalDate startDate = LocalDate.of(2024, 4, 24);
        voucher.setVoucherStartDate(startDate);

        LocalDate endDate = LocalDate.of(2024, 5, 24);
        voucher.setVoucherEndDate(endDate);
        voucherRepository.createVoucher(voucher);

        // Edit With Invalid Value
        Voucher voucherModified = new Voucher();

        voucherModified.setVoucherId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        voucherModified.setVoucherName("Diskon Scam");
        voucherModified.setVoucherDescription("Syarat dan Ketentuan Berlaku");
        voucherModified.setVoucherDiscount(200);
        voucherModified.setVoucherUsageLimit(50);

        startDate = LocalDate.of(2024, 4, 24);
        voucherModified.setVoucherStartDate(startDate);

        endDate = LocalDate.of(2024, 5, 24);
        voucherModified.setVoucherEndDate(endDate);

        assertThrows(IllegalArgumentException.class, () -> voucherRepository.editVoucher(voucherModified));
    }

    @Test
    void testDeleteProduct(){
        // Create Voucher
        Voucher voucher = new Voucher();

        voucher.setVoucherId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        voucher.setVoucherName("Gratis Ongkir");
        voucher.setVoucherDescription("Syarat dan Ketentuan Berlaku");
        voucher.setVoucherDiscount(50);
        voucher.setVoucherUsageLimit(50);

        LocalDate startDate = LocalDate.of(2024, 4, 24);
        voucher.setVoucherStartDate(startDate);

        LocalDate endDate = LocalDate.of(2024, 5, 24);
        voucher.setVoucherEndDate(endDate);
        voucherRepository.createVoucher(voucher);

        // Delete Voucher
        voucherRepository.deleteVoucher("eb558e9f-1c39-460e-8860-71af6af63bd6");

        Voucher deletedProduct = voucherRepository.getVoucherById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNull(deletedProduct);
    }

    @Test
    void testDeleteProduct_IfVoucherNotFound(){
        // Create Random Voucher Without Inserting it to Repository
        Voucher voucher = new Voucher();
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> voucherRepository.deleteVoucher("randomVoucherId"));
    }
}