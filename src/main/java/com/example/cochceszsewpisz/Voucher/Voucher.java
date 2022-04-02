package com.example.cochceszsewpisz.Voucher;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.UUID;

import static javax.persistence.GenerationType.AUTO;

@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@Entity
public class Voucher {

    @Id
    @GeneratedValue(strategy = AUTO)
    public UUID code;

    @NonNull
    public VoucherStatus status;

}
