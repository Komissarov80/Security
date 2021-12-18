package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      Car car = user.getCar();
      sessionFactory.getCurrentSession().save(car);
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

   @Override
   public void add(Car car) {
      sessionFactory.getCurrentSession().save(car);
   }

   @Override
   @SuppressWarnings("unchecked")
   public User getUserByCar(String model, int series) {
       TypedQuery<User> query = null;
       String hql = "FROM User u WHERE u.car.model=:paramModel and u.car.series=:paramSeries";
       try {
          query = sessionFactory.getCurrentSession().createQuery(hql);
       } catch (HibernateException e) {
          e.printStackTrace();
       }
       query.setParameter("paramModel", model).setParameter("paramSeries", series);
       return query.setMaxResults(1).getSingleResult();
   }

}
