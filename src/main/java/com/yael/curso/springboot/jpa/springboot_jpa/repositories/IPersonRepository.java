package com.yael.curso.springboot.jpa.springboot_jpa.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.yael.curso.springboot.jpa.springboot_jpa.dto.PersonDto;
import com.yael.curso.springboot.jpa.springboot_jpa.entities.Person;


//TODO: https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html#jpa.query-methods.query-creation




// tipo de dato de la entidad y la llave primaria
// puede tambien heredar de JpaRepository
//? JPQL -> personalizados
public interface IPersonRepository extends CrudRepository<Person, Long> {

    @Query("select p from Person p where p.name between ?1 and ?2")
    List<Person> findAllBetweenName(char c1, char n2);

    @Query("select p from Person p where p.id between 2 and 5")
    List<Person> findAllBetweenId();

    // @Query("select CONCAT(p.name, ' ', p.lastname) from Person p")
    // List<String> findAllFullNameConcat();
    @Query("select p.name || ' ' || p.lastname from Person p")
    List<String> findAllFullNameConcat();

    @Query("select UPPER(p.name || ' ' || p.lastname) from Person p")
    List<String> findAllFullNameUpper();

    @Query("select DISTINCT(p.programmingLanguage) from Person p")
    List<String> findAllNamesDistincts();

    @Query("select DISTINCT(p.name) from Person p")
    List<String> findAllProgrammingLanguajesDistincts();

    @Query("select p.name from Person p")
    List<String> findAllNames();

    //Para algo que no es una entidad de la base de datos, teienes que agregar todo el package.
    @Query("select new com.yael.curso.springboot.jpa.springboot_jpa.dto.PersonDto(p.name, p.lastname) from Person p")
    List<PersonDto> findAllPersonDto();

    @Query("select new Person(p.name, p.lastname) from Person p")
    List<Person> findAllClassPerson();

    @Query("select p.name from Person p where p.id=?1")
    String getNameById(Long id);

    @Query("select p.id from Person p where p.id=?1")
    Long getIdById(Long id);

    @Query("select concat(p.name, ' ', p.lastname) as fullname from Person p where p.id=?1")
    String getFullNameById(Long id);


    List<Person> findByProgrammingLanguage(String programmingLanguage); // lo pasa automaticamente

    // escribir querys de Jpa
    // @Query("select p from Person p where p.programmingLanguage=?1 and p.name=?2")
    // List<Person> buscarByProgrammingLanguage(String programmingLanguage); //hace query automaticamente

    // Hace query automaticamente mediante nombres especificos de la documentacion
    List<Person> findByProgrammingLanguageAndName(String programmingLanguage, String name); //hace query automaticamente

    @Query("select p.id, p.name, p.lastname from Person p")
    List<Object[]> obtenerPersonDataFull();

    @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p where p.id=?1")
    Object obtenerPersonDataFullById(Long id);

    @Query("select p, p.programmingLanguage from Person p")
    List<Object[]> findAllMixPerson();

    @Query("select p.id, p.name, p.lastname, p.programmingLanguage from Person p")
    List<Object[]> obtenerPersonData();

    @Query("select p.name, p.programmingLanguage from Person p where p.name=?1")
    List<Object[]> obtenerPersonData(String name);

    @Query("select p from Person p where p.id=?1")
    Optional<Person> findOne(Long id);

    @Query("select p from Person p where p.name=?1")
    Optional<Person> findOneByName(String name);

    @Query("select p from Person p where p.name like %?1%")
    Optional<Person> findOneLikeName(String name);

    Optional<Person> findByNameContaining(String name); // hace el like
}
