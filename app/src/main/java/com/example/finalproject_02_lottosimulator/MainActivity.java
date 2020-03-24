package com.example.finalproject_02_lottosimulator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.finalproject_02_lottosimulator.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends BaseActivity{

    List<TextView> winNumTxtList = new ArrayList<>();
    int[] winLottoNumArr = new int[6];
    int bonusNum = 0;

    long useMoneyAmount = 0;

    ActivityMainBinding binding = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        binding.buyOneLottoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeWinLottoNum();
//                몇등인지 판단.
                checkLottoRank();

            }
        });

    }

    @Override
    public void setValues() {

        winNumTxtList.add(binding.winLottoNumTxt01);
        winNumTxtList.add(binding.winLottoNumTxt02);
        winNumTxtList.add(binding.winLottoNumTxt03);
        winNumTxtList.add(binding.winLottoNumTxt04);
        winNumTxtList.add(binding.winLottoNumTxt05);
        winNumTxtList.add(binding.winLottoNumTxt06);

    }

    void makeWinLottoNum() {
        for (int i = 0; i < winLottoNumArr.length; i++) {
            winLottoNumArr[i] = 0;
        }
        bonusNum = 0;

        for (int i = 0; i < winLottoNumArr.length; i++) {

            while (true) {

                int randomNum = (int) (Math.random()*45 + 1);

                boolean isDuplOk = true;

                for (int winNum : winLottoNumArr) {

                    if (winNum == randomNum) {
                        isDuplOk = false;
                        break;
                    }

                }

                if (isDuplOk) {
                    winLottoNumArr[i] = randomNum;
                    Log.i("당첨번호", randomNum+"");
                    break; // 무한반복 탈출
                }

            }

        }

//        6개의 당첨번호를
        Arrays.sort(winLottoNumArr);

        for (int i = 0; i < winLottoNumArr.length; i++ ) {
            winNumTxtList.get(i).setText(winLottoNumArr[i]+"");
        }

        while (true) {
            int randomNum = (int) (Math.random()*45+1);

            boolean isDuplOk = true;
            for (int winNum : winLottoNumArr) {
                if (winNum == randomNum) {
                    isDuplOk = false;
                    break;
                }
            }

            if (isDuplOk) {
                bonusNum = randomNum;
                break;
            }
        }

        binding.bonusNumTxt.setText(bonusNum+"");



    }

    void  checkLottoRank(){
//      돈을 천원 지불 + 등수 확인
        useMoneyAmount += 1000;

        binding.useMoneyTxt.setText(String.format("사용 금액 : %,d원",useMoneyAmount));

    }
}