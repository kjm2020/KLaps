package com.kds.cop.klap.member;
import java.util.List;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import com.querydsl.jpa.JPQLQuery;
import com.kds.cop.klap.member.Member;

//public class MemberRepository extends QuerydslRepositorySupport implements IntentRepositoryCustom {
public class MemberRepository extends QuerydslRepositorySupport {

    public MemberRepository() {
        super(Member.class);
    }

    @Override
    public List<Member> selectByCategoryAndName(String category, String name) {

        /*QIntentEntity intent = QIntentEntity.intentEntity;

        JPQLQuery<Member> query = from(intent)
                .where(intent.memberName.contains(name));*/

        return query.fetch();
    }

}
