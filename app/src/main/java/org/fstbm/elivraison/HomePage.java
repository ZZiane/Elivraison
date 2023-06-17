package org.fstbm.elivraison;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import org.fstbm.elivraison.commands.CommandListViewAdapter;
import org.fstbm.elivraison.data.LoginDataSource;
import org.fstbm.elivraison.data.model.Command;
import org.fstbm.elivraison.data.model.Livreur;

public class HomePage extends AppCompatActivity {

    private Livreur livreur;
    private TextView nomLivreur;
    private ListView listView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        Bundle b = getIntent().getBundleExtra("bundle");
        this.livreur = (Livreur) b.get("livreur");
        listView = findViewById(R.id.commandsList);
        CommandListViewAdapter adapter = new CommandListViewAdapter(getApplicationContext(),livreur.getCommands());
        nomLivreur = findViewById(R.id.nomLivreur);
        nomLivreur.setText("Livreur "+livreur.getFirstName() + " "+ livreur.getLastName());
        listView.setAdapter(adapter);



    }
}