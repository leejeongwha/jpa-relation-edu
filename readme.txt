환경설정 
1. JPA, WEB, H2, LOMBOK dependency 설정

2. 서버를 띄운 후 아래와 같이 console 화면이 제대로 뜨는지 확인 
http://localhost:8080/h2-console/login.jsp



내용
1. mappedBy가 없을 경우 매핑 테이블이 자동으로 생성됨 왜?
- 뭘로 매핑할지 모르므로

2. jpa에서는 setter를 통해서 데이터를 set하지 않음
- jpa에서는 final keyword를 지원하지 않음 

3. 일대다 양방향 매핑 (insertable, updatable false로 설정 )
연관관계의 주인은 테이블에서 FK를 가지고 있는 쪽이다.
주인이 아닌 쪽은 읽기만 가능하다.

4. 일대다 양방향 매핑보다 연관관계의 주인이 다 쪽에서 가지는 다대일 양방향 매핑을 권장한다.
- mappedBy를 통해서 설정하는 것이 더 유리 
- member 쪽에서 insert 될 때 team을 모르기 때문에 update쿼리가 추가로 수행된다. 

5. 일대일 양방향 관계는 연관관계의 주인이 아닌 쪽에서 @OneToOne(mappedBy=) 설정한다.

6. 다대다 관계 매핑은 @JoinTable로 연결 테이블을 매핑 

7. 식별관계보다 비식별관계를 추천(2개의 차이점을 잘 아는 것이 중요)

8. @Embedde 의 경우 테이블 안에 컬럼으로 해당 필드가 생성됨 
- 불변객체이므로 외부에서 수정하지 못하도록 구성해야 함
- 테이블을 분리시킬 수 도 있으나 Join이 발생하지 않게 하려고 할때 사용
- 잘 사용되지 않을 듯??

9. @ElementCollection(컬렉션 값 타입)
- 컬렉션이 변경될 경우(1개만 추가되더라도) 모두 삭제한 후에 새로 등록한다.(성능 안좋음)
- 이것보다는 @OneToMany를 사용하자!!!

10. 불변객체의 경우 Entity와 생명주기를 같이한다.

11. cascade
- 영속상태에 대한 관리기능 제공
- cascadeType.REMOVE(조심!), cascadeType.PERSIST
- 양방향 연관관계는 한쪽에서만 관리하는 것이 좋다.
- orphanRemoval = true 옵션은 @OneToOne 과 @OneToMany 에 제공

13. Fetch 전략 
- @ManyToOne, @OneToOne 의 디폴트 Fetch 전략이 Eager 이다. 
- @OneToMany, @ManyToMany 의 디폴트 Fetch 전략은 Lazy 이다.
- EAGER Fetch 전략보다 LAZY Fetch전략을 사용하는 편이 더 좋음 

14. 트러블 슈팅 
- 양방향 관계를 왠만하면 만들지 말자. 단방향 관계로도 충분히 해결 가능함.(순환참조 오류 등이 발생할 수 있으므로)
- 무심코 CascadeType.ALL을 하지 말자
- proxy객체에 대한 instanceof 사용 시 주의 
- equals 및 hashCode 오버라이딩은 하는 편이 좋다.
- equals 및 hashCode는 get메서드가 아닌 내부변수에 직접 접근하므로 getter를 통해서 호출하도록 해야 함.
(proxy일때 문제가 됨)
- Entity안에 컬렉션에 대한 EAGER Fetch가 2개 이상이 될 경우 문제가 될수 있으므로 왠만하면 LAZY를 써라.\

15. 성능 최적화
- optinoal = false를 사용한 InnerJoin
- Lob타입은 엔티티로 분리한 후 @OneToOne 관계 설정 하는 편이 좋음
- N+1 로딩 문제 (Team - Member - Locker)
  - Fetch Join으로 해결 
  - @BatchSize를 사용 (하이버네이트에서 제공)
  - Fetch(FetchMode.SUBSELECT) 사용
  
16. 연관관계 모델링 전략
 


