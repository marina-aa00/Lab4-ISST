package es.upm.dit.isst.tfgapi.repository;
import org.springframework.data.repository.CrudRepository;
import java.util.List;
import  es.upm.dit.isst.tfgapi.model.*;


public interface TFGRepository extends CrudRepository<TFG, String> {

    List<TFG> findByTutor(String tutor);

}