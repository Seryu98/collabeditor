# 📝 Real-time Collaborative Text Editor

## 📌 프로젝트 소개

WebSocket 기반으로 여러 사용자가 동시에 문서를 편집할 수 있는 실시간 협업 텍스트 에디터 백엔드 프로젝트입니다.

이 프로젝트는 단순 CRUD API가 아닌, **실시간 협업 환경 구현**을 목표로 설계되었습니다.

---

## 🛠 기술 스택

* Java
* Spring Boot
* Spring WebSocket (STOMP)
* JPA (Hibernate)
* MySQL
* Gradle

---

## 🧩 아키텍처 개요

* **HTTP API**

  * 문서 생성 / 조회 / 저장 (DB 영속화)
* **WebSocket**

  * 실시간 텍스트 동기화 (브로드캐스트)
* **In-Memory 상태 관리**

  * 서버 메모리(Map)를 활용한 문서 상태 관리

---

## 📂 프로젝트 구조

```plaintext
com.example.collabeditor
├─ common
│  ├─ config
│  ├─ exception
│  └─ dto
├─ document
│  ├─ controller
│  ├─ service
│  ├─ repository
│  ├─ entity
│  └─ dto
└─ collaboration
   ├─ controller
   ├─ service
   ├─ session
   └─ dto
```

---

## 📌 주요 기능

### 🗂 문서 관리 (HTTP)

* 문서 생성
* 문서 조회
* 문서 저장

### 🔄 실시간 협업 (WebSocket)

* 여러 사용자 동시 접속
* 텍스트 변경사항 실시간 반영
* 동일 문서 사용자에게 즉시 브로드캐스트

---

## 🔄 동작 흐름

### 📍 HTTP 흐름 (문서 관리)

1. 클라이언트 요청
2. Controller → Service → Repository
3. MySQL 저장 및 조회

---

### 📍 WebSocket 흐름 (실시간 편집)

1. 클라이언트 `/topic/docs/{id}` 구독
2. `/app/docs/{id}/edit` 메시지 전송
3. 서버에서 메모리 상태 갱신
4. 모든 구독자에게 브로드캐스트

---

## 📡 API 명세

### 문서 생성

```
POST /api/documents
```

### 문서 조회

```
GET /api/documents/{id}
```

### 문서 저장

```
PUT /api/documents/{id}
```

---

## 💬 WebSocket 메시지 구조

```json
{
  "type": "EDIT",
  "documentId": 1,
  "userId": "user1",
  "content": "변경된 전체 텍스트",
  "timestamp": 1710000000
}
```

---

## 💡 설계 포인트

* Controller / Service / Repository 계층 분리
* HTTP와 WebSocket 역할 분리
* Redis 없이 메모리 기반으로 단순화
* 전체 텍스트 동기화 방식으로 구현 단순화

---

## 🤔 WebSocket을 사용한 이유

HTTP는 요청-응답 구조이기 때문에 실시간 협업에 부적합하다고 판단하여
양방향 통신이 가능한 WebSocket을 사용했습니다.

---

## ⚠️ 한계 및 개선 방향

* 서버 재시작 시 메모리 데이터 유실
* 동시 편집 충돌 처리 미구현 (OT/CRDT 미적용)

### 🔧 향후 개선 계획

* Redis 기반 세션 관리
* OT / CRDT 적용
* 자동 저장 기능 추가
* 사용자 인증 (JWT)

---

## ⚙️ 실행 방법

```bash
./gradlew bootRun
```

Windows:

```bash
gradlew.bat bootRun
```

---

## 📌 Project Rules

- [코딩 규칙](docs/CODE_STYLE.md)
- [커밋 규칙](docs/COMMIT_CONVENTION.md)
- [브랜치 전략](docs/BRANCH_STRATEGY.md)

---

## 🙋‍♂️ 개발자

* GitHub: https://github.com/Seryu98/collabeditor
