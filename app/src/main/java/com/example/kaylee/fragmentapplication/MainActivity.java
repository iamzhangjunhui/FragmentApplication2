package com.example.kaylee.fragmentapplication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Fragment[] fragments = {new BlankFragment(), new SecondFragment(), new ThirdFragment()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setFrame(0);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text1:
                setFrame(0);
                break;
            case R.id.text2:
                setFrame(1);
                break;
            case R.id.text3:
                setFrame(2);
                break;
        }
    }

    private void setFrame(int position) {
        Fragment willShowFrame = getSupportFragmentManager().findFragmentByTag(fragments[position].getClass().getName());
        if (willShowFrame != null && willShowFrame.isVisible()) {
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (Fragment fragment : fragments) {
            Fragment fra = getSupportFragmentManager().findFragmentByTag(fragment.getClass().getName());
            if (fra != null && fra.isAdded()) {
                transaction.hide(fra);
            }
        }
        if (willShowFrame!=null&&willShowFrame.isAdded()) {
            transaction.show(willShowFrame);
        } else {
            willShowFrame=fragments[position];
            transaction.add(R.id.frame, willShowFrame,willShowFrame.getClass().getName());
        }
        transaction.commit();
    }
}
