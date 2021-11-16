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

package com.wgzhao.addax.admin.utils;

import lombok.extern.log4j.Log4j2;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author liuting
 */
@Log4j2
public class DateUtil
{
    /**
     * 日期格式，年月日，例如：20061225，20080808
     */
    public static final String DATE_FORMAT_YYYYMMDD = "yyyyMMdd";

    /**
     * 日期格式，年月日，用横杠分开，例如：2006-12-25，2008-08-08
     */
    public static final String DATE_FORMAT_YYYY_MM_DD = "yyyy-MM-dd";

    /**
     * 日期格式，年月日时分秒，年月日用横杠分开，时分秒用冒号分开 例如：2005-05-10 23：20：00，2008-08-08 20:08:08
     */
    public static final String DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * LocalDateTime转String
     *
     * @param timeFormat 日期格式
     * @param date 日期
     * @return String
     */
    public static String parseLocalDateTimeToStr(String timeFormat, LocalDate date)
    {
        return DateTimeFormatter.ofPattern(timeFormat).format(date);
    }

    /**
     * 格式化Date时间
     *
     * @param time Date类型时间
     * @param timeFromat String类型格式
     * @return 格式化后的字符串
     */
    public static String parseDateToStr(Date time, String timeFromat)
    {
        DateFormat dateFormat = new SimpleDateFormat(timeFromat);
        return dateFormat.format(time);
    }

    /**
     * 格式化String时间
     *
     * @param time String类型时间
     * @param timeFromat String类型格式
     * @return 格式化后的Date日期
     */
    public static Date parseStrToDate(String time, String timeFromat)
    {
        if (time == null || time.equals("")) {
            return null;
        }
        Date date = null;
        try {
            DateFormat dateFormat = new SimpleDateFormat(timeFromat);
            date = dateFormat.parse(time);
        }
        catch (Exception e) {
        }
        return date;
    }

    /**
     * 获取某个时间的前/后多少天
     *
     * @param timeFormat 日期格式
     * @param date 某个日期
     * @param days 几天
     * @return String
     */
    public static String getDayByDaysAndFormat(String timeFormat, int days, LocalDate date)
    {
        return date.plusDays(days).format(DateTimeFormatter.ofPattern(timeFormat));
    }
}
