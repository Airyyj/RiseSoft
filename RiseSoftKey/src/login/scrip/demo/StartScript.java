package login.scrip.demo;

public class StartScript {
	public static String Keywords = null;
	public static String r;
	public static boolean bResult;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// 读取 excel 获取 需要自行的TCID 和sheetname

		ExcelHandle.setExcelFile(Parameter.excelFile + Parameter.excelName);
		bResult = true;
		// 循环读取suitSheet里面的值，找出运行的场景
		for (int j = 1; j <= ExcelHandle.getLastRowNums(Parameter.suitSheet); j++) {

			String Runmode = ExcelHandle.getCellDate(j, Parameter.suitRunmode,
					Parameter.suitSheet);
			String suitTCId = ExcelHandle.getCellDate(j, Parameter.suitTCId,
					Parameter.suitSheet);
			String suitSheetName = ExcelHandle.getCellDate(j,
					Parameter.suitSheetName, Parameter.suitSheet);
			int sRowNum;

			if (Runmode.equals("Y")) {
				// 根据suitTCId在loginSheet中循环查找相对应的执行用例
				for (sRowNum = 1; sRowNum <= ExcelHandle
						.getLastRowNums(suitSheetName); sRowNum++) {
					String loginTCID = ExcelHandle.getCellDate(sRowNum,
							Parameter.TCID, suitSheetName);
					String loginCaseId = ExcelHandle.getCellDate(sRowNum,
							Parameter.CaseId, suitSheetName);
					String loginRunmode = ExcelHandle.getCellDate(sRowNum,
							Parameter.Runmode, suitSheetName);
					String loginName = ExcelHandle.getCellDate(sRowNum,
							Parameter.UserName, suitSheetName);
					String loginPassWord = ExcelHandle.getCellDate(sRowNum,
							Parameter.PassWord, suitSheetName);
					

					if (loginTCID.trim().equals(suitTCId)
							&& loginRunmode.equals("Y")) {
						System.out.println(loginCaseId);
						// 根据 caseid 在stepsheet 页 循环查找对应的用例
						for (int i = 1; i < ExcelHandle
								.getLastRowNums(Parameter.caseStep); i++) {
							String stepCaseId = ExcelHandle.getCellDate(i,
									Parameter.stepCaseId, Parameter.caseStep);
							if (stepCaseId.equals(loginCaseId)) {
								Keywords = ExcelHandle.getCellDate(i,
										Parameter.Keyword, Parameter.caseStep);
								switch (Keywords) {
								case "openBrowser":
									KeyWords.openBrowser();
									break;
								case "getUrl":
									KeyWords.getUrl();
									break;
								case "inputUserName":
									KeyWords.inputUserName(loginName);
									break;
								case "inPutPassWord":
									KeyWords.inPutPassWord(loginPassWord);
									break;
								case "clickButton":
									KeyWords.clickButton();
									break;
								case "verifyLogin":
									bResult = KeyWords.verifyLogin();
									break;
								case "clossBrowser":
									KeyWords.clossBrowser();
									break;

								default:
									break;
								}

							}
							if (bResult == true) {
								ExcelHandle.setCellData(Parameter.pass, sRowNum, Parameter.Result, Parameter.excelFile + Parameter.excelName, suitSheetName);
							}
							else {
								ExcelHandle.setCellData(Parameter.fail, sRowNum, Parameter.Result, Parameter.excelFile + Parameter.excelName, suitSheetName);
							}
						}

					}
					
					

				}
			}
		}
	}
}
