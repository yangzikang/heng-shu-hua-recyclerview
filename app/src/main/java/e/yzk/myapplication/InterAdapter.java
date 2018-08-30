package e.yzk.myapplication;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class InterAdapter extends RecyclerView.Adapter<InterAdapter.ViewHolder> {
    private List<Day> list;

    public InterAdapter(List<Day> list) {
        this.list = list;
    }

    @Override
    public InterAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_in_in, parent, false);
        InterAdapter.ViewHolder viewHolder = new InterAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(InterAdapter.ViewHolder holder, int position) {
        holder.mText.setText(list.get(position).name);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView mText;

        ViewHolder(View itemView) {
            super(itemView);
            mText = itemView.findViewById(R.id.textView3);
        }
    }
}
