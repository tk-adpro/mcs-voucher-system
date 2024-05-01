package id.ac.ui.cs.advprog.eshop.voucher.controller;

import id.ac.ui.cs.advprog.eshop.voucher.model.Voucher;
import id.ac.ui.cs.advprog.eshop.voucher.service.VoucherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/voucher-api")
public class VoucherController {

    @Autowired
    private VoucherService<Voucher> voucherService;

    @GetMapping("/list")
    public ResponseEntity<List <Voucher>> voucherList() {
        return ResponseEntity.ok(voucherService.findAllVoucher());
    }

    @PostMapping("/create")
    public ResponseEntity<Voucher> createVoucherPost (@RequestBody Voucher voucher) {
        Voucher createdVoucher = voucherService.createVoucher(voucher);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVoucher);
    }

    @PutMapping("/edit/{voucherId}")
    public ResponseEntity<Voucher> editVoucherPost(@PathVariable String voucherId, @RequestBody Voucher voucher) {
        Voucher currentVoucher = voucherService.getVoucherById(voucherId);
        return currentVoucher != null ?
                ResponseEntity.ok(voucherService.editVoucher(voucher)) :
                ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{voucherId}")
    public ResponseEntity<Voucher> deleteVoucherPost(@PathVariable String voucherId) {
        Voucher deletedVoucher = voucherService.deleteVoucher(voucherId);
        return deletedVoucher != null ?
                ResponseEntity.ok(deletedVoucher) :
                ResponseEntity.notFound().build();
    }
}


