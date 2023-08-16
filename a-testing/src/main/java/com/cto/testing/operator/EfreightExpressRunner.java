package com.cto.testing.operator;

import com.ql.util.express.ExpressRunner;
import com.ql.util.express.instruction.op.OperatorMinMax;
import com.ql.util.express.instruction.op.OperatorRound;

/**
 * 规则引擎自定义方法添加
 * @author 张永伟
 * @since 2023/8/15
 */
public class EfreightExpressRunner extends ExpressRunner {

    public EfreightExpressRunner() throws Exception {
        super.addFunction("SUM", new SumOperator());
        super.addFunction("VERAGE", new AvgOperator());
        super.addFunction("DATEDIF", new DateDiffOperator());
        super.addFunction("DATE", new DateOperator("DATE"));
        super.addFunction("NOW", new DateOperator("DATETIME"));
        super.addFunction("YEAR", new DateOperator("YEAR"));
        super.addFunction("MONTH", new DateOperator("MONTH"));
        super.addFunction("DAY", new DateOperator("DAY"));
        super.addFunction("WEEKDAY", new DateOperator("WEEK"));
        this.addFunction("MAX", new OperatorMinMax("max"));
        this.addFunction("MIN", new OperatorMinMax("min"));
        this.addFunction("LEFT", new SubStringOperator("LEFT"));
        this.addFunction("RIGHT", new SubStringOperator("RIGHT"));
        this.addFunction("ROUND", new OperatorRound("round"));
        this.addFunction("LEN", new LengthStringOperator());
        super.addOperatorWithAlias("IF", "if", null);
        super.addOperatorWithAlias("ELSE", "else", null);
        super.addOperatorWithAlias("RETURN", "return", null);
    }
}
