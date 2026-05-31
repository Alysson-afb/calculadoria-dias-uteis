package model.classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class DateUtils {

    public static Date stringToDate(String stringDate) {
        Date date = null;
        try {
            SimpleDateFormat sdf
                    = new SimpleDateFormat("dd/MM/yyyy", new Locale.Builder().setLanguage("pt").setRegion("BR").build());
            date = sdf.parse(stringDate);
        } catch (ParseException e) {
        }
        return date;
    }

    public static int deductDates(Date initialDate, Date finalDate) {
        if (initialDate == null || finalDate == null) {
            return 0;
        }
        int days = (int) ((finalDate.getTime() - initialDate.getTime()) / (24 * 60 * 60 * 1000));
        return (days > 0 ? days : 0);
    }

    public static String dateToString(Date date) {
        SimpleDateFormat sdf
                = new SimpleDateFormat("dd/MM/yyyy", new Locale.Builder().setLanguage("pt").setRegion("BR").build());
        String dateFormated = sdf.format(date);
        return dateFormated;
    }

    public static Date clearHour(Date date) {
        return (stringToDate(dateToString(date)));
    }

    public static int getWorkingDays(Date initialDate, Date finalDate, List<Feriado> feriados) {

        if (initialDate == null || finalDate == null) {
            return 0;
        }

        Set<Long> feriadosSet = feriados.stream()
                .map(f -> clearHour(f.getDia()).getTime())
                .collect(Collectors.toSet());

        int workingDays = 0;
        int totalDays = deductDates(initialDate, finalDate);

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(clearHour(initialDate));

        for (int i = 0; i <= totalDays; i++) {
            int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

            boolean isWeekend = (dayOfWeek == Calendar.SATURDAY) || (dayOfWeek == Calendar.SUNDAY);

            boolean isHoliday = feriadosSet.contains(calendar.getTimeInMillis());

            if (!isWeekend && !isHoliday) {
                workingDays++;
            }

            calendar.add(Calendar.DATE, 1);
        }

        return workingDays;
    }
}
