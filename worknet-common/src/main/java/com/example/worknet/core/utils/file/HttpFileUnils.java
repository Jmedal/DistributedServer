package com.example.worknet.core.utils.file;

import com.example.worknet.common.constant.Const;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.multipart.MultipartHttpServletRequest;


/**
 * @Author: YunJieJiang
 * @Date: Created in 16:45 2019/7/9 0009
 */
public class HttpFileUnils {


    /**
     * 保存或更新一个文件
     * @param request       Request类
     * @param savePath      保存的相对路径
     * @param oldPath       旧文件路径
     * @param defaultPath   默认文件避免删除
     * @return
     */
    public static String requestFile(MultipartHttpServletRequest request, String savePath, String oldPath, String defaultPath) {
        if(request.getFile("avatar") == null || request.getFile("avatar").getSize() == 0)
            return null;
        if(oldPath != null && !oldPath.equals("") && defaultPath != null && !defaultPath.equals(oldPath))
            FileToolsUtil.deleteFile(Const.FILE_PATH + oldPath);    //删除旧图片
        //绝对保存路径
        String file_full_path = Const.FILE_PATH + savePath;
        //保存文件
        String file_name = FileToolsUtil.fileUpload(request.getFile("avatar"), FileToolsUtil.createDiretory(file_full_path));
        //文件路径信息
        return savePath + Const.FILE_SEPARATOR + file_name;
    }

    /**
     * 返回一个文件
     * @param resourceLoader     资源统一处理器
     * @param filePath          文件绝对保存路径
     * @param strDirPath        tomcat服务器根目录路径
     * @return
     */
    public static Resource responseFile(ResourceLoader resourceLoader, String filePath, String strDirPath) {
        if(filePath == null || strDirPath == null)
           throw new RuntimeException();
        filePath = Const.FILE_PATH + filePath;
        //tomcat服务器资源路径
        strDirPath = strDirPath + "WEB-INF" + Const.FILE_SEPARATOR + "classes" + Const.FILE_SEPARATOR + "static" + Const.FILE_SEPARATOR + "upload";
        //复制文件
        FileToolsUtil.fileToUpload(strDirPath, filePath);
        //文件名
        String file_name = filePath.substring(filePath.lastIndexOf(Const.FILE_SEPARATOR) + 1);
        return resourceLoader.getResource("file:" + strDirPath + Const.FILE_SEPARATOR + file_name);
    }

    /**
     * 复制文件
     * @param savePath
     * @param oldPath
     * @return
     */
    public static boolean copyFile(String savePath, String oldPath) {
        if(savePath == null || oldPath == null)
            return false;
        String save_full_path = Const.FILE_PATH + savePath;
        String old_full_path = Const.FILE_PATH + oldPath;
        FileToolsUtil.fileToUpload(save_full_path, old_full_path);
        return true;
    }

}
