package springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import springboot.entity.News;

import javax.transaction.Transactional;

/**
 * ◢◤●████▄▄▄▄▄▄ ●●●●●   Created with Intellij IDEA.
 * ▄▅██████▅▄▃▂          User: Mario.Hu
 * ██████████████        Date: 07/04/2017 20:26
 * ◥⊙▲⊙▲⊙▲⊙▲⊙▲⊙▲◤
 */
public interface NewsResp extends JpaRepository<News, Long> {
    @Transactional
    @Query("select title from News where id = :qId")
    String findTitle(@Param("qId") long Id);

    @Modifying
    @Transactional
    @Query("update News set title=:qTitle, news=:qNews where id=:qId")
    void updateNews(@Param("qTitle") String title, @Param("qNews") String news,@Param("qId") long Id);
}
