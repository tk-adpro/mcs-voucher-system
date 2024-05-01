package id.ac.ui.cs.advprog.eshop.voucher.service;

import java.util.List;

public interface VoucherService <Voucher>{
    public Voucher createVoucher(Voucher voucher);
    public Voucher editVoucher(Voucher voucher);
    public Voucher deleteVoucher(String voucherId);
    public Voucher getVoucherById (String voucherId);
    public List<Voucher> findAllVoucher();
}