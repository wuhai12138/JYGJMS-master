package com.summ;

/**
 * 全局常量
 * 
 * @author johsnon
 *
 */
public final class Consts {

	/**图片上传地址*/
	public static String nannyAvatarUrl = "/web/images/nannyavatar/";
	public static String nannyCertUrl = "/web/images/nannycertificate/";
	public static String goodsBannerUrl = "/web/images/goodsbanner/";
	public static String supplierBusinessLicenseUrl = "/web/images/supplierbusinesslicense/";
	public static String leaguerBusinessLicenseUrl = "/web/images/leaguerbusinesslicense/";
	public static String supplierIdcardUrl = "/web/images/supplieridcard/";
	public static String leaguerIdcardUrl = "/web/images/leagueridcard/";
	public static String appBannerUrl = "/web/images/appbanner/";

	/**图片下载地址*/
	public static String goodsBannerUrlRes = "http://source.jyguanjia.com/images/goodsbanner/";
	public static String nannyAvatarUrlRes = "http://source.jyguanjia.com/images/nannyavatar/";
	public static String nannyCertUrlRes = "http://source.jyguanjia.com/images/nannycertificate/";
	public static String supplierBusinessLicenseUrlRes = "http://source.jyguanjia.com/images/supplierbusinesslicense/";
	public static String leaguerBusinessLicenseUrlRes = "http://source.jyguanjia.com/images/leaguerbusinesslicense/";
	public static String supplierIdcardUrlRes = "http://source.jyguanjia.com/images/supplieridcard/";
	public static String leaguerIdcardUrlRes = "http://source.jyguanjia.com/images/leagueridcard/";
	public static String customerScheduleFeedbackUrlRes = "http://source.jyguanjia.com/images/customerScheduleFeedback/";
	public static String nannyScheduleFeedbackUrlRes = "http://source.jyguanjia.com/images/nannyScheduleFeedback/";
	public static String appBannerUrlRes = "http://source.jyguanjia.com/images/appbanner/";
	public static String nannyDianPingUrlRes = "http://source.jyguanjia.com/images/nannydianpingphoto/";


	public static boolean DEBUG_MODE = false;

	/**充值方式*/
	public static int zhifubao = 44;
	public static int weixin = 45;
	public static int card = 209;
	public static int cash = 127;
	//银行转账
	public static int bank = 243;

	/**百度地图API ak*/
	public static String ak = "GpprDsu0RcV1PAm8qRd1MBTBt2En99Tv";
	/**腾讯地图API key*/
	public static String key = "GK3BZ-CYFKQ-EFB5D-GPCCM-3GJHT-G7BOO";


	/**服务器地址*/
	/**106.14.179.115:8080*/
	public static String serverUrl = "https://three.jyguanjia.com/JYGJMS";
	/**106.14.179.115:8081*/
	public static String testServerUrl = "https://ceshi.jyguanjia.com/JYGJMS";


	/**
	 * 应用ID、商户ID、回头啊函数、商户密钥、微信接口
//	 */
//	public interface ConstWeiXin {
//		String APP_ID = "wx1024cb31dfbae7cc";
//		String MCH_ID = "1488511392";
//		String WEIXIN_CALLBACK_URL = "http://112.74.182.69:8082/customer/WeiXinPayResultNew";
//		String KEY = "beaaa56c4ad04744391d322ff2884db8";
//		String URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
//		String ORDER_QUERY_URL="https://api.mch.weixin.qq.com/pay/downloadbill";
//		String PACKAGE_VALUE = "Sign=WXPay";//packageValue
//	}

	/***存储支付宝账单下载位置*/
	public static String localPath="C:/Users/summ/Desktop/upload/";
	public static String serverPath="";
}
