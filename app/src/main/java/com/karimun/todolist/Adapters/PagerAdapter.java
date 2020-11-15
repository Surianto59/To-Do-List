package com.karimun.todolist.Adapters;

import com.karimun.todolist.Fragments.Day3Fragment;
import com.karimun.todolist.Fragments.Day4Fragment;
import com.karimun.todolist.Fragments.Day5Fragment;
import com.karimun.todolist.Fragments.Day6Fragment;
import com.karimun.todolist.Fragments.Day7Fragment;
import com.karimun.todolist.Fragments.TodayFragment;
import com.karimun.todolist.Fragments.TomorrowFragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

// Setup a pager adapter to define ViewPager's behavior
public class PagerAdapter extends FragmentStatePagerAdapter {

    int numOfTabs;

    public PagerAdapter(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm, numOfTabs);
        this.numOfTabs = numOfTabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch(position) {
            case 0:
                return new TodayFragment();
            case 1:
                return new TomorrowFragment();
            case 2:
                return new Day3Fragment();
            case 3:
                return new Day4Fragment();
            case 4:
                return new Day5Fragment();
            case 5:
                return new Day6Fragment();
            case 6:
                return new Day7Fragment();
        }
        return null;
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
