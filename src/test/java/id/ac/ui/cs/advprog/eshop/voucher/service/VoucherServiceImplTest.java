package id.ac.ui.cs.advprog.eshop.voucher.service;

import id.ac.ui.cs.advprog.eshop.voucher.model.Voucher;
import id.ac.ui.cs.advprog.eshop.voucher.repository.VoucherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {
    @InjectMocks
    private  VoucherServiceImpl voucherService;

    @Mock
    private VoucherRepository voucherRepository;

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

        Mockito.when(voucherRepository.createVoucher(voucher)).thenReturn(voucher);
        assertEquals(voucher, voucherService.createVoucher(voucher));
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

        Mockito.when(voucherRepository.getVoucherById("eb558e9f-1c39-460e-8860-71af6af63bd6")).thenReturn(voucher);
        assertEquals(voucher, voucherService.getVoucherById("eb558e9f-1c39-460e-8860-71af6af63bd6"));
    }

    @Test
    void testEditVoucher() {
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

        Mockito.when(voucherRepository.editVoucher(voucher)).thenReturn(voucher);
        assertEquals(voucher, voucherService.editVoucher(voucher));
    }

    @Test
    void testDeleteVoucher() {
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

        Mockito.when(voucherRepository.deleteVoucher(voucher.getVoucherId())).thenReturn(voucher);
        assertEquals(voucher, voucherService.deleteVoucher(voucher.getVoucherId()));
    }

    @Test
    void testFindAllVoucher() {
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

        ArrayList<Voucher> products = new ArrayList<>();
        products.add(voucher);

        Iterator<Voucher> productIterator = products.iterator();

        Mockito.when(voucherRepository.findAllVoucher()).thenReturn(productIterator);
        assertEquals(products, voucherService.findAllVoucher());
    }
}