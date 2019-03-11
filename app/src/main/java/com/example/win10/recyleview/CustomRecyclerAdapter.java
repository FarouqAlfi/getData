package com.example.win10.recyleview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CustomRecyclerAdapter extends RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder> {
    private Context context;
    private List<Padi> personUtils;

    public CustomRecyclerAdapter(Context context, List personUtils) {
        this.context = context;
        this.personUtils = personUtils;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemView.setTag(personUtils.get(position));

        Padi pu = personUtils.get(position);

//        holder.pName.setText(pu.getPersonFirstName());
//        holder.pJobProfile.setText(pu.getJobProfile());
        holder.mTextViewId.setText(pu.getId());
        holder.mTextLuasLahan.setText(pu.getLuas_lahan());
        holder.mTextTglTanam.setText(pu.getTgl_tanam());
        holder.mTextTglPanen.setText(pu.getTgl_siap_panen());
        holder.mTextHasilPanen.setText(pu.getHasil_panen());
        holder.mTextPemilik.setText(pu.getPemilik());
        holder.mTextNik.setText(pu.getNik());
        holder.mTextPekerja.setText(pu.getPekerja());

    }

    @Override
    public int getItemCount() {
        return personUtils.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView mTextViewId, mTextLuasLahan, mTextTglTanam, mTextTglPanen, mTextHasilPanen, mTextPemilik, mTextNik, mTextPekerja;


        public ViewHolder(View itemView) {
            super(itemView);

            mTextViewId = (TextView) itemView.findViewById(R.id.item_id);
            mTextLuasLahan = (TextView) itemView.findViewById(R.id.item_luaslahan);
            mTextTglTanam = (TextView) itemView.findViewById(R.id.item_tgltanam);
            mTextTglPanen = (TextView) itemView.findViewById(R.id.item_tglpanen);
            mTextHasilPanen = (TextView) itemView.findViewById(R.id.item_hasilpanen);
            mTextPemilik = (TextView) itemView.findViewById(R.id.item_pemilik);
            mTextNik = (TextView) itemView.findViewById(R.id.item_nik);
            mTextPekerja = (TextView) itemView.findViewById(R.id.item_jmlpekerja);

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    showAlertDialogButtonClicked();
//                    Padi cpu = (Padi) view.getTag();
//
//                    Toast.makeText(view.getContext(), cpu.getPemilik()+" is "+ cpu.getLuas_lahan(), Toast.LENGTH_SHORT).show();

                    return false;
                }

            });


    }
        private void showAlertDialogButtonClicked() {

            // setup the alert builder
            AlertDialog.Builder builder = new AlertDialog.Builder(context);

            // add the buttons
            builder.setPositiveButton("Edit", null);
            builder.setNegativeButton("Delete", null);

            // create and show the alert dialog
            AlertDialog dialog = builder.create();
            dialog.show();
        }
}


}