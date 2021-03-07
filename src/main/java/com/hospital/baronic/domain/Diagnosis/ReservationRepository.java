package com.hospital.baronic.domain.Diagnosis;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    /**
     * <JPA 메서드 문법>
     * EX)
     * And => findByLastnameAndFirstname (EX. where x.lastname = ?1 and x.firstname = ?2)
     * Or => findByLastnameOrFirstname (EX. where x.lastname = ?1 or x.firstname = ?2)
     * Is, Equals => findByName,findByNameIs,findByNameEquals (EX. where x.name = 1?)
     * Between => findBySalBetween(EX. where x.sal between 1? and ?2)
     * 기타 등등
     */
}
