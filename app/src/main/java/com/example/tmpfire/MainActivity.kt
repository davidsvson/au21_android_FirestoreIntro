package com.example.tmpfire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    val TAG = "!!!"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db = Firebase.firestore
        val itemsRef = db.collection("items")

/*
        val item = Item("gurka", false, "grönsak")
        val item2 = Item("banan")

        db.collection("items").document("gurka").set(item)

        db.collection("items").add(item2)
*/
        val shoppingItems = mutableListOf<Item>()
/*
        Log.d(TAG, "1. onCreate: gör Get")
        itemsRef.get().addOnSuccessListener { documentSnapShot ->
            for (document in documentSnapShot.documents) {
                val newItem = document.toObject(Item::class.java)
                if (newItem != null)
                    shoppingItems.add(newItem)
                Log.d(TAG, "2. onCreate: ${newItem}")
            }
            // adapter.notifydatesetchanged()
        }
        Log.d(TAG, "3. onCreate: efter get")

        Log.d(TAG, "onCreate: ${shoppingItems.size}")
*/

        itemsRef.addSnapshotListener { snapshot, e ->
            Log.d(TAG, "onCreate: database changed!")
            if (snapshot != null) {
                shoppingItems.clear()
                for( document in snapshot.documents) {
                    val newItem = document.toObject(Item::class.java)
                    Log.d(TAG, "2. onCreate: ${newItem}")
                    if ( newItem != null) {
                        shoppingItems.add(newItem)
                    }
                }
            }
            Log.d(TAG, "onCreate: ${shoppingItems.size}")
        }




        /*
        // Create a new user with a first and last name
        val user = hashMapOf(
            "first" to "Ada",
            "last" to "Lovelace",
            "born" to 1815
        )

        Log.d(TAG, "onCreate: KÖrs")

        // Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }

*/

    }

}