package zcq.myjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import zcq.myjpa.entity.Bill;

import java.util.List;

/**
 * TODO
 *
 * @author zhengchuqin
 * @history 2019-07-31 zhengchuqin 新建
 * @since JDK1.8
 */
@Repository
public interface BillRepository extends BaseRepository<Bill,Long> {

    @Query(value = "select * from mj_bill where id > ?2 and ?1 <> 'n9342'",nativeQuery = true)
    List<Bill> selectByCodeAndId(String code, String id);
}
