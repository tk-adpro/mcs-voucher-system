package id.ac.ui.cs.advprog.eshop.voucher.controller;

import id.ac.ui.cs.advprog.eshop.voucher.model.Voucher;
import id.ac.ui.cs.advprog.eshop.voucher.service.VoucherService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/voucher-api")
public class VoucherController {

    @Autowired
    private VoucherService voucherService;

    @GetMapping("/list")
    public ResponseEntity<List<Voucher>> getAllVouchers() {
        List<Voucher> vouchers = voucherService.findAll();
        return ResponseEntity.ok(vouchers);
    }

    @GetMapping("/get/{voucherId}")
    public ResponseEntity<Voucher> getVoucherById(@PathVariable String voucherId) {
        return voucherService.findById(voucherId)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<Voucher> createVoucherPost (@RequestBody Voucher voucher) {
        Voucher createdProduct = voucherService.create(voucher);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    @PutMapping("/edit/{voucherId}")
    public ResponseEntity<Voucher> editVoucherPost(@PathVariable String voucherId, @RequestBody Voucher voucher) {
        return voucherService.findById(voucherId)
                .map(p -> ResponseEntity.ok(voucherService.update(voucher)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/delete/{voucherId}")
    public ResponseEntity<Voucher> deleteVoucherPost(@PathVariable String voucherId) {
        return voucherService.delete(voucherId) ?
                ResponseEntity.noContent().build() :
                ResponseEntity.notFound().build();
    }
}


