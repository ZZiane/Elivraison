package org.fstbm.elivraison.commands;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.fstbm.elivraison.DetailsActivity;
import org.fstbm.elivraison.HomePage;
import org.fstbm.elivraison.R;
import org.fstbm.elivraison.data.model.Command;
import org.fstbm.elivraison.data.model.Produit;

import java.util.List;

public class CommandListViewAdapter extends BaseAdapter {

    private List<Command> commands;
    private Context context;
    private LayoutInflater inflater;

    public CommandListViewAdapter(Context context, List<Command> commands) {
        this.context = context;
        this.commands = commands;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return commands.size();
    }


    public Command getItem(int i) {
        return commands.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.activity_command_list_view,null);
        Command c = commands.get(i);
        TextView cmd = view.findViewById(R.id.commandNum);
        cmd.setText("Commande #"+c.getId());
        TextView client = view.findViewById(R.id.clientChamp);
        client.setText(c.getClient().getName()+" - "+c.getClient().getTel());
        TextView adress = view.findViewById(R.id.address);
        adress.setText("A livrer : "+c.getDestination());
        List<Produit> produits = c.getProducts();
        LinearLayout linearLayout = view.findViewById(R.id.produitsChamp);
        for(Produit p  : produits){
            TextView textView = new TextView(inflater.getContext());
            textView.setText(p.getName() + " x " + p.getQuantity() +" : " +p.getPrice());
            linearLayout.addView(textView);
        }
        TextView price = view.findViewById(R.id.price);
        double totale = produits.stream().map((p)-> p.getPrice()).reduce(0d, Double::sum);
        price.setText(totale+" Dhs");
        Button b = view.findViewById(R.id.btnA);
        b.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Log.d("=================>",c.getState());
                Intent it = new Intent(context, DetailsActivity.class);
                it.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                Bundle b = new Bundle();
                b.putSerializable("command", c);
                it.putExtra("bundleC",b);
                context.startActivity(it);
            }
        });
        return view;
    }

}
