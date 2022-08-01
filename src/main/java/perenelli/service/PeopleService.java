package perenelli.service;

import perenelli.models.Book;
import perenelli.models.Person;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import perenelli.repositories.BookRepository;
import perenelli.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;


/**
 * Created by Vladislav Domaniewski
 */

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    private final BookRepository bookRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, BookRepository bookRepository) {
        this.peopleRepository = peopleRepository;
        this.bookRepository = bookRepository;
    }

    public List<Person> index() {
        return peopleRepository.findAll();
    }

    public Person show(int id) {
        Optional<Person> foundPerson= peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    public List<Book> getBookId(int id) {
        Optional<Person> person = peopleRepository.findById(id);
        Hibernate.initialize(person.get().getListBook());
        return person.get().getListBook();
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person personUpdated) {
        Person personUntilForUpdated = peopleRepository.findById(id).get();
        personUpdated.setId(id);
        peopleRepository.save(personUpdated);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

}
