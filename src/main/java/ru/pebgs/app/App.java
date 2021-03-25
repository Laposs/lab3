package ru.pebgs.app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.pebgs.model.Mark;
import ru.pebgs.model.Student;

import javax.persistence.EntityManager;

@SpringBootApplication(scanBasePackages = "ru.pebgs.*")
public class App implements CommandLineRunner {

    @Autowired
    private SessionFactory sessionFactory;

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        createStudents();
        createMarks();
    }

    private void createStudents() {
        Student student = Student.builder()
                .name("Name")
                .secondName("SecondName")
                .surname("surname")
                .build();
        Session session = this.sessionFactory.openSession();
        session.getTransaction().begin();
        session.save(student);
        session.getTransaction().commit();
        session.close();
    }

    private void createMarks() {
        Mark mark = new Mark();
        mark.setName("Отлично");
        mark.setValue("5");
        Session session = this.sessionFactory.openSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        session.save(mark);
        transaction.commit();
        session.close();
    }
}
