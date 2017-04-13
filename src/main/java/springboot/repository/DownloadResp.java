package springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springboot.entity.Download;

import java.util.List;

/**
 * ◢◤●████▄▄▄▄▄▄ ●●●●●   Created with Intellij IDEA.
 * ▄▅██████▅▄▃▂          User: Mario.Hu
 * ██████████████        Date: 11/04/2017 20:20
 * ◥⊙▲⊙▲⊙▲⊙▲⊙▲⊙▲◤
 */
@Repository
public interface DownloadResp extends JpaRepository<Download,Long> {

    List<Download> findAllByOrderByIdDesc();
}
