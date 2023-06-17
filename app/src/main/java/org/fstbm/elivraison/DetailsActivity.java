package org.fstbm.elivraison;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.fstbm.elivraison.connection.Connection;
import org.fstbm.elivraison.data.model.Command;
import org.fstbm.elivraison.data.model.Produit;
import org.fstbm.elivraison.listeners.LastLocation;
import org.fstbm.elivraison.listeners.LocationEvent;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;

import java.util.List;

public class DetailsActivity extends AppCompatActivity {

    private Command c;
    private Button back;
    private Button delevry;
    private DatabaseReference dsCmd;

    private MapView mapView;

    private LocationManager locationManager;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));
        setContentView(R.layout.activity_details);
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},111 );
        }
        else{
            Log.println(Log.ERROR,"==========>","Khedddddddddddddam");
            construct();
        }
    }


    @SuppressLint("MissingPermission")
    public void construct(){
        Context ctx = getApplicationContext();
        Bundle b = getIntent().getBundleExtra("bundleC");
        this.c = (Command) b.get("command");
        TextView cmd = findViewById(R.id.commandNum);
        cmd.setText("Commande #"+c.getId());
        TextView client = findViewById(R.id.clientChamp);
        client.setText(c.getClient().getName()+" - "+c.getClient().getTel());
        TextView adress = findViewById(R.id.address);
        adress.setText("A livrer : "+c.getDestination());
        List<Produit> produits = c.getProducts();
        LinearLayout linearLayout =findViewById(R.id.produitsChamp);
        for(Produit p  : produits){
            TextView textView = new TextView(this);
            textView.setText(p.getName() + " x " + p.getQuantity() +" : " +p.getPrice());
            linearLayout.addView(textView);
        }

        TextView price = findViewById(R.id.price);
        double totale = produits.stream().map((p)-> p.getPrice()).reduce(0d, Double::sum);
        price.setText(totale+" Dhs");
        TextView state = findViewById(R.id.state);
        state.setText(c.getState());
        back = findViewById(R.id.backB);
        delevry = findViewById(R.id.delevry);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();

            }
        });

        delevry.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                DataSnapshot dss = Connection.getDataSnapshot();
                DataSnapshot commands = dss.child("commands");
                for(DataSnapshot command : commands.getChildren()){
                    Command newCommand = command.getValue(Command.class);
                    if(newCommand.getId() == c.getId()) {
                        dsCmd = command.getRef();
                        dsCmd.child("state").getRef().setValue("A livrer").addOnSuccessListener(new OnSuccessListener<Void>() {

                            public void onSuccess(Void aVoid) {
                                Toast.makeText(getApplicationContext(), "Etat bien modifier", Toast.LENGTH_LONG).show();
                                dsCmd.addListenerForSingleValueEvent(new ValueEventListener(){

                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        c = snapshot.getValue(Command.class);
                                        Intent it = new Intent(DetailsActivity.this,FinalStepActivity.class);
                                        Bundle b = new Bundle();
                                        b.putSerializable("command",c);
                                        it.putExtra("bundleC",b);
                                        startActivity(it);
                                    }

                                    public void onCancelled(@NonNull DatabaseError error) { }
                                });

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(), "Erreur reseau", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }
            }
            public void onBackPressed() {
                finish();
            }
        });

        mapView = findViewById(R.id.map);
        mapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        GeoPoint geoPoint = new GeoPoint(c.getPosition().getLatitude(), c.getPosition().getLongitude());
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, new LocationEvent(ctx,mapView,geoPoint));
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 111) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getApplicationContext(), "Acces autoriser", Toast.LENGTH_LONG).show();
                construct();
            } else {
                Toast.makeText(getApplicationContext(), "Acces non autoriser", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
}