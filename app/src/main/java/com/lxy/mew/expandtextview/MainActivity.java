package com.lxy.mew.expandtextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lxy.mew.expandtextview.adapter.CustomAdapter;
import com.lxy.mew.expandtextview.view.RecycleViewItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new RecycleViewItemDecoration(this, 1));
        adapter = new CustomAdapter(this);
        recyclerView.setAdapter(adapter);

        loadData();
    }

    private void loadData() {
        List<String> datas = new ArrayList<>();
        datas.add("joisdjfoajsfoiasjfoijasdojfosdajgoasdjgoasdjgoajsogoadhofashdofhaosdhfoashfoahsoghaoshgoashgoashgoasdhgoashgohg");
        datas.add("joajsogoadhofashdofhaosdhfoashfoahsoghaoshgoashgoashgoasdhgoashgohg");
        datas.add("joisdjfoajsfoiasjfoijasdojfosdajgoasdjgoas");
        adapter.setDatas(datas);
        adapter.notifyDataSetChanged();
    }
}
