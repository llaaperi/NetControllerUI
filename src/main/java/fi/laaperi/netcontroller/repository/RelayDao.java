package fi.laaperi.netcontroller.repository;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
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
		logger.debug("Persist relay " + relay);
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(relay);
	}
	
	@Transactional
	public void persistAll(List<Relay> relays){
		logger.debug("Persist " + relays.size() + " relays");
		for(Relay relay : relays){
			persist(relay);
		}
	}
	
	@Transactional
	public Relay findById(Long id) {
		logger.debug("Find relay with id " + id);
		Session session = sessionFactory.getCurrentSession();
		Relay relay = (Relay)session.get(Relay.class, id);
		return relay;
	}
	
	@Transactional
	public List<Relay> getAll() {
		logger.debug("Get all relays");
		Session session = sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(Relay.class);
		cr.addOrder(Order.asc("id"));
		@SuppressWarnings("unchecked")
		List<Relay> relays = cr.list();
		return relays;
	}
	
	@Transactional
	public void delete(long id){
		logger.debug("Delete relay with id " + id);
		Session session = sessionFactory.getCurrentSession();
		Relay relay = (Relay)session.get(Relay.class, id);
		session.delete(relay);
	}
	
}
