package com.lhw.mongo.exception;

/**
 * @author ：linhw
 * @date ：22.6.23 09:42
 * @description：
 * @modified By：
 */
public class MongoHandlerException {

    public static class CommonException extends AbstractException{

        public CommonException(String message) {
            super(message);
        }
    }

    public static class DataExist extends AbstractException {
        public DataExist(String message) {
            super(message + "已存在");
        }
    }

    public static class DataNotExist extends AbstractException {
        public DataNotExist(String message) {
            super(message + "不存在");
        }
    }

    public static class FileUploadFail extends AbstractException {
        public FileUploadFail(String fileName) {
            super(fileName + "上传失败");
        }
    }

    public static class FileDownloadFail extends AbstractException {
        public FileDownloadFail(String fileName) {
            super(fileName + "下载失败");
        }
    }

    public static class FileNoExist extends AbstractException {
        public FileNoExist(String fileName) {
            super(fileName + "文件不存在");
        }
    }

    public static class FileNameNotDefinition extends AbstractException{
        public FileNameNotDefinition(String message) {
            super(message + "文件名未定义");
        }
    }

    public static class StringConvertFail extends AbstractException {
        public StringConvertFail(String s) {
            super(s + "转码失败");
        }
    }

    public static class VerifyPowerFail extends AbstractException {
        public VerifyPowerFail(String msg){super("无" + msg + "权限");}
    }

}
