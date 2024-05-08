package id.ac.ui.cs.advprog.eshop.voucher.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Table(name = "voucher")
@Getter @Setter
public class Voucher {
    @Id
    @Column(name = "voucher_id", updatable = false, nullable = false)
    private String voucherId;

    @Column(name = "voucher_name")
    private String voucherName;

    @Column(name = "voucher_description")
    private String voucherDescription;

    @Column(name = "voucher_start_date")
    private LocalDate voucherStartDate;

    @Column(name = "voucher_end_date")
    private LocalDate voucherEndDate;

    @Column(name = "voucher_discount")
    private double voucherDiscount;

    @Column(name = "voucher_usage_limit")
    private int voucherUsageLimit;
}
