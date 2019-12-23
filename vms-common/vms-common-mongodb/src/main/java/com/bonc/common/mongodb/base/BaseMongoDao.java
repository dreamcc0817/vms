package com.bonc.common.mongodb.base;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @Title: vms
 * @Package: com.bonc.common.mongodb.base
 * @Description: mongodb工具类
 * @Author: dreamcc
 * @Date: 2019/12/23 11:31
 * @Version: V1.0
 */
public interface BaseMongoDao<T> {

	/**
	 * 插入对象
	 *
	 * @param entity     对象
	 * @return
	 */
	T save(T entity);

	/**
	 * 更新对象
	 *
	 * @param entity 对象信息
	 * @return
	 */
	UpdateResult update(T entity);

	/**
	 * 删除对象
	 *
	 * @param t 删除对象
	 * @return
	 */
	DeleteResult remove(T t);

	/**
	 * 查找对象
	 *
	 * @param query 筛选条件
	 * @return
	 */
	T findOne(Query query);
}
