package adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.singinapplication.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import inteface.OnitemStatusClickListener;
import jsonmodel.reponse.Status;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.MyViewHolder> {
    ArrayList<Status> statuses;

    OnitemStatusClickListener onitemStatusClickListener;

    public StatusAdapter(ArrayList<Status> statuses, OnitemStatusClickListener onitemStatusClickListener) {
        this.statuses = statuses;
        this.onitemStatusClickListener =onitemStatusClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_status, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        //bind view
        holder.bindView(statuses.get(position));
    }

    @Override
    public int getItemCount() {
        return statuses.size();
    }

    public void notifyDataSetChanged(int i) {
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        Context context;
        TextView tvContent;
        CircleImageView imageView;
        TextView tvFullName;
        TextView tvCreateDate;
        TextView tvNumberLike;
        TextView tvNumberComment;
        ImageView imvLike;
        TextView tvLike;
        LinearLayout itemLike;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            context =itemView.getContext();
           imageView = itemView.findViewById(R.id.iv_avatar);
            tvFullName = itemView.findViewById(R.id.tv_full_name);
           tvCreateDate = itemView.findViewById(R.id.tv_create_date);
            tvContent = itemView.findViewById(R.id.tv_content);
           tvNumberLike = itemView.findViewById(R.id.tv_number_like);
           tvNumberComment = itemView.findViewById(R.id.tv_number_comment);
            imvLike = itemView.findViewById(R.id.imv_like);
            tvLike = itemView.findViewById(R.id.tv_like);
            itemLike = itemView.findViewById(R.id.item_like);
        }
        private void bindView(Status status)
        {
            Glide.with(context).load(status.getAuthorAvatar()).into(imageView);

            tvFullName.setText(status.getAuthor());
            tvCreateDate.setText(status.getCreateDate());
            tvContent.setText(status.getContent());
            tvNumberLike.setText(String.valueOf(status.getNumberLike()));
            tvNumberComment.setText(String.format("%s comment",status.getNumberComment()));

            if(status.isLike())
            {
                imvLike.setBackground(context.getResources().getDrawable(R.drawable.ic_like));

                tvLike.setTextColor(context.getResources().getColor(R.color.red));
            }
            else
            {
                imvLike.setBackground(context.getResources().getDrawable(R.drawable.ic_heart));
                tvLike.setTextColor(context.getResources().getColor(R.color.gray2));
            }

            imvLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onitemStatusClickListener.onLikeClick(status);
                    if(status.isLike())
                    {
                        imvLike.setBackground(context.getResources().getDrawable(R.drawable.ic_heart));
                        tvLike.setTextColor(context.getResources().getColor(R.color.gray2));
                        tvNumberLike.setText(String.valueOf(status.getNumberLike()-1));
                    }
                    else
                    {
                       imvLike.setBackground(context.getResources().getDrawable(R.drawable.ic_like));

                        tvLike.setTextColor(context.getResources().getColor(R.color.red));
                        tvNumberLike.setText(String.valueOf(status.getNumberLike()+1));
                    }
                }
            });
        }
    }
}
