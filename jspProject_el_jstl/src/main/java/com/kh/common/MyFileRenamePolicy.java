package com.kh.common;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyFileRenamePolicy implements FileRenamePolicy{
	//implements => 상속(부모 객체는 선언만 하며 정의(내용)은 자식에서 오버라이딩 (재정의) 해서 사용해야함)
	//부모객체 => FileRenamePolicy , 자식 객체 => MyFileRenamePolicy
	
	
	//원본파일 전달 받아서 파일명 수정작업 후 수정된 파일을 반환시켜주는 메소드
	@Override
	public File rename(File originFile) { //originFile => 이름 설정 가능, 자료형 => File : 이건 바꿀수 없다.

		//원본파일명("aaa.jpg")
		String originName = originFile.getName(); //원본 파일 명을 originName에 넣는다.
		
		//=>수정파일명("2023081712253012345.jpg")
		//			파일업로드한시간(년월일시분초) + 5자리랜덤값(10000~99999) + 원본파일확장자
		
		//1. 파일업로드 시간(년월일시분초 형태) (String currentTime)
		String currentTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());//"20230817122955"
		
		//2. 5자리 랜덤값(int ranNum) random=>자료형이 double이기 때문에 형변환 필요
		int ranNum = (int)(Math.random() * 90000 + 10000);//25312

		//3. 원본파일 확장자 (String ext)
		//String ext = originName.substring(마지막.의 인덱스)
		String ext = originName.substring(originName.lastIndexOf("."));
		
		//합치기
		String changeName = currentTime + ranNum + ext;
		
		
		return new File(originFile.getParent(),changeName); //원본의 디렉토리를 알아낸 후 변경된 이름으로 저장하는 메소드
	}

	
	
	
	
}
