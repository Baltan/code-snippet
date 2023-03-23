package jdbc_test.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class ApplyForm {

    private static final long serialVersionUID = 1L;

    
    private Long id;

    
    private Long batchId;

    
    private String batchNo;

    
    private String policyCode;

    
    private Long areaCode;

    
    private Long streetCode;

    
    private String formCode;

    
    private Long cashId;

    
    private String cashNo;

    
    private Integer formState;

    
    private Long contactUserId;

    
    private String contactName;

    
    private Integer contactCardType;

    
    private String contactIdCard;

    
    private String contactPhone;

    
    private Long companyId;

    
    private String creditCode;

    
    private String companyName;

    
    private Date applyTime;

    
    private Long checkUserId;

    
    private Date checkTime;

    
    private String personalName;

    
    private String personalPhone;

    
    private Integer personalCardType;

    
    private String personalIdCard;

    
    private Integer bankAccountType;

    
    private String personalAliipayAccount;

    
    private Integer payMoney;

    
    private Integer payWay;

    
    private Integer payChannel;

    
    private String companyBankAccountName;

    
    private String companyPublicBank;

    
    private String companyPublicBankBranch;

    
    private String companyPublicBankBranchCode;

    
    private String companyPublicBankBranchProvince;

    
    private String companyPublicBankBranchCity;

    
    private String companyPublicCard;

    
    private String cardUserName;

    
    private String cardUserPhone;

    
    private Integer cardUserCardType;

    
    private String cardUserIdCard;

    
    private String personalBankName;

    
    private String personalBankCode;

    
    private String personalBankCard;

    
    private String payFailedReason;

    
    private Date paySuccessTime;

    
    private Date payStartTime;

    
    private Integer appealCount;

    
    private Integer dataFlag;

    
    private Date createTime;

    
    private Date updateTime;

    
    private Long createUserId;

    
    private Long whiteListId;

    
    private Long updateUserId;

    
    private String cashPeriod;

    
    private String versionNo;

    
    private String reasonCodes;
}
