package ru.kotb.accounting_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


/**
 * The class that provides specified period of time.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatePeriodDTO {

    /**
     * Start date of the period.
     */
    private Date start;

    /**
     * End date of the period.
     */
    private Date end;
}
