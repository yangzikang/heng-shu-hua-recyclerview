package e.yzk.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    OuterAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Day> list = new ArrayList();
        for (int i = 0; i < 27; i++) {
            if (i > 3 && i < 7 || i > 10 && i < 17) {
                Day day = new Day();
                day.name = String.valueOf(i);
                list.add(day);
            } else {
                Day day = new Day();
                day.name = String.valueOf(i);
                day.doing = "打" + i;
                day.icon = R.drawable.ic_launcher_background;
                list.add(day);
            }
        }


        //通过findViewById拿到RecyclerView实例
        mRecyclerView = findViewById(R.id.recyclerView);
//设置RecyclerView管理器
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
//初始化适配器

        for(int i= 0;i<list.size();i++){
            Log.e("yang2",list.get(i).name);
        }

        final List<Day> fl = new ArrayList(list);
        mAdapter = new OuterAdapter(list, this);
//设置添加或删除item时的动画，这里使用默认动画
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//设置适配器
        mRecyclerView.setAdapter(mAdapter);



        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText e = findViewById(R.id.editText);
                int a = Integer.valueOf(e.getText().toString());
               int top = scrollTopPosition(fl , a,mRecyclerView);
               int left = scrollLeft(fl,a);
               if(left!=0){
                   mAdapter.scrollPosition(top,left);
               }
            }
        });

    }

    int scrollTopPosition(List <Day>list, int position, RecyclerView recyclerView){

        Boolean isFirst = true;
        int leftScroll = 0;
        int p = position;

        for(int i = position ; i >=0 ;i--) {
            if (list.get(i).doing == null) {
                while(true){
                    p--;
                    i--;
                    if(isFirst) {
                        leftScroll ++;
                    }
                    if(list.get(i).doing !=null){
                        p++;
                        if(isFirst) {
                            leftScroll --;
                        }
                        break;
                    }
                }
            }
            isFirst = false;
        }

        recyclerView.smoothScrollToPosition(p);
        return p;
    }

    int scrollLeft(List<Day> list,int position){
        Boolean isFirst = true;
        int leftScroll = 0;
        int p = position;

        for(int i = position ; i >=0 ;i--) {
            if (list.get(i).doing == null) {
                while(true){
                    p--;
                    i--;
                    if(isFirst) {
                        leftScroll ++;
                    }
                    if(list.get(i).doing !=null){
                        p++;
                        if(isFirst) {
                            leftScroll --;
                        }
                        break;
                    }
                }
            }
            isFirst = false;
        }
        return  leftScroll;

    }



}
