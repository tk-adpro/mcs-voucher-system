package id.ac.ui.cs.advprog.eshop.voucher.service;

import id.ac.ui.cs.advprog.eshop.voucher.model.Voucher;
import id.ac.ui.cs.advprog.eshop.voucher.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class VoucherServiceImpl implements VoucherService {

    @Autowired
    private VoucherRepository voucherRepository;

    @Override
    public Voucher create(Voucher voucher) {
        return voucherRepository.save(voucher);
    }

    @Override
    public List<Voucher> findAll() {
        List<Voucher> allProducts = voucherRepository.findAll();
        return allProducts;
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

    @Override
    public Voucher update(Voucher voucher) {
        return voucherRepository.save(voucher);
    }
}
