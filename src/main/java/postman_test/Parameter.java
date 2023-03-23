package postman_test;

import lombok.Data;

/**
 * Description:
 *
 * @author Baltan
 * @date 2018/11/30 14:01
 */
@Data
public class Parameter {
    private String deptId;
    private String areaCode;
    private String serviceCodeId;
    private String applyName;
    private String applyCardType;
    private String applyCardNumber;
    private String busType;
    private String applyFrom;
    private String belongSystem;
    private String appKey;
    private String sign;
    private String requestTime;
    private Additional additional;
}
