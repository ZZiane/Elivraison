package org.fstbm.elivraison.data;

import android.provider.ContactsContract;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.fstbm.elivraison.connection.Connection;
import org.fstbm.elivraison.data.model.Livreur;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    private static Livreur l;
    private CountDownLatch latch;

    public Result<Livreur> login(String username, String password) {

        s(username, password);
        if(l == null || l.getId() == 0){
            return new Result.Error(new IOException("Error in logging"));}
        return new Result.Success<>(l);
    }

    public void s(String username, String password){
        DatabaseReference dbr = FirebaseDatabase.getInstance().getReference().child("delevries");
        dbr.addListenerForSingleValueEvent(new ValueEventListener(){
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            Livreur livreur = snapshot.getValue(Livreur.class);
                            if(livreur.getEmail().equals(username) && livreur.getPassword().equals(password)){
                                Connection.setDataSnapshot(snapshot);
                                l = snapshot.getValue(Livreur.class);
                            }

                        }
                        if (l == null) {
                            l = new Livreur();
                        }
                    }
                }

                public void onCancelled(DatabaseError databaseError) {}
            });
        }




    public void logout() {
    }
    public static Livreur getLivreur(){
        return l;
    }
}


/**
 *
 *
 * if(element.contains("root")){
 *                             l = new Livreur();
 *                             l.setId(snapshot.child("id").getValue(Integer.class));
 *                             l.setFirstName(snapshot.child("firstName").getValue(String.class));
 *                             l.setLastName(snapshot.child("lastName").getValue(String.class));
 *                             l.setEmail(snapshot.child("email").getValue(String.class));
 *                             l.setTel(snapshot.child("tel").getValue(String.class));
 *                             l.setPassword(snapshot.child("password").getValue(String.class));
 *                             List<Command> commands = new Vector<>();
 *                             l.setCommands(commands);
 *                             for(DataSnapshot command : commandsSnapshot.getChildren()){
 *                                 List<Produit> products = new Vector<>();
 *                                 Command commandM = new Command();
 *                                 commandM.setDestination(command.child("destination").getValue(String.class));
 *                                 commandM.setFrom(command.child("from").getValue(String.class));
 *                                 commandM.setState(command.child("state").getValue(String.class));
 *                                 DataSnapshot productsSnapshot = command.child("products");
 *                                 for(DataSnapshot product : productsSnapshot.getChildren()){
 *                                     Produit produit = new Produit();
 *                                     produit.setName(product.child("name").getValue(String.class));
 *                                     produit.setPrice(product.child("price").getValue(Double.class));
 *                                     produit.setQunatity(product.child("qunatity").getValue(Integer.class));
 *                                     products.add(produit);
 *                                 }
 *                                 commandM.setProducts(products);
 *                                 commands.add(commandM);
 *                             }
 *                             break;
 *                         }
 */