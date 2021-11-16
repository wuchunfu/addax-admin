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

package com.wgzhao.addax.admin.enums;

public class SystemConfigTypeConstant
{
    public enum SystemConfigTypeEnum
    {
        TARGET_TABLE_AUTO_UPDATE("TARGET_TABLE_AUTO_UPDATE", "目标表自动更新"),

        SCAN_TIME("SCAN_TIME", "扫描时间设置");

        SystemConfigTypeEnum(String code, String value)
        {
            this.code = code;
            this.value = value;
        }

        private String value;
        private String code;

        public String getValue()
        {
            return value;
        }

        public void setValue(String value)
        {
            this.value = value;
        }

        public String getCode()
        {
            return code;
        }

        public void setCode(String code)
        {
            this.code = code;
        }
    }

    public enum SystemConfigStatusEnum
    {
        OPEN(1, "开启"),

        CLOSE(0, "关闭");

        SystemConfigStatusEnum(Integer code, String value)
        {
            this.code = code;
            this.value = value;
        }

        private String value;
        private Integer code;

        public String getValue()
        {
            return value;
        }

        public void setValue(String value)
        {
            this.value = value;
        }

        public void setCode(Integer code)
        {
            this.code = code;
        }

        public Integer getCode()
        {
            return code;
        }

        public static String getValue(Integer code)
        {
            for (SystemConfigStatusEnum ele : SystemConfigStatusEnum.values()) {
                if (code.equals(ele.getCode())) {
                    return ele.getValue();
                }
            }
            return "";
        }
    }

    public enum CycleEnum
    {
        NON(0, "无"),

        EVERY_DAY(1, "每天");

        CycleEnum(Integer code, String value)
        {
            this.code = code;
            this.value = value;
        }

        private String value;
        private Integer code;

        public String getValue()
        {
            return value;
        }

        public void setValue(String value)
        {
            this.value = value;
        }

        public void setCode(Integer code)
        {
            this.code = code;
        }

        public Integer getCode()
        {
            return code;
        }

        public static String getValue(Integer code)
        {
            for (CycleEnum ele : CycleEnum.values()) {
                if (code.equals(ele.getCode())) {
                    return ele.getValue();
                }
            }
            return "";
        }
    }
}
