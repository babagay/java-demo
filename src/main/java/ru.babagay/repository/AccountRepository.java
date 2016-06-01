package ru.babagay.repository;


import ru.babagay.entity.DomainObject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
import java.util.UUID;

//import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

//public interface AccountRepository extends JpaRepository<DomainObject, UUID> {
//}


public interface AccountRepository<V extends DomainObject> {

    void persist(V object);

    void delete(V object);

    Set<String> getRandomData();

}