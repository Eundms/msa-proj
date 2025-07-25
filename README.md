# msa-proj
## common-lib
- DTO, 공통 유틸 라이브러리 (Jar 배포)

## batch-service
- spring batch 

## 외부 API 호출 서비스 
- Feign Client 사용 (Retry, Circuit Breaker)
- Eureka Client로 등록

## 내부 API 호출 서비스 
- DB 접근 + 내부 비즈니스 API
- MyBatis, MariaDB

## API Gateway (Spring Cloud Gateway)
- 외부 요청 -> 내부 서비스 라우팅

## Service Discovery
- 서비스 등록 / 발견