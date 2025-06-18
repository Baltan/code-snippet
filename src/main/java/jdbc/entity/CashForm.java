package jdbc.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class CashForm {

    private static final long serialVersionUID = 1L;

    
    private Long id;

    
    private String policyCode;

    
    private Long areaCode;

    
    private Long streetCode;

    
    private Long companyId;

    
    private Long companyUserId;

    
    private String creditCode;

    
    private String companyName;

    
    private String cashNo;

    
    private Integer formState;

    
    private String contactName;

    
    private String contactPhone;

    
    private Integer bankAccountType;

    
    private Integer payMoney;

    
    private Integer payChannel;

    
    private Integer payWay;

    
    private String companyBankAccountName;

    
    private String personalName;

    
    private String personalPhone;

    
    private Integer personalCardType;

    
    private String personalIdCard;

    
    private String companyPublicBank;

    
    private String companyPublicBankBranch;

    
    private String companyPublicBankBranchCode;

    
    private String companyPublicBankBranchProvince;

    
    private String companyPublicBankBranchCity;

    
    private String companyPublicCard;

    
    private String cardUserName;

    
    private Integer cardUserCardType;

    
    private String cardUserIdCard;

    
    private String cardUserPhone;

    
    private String personalBankName;

    
    private String personalBankCode;

    
    private String personalBankCard;

    
    private Date paySuccessTime;

    
    private Integer dataFlag;

    
    private Date createTime;

    
    private Date updateTime;

    
    private Long createUserId;

    
    private Long updateUserId;

    
    private String versionNo;

    
    private Long whiteListId;

    private String payFailReason;
}
