package plittr.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import plittr.entity.Plitter;
public interface PlitterRepository extends JpaRepository<Plitter,Long>{

	Plitter findByUsername(String userName);

}
