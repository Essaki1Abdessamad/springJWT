package com.example.securityjwt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {

   @Id @GeneratedValue(strategy = GenerationType.AUTO)
   private Long Id;
   private String name;

}
