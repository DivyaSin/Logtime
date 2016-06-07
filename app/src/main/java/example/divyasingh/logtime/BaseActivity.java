package example.divyasingh.logtime;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;

public class BaseActivity extends AppCompatActivity{
    private FrameLayout mContentFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_base);

        mContentFrame = (FrameLayout) findViewById(R.id.frame_container);
    }

    @Override
    public void setContentView(int layoutResID) {
        getLayoutInflater().inflate(layoutResID, mContentFrame, true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();

                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void finish() {
        super.finish();
    }
}
