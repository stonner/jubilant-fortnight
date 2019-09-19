package zcq.myjpa.examples;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/08/13
 */
public interface Example {
    void doing1() throws IOException, Exception;

    void doing2() throws IOException, InvalidFormatException;
}
