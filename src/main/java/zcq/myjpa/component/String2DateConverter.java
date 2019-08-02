package zcq.myjpa.component;


import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import zcq.myjpa.utils.DateUtil;

import java.util.Date;

/**
 * @author zhengchuqin
 * @history 2019-08-02 zhengchuqin 新建
 * @since JDK1.8
 */
@Component
public class String2DateConverter implements Converter<String, Date> {

    @Override
    public Date convert(String s) {
        return DateUtil.convert(s);
    }
}
