package springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import springboot.entity.Userinfo;

import javax.transaction.Transactional;

/**
 * ◢◤●████▄▄▄▄▄▄ ●●●●●   Created with Intellij IDEA.
 * ▄▅██████▅▄▃▂          User: Mario.Hu
 * ██████████████        Date: 07/04/2017 16:20
 * ◥⊙▲⊙▲⊙▲⊙▲⊙▲⊙▲◤
 */
@Repository
public interface UserinfoResp extends JpaRepository<Userinfo,Long>{

    @Transactional
    @Query("select password from Userinfo where num = :qnumber")
    String findAccount(@Param("qnumber") String number);

    @Transactional
    @Query("select name from Userinfo where num = :qnumber")
    String findName(@Param("qnumber") String number);

    @Transactional
    @Query("select department from Userinfo where num = :qnumber")
    String findDepart(@Param("qnumber") String number);

    @Modifying
    @Transactional
    @Query("update Userinfo set time=:qTime where num=:qnumber")
    void updateTime(@Param("qTime") String date, @Param("qnumber") String number);

}
