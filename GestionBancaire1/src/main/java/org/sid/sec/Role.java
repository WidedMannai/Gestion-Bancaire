package org.sid.sec;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

import javax.persistence.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role") 
public class Role {

    	@Id
    	@GeneratedValue(strategy = GenerationType.IDENTITY)
    	private Long id ;
    	
    	@Column(name = "role")
    	private String role;
    	
    	@ManyToMany(mappedBy="role")
    	private List<User> user;
    	
    	
    	public String getRole() {
    		return role;
    	}
    	public void setRole(String role) {
    		this.role = role;
    	}
    	public Role(String role) {
    		this.role = role;
    	}
    	
		public List<User> getUser() {
			return user;
		}
		public void setUser(List<User> user) {
			this.user = user;
		}
		public Role() {
    	}
		public Role(String role, List<User> user) {
			super();
			this.role = role;
			this.user = user;
		}

 
   
    
	
	
}