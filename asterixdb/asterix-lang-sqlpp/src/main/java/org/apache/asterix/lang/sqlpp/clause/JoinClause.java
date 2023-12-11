/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.asterix.lang.sqlpp.clause;

import org.apache.asterix.common.exceptions.CompilationException;
import org.apache.asterix.lang.common.base.Expression;
import org.apache.asterix.lang.common.expression.VariableExpr;
import org.apache.asterix.lang.common.visitor.base.ILangVisitor;
import org.apache.asterix.lang.sqlpp.optype.JoinType;
import org.apache.asterix.lang.sqlpp.visitor.base.ISqlppVisitor;

public class JoinClause extends AbstractBinaryCorrelateWithConditionClause {

    private final JoinType joinType;

    public JoinClause(JoinType joinType, Expression rightExpr, VariableExpr rightVar, VariableExpr rightPosVar,
            Expression conditionExpr) {
        super(rightExpr, rightVar, rightPosVar, conditionExpr);
        this.joinType = joinType;
    }

    @Override
    public <R, T> R accept(ILangVisitor<R, T> visitor, T arg) throws CompilationException {
        return ((ISqlppVisitor<R, T>) visitor).visit(this, arg);
    }

    @Override
    public ClauseType getClauseType() {
        return ClauseType.JOIN_CLAUSE;
    }

    public JoinType getJoinType() {
        return joinType;
    }

    @Override
    public int hashCode() {
        return 31 * super.hashCode() + joinType.hashCode();
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (!(object instanceof JoinClause)) {
            return false;
        }
        JoinClause target = (JoinClause) object;
        return super.equals(target) && joinType.equals(target.getJoinType());
    }
}
