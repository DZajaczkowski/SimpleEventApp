package com.example.cochceszsewpisz.Voucher;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RequiredArgsConstructor
@RestController
@RequestMapping("/voucher")
public class VoucherController {

    private final VoucherRepository voucherRepository;

    @PostMapping
    @ResponseStatus(CREATED)
    public Voucher createVoucher(@RequestBody VoucherData voucherData) {
        return voucherRepository.save(new Voucher(voucherData.status()));
    }

    @GetMapping("/{code}")
    public Voucher showVoucherById(@PathVariable UUID code) {
        return voucherRepository.findById(code)
                .orElseThrow(VoucherNotFoundException::new);
    }

    @GetMapping
    public List<Voucher> showVoucherList() {
        return voucherRepository.findAll();
    }

    @DeleteMapping("/{code}")
    @ResponseStatus(NO_CONTENT)
    public void deleteVoucher(@PathVariable UUID code) {
        voucherRepository.deleteById(code);
    }

    @PutMapping("/{code}")
    public Voucher editVoucherById(@PathVariable UUID code, @RequestBody VoucherData voucherData) {
        Voucher voucher = voucherRepository.getById(code);
        voucher.status = voucherData.status();
        return voucherRepository.save(voucher);
    }
}
