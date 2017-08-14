package com.android.fastcampus.kwave.plot;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.fastcampus.kwave.plot.DataSource.Records;
import com.android.fastcampus.kwave.plot.Fragments.ExDetailFragment;
import com.android.fastcampus.kwave.plot.Fragments.ExInfoFragment;
import com.android.fastcampus.kwave.plot.Fragments.ExReviewFragment;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareDialog;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView postImg;
    TextView textTitle, textPeriod, textAddr;
    Button btnBooking, btnLocation, btnReview, btnWant;
    ExDetailFragment ExDetail;
    ExInfoFragment ExInfo;
    ExReviewFragment ExReview;
    List<Fragment> pages;
    PagerAdapter adapter;
    TabLayout tab;
    ViewPager pager;
    int position;
    Bundle bundle;
    Records records;
    String listKey = "";
    private CallbackManager callbackManager;
    ShareDialog shareDialog;
    

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
        setData();
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
        btnBooking.setOnClickListener(this);

        btnLocation = (Button) findViewById(R.id.btnLocation);
        btnLocation.setOnClickListener(this);
        btnReview = (Button) findViewById(R.id.btnReview);
        btnReview.setOnClickListener(this);
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
    포스터 아래 만들어놓은 버튼별 기능 구현(현재 예약하기, 댓글쓰기만 구현함)
    */
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnBooking:
                goReserve();
                break;
            case R.id.btnLocation:
                goMap();
                break;
            case R.id.btnReview:
                goComment();
                break;
        }
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
        ExReview = new ExReviewFragment();

        pages.add(ExDetail);
        pages.add(ExInfo);
        pages.add(ExReview);
        ExDetail = (ExDetailFragment) getSupportFragmentManager().findFragmentById(R.id.pager);
        ExInfo = (ExInfoFragment) getSupportFragmentManager().findFragmentById(R.id.pager);
        ExReview = (ExReviewFragment) getSupportFragmentManager().findFragmentById(R.id.pager);

    }

    /*
   탭레이아웃 세팅
   */
    private void setTabLayout(){
        tab = (TabLayout) findViewById(R.id.tab);
        tab.addTab(tab.newTab().setText("전시 상세"));
        tab.addTab(tab.newTab().setText("전시 정보"));
        tab.addTab(tab.newTab().setText("관람평"));
    }

    /*
    예매하기 버튼을 누르면 예매하는 액티비티로 이동하는 함수
     */
    private void goReserve(){
        Intent intent = new Intent(DetailActivity.this, ReserveActivity.class);
        intent.putExtra("Title", textTitle.getText().toString());
        startActivity(intent);
    }

    /*
     댓글쓰기 버튼을 누르면 관람평을 남기는 액티비티로 이동하는 함수
     */
    private void goComment(){
        Intent intent = new Intent(DetailActivity.this, CommentActivity.class);
        intent.putExtra("Title", textTitle.getText().toString());
        startActivity(intent);
    }

    /*
    지도 액티비티로 이동하는 함수
     */
    private void goMap(){
        Intent intent = new Intent(DetailActivity.this, MapsActivity.class);
        intent.putExtra("fromDetailAddress", records.getAddress_road());
        intent.putExtra("fromDetailLocation", records.getLocation());
        startActivity(intent);
    }

    /**
     * ListActivity 에서 데이터 넘겨 받기
     */
    private void setData(){

        Intent intent = getIntent();
        position = intent.getIntExtra("POSITION", -1);
        listKey = intent.getStringExtra("ListId");
        bundle = intent.getExtras();
        switch (listKey){
            case "category":
                if(position > -1 ) {
                    records = (Records) bundle.getSerializable("fromList");
                    Log.i("records","================"+records);
                    ExInfo.records = records;
                    setValue();
                }
                break;

            case "MainActivity":
                if(position > -1 ) {
                    records = (Records) bundle.getSerializable("fromMain");
                    setValue();
                }
                break;
        }
    }

    /*
    ListActivity 정보 DetailActivity widget 에 연결
     */
    private void setValue(){
        textTitle.setText(records.getTitle());
        textPeriod.setText(records.getStartdate());
        textAddr.setText(records.getLocation());
    }

    //액션바 버튼 추가
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    //액션바의 공유버튼을 눌렀을 때 실행되는 함수
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FacebookSdk.sdkInitialize(getApplicationContext());
        shareDialog = new ShareDialog(this);
        callbackManager = CallbackManager.Factory.create();
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                Toast.makeText(DetailActivity.this, "공유 됬다", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(DetailActivity.this, "공유 취소 됬다", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(DetailActivity.this, "안된다.....ERROR", Toast.LENGTH_SHORT).show();
            }
        });
        ShareLinkContent shareLinkContent = new ShareLinkContent.Builder().setContentUrl(Uri.parse("http://naver.com")).build();
        shareDialog.show(shareLinkContent);
        return super.onOptionsItemSelected(item);
    }
}
