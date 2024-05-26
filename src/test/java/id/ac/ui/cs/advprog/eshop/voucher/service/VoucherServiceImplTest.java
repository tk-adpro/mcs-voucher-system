package id.ac.ui.cs.advprog.eshop.voucher.service;

import id.ac.ui.cs.advprog.eshop.voucher.model.Voucher;
import id.ac.ui.cs.advprog.eshop.voucher.repository.VoucherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VoucherServiceImplTest {

    @Mock
    private VoucherRepository voucherRepository;

    @InjectMocks
    private VoucherServiceImpl voucherService;

    private Voucher voucher;
    private Voucher dateVoucher;
    private Voucher usageLimitVoucher;
    private Voucher bothVoucher;
    private Voucher expiredVoucher1;
    private Voucher expiredVoucher2;


    @BeforeEach
    void setUp() {
        voucher = new Voucher.VoucherBuilder("1", "Test Voucher", "Description", 10.0)
                .setVoucherDate(LocalDate.now(), LocalDate.now().plusDays(10))
                .setUsageLimit(100)
                .setType("Expired Date and Usage Limit")
                .build();

        dateVoucher = new Voucher.VoucherBuilder("2", "Date Voucher", "Description", 10.0)
                .setVoucherDate(LocalDate.now().minusDays(1), LocalDate.now().plusDays(10))
                .setType("Expired Date")
                .build();

        usageLimitVoucher = new Voucher.VoucherBuilder("3", "Usage Limit Voucher", "Description", 10.0)
                .setUsageLimit(100)
                .setType("Usage Limit")
                .build();

        bothVoucher = new Voucher.VoucherBuilder("4", "Both Voucher", "Description", 10.0)
                .setVoucherDate(LocalDate.now().minusDays(1), LocalDate.now().plusDays(10))
                .setUsageLimit(100)
                .setType("Expired Date and Usage Limit")
                .build();

        expiredVoucher1 = new Voucher.VoucherBuilder("5", "Expired Voucher", "Description", 10.0)
                .setVoucherDate(LocalDate.now().minusDays(10), LocalDate.now().minusDays(1))
                .setUsageLimit(0)
                .setType("Expired Date and Usage Limit")
                .build();

        expiredVoucher2 = new Voucher.VoucherBuilder("6", "Expired Voucher", "Description", 10.0)
                .setVoucherDate(LocalDate.now().plusDays(10), LocalDate.now().plusDays(20))
                .setUsageLimit(0)
                .setType("Expired Date and Usage Limit")
                .build();
    }

    @Test
    void testCreateVoucher() {
        when(voucherRepository.save(any(Voucher.class))).thenReturn(voucher);

        Voucher createdVoucher = voucherService.create(voucher);

        assertNotNull(createdVoucher);
        assertEquals(voucher.getVoucherId(), createdVoucher.getVoucherId());
        verify(voucherRepository, times(1)).save(any(Voucher.class));
    }

    @Test
    void testCreateVoucher_ExpiredDate() {
        dateVoucher.setVoucherType("Expired Date");
        when(voucherRepository.save(any(Voucher.class))).thenReturn(dateVoucher);

        Voucher createdVoucher = voucherService.create(dateVoucher);

        assertNotNull(createdVoucher);
        assertEquals(dateVoucher.getVoucherId(), createdVoucher.getVoucherId());
        verify(voucherRepository, times(1)).save(any(Voucher.class));
    }

    @Test
    void testCreateVoucher_UsageLimit() {
        usageLimitVoucher.setVoucherType("Usage Limit");
        when(voucherRepository.save(any(Voucher.class))).thenReturn(usageLimitVoucher);

        Voucher createdVoucher = voucherService.create(usageLimitVoucher);

        assertNotNull(createdVoucher);
        assertEquals(usageLimitVoucher.getVoucherId(), createdVoucher.getVoucherId());
        verify(voucherRepository, times(1)).save(any(Voucher.class));
    }

    @Test
    void testUpdateVoucher() {
        when(voucherRepository.save(any(Voucher.class))).thenReturn(voucher);

        Voucher updatedVoucher = voucherService.update(voucher);

        assertNotNull(updatedVoucher);
        assertEquals(voucher.getVoucherId(), updatedVoucher.getVoucherId());
        verify(voucherRepository, times(1)).save(any(Voucher.class));
    }

    @Test
    void testUpdateVoucher_ExpiredDate() {
        dateVoucher.setVoucherType("Expired Date");
        when(voucherRepository.save(any(Voucher.class))).thenReturn(dateVoucher);

        Voucher updatedVoucher = voucherService.update(dateVoucher);

        assertNotNull(updatedVoucher);
        assertEquals(dateVoucher.getVoucherId(), updatedVoucher.getVoucherId());
        verify(voucherRepository, times(1)).save(any(Voucher.class));
    }

    @Test
    void testUpdateVoucher_UsageLimit() {
        usageLimitVoucher.setVoucherType("Usage Limit");
        when(voucherRepository.save(any(Voucher.class))).thenReturn(usageLimitVoucher);

        Voucher updatedVoucher = voucherService.update(usageLimitVoucher);

        assertNotNull(updatedVoucher);
        assertEquals(usageLimitVoucher.getVoucherId(), updatedVoucher.getVoucherId());
        verify(voucherRepository, times(1)).save(any(Voucher.class));
    }

    @Test
    void testUpdateVoucher_UsageLimit_SetUsageLimit() {
        usageLimitVoucher.setVoucherType("Usage Limit");
        when(voucherRepository.save(any(Voucher.class))).thenReturn(usageLimitVoucher);

        Voucher updatedVoucher = voucherService.update(usageLimitVoucher);

        assertNotNull(updatedVoucher);
        assertEquals(usageLimitVoucher.getVoucherId(), updatedVoucher.getVoucherId());
        verify(voucherRepository, times(1)).save(any(Voucher.class));

        assertEquals(usageLimitVoucher.getVoucherUsageLimit(), updatedVoucher.getVoucherUsageLimit());
    }

    @Test
    void testFindAllVouchers() {
        List<Voucher> vouchers = Arrays.asList(voucher);
        when(voucherRepository.findAll()).thenReturn(vouchers);

        List<Voucher> foundVouchers = voucherService.findAll();

        assertNotNull(foundVouchers);
        assertEquals(1, foundVouchers.size());
        verify(voucherRepository, times(1)).findAll();
    }

    @Test
    void testDeleteVoucher() {
        doNothing().when(voucherRepository).deleteById(anyString());

        boolean isDeleted = voucherService.delete(voucher.getVoucherId());

        assertTrue(isDeleted);
        verify(voucherRepository, times(1)).deleteById(anyString());
    }

    @Test
    void testFindVoucherById() {
        when(voucherRepository.findById(anyString())).thenReturn(Optional.of(voucher));

        Optional<Voucher> foundVoucher = voucherService.findById(voucher.getVoucherId());

        assertTrue(foundVoucher.isPresent());
        assertEquals(voucher.getVoucherId(), foundVoucher.get().getVoucherId());
        verify(voucherRepository, times(1)).findById(anyString());
    }

    @Test
    void testCanUseVoucher_ExpiredDate() {
        assertTrue(voucherService.canUseVoucher(dateVoucher));
    }

    @Test
    void testCanUseVoucher_UsageLimit() {
        assertTrue(voucherService.canUseVoucher(usageLimitVoucher));
    }

    @Test
    void testCanUseVoucher_Both() {
        assertTrue(voucherService.canUseVoucher(bothVoucher));
    }

    @Test
    void testCanUseVoucher1_Expired() {
        assertFalse(voucherService.canUseVoucher(expiredVoucher1));
    }

    @Test
    void testCanUseVoucher2_Expired() {
        assertFalse(voucherService.canUseVoucher(expiredVoucher2));
    }

    @Test
    void testUseVoucher_ExpiredDate() {
        when(voucherRepository.findById(anyString())).thenReturn(Optional.of(dateVoucher));
        Voucher usedVoucher = voucherService.useVoucher("2");
        assertEquals(0, usedVoucher.getVoucherUsageLimit());
    }

    @Test
    void testUseVoucher_UsageLimit() {
        when(voucherRepository.findById(anyString())).thenReturn(Optional.of(usageLimitVoucher));
        Voucher usedVoucher = voucherService.useVoucher("3");
        assertEquals(99, usedVoucher.getVoucherUsageLimit());
    }

    @Test
    void testUseVoucher_Both() {
        when(voucherRepository.findById(anyString())).thenReturn(Optional.of(bothVoucher));
        Voucher usedVoucher = voucherService.useVoucher("4");
        assertEquals(99, usedVoucher.getVoucherUsageLimit());
    }

    @Test
    void testUseVoucher1_Expired() {
        when(voucherRepository.findById(anyString())).thenReturn(Optional.of(expiredVoucher1));
        assertThrows(IllegalStateException.class, () -> voucherService.useVoucher("5"));
    }

    @Test
    void testUseVoucher2_Expired() {
        when(voucherRepository.findById(anyString())).thenReturn(Optional.of(expiredVoucher2));
        assertThrows(IllegalStateException.class, () -> voucherService.useVoucher("6"));
    }

    @Test
    void testUseVoucher_NotFound() {
        String voucherId = "999"; // An ID that doesn't exist

        // Mock the repository to return an empty Optional when searching for the voucher ID
        when(voucherRepository.findById(voucherId)).thenReturn(Optional.empty());

        // Verify that using a non-existent voucher ID throws NoSuchElementException
        assertThrows(NoSuchElementException.class, () -> voucherService.useVoucher(voucherId));
    }
}
