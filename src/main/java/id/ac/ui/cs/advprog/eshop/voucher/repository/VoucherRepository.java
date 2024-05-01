package id.ac.ui.cs.advprog.eshop.voucher.repository;

import id.ac.ui.cs.advprog.eshop.voucher.model.Voucher;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class VoucherRepository {
    private final List<Voucher> vouchers = new ArrayList<>();

    public Voucher createVoucher(Voucher voucher) {

        if (voucher.getVoucherDiscount() < 0 || voucher.getVoucherDiscount() > 100){
            throw new IllegalArgumentException("Invalid Voucher Discount");
        }

        if (voucher.getVoucherUsageLimit() < 0){
            throw new IllegalArgumentException("Invalid Voucher Usage Limit");
        }

        if (voucher.getVoucherId() == null) voucher.setVoucherId(UUID.randomUUID().toString());

        vouchers.add(voucher);
        return voucher;
    }

    public Voucher editVoucher (Voucher voucher) {

        if (voucher.getVoucherDiscount() < 0 || voucher.getVoucherDiscount() > 100){
            throw new IllegalArgumentException("Invalid Voucher Discount");
        }

        if (voucher.getVoucherUsageLimit() < 0){
            throw new IllegalArgumentException("Invalid Voucher Usage Limit");
        }

        Voucher existingVoucher = getVoucherById(voucher.getVoucherId());

        existingVoucher.setVoucherName(voucher.getVoucherName());
        existingVoucher.setVoucherDescription(voucher.getVoucherDescription());
        existingVoucher.setVoucherDiscount(voucher.getVoucherDiscount());
        existingVoucher.setVoucherUsageLimit(voucher.getVoucherUsageLimit());
        existingVoucher.setVoucherStartDate(voucher.getVoucherStartDate());
        existingVoucher.setVoucherEndDate(voucher.getVoucherEndDate());

        return existingVoucher;
    }

    public Voucher deleteVoucher (String voucherId) {

        for (Voucher voucher : vouchers) {
            if (voucher.getVoucherId().equals(voucherId)) {
                vouchers.remove(voucher);
                return voucher;
            }
        }

        throw new ArrayIndexOutOfBoundsException("Product Doesn't Exist");
    }

    public Iterator<Voucher> findAllVoucher(){
        return vouchers.iterator();
    }

    public Voucher getVoucherById(String voucherId) {
        for (Voucher voucher : vouchers) {
            if (voucher.getVoucherId().equals(voucherId)) {
                return voucher;
            }
        }
        return null;
    }

}
