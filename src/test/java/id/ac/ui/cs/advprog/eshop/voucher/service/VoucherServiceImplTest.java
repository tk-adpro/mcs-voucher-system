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

    @BeforeEach
    void setUp() {
        voucher = new Voucher.VoucherBuilder("1", "Test Voucher", "Description", 10.0)
                .setVoucherDate(LocalDate.now(), LocalDate.now().plusDays(10))
                .setUsageLimit(100)
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
    void testUpdateVoucher() {
        when(voucherRepository.save(any(Voucher.class))).thenReturn(voucher);

        Voucher updatedVoucher = voucherService.update(voucher);

        assertNotNull(updatedVoucher);
        assertEquals(voucher.getVoucherId(), updatedVoucher.getVoucherId());
        verify(voucherRepository, times(1)).save(any(Voucher.class));
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
}
