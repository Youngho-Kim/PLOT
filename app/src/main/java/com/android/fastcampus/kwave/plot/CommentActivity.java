package com.android.fastcampus.kwave.plot;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

public class CommentActivity extends AppCompatActivity {

    Button btnWriteComment;
    EditText textComment;
    String comment = "";
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
                Toast.makeText(getBaseContext(), comment+rate, Toast.LENGTH_SHORT).show();
            }
        });

    }
}
