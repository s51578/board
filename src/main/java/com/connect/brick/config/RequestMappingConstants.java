package com.connect.brick.config;

public class RequestMappingConstants {

	//th:href="${T(com.connect.brick.config.RequestMappingConstants).ROLE_PREFIX}"
	
	//_Main
	public static final String _INDEX_URL = "/";
	public static final String _MAIN_URL = "/main";
	//th:href="${T(com.connect.brick.config.RequestMappingConstants)._MAIN_URL}"
	
	//회원-접근성
	public static final String _ACCESS_LOGIN_ERROR = "/access/loginError";
	public static final String _ACCESS_LOGIN = "/access/login";
	public static final String _ACCESS_VALID = "/access/valid";
	
	//에러
	public static final String _ERROR_PATH = "/error";
	public static final String _ERROR_LOAD = "/error/load/";
	public static final String _ERROR_PATH_CODE = "/error/";
	
	//서비스 관련 문서
	public static final String _POLICY_PRIVACY = "/service/policy/privacy";
	public static final String _POLICY_CONSENT = "/service/policy/consent";
	public static final String _POLICY_MARKETING = "/service/policy/marketing";
	public static final String _POLICY_PROVIDING = "/service/policy/providing";
	public static final String _POLICY_PARTNER = "/service/policy/partner";
	public static final String _POLICY_TERMS_OF_USE = "/service/policy/termsofuse";
	
	
	
	//인코딩
	public static final String _GET_ENCODE = "/view/get/encode";
	public static final String _GO_ENCODE = "/view/";
	
	//오픈 상품 뷰
	public static final String _DP_DETAIL = "/service/details/contents";
	//th:href="${T(com.connect.brick.config.RequestMappingConstants)._DP_DETAIL}"
	public static final String _DP_DETAIL_PRODUCT = "/service/details/product";
	
	//뷰어
	public static final String _DETAILS_STORE_MAIN = "/service/details/store/main";
	public static final String _DETAILS_VIEWED_PRODUCT = "/service/details/viewedProduct";
	
	//견적 관련
	public static final String _ESTIMATE_CALC = "/service/estimate/calc";
	public static final String _ESTIMATE_SEND_EMAIL = "/service/estimate/send/email";
	
	public static final String _ESTIMATE_DOC_LOGIN = "/service/estimate/doc/no/";
	public static final String _ESTIMATE_DOC = "/service/estimate/doc";
	
	
	//가이드
	public static final String _GUIDE_CHECKLIST = "/service/guide/checklist";
	public static final String _GUIDE_BOOK_1 = "/service/guide/guidebook_1";
	public static final String _GUIDE_BOOK_2 = "/service/guide/guidebook_2";
	public static final String _GUIDE_BOOK_3 = "/service/guide/guidebook_3";
	public static final String _GUIDE_BOOK_4 = "/service/guide/guidebook_4";
	public static final String _GUIDE_BOOK_5 = "/service/guide/guidebook_5";
	public static final String _GUIDE_BOOK_6 = "/service/guide/guidebook_6";
	public static final String _GUIDE_BOOK_7 = "/service/guide/guidebook_7";
	public static final String _GUIDE_CONSTRUCT = "/service/guide/construct";
	
	//구글폼, 아파트 시공 견적 검색 및 결과
	public static final String _GOOGLE_FORM = "/service/form";
	public static final String _GOOGLE_FORM_REG = "/service/form/reg";
	public static final String _GOOGLE_FORM_COMPLETE = "/service/form/complete";
	public static final String _QUICK_SEARCH_ESTIMATE_MAIN = "/service/form/quickSearch/main";
	public static final String _QUICK_SEARCH_ESTIMATE_SIDO = "/service/form/quickSearch/region";
	public static final String _QUICK_SEARCH_ESTIMATE_APT = "/service/form/quickSearch/apt";
	public static final String _QUICK_SEARCH_ESTIMATE_APT_TYPE = "/service/form/quickSearch/apt/type";
	public static final String _QUICK_SEARCH_ESTIMATE_RESULT = "/service/form/quickSearchResult";
	public static final String _QUICK_SEARCH_ESTIMATE_RESULT_DETAIL = "/service/form/quickSearchResultDetail";
	public static final String _QUICK_SEARCH_ESTIMATE_TILE = "/service/form/quickSearch/tile";
	
	//문의
	public static final String _QNA_MAIN = "/service/qna/main";
	public static final String _QNA_FORM = "/service/qna/form";
	public static final String _QNA_REG = "/service/qna/reg";
	public static final String _QNA_DETAIL = "/service/qna/detail";
	public static final String _QNA_PASSWORD = "/service/qna/password";
	public static final String _QNA_PWDCHECK = "/service/qna/pwdcheck";
	public static final String _QNA_ERROR = "/service/qna/error";
	
	//시공사례 뷰
	public static final String _CONSREVIEW_DETAIL = "/service/consreview/detail";
	
	//AJAX //Estimate
	public static final String _AJAX_CONTACT = "/service/ajax/contact";
	public static final String _AJAX_ESTIMATE_MIN = "/service/ajax/estimate/min";
	public static final String _AJAX_ESTIMATE = "/service/ajax/estimate";
	
