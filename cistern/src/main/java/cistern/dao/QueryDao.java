package cistern.dao;

import java.util.List;

import cistern.common.AppBizException;

/**
 * @project: Steel
 * @description: ��ѯDao�ӿڶ�����
 * @author: seabao
 * @create_time: 2007-3-3
 * @modify_time: 2007-3-3
 */
public interface QueryDao<T, Q> {
	/**
	 * ��ѯ��������ڵĶ���
	 * @param cond ��������
	 * @param firstResult ��ʼ��¼��ţ���0��ʼ��
	 * @param maxResults ����¼����
	 * @return ����
	 */
	public List<T> query(Q cond, long firstResult, long maxResults);
	
	/**
	 * ���������ڵĶ���
	 * @param cond ��������
	 * @param callback �����ص�
	 */
	public void iterate(Q cond, IterateCallback callback) throws AppBizException;
	
	/**
	 * ��������ڵĶ�������
	 * @param cond ��������
	 * @return ��������
	 */
	public long count(Q cond);
	
	/**
	 * ��������ڵĽ���Զ���
	 * @param cond
	 * @param firstResult
	 * @param maxResults
	 * @return
	 */
	public QueryResult<T> queryForResult(Q cond, long firstResult, long maxResults);
}
