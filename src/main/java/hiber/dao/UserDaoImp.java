package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   public List listUsers() {
      Query query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public User getUserByCar(String model, Integer series) {
      return (User) sessionFactory.getCurrentSession()
              .createQuery("from User usr where usr.car.model=:model and usr.car.series=:series")
              .setParameter("model", model)
              .setParameter("series", series)
              .uniqueResult();
   }
}
