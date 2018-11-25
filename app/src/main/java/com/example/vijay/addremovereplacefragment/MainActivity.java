package com.example.vijay.addremovereplacefragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        textView = findViewById(R.id.textview);

        fragmentManager = getSupportFragmentManager();
        textView.setText("counter = " + fragmentManager.getBackStackEntryCount());
        fragmentManager.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                textView.setText("fragment counter = " + fragmentManager.getBackStackEntryCount());
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment();
            }
        });


    }

    private void addFragment() {
        Fragment fragment;
//        switch (fragmentManager.getBackStackEntryCount()) {
//            case 0:
//                fragment = new BlankFragment();
//                break;
//            case 1:
//                fragment = new BlankFragment1();
//                break;
//            case 2:
//                fragment = new BlankFragment2();
//                break;
//            default:
//                fragment = new BlankFragment();
//                break;
//        }


        fragment=fragmentManager.findFragmentById(R.id.fragmentContainer);
        if (fragment instanceof BlankFragment){
            fragment=new BlankFragment1();
        }
        else if (fragment instanceof BlankFragment1){
            fragment=new BlankFragment2();
        }
        else {
            fragment=new BlankFragment();


        }



        fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.add(R.id.fragmentContainer,fragment);
        fragmentTransaction.replace(R.id.fragmentContainer,fragment);
        //fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = fragmentManager.findFragmentById(R.id.fragmentContainer);
        if (fragment != null) {
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.remove(fragment);
            fragmentTransaction.commit();
        } else {
            super.onBackPressed();
        }

    }
}
