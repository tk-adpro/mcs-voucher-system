package id.ac.ui.cs.advprog.eshop.voucher.service;

import id.ac.ui.cs.advprog.eshop.voucher.model.Voucher;
import id.ac.ui.cs.advprog.eshop.voucher.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    private VoucherRepository voucherRepository;

    @Override
    public Voucher create(Voucher voucher) {

        // Required parameters
        String voucherId = voucher.getVoucherId();
        String voucherName = voucher.getVoucherName();
        String voucherDescription = voucher.getVoucherDescription();
        double voucherDiscount = voucher.getVoucherDiscount();
        String voucherType = voucher.getVoucherType();

        // Optional parameters
        LocalDate voucherStartDate = voucher.getVoucherStartDate();
        LocalDate voucherEndDate = voucher.getVoucherEndDate();
        int voucherUsageLimit = voucher.getVoucherUsageLimit();

        Voucher.VoucherBuilder builder = new Voucher.VoucherBuilder(voucherId, voucherName, voucherDescription, voucherDiscount);

        if (Objects.equals(voucherType, "Usage Limit")) {
            builder.setUsageLimit(voucherUsageLimit);
            builder.setType("Usage Limit");
        } else if (Objects.equals(voucherType, "Expired Date")) {
            builder.setVoucherDate(voucherStartDate, voucherEndDate);
            builder.setType("Expired Date");
        } else {
            builder.setVoucherDate(voucherStartDate, voucherEndDate);
            builder.setUsageLimit(voucherUsageLimit);
            builder.setType("Expired Date and Usage Limit");
        }

        voucher = builder.build();

        return voucherRepository.save(voucher);
    }

    @Override
    public Voucher update(Voucher voucher) {

        // Required parameters
        String voucherId = voucher.getVoucherId();
        String voucherName = voucher.getVoucherName();
        String voucherDescription = voucher.getVoucherDescription();
        double voucherDiscount = voucher.getVoucherDiscount();
        String voucherType = voucher.getVoucherType();

        // Optional parameters
        LocalDate voucherStartDate = voucher.getVoucherStartDate();
        LocalDate voucherEndDate = voucher.getVoucherEndDate();
        int voucherUsageLimit = voucher.getVoucherUsageLimit();

        Voucher.VoucherBuilder builder = new Voucher.VoucherBuilder(voucherId, voucherName, voucherDescription, voucherDiscount);

        if (Objects.equals(voucherType, "Usage Limit")) {
            builder.setUsageLimit(voucherUsageLimit);
            builder.setType("Usage Limit");
        } else if (Objects.equals(voucherType, "Expired Date")) {
            builder.setVoucherDate(voucherStartDate, voucherEndDate);
            builder.setType("Expired Date");
        } else {
            builder.setVoucherDate(voucherStartDate, voucherEndDate);
            builder.setUsageLimit(voucherUsageLimit);
            builder.setType("Expired Date and Usage Limit");
        }

        voucher = builder.build();

        return voucherRepository.save(voucher);
    }

    @Override
    public List<Voucher> findAll() {
        return voucherRepository.findAll();
    }

    @Override
    public boolean delete(String voucherId) {
        voucherRepository.deleteById(voucherId);
        return true;
    }

    @Override
    public Optional<Voucher> findById(String productId) {
        return voucherRepository.findById(productId);
    }
}