	//master
	//Dropzone
	public static final String _DROPZONE_IMAGE_UPLOAD = "/dropzone/image/upload";
	public static final String _DROPZONE_IMAGE_PRELOAD = "/dropzone/image/preload";
	public static final String _DROPZONE_IMAGE_REMOVE = "/dropzone/image/remove";
	
	//AJAX //Image
	public static final String _AJAX_IMAGE_UPLOAD = "/service/ajax/image/upload";
	public static final String _AJAX_IMAGE_REMOVE = "/service/ajax/image/remove";
		
	//AJAX
	public static final String _AJAX_UPDATE_POPULAR = "/ajax/update/popular";
	
	//manage
	public static final String _MASTER_POPULAR_LIST = "/master/contents/popular/list";
	public static final String _MASTER_POPULAR_ADD = "/master/contents/popular/add";
	public static final String _MASTER_POPULAR_DEL = "/master/contents/popular/delete";
	
	public static final String _MASTER_FORM_EMAIL_LIST = "/master/form/email/list";
	public static final String _MASTER_FORM_EMAIL_DETAIL = "/master/form/email/detail";
	
	
	public static final String _MASTER_MAIN = "/master/main";
	
	public static final String _MASTER_MATERIAL_LIST = "/master/material/list";
	public static final String _MASTER_MATERIAL_DETAIL = "/master/material/detail";
	public static final String _MASTER_MATERIAL_CREATE = "/master/material/create";
	public static final String _MASTER_MATERIAL_REG = "/master/material/reg";
	public static final String _MASTER_MATERIAL_UPDATE = "/master/material/update";
	public static final String _MASTER_MATERIAL_MOD = "/master/material/mod";
	public static final String _MASTER_MATERIAL_DEL = "/master/material/delete";
	public static final String _MASTER_MATERIAL_SUB_LIST = "/master/material/sub/list";
	public static final String _MASTER_MATERIAL_SUB_DETAIL = "/master/material/sub/detail";
	public static final String _MASTER_MATERIAL_SUB_CREATE = "/master/material/sub/create";
	public static final String _MASTER_MATERIAL_SUB_REG = "/master/material/sub/reg";
	public static final String _MASTER_MATERIAL_SUB_UPDATE = "/master/material/sub/update";
	public static final String _MASTER_MATERIAL_SUB_MOD = "/master/material/sub/mod";
	public static final String _MASTER_MATERIAL_SUB_DEL = "/master/material/sub/delete";
	
	
	public static final String _MASTER_ORDER_CONTACT_LIST = "/master/order/contact/list";
	public static final String _MASTER_ORDER_CONTACT_DETAIL = "/master/order/contact/detail";
	
	//order
	public static final String _MASTER_ORDER_LIST = "/master/order/list";
	public static final String _MASTER_ORDER_DETAIL = "/master/order/detail";
	public static final String _MASTER_ORDER_CREATE = "/master/order/create";
	public static final String _MASTER_ORDER_REG = "/master/order/reg";
	public static final String _MASTER_ORDER_UPDATE = "/master/order/update";
	public static final String _MASTER_ORDER_MOD = "/master/order/mod";
	public static final String _MASTER_ORDER_DEL = "/master/order/delete";
	
	//estimate
	public static final String _MASTER_ESTIMATE_LIST = "/master/estimate/list";
	public static final String _MASTER_ESTIMATE_DETAIL = "/master/estimate/detail";
	public static final String _MASTER_ESTIMATE_CREATE = "/master/estimate/create";
	public static final String _MASTER_ESTIMATE_REG = "/master/estimate/reg";
	public static final String _MASTER_ESTIMATE_CONFIRM = "/master/estimate/confirm";
	public static final String _MASTER_ESTIMATE_UPDATE = "/master/estimate/update";
	public static final String _MASTER_ESTIMATE_MOD = "/master/estimate/mod";
	public static final String _MASTER_ESTIMATE_DEL = "/master/estimate/delete";
	public static final String _MASTER_ESTIMATE_STATE = "/master/estimate/state";
	
	public static final String _MASTER_QNA_MAIN = "/master/qna/main";
	public static final String _MASTER_QNA_FORM = "/master/qna/form";
	public static final String _MASTER_QNA_REG = "/master/qna/reg";
	public static final String _MASTER_QNA_DETAIL = "/master/qna/detail";
	public static final String _MASTER_QNA_DEL = "/master/qna/delete";
	
	public static final String _MASTER_QNA_REPLY_FORM = "/master/qna/replyform";
	public static final String _MASTER_QNA_REPLY_REG = "/master/qna/replyreg";
	public static final String _MASTER_QNA_REPLY_UPDATE = "/master/qna/update";
	public static final String _MASTER_QNA_REPLY_MOD = "/master/qna/mod";
	
	//review
	public static final String _MASTER_CONS_REVIEW_LIST = "/master/cons/review/list";
	public static final String _MASTER_CONS_REVIEW_DETAIL = "/master/cons/review/detail";
	public static final String _MASTER_CONS_REVIEW_CREATE = "/master/cons/review/create";
	public static final String _MASTER_CONS_REVIEW_REG = "/master/cons/review/reg";
	public static final String _MASTER_CONS_REVIEW_UPDATE = "/master/cons/review/update";
	public static final String _MASTER_CONS_REVIEW_MOD = "/master/cons/review/mod";
	public static final String _MASTER_CONS_REVIEW_DEL = "/master/cons/review/delete";
	
}
