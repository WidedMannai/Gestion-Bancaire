package org.sid.sec;


import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")  // kent name ="users" f tuto w badltha user l wided
    public class User {
      
	    @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "user_id")
	    private Long id;
    	
	   
        
        private int cin ;

        @Column(name = "username")
        @Length(min = 5, message = "*Your user name must have at least 5 characters")
        @NotEmpty(message = "*Please provide a user name")
		private String username;
        
        @Column(name = "password")
        @Length(min = 5, message = "*Your password must have at least 5 characters")
        @NotEmpty(message = "*Please provide your password")
        private String password;

        @Transient
        private String passwordConfirm;
        
        @Column(name = "name")
       // @NotEmpty(message = "*Please provide your name")
        private String name;
        
        @Column(name = "last_name")
       // @NotEmpty(message = "*Please provide your last name")
        private String lastName;
        
        @Column(name = "active")
        private Boolean active;
        

        @ManyToOne
        @JoinColumn(name ="Role")
    	private Role role;
        
        @Column(name = "email")
        @Email(message = "*Please provide a valid Email")
        @NotEmpty(message = "*Please provide an email")
        private String email;
        
        private String post;
        private int tel;
        
        private Date dateCreation;
        

		
        
     
        
		public User(Long id,  int cin, @NotNull String username, @NotNull String password, String passwordConfirm, Role role, @NotNull String name,
				@NotNull String lastname,@NotNull String email, String post, int tel, Date dateCreation) {
			super();
			this.id = id;
			this.cin = cin;
			this.username = username;
			this.password = password;
			this.passwordConfirm = passwordConfirm;
			this.role = role;
			this.name= name;
			this.lastName = lastName;
			this.email = email;
			this.post = post;
			this.tel = tel;
			this.dateCreation = dateCreation;
		}



		public User() {
			
			// TODO Auto-generated constructor stub
		}



		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
		public String getPasswordConfirm() {
			return passwordConfirm;
		}
		public void setPasswordConfirm(String passwordConfirm) {
			this.passwordConfirm = passwordConfirm;
		}
		
		public String getName() {
			return name;
		}
		public Role getRole() {
			return role;
		}



		public void setRole(Role role) {
			this.role = role;
		}



		public void setName(String name) {
			this.name = name;
		}
		public String getLastName() {
			return lastName;
		}
		public void setLastName(String lastName) {
			this.lastName = lastName;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getPost() {
			return post;
		}
		public void setPost(String post) {
			this.post = post;
		}
		public int getTel() {
			return tel;
		}
		public void setTel(int tel) {
			this.tel = tel;
		}
		public int getCin() {
			return cin;
		}
		public void setCin(int cin) {
			this.cin = cin;
		}
        

		  public Date getDateCreation() {
				return dateCreation;
			}



			public void setdateCreation(Date dateCreation) {
				this.dateCreation = dateCreation;
			}



			public Boolean getActive() {
				return active;
			}



			public void setActive(Boolean active) {
				this.active = active;
			}



			

     
    
}