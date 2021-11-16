/*
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.wgzhao.addax.admin.mapper;

import com.wgzhao.addax.admin.pojo.TableInfo;

import java.util.List;

public interface TableInfoMapper {
    int insert(TableInfo record);

    int insertSelective(TableInfo record);

    /**
     * 查询字段表
     * @param record 查询条件
     * @return list
     */
    List<TableInfo> getTableInfos(TableInfo record);
}