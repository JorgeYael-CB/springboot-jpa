package com.yael.curso.springboot.jpa.springboot_jpa;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.Transactional;

import com.yael.curso.springboot.jpa.springboot_jpa.dto.PersonDto;
import com.yael.curso.springboot.jpa.springboot_jpa.entities.Person;
import com.yael.curso.springboot.jpa.springboot_jpa.repositories.IPersonRepository;


@SpringBootApplication
public class SpringbootJpaApplication implements CommandLineRunner {

	@Autowired
	private IPersonRepository repository;


	public static void main(String[] args) {
		SpringApplication.run(SpringbootJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.update();
	}


	@Transactional(readOnly=true)
	public void list(){
		// List<Person> persons = (List<Person>) this.repository.findAll();
		List<Person> persons = (List<Person>) repository
			.findByProgrammingLanguageAndName("Java", "Maria");

		List<Object[]> personsValues = repository.obtenerPersonData("Pepe");
		personsValues.stream().forEach(person -> {
			System.out.println(person[0] + " -> " + person[1]);
		});

		persons.stream().forEach( person -> System.out.println(person) );
	}

	@Transactional(readOnly=true)
	public void findOne(){
		// Person person = null;
		// Optional<Person> optionalPerson = repository.findById(1L);

		// // if( !optionalPerson.isEmpty() )
		// if( optionalPerson.isPresent() ){
		// 	person = optionalPerson.get();
		// }
		// System.out.println(person);

		repository.findByNameContaining("se")
			.ifPresent( System.out::println );
	}

	@Transactional
	public void create() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Ingresa tu nombre: ");
		String name = scanner.next();
		System.out.print("Ingresa tu apellido: ");
		String lastName = scanner.next();
		System.out.print("Ingresa tu lenguaje de programacion: ");
		String programmingLanguaje = scanner.next();
		scanner.close();

		Person person = new Person(null, name, lastName, programmingLanguaje);

		Person newPerson = repository.save(person); // guardar nueva entidad en la base de datos
		System.out.println(newPerson);

		repository.findById(newPerson.getId())
			.ifPresent(System.out::println);
	}

	@Transactional
	public void update(){
		Scanner scanner = new Scanner(System.in);

		System.out.print("Ingrese el id de la persona a editar: ");
		Long userId = Long.valueOf(scanner.next());

		Optional<Person> optionalPerson = repository.findById(userId);

		if( optionalPerson.isPresent() ){
			System.out.println("**** INFORMACION ****");
			Person person = optionalPerson.get();

			System.out.println("Nombre: " + person.getName() + " " + person.getLastname());
			System.out.println("Lenguaje de programacion: " + person.getProgrammingLanguage());

			System.out.println("Ingrese el nuevo lenguaje de programacion: ");

			String newLanguaje = scanner.next();
			person.setProgrammingLanguage(newLanguaje);

			repository.save(person);

			System.out.println("*** CAMBIOS REALIZADOS CORRECTAMENTE ***");
			System.out.println("Nuevo lenguaje agregado: " + person.getProgrammingLanguage());
			return;
		}

		System.out.println("La persona no fue encontrada, ingrese de nuevo su Id.");
		update();
	}

	@Transactional
	public void remove(){
		Scanner scanner = new Scanner(System.in);

		System.out.println("Ingrese el id del usuario a eliminar: ");
		Long userId = Long.valueOf(scanner.next());

		repository.deleteById(userId);

		scanner.close();
	}

	@Transactional
	public void remove2(){
		Scanner scanner = new Scanner(System.in);

		System.out.println("Ingrese el id del usuario a eliminar: ");
		Long userId = Long.valueOf(scanner.next());

		Optional<Person> personOptional = repository.findById(userId);

		personOptional.ifPresentOrElse(
			repository::delete,
			()-> System.out.println("El usuario con el id: " + userId + " no existe"));

		scanner.close();
	}


	@Transactional(readOnly=true)
	public void personalizedQueries(){
		Scanner scanner = new Scanner(System.in);
		System.out.print("Ingrese el id de la persona: ");
		Long id = Long.valueOf(scanner.next());


		System.out.println("============ name ============");
		String name = repository.getNameById(id);
		System.out.println(name);


		System.out.println("============ id ============");
		Long userId = repository.getIdById(id);
		System.out.println(userId);


		System.out.println("============ full name ============");
		String fullName = repository.getFullNameById(id);
		System.out.println(fullName);

		System.out.println("============ full data by id ============");
		Object[] fullDataById = (Object[]) repository.obtenerPersonDataFullById(id);
		System.out.println("id="+fullDataById[0]+", name="+fullDataById[1]+", apellido="+fullDataById[2]+", lenguaje="+fullDataById[3]);

		System.out.println("============ full data by id ============");
		List<Object[]> fullData = repository.obtenerPersonData();
		fullData.forEach( p -> {
			System.out.println("id="+p[0]+", name="+p[1]+", apellido="+p[2]+", lenguaje="+p[3]);
		});


		scanner.close();
	}

	@Transactional(readOnly=true)
	public void personalizedQueries2(){
		System.out.println("++++++++++ Consulta por obj persona ++++++++++");
		List<Object[]> personRegs = repository.findAllMixPerson();
		personRegs.forEach( reg -> {
			System.out.println("programmingLanguaje " + reg[1] + " " + reg[0]);
		});

		System.out.println("++++++++++ Clase persona instanciada desde Db ++++++++++");
		List<Person> persons = repository.findAllClassPerson();
		persons.forEach( System.out::println );

		System.out.println("++++++++++ Clase persona ++++++++++");
		List<PersonDto> personsDtos = repository.findAllPersonDto();
		personsDtos.forEach( System.out::println );
	}


	@Transactional(readOnly=true)
	public void personalizedQueriesDistinct(){
		System.out.println("++++++++++++++++++++++++ names ++++++++++++++++++++++++");
		List<String> names = repository.findAllNames();
		names.forEach(System.out::println);

		System.out.println("++++++++++++++++++++++++ Names distinc ++++++++++++++++++++++++");
		List<String> names2 = repository.findAllNamesDistincts();
		names2.forEach(System.out::println);

		System.out.println("++++++++++++++++++++++++ languajes distinc ++++++++++++++++++++++++");
		List<String> programmingLanguajesDistinc = repository.findAllProgrammingLanguajesDistincts();
		programmingLanguajesDistinc.forEach(System.out::println);
	}

	@Transactional(readOnly=true)
	public void personalizedQueriesStrings(){
		System.out.println("++++++++++++++++ Nombres concat ++++++++++++++++");
		List<String> names = repository.findAllFullNameConcat();
		names.forEach(System.out::println);

		System.out.println("++++++++++++++++ Nombres uppers ++++++++++++++++");
		List<String> namesUppers = repository.findAllFullNameUpper();
		namesUppers.forEach(System.out::println);
	}

	@Transactional(readOnly=true)
	public void personalizedQueriesBetween(){
		System.out.println("++++++++++++++ Between Id ++++++++++++++");
		List<Person> personsBetweenId = repository.findAllBetweenId();
		personsBetweenId.forEach(System.out::println);

		System.out.println("++++++++++++++ Between Name ++++++++++++++");
		List<Person> personsBetweenName = repository.findAllBetweenName('J', 'D');
		personsBetweenName.forEach(System.out::println);
	}



}
