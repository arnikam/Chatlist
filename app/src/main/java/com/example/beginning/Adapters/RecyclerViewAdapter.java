package com.example.beginning.Adapters;

import android.content.Context;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.beginning.Model.User;
import com.example.beginning.R;
import com.example.beginning.ViewModel.FragmentViewModel;
import com.example.beginning.ViewModel.ItemClickListener;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdapter extends PagedListAdapter<User, RecyclerViewAdapter.ViewHolder> {
    ItemClickListener itemClickListener;


    public static DiffUtil.ItemCallback<User> DIFF_CALLBACK = new DiffUtil.ItemCallback<User>() {
        @Override
        public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.get_id() == newItem.get_id();
        }

        @Override
        public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
            return oldItem.equals(newItem);
        }
    };

    public RecyclerViewAdapter(ItemClickListener itemClickListener) {
        super(DIFF_CALLBACK);
        this.itemClickListener = itemClickListener;
    }

//    public void setItemClickListener(ItemClickListener itemClickListener) {
//        this.itemClickListener = itemClickListener;
//    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chat_data, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User user = getItem(position);
        holder.textViewName.setText(user.getName());
        holder.textViewNumber.setText(user.getContactNumber());

        if(user.getProfilePic() != null){
            Glide.with(holder.imageViewProfilePic.getContext())
                    .load(Uri.parse(user.getProfilePic()))
                    .error(R.drawable.ic_baseline_person_24)
                    .into(holder.imageViewProfilePic);
        }

    }


    class ViewHolder extends RecyclerView.ViewHolder implements OnClickListener, View.OnLongClickListener {

        ImageView imageViewProfilePic;
        TextView textViewName, textViewNumber;
        Button buttonEdit, buttonDelete;
        SwipeRevealLayout swipeRevealLayout;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewProfilePic = itemView.findViewById(R.id.image_view_profile_pic);
            textViewName = itemView.findViewById(R.id.text_view_name);
            textViewNumber = itemView.findViewById(R.id.text_view_number);

            buttonEdit = itemView.findViewById(R.id.edit);
            buttonDelete = itemView.findViewById(R.id.delete);
            buttonDelete.setVisibility(View.GONE);
            buttonEdit.setVisibility(View.GONE);

            itemView.setOnClickListener(this);

            itemView.setOnLongClickListener(this);

//            buttonEdit.setOnClickListener(this);
//            buttonDelete.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d("TAG", "onClick in adapter called: " + view.getId());
            if (itemClickListener != null)
                itemClickListener.onItemClicked(view, getItem(getAdapterPosition()));
        }

        @Override
        public boolean onLongClick(View view) {
            Log.d("TAG", "onLongClick boolean called: " + view.getId());
            if (itemClickListener != null)
                itemClickListener.onItemLongClicked(view, getItem(getAdapterPosition()), getAdapterPosition());

            return true;




        };
    }
}
