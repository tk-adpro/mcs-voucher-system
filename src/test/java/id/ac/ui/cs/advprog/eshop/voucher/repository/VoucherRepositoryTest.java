package id.ac.ui.cs.advprog.eshop.voucher.repository;

import id.ac.ui.cs.advprog.eshop.voucher.model.Voucher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class VoucherRepositoryTest {
    @Mock
    VoucherRepository voucherRepository;

    private Voucher voucher;

    @BeforeEach
    void setUp() {
        voucher = new Voucher();
        voucher.setVoucherId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        voucher.setVoucherName("Boneka Doraemon");
        voucher.setVoucherUsageLimit(100);
    }

    @Test
    void testCreateProduct_HappyPath() {
        when(voucherRepository.save(any(Voucher.class))).thenReturn(voucher);
        Voucher createdProduct = voucherRepository.save(voucher);
        assertNotNull(createdProduct);
        assertEquals("Boneka Doraemon", createdProduct.getVoucherName());
    }

    @Test
    void testCreateProduct_UnhappyPath_NullProduct() {
        when(voucherRepository.save(null)).thenThrow(new IllegalArgumentException("Voucher cannot be null"));
        Exception exception = assertThrows(IllegalArgumentException.class, () -> voucherRepository.save(null));
        assertEquals("Voucher cannot be null", exception.getMessage());
    }

    @Test
    void testFindAll_HappyPath() {
        when(voucherRepository.findAll()).thenReturn(List.of(voucher));
        List<Voucher> products = voucherRepository.findAll();
        assertFalse(products.isEmpty());
        assertEquals(voucher, products.get(0));
    }

    @Test
    void testFindAll_UnhappyPath_EmptyRepository() {
        when(voucherRepository.findAll()).thenReturn(List.of());
        List<Voucher> products = voucherRepository.findAll();
        assertTrue(products.isEmpty());
    }

    @Test
    void testFindById_HappyPath() {
        when(voucherRepository.findById(voucher.getVoucherId())).thenReturn(Optional.of(voucher));
        Optional<Voucher> foundProduct = voucherRepository.findById(voucher.getVoucherId());
        assertTrue(foundProduct.isPresent());
        assertEquals("Boneka Doraemon", foundProduct.get().getVoucherName());
    }

    @Test
    void testFindById_UnhappyPath_ProductNotFound() {
        when(voucherRepository.findById("nonexistent-id")).thenReturn(Optional.empty());
        Optional<Voucher> foundProduct = voucherRepository.findById("nonexistent-id");
        assertFalse(foundProduct.isPresent());
    }

    @Test
    void testUpdate_HappyPath() {
        Voucher updatedProduct = new Voucher();
        updatedProduct.setVoucherName("Updated Name");
        updatedProduct.setVoucherId(voucher.getVoucherId());
        when(voucherRepository.save(updatedProduct)).thenReturn(updatedProduct);

        Voucher result = voucherRepository.save(updatedProduct);
        assertNotNull(result);
        assertEquals("Updated Name", result.getVoucherName());
    }

    @Test
    void testUpdate_UnhappyPath_ProductNotFound() {
        Voucher updatedProduct = new Voucher();
        updatedProduct.setVoucherName("Updated Name");
        updatedProduct.setVoucherId("nonexistent-id");
        when(voucherRepository.findById("nonexistent-id")).thenReturn(Optional.empty());
        Optional<Voucher> result = voucherRepository.findById("nonexistent-id");
        assertFalse(result.isPresent());
    }

    @Test
    void testDelete_HappyPath() {
        doNothing().when(voucherRepository).deleteById(voucher.getVoucherId());
        voucherRepository.deleteById(voucher.getVoucherId());
        verify(voucherRepository, times(1)).deleteById(voucher.getVoucherId());
    }

    @Test
    void testDelete_UnhappyPath() {
        doThrow(new IllegalArgumentException("Voucher not found")).when(voucherRepository).deleteById("nonexistent-id");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> voucherRepository.deleteById("nonexistent-id"));
        assertEquals("Voucher not found", exception.getMessage());
    }
}