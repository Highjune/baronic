package com.hospital.baronic.domain.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// JpaRepository<PatientDto, Integer>에서 <>는 <Entity, 기본키(pk)의 타입>
// 이렇게만 해주면 기본적인 CRUD 메소드 자동 생성됨
@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {
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
