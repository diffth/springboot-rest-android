package com.example.god.springboot_rest_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //Retrofit retrofit;
    TestService testService;

    @BindView(R.id.btnList)
    Button btnList;
    @BindView(R.id.btnView)
    Button btnView;
    @BindView(R.id.btnDel)
    Button btnDel;
    @BindView(R.id.btnInsert)
    Button btnInsert;
    @BindView(R.id.btnUpdate)
    Button btnUpdate;

    @BindView(R.id.textView)
    TextView textView;

    private MainActivity mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mContext = MainActivity.this;

        /*retrofit = new Retrofit.Builder()
                .baseUrl(TestService.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        testService = retrofit.create(TestService.class);
        Call<List<Test>> comment = testService.getComment();
        //Call<ResponseBody> comment = apiService.getComment(1);

        comment.enqueue(new Callback<List<Test>>() {
            @Override
            public void onResponse(Call<List<Test>> call, Response<List<Test>> response) {
                textView = (TextView) findViewById(R.id.textView);

                for (Test tList : response.body()) {
                    Log.v("Test", tList.getName());

                    textView.setText(tList.getName());
                }
            }
            @Override
            public void onFailure(Call<List<Test>> call, Throwable t) {

            }
        });
    }*/
    }

    /*@OnClick(R.id.btnList)
    public void onClick_btnList() {

        testService = TestService.Factory.getInstance(mContext);
        testService.getList().enqueue(new Callback<List<Test>>() {
            @Override
            public void onResponse(Call<List<Test>> call, Response<List<Test>> response) {

                if (response.isSuccessful()) {
                    for (Test getList : response.body()) {
                        textView.append(getList.getNo() + " " + getList.getName() + " " + getList.getAge() + "\n");
                    }
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Test>> call, Throwable t) {

            }
        });
    }*/

    @OnClick({R.id.btnList, R.id.btnView, R.id.btnDel, R.id.btnInsert, R.id.btnUpdate})
    public void onClick(View view) {
        testService = TestService.Factory.getInstance(mContext);

        switch (view.getId()) {

            case R.id.btnList:

                testService.getList().enqueue(new Callback<List<Test>>() {
                    @Override
                    public void onResponse(Call<List<Test>> call, Response<List<Test>> response) {

                        if (response.isSuccessful()) {
                            for (Test getList : response.body()) {
                                textView.append(getList.getNo() + " " + getList.getName() + " " + getList.getAge() + "\n");
                            }
                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call<List<Test>> call, Throwable t) {

                    }
                });
                break;
            case R.id.btnView:
                break;
            case R.id.btnDel:
                break;
            case R.id.btnInsert:
                break;
            case R.id.btnUpdate:
                break;
        }
    }
}
