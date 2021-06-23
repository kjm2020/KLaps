package com.kds.cop.klap.member;

import java.io.Serializable;

import javax.persistence.*;

import com.kds.cop.klap.common.CommonEntity;

import lombok.*;


/*
@Table(name="MEMBER"
        ,indexes=@Index(columnList="MEMBER_NAME",unique=false))
@AttributeOverride(name = "id",column = @Column(name = "MEMBER_ID"))
@TableGenerator(name="MEMBER_SEQ_GENERATOR",table="TB_SEQUENCE",
        pkColumnName="SEQ_NAME",pkColumnValue="MEMBER_SEQ",allocationSize=1)
*/

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
/** @Entity가 붙은 클래스는 JPA가 관리하는 클래스이고,
 *  테이블과 매핑할 테이블은 해당 어노테이션을 붙인다.**/
@Entity(name="member")

public class Member extends CommonEntity<Member> { // implements Serializable
//    private static final long serialVersionUID = 1864304860822295551L;

//    @Column(name="MEMBER_NAME",nullable=false)
//    private String memberName;
    @Id
    /** mbrNo 필드는 @id 를 사용하여 기본키(PK)로 지정한다.
     * Table 생성시 해당 필드를 PK, AUTO_INCREMENT로 설정하였기때문에 직접할당 방식이 아닌,
     * 자동으로 생성되도록 하기위해 @GeneratedValue를 사용한다
     * GenerationType.IDENTITY는 기본 키 생성을 데이터베이스에 위임하는방식.
     * GeneratedValue는 여러 strategy가 있다.
     * IDENTITY
     * SEQUENCE
     * TABLE
     * AUTO
     **/
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long mbrNo;
    private String id;
    private String name;
    @Builder
    public Member(String id, String name) {
        this.id = id; this.name = name;
    }

}
