package cistern.dao;

import java.util.List;

import cistern.common.AppBizException;

/**
 * @project: Steel
 * @description: 查询Dao接口定义类
 * @author: seabao
 * @create_time: 2007-3-3
 * @modify_time: 2007-3-3
 */
public interface QueryDao<T, Q> {
	/**
	 * 查询获得条件内的对象集
	 * @param cond 条件对象
	 * @param firstResult 起始记录编号（从0开始）
	 * @param maxResults 最大记录数量
	 * @return 对象集
	 */
	public List<T> query(Q cond, long firstResult, long maxResults);
	
	/**
	 * 遍历条件内的对象集
	 * @param cond 条件对象
	 * @param callback 遍历回调
	 */
	public void iterate(Q cond, IterateCallback callback) throws AppBizException;
	
	/**
	 * 获得条件内的对象数量
	 * @param cond 条件对象
	 * @return 对象数量
	 */
	public long count(Q cond);
	
	/**
	 * 获得条件内的结果对对象
	 * @param cond
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	public QueryResult<T> queryForResult(Q cond, long firstResult, long maxResults);
}
