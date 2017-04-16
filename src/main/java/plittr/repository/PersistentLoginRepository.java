package plittr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import plittr.entity.PersistentLogin;

public interface PersistentLoginRepository extends JpaRepository<PersistentLogin, String> {

	PersistentLogin findBySeries(String seriesId);

	PersistentLogin findByUsername(String username);

}
