package com.example.demospring.repository;

import com.example.demospring.model.Person;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

@Repository
public interface IPersonRepository extends JpaRepository<Person, Long> {
    @Procedure(name = "addNewPerson")
    String addNewPerson(@Param("v_address") String v_address, @Param("v_avatar") String v_avatar, @Param("v_dob") LocalDate v_dob,
                      @Param("v_gender") Boolean v_gender, @Param("v_name") String v_name,
                      @Param("v_phone") String v_phone, @Param("v_classify_id") Long v_classify_id);

    @Procedure(name = "updatePerson")
    String updatePerson(@Param("v_address") String v_address, @Param("v_avatar") String v_avatar, @Param("v_dob") LocalDate v_dob,
                      @Param("v_gender") Boolean v_gender, @Param("v_name") String v_name,
                      @Param("v_phone") String v_phone, @Param("v_classify_id") Long v_classify_id,@Param("v_person_id") Long v_person_id);

    @Procedure(name = "deletePerson")
    String deletePerson(@Param("v_person_id") Long v_person_id);


    Iterable<Person> findAllByStatusIsTrueOrderByIdDesc();
    @Query(value = "select * from person where status = true and type_action= false order by id desc", nativeQuery = true)
    Iterable<Person> findAllPersonInFree();
    Page<Person> findAllByStatusIsTrueOrderByIdDesc(Pageable pageable);
    @Query(value = "select * from person where status = true and concat(name,code,phone) like :key and date_of_birth between :from and :to order by id desc",nativeQuery = true)
    Page<Person> findAllWithKey(String key, String from, String to, Pageable pageable);
    @Query(value = "select * from person where status = true and concat(name,code,phone) like :key and date_of_birth between :from and :to order by id desc",nativeQuery = true)
    Iterable<Person> findPersonWithKey(String key, String from, String to);
    @Query(value = "select * from person where id = (select max(id) from person)", nativeQuery = true)
    Optional<Person> getLastestPerson();
    Optional<Person> findPersonByPhone(String phone);
    Iterable<Person> findAllByNameContainingAndStatusIsTrue(String name);
    @Query(value = "select * from person where status = true and type_action = false and concat(name,code) like :key order by id desc",nativeQuery = true)
    Iterable<Person> findPersonWithKeyBorroed(String key);

}
