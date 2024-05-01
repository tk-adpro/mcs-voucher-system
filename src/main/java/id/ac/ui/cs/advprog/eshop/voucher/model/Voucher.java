package id.ac.ui.cs.advprog.eshop.voucher.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter @Setter
public class Voucher {
    private String voucherId;
    private String voucherName;
    private String voucherDescription;
    private LocalDate voucherStartDate;
    private LocalDate voucherEndDate;
    private double voucherDiscount;
    private int voucherUsageLimit;
}
