package com.example.demo.vo;

import lombok.Getter;

public class ResultData<DT> {
	@Getter
	private String resultCode;
	@Getter
	private String msg;
	@Getter
	private DT data1;
	@Getter
	private String data1Name;
	
	@Getter
	private Object data2;	//조회수 정보추가 게시글번호 정보
	@Getter
	private String data2Name;	//조회수 정보추가 ID정보
	
	//좋아요
	@Getter
	private Object data3;
	@Getter
	private String data3Name;
	
	
	public static <DT> ResultData<DT> from(String resultCode, String msg) {
		return from(resultCode, msg, null, null);
	}

	public static <DT> ResultData<DT> from(String resultCode, String msg, String data1Name, DT data1) {
		ResultData<DT> rd = new ResultData<DT>();
		rd.resultCode = resultCode;
		rd.msg = msg;
		rd.data1Name = data1Name;
		rd.data1 = data1;

		return rd;
	}

	public static <DT> ResultData<DT> newData(ResultData rd, String dataName, DT newData) {
		return from(rd.getResultCode(), rd.getMsg(), dataName, newData);
	}

	public boolean isSuccess() {
		return resultCode.startsWith("S-");
	}

	public boolean isFail() {
		return isSuccess() == false;
	}
	
	//조회수 증가 확인정보위함
	//이 정보는 웹의 상세보기의 f12에 네트워크에서 response부분임
	public void setData2(String data2Name, Object data2) {
		this.data2Name = data2Name;
		this.data2 = data2;
	}

	public void setData3(String data3Name, Object data3) {
		this.data3Name = data3Name;
		this.data3 = data3;
	}
	
}
