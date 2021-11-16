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

package com.wgzhao.addax.admin.vo;

import com.wgzhao.addax.admin.enums.ProcessStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * @author liuting
 */
@ApiModel
public class JsonProcessVo
{
    @ApiModelProperty(value = "任务id")
    private String taskId;

    @ApiModelProperty(value = "进程状态 1:已完成；0：未完成")
    private Integer processStatus = ProcessStatusEnum.NOT_FINISHED.getCode();

    @ApiModelProperty(value = "进程列表")
    private List<ProcessTableVo> processVoList;

    public String getTaskId()
    {
        return taskId;
    }

    public void setTaskId(String taskId)
    {
        this.taskId = taskId;
    }

    public Integer getProcessStatus()
    {
        return processStatus;
    }

    public void setProcessStatus(Integer processStatus)
    {
        this.processStatus = processStatus;
    }

    public List<ProcessTableVo> getProcessVoList()
    {
        return processVoList;
    }

    public void setProcessVoList(List<ProcessTableVo> processVoList)
    {
        this.processVoList = processVoList;
    }
}
