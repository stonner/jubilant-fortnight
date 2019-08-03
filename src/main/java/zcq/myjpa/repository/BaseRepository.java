package zcq.myjpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * TODO
 *
 * @author zhengchuqin
 * @history 2019-08-01 zhengchuqin 新建
 * @since JDK1.8
 */
@NoRepositoryBean
public interface BaseRepository<T,E> extends JpaRepository<T,E>, JpaSpecificationExecutor<T> {
}
