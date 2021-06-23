package com.kds.cop.klap.member;

import org.springframework.data.jpa.repository.JpaRepository;
import com.kds.cop.klap.member.Member;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
/** JPA에서 단순히 Repository 인터페이스를 생성한후
 * JpaRepository<Entity, 기본키타입> 을 상속받으면 기본적인
 * CRUD가 자동생성된다.
 *
 * JPA처리를 담당하는 Repository는 기본적으로 4가지
 * (T:Entity의 타입클래스, ID:PK값의 타입)
 * 1) Repository<T, ID>
 * 2) CrudRepository<T, ID>
 * 3) PagingAndSortingRepository<T, ID>
 * 4) JpaRepository<T,ID>
 */
public interface IMemberRepository extends JpaRepository<Member, Long> {
    // 비워있어도 잘 작동함.
    // Long ex) int => Integer 같이 primitive 형식 사용못함.
    // findBy뒤에 컬럼명을 붙여주면 이를 이용한 검색이 가능.
    public List<Member> findById(String id);

    public List<Member> findByIdName(String name);
    // Like 검색
    public List<Member> findByIdNameLike(String keyword);

    /** EX)
     * AND => findByLastnameAndFirstname ( where x.lastname = :var1 and x.firstname = :var2)
     * OR => findByLastnameOrFirstname  ( where x.lastname = :var1 or x.firstname = :var2)
     * Is, Equals => findByName,findByNameIs,findByNameEquals (where x.name = :var)
     * Between => findBySalBetween (where x.sal between :var1 and :var2)
     */



//    public Member findByMemberName(String memberName);
}

