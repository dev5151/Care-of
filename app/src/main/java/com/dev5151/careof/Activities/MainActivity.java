package com.dev5151.careof.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.transition.Fade;
import android.widget.FrameLayout;

import com.dev5151.careof.Fragments.BasicFragment;
import com.dev5151.careof.Fragments.CategoryFragment;
import com.dev5151.careof.Fragments.LifeStyleFragment;
import com.dev5151.careof.Interfaces.CategoryInterface;
import com.dev5151.careof.Interfaces.OnClickInterface;
import com.dev5151.careof.Models.OptionsModel;
import com.dev5151.careof.R;

public class MainActivity extends AppCompatActivity {

    FragmentManager fragmentManager;
    ConstraintLayout frame;
    public static int flag = 0;
    static CategoryInterface categoryInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        frame = findViewById(R.id.frame);
        fragmentManager = getSupportFragmentManager();

        fragmentTransition(new CategoryFragment());

        categoryInterface = new CategoryInterface() {
            @Override
            public void switchToFragmentLifeStyle() {
                fragmentTransition(new LifeStyleFragment());
            }

            @Override
            public void switchToFragmentCategory() {
                fragmentTransition(new CategoryFragment());
            }

            @Override
            public void switchToFragmentBasic() {
                fragmentTransition(new BasicFragment());
            }
        };


    }

    private void fragmentTransition(Fragment fragment) {
        fragment.setEnterTransition(new Fade());
        fragment.setExitTransition(new Fade());
        fragmentManager.beginTransaction()
                .replace(R.id.frame, fragment)
                .commit();
    }

    public static CategoryInterface getCategoryInterface() {
        return categoryInterface;
    }
}
