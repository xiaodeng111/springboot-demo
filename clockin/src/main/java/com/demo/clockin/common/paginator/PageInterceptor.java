package com.demo.clockin.common.paginator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.binding.MapperMethod.ParamMap;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.MappedStatement.Builder;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * 分页插件
 * 
 * @author Bobbie.Qi
 */
@Intercepts({ @Signature(type = Executor.class, method = "query", args = { MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class } )})
public class PageInterceptor implements Interceptor {
	
	protected static final Log LOG = LogFactory.getLog(PageInterceptor.class);
	
	static int MAPPED_STATEMENT_INDEX = 0;
	static int PARAMETER_INDEX = 1;
	static int ROWBOUNDS_INDEX = 2;
	static int RESULT_HANDLER_INDEX = 3;
	
	private Dialect dialect;
	private String pageMatch = ".*Page$";

	@Override
	public Object intercept(Invocation inv) throws Throwable {
        final Object[] queryArgs = inv.getArgs();
        // 查找分页参数
        PageRequest pageRequest = this.findPageableObject(queryArgs[PARAMETER_INDEX]);
        final MappedStatement ms = (MappedStatement)queryArgs[MAPPED_STATEMENT_INDEX];
        
		if(isPageable(pageRequest, ms)) {
	        final Object parameter = queryArgs[PARAMETER_INDEX];
			final BoundSql boundSql = ms.getBoundSql(parameter);
			
			// 删除尾部的 ';'
	        String sql = boundSql.getSql().trim().replaceAll(";$", "");

	        // 1. 查询总记录数（如果需要的话）			
			int total = 0;
			if (pageRequest.getIsCount() == 0) {
				total = this.queryTotal(sql, ms, boundSql);
			}
			
			// 2. 拼装limit查询
			// 2.1 获取分页SQL，并完成参数准备
			String limitSql = dialect.getLimitString(sql, pageRequest.getOffset(), pageRequest.getPageSize());			
			
			queryArgs[ROWBOUNDS_INDEX] = new RowBounds(RowBounds.NO_ROW_OFFSET,RowBounds.NO_ROW_LIMIT);
			queryArgs[MAPPED_STATEMENT_INDEX] = copyFromNewSql(ms, boundSql, limitSql);
			
			// 2.2 继续执行剩余步骤，获取查询结果
			Object result = inv.proceed();
			
			// 3. 组成分页对象
			@SuppressWarnings({ "unchecked", "rawtypes" })
			Page<?> page = new Page((List<?>)result, pageRequest.getPageNumber(), pageRequest.getPageSize(), total);
			
			// 4. MyBatis 需要返回一个List对象，这里只是满足MyBatis而作的临时包装
			List<Page<?>> tmp = new ArrayList<Page<?>>(1);
			tmp.add(page);
			return tmp;
		}
		
		return inv.proceed();
	}
	
	/**
	 * 判断是否需要分页
	 */
	private boolean isPageable(PageRequest pageRequest, MappedStatement ms) {
		if (pageRequest != null && ms.getId().matches(pageMatch)) {
			return true;
		}
		return false;
	}
	
