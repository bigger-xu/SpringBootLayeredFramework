package com.efreight.common.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

/**
 * @author 张永伟
 * @since 2023/8/23
 */
public class FeeFormulaEnum {
    
    public enum RoundType {
        /** 向上取整 */
        UP,
        /** 向下取整 */
        DOWN,
        /** 四舍五入 */
        ROUND,
        ;
        
        RoundType() {
        }
        
        public static RoundType getRoundTypeByName(String name) {
            for (RoundType value : RoundType.values()) {
                if (value.name().equals(name)) {
                    return value;
                }
            }
            return UP;
        }
        
    }
    
    
    @Getter
    public enum Scale {
        INTEGER("INTEGER", "1"),
        ROUND_ONE("ROUND_ONE", "0.1"),
        ROUND_TWO("ROUND_TWO", "0.01"),
        ROUND_FIVE("ROUND_FIVE", "0.5"),
        ;
        
        @EnumValue
        private final String key;
        
        private final String value;
        
        Scale(String key, String value) {
            this.key = key;
            this.value = value;
        }
        
        public static Scale getScaleByName(String name) {
            for (Scale value : Scale.values()) {
                if (value.name().equals(name)) {
                    return value;
                }
            }
            return INTEGER;
        }
    }
}
