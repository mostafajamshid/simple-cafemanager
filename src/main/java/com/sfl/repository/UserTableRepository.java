package com.sfl.repository;

/**
 * Created by Mostafa Jamshid on 27.05.2021.
 */
import com.sfl.model.Table;
import com.sfl.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sfl.model.WaiterTable;

import java.util.List;

@Repository("userTableRepository")
public interface UserTableRepository extends JpaRepository<WaiterTable, Integer> {
    List<WaiterTable> findByTable(Table table);
    List<WaiterTable> findByUser (User user);
}
