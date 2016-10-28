package com.example.god.springboot_rest_android;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
    @BindView(R.id.edView)
    EditText edView;
    @BindView(R.id.edInsertName)
    EditText edInsertName;
    @BindView(R.id.edInsertAge)
    EditText edInsertAge;
    @BindView(R.id.edUpdateName)
    EditText edUpdateName;
    @BindView(R.id.edUpdateAge)
    EditText edUpdateAge;

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

                //LIST
                testService.getList().enqueue(new Callback<List<Test>>() {
                    @Override
                    public void onResponse(Call<List<Test>> call, Response<List<Test>> response) {

                        if (response.isSuccessful()) {
                            textView.setText("");

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

                //READ
                testService.getView(edView.getText().toString().trim()).enqueue(new Callback<Test>() {
                    @Override
                    public void onResponse(Call<Test> call, Response<Test> response) {

                        if (response.isSuccessful()) {
                            textView.setText("");
                            Test testView = response.body();
                            textView.setText(testView.getNo() + " " + testView.getName() + " " + testView.getAge());
                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call<Test> call, Throwable t) {

                    }
                });
                break;
            case R.id.btnDel:

                //DELETE
                testService.getDelete(edView.getText().toString().trim()).enqueue(new Callback<Test>() {
                    @Override
                    public void onResponse(Call<Test> call, Response<Test> response) {
                        if (response.isSuccessful()) {
                            textView.setText("");
                            textView.setText("삭제");
                        }
                    }

                    @Override
                    public void onFailure(Call<Test> call, Throwable t) {

                    }
                });
                break;
            case R.id.btnInsert:

                String name = edInsertName.getText().toString().trim();
                String age = edInsertAge.getText().toString().trim();

                //CREATE
                testService.insertTest(name, age).enqueue(new Callback<Test>() {
                    @Override
                    public void onResponse(Call<Test> call, Response<Test> response) {
                        if (response.isSuccessful()) {
                            textView.setText("");
                            textView.setText("입력");
                        }
                    }

                    @Override
                    public void onFailure(Call<Test> call, Throwable t) {

                    }
                });
                break;
            case R.id.btnUpdate:

                String uno   = edView.getText().toString();
                String uname = edUpdateName.getText().toString().trim();
                String uage  = edUpdateAge.getText().toString().trim();

                Log.v("UPDATE", uno.toString());

                //UPDATE
                testService.updateTest(uno, uname, uage).enqueue(new Callback<Test>() {
                    @Override
                    public void onResponse(Call<Test> call, Response<Test> response) {
                        if (response.isSuccessful()) {
                            textView.setText("");
                            textView.setText("수정");
                        }
                    }

                    @Override
                    public void onFailure(Call<Test> call, Throwable t) {

                    }
                });
                break;
        }
    }
}
