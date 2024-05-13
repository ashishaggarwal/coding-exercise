package com.wellknownorg.addressbook.date;

import java.time.LocalDate;

public interface DateFormatter {

    LocalDate parse(String dateString);
}
