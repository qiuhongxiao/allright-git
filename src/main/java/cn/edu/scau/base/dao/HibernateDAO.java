package cn.edu.scau.base.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author wxj
 *
 * @param <T>
 */
@Repository
@Transactional
public class HibernateDAO<T> {
	/**
	 * 增加操作
	 * @param entity
	 * @return
	 */
	@Resource(name="sessionFactory") 
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	public Session getSession(){
	    try{		  
		  Session session=sessionFactory.getCurrentSession();
		  return session;
		}catch(Exception e){
			
			e.printStackTrace();
			return null;
		}
		
	}
	public void save(T entity){
		if(entity!=null)
			this.getSession().save(entity);		
	}
	/**
	 * 更新或者修改
	 * @param entity
	 */
	public void update(T entity){
		try{
		if(entity!=null)
			//this.getSession().merge(entity);
			this.getSession().update(entity);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	/**
	 * 删除操作
	 * @param entity
	 */
	public void delete(T entity){
		if(entity!=null)
			
			this.getSession().delete(entity);
	}
	/**
	 * 根据hql删除数据
	 * @param hql
	 */
	public void delete(String hql){
		Query query = this.getSession().createQuery(hql);
		query.executeUpdate();
	}
	/**
	 * 根据具体类的id查询单条记录
	 * @param id
	 * @return
	 */
	public T get(Serializable id){
		return (T)this.getSession().get(getEntity(), id);
	}
	/**
	 * 获得所有的记录
	 * @return
	 */
	public List<T> findAll(){
		Criteria criteria = this.getSession().createCriteria(getEntity());
		return criteria.list();
	}
	/**
	 * 根据条件查询取得分页记录
	 * @param page 当前的处于第几页
	 * @param rows 每一页显示多少行
	 * @param sort 根据那个字段排序
	 * @param order 拍寻是升序还是降序
	 * @param kewords 搜索的关键词
	 * @param propertyforsearch 搜索的字段
	 * @return
	 */
	public List<T> findAll(int page,int rows,String sort,String order,String kewords,String propertyforsearch){
		List<T> criterialist = new ArrayList<T>();
		Criteria criteria = this.getSession().createCriteria(getEntity());
		String kewords1 = "";
		if(kewords!=null&&!kewords.isEmpty())
			kewords1=kewords;
		String searchwords="%"+kewords1+"%";
		System.out.println("searchwords:"+searchwords);
		criteria.add(Restrictions.like(propertyforsearch, searchwords));
		criteria.setFirstResult((page-1)*rows);
		criteria.setMaxResults(rows);
		if("asc".equals(order)){
			criteria.addOrder(Order.asc(sort));
		}else{
			criteria.addOrder(Order.desc(sort));
		}
		try{
			criterialist=criteria.list();
			return criterialist;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 获取整张表的记录个数
	 * @return
	 */
	public int countAll(){
		Criteria criteria = this.getSession().createCriteria(getEntity());
		return criteria.list().size();
	}
	/**
	 * 获取符合关键词查询的记录总数
	 * @param kewords
	 * @param propertyforsearch
	 * @return
	 */
	public int countAllForSearch(String kewords,String propertyforsearch){
		Criteria criteria = this.getSession().createCriteria(getEntity());
		String kewords1 = "";
		if(kewords!=null&&!kewords.isEmpty())
			kewords1=kewords;
		String searchwords="%"+kewords1+"%";
		criteria.add(Restrictions.like(propertyforsearch, searchwords));
		int count = criteria.list().size();
		System.out.println("查询记录总数："+count);
		return count;
	}
	/**
	 * 运行特定的hql查询获得第一条记录
	 * @param hql
	 * @return
	 */
	public T get(String hql){
		Query query = this.getSession().createQuery(hql);
		List<T> list = query.list();
		if(list.size()>0&&list!=null)
			return list.get(0);
		else
			return null;
		
	}
	/**
	 * 根据hql查询，并把参数封装在params的Maps中
	 * @param hql
	 * @param params
	 * @return
	 */
	public T get(String hql,Map<String,Object> params){
		Query query = this.getSession().createQuery(hql);
		if(params!=null&&params.isEmpty()){
			for(String key:params.keySet()){
				query.setParameter(key, params.get(key));
			}
		}
		List<T> list = query.list();
		if(list.size()>0&&list!=null)
			return list.get(0);
		else
			return null;
	}
	/**
	 * 直接传入包括参数的hql语句
	 * @param hql
	 * @return
	 */
	public List<T> find(String hql){
		Query query = this.getSession().createQuery(hql);
		return query.list();
	}
	/**
	 * 查找符合条件的记录
	 * @param hql
	 * @param params
	 * @return
	 */
	public List<T> find(String hql,Map<String,Object> params){
		Query query = this.getSession().createQuery(hql);
		if(params.isEmpty()&&params!=null){
			for(String key:params.keySet()){
				query.setParameter(key, params.get(key));
			}
		}
		List<T> list = query.list();
		return list;
	}
	/**
	 * 得到具体的实体类型
	 * @return
	 */
	protected Class<T> getEntity(){
		Class<T> entityClass = (Class<T>)((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
		return entityClass;
	}

}
