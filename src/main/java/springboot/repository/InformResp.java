package springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import springboot.entity.Inform;

import javax.transaction.Transactional;

/**
 * ◢◤●████▄▄▄▄▄▄ ●●●●●   Created with Intellij IDEA.
 * ▄▅██████▅▄▃▂          User: Mario.Hu
 * ██████████████        Date: 07/04/2017 16:20
 * ◥⊙▲⊙▲⊙▲⊙▲⊙▲⊙▲◤
 */
public interface InformResp extends JpaRepository<Inform,Long> {

    @Modifying
    @Transactional
    @Query("update Inform set message = :qMessage where id=:qId")
    void updateMessage(@Param("qMessage") String Message, @Param("qId") long Id);

    @Transactional
    @Query("select message from Inform where id = :qId")
    String findMessage(@Param("qId") long Id);
}
