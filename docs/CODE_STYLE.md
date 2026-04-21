# 💡 Code Style Guide

---

## 📌 네이밍 규칙
- 클래스: PascalCase (UserService)
- 메서드: camelCase (getUserInfo)
- 변수: camelCase (userName)

---

## 📌 패키지 구조
- controller: API 요청 처리
- service: 비즈니스 로직
- repository: DB 접근
- entity: DB 모델

---

## 📌 기본 원칙
- 하나의 클래스는 하나의 책임만 가진다 (SRP)
- 비즈니스 로직은 Service에 작성
- Controller는 최대한 얇게 유지한다

---

## 📌 DTO 규칙
- Request / Response 객체를 분리하여 사용한다
- Entity를 직접 API 응답으로 사용하지 않는다

---

## 📌 예외 처리
- GlobalExceptionHandler를 사용하여 예외를 통합 처리한다
- API 응답 형식을 일관되게 유지한다

---

## 📌 테스트 규칙
- Service 단위 테스트를 기준으로 작성한다
- 핵심 비즈니스 로직 검증을 우선한다