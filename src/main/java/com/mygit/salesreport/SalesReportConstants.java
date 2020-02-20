package com.mygit.salesreport;

public class SalesReportConstants {

    public static final String DIRECTORY_PATH = System.getProperty("user.dir") + "\\data\\";

    public static final String INPUT_FILE_DIRECTORY_PATH = DIRECTORY_PATH + "in\\";

    public static final String OUTPUT_FILE_DIRECTORY_PATH = DIRECTORY_PATH + "out\\";

    public static final String OUTPUT_FILE_NAME = "output_" + System.currentTimeMillis() + ".txt";

    public static final String RMQ_QUEUE_NAME = "filesQueue";
}
