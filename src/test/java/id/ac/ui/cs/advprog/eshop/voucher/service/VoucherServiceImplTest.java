package id.ac.ui.cs.advprog.eshop.voucher.service;

import id.ac.ui.cs.advprog.eshop.voucher.model.Voucher;
import id.ac.ui.cs.advprog.eshop.voucher.repository.VoucherRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VoucherServiceImplTest {
    @InjectMocks
    private VoucherServiceImpl voucherService;

    @Mock
    private VoucherRepository voucherRepository;

    private Voucher voucher;

    @BeforeEach
    void setUp() {
        voucher = new Voucher();
        voucher.setVoucherId("p1");
        voucher.setVoucherName("Voucher 1");
        voucher.setVoucherUsageLimit(1);
    }

    @Test
    void testCreateProduct() {
        when(voucherRepository.save(voucher)).thenReturn(voucher);
        Voucher createdProduct = voucherService.create(voucher);
        verify(voucherRepository).save(voucher);
        assertEquals(voucher.getVoucherId(), createdProduct.getVoucherId());
    }

    @Test
    void testFindAllProducts() {
        Voucher product2 = new Voucher();
        product2.setVoucherId("p2");
        product2.setVoucherName("Voucher 2");
        when(voucherRepository.findAll()).thenReturn(List.of(voucher, product2));

        List<Voucher> products = voucherService.findAll();
        assertEquals(2, products.size());
        assertTrue(products.contains(voucher) && products.contains(product2));
    }

    @Test
    void testDeleteProductSuccess() {
        doNothing().when(voucherRepository).deleteById(voucher.getVoucherId());
        voucherService.delete(voucher.getVoucherId());
        verify(voucherRepository).deleteById(voucher.getVoucherId());
    }

    @Test
    void testDeleteProductFailure() {
        doThrow(new IllegalArgumentException("Voucher not found")).when(voucherRepository).deleteById("p3");
        assertThrows(IllegalArgumentException.class, () -> voucherService.delete("p3"));
        verify(voucherRepository).deleteById("p3");
    }

    @Test
    void testFindProductByIdFound() {
        when(voucherRepository.findById(voucher.getVoucherId())).thenReturn(Optional.of(voucher));
        Voucher foundProduct = voucherService.findById(voucher.getVoucherId()).orElse(null);
        assertNotNull(foundProduct);
        assertEquals(voucher.getVoucherId(), foundProduct.getVoucherId());
    }

    @Test
    void testFindProductByIdNotFound() {
        when(voucherRepository.findById("unknown")).thenReturn(Optional.empty());
        Optional<Voucher> foundProduct = voucherService.findById("unknown");
        assertFalse(foundProduct.isPresent());
    }

    @Test
    void testUpdateProduct() {
        when(voucherRepository.save(voucher)).thenReturn(voucher);
        Voucher updatedProduct = voucherService.update(voucher);
        verify(voucherRepository).save(voucher);
        assertEquals(voucher.getVoucherId(), updatedProduct.getVoucherId());
    }
}