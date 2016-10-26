package login.scrip.demo;

public class Parameter {
	// 定义一些基本属性
//	定义访问地址
	public static  String url="http://192.168.78.168:8000/bpm";
//	定义基本路径
	public static String baseUrl="D:/StudyProgram/eclipseSR/WorkSpace/RiseSoftKey/";
//	Excel的路径和名称
	public static String excelFile=baseUrl+"src/login/testData/";
	public static String excelName="TestCase.xlsx";
//	用例sheet页
	public static String caseSheet="Login";
//	用例场景的sheet页
	public static String suitSheet="Suite";
//  用例步骤sheet 页
	public static String caseStep = "Step";
//	Suite页的设置
	public static int suitTCId=0;
	public static int suitSheetName=2;
	public static int suitRunmode=3;
	public static int suitResult=4;
//  Login页的设置
	public static int TCID = 0;
	public static int CaseId = 1;
	public static int UserName = 3;
	public static int PassWord = 4;
	public static int Runmode = 5;
	public static int Result = 6;
	
// step 页的设置
	public static int stepCaseId = 0;
	public static int TSID = 1;
	public static int Keyword = 3;
	public static int Element = 4;


//	成功和失败
	public static  String fail="FAIL";
	public static  String pass="PASS";

}
