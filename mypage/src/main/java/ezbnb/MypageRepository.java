package ezbnb;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

//@RepositoryRestResource(collectionResourceRel="mypages", path="mypages")
public interface MypageRepository extends CrudRepository<Mypage, Long> {

    List<Mypage> findByBookId(Long bookId);

    List<Mypage> findByGuestName(String guestName);

    List<Mypage> findByHost(String host);

    /*
    public interface CrudRepository<T, ID extends Serializable> extends Repository<T, ID> {
    <S extends T> S save(S var1);
    <S extends T> Iterable<S> save(Iterable<S> var1);
 
    T findOne(ID var1);
    
    <T> findAll();
    Iterable<T> findAll(Iterable<ID> var1);
 
    boolean exists(ID var1);
    long count();
 
    void delete(ID var1);
    void delete(T var1);
    void delete(Iterable<? extends T> var1);
    void deleteAll();
}

    */

}