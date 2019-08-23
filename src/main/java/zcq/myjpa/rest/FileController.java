package zcq.myjpa.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zcq.myjpa.utils.FileUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhengchuqin
 * @version 1.0
 * @since 2019/08/23
 */
@RestController
@RequestMapping("/file")
public class FileController {

    //http://localhost:8081/file/download?url=http://10.10.15.32:7000/water/onLine/testapi/FileExport/pdf/1563278169764.pdf

    @GetMapping("/download")
    public void download(String url, HttpServletResponse response) {
        try {
            byte[] bytes = FileUtils.fileToBytes(url);
            if (null != bytes && bytes.length > 0) {
                final ServletOutputStream outputStream = response.getOutputStream();
                outputStream.write(bytes);
                outputStream.flush();
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
