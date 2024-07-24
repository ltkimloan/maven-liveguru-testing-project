package commons;

import java.io.File;

public class GlobalConstants {

    public static final String ADMIN_URL ="http://live.techpanda.org/index.php/backendlogin";
    public static final String USER_URL ="http://live.techpanda.org/";
    public static final long SHORT_TIMEOUT = 5;
    public static final long LONG_TIMEOUT = 30;

    public static final String DEV_ADMIN_USERNAME="user01";
    public static final String DEV_ADMIN_PASSWORD="guru99com";
    public static final String OS_NAME = System.getProperty("os.name");
    public static final String RELATIVE_PROJECT_PATH = System.getProperty("user.dir");
    public static final String JAVA_VERSION = System.getProperty("java.version");
    public static final String UPLOAD_PATH = RELATIVE_PROJECT_PATH + File.separator + "uploadFiles"+ File.separator;
    public static final String DOWLOAD_PATH = RELATIVE_PROJECT_PATH + File.separator + "downloadFiles"+ File.separator;
    public static final String DATA_TEST_PATH = RELATIVE_PROJECT_PATH + File.separator + "dataTest"+ File.separator;
    public static final String BROWSER_LOG_PATH = RELATIVE_PROJECT_PATH + File.separator + "browserLogs"+ File.separator;

}
