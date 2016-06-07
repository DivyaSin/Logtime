package example.divyasingh.logtime;

import android.app.Application;

public class LogTimeApp extends Application {

    private static LogTimeApp _instance;

    @Override
    public void onCreate() {
        super.onCreate();
        _instance = this;
    }

    public static LogTimeApp getInstance() {
        return _instance;
    }
}





