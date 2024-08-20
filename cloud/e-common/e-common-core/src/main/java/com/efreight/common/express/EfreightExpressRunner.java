package com.efreight.common.express;

import com.efreight.common.express.operator.AvgOperator;
import com.efreight.common.express.operator.DateDiffOperator;
import com.efreight.common.express.operator.DateOperator;
import com.efreight.common.express.operator.LengthStringOperator;
import com.efreight.common.express.operator.OperatorDispatchNumerate;
import com.efreight.common.express.operator.OperatorMiscNumerate;
import com.efreight.common.express.operator.OperatorNotLike;
import com.efreight.common.express.operator.SubStringOperator;
import com.efreight.common.express.operator.SumOperator;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import com.ql.util.express.instruction.op.OperatorMinMax;
import com.ql.util.express.instruction.op.OperatorRound;

/**
 * 规则引擎自定义入口
 *
 * @author 张永伟
 * @since 2023/8/15
 */
public class EfreightExpressRunner extends ExpressRunner {
    
    public EfreightExpressRunner() throws Exception {
        super();
        addFunction();
    }
    
    /**
     * @param isPrecise 是否需要高精度计算支持
     * @param isTrace   是否跟踪执行指令的过程
     */
    public EfreightExpressRunner(boolean isPrecise, boolean isTrace) throws Exception {
        super(isPrecise, isTrace);
        addFunction();
    }
    
    private void addFunction() throws Exception {
        super.addFunction("SUM", new SumOperator());
        super.addFunction("VERAGE", new AvgOperator());
        super.addFunction("DATEDIF", new DateDiffOperator());
        super.addFunction("DATE", new DateOperator("DATE"));
        super.addFunction("NOW", new DateOperator("DATETIME"));
        super.addFunction("YEAR", new DateOperator("YEAR"));
        super.addFunction("MONTH", new DateOperator("MONTH"));
        super.addFunction("DAY", new DateOperator("DAY"));
        super.addFunction("WEEKDAY", new DateOperator("WEEK"));
        super.addFunction("杂费单价", new OperatorMiscNumerate("PRICE"));
        super.addFunction("杂费数量", new OperatorMiscNumerate("NUM"));
        super.addFunction("杂费金额", new OperatorMiscNumerate("AMOUNT"));
        super.addFunction("派车单单价", new OperatorDispatchNumerate("PRICE"));
        super.addFunction("派车单金额", new OperatorDispatchNumerate("AMOUNT"));
        super.addFunction("派车单毛重", new OperatorDispatchNumerate("WEIGHT"));
        super.addFunction("派车单车队名", new OperatorDispatchNumerate("CAR_NAME"));
        super.addFunction("派车单车队类型", new OperatorDispatchNumerate("CAR_TYPE"));
        this.addFunction("MAX", new OperatorMinMax("max"));
        this.addFunction("MIN", new OperatorMinMax("min"));
        this.addFunction("LEFT", new SubStringOperator("LEFT"));
        this.addFunction("RIGHT", new SubStringOperator("RIGHT"));
        this.addFunction("ROUND", new OperatorRound("round"));
        this.addFunction("LEN", new LengthStringOperator());
        super.addOperatorWithAlias("IF", "if", null);
        super.addOperatorWithAlias("ELSE", "else", null);
        super.addOperatorWithAlias("RETURN", "return", null);
        super.addOperatorWithAlias("LK", "like", null);
        super.addOperator("notlike", new OperatorNotLike("notlike"));
        super.addOperator("NLK", new OperatorNotLike("notlike"));
    }
    
    /**
     * QL执行器
     *
     * @param formula        执行脚本
     * @param expressContext 上下文参数
     * @return Object
     * @since 2023/9/19
     */
    public static Object efExecute(String formula, DefaultContext<String, Object> expressContext) throws Exception {
        EfreightExpressRunner runner = new EfreightExpressRunner(true, false);
        return runner.execute(formula, expressContext, null, false, false);
    }
}
