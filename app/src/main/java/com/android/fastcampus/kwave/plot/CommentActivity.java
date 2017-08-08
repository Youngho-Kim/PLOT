package com.android.fastcampus.kwave.plot;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.Calendar;

public class CommentActivity extends AppCompatActivity {

    Button btnWriteComment;
    EditText textComment;
    String comment = "";
    String date;
    RatingBar writeRatingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);


        writeRatingBar = (RatingBar) findViewById(R.id.writeRatingBar);
        btnWriteComment = (Button) findViewById(R.id.btnWriteComment);
        textComment = (EditText) findViewById(R.id.textComment);
        btnWriteComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                comment = textComment.getText().toString();
                float rate = writeRatingBar.getRating();
                Intent intent = new Intent();
                intent.putExtra("Comment", comment);
                intent.putExtra("Rate", rate);
                setDate();
                setNoti();
                Toast.makeText(getBaseContext(), "관람평 = " + comment + "평점 = " + rate + "설정한 날짜 = " + date, Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void setDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 8);
        calendar.set(Calendar.DATE, 28);
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);
        date = (calendar.get(Calendar.MONTH)) + "월" + (calendar.get(Calendar.DATE)) + "일";
    }

    public void setNoti(){
        int requestCode = 365;
        if(requestCode == 365) {
            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notification = new NotificationCompat.Builder(getApplicationContext())
                    .setContentTitle("예매 완료")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentText(date + "에 보실 전시를 예매하였습니다.")
                    .build();

            notificationManager.notify(requestCode, notification);
        }
    }
}
