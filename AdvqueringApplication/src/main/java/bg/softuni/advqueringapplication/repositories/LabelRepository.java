package bg.softuni.advqueringapplication.repositories;

import bg.softuni.advqueringapplication.entities.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LabelRepository extends JpaRepository<Label, Long> {
}
