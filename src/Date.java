public class Date {
    private int date;
    private String dayOfWeek;
    private boolean isHoliday;
    private boolean isHolidayEve;

    public Date(int date, String dayOfWeek, boolean isHoliday, boolean isHolidayEve) {
        this.date = date;
        this.dayOfWeek = dayOfWeek;
        this.isHoliday = isHoliday;
        this.isHolidayEve = isHolidayEve;
    }

    public int getDate() {
        return date;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public boolean getIsHoliday() {
        return isHoliday;
    }

    public boolean getIsHolidayEve() {
        return isHolidayEve;
    }

    public void setDate(int newDate) {
        this.date = newDate;
    }

    public void setDayOfWeek(String newDay) {
        this.dayOfWeek = newDay;
    }

    public void setHoliday(boolean newHoliday) {
        this.isHoliday = newHoliday;
    }

    public void setHolidayEve(boolean newEve) {
        this.isHolidayEve = newEve;
    }
}
