package br.ifsc.edu.br.launcher;

import android.content.pm.ResolveInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AppAdapter extends ArrayAdapter<ResolveInfo> {
    public AppAdapter(@NonNull MainActivity mainActivity, int item_lista, @NonNull List<ResolveInfo> packageInfoList) {
        super(mainActivity, item_lista, packageInfoList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.item_lista, parent, false);

        ResolveInfo resolveInfo = getItem(position);

        TextView textViewAppName = view.findViewById(R.id.app_name);
        ImageView imageViewAppIcon = view.findViewById(R.id.app_icon);

        imageViewAppIcon.setImageDrawable(resolveInfo.loadIcon(getContext().getPackageManager()));
        textViewAppName.setText(resolveInfo.loadLabel(getContext().getPackageManager()));
        return view;
    }
}
