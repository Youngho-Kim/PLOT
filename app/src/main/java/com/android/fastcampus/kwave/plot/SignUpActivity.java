package com.android.fastcampus.kwave.plot;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText signUpEmail;
    private EditText signUpNickname;
    private EditText signUpUsername;
    private EditText signUpPassword;
    private EditText signUpCheckPassword;
    private Button signUpSaveButton;
    private Button signUpCancelButton;
    String email, pw, pwCheck, nickname, name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initView();
    }

    private void initView() {
        signUpEmail = (EditText) findViewById(R.id.signUpEmail);
        signUpNickname = (EditText) findViewById(R.id.signUpNickname);
        signUpUsername = (EditText) findViewById(R.id.signUpUsername);
        signUpPassword = (EditText) findViewById(R.id.signUpPassword);
        signUpCheckPassword = (EditText) findViewById(R.id.signUpCheckPassword);
        signUpSaveButton = (Button) findViewById(R.id.signUpSaveButton);
        signUpCancelButton = (Button) findViewById(R.id.signUpCancelButton);

        signUpSaveButton.setOnClickListener(this);
        signUpCancelButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.signUpSaveButton :
                save(v);
                break;
            case R.id.signUpCancelButton :
                intent = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(intent);
                break;

        }
    }

    /* onClick에서 정의한 이름과 똑같은 이름으로 생성 */
    public void save(View view)
    {
    /* 버튼을 눌렀을 때 동작하는 소스 */
        email = signUpEmail.getText().toString();
        nickname = signUpNickname.getText().toString();
        name = signUpUsername.getText().toString();
        pw = signUpPassword.getText().toString();
        pwCheck = signUpCheckPassword.getText().toString();

        if(pw.equals(pwCheck))
        {
        /* 패스워드 확인이 정상적으로 됨 */
            Toast.makeText(this, "패스워드가 맞습니다.", Toast.LENGTH_SHORT).show();
            RegistSignUp registSignUp = new RegistSignUp();
            registSignUp.execute();
        }
        else
        {
        /* 패스워드 확인이 불일치 함 */
            Toast.makeText(this, "패스워드가 안 맞습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    public class RegistSignUp extends AsyncTask<Void, Integer, Void> {

        @Override
        protected Void doInBackground(Void... unused) {

/* 인풋 파라메터값 생성 */
            String param = "u_id=" + email + "&u_pw=" + pw + "";
            try {
/* 서버연결 */
                URL url = new URL("plot.ejjeong.com/api/member/signup/");
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.connect();

/* 안드로이드 -> 서버 파라메터값 전달 */
                OutputStream outs = conn.getOutputStream();
                outs.write(param.getBytes("UTF-8"));
                outs.flush();
                outs.close();

/* 서버 -> 안드로이드 파라메터값 전달 */
                InputStream is = null;
                BufferedReader br = null;
                String data = "";

                is = conn.getInputStream();
                br = new BufferedReader(new InputStreamReader(is), 8 * 1024);
                String line = null;
                StringBuffer buff = new StringBuffer();
                while ( ( line = br.readLine() ) != null )
                {
                    buff.append(line + "\n");
                }
                data = buff.toString().trim();
                Log.e("RECV DATA",data);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

    }
}
