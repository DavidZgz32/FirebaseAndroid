package com.example.david.conecta;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class UsersListAdapter extends RecyclerView.Adapter<UsersListAdapter.ViewHolder>{

    public List<Mensajes> mensajesList;

    public UsersListAdapter(List<Mensajes> mensajesList){

        this.mensajesList = mensajesList;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_item, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        viewHolder.mensajeText.setText(mensajesList.get(i).getMensaje());
        viewHolder.categoriaText.setText(mensajesList.get(i).getCategoria());

    }

    @Override
    public int getItemCount() {
        return mensajesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        View mView;
        public TextView mensajeText;
        public TextView categoriaText;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            mensajeText = mView.findViewById(R.id.mensajeText);
            categoriaText = mView.findViewById(R.id.categoriaText);
        }
    }

}
