package com.bonc.common.mongodb.util;

import cn.hutool.core.util.ClassUtil;
import com.bonc.common.mongodb.base.BaseMongoDao;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.Field;

/**
 * @Title: vms
 * @Package: com.bonc.common.mongodb.util
 * @Description: mongodbDAO装饰类
 * @Author: dreamcc
 * @Date: 2019/12/23 12:01
 * @Version: V1.0
 */
public class MongoDecorator<T extends Object> implements BaseMongoDao<T> {

	@Autowired
	private MongoTemplate mongoTemplate;

	@Override
	public T save(T entity) {
		return mongoTemplate.insert(entity);
	}

	@Override
	@SneakyThrows
	public UpdateResult update(T entity) {
		Class<T> clazz = this.getEntityClass();
		Field field = clazz.getDeclaredField("id");
		field.setAccessible(true);
		Long id = (Long) field.get(entity);
		Query query = new Query().addCriteria(Criteria.where("id").is(id));
		Update update = getUpdateObj(entity);
		return mongoTemplate.updateFirst(query, update, clazz);
	}

	@Override
	public DeleteResult remove(T query) {
		return mongoTemplate.remove(query);
	}

	@Override
	public T findOne(Query query) {
		Class c = getEntityClass();
		return mongoTemplate.findOne(query,getEntityClass());
	}

	/**
	 * 获取泛型类
	 *
	 * @return 泛型类对象
	 */
	private Class<T> getEntityClass() {
		return (Class<T>) ClassUtil.getTypeArgument(getClass(), 0);
	}

	/**
	 * 获取要更新的字段
	 *
	 * @param object 更新对象
	 * @return
	 */
	@SneakyThrows
	private Update getUpdateObj(final Object object) {
		Update update = null;
		Field[] fields = this.getEntityClass().getDeclaredFields();
		boolean isFirst = true;
		for (Field field : fields) {
			field.setAccessible(true);
			Object value = field.get(object);
			if ("id".equals(field.getName().toLowerCase()) || "serialversionuid".equals(field.getName().toLowerCase())) {
				continue;
			}
			if(value==null){
				continue;
			}
			if(isFirst){
				update = Update.update(field.getName(), value);
				isFirst = false;
			}else {
				update = update.set(field.getName(), value);
			}
		}
		return update;
	}
}
