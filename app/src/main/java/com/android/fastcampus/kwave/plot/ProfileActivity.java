package com.android.fastcampus.kwave.plot;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageView;

import com.android.fastcampus.kwave.plot.adapter.ViewPagerAdapter;
import com.bumptech.glide.Glide;
import com.facebook.Profile;
import com.facebook.login.widget.ProfilePictureView;

public class ProfileActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    ImageView userProfileImage;
    ProfilePictureView profilePictureView;
    String fbid;

    public static final int REQ_CODE_SELECT_IMAGE = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        profilePictureView.setProfileId();
        initProfileImage(fbid);
        initViewPager();
        initTabLayout();

        Log.i("id", fbid);
    }


    /*
        프로필 이미지를 띄우고, 바꾸는 함수.
        프로필 이미지 변경은 현재 갤러리를 열어서 사진을 선택하는 정도까지만 구현함.
     */

    private void initProfileImage(String facebookid){
        userProfileImage = (ImageView) findViewById(R.id.userProfileImage);
        Glide.with(this).load("http://graph.facebook.com/" + facebookid + "picture?width=500&height=500").into(userProfileImage);
        userProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
            }
        });
    }

    /*
    뷰페이저 구현
     */

    private void initViewPager(){
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
    }

    /*
    탭 레이아웃 구현
     */

    private void initTabLayout(){
        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

}
