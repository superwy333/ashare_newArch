package com.zynsun.openplatform.mapper.provider;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

import java.util.Set;

public class PagerProvider extends MapperTemplate {
  
	//继承父类的方法
    public PagerProvider(Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }
    
    /**
     * 分页查询
     * @param ms
     * @return
     */
    public String selectPage(MappedStatement ms) {
    	
    	//获取实体
        final Class<?> entityClass = getEntityClass(ms);
        
        //修改返回值类型为实体类型
        setResultType(ms, entityClass);
        
        /*      
    	<select id="queryPage" parameterType="java.util.Map" resultType="java.lang.Long">
			select id from vc_area where 1=1
			<if user="areaName != null and areaName != ''">
		      and area_name like concat('%',#{areaName}, '%')
		    </if>
		    <if user="areaType != null and areaType != ''">
		       and area_type like concat('%',#{areaType}, '%')
		    </if>
		    <if user="areaCode != null and areaCode != ''">
		       and area_code like concat('%',#{areaCode}, '%')
		    </if>
	    </select>
	   */
        
        //开始拼sql
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.selectAllColumns(entityClass));
        sql.append(SqlHelper.fromTable(entityClass, tableName(entityClass)));
        sql.append(" where 1=1 ");
       
        //获取全部列
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        //当某个列有主键策略时，不需要考虑他的属性是否为空，因为如果为空，一定会根据主键策略给他生成一个值
        for (EntityColumn column : columnList) {
           sql.append("<if user=\""+ column.getProperty()+ "!= null and  "+ column.getProperty() + " !=''\" >"
           		+ " 	and "+ column.getColumn() +" like concat('%',#{"+column.getProperty()+"},'%')" 
           		+ " </if>");
        }
        
        return sql.toString();
    }
}
