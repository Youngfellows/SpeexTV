package com.speex.tv;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.speex.tv.widget.MetroViewBorderImpl;

public class MainActivity extends AppCompatActivity {
    private String TAG = this.getClass().getSimpleName();
    private RecyclerView mRvPoetry;
    private MetroViewBorderImpl mMetroViewBorderImpl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRvPoetry = (RecyclerView) findViewById(R.id.rv_poetry);

        mMetroViewBorderImpl = new MetroViewBorderImpl(this);
        mMetroViewBorderImpl.setBackgroundResource(R.drawable.border_color);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvPoetry.setLayoutManager(layoutManager);
        mRvPoetry.setFocusable(false);
        mMetroViewBorderImpl.attachTo(mRvPoetry);
        createOptionItemData(mRvPoetry, R.layout.detail_menu_item);
    }

    private void createOptionItemData(final RecyclerView recyclerView, int id) {
        OptionItemAdapter adapter = new OptionItemAdapter(this, id, new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                Log.i(TAG, "onFocusChange: " + view.getTag() + "位置" + (b ? "获取焦点" : "失去焦点"));
                RelativeLayout relativeLayout = (RelativeLayout) view;
                TextView textView = (TextView) relativeLayout.getChildAt(0);
                if (b) {
                    textView.setTextColor(Color.parseColor("#6D8B37"));
                } else {
                    textView.setTextColor(Color.parseColor("#000000"));
                }
            }
        });
        adapter.setOnBindListener(new OptionItemAdapter.OnBindListener() {
            @Override
            public void onBind(View view, int i) {
                Log.i(TAG, view.getTag() + " ,onBind: " + i + "位置");
            }
        });
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(0);
    }
}
