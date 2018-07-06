package bootjsp.demo.Dao;

import bootjsp.demo.model.student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface studentRepo extends JpaRepository<student, Integer> {
    student findByName(String mohamed);

    List<student> findByIdGreaterThan(int id);

    List<student> findAllByAddressOrderByName(String address);


}
