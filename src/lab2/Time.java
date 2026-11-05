package lab2;

public class Time {
    private final int secondsSinceMidnight;

    public Time(long seconds) {
        if (seconds < 0) throw new IllegalArgumentException("число секунд не может быть отрицательным");
        this.secondsSinceMidnight = (int) (seconds % 86400);
    }

    public Time(int hours, int minutes, int seconds) {
        this(toSeconds(hours, minutes, seconds));
    }

    private static long toSeconds(int hours, int minutes, int seconds) {
        if (hours < 0 || minutes < 0 || minutes > 59 || seconds < 0 || seconds > 59)
            throw new IllegalArgumentException("часы должны быть неотрицательными, минуты и секунды в интервале от 0 до 59");
        return (long) hours * 3600 + minutes * 60 + seconds;
    }

    public int getHours() { return secondsSinceMidnight / 3600; }
    public int getMinutes() { return secondsSinceMidnight / 60 % 60; }
    public int getSeconds() { return secondsSinceMidnight % 60; }

    @Override
    public String toString() {
        return String.format("%d:%02d:%02d", getHours(), getMinutes(), getSeconds());
    }
}
