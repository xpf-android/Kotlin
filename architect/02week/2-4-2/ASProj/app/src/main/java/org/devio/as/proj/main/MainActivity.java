package org.devio.as.proj.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import org.devio.as.proj.common.ui.component.HiBaseActivity;
import org.devio.as.proj.main.R;
import org.devio.as.proj.main.logic.MainActivityLogic;

public class MainActivity extends HiBaseActivity implements MainActivityLogic.ActivityProvider {

    private MainActivityLogic activityLogic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activityLogic = new MainActivityLogic(this, savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        activityLogic.onSaveInstanceState(outState);
    }
}
