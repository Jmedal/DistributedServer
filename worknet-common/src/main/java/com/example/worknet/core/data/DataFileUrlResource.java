package com.example.worknet.core.data;

import org.springframework.core.io.FileUrlResource;
import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

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
