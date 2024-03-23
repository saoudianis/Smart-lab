package com.example.msorderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.sql.Connection;

@SpringBootApplication
@ComponentScan("com.example.msorderservice")
public class MsorderserviceApplication {



	public static void main(String[] args) {
		DbFunctions db=new DbFunctions();
		//Connection conn =db.connect_to_db("tutdb","postgres","mysecretpassword");
		//db.createTable(conn,"employee");
		//db.insert_row(conn,"employee","test","test");
		//db.update_name(conn,"employee","islam","islam updated");
		//db.read_data(conn,"employee");
		//db.search_by_name(conn,"employee","Anis");
		//db.search_by_id(conn,"employee",2);
		//db.Delete_row_by_id(conn,"employee",4);


		SpringApplication.run(MsorderserviceApplication.class, args);

	}

}
