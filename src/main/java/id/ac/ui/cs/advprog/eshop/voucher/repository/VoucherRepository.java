package id.ac.ui.cs.advprog.eshop.voucher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import id.ac.ui.cs.advprog.eshop.voucher.model.Voucher;
import org.springframework.stereotype.Repository;

@Repository
public interface VoucherRepository extends JpaRepository<Voucher, String>{
}
