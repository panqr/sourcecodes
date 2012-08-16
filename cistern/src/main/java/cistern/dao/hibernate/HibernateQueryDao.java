package cistern.dao.hibernate;

import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cistern.common.AppBizException;
import cistern.dao.IterateCallback;
import cistern.dao.QueryDao;
import cistern.dao.QueryResult;
import cistern.dao.simplequery.SimpleQueryFactory;

public class HibernateQueryDao<T, Q> extends HibernateDaoSupport implements QueryDao<T, Q> {
	/**
	 * SimpleQuery工厂
	 */
	protected SimpleQueryFactory simpleQueryFactory;
	
	/**
	 * 查询条件类
	 */
	protected Class<? extends Q> conditionClazz;

	public HibernateQueryDao(Class<? extends Q> qClazz) {
		conditionClazz = qClazz;
	}
	
	@PostConstruct
	public void init() {
		simpleQueryFactory.registerCondition(conditionClazz);
	}
	
	public long count(Q cond) {
		HQLQuery hqlQuery = simpleQueryFactory.genHQLQuery(cond);
		return HibernateUtil.countElements(this.getHibernateTemplate(), hqlQuery);
	}
	
	@SuppressWarnings("unchecked")
	public List<T> query(Q cond, long firstResult, long maxResults) {
		HQLQuery hqlQuery = simpleQueryFactory.genHQLQuery(cond);
		return HibernateUtil.queryElements(getHibernateTemplate(), hqlQuery, firstResult, maxResults);
	}

	public void iterate(Q cond, IterateCallback callback) throws AppBizException {
		HQLQuery hqlQuery = simpleQueryFactory.genHQLQuery(cond);
		HibernateUtil.iterateElement(getHibernateTemplate(), hqlQuery, callback);
	}

	public QueryResult<T> queryForResult(Q cond, long firstResult, long maxResults) {
		QueryResult<T> res = new QueryResult<T>();
		res.setElements(query(cond, firstResult, maxResults));
		res.setCount(count(cond));
		
		return res;
	}

	public SimpleQueryFactory getSimpleQueryFactory() {
		return simpleQueryFactory;
	}

	@Autowired
	public void setSimpleQueryFactory(@Qualifier("simpleQueryFactory")SimpleQueryFactory simpleQueryFactory) {
		this.simpleQueryFactory = simpleQueryFactory;
	}

	@Autowired(required=false)
	public void setSessionFactory2(@Qualifier("hibernateSessionFactory")SessionFactory sf) {
		super.setSessionFactory(sf);
	}

}
