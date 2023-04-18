package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//단순히 넣고 빼는 repository 느낌
public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); //null로 반환될 때
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
