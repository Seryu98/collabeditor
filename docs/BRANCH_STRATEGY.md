# 🌿 Branch Strategy

## 📌 기본 브랜치
- main: 배포 및 최종 코드

---

## 📌 작업 방식
1. feature 또는 fix 브랜치에서 작업 시작
2. 기능 단위로 commit 진행
3. 작업 완료 후 Pull Request 생성
4. 코드 리뷰 후 main 브랜치에 merge

---

## 📌 브랜치 이름 규칙
- feature/기능명 (예: feature/login, feature/websocket-sync)
- fix/버그명 (예: fix/login-error, fix/message-bug)

---

## 📌 참고 사항
- 모든 변경 사항은 Pull Request를 통해 main에 반영
- main 브랜치에는 직접 commit 하지 않음
- 작은 기능 단위로 브랜치를 나누어 작업