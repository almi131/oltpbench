/******************************************************************************
 *  Copyright 2015 by OLTPBenchmark Project                                   *
 *                                                                            *
 *  Licensed under the Apache License, Version 2.0 (the "License");           *
 *  you may not use this file except in compliance with the License.          *
 *  You may obtain a copy of the License at                                   *
 *                                                                            *
 *    http://www.apache.org/licenses/LICENSE-2.0                              *
 *                                                                            *
 *  Unless required by applicable law or agreed to in writing, software       *
 *  distributed under the License is distributed on an "AS IS" BASIS,         *
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  *
 *  See the License for the specific language governing permissions and       *
 *  limitations under the License.                                            *
 ******************************************************************************/

package com.oltpbenchmark.benchmarks.tpch.queries;

import com.oltpbenchmark.api.SQLStmt;

public class Q12 extends GenericQuery {

    public final SQLStmt query_stmt = new SQLStmt(
              "select "
            +     "l_shipmode, "
            +     "sum(case "
            +         "when o_orderpriority = '1-URGENT' "
            +             "or o_orderpriority = '2-HIGH' "
            +             "then 1 "
            +         "else 0 "
            +     "end) as high_line_count, "
            +     "sum(case "
            +         "when o_orderpriority <> '1-URGENT' "
            +             "and o_orderpriority <> '2-HIGH' "
            +             "then 1 "
            +         "else 0 "
            +     "end) as low_line_count "
            + "from "
            +     "orders, "
            +     "lineitem "
            + "where "
            +     "o_orderkey = l_orderkey "
            +     "and l_shipmode in ('AIR', 'REG AIR') "
            +     "and l_commitdate < l_receiptdate "
            +     "and l_shipdate < l_commitdate "
            +     "and l_receiptdate >= to_date ('1997-01-01', 'YYYY-MM-DD') "
            +     "and l_receiptdate < add_years (to_date ('1997-01-01', 'YYYY-MM-DD'), 1) "
            + "group by "
            +     "l_shipmode "
            + "order by "
            +     "l_shipmode"
        );

    protected SQLStmt get_query() {
        return query_stmt;
    }
}