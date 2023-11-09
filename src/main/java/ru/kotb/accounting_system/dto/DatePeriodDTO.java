package ru.kotb.accounting_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DatePeriodDTO {

    private Date start;
    private Date end;
}
