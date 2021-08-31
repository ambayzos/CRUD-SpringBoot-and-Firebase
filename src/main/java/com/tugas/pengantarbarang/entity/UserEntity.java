package com.tugas.pengantarbarang.entity;


import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserEntity {

	private String name;
	private String username;
	private String password;

}
