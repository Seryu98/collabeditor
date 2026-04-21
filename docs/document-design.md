
# 📄 Document Entity 설계 설명

## 📌 개요
Document 엔티티는 실시간 협업 텍스트 에디터에서  
"문서의 상태를 저장하는 핵심 도메인 객체"입니다.

단순한 DB 테이블 매핑이 아니라  
문서의 생성, 수정, 상태 변경까지 포함하는 도메인 모델입니다.

---

## 📌 테이블 매핑 구조

- 테이블명: documents
- 역할: 문서 데이터 영속화

---

## 📌 필드 설계 이유

### 🆔 id
- 문서를 구분하는 유일한 식별자
- WebSocket에서도 documentId로 room 분리

---

### 📝 title
- 문서 목록 UI에서 표시용
- 최대 100자로 제한하여 비정상 데이터 방지

---

### 📄 content
- 실제 문서 내용 저장
- TEXT 타입 사용 이유:
  - 긴 문서 지원
  - VARCHAR 제한 회피

---

### ⏱ createdAt / updatedAt
- 문서 생성/수정 이력 관리
- 최신 문서 정렬 및 추적 용도

---

## 📌 자동 시간 관리 (@PrePersist / @PreUpdate)

### @PrePersist
- 최초 저장 시 자동 실행
- createdAt / updatedAt 초기화

### @PreUpdate
- 수정 시 자동 실행
- updatedAt 갱신

👉 이유:
서비스 코드에서 시간 처리 실수를 방지하기 위해 JPA 엔티티 라이프사이클(@PrePersist / @PreUpdate)에서 자동 처리

---

## 📌 캡슐화 설계 (setter 미사용)

### ❌ setter 방식 금지
- 외부에서 무분별한 값 변경 가능

### ✔ update 메서드 사용
```java
updateContent()
updateTitle()
```

---

## 📌 도메인 규칙 (운영 기준)

- Document는 항상 `title`, `content`를 가진다. (`null` 허용하지 않음)
- 생성 시점에는 `createdAt`, `updatedAt`이 동일해야 한다.
- 수정 시점에는 `updatedAt`만 변경되어야 한다.
- 외부 계층(Controller)에서 엔티티 필드를 직접 수정하지 않는다.

---

## 📌 검증/예외 처리 정책

### 입력 검증
- title: 공백 제외 1자 이상, 최대 100자
- content: `null` 금지, 빈 문자열 허용 여부는 서비스 정책으로 관리

### 예외 처리
- 존재하지 않는 문서 조회/저장 요청 시 `404 Not Found`
- 검증 실패 시 `400 Bad Request`
- 예외 응답은 공통 포맷(`ApiResponse`)으로 반환

---

## 📌 서비스 레이어 책임 범위

- `DocumentService`는 문서 생성/조회/저장의 비즈니스 흐름을 담당한다.
- `DocumentRepository`는 DB 접근만 담당한다.
- 타임스탬프 관리 로직은 엔티티 라이프사이클에 위임한다.

---

## 📌 확장 계획 (미적용)

### 버전 관리
- 현재: 최신 content 단일 저장
- 확장: `DocumentRevision` 테이블 추가로 히스토리 관리

### 검색 기능
- 현재: ID 기반 단건 조회
- 확장: title 키워드 검색, updatedAt 정렬

### 협업 연동
- 현재: WebSocket 편집 상태는 메모리에서만 관리
- 확장: 저장 시점/자동 저장 시점 정책을 명확히 분리

---

## 📌 변경 이력

- 2026-04-21: 초안 작성 (엔티티 필드, 시간 자동 처리, 캡슐화 정책)
- 2026-04-21: 시간 처리 설명을 DB 레벨 -> JPA 라이프사이클 기준으로 정정