package id.ac.ui.cs.advprog.eshop.voucher.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
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

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@ExtendWith(MockitoExtension.class)
public class VoucherControllerTest {

    @InjectMocks
    VoucherController VoucherController;

    @Mock
    private VoucherServiceImpl voucherService;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(VoucherController).build();
    }

    @Test
    void voucherListTest() throws Exception {
        List<Voucher> vouchers = Arrays.asList(new Voucher(), new Voucher());

        when(voucherService.findAllVoucher()).thenReturn(vouchers);
        mockMvc.perform(get("/voucher-api/list"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$.length()").value(vouchers.size()));

        verify(voucherService, times(1)).findAllVoucher();
    }

    @Test
    void createVoucherTest() throws Exception {
        Voucher voucher = new Voucher();
        voucher.setVoucherId("eb558e9f-1c39-460e-8860-71af6af63bd6");

        when(voucherService.createVoucher(any(Voucher.class))).thenReturn(voucher);
        mockMvc.perform(post("/voucher-api/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(voucher)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.voucherId").value(voucher.getVoucherId()));

        verify(voucherService, times(1)).createVoucher(any(Voucher.class));
    }

    @Test
    void editProductTest() throws Exception {
        String voucherId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        Voucher voucher = new Voucher();
        voucher.setVoucherId(voucherId);

        when(voucherService.getVoucherById(voucherId)).thenReturn(voucher);

        when(voucherService.editVoucher(any(Voucher.class))).thenReturn(voucher);

        mockMvc.perform(put("/voucher-api/edit/{voucherId}", voucherId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(voucher)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.voucherId").value(voucher.getVoucherId()));

        verify(voucherService, times(1)).editVoucher(any(Voucher.class));
    }

    @Test
    void deleteVoucherTest() throws Exception {
        String voucherId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        Voucher voucher = new Voucher();
        voucher.setVoucherId(voucherId);

        when(voucherService.deleteVoucher(voucherId)).thenReturn(voucher);

        mockMvc.perform(delete("/voucher-api/delete/{voucherId}", voucherId))
                .andExpect(status().isNoContent());

        verify(voucherService, times(1)).deleteVoucher(voucherId);
    }
}