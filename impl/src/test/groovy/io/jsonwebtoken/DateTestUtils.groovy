/*
 * Copyright (C) 2019 jsonwebtoken.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.jsonwebtoken

final class DateTestUtils {

    /**
     * Date util method for lopping truncate the millis from a date.
     * @param date input date
     * @return The date time in millis with the precision of seconds
     */
    static long truncateMillis(Date date) {
        Calendar cal = Calendar.getInstance()
        cal.setTime(date)
        cal.set(Calendar.MILLISECOND, 0)
        return cal.getTimeInMillis()
    }
}
