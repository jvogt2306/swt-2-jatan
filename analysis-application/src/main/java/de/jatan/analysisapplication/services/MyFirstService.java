package de.jatan.analysisapplication.services;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.jatan.analysisapplication.Database.entities.AppVisitorList;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class MyFirstService {

  @PersistenceContext
  private EntityManager entityManager;

  public MyFirstService() {
    System.out.println("MyFirstService is created");
  }

  @EventListener(classes = ApplicationReadyEvent.class)
  public void init() {
    System.out.println("MyFirstService is Ready and Running");
  }

  static int counter = 0;

  @Transactional
  public HelloDto hello(String user) {
    counter++;
    String visitors = getVisitorList(user);
    return new HelloDto(String.format(
        "Hello Jatan Member %s, \n this application has been called %s times. \n Following User visited the page %s",
        user, counter, visitors));
  }

  private String getVisitorList(String user) {
    AppVisitorList result = entityManager.find(AppVisitorList.class, 1L);
    if (result == null) {
      AppVisitorList newList = new AppVisitorList();
      result = newList;
      entityManager.persist(newList);
    }
    result.addVisitor(user);
    return result.getListAsString();
  }
}
