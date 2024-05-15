package id.ac.ui.cs.advprog.eshop.voucher.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import id.ac.ui.cs.advprog.eshop.voucher.model.Voucher;
import id.ac.ui.cs.advprog.eshop.voucher.service.VoucherServiceImpl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@ExtendWith(MockitoExtension.class)
public class VoucherControllerTest {

    @InjectMocks
    id.ac.ui.cs.advprog.eshop.voucher.controller.VoucherController VoucherController;

    @Mock
    private VoucherServiceImpl voucherService;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(VoucherController).build();
    }
    @Test
    public void testGetAllVouchers() throws Exception {
        List<Voucher> products = Arrays.asList(new Voucher(), new Voucher());
        when(voucherService.findAll()).thenReturn(products);

        mockMvc.perform(get("/voucher-api/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(products.size()));

        verify(voucherService, times(1)).findAll();
    }

    @Test
    public void testGetVoucherById() throws Exception {
        String voucherId = "1";
        Voucher product = new Voucher();
        product.setVoucherId(voucherId);

        when(voucherService.findById(voucherId)).thenReturn(Optional.of(product));

        mockMvc.perform(get("/voucher-api/get/{voucherId}", voucherId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.voucherId").value(voucherId));

        verify(voucherService, times(1)).findById(voucherId);
    }

    @Test
    public void testGetVoucherByIdNotFound() throws Exception {
        String voucherId = "1";

        when(voucherService.findById(voucherId)).thenReturn(Optional.empty());

        mockMvc.perform(get("/voucher-api/get/{voucherId}", voucherId))
                .andExpect(status().isNotFound());

        verify(voucherService, times(1)).findById(voucherId);
    }

    @Test
    public void testCreateVoucher() throws Exception {
        Voucher product = new Voucher();
        product.setVoucherName("Test Voucher");

        when(voucherService.create(any(Voucher.class))).thenReturn(product);
        mockMvc.perform(post("/voucher-api/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(product)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.voucherName").value(product.getVoucherName()));

        verify(voucherService, times(1)).create(any(Voucher.class));
    }

    @Test
    public void testEditVoucher() throws Exception {
        String voucherId = "1";
        Voucher product = new Voucher();
        product.setVoucherId(voucherId);
        product.setVoucherName("Updated Voucher");

        when(voucherService.findById(voucherId)).thenReturn(Optional.of(product));
        when(voucherService.update(any(Voucher.class))).thenReturn(product);

        mockMvc.perform(put("/voucher-api/edit/{productId}", voucherId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.voucherName").value(product.getVoucherName()));

        verify(voucherService, times(1)).update(any(Voucher.class));
    }

    @Test
    public void testEditVoucherNotFound() throws Exception {
        String voucherId = "1";
        Voucher voucher = new Voucher();

        // Mocking the service to return an empty optional when findById is called
        when(voucherService.findById(voucherId)).thenReturn(Optional.empty());

        mockMvc.perform(put("/voucher-api/edit/{voucherId}", voucherId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(voucher)))
                .andExpect(status().isNotFound());

        verify(voucherService, times(1)).findById(voucherId);
        verify(voucherService, never()).update(any(Voucher.class));
    }

    @Test
    public void testDeleteVoucher() throws Exception {
        String voucherId = "1";

        when(voucherService.delete(voucherId)).thenReturn(true);

        mockMvc.perform(delete("/voucher-api/delete/{productId}", voucherId))
                .andExpect(status().isNoContent());

        verify(voucherService, times(1)).delete(voucherId);
    }

    @Test
    public void testDeleteVoucherNotFound() throws Exception {
        String voucherId = "1";

        when(voucherService.delete(voucherId)).thenReturn(false);

        mockMvc.perform(delete("/voucher-api/delete/{voucherId}", voucherId))
                .andExpect(status().isNotFound());

        verify(voucherService, times(1)).delete(voucherId);
    }
}