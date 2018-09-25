package fahri.com.kamus.ui.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import fahri.com.kamus.ui.eng_indkamus.FragmentEngIndo;
import fahri.com.kamus.ui.ind_engkamus.FragmentIndEng;


public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private static final String TAG = "MainPagerAdapter";

    private int mTabCount;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
        mTabCount = 0;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
             return FragmentIndEng.newInstance();

            case 1:
             return FragmentEngIndo.newInstance();


            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mTabCount;
    }

    public void setCount(int count) {
        mTabCount = count;
    }
}
