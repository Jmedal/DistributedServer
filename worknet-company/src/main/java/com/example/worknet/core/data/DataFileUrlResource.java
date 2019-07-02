package com.example.worknet.core.data;

import org.springframework.core.io.FileUrlResource;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * @Author: YunJieJiang
 * @Date: Created in 21:22 2019/6/28 0028
 */
public class DataFileUrlResource extends FileUrlResource implements Serializable {
    public DataFileUrlResource(URL url) {
        super(url);
    }

    public DataFileUrlResource(String location) throws MalformedURLException {
        super(location);
    }
}
