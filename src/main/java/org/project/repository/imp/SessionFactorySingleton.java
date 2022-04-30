package org.project.repository.imp;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.project.entity.BaseClass;
import org.project.entity.Customer;
import org.project.entity.Person;
import org.project.entity.Specialist;

import javax.persistence.PersistenceContext;

public class SessionFactorySingleton {

    private SessionFactorySingleton() {}

    private static class LazyHolder {
        static SessionFactory INSTANCE;

        static {
            var registry = new StandardServiceRegistryBuilder()
                    .configure() // goes and fetches configuration from hibernate.cfg.xml
                    .build();

            // registry is useful for creating SessionFactory
            // SessionFactory is a heavyweight object.
            // SessionFactory is thread safe.
            // SessionFactory is immutable.
            INSTANCE = new MetadataSources(registry)
                    .addAnnotatedClass(BaseClass.class)
                    .addAnnotatedClass(Person.class)
                    .addAnnotatedClass(Customer.class)
                    .addAnnotatedClass(Specialist.class)
                    .addAnnotatedClass(BaseClass.class)
                    .buildMetadata()
                    .buildSessionFactory();
        }
    }

    public static SessionFactory getInstance() {
        return LazyHolder.INSTANCE;
    }
}
