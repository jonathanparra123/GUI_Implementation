package view;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

public class Firebase{

    public FirebaseDatabase database;
    public DatabaseReference user;
    public boolean check;

    //initializes firebase database
    public Firebase() {
            try {
                FileInputStream service = new FileInputStream("src/view/package.json"); //uses JSON file for credentials

                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(service))
                        .setDatabaseUrl("https://login-ab868.firebaseio.com")
                        .build();
                FirebaseApp.initializeApp(options);

                database = FirebaseDatabase.getInstance("https://login-ab868.firebaseio.com");
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
    }

    //adds object to database
    public void Add(Object v){
        try{
            user = database.getReference("users");
            final CountDownLatch latch = new CountDownLatch(1);
            user.setValue(v, new DatabaseReference.CompletionListener(){
                @Override
                public void onComplete(DatabaseError d, DatabaseReference r){
                    latch.countDown();
                }
            });
            latch.await();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //checks to see if data is in database
    public boolean Check(String u, String p){
        try {
            user = database.getReference("users/"+u+"/"+"password");
            final CountDownLatch latch = new CountDownLatch(1);

            user.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    if(dataSnapshot.getValue() == null) {
                        check = false;
                    }else {
                        if (dataSnapshot.getValue().equals(p)) {
                            check = true;
                        } else {
                            check = false;
                        }
                    }
                    latch.countDown();
                }
                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
            latch.await();
        }catch (Exception e){}

        return check;
    }
}

