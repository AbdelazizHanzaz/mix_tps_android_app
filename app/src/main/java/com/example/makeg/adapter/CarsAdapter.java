package com.example.makeg.adapter;



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.makeg.R;
import com.example.makeg.pojo.Car;

import java.util.ArrayList;
import java.util.List;

public class CarsAdapter extends RecyclerView.Adapter<CarsAdapter.CarViewHolder> {


    private List<Car> cars;
    private Context mContext;

    public CarsAdapter(Context context){
        cars = new ArrayList<>();
        mContext = context;
    }
    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }




    @NonNull
    @Override
    public CarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.car_item, parent, false);

        return new CarViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CarViewHolder holder, int position) {
        holder.bind(cars.get(position));
    }

    @Override
    public int getItemCount() {
        return cars.size();
    }

    @Override
    public long getItemId(int position) {
        return cars.get(position).getId();
    }

    class CarViewHolder extends RecyclerView.ViewHolder{

        private TextView id, model, color, dpl;

        public CarViewHolder(@NonNull View itemView) {
            super(itemView);

            setupView(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClickListener != null){
                        int carId = (int) CarsAdapter.this.getItemId(getAdapterPosition());
                        onItemClickListener.onClick(""+carId);
                    }
                }
            });
            id.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClickListener != null){
                        onItemClickListener.onIdClick("OnIdClick : "+id.getText().toString());
                    }
                }
            });

        }

        public void bind(Car car){
            id.setText(String.valueOf(car.getId()));
            model.setText(String.format("%s %s", getStringTitle(R.string.model_text_title), car.getModel()));
            color.setText(String.format("%s %s", getStringTitle(R.string.color_text_title), car.getColor()));
            dpl.setText(String.format("%s %s", getStringTitle(R.string.dpl_text_title), car.getDpl()));
        }

        private String getStringTitle(int idRes){
            return mContext!= null ? mContext.getResources().getString(idRes) : "";
        }

        private void setupView(View itemView){
            id = itemView.findViewById(R.id.car_id_tv);
            model = itemView.findViewById(R.id.car_model_tv);
            color = itemView.findViewById(R.id.car_color_tv);
            dpl = itemView.findViewById(R.id.car_dpl_tv);

        }
    }

    public interface OnItemClickListener{
        void onClick(String itemId);
        void onIdClick(String id);
        //void onLongClick(String itemId);
    }

    OnItemClickListener onItemClickListener;
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener = onItemClickListener;
    }
}
