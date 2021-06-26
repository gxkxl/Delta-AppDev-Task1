package com.example.guesstheday;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView qn;
    Button op1;
    Button op2;
    Button op3;
    Button op4;
    Integer day_no;
    String day;
    String date_day;
    String date;
    String score_str;
    String[] date_array;
    String[] days={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
    Integer score;

    public int dec()
    {
        score=0;
        return score;
    }
    Integer ans_opt;


    private String gen_ran_Date()//creating random date
    {
        GregorianCalendar gc= new GregorianCalendar();
        int year = randBetween(2000,2025);
        gc.set(GregorianCalendar.YEAR,year);
        int day_of_Year = randBetween(1,gc.getActualMaximum(GregorianCalendar.DAY_OF_YEAR));
        gc.set(GregorianCalendar.DAY_OF_YEAR,day_of_Year);
        int fin_year=gc.get(GregorianCalendar.YEAR);
        int fin_month=(gc.get(GregorianCalendar.MONTH)+1);
        int fin_date=gc.get(GregorianCalendar.DAY_OF_MONTH);
        int fin_day=gc.get(GregorianCalendar.DAY_OF_WEEK);
        return fin_year+" "+fin_month+" "+fin_date+" "+fin_day;
    }


    private static int randBetween(int start, int end)//random function
    {
        return start + (int)Math.round(Math.random()*(end - start));
    }


    private int qn_setter()//function for question setting
    {
        date_day=gen_ran_Date();//getting date
        date_array = date_day.split("\\s+");//array of date
        date=date_array[0]+" "+date_array[1]+" "+date_array[2];//splitting date alone
        qn.setText(date);
        day_no=Integer.parseInt(date_array[3]);
        day=days[day_no-1];//getting day

        ArrayList<String> days_list= new ArrayList<String>();//creating list with all days to get options
        days_list.add("Sunday");
        days_list.add("Monday");
        days_list.add("Tuesday");
        days_list.add("Wednesday");
        days_list.add("Thursday");
        days_list.add("Friday");
        days_list.add("Saturday");

        days_list.remove(day_no-1);//removing the answer for getting other 3 random options

        //removing 3 days randomly using shuffle method
        Collections.shuffle(days_list);
        days_list.remove(0);
        Collections.shuffle(days_list);
        days_list.remove(0);
        Collections.shuffle(days_list);
        days_list.remove(0);

        days_list.add(day);//adding the answer to final options list

        Collections.shuffle(days_list);//shuffling the list
        ans_opt=days_list.indexOf(day);//getting the index of answer

        //assigning the options in buttons
        op1.setText((String) days_list.get(0));
        op2.setText((String) days_list.get(1));
        op3.setText((String) days_list.get(2));
        op4.setText((String) days_list.get(3));

        return (ans_opt);
    }



    private void answer_check(Integer ans_op)//function for checking the answers
    {

        op1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(ans_op == 0)
                {
                    score+=1;
                    new_qn();
                }
                else
                {
                    wrong_ans(score);
                }
            }
        });
        op2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(ans_op==1)
                {
                    score+=1;
                    new_qn();
                }
                else
                {
                    wrong_ans(score);
                }
            }
        });
        op3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(ans_op==2)
                {
                    score+=1;
                    new_qn();
                }
                else
                {
                    wrong_ans(score);
                }
            }
        });
        op4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                if(ans_op==3)
                {
                    score+=1;
                    new_qn();
                }
                else
                {
                    wrong_ans(score);
                }
            }
        });

    }


    private void new_qn()//function for invoking the new question set
    {
        qn_setter();
        answer_check(ans_opt);
    }

    public void wrong_ans(Integer score)//function for invoking the score page activity after user selects wrong option
    {
        Intent result_page=new Intent(this, score_page.class);
        score_str=String.valueOf(score);
        result_page.putExtra("final_score",score_str);
        startActivity(result_page);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        qn=(TextView) findViewById(R.id.question);
        op1=(Button)findViewById(R.id.option1);
        op2=(Button)findViewById(R.id.option2);
        op3=(Button)findViewById(R.id.option3);
        op4=(Button)findViewById(R.id.option4);

        dec();
        new_qn();

    }
}