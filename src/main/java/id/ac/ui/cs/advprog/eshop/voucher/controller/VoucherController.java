package id.ac.ui.cs.advprog.eshop.voucher.controller;

import id.ac.ui.cs.advprog.eshop.voucher.model.Voucher;
import id.ac.ui.cs.advprog.eshop.voucher.service.VoucherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/voucher-api")
public class VoucherController {

    @Autowired
    private VoucherService<Voucher> voucherService;

    @PostMapping("/create")
    public String createVoucherPost (@ModelAttribute Voucher voucher) {
        voucherService.createVoucher(voucher);
        return "Voucher Created Succesfully";
    }

    @PostMapping("/edit")
    public String editVoucherPost(@ModelAttribute Voucher voucher) {
        voucherService.editVoucher(voucher);
        return "Voucher Edited Succesfully";
    }

    @PostMapping("/delete")
    public String deleteVoucherPost(@RequestParam("productId") String productId) {
        Voucher voucher = voucherService.getVoucherById(productId);
        voucherService.deleteVoucher(voucher);
        return "Voucher Deleted Succesfully";
    }

    @GetMapping("/list")
    public List <Voucher> voucherList() {
        return voucherService.findAllVoucher();
    }
}


