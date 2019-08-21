package zcq.myjpa.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import zcq.myjpa.common.enums.ApplyType;
import zcq.myjpa.common.enums.DateFormat;
import zcq.myjpa.exceptions.ServiceException;

import java.lang.ref.SoftReference;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhengchuqin
 * @history 2019-08-02 zhengchuqin 新建
 * @since JDK1.8
 */
public class DateUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtil.class);
    private static final List<String> FORMATS = new ArrayList<>();
    private static final List<String> PATTERNS = new ArrayList<>();

    /**
     * 日期字符串自动转换Date
     * @param dateStr
     * @return Date
     */
    public static Date convert (String dateStr) {
        if (StringUtils.isBlank(dateStr)) {
            throw  new ServiceException("时间转换: 字符串为空，不能转换");
        }
        String datePattern = null;
        for (DateFormat df : DateFormat.values()) {
            if (dateStr.matches(df.getPattern())) {
                datePattern = df.getFormat();
            }
        }
        SimpleDateFormat sdf = DateFormatHolder.formatFor(datePattern);
        try {
            return sdf.parse(dateStr);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }

    /**
     * 清除线程副本
     */
    public static void clearThreadLocal() {
        DateFormatHolder.clearThreadLocal();
    }

    static final class DateFormatHolder {

        /** 每个线程获取一个SimpleDateFormat对象 避免线程时共享出现多线程问题 使用软引用 便于jvm回收*/
        private static final ThreadLocal<SoftReference<Map<String, SimpleDateFormat>>> THREADLOCAL_FORMATS = new ThreadLocal() {
            @Override
            protected SoftReference<Map<String, SimpleDateFormat>> initialValue() {
                return new SoftReference(new HashMap());
            }
        };
        /** 获取SimpleDateFormat
         * @param pattern pattern
         * @return SimpleDateFormat
         */
        public static SimpleDateFormat formatFor(String pattern) {
            SoftReference ref = THREADLOCAL_FORMATS.get();
            Map formats = (Map) ref.get();
            if (formats == null) {
                formats = new HashMap();
                THREADLOCAL_FORMATS.set(new SoftReference(formats));
            }

            SimpleDateFormat format = (SimpleDateFormat) formats.get(pattern);
            if (format == null) {
                format = new SimpleDateFormat(pattern);
                formats.put(pattern, format);
            }
            return format;
        }

        public static void clearThreadLocal() {
            THREADLOCAL_FORMATS.remove();
        }
    }
}
