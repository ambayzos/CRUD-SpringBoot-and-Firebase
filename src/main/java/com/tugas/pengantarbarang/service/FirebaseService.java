package com.tugas.pengantarbarang.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.stereotype.Service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.tugas.pengantarbarang.entity.UserEntity;

@Service
public class FirebaseService {
	
	 private static final String COLLECTION_NAME ="db_users" ;
	 
	 //save
	public String saveUserDetails(UserEntity entity)throws ExecutionException, InterruptedException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionsApiFuture = dbFirestore.collection(COLLECTION_NAME).document(entity.getName()).set(entity);
		return collectionsApiFuture.get().getUpdateTime().toString();
	}
	
	//getAll
	public List<UserEntity> getAllUser() throws ExecutionException, InterruptedException {

        Firestore dbFirestore= FirestoreClient.getFirestore();

        Iterable<DocumentReference> documentReference=dbFirestore.collection(COLLECTION_NAME).listDocuments();
        Iterator<DocumentReference> iterator=documentReference.iterator();

        List<UserEntity> userList=new ArrayList<>();
        UserEntity users=null;

        while(iterator.hasNext()){
            DocumentReference documentReference1=iterator.next();
           ApiFuture<DocumentSnapshot> future= documentReference1.get();
           DocumentSnapshot document=future.get();

            users=document.toObject(UserEntity.class);
           userList.add(users);

        }
        return userList;
    }
	
	//getUserByName
	
	public UserEntity getUserByName(String name) throws ExecutionException, InterruptedException{
		
		Firestore dbFirestore = FirestoreClient.getFirestore();
		DocumentReference documentReference = dbFirestore.collection(COLLECTION_NAME).document(name);
		ApiFuture<DocumentSnapshot> future = documentReference.get();
		
		DocumentSnapshot document = future.get();
		
		UserEntity users = null;
		if (document.exists()) {
			users = document.toObject(UserEntity.class);
			return users;
		}else {
			return null;
		}
	}
	
	
	//getUsersDetails
	public List<UserEntity> getUserDetails()throws ExecutionException, InterruptedException{
		
		Firestore dbFirestore = FirestoreClient.getFirestore();
		Iterable<DocumentReference> documentReference = dbFirestore.collection(COLLECTION_NAME).listDocuments();
		Iterator<DocumentReference> iterator = documentReference.iterator();
		
		List<UserEntity> userList = new ArrayList<>();
		UserEntity user = null;
		while (iterator.hasNext()) {
			DocumentReference documentReference1 = iterator.next();
			ApiFuture<DocumentSnapshot> future = documentReference1.get();
			DocumentSnapshot document = future.get();
			
			user=document.toObject(UserEntity.class);
			userList.add(user);
			
		}
		
		return userList;
		
	}
	
	//update
	public String udateUsers(UserEntity entity)throws ExecutionException, InterruptedException{
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(entity.getName()).set(entity);
		return collectionApiFuture.get().getUpdateTime().toString();
	}
	
	//delete
	public String deleteUsers(String name) throws ExecutionException, InterruptedException{
		Firestore dbFirestore = FirestoreClient.getFirestore();
		ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(name).delete();
		return "Document Users Id"+name+"Berhasil dihapus";
	}
}


