package id.ac.ui.cs.advprog.eshop.voucher.service;

import id.ac.ui.cs.advprog.eshop.voucher.model.Voucher;
import id.ac.ui.cs.advprog.eshop.voucher.repository.VoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class VoucherServiceImpl implements VoucherService<Voucher> {

    @Autowired
    private VoucherRepository voucherRepository;

    @Override
    public Voucher createVoucher (Voucher voucher) {
        return voucherRepository.createVoucher(voucher);
    }

    @Override
    public Voucher editVoucher(Voucher voucher) {
        return voucherRepository.editVoucher(voucher);
    }

    @Override
    public Voucher deleteVoucher (Voucher voucher) {
        return voucherRepository.deleteVoucher(voucher);
    }

    @Override
    public Voucher getVoucherById (String VoucherId) {
        return voucherRepository.getVoucherById(VoucherId);
    }

    @Override
    public List<Voucher> findAllVoucher() {
        Iterator<Voucher> VoucherIterator = voucherRepository.findAllVoucher();
        List<Voucher> allVoucher = new ArrayList<>();
        VoucherIterator.forEachRemaining(allVoucher::add);
        return allVoucher;
    }
}
