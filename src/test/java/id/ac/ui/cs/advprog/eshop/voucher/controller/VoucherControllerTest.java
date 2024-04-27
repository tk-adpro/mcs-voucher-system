package id.ac.ui.cs.advprog.eshop.voucher.controller;
import id.ac.ui.cs.advprog.eshop.voucher.service.VoucherServiceImpl;

import id.ac.ui.cs.advprog.eshop.voucher.model.Voucher;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.ui.Model;

@ExtendWith(MockitoExtension.class)
public class VoucherControllerTest {

    @InjectMocks
    VoucherController VoucherController;

    @Mock
    private VoucherServiceImpl productService;

    @Mock
    private Model model;

    @Test
    void createVoucherPostTest() {
        Voucher product = new Voucher();
        String result = VoucherController.createVoucherPost(product);
        assertEquals("Voucher Created Succesfully", result);
    }
    
    @Test
    void testEditProductPost() {
        Voucher product = new Voucher();
        String result = VoucherController.editVoucherPost(product);
        assertEquals("Voucher Edited Succesfully", result);
    }

    @Test
    void deleteVoucherPostTest() {
        String result = VoucherController.deleteVoucherPost("some-product-id");
        assertEquals("Voucher Deleted Succesfully", result);
    }

//    @Test
//    void voucherListTest() {
//        String result = VoucherController.voucherList(model);
//        assertEquals("Vouchers", result);
//    }
}