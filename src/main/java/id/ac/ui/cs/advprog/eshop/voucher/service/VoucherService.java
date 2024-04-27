package id.ac.ui.cs.advprog.eshop.voucher.service;

import java.util.List;

public interface VoucherService <Voucher>{
    public Voucher createVoucher(Voucher product);
    public Voucher editVoucher(Voucher product);
    public Voucher deleteVoucher(Voucher product);
    public Voucher getVoucherById (String productId);
    public List<Voucher> findAllVoucher();
}