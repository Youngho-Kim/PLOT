package com.android.fastcampus.kwave.plot;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ReserveActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    String[] number = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

    Spinner spinnerNormal, spinnerStudent, spinnerPackage, spinnerWeekday, spinnerWeekend;
    ArrayAdapter adapter;
    TextView txtPriceResult;
    Button btnGoBuy;
    ImageView btnReset;

    static int normalPrice, studentPrice, packagePrice, weekdayPrice, weekendPrice, resultPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        adapter = new ArrayAdapter(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, number);

        initView();
        setSpinnerAdapter(spinnerNormal);
        setSpinnerAdapter(spinnerStudent);
        setSpinnerAdapter(spinnerPackage);
        setSpinnerAdapter(spinnerWeekday);
        setSpinnerAdapter(spinnerWeekend);

    }

   public void initView(){
       spinnerNormal = (Spinner) findViewById(R.id.spinnerNormal);
       spinnerStudent = (Spinner) findViewById(R.id.spinnerStudent);
       spinnerPackage = (Spinner) findViewById(R.id.spinnerPackage);
       spinnerWeekday = (Spinner) findViewById(R.id.spinnerWeekday);
       spinnerWeekend = (Spinner) findViewById(R.id.spinnerWeekend);

       txtPriceResult = (TextView) findViewById(R.id.txtPriceResult);

       btnGoBuy = (Button) findViewById(R.id.btnGoBuy);
       btnGoBuy.setOnClickListener(this);
   }

   public void setSpinnerAdapter(Spinner spinner){
       spinner.setAdapter(adapter);
       spinner.setOnItemSelectedListener(this);
   }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnGoBuy:
                Toast.makeText(getBaseContext(), resultPrice + " 원 결제 되었습니다.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch(parent.getId()){
            case R.id.spinnerNormal:
                normalPrice = position * 20000;
                break;
            case R.id.spinnerStudent:
                studentPrice = position * 10000;
                break;
            case R.id.spinnerPackage:
                packagePrice = position * 28000;
                break;
            case R.id.spinnerWeekday:
                weekdayPrice = position * 15000;
                break;
            case R.id.spinnerWeekend:
                weekendPrice = position * 17000;
                break;
        }
        resultPrice = normalPrice + studentPrice + packagePrice + weekdayPrice + weekendPrice;
        txtPriceResult.setText(resultPrice+"");
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
