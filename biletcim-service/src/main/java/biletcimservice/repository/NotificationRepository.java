package biletcimservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import biletcimservice.model.Notification;

@Repository
public interface NotificationRepository extends JpaRepository<Notification,Integer>{

}
