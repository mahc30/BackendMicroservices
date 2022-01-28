package backendJava.client.repository;

import backendJava.client.entity.Foto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoRepository  extends MongoRepository<Foto, String>{}
