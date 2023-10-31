package com.example.samuraitravel.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Service
public class ReservationService {
    //宿泊人数が定員以下かどうかをチェックする
    public boolean isWithinCapacity(Integer numberOfPeople, Integer capacity) {
        return numberOfPeople <= capacity;
    }

    //宿泊料金を計算する
    public Integer calculateAmount(LocalDate checkinDate, LocalDate checkoutDate, Integer price) {
        long numberOfNights = ChronoUnit.DAYS.between(checkinDate, checkoutDate);
        int amount = price * (int)numberOfNights;
        return amount;
    }
}
