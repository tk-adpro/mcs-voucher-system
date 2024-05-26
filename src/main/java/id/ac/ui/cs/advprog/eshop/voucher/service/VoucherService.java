package id.ac.ui.cs.advprog.eshop.voucher.service;

import id.ac.ui.cs.advprog.eshop.voucher.model.Voucher;
import java.util.List;
import java.util.Optional;

public interface VoucherService {
    Voucher create(Voucher voucher);
    List<Voucher> findAll();
    Optional<Voucher> findById(String voucherId);
    boolean delete(String voucherId);
    Voucher update(Voucher voucher);

    Voucher useVoucher(String voucherId);
}