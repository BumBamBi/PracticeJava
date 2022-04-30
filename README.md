## SSAFY 과제

- 소스안에 주석 소요시간, 메모리사용량

- SWEA
    - Solution_문제번호_문제이름_이광우.java

- BOJ/JO
    - Main_문제번호_문제이름_이광우.java


##
- map에 객체를 키로 사용하고 싶다면, hashCode와 equals를 재정의 해야함
```java
@Override
public boolean equals(Object o) {
    if (this == o)
        return true;
    if (o == null || getClass() != o.getClass())
        return false;

    Identifier other = (Identifier) o;

    return other.A == A && other.B == B && other.C == C;
}

@Override
public int hashCode() {
    return Objects.hash(A, B, C); // import java.util.Objects; 가 필요함
}
```