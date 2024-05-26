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
    private VoucherService voucherService;

    @GetMapping("/public/list")
    public ResponseEntity<List<Voucher>> getAllVouchers() {
        List<Voucher> vouchers = voucherService.findAll();
        return ResponseEntity.ok(vouchers);
    }

    @GetMapping("/public/get/{voucherId}")
    public ResponseEntity<Voucher> getVoucherById(@PathVariable String voucherId) {
        return voucherService.findById(voucherId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/public/use/{voucherId}")
    public ResponseEntity<String> useVoucher(@PathVariable String voucherId) {
        try {
            Voucher voucher = voucherService.useVoucher(voucherId);
            return ResponseEntity.ok("Voucher used successfully: " + voucher.getVoucherId());
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body("Voucher cannot be used");
        }
    }

    @PostMapping("/admin/create")
    public ResponseEntity<Voucher> createVoucherPost (@RequestBody Voucher voucher) {
        Voucher createdProduct = voucherService.create(voucher);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/admin/edit/{voucherId}")
    public ResponseEntity<Voucher> editVoucherPost(@PathVariable String voucherId, @RequestBody Voucher voucher) {
        return voucherService.findById(voucherId)
                .map(p -> ResponseEntity.ok(voucherService.update(voucher)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/admin/delete/{voucherId}")
    public ResponseEntity<Voucher> deleteVoucherPost(@PathVariable String voucherId) {
        return voucherService.delete(voucherId) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}


