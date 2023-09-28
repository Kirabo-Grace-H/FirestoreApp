package com.kotlin.firestoreapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = Firebase.firestore
        val texttv = findViewById<TextView>(R.id.text_TV)

        //create collections
        val user_collection = db.collection("Users")
        //create documents
        val user1 = hashMapOf(
            "name" to "Jack",
            "last" to "Reacher",
            "born" to "1990"
        )
        val user2 = hashMapOf(
            "name" to "Tom",
            "last" to "Alex",
            "born" to "2000"
        )

        //adding document to collections
        user_collection.document("user1").set(user1)
        user_collection.document("user2").set(user2)

        //read or receive from firestore
        val docRef = db.collection("Users").document("user1")
        docRef.get().addOnSuccessListener { document->
            if (document!= null){
                // texttv.text="${document.data}"
                texttv.text="${document.data?.get("name")}"

                //Get all documents from a collection
                var allDocuments:String=""
                db.collection("Users").get().addOnSuccessListener { result->
                    for (document in result){
                        //texttv.text="${document.data}"
                        allDocuments +="${document.data}"
                        allDocuments += "/n"
                    }
                    texttv.text= ""+ allDocuments
                }
            }
        }
    }
    }
