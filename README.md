## SSAFY 과제

- 소스안에 주석 소요시간, 메모리사용량

- SWEA

  - Solution*문제번호*문제이름\_이광우.java

- BOJ/JO
  - Main*문제번호*문제이름\_이광우.java

## next()와 nextLine()의 차이점

    문자열입력후 엔터반복 -> abcdefg[\n]
                            hijklmn[\n]
    sc.next() : abcdefg[\n]
    sc.next() : hijklmn[\n]

    sc.nextLine() : abcdefg
    sc.nextLine() : [\n]
    sc.nextLine() : hijklmn
    sc.nextLine() : [\n]

## Map에 객체를 키로 사용하기

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

## Map Foreach

```java
for(Class c : Map.KeySet()){

}
```
