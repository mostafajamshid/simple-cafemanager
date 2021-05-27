package com.sfl.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.sfl.model.Role;
import com.sfl.repository.RoleRepository;
@Service("roleService")
public class RoleService {


	@Qualifier("roleRepository")
	@Autowired
    private RoleRepository roleRepository;
	
	
	public RoleService(@Qualifier("roleRepository") RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}
	
	public List<Role> findAll(){
		List<Role> roles = new ArrayList<>();
		roles = roleRepository.findAll();
		return roles;
	}
	
	public Role findRole(int id){
		return roleRepository.findOne(id);
	}
	
	public void save(Role role){
		roleRepository.save(role);
	}
	
	public void delete(int id){
		roleRepository.delete(id);

	}

	public Role findRole(String role) {
		// TODO Auto-generated method stub
		return roleRepository.findByRole(role);
	}

}
