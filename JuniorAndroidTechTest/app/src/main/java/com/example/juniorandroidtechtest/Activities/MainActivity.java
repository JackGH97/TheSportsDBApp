package com.example.juniorandroidtechtest.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import com.example.juniorandroidtechtest.Database.DataBaseHelper;
import com.example.juniorandroidtechtest.Fragments.HomeFragment;
import com.example.juniorandroidtechtest.Fragments.TeamSelectionFragment;
import com.example.juniorandroidtechtest.R;


public class MainActivity extends AppCompatActivity {

    // Database Management
    DataBaseHelper mDataBaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDataBaseHelper = new DataBaseHelper(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        updateUI();
    }

    @Override
    public void onStart() {
        super.onStart();
    }


    // Inflate Toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            getMenuInflater().inflate(R.menu.main_manu, menu);
        return true;
    }

    // Dealing with toolbar actions
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_more) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Please Confirm");
            builder.setMessage("Do you want to select a different Team?");

            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    mDataBaseHelper.getReadableDatabase();
                    mDataBaseHelper.deleteTableColumn();
                    getSupportFragmentManager().popBackStack();
                    Fragment fragment = null;
                    Class fragmentClass = null;
                    String ftag = "";
                    ftag = "Team";
                    fragmentClass = TeamSelectionFragment.class;

                    try {
                        fragment = (Fragment) fragmentClass.newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    FragmentManager fragmentManager = getSupportFragmentManager();

                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.flContent, fragment, ftag);
                    fragmentTransaction.addToBackStack(ftag);
                    fragmentTransaction.commit();

                }
            });

            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();
            }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Back Button Functionality
     */
    @Override
    public void onBackPressed() {
        TeamSelectionFragment myFragment = (TeamSelectionFragment)getSupportFragmentManager().findFragmentByTag("Team");
        if (myFragment != null && myFragment.isVisible()) {
            // disable button
        }

        else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setTitle("Please Confirm");
            builder.setMessage("Do you want to select a different Team?");

            builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    mDataBaseHelper.getReadableDatabase();
                    mDataBaseHelper.deleteTableColumn();
                    getSupportFragmentManager().popBackStack();
                    Fragment fragment = null;
                    Class fragmentClass = null;
                    String ftag = "";
                    ftag = "Team";
                    fragmentClass = TeamSelectionFragment.class;

                    try {
                        fragment = (Fragment) fragmentClass.newInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    FragmentManager fragmentManager = getSupportFragmentManager();

                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.flContent, fragment, ftag);
                    fragmentTransaction.addToBackStack(ftag);
                    fragmentTransaction.commit();

                }
            });

            builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            AlertDialog alert = builder.create();
            alert.show();
        }
    }


    /**
     *
     */
    private void updateUI() {
        String ftag = "";
        Fragment fragment = null;
        Class fragmentClass = null;

        if(mDataBaseHelper.tableLength() > 0){
            ftag = "Home";
            fragmentClass = HomeFragment.class;
        }
        else {
            ftag = "Team";
            fragmentClass = TeamSelectionFragment.class;
        }
        try {
            fragment = (Fragment) fragmentClass.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.flContent, fragment, ftag);
        fragmentTransaction.addToBackStack(ftag);
        fragmentTransaction.commit();

    }

}




