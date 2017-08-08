package com.android.fastcampus.kwave.plot;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    ImageView postImg;
    TextView textTitle, textPeriod, textAddr;
    Button btnBooking, btnLocation, btnReview, btnWant;
    Fragment ExDetail, ExInfo, ExMap, ExReview;
    List<Fragment> pages;
    PagerAdapter adapter;
    TabLayout tab;
    ViewPager pager;
    private final int REQ_PERMISSION = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setWidget();
        setTabLayout();
        setViewPager();
        setFragments();
        setTab2Pager();
        setPageAdapter();

    }
    /*
    위젯 세팅
     */
    private void setWidget(){

        textTitle = (TextView) findViewById(R.id.textTitle);
        textPeriod = (TextView) findViewById(R.id.textPeriod);
        textAddr = (TextView) findViewById(R.id.textAddr);

        postImg = (ImageView) findViewById(R.id.postImg);

        btnBooking = (Button) findViewById(R.id.btnBooking);
        btnLocation = (Button) findViewById(R.id.btnLocation);
        btnReview = (Button) findViewById(R.id.btnReview);
        btnWant = (Button) findViewById(R.id.btnWant);

    }
    /*
   뷰페이저 생성
    */
    private void setViewPager(){
        pager = (ViewPager) findViewById(R.id.pager);
    }

    /*
    뷰페이저와 탭레이아웃 연결
    */
    private void setTab2Pager(){
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tab));
        tab.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager));

    }

    /*
     뷰페이져 아답터 연결
     */
    private void setPageAdapter(){
        adapter = new PagerAdapter(getSupportFragmentManager(),pages);
        pager.setAdapter(adapter);

    }
    /*
    뷰페이저 아답터
     */
    class PagerAdapter extends FragmentStatePagerAdapter {
        List<Fragment> pagelist;
        public PagerAdapter(FragmentManager fm, List<Fragment> pager) {
            super(fm);
            this.pagelist = pager;
        }
        @Override
        public Fragment getItem(int position) {
            return pagelist.get(position);
        }
        @Override
        public int getCount() {
            return pagelist.size();
        }
    }

    /*
    프래그먼트 세팅
    */
    private void setFragments(){
        pages = new ArrayList<>();
        ExDetail = new ExDetailFragment();
        ExInfo = new ExInfoFragment();
        ExMap = new ExMapFragment();
        ExReview = new ExReviewFragment();

        pages.add(ExDetail);
        pages.add(ExInfo);
        pages.add(ExMap);
        pages.add(ExReview);
    }

    /*
   탭레이아웃 세팅
   */
    private void setTabLayout(){
        tab = (TabLayout) findViewById(R.id.tab);
        tab.addTab(tab.newTab().setText("전시 상세"));
        tab.addTab(tab.newTab().setText("전시 정보"));
        tab.addTab(tab.newTab().setText("관람평"));
        tab.addTab(tab.newTab().setText("전시장 위치"));
    }

}
