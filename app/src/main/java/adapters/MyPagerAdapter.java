package adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

import com.zari.matan.testapk.R;

import fragments.FavoriteFragment;
import fragments.HomeFragment;
import fragments.NotificationsFragment;
import fragments.SearchFragment;

/**
 * Created by Matan on 4/19/2015.
 */
public class MyPagerAdapter extends FragmentStatePagerAdapter {

    private Context context;
    private int NumbOfTabs; // Store the number of tabs, this will also be passed when the ViewPagerAdapter is created
    protected int[] imageResId;

    // Build a Constructor and assign the passed Values to appropriate values in the class
    public MyPagerAdapter(Context context, FragmentManager fm, int[] imageResId, int mNumbOfTabsumb) {
        super(fm);
        this.context = context;
        this.imageResId = imageResId;
        this.NumbOfTabs = mNumbOfTabsumb;

    }

    //This method return the fragment for the every position in the View Pager
    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new NotificationsFragment();
                break;
            case 2:
                fragment = new SearchFragment();
                break;
            default:
                fragment = new FavoriteFragment();
                break;


        }

        return fragment;
    }

    // This method return the titles for the Tabs in the Tab Strip

    @Override
    public CharSequence getPageTitle(int position) {
        Drawable image = context.getResources().getDrawable(imageResId[position]);
        image.setBounds(0, 0, image.getIntrinsicWidth(), image.getIntrinsicHeight());
        SpannableString sb = new SpannableString(" ");
        ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
        sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return sb;
    }

    // This method return the Number of tabs for the tabs Strip

    @Override
    public int getCount() {
        return NumbOfTabs;

    }

}
