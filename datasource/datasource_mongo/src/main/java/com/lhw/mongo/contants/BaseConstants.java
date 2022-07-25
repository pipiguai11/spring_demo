package com.lhw.mongo.contants;

import java.text.SimpleDateFormat;

/**
 * @author ：linhw
 * @date ：22.6.16 23:14
 * @description：
 * @modified By：
 */
public class BaseConstants {

    public static final SimpleDateFormat YEAR_MONTH_DATE = new SimpleDateFormat("yyyyMMdd");

    public static final SimpleDateFormat YEAR_MONTH_DATE_V2 = new SimpleDateFormat("yyyy年MM月dd日");

    public static class FileConstants {

        public static class MongoGridFsMetadata {
            public static final String META_DATA_PREFIX = "metadata.";
            public static final String FILE_NAME = "filename";
            public static final String FILE_SUFFIX = "suffix";
            public static final String FILE_TYPE = "type";
            public static final String OBJECT_CODE = "object_code";
            public static final String FILE_ID = "_id";
        }

        public static class FileType {
            public static final String PICTURE = "picture";
            public static final String AUDIO = "audio";

            public static final int PICTURE_TYPE = 1;
            public static final int AUDIO_TYPE = 2;
        }

    }

}
