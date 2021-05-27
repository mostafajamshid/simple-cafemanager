package com.sfl.repository;

/**
 * Created by Mostafa Jamshid on 27.05.2021.
 */
import com.sfl.model.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("tableRepository")
public interface TableRepository extends JpaRepository<Table, Integer> {
}
