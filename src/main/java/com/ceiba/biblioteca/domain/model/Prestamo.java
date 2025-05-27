package com.ceiba.biblioteca.domain.model;

import com.ceiba.biblioteca.domain.exception.PrestamoUserTypeNotAllowedException;
import com.ceiba.biblioteca.utilities.Utilities;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Data
public class Prestamo {

    private Long id;
    private String isbn;
    private String userIdentification;
    private int userType;
    private LocalDate maximumReturnDate;

    public Prestamo(String isbn, String userIdentification, int userType) {
        this.isbn = isbn;
        this.userIdentification = userIdentification;
        this.userType = userType;
        this.maximumReturnDate = calculateMaximumReturnDate(userType);
    }

    private LocalDate calculateMaximumReturnDate(int userType) {

        LocalDate currentDate = LocalDate.now();
        int additionalDays;

        switch (userType) {
            case 1:
                additionalDays = 10;
                break;
            case 2:
                additionalDays = 8;
                break;
            case 3:
                additionalDays = 7;
                break;
            default:
                throw new PrestamoUserTypeNotAllowedException(Utilities.getValueMessage("msg_user_type_not_allowed"));
        }

        return addDaysExcludingWeekend(currentDate, additionalDays);

    }

    private LocalDate addDaysExcludingWeekend(LocalDate date, int days) {

        LocalDate result = date;
        int daysAdded = 0;

        while (daysAdded < days) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY ||
                    result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                daysAdded++;
            }
        }

        return result;

    }

}
