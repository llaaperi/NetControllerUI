package fi.laaperi.netcontroller.repository;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class RelayDao {

	private static final Logger logger = Logger.getLogger(RelayDao.class);
	
	@Autowired
	SessionFactory sessionFactory;
	
	
	@Transactional
	public void persist(Relay relay) {
		logger.info("Persist relay " + relay.getName());
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(relay);
	}
	
	@Transactional
	public void persistAll(List<Relay> relays){
		logger.info("Persist " + relays.size() + " relays");
		Session session = sessionFactory.getCurrentSession();
		for(Relay relay : relays){
			session.saveOrUpdate(relay);
		}
	}
	
	@Transactional
	public Relay findById(Long id) {
		logger.info("Find relay with id " + id);
		Session session = sessionFactory.getCurrentSession();
		Relay relay = (Relay)session.get(Relay.class, id);
		return relay;
	}
	
	@Transactional
	public List<Relay> getAll() {
		logger.info("Get all relays");
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Relay> relays = session.createCriteria(Relay.class).list();
		return relays;
	}
	
	@Transactional
	public void delete(long id){
		logger.info("Delete relay with id " + id);
		Session session = sessionFactory.getCurrentSession();
		Relay relay = (Relay)session.get(Relay.class, id);
		session.delete(relay);
	}
	
}