	/**
	 * 在方法参数中查找分页请求对象
	 */
	private PageRequest findPageableObject(Object params) {
		if (params == null) {
			return null;
		}
		
		// 单个参数 表现为参数对象
		if(PageRequest.class.isAssignableFrom(params.getClass())) {
			return (PageRequest) params;					
		}
		
		// 多个参数 表现为 ParamMap
		else if (params instanceof ParamMap) {
			@SuppressWarnings("unchecked")
            ParamMap<Object> paramMap = (ParamMap<Object>) params;
			for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
				Object paramValue = entry.getValue();
				if(paramValue != null && PageRequest.class.isAssignableFrom(paramValue.getClass())) {
					return (PageRequest) paramValue;
				}
			}
		}
		return null;
	}

	@Override
	public Object plugin(Object target) {
		if (Executor.class.isAssignableFrom(target.getClass())) {
            return Plugin.wrap(target, this);
        }
        return target;
	}
	
	@Override
	public void setProperties(Properties p) {
		String dialectClass = p.getProperty("dialectClass");
		if(dialectClass == null) {
			throw new IllegalArgumentException("DialectClass must not null");
		}
		try {
            setDialect((Dialect) Class.forName(dialectClass).newInstance());
		} catch (Exception e) {
			throw new RuntimeException("Cannot create dialect instance by dialectClass: " + dialectClass, e);
		}
		
		String pageMatch = p.getProperty("pageMatch");
		if(pageMatch != null) {
			this.pageMatch = pageMatch;
		}
	}
	
	/**
	 * 查询总记录数
	 */
	private int queryTotal(String sql, MappedStatement mappedStatement, BoundSql boundSql) throws SQLException {
		Connection connection = null;
        PreparedStatement countStmt = null;
        ResultSet rs = null;
        try {
        	connection = mappedStatement.getConfiguration().getEnvironment().getDataSource().getConnection();
    		String countSql = this.dialect.getCountString(sql);
            countStmt = connection.prepareStatement(countSql);
            BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
            setParameters(countStmt, mappedStatement, countBoundSql, boundSql.getParameterObject());
            rs = countStmt.executeQuery();
            int totalCount = 0;
            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
            return totalCount;
        } catch (SQLException e) {
            throw e;
        } finally {
        	if (rs != null) {
	        	try {
	                rs.close();
	            } catch (SQLException e) {
	            	LOG.error("Exception happens when doing: ResultSet.close()", e);
	            }
        	}
        	
        	if (countStmt != null) {
	            try {
	                countStmt.close();
	            } catch (SQLException e) {
	            	LOG.error("Exception happens when doing: PreparedStatement.close()", e);
	            }
        	}
        	
        	if (connection != null) {
	            try {
	            	connection.close();
	            } catch (SQLException e) {
	            	LOG.error("Exception happens when doing: Connection.close()", e);
	            }
        	}
        }
		
	}
	
	/**
     * 对SQL参数(?)设值 
     */
    private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql, Object parameterObject) throws SQLException {
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
        parameterHandler.setParameters(ps);
    }

	public Dialect getDialect() {
		return dialect;
	}

	public void setDialect(Dialect dialect) {
		this.dialect = dialect;
	}

	private MappedStatement copyFromNewSql(MappedStatement ms, BoundSql boundSql, String sql) {
		BoundSql newBoundSql = copyFromBoundSql(ms, boundSql, sql);
		return copyFromMappedStatement(ms, new BoundSqlSqlSource(newBoundSql));
	}
	
	public static class BoundSqlSqlSource implements SqlSource {
		BoundSql boundSql;
		public BoundSqlSqlSource(BoundSql boundSql) {
			this.boundSql = boundSql;
		}
		public BoundSql getBoundSql(Object parameterObject) {
			return boundSql;
		}
	}
	
	private BoundSql copyFromBoundSql(MappedStatement ms, BoundSql boundSql,
                                      String sql) {
		BoundSql newBoundSql = new BoundSql(ms.getConfiguration(),sql, boundSql.getParameterMappings(), boundSql.getParameterObject());
		for (ParameterMapping mapping : boundSql.getParameterMappings()) {
		    String prop = mapping.getProperty();
		    if (boundSql.hasAdditionalParameter(prop)) {
		        newBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
		    }
		}
		return newBoundSql;
	}
	
	//see: MapperBuilderAssistant
	private MappedStatement copyFromMappedStatement(MappedStatement ms, SqlSource newSqlSource) {
		Builder builder = new Builder(ms.getConfiguration(),ms.getId(),newSqlSource,ms.getSqlCommandType());
		
		builder.resource(ms.getResource());
		builder.fetchSize(ms.getFetchSize());
		builder.statementType(ms.getStatementType());
		builder.keyGenerator(ms.getKeyGenerator());
		if(ms.getKeyProperties() != null && ms.getKeyProperties().length !=0){
            StringBuffer keyProperties = new StringBuffer();
            for(String keyProperty : ms.getKeyProperties()){
                keyProperties.append(keyProperty).append(",");
            }
            keyProperties.delete(keyProperties.length()-1, keyProperties.length());
			builder.keyProperty(keyProperties.toString());
		}
		
		//setStatementTimeout()
		builder.timeout(ms.getTimeout());
		
		//setStatementResultMap()
		builder.parameterMap(ms.getParameterMap());
		
		//setStatementResultMap()
        builder.resultMaps(ms.getResultMaps());
		builder.resultSetType(ms.getResultSetType());
	    
		//setStatementCache()
		builder.cache(ms.getCache());
		builder.flushCacheRequired(ms.isFlushCacheRequired());
		builder.useCache(ms.isUseCache());
		
		return builder.build();
	}
}
