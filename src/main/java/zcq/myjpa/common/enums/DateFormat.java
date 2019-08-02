package zcq.myjpa.common.enums;

/**
 * @author zhengchuqin
 * @history 2019-08-02 zhengchuqin 新建
 * @since JDK1.8
 */
public enum DateFormat {
    DATE_TIME_FORMAT("yyyy-MM-dd HH:mm:ss","^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$"),
    DATE_TIME_MIN_FORMAT("yyyy-MM-dd HH:mm","^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$"),
    DATE_FORMAT("yyyy-MM-dd","^\\d{4}-\\d{1,2}-\\d{1,2}$"),
    DATE_LONG_FORMAT("yyyyMMdd","^\\d{4}\\d{1,2}\\d{1,2}$"),
    TIME_FORMAT("hh:mm:ss","^\\d{1,2}:\\d{1,2}:\\d{1,2}$"),
    TIME_MIN_FORMAT("hh:mm","^\\d{1,2}:\\d{1,2}$");

    DateFormat(String format, String pattern) {
        this.format = format;
        this.pattern = pattern;
    }

    private String format;
    private String pattern;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getPattern() {
        return pattern;
    }

    public void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
