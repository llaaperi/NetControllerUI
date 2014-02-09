package fi.laaperi.netcontroller.repository;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class SensorDao {
	
	private static final Logger logger = Logger.getLogger(SensorDao.class);
	
	@Autowired
	SessionFactory sessionFactory;
	
	
	@Transactional
	public void persist(Sensor sensor) {
		logger.debug("Persist sensor " + sensor.getId());
		Session session = sessionFactory.getCurrentSession();
		if(sensor.getName() == null){
			sensor.setName("Sensor " + sensor.getId());
		}
		session.saveOrUpdate(sensor);
	}
	
	@Transactional
	public void persistAll(List<Sensor> sensors){
		logger.debug("Persist " + sensors.size() + " sensors");
		for(Sensor sensor : sensors){
			persist(sensor);
		}
	}
	
	
	@Transactional
	public Sensor findById(Long id) {
		logger.debug("Find sensor with id " + id);
		Session session = sessionFactory.getCurrentSession();
		Sensor sensor = (Sensor)session.get(Sensor.class, id);
		return sensor;
	}
	
	@Transactional
	public List<Sensor> getAll() {
		logger.debug("Get all sensors");
		Session session = sessionFactory.getCurrentSession();
		@SuppressWarnings("unchecked")
		List<Sensor> sensors = session.createCriteria(Sensor.class).list();
		return sensors;
	}
	
	@Transactional
	public void delete(long id){
		logger.debug("Delete sensor with id " + id);
		Session session = sessionFactory.getCurrentSession();
		Sensor sensor = (Sensor)session.get(Sensor.class, id);
		session.delete(sensor);
	}
}
