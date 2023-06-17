package org.fstbm.elivraison.connection;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.fstbm.elivraison.data.model.Livreur;

public class Connection {
    private static DataSnapshot dataSnapshot;


    public static DataSnapshot getDataSnapshot(){
        return dataSnapshot;
    }
    public static void setDataSnapshot(DataSnapshot dss){   dataSnapshot =dss;   }

}
