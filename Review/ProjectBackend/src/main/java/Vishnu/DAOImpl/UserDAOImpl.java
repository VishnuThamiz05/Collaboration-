package Vishnu.DAOImpl;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import Vishnu.DAO.UserDAO;
import Vishnu.Model.User;


@Repository
@Transactional	
public class UserDAOImpl implements UserDAO{
	@Autowired
	private SessionFactory sessionF;
	public void registerUser(User user) {
	Session session=sessionF.getCurrentSession();
	session.save(user);
			
	}
	public boolean isEmailValid(String email) {
			Session session=sessionF.getCurrentSession();
			Query query=session.createQuery("from User where email='"+email+"'");
			User user=(User)query.uniqueResult();
			if(user==null)//email is valid,unique
				return true;
			else
				return false;
		}
	public boolean isUsernameValid(String username) {
			Session session=sessionF.getCurrentSession();
			User user=(User)session.get(User.class, username);
			if(user==null)
				return true;
			else
				return false;
		}

	public User login(User user) {
		Session session=sessionF.getCurrentSession();
		Query query=session.createQuery("from User where username=? and password=?");
		query.setString(0,user.getUsername());
		query.setString(1, user.getPassword());
		User validUser=(User)query.uniqueResult();
		return validUser;//either null or 1 user object
	}
	public void updateUser(User user) {
		Session session=sessionF.getCurrentSession();
		session.update(user);//update User set online=true where username=?
	}
	public User getUserByUsername(String username) {
		Session session=sessionF.getCurrentSession();
		User user=(User)session.get(User.class, username);
		return user;
	}


	}


