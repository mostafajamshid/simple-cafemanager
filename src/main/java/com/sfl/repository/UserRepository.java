package com.sfl.repository;

/**
 * Created by Mostafa Jamshid on 27.05.2021.
 */
import com.sfl.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sfl.model.User;

import java.util.List;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);
	List<User> findByRole(Role role);


}
