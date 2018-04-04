package info.stolarczyk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import info.stolarczyk.domain.Notes;

@Repository
public interface NotesRepository extends JpaRepository <Notes, Long> {

}
