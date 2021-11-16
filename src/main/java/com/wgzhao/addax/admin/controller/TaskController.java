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

package com.wgzhao.addax.admin.controller;

import com.wgzhao.addax.admin.config.ServerResponse;
import com.wgzhao.addax.admin.server.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yangkai
 */
@RestController
@RequestMapping("/task")
public class TaskController
{
    @Autowired
    private AsyncManage asyncManage;

    @GetMapping (value = "readSource")
    public ServerResponse<String> readingSourceTask(String mainTaskId)
    {
        asyncManage.asyncMethod(mainTaskId);
        return  ServerResponse.createBySuccessMessage("请求成功");
    }
}

@Component
class AsyncManage{
    @Autowired
    private TaskService taskService;
    @Async
    public void asyncMethod(String mainTaskId){
        taskService.colInfoStorageTask(mainTaskId);
    }
}