package com.lxy.mew.expandtextview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lxy.mew.expandtextview.R;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by master on 2017/4/29.
 */
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>
{

    private Context context;
    private List<String> datas = new ArrayList<>();


    public CustomAdapter(Context context)
    {
        this.context = context;
    }

    public void setDatas(List<String> datas)
    {
        this.datas = datas;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_expand, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position)
    {

        String item = datas.get(position);
        holder.tvContent.setText(item);

        final View.OnClickListener listener = new View.OnClickListener()
        {
            boolean isExpand;//设置是否展开的标记

            @Override
            public void onClick(View v)
            {
                if (isExpand)
                {
                    holder.tvContent.setMaxLines(2);//如果当前是展开的，则设置为闭合
                    holder.ivArrow.setRotation(0);//箭头重置为向下的状态
                    isExpand = false;
                } else
                {
                    holder.tvContent.setMaxLines(Integer.MAX_VALUE);//如果是闭合的，则展开
                    holder.ivArrow.setRotation(180);//箭头设置为向上
                    isExpand = true;
                }

            }
        };
        holder.tvContent.post(new Runnable()
        {
            @Override
            public void run()
            {
                /**
                 * 在子线程中获取textview的行数，等页面加载完成之后才能取到行数
                 */
                int lineCount = holder.tvContent.getLineCount();
                if (lineCount > 2)//如果行数大于2，就让textview闭合
                {
                    holder.tvContent.setMaxLines(2);
                    holder.viewContainer.setOnClickListener(listener);//可以相应点击事件
                    holder.ivArrow.setVisibility(View.VISIBLE);
                } else
                {
                    holder.viewContainer.setOnClickListener(null);//行数没有超过两行，保持原状，无需改变
                    holder.ivArrow.setVisibility(View.GONE);//让箭头消失
                }
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder
    {

        View viewContainer;
        TextView tvContent;
        ImageView ivArrow;

        ViewHolder(View itemView)
        {
            super(itemView);

            viewContainer = itemView.findViewById(R.id.view_item_container);
            tvContent = (TextView) itemView.findViewById(R.id.tv_item_content);
            ivArrow = (ImageView) itemView.findViewById(R.id.iv_item_arrow);

        }
    }
}
