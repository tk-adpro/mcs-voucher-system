package id.ac.ui.cs.advprog.eshop.voucher.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Objects;

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

            // Ensure voucherName is not null or empty
            this.voucherName = Objects.requireNonNull(voucherName, "voucherName is required");
            if (voucherName.isEmpty()) {
                throw new NullPointerException("voucherName cannot be empty");
            }

            // Ensure voucherDescription is not null or empty
            this.voucherDescription = Objects.requireNonNull(voucherDescription, "voucherDescription is required");
            if (voucherDescription.isEmpty()) {
                throw new NullPointerException("voucherDescription cannot be empty");
            }

            // Ensure voucherDiscount is within the valid range
            if (voucherDiscount <= 0) {
                throw new IllegalArgumentException("voucherDiscount must be between 0 and 100");
            }

            // Ensure voucherDiscount is within the valid range
            if (voucherDiscount >= 100) {
                throw new IllegalArgumentException("voucherDiscount must be between 0 and 100");
            }

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

        public VoucherBuilder setUsageLimit(int voucherUsageLimit) {
            this.voucherUsageLimit = voucherUsageLimit;
            return this;
        }

        public Voucher build() {
            return new Voucher(this);
        }
    }
}
