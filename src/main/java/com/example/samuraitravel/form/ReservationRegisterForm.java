package com.example.samuraitravel.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReservationRegisterForm {
    private Integer houseId;

    private Integer userId;

    private String checkinDate;

    private String checkoutDate;

    private Integer numberOfPeople;

    private Integer amount;
}
