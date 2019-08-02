package zcq.myjpa.repository;

import org.springframework.stereotype.Repository;
import zcq.myjpa.entity.Product;

import java.util.List;

/**
 * TODO
 *
 * @author zhengchuqin
 * @history 2019-07-31 zhengchuqin 新建
 * @since JDK1.8
 */
@Repository
public interface ProductRepository extends BaseRepository<Product> {
    Product findByCode(String code);
}
