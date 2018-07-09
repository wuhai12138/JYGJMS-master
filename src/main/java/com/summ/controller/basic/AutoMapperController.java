package com.summ.controller.basic;


import com.summ.mapper.*;
import com.summ.utils.mongodb.MongoDBUtil;
import com.summ.utils.mongodb.model.MongoConfig;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author summ
 * @date 17/12/18
 */
public class AutoMapperController {
    public static MongoDBUtil mongoDBUtil;

    public MongoConfig mongoConfig = new MongoConfig();

    @Autowired
    public JScheduleChangeApplyMapper jScheduleChangeApplyMapper;
    @Autowired
    public JGoodsShopMapper jGoodsShopMapper;
    @Autowired
    public JNannyDianPingMapper jNannyDianPingMapper;
    @Autowired
    public JAppBannerGoodsMapper jAppBannerGoodsMapper;
    @Autowired
    public JAppBannerMapper jAppBannerMapper;
    @Autowired
    public JNannyScheduleFeedbackMapper jNannyScheduleFeedbackMapper;
    @Autowired
    public JCustomerScheduleFeedbackMapper jCustomerScheduleFeedbackMapper;
    @Autowired
    public JCustomerPrepaidMapper jCustomerPrepaidMapper;
    @Autowired
    public JNannyEmergencyContactMapper jNannyEmergencyContactMapper;
    @Autowired
    public JSystemAuthCodeMapper jSystemAuthCodeMapper;
    @Autowired
    public JCustomerAlyStatmentMapper jCustomerAlyStatmentMapper;
    @Autowired
    public JAdminTypeMapper jAdminTypeMapper;
    @Autowired
    public JAccessDictMapper jAccessDictMapper;
    @Autowired
    public JAdminMapper jAdminMapper;
    @Autowired
    public JCouponMapper jCouponMapper;
    @Autowired
    public JCouponListMapper jCouponListMapper;
    @Autowired
    public JAreaMapper jAreaMapper;
    @Autowired
    public JProvinceMapper jProvinceMapper;
    @Autowired
    public JCityMapper jCityMapper;
    @Autowired
    public JCustomerHouseMapper jCustomerHouseMapper;
    @Autowired
    public JCustomerStatmentMapper jCustomerStatmentMapper;
    @Autowired
    public JCustomerMapper jCustomerMapper;
    @Autowired
    public JNannyInfoMapper jNannyInfoMapper;
    @Autowired
    public JNannyTrainMapper jNannyTrainMapper;
    @Autowired
    public JStreetMapper jStreetMapper;
    @Autowired
    public JDictInfoMapper jDictInfoMapper;
    @Autowired
    public JDictTypeMapper jDictTypeMapper;
    @Autowired
    public JAccessMapper jAccessMapper;
    @Autowired
    public JNannyJobLevelMapper jNannyJobLevelMapper;
    @Autowired
    public JNannyReligionMapper jNannyReligionMapper;
    @Autowired
    public JNannyLanguageMapper jNannyLanguageMapper;
    @Autowired
    public JNannySkillMapper jNannySkillMapper;
    @Autowired
    public JNannyCharacterMapper jNannyCharacterMapper;
    @Autowired
    public JNannyCertificateMapper jNannyCertificateMapper;
    @Autowired
    public JShopMapper jShopMapper;
    @Autowired
    public JNannyShopMapper jNannyShopMapper;
    @Autowired
    public JAdminShopMapper jAdminShopMapper;
    @Autowired
    public JCustomerServiceMapper jCustomerServiceMapper;
    @Autowired
    public JOrderContractMapper jOrderContractMapper;
    @Autowired
    public JNannyWorkTimeMapper jNannyWorkTimeMapper;
    @Autowired
    public JNannyWorkTimeDicMapper jNannyWorkTimeDicMapper;
    @Autowired
    public JOrderScheduleMapper jOrderScheduleMapper;
    @Autowired
    public JScheduleNannyMapper jScheduleNannyMapper;
    @Autowired
    public JGoodsContractMapper jGoodsContractMapper;
    @Autowired
    public JOrderTempMapper jOrderTempMapper;
    @Autowired
    public JNannyStatmentMapper jNannyStatmentMapper;
    @Autowired
    public JGoodsCostMapper jGoodsCostMapper;
    @Autowired
    public JSupplierMapper jSupplierMapper;
    @Autowired
    public JSupplierStatmentMapper jSupplierStatmentMapper;
    @Autowired
    public JTeacherMapper jTeacherMapper;
    @Autowired
    public JTeacherShopMapper jTeacherShopMapper;
    @Autowired
    public JTeacherWorkTimeMapper jTeacherWorkTimeMapper;
    @Autowired
    public JReportSalaryMapper jReportSalaryMapper;
    @Autowired
    public JLeaguerMapper jLeaguerMapper;
    @Autowired
    public JLeaguerShopMapper jLeaguerShopMapper;
    @Autowired
    public JLeaguerInvoiceMapper jLeaguerInvoiceMapper;
    @Autowired
    public JSupplierInvoiceMapper jSupplierInvoiceMapper;
    @Autowired
    public JCustomerInvoiceMapper jCustomerInvoiceMapper;
    @Autowired
    public JNannyCashPledgeRecordMapper jNannyCashPledgeRecordMapper;
    @Autowired
    public JNannyCaseMapper jNannyCaseMapper;
    @Autowired
    public JNannyInsuranceMapper jNannyInsuranceMapper;
    @Autowired
    public JCustomerMessageMapper jCustomerMessageMapper;
    @Autowired
    public JCustomerMessageDepartmentMapper jCustomerMessageDepartmentMapper;
    @Autowired
    public JCustomerMessageFollowMapper jCustomerMessageFollowMapper;
    @Autowired
    public JCustomerHousePropertyMapper jCustomerHousePropertyMapper;
    @Autowired
    public JOrderRefundMapper jOrderRefundMapper;
    @Autowired
    public JOrderYearsMapper jOrderYearsMapper;
    @Autowired
    public JOrderMonthMapper jOrderMonthMapper;
    @Autowired
    public JOrderMonthPrepaidMapper jOrderMonthPrepaidMapper;
    @Autowired
    public JCouponGoodsMapper jCouponGoodsMapper;
    @Autowired
    public JWithdrawalMapper jWithdrawalMapper;
    @Autowired
    public JTeacherStatmentMapper jTeacherStatmentMapper;
    @Autowired
    public JNannyBankInfoMapper jNannyBankInfoMapper;
    @Autowired
    public JInvoiceMapper jInvoiceMapper;
    @Autowired
    public JCompanyDataMapper jCompanyDataMapper;
    @Autowired
    public JCouponShopMapper jCouponShopMapper;
    @Autowired
    public JTeacherReVisitMapper jTeacherReVisitMapper;
    @Autowired
    public JCustomerWeiXinStatmentMapper jCustomerWeiXinStatmentMapper;
    @Autowired
    public JNannyJobLevelGoodsMapper jNannyJobLevelGoodsMapper;
}
