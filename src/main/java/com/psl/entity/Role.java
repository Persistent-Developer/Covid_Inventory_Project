package com.psl.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Role {

	@Id
	private int roleId;
	private String roleName;

	public Role() {
		super();
	}
	
	@OneToMany(mappedBy="role",cascade = CascadeType.ALL)  
	private List<User> users; 
	
	public Role(int roleid, String roleName) {
	super();
	this.roleId = roleid;
	this.roleName = roleName;
}

	public int getRoleId() {
		return roleId;
	}


	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


//	public List<User> getUsers() {
//		return users;
//	}
//
//
//	public void setUsers(List<User> users) {
//		this.users = users;
//	}


	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + "]";//", users=" + users + "]";
	}
	

}