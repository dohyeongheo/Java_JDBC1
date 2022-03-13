-- sql파일 생성 new - other - sql 검색
-- window - show view - other - data검색
-- database connections 우클릭 new - oracle 검색 - new driver definition

-- [name탭] oracle 버전 확인후 맞는 드라이버 선택 -> 

-- [jar리스트탭]
-- 1. ojdb14.jar, remove jar/zip 클릭
-- 2. add jar/zip 클릭 ojdbc6 선택, ok 클릭

-- [properties - general 탭]
-- service name : xe
-- host : localhost (ip주소 입력시 공유가능)
-- user name : hr
-- password : hr 
-- save password 체크
-- 위 내용으로 수정

-- finish

-- connection profile
-- type : oracle 11, name : New Oracle, Database : xe 선택

-- 명령문 오른쪽 클릭 후 execute selected text
select * from MEMBERINFO;

-- 실행 단축키 sql문 드래그 후 alt x
insert into MEMBERINFO values('pbk', '0000', '박병관', 22);

create 



