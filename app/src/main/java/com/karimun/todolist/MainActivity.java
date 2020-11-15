package com.karimun.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.material.tabs.TabLayout;
import com.karimun.todolist.Adapters.PagerAdapter;
import com.karimun.todolist.Fragments.TodayFragment;
import com.karimun.todolist.databinding.ActivityMainBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private InterstitialAd interstitialAd;
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(getString(R.string.ad_unit_id_interstitial));
        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        interstitialAd.loadAd(adRequest);

        adView = findViewById(R.id.ad_view);
        adView.loadAd(adRequest);

        setSupportActionBar(binding.toolbar);

        String dates[] = setupDateOnTabs();

        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Today"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText("Tomorrow"));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(dates[0]));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(dates[1]));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(dates[2]));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(dates[3]));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(dates[4]));
        binding.tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(), 7);
        binding.viewPager.setAdapter(pagerAdapter);
        binding.viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(binding.tabLayout));

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                binding.viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        // Change text color in tab layout based on day/night mode toggled
        if (AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_YES){
            binding.tabLayout.setTabTextColors(Color.WHITE, R.color.colorPrimaryDark);
        }
        else{
            binding.tabLayout.setTabTextColors(Color.GRAY, R.color.colorPrimaryDark);
        }
    }

    // Setup date from day3 to day7
    public static String[] setupDateOnTabs() {
        Calendar c3 = Calendar.getInstance();
        int year = c3.get(Calendar.YEAR);
        int month = c3.get(Calendar.MONTH) + 1;
        int day3 = c3.get(Calendar.DAY_OF_MONTH) + 2;
        int day4 = c3.get(Calendar.DAY_OF_MONTH) + 3;
        int day5 = c3.get(Calendar.DAY_OF_MONTH) + 4;
        int day6 = c3.get(Calendar.DAY_OF_MONTH) + 5;
        int day7 = c3.get(Calendar.DAY_OF_MONTH) + 6;

        String d3=null,d4=null,d5=null,d6=null,d7=null;

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy", Locale.getDefault());

        try {
            Date date3 = simpleDateFormat.parse(String.format("%s/%s/%s", day3, month, year));
            Date date4 = simpleDateFormat.parse(String.format("%s/%s/%s", day4, month, year));
            Date date5 = simpleDateFormat.parse(String.format("%s/%s/%s", day5, month, year));
            Date date6 = simpleDateFormat.parse(String.format("%s/%s/%s", day6, month, year));
            Date date7 = simpleDateFormat.parse(String.format("%s/%s/%s", day7, month, year));

            if (date3!=null && date4!=null && date5!=null && date6!=null && date7!=null) {
                d3 = simpleDateFormat.format(date3);
                d4 = simpleDateFormat.format(date4);
                d5 = simpleDateFormat.format(date5);
                d6 = simpleDateFormat.format(date6);
                d7 = simpleDateFormat.format(date7);
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        String results[] = {d3, d4, d5, d6, d7};

        return results;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        int nightMode = AppCompatDelegate.getDefaultNightMode();
        if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
            menu.findItem(R.id.night_mode).setTitle("Day Mode");
        }
        else{
            menu.findItem(R.id.night_mode).setTitle("Night Mode");
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.remove_current_task:
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setMessage("Deleted tasks can't be recovered.\nAre you sure you want to delete tasks for this day?")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                switch (binding.viewPager.getCurrentItem()) {
                                    case 0:
                                        TodayFragment.taskViewModel.removeTodayList();
                                        break;
                                    case 1:
                                        TodayFragment.taskViewModel.removeTomorrowList();
                                        break;
                                    case 2:
                                        TodayFragment.taskViewModel.removeDay3List();
                                        break;
                                    case 3:
                                        TodayFragment.taskViewModel.removeDay4List();
                                        break;
                                    case 4:
                                        TodayFragment.taskViewModel.removeDay5List();
                                        break;
                                    case 5:
                                        TodayFragment.taskViewModel.removeDay6List();
                                        break;
                                    case 6:
                                        TodayFragment.taskViewModel.removeDay7List();
                                        break;
                                }
                            }
                        })
                        .setNegativeButton("Cancel",null);

                builder.show();
                break;
            case R.id.remove_all_tasks:
                AlertDialog.Builder builderAll = new AlertDialog.Builder(this)
                        .setMessage("This will remove the entire tasks.\nDeleted tasks can't be recovered.\n\nAre you sure you want to delete all tasks?")
                        .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                TodayFragment.taskViewModel.removeAllLists();
                            }
                        })
                        .setNegativeButton("Cancel",null);
                builderAll.show();
                break;
            case R.id.night_mode:
                int nightMode = AppCompatDelegate.getDefaultNightMode();
                if (nightMode==AppCompatDelegate.MODE_NIGHT_YES) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                recreate();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (interstitialAd.isLoaded())
            interstitialAd.show();
        super.onBackPressed();
    }
}