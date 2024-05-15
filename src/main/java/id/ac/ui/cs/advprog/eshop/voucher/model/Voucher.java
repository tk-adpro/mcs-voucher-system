package id.ac.ui.cs.advprog.eshop.voucher.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Entity
@Table(name = "voucher")
@Getter @Setter
public class Voucher {

    // Required parameters
    @Id
    @Column(name = "voucher_id", updatable = false, nullable = false)
    private String voucherId;

    @Column(name = "voucher_name")
    private String voucherName;

    @Column(name = "voucher_description")
    private String voucherDescription;

    @Column(name = "voucher_discount")
    private double voucherDiscount;

    // Optional parameters
    @Column(name = "voucher_type")
    private String voucherType;

    @Column(name = "voucher_start_date")
    private LocalDate voucherStartDate;

    @Column(name = "voucher_end_date")
    private LocalDate voucherEndDate;

    @Column(name = "voucher_usage_limit")
    private int voucherUsageLimit;

    public Voucher() {
    }
    private Voucher(VoucherBuilder builder) {
        this.voucherId = builder.voucherId;
        this.voucherName = builder.voucherName;
        this.voucherDescription = builder.voucherDescription;
        this.voucherDiscount = builder.voucherDiscount;

        this.voucherType = builder.voucherType;
        this.voucherStartDate = builder.voucherStartDate;
        this.voucherEndDate = builder.voucherEndDate;
        this.voucherUsageLimit = builder.voucherUsageLimit;
    }

    public static class VoucherBuilder {

        // Required parameters
        private final String voucherId;
        private final String voucherName;
        private final String voucherDescription;
        private final double voucherDiscount;

        // Optional parameters
        private String voucherType;
        private LocalDate voucherStartDate;
        private LocalDate voucherEndDate;
        private int voucherUsageLimit;

        public VoucherBuilder(String voucherId, String voucherName, String voucherDescription, double voucherDiscount) {
            this.voucherId = voucherId;
            this.voucherName = voucherName;
            this.voucherDescription = voucherDescription;
            this.voucherDiscount = voucherDiscount;
        }

        public VoucherBuilder setType(String type) {
            this.voucherType = type;
            return this;
        }

        public VoucherBuilder setVoucherDate(LocalDate voucherStartDate, LocalDate voucherEndDate) {
            this.voucherStartDate = voucherStartDate;
            this.voucherEndDate = voucherEndDate;
            return this;
        }

        public VoucherBuilder setUsageLimit (int voucherUsageLimit) {
            this.voucherUsageLimit = voucherUsageLimit;
            return this;
        }

        public Voucher build() {
            return new Voucher(this);
        }
    }
}
