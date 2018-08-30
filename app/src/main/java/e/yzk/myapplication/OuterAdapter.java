package e.yzk.myapplication;


import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class OuterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context activity;
    private List<Day> list;
    private List<Object> showList = new ArrayList<>();
    private List<ArrayList<Day>> emptyDay = new ArrayList<>();
    private int index = 0;
    private OuterAdapter.ViewHolder2 tempHolder;

    public OuterAdapter(List<Day> list, Context activity) {
        this.activity = activity;
        this.list = new ArrayList<>(list);

        ArrayList<Day> temp;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).doing != null) {
                showList.add(list.get(i));
            } else {
//----------------------------------------------
                temp = new ArrayList<>();
                while (true) {
                    if (list.get(i).doing != null) {
                        emptyDay.add(temp);
                        showList.add(index);
                        index++;
                        i--;
                        break;
                    } else {
                        temp.add(list.get(i));
                        list.remove(i);
                    }
                }
//----------------------------------------------
            }
        }


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        switch (viewType) {
            case 0:
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main, parent, false);
                viewHolder = new OuterAdapter.ViewHolder(view);
                break;

            case 1:
                View view2 = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ver, parent, false);
                viewHolder = new OuterAdapter.ViewHolder2(view2);
                tempHolder = (OuterAdapter.ViewHolder2)viewHolder;

                break;
            default:
                return null;

        }
        return viewHolder;
    }

    @Override
    public int getItemViewType(int position) {
        if (showList.get(position) instanceof Day) {
            return 0;
        } else if (showList.get(position) instanceof Integer) {
            return 1;
        } else {
            return 2;
        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            ViewHolder v1 = (ViewHolder) holder;
            Day day = (Day) showList.get(position);
            v1.t1.setText(day.name);
            v1.t2.setText(day.doing);
            v1.imageView.setImageResource(day.icon);
        } else if (holder instanceof ViewHolder2) {//频道
            ViewHolder2 v2 = (ViewHolder2) holder;
            List<Day> list = emptyDay.get((int) showList.get(position));
            v2.recyclerView2.setAdapter(new InterAdapter(list));
        }

    }

    @Override
    public int getItemCount() {

        return showList.size();
    }

    void scrollPosition(int topPosition, int leftPosition) {
        tempHolder.recyclerView2.scrollToPosition(leftPosition);
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView t1;
        TextView t2;
        ImageView imageView;

        ViewHolder(View itemView) {
            super(itemView);
            t1 = itemView.findViewById(R.id.textView);
            t2 = itemView.findViewById(R.id.textView2);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    class ViewHolder2 extends RecyclerView.ViewHolder {
        RecyclerView recyclerView2;

        ViewHolder2(View itemView) {
            super(itemView);
            recyclerView2 = itemView.findViewById(R.id.recyclerView2);
            recyclerView2.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false));
        }
    }

}

