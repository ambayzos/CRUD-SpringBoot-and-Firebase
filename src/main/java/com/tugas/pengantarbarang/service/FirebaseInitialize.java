package com.tugas.pengantarbarang.service;

import java.io.FileInputStream;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@Service
public class FirebaseInitialize {

	@PostConstruct
	public void initialize() {
		 FileInputStream serviceAccount =
	                null;
		 
		try {
			
			 serviceAccount = new FileInputStream("./serviceAccount.json");
					FirebaseOptions options = new FirebaseOptions.Builder()
					  .setCredentials(GoogleCredentials.fromStream(serviceAccount))
					  .setDatabaseUrl("https://pengantarbarang-ec503-default-rtdb.asia-southeast1.firebasedatabase.app")
					  .build();
					FirebaseApp.initializeApp(options);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
}
