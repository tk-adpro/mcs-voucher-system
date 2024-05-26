package id.ac.ui.cs.advprog.eshop.voucher.service;

import id.ac.ui.cs.advprog.eshop.voucher.model.Voucher;
import id.ac.ui.cs.advprog.eshop.voucher.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;

@Service
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    private VoucherRepository voucherRepository;

    private static final String VOUCHER_TYPE_USAGE_LIMIT = "Usage Limit";
    private static final String VOUCHER_TYPE_EXPIRED_DATE = "Expired Date";
    private static final String VOUCHER_TYPE_BOTH = "Expired Date and Usage Limit";

    @Override
    public Voucher create(Voucher voucher) {
        return createOrUpdate(voucher);
    }

    @Override
    public Voucher update(Voucher voucher) {
        return createOrUpdate(voucher);
    }

    private Voucher createOrUpdate(Voucher voucher) {
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

        if (Objects.equals(voucherType, VOUCHER_TYPE_USAGE_LIMIT)) {
            builder.setUsageLimit(voucherUsageLimit);
            builder.setType(VOUCHER_TYPE_USAGE_LIMIT);
        } else if (Objects.equals(voucherType, VOUCHER_TYPE_EXPIRED_DATE)) {
            builder.setVoucherDate(voucherStartDate, voucherEndDate);
            builder.setType(VOUCHER_TYPE_EXPIRED_DATE);
        } else {
            builder.setVoucherDate(voucherStartDate, voucherEndDate);
            builder.setUsageLimit(voucherUsageLimit);
            builder.setType(VOUCHER_TYPE_BOTH);
        }

        voucher = builder.build();

        return voucherRepository.save(voucher);
    }

    @Override
    public List<Voucher> findAll() {
        return voucherRepository.findAll();
    }

    @Override
    public Optional<Voucher> findById(String voucherId) {
        return voucherRepository.findById(voucherId);
    }

    @Override
    public boolean delete(String voucherId) {
        voucherRepository.deleteById(voucherId);
        return true;
    }

    public boolean canUseVoucher(Voucher voucher) {
        LocalDate today = LocalDate.now();

        boolean isDateValid = true;
        LocalDate startDate = voucher.getVoucherStartDate();
        LocalDate endDate = voucher.getVoucherEndDate();

        if (startDate != null && startDate.isAfter(today)) {
            isDateValid = false;
        }

        if (endDate != null && endDate.isBefore(today)) {
            isDateValid = false;
        }

        boolean isUsageValid = voucher.getVoucherUsageLimit() > 0;

        String voucherType = voucher.getVoucherType();

        if ("Expired Date".equals(voucherType)) {
            return isDateValid;
        } else if ("Usage Limit".equals(voucherType)) {
            return isUsageValid;
        } else {
            return isDateValid && isUsageValid;
        }
    }


    public Voucher useVoucher(String voucherId) {
        Optional<Voucher> voucherOptional = voucherRepository.findById(voucherId);
        if (voucherOptional.isPresent()) {
            Voucher voucher = voucherOptional.get();
            String voucherType = voucher.getVoucherType();

            if (canUseVoucher(voucher)) {
                if (Objects.equals(voucherType, "Usage Limit") ||
                        Objects.equals(voucherType, "Expired Date and Usage Limit")){
                    voucher.setVoucherUsageLimit(voucher.getVoucherUsageLimit() - 1);
                }
                voucherRepository.save(voucher);
                return voucher;
            }
        } else {
            throw new NoSuchElementException("Voucher cannot be used.");
        }
        throw new IllegalStateException("Voucher cannot be used.");
    }
}
